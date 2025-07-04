package com.napora.napora.web;

import java.io.IOException;
import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.napora.napora.service.ListingService;
import com.napora.napora.web.dto.ListingDto;

@Controller
@RequestMapping("/listings")
public class ListingController {
    private final ListingService listingService;

    public ListingController(ListingService listingService) {
        this.listingService = listingService;
    }

    @GetMapping
    public String listAll(Model model) {
        model.addAttribute("listings", listingService.findAll());
        return "listings/all";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("listingDto", new ListingDto());
        return "listings/new";
    }

    @PostMapping
    public String create(@ModelAttribute("listingDto") @Validated ListingDto dto,
                         BindingResult br,
                         Principal principal) throws IOException {
        if (br.hasErrors()) return "listings/new";
        listingService.save(dto, principal.getName());
        return "redirect:/listings";
    }

    @GetMapping("/{id}")
    public String details(@PathVariable Long id, Model model) {
        var opt = listingService.findById(id);
        if (opt.isEmpty()) throw new RuntimeException("Nie znaleziono ogłoszenia");
        model.addAttribute("listing", opt.get());
        return "listings/details";
    }
}
