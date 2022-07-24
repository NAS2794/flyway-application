package com.flwaway.Document;


import com.flwaway.Validations.IsValidFlight;
import com.flwaway.Validations.IsValidGender;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.redis.core.RedisHash;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

@Document(collection = "bookingdetails")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@RedisHash
public class BookingDetails implements Serializable {

    @Id
    private String bookingId;

    @NotNull
    @IsValidFlight
    private String flightNumber;


    @NotEmpty(message = "{validation.passenger.notEmpty}")
    @NotBlank(message = "{validation.passenger.notEmpty}")
    private String passangername;

    @NotEmpty(message = "{validation.mail.notEmpty}")
    @Email(message = "{validation.mail.format}")
    private String email;


    @NotNull
    private Date travelDateTime = new Date();


    @NotEmpty(message = "{validation.pickup.size}")
    @Size(max = 3,message = "{validation.pickup.size}")
    private String pickup;


    @NotEmpty(message = "{validation.destination.size}")
    @Size(max = 3,message = "{validation.destination.size}")
    private String destination;

    @NotNull(message = "{validation.flight.status}")
    private boolean intlFlight;

    @NotNull(message = "{validation.allotedseat.number}")
    @NotEmpty(message = "{validation.allotedseat.number}")
    private String allotedSeat;

    @NotNull(message = "{validation.meal.status}")
    private boolean mealStatus;

    @IsValidGender
    private String gender;

    public BookingDetails(@NotNull String flightNumber,
                          @NotEmpty @NotBlank String passangername,
                          @Email String email,
                          @NotEmpty @Size(max = 3) String pickup,
                          @NotEmpty @Size(max = 3) String destination,
                          @NotNull boolean intlFlight,
                          @NotNull String allotedSeat,
                          @NotNull boolean mealStatus,
                          String gender) {
        this.flightNumber = flightNumber;
        this.passangername = passangername;
        this.email = email;
        this.pickup = pickup;
        this.destination = destination;
        this.intlFlight = intlFlight;
        this.allotedSeat = allotedSeat;
        this.mealStatus = mealStatus;
        this.gender = gender;
    }
}
