package com.napora.napora.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.napora.napora.model.Listing;
import com.napora.napora.model.User;
import com.napora.napora.repository.ListingRepository;
import com.napora.napora.repository.UserRepository;
import com.napora.napora.web.dto.ListingDto;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ListingServiceImpl implements ListingService {
    private final ListingRepository listingRepo;
    private final UserRepository userRepo;

    @Value("${app.upload.dir:uploads}")
    private String uploadDir;

    public ListingServiceImpl(ListingRepository listingRepo, UserRepository userRepo) {
        this.listingRepo = listingRepo;
        this.userRepo = userRepo;
    }

    @Override
    public Listing save(ListingDto dto, String username) throws IOException {
        User owner = userRepo.findByEmail(username);
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) Files.createDirectories(uploadPath);

        MultipartFile file = dto.getImage();
        String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path target = uploadPath.resolve(filename);
        file.transferTo(target);

        Listing l = new Listing();
        l.setTitle(dto.getTitle());
        l.setDescription(dto.getDescription());
        l.setPrice(dto.getPrice());
        l.setAddress(dto.getAddress());
        l.setImagePath("/" + uploadDir + "/" + filename);
        l.setPhoneNumber(dto.getPhoneNumber());
        l.setOwner(owner);

        return listingRepo.save(l);
    }

    @Override
    public List<Listing> findAll() {
        return listingRepo.findAllByOrderByIdDesc();
    }

    @Override
    public Optional<Listing> findById(Long id) {
        return listingRepo.findById(id);
    }
}
