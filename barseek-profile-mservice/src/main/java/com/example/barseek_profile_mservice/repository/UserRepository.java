package com.example.barseek_profile_mservice.repository;

import com.example.barseek_profile_mservice.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserProfile,Long> {

}
