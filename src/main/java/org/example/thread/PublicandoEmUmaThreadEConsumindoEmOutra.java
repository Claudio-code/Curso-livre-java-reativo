package org.example.thread;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class PublicandoEmUmaThreadEConsumindoEmOutra {
    public static void main(String[] args) {
        final var flux = Flux.create(fluxSink -> {
                    printThreadName("create");
                    for (int i = 0; i < 4; i++) {
                        fluxSink.next(i);
                    }
                    fluxSink.complete();
                })
                .doOnNext(i -> printThreadName("next: " + i));

        flux
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(i -> printThreadName("next 2: " + i))
                .publishOn(Schedulers.parallel())
                .subscribe(v -> printThreadName("subscribe " + v));

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void printThreadName(String msg) {
        System.out.println(msg + "\t\t: Thread: " + Thread.currentThread().getName());
    }
}
