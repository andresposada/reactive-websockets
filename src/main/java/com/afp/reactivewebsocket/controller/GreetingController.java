package com.afp.reactivewebsocket.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.afp.reactivewebsocket.beans.GreetingRequest;
import com.afp.reactivewebsocket.beans.GreetingResponse;
import com.afp.reactivewebsocket.service.GreetingService;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

/**
 * <p>
 *  
 * </p>
 * 
 * @author Andrés Felipe Posada Ramírez.
 * @since Aug 16, 2020 6:01:47 PM
 * @version 1.0
 *
 */
@RestController
@RequestMapping("old-greetings")
@AllArgsConstructor
public class GreetingController {

  private final GreetingService greetingService;
  
  
  @GetMapping("{name}")
  public Mono<GreetingResponse> greet(@PathVariable String name) {
    return greetingService.greet(new GreetingRequest(name));
  }
  
}
