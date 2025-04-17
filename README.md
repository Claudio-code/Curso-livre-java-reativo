# Sobre conteudo do curso
- Ensinando como usar a programação reativa com Reactor em Java.


## Conseitos basicos do reactor

- O Reactor tem duas implementações de Publishers que são Mono e Flux.
    - Mono:
        - Pode enviar 0 ou 1 item.
        - Pode ser seguido pelos métodos onComplete / onError.
    - Flux:
        - Pode enviar 0 ou N itens.
        - Pode ser seguido pelos métodos onComplete / onError também.

---

### Schedulers != Execução-paralela

- Todas as operações são sempre executadas de forma sequencial.
- Os dados são processados um por um através de 1 thread no ThreadPool para um Subscriber.
- Schedulers.parallel() - É uma pool de threads para tarefas de CPU. Não significa execução paralela.

---

### Flux - create / generate

| Create | Generate |
|--------|----------|
| Aceita um `Consumer<FluxSink<T>>` | Aceita um `Consumer<SynchronousSink<T>>` |
| Consumer é invocado apenas uma vez | Consumer é invocado novamente com base na demanda downstream |
| Consumer pode emitir 0..N elementos imediatamente | Consumer pode emitir apenas um elemento |
| O Publisher pode não estar ciente da velocidade de processamento downstream. Por isso, precisamos fornecer `Estratégia de Overflow` como um parâmetro adicional | O Publisher produz elementos com base na demanda downstream |
| Thread-safe | N/A |
| `fluxSink.requestedFromDownstream()` e `fluxSink.isCancelled()` | N/A |

---

### Schedulers
| Método Schedulers | Uso |
|-------------------|-----|
| BoundedElastic | Chamadas de rede / que consomem tempo |
| Parallel | Tarefas intensivas de CPU |
| Single | Uma única thread dedicada para tarefas únicas |
| Immediate | Thread atual |

---

### Operadores para Agendamento
| Operador | Uso |
|----------|-----|
| subscribeOn | para upstream |
| publishOn | para downstream |

----

### Estratégia de Overflow
| Estratégia | Comportamento |
|------------|---------------|
| Buffer | Manter em memória |
| Drop | Uma vez que a fila esteja cheia, novos itens serão descartados |
| Latest | Uma vez que a fila esteja cheia, mantém 1 item mais recente conforme chega, descarta os antigos |
| Error | Lança erro para o downstream |


- Buffer
    - Com a estratégia BUFFER, como o nome sugere, todos os valores são armazenados em buffer para que o assinante possa receber todos os valores. De acordo com o programa abaixo, o buffer é infinito, então se os valores publicados forem grandes em quantidade e o assinante for muito lento, existe a chance de falta de memória, assim como no Observable.

- Drop
    - A estratégia DROP descarta o valor mais recente seguinte se o downstream não conseguir acompanhar porque é muito lento. Também existem maneiras fornecidas para consumir valores descartados e tratá-los separadamente.

- Latest
    - A estratégia LATEST mantém apenas o valor mais recente, sobrescrevendo qualquer valor anterior se o downstream não conseguir acompanhar porque é muito lento.

- Error
    - A estratégia ERROR lança OverflowException caso o downstream não consiga acompanhar devido à lentidão. O Publisher pode tratar a exceção e garantir que o manipulador de erro seja chamado para que o assinante possa fazer o tratamento no lado do assinante para tais cenários de erro.


---
---

## Stack utilizada
- Java 21 lts
- Gradle
