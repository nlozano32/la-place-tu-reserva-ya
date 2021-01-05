package com.boot.bookingrestaurantapi.jsons;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FoodRest {
	
	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("foodName")
	private String foodName;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("price")
	private Long price;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

}
