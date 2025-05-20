package com.software.hereisdog.controller;

//Validator 인터페이스 이용을 위해 Valid annotation 제거
//import jakarta.validation.constraints.NotEmpty;
//import jakarta.validation.constraints.Size;

/**
 * 리뷰 등록 시 입력받는 데이터
 */
public class ReviewForm {

   // @NotEmpty(message = "리뷰 내용을 입력하세요.")
    //@Size(max = 500, message = "리뷰는 500자 이내로 작성해주세요.")
    private String content;

    // 기본 생성자
    public ReviewForm() {}

    // Getter & Setter
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
