package org.example.mono;

import com.github.javafaker.Faker;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class NoBlockingExample {
    public static void main(String[] args) {
        getName()
//                .subscribeOn(Schedulers.parallel())
                .subscribe(s -> System.out.println(s+ " \n\n"));
        getName()
//                .subscribeOn(Schedulers.parallel())
                .subscribe(s -> System.out.println(s + " \n\n"));
        getName()
//                .subscribeOn(Schedulers.parallel())
                .subscribe(s -> System.out.println(s + " \n\n"));
        getName()
//                .subscribeOn(Schedulers.parallel())
                .subscribe(s -> System.out.println(s + " \n\n"));

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static Mono<String> getName()  {
        System.out.println("iniciando o metodo");
        return Mono.fromSupplier(() -> {
            System.out.println("gerando o nome...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return Faker.instance().name().fullName();
        });
    }
}
