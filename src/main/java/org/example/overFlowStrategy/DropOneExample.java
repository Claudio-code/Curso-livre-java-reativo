package org.example.overFlowStrategy;

import org.example.util.TimeUtil;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class DropOneExample {
    public static void main(String[] args) {
        // A fila do Reactor tem tamanho padrão de 256 itens. Quando esse tamanho é atingido, a fila descarta todos os itens e envia novos.
        // Por exemplo, defino o tamanho da fila em 6
        System.setProperty("reactor.bufferSize.small", "6");

        Flux.create(fluxsink -> {
                    for (int i = 0; i < 501; i++) {
                        fluxsink.next(i);
                        System.out.println("Pushed: " + i);
                        TimeUtil.sleepMilleSeconds(1);
                    }
                    fluxsink.complete();
                })
                .onBackpressureDrop()
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(i -> {
                    TimeUtil.sleepMilleSeconds(10);
                })
                .subscribe(i -> printThreadName("item: " + i));

        TimeUtil.sleepSeconds(10000);
    }

    private static void printThreadName(String msg) {
        System.out.println(msg + "\t\t: Thread: " + Thread.currentThread().getName());
    }
}
