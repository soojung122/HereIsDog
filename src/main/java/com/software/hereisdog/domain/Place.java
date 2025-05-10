package com.software.hereisdog.domain;

/**
 * 장소(Place) 정보를 담는 클래스
 */
public class Place {

    private Long id;
    private String name;
    private String address;
    private String description;

    public Place() {}

    public Place(Long id, String name, String address, String description) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
    }

    // Getter & Setter
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
}
