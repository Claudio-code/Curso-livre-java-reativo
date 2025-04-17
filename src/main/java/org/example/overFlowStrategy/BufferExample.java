package org.example.overFlowStrategy;

import org.example.util.TimeUtil;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class BufferExample {
    public static void main(String[] args) {
        Flux.create(fluxsink -> {
                    for (int i = 0; i < 1501; i++) {
                        fluxsink.next(i);
                        System.out.println("Pushed: " + i);
                    }
                    fluxsink.complete();
                })
//                .subscribeOn(Schedulers.boundedElastic())
//                .parallel()
//                .runOn(Schedulers.boundedElastic())
                .doOnNext(i -> {
                    TimeUtil.sleepMilleSeconds(10);
                    System.out.println("timeout item: " + i);
                })
                .subscribe(i -> System.out.println("item: " + i));

        TimeUtil.sleepSeconds(20000);
    }
}
