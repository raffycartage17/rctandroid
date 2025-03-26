package com.racartech.library.rctandroid.file;

import java.nio.file.*;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.nio.file.StandardWatchEventKinds.*;

public class RCTdirectoryWatcher {


    public enum EventType {
        CREATED("CREATED"),
        DELETED("DELETED"),
        MODIFIED("MODIFIED"),
        OVERFLOWED("OVERFLOWED");

        private final String description;

        EventType(String description) {
            this.description = description;
        }


        public String toString() {
            return description;
        }
    }


    public interface FileEventListener {
        void onFileChange(EventType eventName, String filePath);
    }

    public static void listenDirectory(
            String directory,
            AtomicBoolean active_listener,
            FileEventListener listener,
            long thread_sleep
    ) {
        Path dir = Paths.get(directory);

        try (WatchService watchService = FileSystems.getDefault().newWatchService()) {
            dir.register(watchService, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);

            while (active_listener.get()) {
                WatchKey key = watchService.poll();
                if (key == null) {
                    try {
                        Thread.sleep(thread_sleep);
                    }catch (Exception ignored){}
                    continue;
                }

                for (WatchEvent<?> event : key.pollEvents()) {
                    WatchEvent.Kind<?> kind = event.kind();
                    Path changedFile = dir.resolve((Path) event.context());

                    if (listener != null) {

                        EventType type = null;

                        switch (kind.name()){
                            case "ENTRY_CREATE":
                                type = EventType.CREATED;
                                break;
                            case "ENTRY_DELETE":
                                type = EventType.DELETED;
                                break;
                            case "ENTRY_MODIFY":
                                type = EventType.MODIFIED;
                                break;
                            case "OVERFLOW":
                                type = EventType.OVERFLOWED;
                                break;
                        }
                        listener.onFileChange(type, changedFile.toString());
                    }
                }

                boolean valid = key.reset();
                if (!valid) {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
