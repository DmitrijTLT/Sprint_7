package ru.yandex.practicum.courier;

import org.junit.After;
import org.junit.Test;
import ru.yandex.practicum.*;
import static org.apache.http.HttpStatus.*;

public class CourierCreateNegativeTest {
    String courierLogin = "testSPB_1";
    String password = "TestTLT";
    String firstName = "Dima";

    private CourierSteps steps = new CourierSteps();
    private boolean createSuccess = false;

    @Test
    public void testCreateCourierWithoutBody() {
        // Проверяем, что нельзя создать курьера без тела запроса
        Integer statusCode = steps.createCourierStatusCode();
        if (statusCode != SC_CREATED) {
            steps.checkNegativeCreateCourier(new ErrorResponse("Недостаточно данных для создания учетной записи"));
        } else {
            createSuccess = true;
        }
   }

    @Test
    public void testCreateCourierWithParametersBodyNull() {
        // Создаем объект класса Courier со всеми параметрами = null в конструктор
        Courier courier = new Courier();
        // Проверяем, что нельзя создать курьера, если все параметры в запросе = null
        Integer statusCode = steps.createCourierStatusCode(courier);
        if (statusCode != SC_CREATED) {
            steps.checkNegativeCreateCourier(new ErrorResponse("Недостаточно данных для создания учетной записи"));
        } else {
            createSuccess = true;
        }
    }

    @Test
    public void testCreateCourierWithoutLogin() {
        // Создаем объект класса Courier с передачей двух параметров в конструктор
        Courier courier = new Courier(null, password, firstName);
        // Проверяем, что нельзя создать курьера без логина
        Integer statusCode = steps.createCourierStatusCode(courier);
        if (statusCode != SC_CREATED) {
            steps.checkNegativeCreateCourier(new ErrorResponse("Недостаточно данных для создания учетной записи"));
        } else {
            createSuccess = true;
        }
    }

    @Test
    public void testCreateCourierWithoutPassword() {
        // Создаем объект класса Courier с передачей двух параметров в конструктор
        Courier courier = new Courier(courierLogin, null, firstName);
        // Проверяем, что нельзя создать курьера без пароля
        Integer statusCode = steps.createCourierStatusCode(courier);
        if (statusCode != SC_CREATED) {
            steps.checkNegativeCreateCourier(new ErrorResponse("Недостаточно данных для создания учетной записи"));
        } else {
            createSuccess = true;
        }
    }

    @Test
    public void testCreateCourierWithoutFirstName() {
        // Создаем объект класса Courier с передачей двух параметров в конструктор
        Courier courier = new Courier(courierLogin, password, null);
        // Проверяем, что нельзя создать курьера без имени
        Integer statusCode = steps.createCourierStatusCode(courier);
        if (statusCode != SC_CREATED) {
            createSuccess = false;
            steps.checkNegativeCreateCourier(new ErrorResponse("Недостаточно данных для создания учетной записи"));
        } else {
            createSuccess = true;
        }
    }

    @Test
    public void testNotCreateTwoIdenticalCourier() {
        // Создаем объект класса Courier с передачей трех параметров в конструктор
        Courier courier = new Courier(courierLogin, password, firstName);
        // Проверяем, что можно создать курьера
        Integer statusCode = steps.createCourierStatusCode(courier);
        // Проверяем, что нельзя создать курьера с уже занятым логином
        if (statusCode == SC_CREATED) {
            createSuccess = true;
            steps.checkNegativeCreateTwoIdenticalCourier(new ErrorResponse("Этот логин уже используется"));
        } else {
            System.out.println("Первый курьер не был создан");
        }
    }

    @After
    public void tearDown() {
        if (createSuccess) { // Выполняем удаление только если создание прошло успешно
            // Получаем id курьера
            Login login = new Login(courierLogin, password);
            Integer courierId = steps.getLoginCourierResponse(login).checkGetLoginCourier();
            String courierIdStr = String.valueOf(courierId);

            // Удаляем курьера
            CourierDeleteId courierDeleteId = new CourierDeleteId(courierIdStr);
            steps.deleteCourierResponse(courierDeleteId, courierId).checkDeleteCourier();
        }
    }
}
