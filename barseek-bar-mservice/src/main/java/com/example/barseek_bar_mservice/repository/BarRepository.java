package com.example.barseek_bar_mservice.repository;

import com.example.barseek_bar_mservice.model.Bar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BarRepository extends JpaRepository<Bar,Long> {

    Optional<List<Bar>> findAllByName(String name);

}
