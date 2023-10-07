package an.y.ledov.coding.challenge.phone.booking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class PhoneBookingApp {

	public static void main(String[] args) {
		SpringApplication.run(PhoneBookingApp.class, args);
	}

}
