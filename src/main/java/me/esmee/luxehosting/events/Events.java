package me.esmee.luxehosting.events;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Events {

    public List<Class<?>> eventListeners = new ArrayList<>();

    public Events() {
        this.registerEvents();
    }

    private void registerEvents() {
        File dir = new File("./src/main/java/me/esmee/luxehosting/events/eventlisteners");
        File[] directoryListing = dir.listFiles();
        if (directoryListing == null) {
            System.out.println("No files found in /events directory");
            return;
        }

        for (File child : directoryListing) {
            try {
                Class<?> eventClass = Class.forName(
                        "me.esmee.luxehosting.events.eventlisteners." +
                                child.getName().replace(".java", "")
                );
                this.eventListeners.add(eventClass);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

}
