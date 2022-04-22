package ru.netology.ru.netology;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Locale;
import java.util.Random;

public class Generator {
    private Generator() {
    }

    public static CardInfo generatedCard(String locale){
        Faker faker = new Faker(new Locale(locale));
        return new CardInfo(
                faker.name().fullName(),
                faker.phoneNumber().phoneNumber(),
                getRandomElement()
        );

    }
    public static String getRandomElement(){
        var random = new Random();
        var list = Arrays.asList("Уфа", "Москва", "Казань", "Сургут", "Нижневартовск");
        String randomCity = list.get(random.nextInt(list.size()));
        return randomCity;
    }

    public static String generateDate(int Days){
        return LocalDate.now().plusDays(Days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
}
