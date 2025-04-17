package org.example.flux;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class FluxMultipleSubscribe {

    public static void main(String[] args) {
        Flux<Integer> flux = Flux.just(1, 2, 3, 4);

        flux
                .subscribeOn(Schedulers.boundedElastic(), true)
                .subscribe(integer -> System.out.println("First print : " + integer));


        flux
                .subscribeOn(Schedulers.boundedElastic(), true)
                .subscribe(integer -> System.out.println("Second print : " + integer));

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
