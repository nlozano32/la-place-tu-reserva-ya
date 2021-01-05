package com.boot.bookingrestaurantapi.services.impl;




import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.boot.bookingrestaurantapi.entities.Reservation;
import com.boot.bookingrestaurantapi.entities.Restaurant;
import com.boot.bookingrestaurantapi.entities.Turn;
import com.boot.bookingrestaurantapi.exceptions.BookingException;
import com.boot.bookingrestaurantapi.exceptions.InternalServerErrorException;
import com.boot.bookingrestaurantapi.exceptions.NotFoundException;
import com.boot.bookingrestaurantapi.jsons.CreateReservationRest;
import com.boot.bookingrestaurantapi.jsons.ReservationRest;
import com.boot.bookingrestaurantapi.repositories.ReservationRepository;
import com.boot.bookingrestaurantapi.repositories.RestaurantRepository;
import com.boot.bookingrestaurantapi.repositories.TurnRepository;
import com.boot.bookingrestaurantapi.services.ReservationService;


@Service
public class ReservationServiceImpl implements ReservationService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationServiceImpl.class);

	@Autowired
	RestaurantRepository restaurantRepository;
	
	@Autowired
	private TurnRepository turnRepository;
	
	@Autowired
	private ReservationRepository reservationRepository;

	public ReservationRest getReservation(Long reservationId) throws BookingException {

		//TODO completar 
		return null;
	}

	public String createReservation(CreateReservationRest createReservationRest) throws BookingException {

		final Restaurant restaurantId = restaurantRepository.findById(createReservationRest.getRestaurantId())
				.orElseThrow(() -> new NotFoundException("RESTAURANT_NOT_FOUND", "RESTAURANT_NOT_FOUND"));
		
		final Turn turn = turnRepository.findById(createReservationRest.getTurnId())
				.orElseThrow(() -> new NotFoundException("TURN_NOT_FOUND", "TURN_NOT_FOUND"));
		
		Random rand = new Random();
		int upperbound = 2000;
		int int_random = rand.nextInt(upperbound); 
		
		// String locator = generateLocator(restaurantId, createReservationRest);
		
		String locator = restaurantId.getId()+Integer.toString(rand.nextInt(upperbound)) ; 

		final Reservation reservation = new Reservation();
		reservation.setLocator(locator);
		reservation.setPerson(createReservationRest.getPerson());
		reservation.setDate(createReservationRest.getDate());
		reservation.setRestaurant(restaurantId);
		reservation.setTurn(turn.getName());
		

		try {
			
			reservationRepository.save(reservation);
			
		}catch(final Exception e) {
			
			 LOGGER.error("INTERNAL_SERVER_ERROR", e);
			 throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
		}
		
		return locator;
	}

	private String generateLocator(Restaurant restaurantId, CreateReservationRest createReservationRest)
			throws BookingException {
		return restaurantId.getName() + createReservationRest.getTurnId();
	}

}
