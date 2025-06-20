package com.software.hereisdog.domain;

public class Place {

    private Long id;
    private String name;
    private String address;
    private String description;

    private String ownerUsername;   
    private String openingHours;   
    private String phoneNumber;     
    private String type;


    public Place() {}

    public Place(Long id, String name, String address, String description,
                 String ownerUsername, String openingHours, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
        this.ownerUsername = ownerUsername;
        this.openingHours = openingHours;
        this.phoneNumber = phoneNumber;
    }

    // Getter & Setter (필수 필드만)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public void setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
