package com.racartech.library.rctandroidx.database.offline

import android.os.Environment
import java.io.File

class OfflineStore private constructor() {

    init {
        // This runs only once when the singleton instance is created
        createDatabaseDirectory()
    }

    private fun createDatabaseDirectory() {

    }

    companion object {
        @Volatile
        private var INSTANCE: OfflineStore? = null

        fun getInstance(): OfflineStore {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: OfflineStore().also { INSTANCE = it }
            }
        }
    }
}
