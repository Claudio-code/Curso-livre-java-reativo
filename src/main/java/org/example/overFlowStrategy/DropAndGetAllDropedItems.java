package org.example.overFlowStrategy;

import org.example.util.TimeUtil;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;

public class DropAndGetAllDropedItems {
    public static void main(String[] args) {
        // se você definir o valor, a fila suportará todos os 501 itens
//         System.setProperty("reactor.bufferSize.small", "3");

        var list = new ArrayList<>();
        Flux.create(fluxsink -> {
                    for (int i = 0; i < 501; i++) {
                        fluxsink.next(i);
                        System.out.println("Pushed: " + i);
                        TimeUtil.sleepMilleSeconds(1);
                    }
                    fluxsink.complete();
                })
                .onBackpressureDrop(list::add)
                .publishOn(Schedulers.boundedElastic())
//                .subscribeOn(Schedulers.boundedElastic())
                .doOnNext(i -> {
                    TimeUtil.sleepMilleSeconds(10);
                })
                .subscribe(i -> System.out.println("item: " + i));

        TimeUtil.sleepSeconds(20000);
        System.out.println("Droped items: " + list.size());
    }
}
