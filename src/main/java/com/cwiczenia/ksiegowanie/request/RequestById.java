package com.cwiczenia.ksiegowanie.request;

public class RequestById {
    private Long id;

    public RequestById(Long id) {
        this.id = id;
    }

    public RequestById() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
