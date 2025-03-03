package com.racartech.library.rctandroid.filebase;

import android.content.Context;

import com.racartech.library.rctandroid.file.RCTdirectory;
import com.racartech.library.rctandroid.file.RCTfile;
import com.racartech.library.rctandroid.security.RCThashing;

import java.util.ArrayList;

public class FileBase {

    private Context context;
    private String root_directory;
    private String int_dir;


    private String image_dir = "images";
    private String video_dir = "videos";
    private String audio_dir = "audios";
    private String document_dir = "documents";
    private String file_dir = "files";
    private String json_dir = "jsons";
    private String metadata_filename = "metadata.json";


    public static final int FILE_TYPE_FILE = 0;
    public static final int FILE_TYPE_IMAGE = 1;
    public static final int FILE_TYPE_VIDEO = 2;
    public static final int FILE_TYPE_AUDIO = 3;
    public static final int FILE_TYPE_DOCUMENT = 4;
    public static final int FILE_TYPE_JSON = 5;



    public FileBase(Context context){
        this.context = context;
        this.root_directory = "document_base";
        this.int_dir = RCTfile.getDir_IntAppFiles(context);
    }

    public FileBase(Context context, String root_directory){
        this.context = context;
        this.root_directory = root_directory;
        this.int_dir = RCTfile.getDir_IntAppFiles(context);
    }

    private void initialize(){
        String int_path = RCTfile.getDir_IntAppFiles(context).concat("/");
        String root_dir_path = int_path.concat(this.root_directory).concat("/");
        ArrayList<String> default_dir_paths = new ArrayList<>();
        default_dir_paths.add(root_dir_path.concat(image_dir));
        default_dir_paths.add(root_dir_path.concat(video_dir));
        default_dir_paths.add(root_dir_path.concat(audio_dir));
        default_dir_paths.add(root_dir_path.concat(document_dir));
        default_dir_paths.add(root_dir_path.concat(file_dir));
        default_dir_paths.add(root_dir_path.concat(json_dir));
        for(int index = 0; index<default_dir_paths.size(); index++){
            RCTdirectory.createDirectory(default_dir_paths.get(index));
            RCTfile.createFile(default_dir_paths.get(index).concat("/").concat(metadata_filename));
        }
    }

    private boolean add(String seed, String filepath, int file_type, boolean delete_source){
        try {
            String destination_dir = null;
            String filename = RCThashing.sha256(seed);

            String int_path = RCTfile.getDir_IntAppFiles(context).concat("/");
            String root_dir_path = int_path.concat(this.root_directory).concat("/");
            switch (file_type){
                case FILE_TYPE_FILE:
                    destination_dir = root_dir_path.concat("/").concat(file_dir);
                    break;
                case FILE_TYPE_IMAGE:
                    destination_dir = root_dir_path.concat("/").concat(image_dir);
                    break;
                case FILE_TYPE_VIDEO:
                    destination_dir = root_dir_path.concat("/").concat(video_dir);
                    break;
                case FILE_TYPE_AUDIO:
                    destination_dir = root_dir_path.concat("/").concat(audio_dir);
                    break;
                case FILE_TYPE_DOCUMENT:
                    destination_dir = root_dir_path.concat("/").concat(document_dir);
                    break;
                case FILE_TYPE_JSON:
                    destination_dir = root_dir_path.concat("/").concat(json_dir);
                    break;
            }

            if(delete_source){
                RCTfile.moveFile(filepath, destination_dir);
                //TODO - Rename File
            }else{
                RCTfile.createFile(filepath,destination_dir);
                //TODO - Rename File
            }

        }catch (Exception ignored){
            return false;
        }
        return false;
    }







}
