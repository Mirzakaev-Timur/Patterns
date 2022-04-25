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

    public static CardInfo generatedCard(String locale) {
        Faker faker = new Faker(new Locale(locale));
        String phone = faker.numerify("+79#########");
        return new CardInfo(
                faker.name().fullName(),
                faker.numerify("+79#########"),
                getRandomElement()
        );
    }
    public static String generatePhone(String locale) {

        return locale;
    }

        public static String getRandomElement(){
        var random = new Random();
        var list = Arrays.asList("Уфа", "Москва", "Казань", "Пермь", "Астрахань");
        String randomCity = list.get(random.nextInt(list.size()));
        return randomCity;
    }

    public static String generateDate(int Days){
        return LocalDate.now().plusDays(Days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
}
