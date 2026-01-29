package ru.yandex.practicum.order;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.practicum.orders.OrdersCreate;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;

@RunWith(Parameterized.class)

public class OrderCreateTest {
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String metroStation;
    private final String phone;
    private final Integer rentTime;
    private final String deliveryDate;
    private final String comment;
    private final String[] color;


    private OrderSteps steps = new OrderSteps();

    // Конструктор, который получает параметры из data()
    public OrderCreateTest(String firstName, String lastName, String address,
                           String metroStation, String phone, Integer rentTime,
                           String deliveryDate, String comment, String[] color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    // Генерируем случайную дату
    public static String generateDeliveryDate(int minDays, int maxDays) {
        int randomDays = ThreadLocalRandom.current().nextInt(minDays, maxDays + 1);
        LocalDate deliveryDate = LocalDate.now().plusDays(randomDays);
        return deliveryDate.format(DateTimeFormatter.ISO_DATE);
    }

    // Метод, возвращающий коллекцию параметров
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { "Иван", "Иванов", "ул. Ленина, 1", "1", "+79991234567", 1, generateDeliveryDate(1, 5), "Коммент", new String[]{"BLACK"} },
                { "Мария", "Петрова", "пр-т Ленина, 5", "2", "+79997654321", 2, generateDeliveryDate(3, 20), "Коммент", new String[]{"GREY"} },
                { "Петр", "Петров", "пр-т Ленина, 15", "2", "+79997654321", 2, generateDeliveryDate(3, 20), "Коммент", new String[]{"GREY", "BLACK"} },
                { "Алексей", "Сидоров", "пер. Зелёный, 3", "3", "+79990001122", 3, generateDeliveryDate(10, 30), "", new String[]{} }
        });
    }

    @Test
    public void orderCreate(){
        // Создаем объект класса Orders с передачей всех параметров в конструктор
        OrdersCreate ordersCreate = new OrdersCreate(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
        // Проверяем, что можно создать заказ
        steps.createOrder(ordersCreate).checkOrderResponse();
    }
}
