package com.software.hereisdog.controller;


//Validator 인터페이스 이용을 위해 Valid annotation 제거
//import jakarta.validation.constraints.NotEmpty;
//import jakarta.validation.constraints.Size;
//import jakarta.validation.constraints.Min;
//import jakarta.validation.constraints.Max;


/**
 * 리뷰 등록 시 입력받는 데이터
 */
public class ReviewForm {
	
	private String userId;
	
	//@NotEmpty(message = "리뷰 내용을 입력하세요.")
    //@Size(max = 500, message = "리뷰는 500자 이내로 작성해주세요.")
    private String content;

    //@Min(value = 1, message = "평점은 1점 이상이어야 합니다.")
    //@Max(value = 5, message = "평점은 5점 이하여야 합니다.")
    private Integer rating;  // ⭐️ 평점 필드 추가

    // 기본 생성자
    public ReviewForm() {}

    // Getter & Setter
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public Integer getRating() {  // ⭐️ 추가
        return rating;
    }
    public void setRating(Integer rating) {  // ⭐️ 추가
        this.rating = rating;
    }
}
