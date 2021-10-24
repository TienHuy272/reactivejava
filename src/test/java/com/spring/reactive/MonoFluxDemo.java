package com.spring.reactive;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MonoFluxDemo {

    //flow subscriber subscribe to publisher (manual)
    //publisher return subscription(auto) link between subscriber and subscriber (auto)
    //publisher call onNext() or onError() return data to subscriber (auto)
    //subscriber consume data onNext(), or onError

    /**
     * mono using for one event
     */
    @Test
    public void testMono() {
        //act as publisher
        Mono<?> monoString = Mono.just("java")
                .then(Mono.error(new RuntimeException("error..")))
                .log();

        //consume event from publisher
        monoString.subscribe(System.out::println, event -> System.out.println("event: " + event.getMessage()));
    }

    /**
     * flux using for multi event
     */
    @Test
    public void testFlux() {
        //act as publisher
        Flux<?> fluxString = Flux.just("java", "js", "vue", "mysql")
                .concatWith(Flux.error(new RuntimeException("error..")))
                .concatWithValues("aws")
                .log();

        //consume event from publisher
        fluxString.subscribe(System.out::println);
    }
}
