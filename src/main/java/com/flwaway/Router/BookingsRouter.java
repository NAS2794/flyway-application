//package com.flwaway.Router;
//
//import com.flwaway.Handler.BookingsHandler;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.reactive.function.server.RouterFunction;
//import org.springframework.web.reactive.function.server.RouterFunctions;
//import org.springframework.web.reactive.function.server.ServerResponse;
//
//import static org.springframework.http.MediaType.APPLICATION_JSON;
//import static org.springframework.web.reactive.function.server.RequestPredicates.*;
//import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
//
//@Configuration
//@Slf4j
//public class BookingsRouter {
//
//    @Bean
//    public RouterFunction<ServerResponse> itemsRoute(BookingsHandler bookingsHandler){
//
//        return RouterFunctions
//                .route(GET("/booking").and(accept(APPLICATION_JSON))
//                        ,bookingsHandler::getAllBooking)
//                .andRoute(GET("/booking"+"/{id}").and(accept(APPLICATION_JSON))
//                        ,bookingsHandler::getOneBooking)
//                .andRoute(POST("/booking").and(accept(APPLICATION_JSON))
//                        ,bookingsHandler::createBooking)
//                .andRoute(DELETE("/booking"+"/{id}").and(accept(APPLICATION_JSON))
//                        ,bookingsHandler::deleteBooking)
//                .andRoute(PUT("/booking"+"/{id}").and(accept(APPLICATION_JSON))
//                        ,bookingsHandler::updateBooking);
//    }
//
//}
