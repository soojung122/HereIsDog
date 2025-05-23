package com.software.hereisdog.domain;

/**
 * 장소에 대한 리뷰를 나타내는 클래스
 */
public class Review {

    private Long id;
    private String content;
    private int rating;
    private Place place;
    private Customer customer;

    public Review() {}

    public Review(Long id, String content, int rating, Place place, Customer customer) {
        this.id = id;
        this.content = content;
        this.rating = rating;
        this.place = place;
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
