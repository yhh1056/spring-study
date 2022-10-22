package com.example.common;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EventFailRepository {

    private static final List<FailEvent> events = new ArrayList<>();

    public static void add(final FailEvent failEvent) {
        events.add(failEvent);
    }

    public static void publish() {
        for (FailEvent event : events) {
            if (!event.isPublished()) {
                log.info("====== process event =======");
                Events.publish(event.getEvent());
                event.isPublish();
            }
        }
    }

    public static void delete() {
        events.removeIf(FailEvent::isPublished);
    }

    public static void size() {
        System.out.println(events.size());
    }
}
