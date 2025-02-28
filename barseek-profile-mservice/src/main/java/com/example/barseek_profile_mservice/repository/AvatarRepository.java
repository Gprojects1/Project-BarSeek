package com.example.barseek_profile_mservice.repository;

import com.example.barseek_profile_mservice.model.entity.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvatarRepository extends JpaRepository<Avatar,Long> {

}
