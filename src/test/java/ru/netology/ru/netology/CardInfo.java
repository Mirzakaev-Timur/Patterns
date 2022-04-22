package ru.netology.ru.netology;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor

public class CardInfo {
    private final String name;
    private final String phone;
    private final String city;
}
