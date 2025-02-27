package com.example.barseek_profile_mservice.service;

import com.example.barseek_profile_mservice.model.entity.Avatar;
import com.example.barseek_profile_mservice.repository.AvatarRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AvatarService {

    @Autowired
    private final AvatarRepository avatarRepository;

    @Transactional
    public void deleteAvatarById(Long avatarId) {
        avatarRepository.deleteById(avatarId);
    }

    @Transactional
    public void addNew(Avatar newAvatar) {
        avatarRepository.save(newAvatar);
    }

    public void deleteAvatarFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        if(Files.exists(path)) {
            Files.delete(path);
        }
    }

    public String generateAvatarFileName(String originalFileName) {
        return UUID.randomUUID() + "_" + originalFileName;
    }


}
