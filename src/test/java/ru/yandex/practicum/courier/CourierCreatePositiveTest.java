package ru.yandex.practicum.courier;

import org.junit.After;
import org.junit.Test;

public class CourierCreatePositiveTest {

    String courierLogin = "testSPB_1";
    String password = "TestTLT";
    String firstName = "Dima";

    private CourierSteps steps = new CourierSteps();

    @Test
    public void testCreateCourier() {
        // Создаем объект класса Courier с передачей трех параметров в конструктор
        Courier courier = new Courier(courierLogin, password, firstName);
        // Проверяем, что можно создать курьера
        steps.createCourier(courier).checkCreateCourier();
    }

    @After
    public void tearDown() {
        // Получаем id курьера
        Login login = new Login(courierLogin, password);
        Integer courierId = steps.getLoginCourierResponse(login).checkGetLoginCourier();
        String courierIdStr = String.valueOf(courierId);

        // Удаляем курьера
        CourierDeleteId courierDeleteId = new CourierDeleteId(courierIdStr);
        steps.deleteCourierResponse(courierDeleteId, courierId).checkDeleteCourier();
    }
}
