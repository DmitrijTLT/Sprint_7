package ru.yandex.practicum.orders;

public class OrdersCreateResponse {
    private Integer track;

    public OrdersCreateResponse(Integer track) {
        this.track = track;
    }

    public OrdersCreateResponse() {
    }

    public Integer getTrack() {
        return track;
    }

    public void setTrack(Integer track) {
        this.track = track;
    }
}
