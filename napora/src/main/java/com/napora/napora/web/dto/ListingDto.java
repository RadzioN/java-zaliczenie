package com.napora.napora.web.dto;

import java.math.BigDecimal;

import org.springframework.web.multipart.MultipartFile;

public class ListingDto {
    private String title;
    private String description;
    private BigDecimal price;
    private String address;
    private MultipartFile image;
    private String phoneNumber;

    public ListingDto() {

    }

    public ListingDto(String title, String description, BigDecimal price, String address, MultipartFile image, String phoneNumber) {
        super();
        this.title = title;
        this.description = description;
        this.price = price;
        this.address = address;
        this.image = image;
        this.phoneNumber = phoneNumber;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public MultipartFile getImage() {
        return image;
    }
    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
