package com.flwaway.Service;


import com.flwaway.Document.BookingDetails;
import com.flwaway.Repository.BookingDetailsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Service
@Slf4j
@CacheConfig(cacheNames = {"bookingdetails"})
public class BookingDetailsService {

    @Autowired
    BookingDetailsRepository bookingRepo;

    public Flux<BookingDetails> findAllBookings() {
        return bookingRepo.findAll();
    }


    public Mono<BookingDetails> createBooking(BookingDetails detailsObj){
        return bookingRepo.save(detailsObj);
    }

    @Cacheable(key = "#bookingId",value = "bookingId")
    public Mono<BookingDetails> findBookingsById(String bookingId){
        System.out.print("Made a Database call for findBooking()");
        return bookingRepo.findById(bookingId);


    }


    public Mono<BookingDetails> updateBooking(String bookingId,BookingDetails bookingObj)
    {
        return bookingRepo.findById(bookingId)
                .flatMap(current -> {
                    current.setPassangername(bookingObj.getPassangername());
                    current.setEmail(bookingObj.getEmail());
                    current.setAllotedSeat(bookingObj.getAllotedSeat());
                    current.setGender(bookingObj.getGender());
                    return bookingRepo.save(current);
                });
    }

    public Mono<Void> deleteBooking(String bookingId) {

        return bookingRepo.deleteById(bookingId);
    }


}
