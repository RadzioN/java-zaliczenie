package com.napora.napora.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.napora.napora.model.Listing;
import com.napora.napora.web.dto.ListingDto;

public interface ListingService {
    Listing save(ListingDto dto, String username) throws IOException;
    List<Listing> findAll();
    Optional<Listing> findById(Long id);
}
