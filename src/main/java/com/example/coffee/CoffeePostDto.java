package com.example.coffee;

public class CoffeePostDto { // 커피 정보 등록 시 Request Body를 받을 때 사용되는 클래스
    private String engName;
    private String korName;
    private int price;

    public String getEngName() {
        return engName;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }

    public String getKorName() {
        return korName;
    }

    public void setKorName(String korName) {
        this.korName = korName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
