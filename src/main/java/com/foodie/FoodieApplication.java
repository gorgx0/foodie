package com.foodie;

import com.foodie.model.Meal;
import com.foodie.model.MealRepository;
import com.foodie.model.Restaurant;
import com.foodie.model.RestaurantRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.nodes.Tag;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.yaml.snakeyaml.nodes.Tag.MAP;

@SpringBootApplication
public class FoodieApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodieApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(RestaurantRepository restaurantRepository, MealRepository mealRepository) {
		return args -> {

			Meal meal01 = new Meal("meal01", true, new BigDecimal(14.44d));
			Meal meal02 = new Meal("meal02", true, new BigDecimal(15.44d));
			Meal meal03 = new Meal("meal03", true, new BigDecimal(12.22d));
			Meal meal04 = new Meal("meal04", true, new BigDecimal(12.33d));

			Restaurant rest01 = new Restaurant("rest01", true, BigDecimal.ZERO);
			Restaurant rest02 = new Restaurant("rest02", true, new BigDecimal(10.54d));
			Restaurant rest03 = new Restaurant("rest03", true, new BigDecimal(5.34d));

			meal01.setRestaurant(rest03);
			meal02.setRestaurant(rest03);
			meal03.setRestaurant(rest01);
			meal04.setRestaurant(rest01);

			restaurantRepository.save(rest01);
			restaurantRepository.save(rest02);
			restaurantRepository.save(rest03);

			mealRepository.save(meal01);
			mealRepository.save(meal02);
			mealRepository.save(meal03);
			mealRepository.save(meal04);


			Yaml yaml = new Yaml();
			String restaurantsYaml = yaml.dumpAs(restaurantRepository.findAll(), MAP, DumperOptions.FlowStyle.AUTO);
			System.out.println("****************************************");
			System.out.println(restaurantsYaml);
			System.out.println("****************************************");
		};
	}
}
