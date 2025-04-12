package az.xecore.appluni.services;

import az.xecore.appluni.models.UploadedFile;
import az.xecore.appluni.repos.UploadedFileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileStorageService {

    private final UploadedFileRepository repository;

    private final Path uploadDir = Paths.get("uploads");

    public UploadedFile storeFile(MultipartFile file) throws IOException {
        if (!Files.exists(uploadDir)) {
            Files.createDirectories(uploadDir);
        }

        String originalFilename = file.getOriginalFilename();
        String storedFilename = UUID.randomUUID() + "_" + originalFilename;
        Path targetPath = uploadDir.resolve(storedFilename);

        Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

        UploadedFile savedFile = UploadedFile.builder()
                .originalFilename(originalFilename)
                .storedFilename(storedFilename)
                .path(targetPath.toString())
                .uploadedAt(LocalDateTime.now())
                .build();

        return repository.save(savedFile);
    }

    public UploadedFile getFileById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("File not found"));
    }
}
