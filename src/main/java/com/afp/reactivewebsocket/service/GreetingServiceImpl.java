package com.afp.reactivewebsocket.service;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.afp.reactivewebsocket.beans.GreetingRequest;
import com.afp.reactivewebsocket.beans.GreetingResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * <p>
 *  
 * </p>
 * 
 * @author Andrés Felipe Posada Ramírez.
 * @since Aug 16, 2020 6:00:53 PM
 * @version 1.0
 *
 */
@Service
public class GreetingServiceImpl implements GreetingService {

  /* (non-Javadoc)
   * @see com.afp.reactivehttp.service.GreetingService#greet(com.afp.reactivehttp.beans.GreetingRequest)
   */
  @Override
  public Mono<GreetingResponse> greet(GreetingRequest request) {
    return Mono.just(createGreet(request.getName()));
  }

  @Override
  public Flux<GreetingResponse> greetMany(GreetingRequest request) {
    return Flux.fromStream(Stream.generate(() -> createGreet(request.getName()))).delayElements(Duration.ofSeconds(1));
  }
  
  private GreetingResponse createGreet(String name) {
    return new GreetingResponse("Hello " + name +  " @ " + Instant.now()); 
  }

}
