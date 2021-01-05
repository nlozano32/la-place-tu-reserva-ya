package com.boot.bookingrestaurantapi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boot.bookingrestaurantapi.entities.Food;


@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
	
	Optional<Food> findById(Long id);
	
	Optional<Food> findByRestaurantId(Long restaurantId);

}
