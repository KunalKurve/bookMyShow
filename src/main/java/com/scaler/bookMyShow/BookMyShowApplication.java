package com.scaler.bookMyShow;

import com.scaler.bookMyShow.controller.UserController;
import com.scaler.bookMyShow.dto.requests.SignupRequestDto;
import com.scaler.bookMyShow.dto.responses.SignupResponseDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BookMyShowApplication implements CommandLineRunner {

	@Autowired
	UserController userController;

	public static void main(String[] args) {
		SpringApplication.run(BookMyShowApplication.class, args);

		// Design a class diagram for BookMyShow Requirements:
		// 1. We will have multiple cities.
		// 2. Each city has mulitple theatres.
		// 3. Each theatre has multiple screens.
		// 4. Each screen has multiple seats.
		// 5. There are different types of seats: PLATINUM, GOLD, SILVER.
		// 6. Each screen will have multiple shows.
		// 7. Every show will be for a movie and will have a start time and end time.
		// 8. Each show depending upon the day, time
		// and seat type will have different prices.
		// 9. Movies, shows and screens will support features like
		// 2D, 3D, DOLBY_IMAX, DOLBY_VISION etc.
		// 10. We integrate with Razorpay to handle payments.
		// 11. Users should be able to book multiple seats for a
		// show via the app by paying for them at the time of booking.
	}

	@Override
	public void run(String... args) throws Exception {
		SignupRequestDto requestDto = new SignupRequestDto();
		requestDto.setName("Virat Kohli");
		requestDto.setEmail("viratkohli18@zoho.in");
		requestDto.setPhone("1234567890");
		requestDto.setPassword("virushkaRocks");

		SignupResponseDto responseDto = userController.signUp(requestDto);
		System.out.println(responseDto.getUserId());
		System.out.println(responseDto.getResponseStatus());
	}
}
