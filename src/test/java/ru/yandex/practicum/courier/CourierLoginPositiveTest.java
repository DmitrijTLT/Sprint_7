package ru.yandex.practicum.courier;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CourierLoginPositiveTest {
    Integer courierId;
    String courierIdStr;
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

    @After
    public void tearDown() {
        // Удаляем курьера
        courierIdStr = String.valueOf(courierId);
        CourierDeleteId courierDeleteId = new CourierDeleteId(courierIdStr);
        steps.deleteCourierResponse(courierDeleteId, courierId).checkDeleteCourier();
    }

    @Test
    public void testGetLoginCourier() {
        // Создаем объект класса Login с передачей двух параметров в конструктор
        Login login = new Login(courierLogin, password);
        // Проверяем, что курьер есть в системе
        courierId = steps.getLoginCourierResponse(login).checkGetLoginCourier();
    }
}
