package com.flwaway.Repository;

import com.flwaway.Document.BookingDetails;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface BookingDetailsRepository extends ReactiveMongoRepository<BookingDetails,String> {


}
