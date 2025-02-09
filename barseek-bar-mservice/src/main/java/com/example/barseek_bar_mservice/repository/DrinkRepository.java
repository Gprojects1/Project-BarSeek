package com.example.barseek_bar_mservice.repository;


import com.example.barseek_bar_mservice.model.entity.Drink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DrinkRepository extends JpaRepository<Drink,Long> {

    Optional<Drink> findByIdAndBarId(Long drinkId, Long barId);

    void deleteByBarIdAndId(Long barId, Long drinkId);

    Optional<List<Drink>> findAllByBarId(Long id);
}
