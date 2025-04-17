package org.example;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.List;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        // execução concorente dentro da thread principal do programa
//        var lista = IntStream
//                .range(1, 1501)
//                .boxed()
//                .toList();
//
//        lista.parallelStream()
//                .forEach(System.out::println);


        // execução paralela em outra thread fora da thread principal
        var fluxInt = Flux.fromStream(IntStream
                .range(1, 1501)
                .boxed());

        fluxInt
//                .subscribeOn(Schedulers.boundedElastic())
//                .parallel()
//                .runOn(Schedulers.boundedElastic())
                .subscribe(integer -> System.out.println("First print : " + integer));

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}