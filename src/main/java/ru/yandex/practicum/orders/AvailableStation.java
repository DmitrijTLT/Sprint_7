package ru.yandex.practicum.orders;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvailableStation {
    private String name;
    private String number;
    private String color;
}
