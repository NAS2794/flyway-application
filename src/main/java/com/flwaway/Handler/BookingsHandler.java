//package com.flwaway.Handler;
//
//import com.flwaway.Document.BookingDetails;
//import com.flwaway.Exception.GlobalException;
//import com.flwaway.Repository.BookingDetailsRepository;
//import com.flwaway.Service.BookingDetailsService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.reactive.function.server.ServerRequest;
//import org.springframework.web.reactive.function.server.ServerResponse;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//import javax.validation.Valid;
//
//import static org.springframework.web.reactive.function.BodyInserters.fromObject;
//
//@Configuration
//@Slf4j
//public class BookingsHandler {
//
//    @Autowired
//    BookingDetailsRepository bookingRepo;
//
//    static Mono<ServerResponse> notFound = ServerResponse.notFound().build();
//
//    public Mono<ServerResponse> getAllBooking(ServerRequest serverRequest) {
//
//        System.out.println("get all booking():db call");
//
//
//          Flux<BookingDetails> resp = bookingRepo.findAll();
//
//
//
//        return ServerResponse.ok()
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(resp, BookingDetails.class);
//
//    }
//
//    public Mono<ServerResponse> getOneBooking(ServerRequest serverRequest) {
//
//        System.out.println("get one booking():db call");
//
//        String id = serverRequest.pathVariable("id");
//        Mono<BookingDetails> itemMono = bookingRepo.findById(id);
//
//        return itemMono.flatMap(item ->
//                ServerResponse.ok()
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .body(fromObject(item)))
//                .switchIfEmpty(notFound);
//
//    }
//
//    public Mono<ServerResponse> createBooking(ServerRequest serverRequest) {
//
//        System.out.println("create booking():db call");
//
//        Mono<BookingDetails> itemTobeInserted = serverRequest.bodyToMono(BookingDetails.class);
//
//        return itemTobeInserted.flatMap(item ->
//                ServerResponse.ok()
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .body(bookingRepo.save(item), BookingDetails.class));
//
//    }
//
//    public Mono<ServerResponse> deleteBooking(ServerRequest serverRequest) {
//
//        System.out.println("delete a booking():db call");
//
//        String id = serverRequest.pathVariable("id");
//        Mono<Void> deleteItem = bookingRepo.deleteById(id);
//
//        return ServerResponse.ok()
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(deleteItem, Void.class);
//    }
//
//    public Mono<ServerResponse> updateBooking(ServerRequest serverRequest) {
//
//        System.out.println("update a booking():db call");
//
//        String id = serverRequest.pathVariable("id");
//
//        Mono<BookingDetails> updatedItem = serverRequest.bodyToMono(BookingDetails.class)
//                .flatMap((newObj) -> {
//
//                    Mono<BookingDetails> itemMono = bookingRepo.findById(id)
//                            .flatMap(current -> {
//                                current.setPassangername(newObj.getPassangername());
//                                current.setEmail(newObj.getEmail());
//                                current.setAllotedSeat(newObj.getAllotedSeat());
//                                current.setGender(newObj.getGender());
//                                return bookingRepo.save(current);
//                            });
//                    return itemMono;
//                });
//
//        return updatedItem.flatMap(item ->
//                ServerResponse.ok()
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .body(fromObject(item)))
//                .switchIfEmpty(notFound);
//
//
//    }
//
//}
