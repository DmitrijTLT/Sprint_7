package ru.yandex.practicum.courier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Courier {
    // ключ login стал полем типа String
    private String login;
    // ключ password стал полем типа String
    private String password;
    // ключ firstName стал полем типа String
    private String firstName;
}
