package com.racartech.library.rctandroidx.cloudflare

import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.*
import java.io.File
import java.net.URI
import java.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import kotlin.text.Charsets.UTF_8

object RCTcloudflareR2 {

    data class CloudflareR2Instance(
        val accessKey: String,
        val secretKey: String,
        val endpoint: String, // e.g. https://<accountid>.r2.cloudflarestorage.com
        val bucketName: String
    )

    // --- Retrofit Interface ---
    interface R2Service {
        @PUT("{bucket}/{key}")
        suspend fun uploadFile(
            @Path("bucket") bucket: String,
            @Path(value = "key", encoded = true) key: String,
            @Body body: RequestBody,
            @HeaderMap headers: Map<String, String>
        ): retrofit2.Response<String>

        @GET("{bucket}/{key}")
        suspend fun downloadFile(
            @Path("bucket") bucket: String,
            @Path(value = "key", encoded = true) key: String,
            @HeaderMap headers: Map<String, String>
        ): ResponseBody

        @DELETE("{bucket}/{key}")
        suspend fun deleteFile(
            @Path("bucket") bucket: String,
            @Path(value = "key", encoded = true) key: String,
            @HeaderMap headers: Map<String, String>
        ): retrofit2.Response<String>

        @GET("{bucket}")
        suspend fun listFiles(
            @Path("bucket") bucket: String,
            @Query("list-type") listType: Int = 2,
            @HeaderMap headers: Map<String, String>
        ): String
    }

    private fun getRetrofit(instance: CloudflareR2Instance): R2Service {
        val client = OkHttpClient.Builder().build()
        return Retrofit.Builder()
            .baseUrl(instance.endpoint.trimEnd('/') + "/")
            .client(client)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
            .create(R2Service::class.java)
    }

    // --- AUTHENTICATION ---
    // Cloudflare R2 uses AWS Signature V4 (same as S3)
    private fun buildAuthHeaders(
        instance: CloudflareR2Instance,
        method: String,
        bucket: String,
        key: String
    ): Map<String, String> {
        val now = Date()
        val isoDate = java.time.format.DateTimeFormatter
            .ofPattern("yyyyMMdd'T'HHmmss'Z'")
            .withZone(java.time.ZoneOffset.UTC)
            .format(java.time.Instant.now())

        val dateStamp = isoDate.substring(0, 8)

        val service = "s3"
        val region = "auto" // Cloudflare doesn’t use regions, but must exist
        val host = URI(instance.endpoint).host

        val canonicalUri = "/$bucket/$key"
        val canonicalHeaders = "host:$host\nx-amz-content-sha256:UNSIGNED-PAYLOAD\nx-amz-date:$isoDate\n"
        val signedHeaders = "host;x-amz-content-sha256;x-amz-date"
        val payloadHash = "UNSIGNED-PAYLOAD"
        val canonicalRequest = "$method\n$canonicalUri\n\n$canonicalHeaders\n$signedHeaders\n$payloadHash"

        val algorithm = "AWS4-HMAC-SHA256"
        val credentialScope = "$dateStamp/$region/$service/aws4_request"
        val stringToSign = "$algorithm\n$isoDate\n$credentialScope\n${sha256Hex(canonicalRequest)}"

        val signingKey = getSignatureKey(instance.secretKey, dateStamp, region, service)
        val signature = hmacHex(signingKey, stringToSign)

        val authorizationHeader = "$algorithm Credential=${instance.accessKey}/$credentialScope, SignedHeaders=$signedHeaders, Signature=$signature"

        return mapOf(
            "Authorization" to authorizationHeader,
            "x-amz-content-sha256" to payloadHash,
            "x-amz-date" to isoDate,
            "Host" to host
        )
    }

    private fun sha256Hex(data: String): String {
        val digest = java.security.MessageDigest.getInstance("SHA-256")
        return digest.digest(data.toByteArray(UTF_8)).joinToString("") { "%02x".format(it) }
    }

    private fun hmacHex(key: ByteArray, data: String): String {
        val mac = Mac.getInstance("HmacSHA256")
        mac.init(SecretKeySpec(key, "HmacSHA256"))
        return mac.doFinal(data.toByteArray(UTF_8)).joinToString("") { "%02x".format(it) }
    }

    private fun hmacSHA256(key: ByteArray, data: String): ByteArray {
        val mac = Mac.getInstance("HmacSHA256")
        mac.init(SecretKeySpec(key, "HmacSHA256"))
        return mac.doFinal(data.toByteArray(UTF_8))
    }

    private fun getSignatureKey(key: String, dateStamp: String, region: String, service: String): ByteArray {
        val kDate = hmacSHA256(("AWS4$key").toByteArray(UTF_8), dateStamp)
        val kRegion = hmacSHA256(kDate, region)
        val kService = hmacSHA256(kRegion, service)
        return hmacSHA256(kService, "aws4_request")
    }

    // --- CRUD Operations ---
    suspend fun uploadFile(instance: CloudflareR2Instance, key: String, file: File): Boolean {
        val body = file.asRequestBody("application/octet-stream".toMediaType())
        val headers = buildAuthHeaders(instance, "PUT", instance.bucketName, key)
        val service = getRetrofit(instance)
        val response = service.uploadFile(instance.bucketName, key, body, headers)
        return response.isSuccessful
    }

    suspend fun downloadFile(instance: CloudflareR2Instance, key: String, destination: File): Boolean {
        val headers = buildAuthHeaders(instance, "GET", instance.bucketName, key)
        val service = getRetrofit(instance)
        val body = service.downloadFile(instance.bucketName, key, headers)
        destination.outputStream().use { out -> body.byteStream().copyTo(out) }
        return true
    }

    suspend fun deleteFile(instance: CloudflareR2Instance, key: String): Boolean {
        val headers = buildAuthHeaders(instance, "DELETE", instance.bucketName, key)
        val service = getRetrofit(instance)
        val response = service.deleteFile(instance.bucketName, key, headers)
        return response.isSuccessful
    }

    suspend fun listFiles(instance: CloudflareR2Instance): String {
        val headers = buildAuthHeaders(instance, "GET", instance.bucketName, "")
        val service = getRetrofit(instance)
        return service.listFiles(instance.bucketName, 2, headers)
    }
}



//package com.racartech.library.rctandroidx.cloudflare
//
//import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
//import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
//import software.amazon.awssdk.regions.Region
//import software.amazon.awssdk.services.s3.S3Client
//import software.amazon.awssdk.services.s3.model.*
//import software.amazon.awssdk.core.sync.RequestBody
//import java.io.File
//import java.net.URI
//
//object RCTcloudflareR2 {
//
//    data class CloudflareR2Instance(
//        val accessKey: String,
//        val secretKey: String,
//        val endpoint: String, // Example: https://<accountid>.r2.cloudflarestorage.com
//        val bucketName: String
//    )
//
//    // --- Build client per instance ---
//    private fun getClient(instance: CloudflareR2Instance): S3Client {
//        return S3Client.builder()
//            .endpointOverride(URI.create(instance.endpoint))
//            .region(Region.of("auto")) // Cloudflare ignores this, but must be set
//            .credentialsProvider(
//                StaticCredentialsProvider.create(
//                    AwsBasicCredentials.create(instance.accessKey, instance.secretKey)
//                )
//            )
//            .build()
//    }
//
//    // --- Create / Upload file ---
//    fun uploadFile(instance: CloudflareR2Instance, key: String, file: File): Boolean {
//        return try {
//            val client = getClient(instance)
//            client.putObject(
//                PutObjectRequest.builder()
//                    .bucket(instance.bucketName)
//                    .key(key)
//                    .build(),
//                RequestBody.fromFile(file)
//            )
//            client.close()
//            true
//        } catch (e: Exception) {
//            e.printStackTrace()
//            false
//        }
//    }
//
//    // --- Read / Download file ---
//    fun downloadFile(instance: CloudflareR2Instance, key: String, destination: File): Boolean {
//        return try {
//            val client = getClient(instance)
//            client.getObject(
//                GetObjectRequest.builder()
//                    .bucket(instance.bucketName)
//                    .key(key)
//                    .build(),
//                destination.toPath()
//            )
//            client.close()
//            true
//        } catch (e: Exception) {
//            e.printStackTrace()
//            false
//        }
//    }
//
//    // --- Delete file ---
//    fun deleteFile(instance: CloudflareR2Instance, key: String): Boolean {
//        return try {
//            val client = getClient(instance)
//            client.deleteObject(
//                DeleteObjectRequest.builder()
//                    .bucket(instance.bucketName)
//                    .key(key)
//                    .build()
//            )
//            client.close()
//            true
//        } catch (e: Exception) {
//            e.printStackTrace()
//            false
//        }
//    }
//
//    // --- List all files in bucket ---
//    fun listFiles(instance: CloudflareR2Instance, prefix: String? = null): List<String> {
//        return try {
//            val client = getClient(instance)
//            val request = ListObjectsV2Request.builder()
//                .bucket(instance.bucketName)
//                .apply { prefix?.let { prefix(it) } }
//                .build()
//
//            val response = client.listObjectsV2(request)
//            val keys = response.contents()?.map { it.key() } ?: emptyList()
//            client.close()
//            keys
//        } catch (e: Exception) {
//            e.printStackTrace()
//            emptyList()
//        }
//    }
//
//    // --- Check if file exists ---
//    fun fileExists(instance: CloudflareR2Instance, key: String): Boolean {
//        return try {
//            val client = getClient(instance)
//            client.headObject(
//                HeadObjectRequest.builder()
//                    .bucket(instance.bucketName)
//                    .key(key)
//                    .build()
//            )
//            client.close()
//            true
//        } catch (e: NoSuchKeyException) {
//            false
//        } catch (e: Exception) {
//            e.printStackTrace()
//            false
//        }
//    }
//}
