package ru.yandex.practicum.courier;

public class LoginCourierResponse {
    private Integer id;

    public LoginCourierResponse(Integer id) {
        this.id = id;
    }

    public LoginCourierResponse() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
