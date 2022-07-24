package com.flwaway.Controller;

import com.flwaway.Document.BookingDetails;
import com.flwaway.Repository.BookingDetailsRepository;
import com.flwaway.Service.BookingDetailsService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;



@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class BookingDetailsControllerTests {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    BookingDetailsService bookingService;

    @Autowired
    BookingDetailsRepository bookingRepo;

    @Test
    public void testmethod(){
        Assert.assertEquals(0,0);
    }

    @Test
    public void getAllItems(){

        //Mockito use for mocking database
        webTestClient.get().uri("/booking")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(BookingDetails.class);
    }

    @Test
    public void getSingleItem_positive(){

        webTestClient.get().uri("/booking/{id}","6002c4ea0c1b1c003e088aea")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.passangername","Prakriti");
    }

    @Test
    public void getSingleItem_negative(){

        webTestClient.get().uri("/booking/{id}","59ba5389d2b2a")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    public void createItem_positive(){

        BookingDetails item = new BookingDetails("IN-12345","Nikhil","nikhil@gmail.com",
                "LON","NYC",true,"A97",true,"male");

        webTestClient.post().uri("/booking")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(item),BookingDetails.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.bookingId").isNotEmpty()
                .jsonPath("$.passangername").isEqualTo("Nikhil");

    }

    @Test
    public void createItem_negative(){

        BookingDetails item = new BookingDetails("IN-12345","Nikhil","nikhil@gmail.com",
                "LON","NYC",true,"A97",true,"maleeee");

        webTestClient.post().uri("/booking")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(item),BookingDetails.class)
                .exchange()
                .expectStatus().isBadRequest();


    }

    @Test
    public void deleteItem_positive(){

        webTestClient.delete().uri("/booking/{id}","6003ca1e383370358006a9f0")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Void.class);

    }

    @Test
    public void deleteItem_negative(){

        webTestClient.delete().uri("/booking/{id}","6003ca9b6295746fdb04e7da")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound();

    }

    @Test
    public void updateItem_positive(){

        BookingDetails item = new BookingDetails("IN-12345","checking update tests","nikhil@gmail.com",
                "LON","NYC",true,"A97",true,"male");

        webTestClient.put().uri("/booking/{id}","6003ca1e383370358006a9f0")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(item),BookingDetails.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.passangername","checking update tests");

    }


    @Test
    public void updateItem_negative(){

        BookingDetails item = new BookingDetails("IN12345","checking update test","nikhil@gmail.com",
                "LON","NYC",true,"A97",true,"male");

        webTestClient.put().uri("/booking/{id}","6003ca1e383370358006a9f0")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(item),BookingDetails.class)
                .exchange()
                .expectStatus().isBadRequest();

    }
}
