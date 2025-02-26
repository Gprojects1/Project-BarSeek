package com.example.barseek_profile_mservice.repository;

import com.example.barseek_profile_mservice.model.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserProfile,Long> {

    Optional<UserProfile> findByUserId(Long userId);
    void deleteByUserId(Long userId);
}
