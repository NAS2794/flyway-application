package com.flwaway.Controller;


import com.flwaway.Document.BookingDetails;
import com.flwaway.Service.BookingDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@Slf4j
@Validated
public class BookingDetailsController {

    @Autowired
    BookingDetailsService bookingService;

    @GetMapping("/booking")
    public Flux<BookingDetails> getAllBookings() {
        return bookingService.findAllBookings();
    }

    @PostMapping("/booking")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<BookingDetails> createBooking(@Valid @RequestBody BookingDetails bookingObj) {

        return bookingService.createBooking(bookingObj);


    }

    @GetMapping("/booking/{id}")
    public Mono<ResponseEntity<BookingDetails>> findBookingById(@PathVariable(value = "id") String bookingId) {
        return bookingService.findBookingsById(bookingId)
                .map(current -> ResponseEntity.ok(current))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }


    @PutMapping("/booking/{id}")
    public Mono<ResponseEntity<BookingDetails>> updateBooking(@PathVariable(value = "id") String bookingId,
                                                   @Valid @RequestBody BookingDetails bookingObj) {
        return bookingService.updateBooking(bookingId,bookingObj)
               .map(current -> ResponseEntity.ok(current))
               .defaultIfEmpty(ResponseEntity.notFound().build());

    }

    @DeleteMapping("/booking/{id}")
    public Mono<ResponseEntity<Void>> deleteBooking(@PathVariable(value = "id") String bookingId) {

        return bookingService.findBookingsById(bookingId)
                .flatMap(current ->
                        bookingService.deleteBooking(current.getBookingId())
                                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                )
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }



}
