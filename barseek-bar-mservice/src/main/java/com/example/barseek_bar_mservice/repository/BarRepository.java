package com.example.barseek_bar_mservice.repository;

import com.example.barseek_bar_mservice.model.Bar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BarRepository extends JpaRepository<Bar,Long> {

}
