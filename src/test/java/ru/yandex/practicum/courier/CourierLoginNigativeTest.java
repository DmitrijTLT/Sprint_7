package ru.yandex.practicum.courier;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.practicum.ErrorResponse;

public class CourierLoginNigativeTest {
    Integer courierId;
    String courierIdStr;
    Login login;
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
        Login login = new Login(courierLogin, password);
        courierId = steps.getLoginCourierResponse(login).checkGetLoginCourier();
    }

    @After
    public void tearDown() {
        // Удаляем курьера
        courierIdStr = String.valueOf(courierId);
        CourierDeleteId courierDeleteId = new CourierDeleteId(courierIdStr);
        steps.deleteCourierResponse(courierDeleteId, courierId).checkDeleteCourier();
    }

    @Test
    public void testGetLoginCourierWithoutPassword() {
        // Создаем объект класса Login с передачей двух параметров в конструктор
        login = new Login(courierLogin, null);
        // Проверяем, что курьер есть в системе
        steps.getLoginCourierResponse(login).checkLoginNegativeResponse(new ErrorResponse("Недостаточно данных для входа"));
    }

    @Test
    public void testGetLoginCourierWithoutLogin() {
        // Создаем объект класса Login с передачей двух параметров в конструктор
        login = new Login(null, password);
        // Проверяем, что курьер есть в системе
        steps.getLoginCourierResponse(login).checkLoginNegativeResponse(new ErrorResponse("Недостаточно данных для входа"));
    }

    @Test
    public void testGetLoginCourierWrongCredentials() {
        // Создаем объект класса Login с передачей двух параметров в конструктор
        login = new Login(courierLogin, "qwerty");
        // Проверяем, что курьер есть в системе
        steps.getLoginCourierResponse(login).checkLoginNegativeResponseWrongCredentials(new ErrorResponse("Учетная запись не найдена"));
    }
}
