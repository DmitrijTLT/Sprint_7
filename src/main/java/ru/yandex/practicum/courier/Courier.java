package ru.yandex.practicum.courier;

public class Courier {
    // ключ login стал полем типа String
    private String login;
    // ключ password стал полем типа String
    private String password;
    // ключ firstName стал полем типа String
    private String firstName;

    // конструктор со всеми параметрами
    public Courier(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    // конструктор без параметров
    public Courier() {

    }

    // геттер для поля login
    public String getLogin() {
        return login;
    }

    // сеттер для поля login
    public void setLogin(String login) {
        this.login = login;
    }

    // геттер для поля password
    public String getPassword() {
        return password;
    }

    // сеттер для поля password
    public void setPassword(String password) {
        this.password = password;
    }

    // геттер для поля firstName
    public String getFirstName() {
        return firstName;
    }

    // сеттер для поля firstName
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
