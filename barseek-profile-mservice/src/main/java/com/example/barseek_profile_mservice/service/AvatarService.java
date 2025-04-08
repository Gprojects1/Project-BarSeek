package com.example.barseek_profile_mservice.service;

import com.example.barseek_profile_mservice.exception.customExceptions.FileProcessingFailedException;
import com.example.barseek_profile_mservice.model.entity.Avatar;
import com.example.barseek_profile_mservice.repository.AvatarRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AvatarService {

    @Value("${app.avatar.upload-dir}")
    private String uploadDir;

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

    public void deleteAvatarFile(Path filePath) {
        if(Files.exists(filePath)) {
            try {
                Files.delete(filePath);
            } catch (IOException e) {
                throw new FileProcessingFailedException(("Unable to delete file locally"));
            }
        }
        else {throw new FileProcessingFailedException("Unable to find file locally");}
    }

    public Path downloadAvatarFile(MultipartFile file) {
        String fileName = generateAvatarFileName(file.getOriginalFilename());
        Path filePath = Paths.get(uploadDir,fileName);
        try {
            Files.createDirectories(filePath.getParent());
            Files.copy(file.getInputStream(),filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new FileProcessingFailedException("Unable to download file locally");
        }
        return filePath;
    }

    public String generateAvatarFileName(String originalFileName) {
        return UUID.randomUUID() + "_" + originalFileName;
    }

}
