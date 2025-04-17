package org.example.mono;

import reactor.core.publisher.Mono;

public class MonoJust {
    /**
     *  myPromise
     *     .then((result) => {
     *       console.log("Success:", result);
     *     })
     *     .catch((error) => {
     *       console.log("Failure:", error);
     *     })
     *     .finally(() => {
     *       console.log("Promise completed, cleanup code here");
     *     });
     */
    public static void main(String[] args) {
        // publisher
        Mono<Integer> mono = Mono.just(1);
        System.out.println(mono);

        mono
                .onErrorComplete(throwable -> {
                    System.out.println("Erro: " + throwable.getMessage());
                    return true;
                })
                .doFinally(signalType -> {
                    System.out.println("Finalizando o fluxo");
                })
                .subscribe(integer -> {
                    System.out.println("integer: " + integer);
                });
    }
}
