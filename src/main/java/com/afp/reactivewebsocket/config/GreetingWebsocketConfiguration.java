package com.afp.reactivewebsocket.config;

import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;

import com.afp.reactivewebsocket.beans.GreetingRequest;
import com.afp.reactivewebsocket.beans.GreetingResponse;
import com.afp.reactivewebsocket.service.GreetingService;

/**
 * <p>
 * 
 * </p>
 * 
 * @author Andrés Felipe Posada Ramírez.
 * @since Aug 16, 2020 7:00:44 PM
 * @version 1.0
 *
 */
@Configuration
public class GreetingWebsocketConfiguration {

  @Bean
  SimpleUrlHandlerMapping simpleUrlHandlerMapping(WebSocketHandler wsh) {
    return new SimpleUrlHandlerMapping(Map.of("/ws/greetings", wsh), 10);
  }

  @Bean
  WebSocketHandler webSocketHandler(GreetingService greetingService) {
    return session -> {

        var receive = session.receive().map(WebSocketMessage::getPayloadAsText).map(GreetingRequest::new)
            .flatMap(greetingService::greetMany).map(GreetingResponse::getMessage).map(session::textMessage);
        return session.send(receive);
      };
  }

  @Bean
  WebSocketHandlerAdapter webSocketHandlerAdapter() {
    return new WebSocketHandlerAdapter();
  }

}
