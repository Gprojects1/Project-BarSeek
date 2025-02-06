package com.example.barseek_bar_mservice.repository;


import com.example.barseek_bar_mservice.model.Drink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrinkRepository extends JpaRepository<Drink,Long> {

}
