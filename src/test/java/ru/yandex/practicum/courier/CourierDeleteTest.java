package ru.yandex.practicum.courier;

import org.junit.Before;
import org.junit.Test;

public class CourierDeleteTest {
    String courierLogin = "testSPB_1";
    String password = "TestTLT";
    String firstName = "Dima";

    private CourierSteps steps = new CourierSteps();

    @Before
    public void setUp() {
        // Создаем объект класса Courier с передачей трех параметров в конструктор
        Courier courier = new Courier(courierLogin, password, firstName);
        // Проверяем, что можно создать курьера
        steps.createCourier(courier).checkCreateCourier();
    }

    @Test
    public void testDeleteCourier() {
        // Получаем id курьера
        Login login = new Login(courierLogin, password);
        Integer courierId = steps.getLoginCourierResponse(login).checkGetLoginCourier();
        String courierIdStr = String.valueOf(courierId);

        // Удаляем курьера
        CourierDeleteId courierDeleteId = new CourierDeleteId(courierIdStr);
        steps.deleteCourierResponse(courierDeleteId, courierId).checkDeleteCourier();
    }
}
