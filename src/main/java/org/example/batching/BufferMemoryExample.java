package org.example.batching;

import org.example.util.TimeUtil;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class BufferMemoryExample {
    public static void main(String[] args) {
        eventStream()
                .buffer(5)
                .subscribe(item -> System.out.println("sub: " + item));

        TimeUtil.sleepMilleSeconds(6000);
    }

    private static Flux<String> eventStream() {
        return Flux
                .interval(Duration.ofMillis(300))
                .map(i -> "Event" + i);
    }
}
