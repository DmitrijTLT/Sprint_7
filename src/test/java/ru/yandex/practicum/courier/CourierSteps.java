package ru.yandex.practicum.courier;

import io.restassured.response.ValidatableResponse;
import ru.yandex.practicum.ErrorResponse;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class CourierSteps {

    private Integer courierId;
    private CourierApi courierApi = new CourierApi();
    private ValidatableResponse response;

    public CourierSteps createCourier(Courier courier) {
        response = courierApi.createCourier(courier).then();
        return this;
    }

    public Integer createCourierStatusCode(Courier courier) {
        response = courierApi.createCourier(courier).then();
        return response.extract().statusCode();
    }
    public Integer createCourierStatusCode( ) {
        response = courierApi.createCourier().then();
        return response.extract().statusCode();
    }

    public CourierSteps createCourier() {
        response = courierApi.createCouriereResponse().then();
        return this;
    }

    public Integer checkCreateCourier() {
        response.assertThat().statusCode(201);
        CreateCourierResponse createCourierResponseFromApi = response.extract().body().as(CreateCourierResponse.class);
        assertTrue("Ответ сервера должен содержать ok=true", createCourierResponseFromApi.isOk());
        return response.extract().statusCode();
    }

    public CourierSteps getLoginCourierResponse(Login login) {
        response = courierApi.getLoginCourierResponse(login).then();
        return this;
    }

    public CourierSteps checkLoginNegativeResponse(ErrorResponse expectedError) {
        response.assertThat().statusCode(400);
        ErrorResponse errorResponseFromApi = response.extract().body().as(ErrorResponse.class);
        assertEquals("Ответ должен быть: Недостаточно данных для входа", expectedError.getMessage(), errorResponseFromApi.getMessage());
        return this;
    }

    public CourierSteps checkLoginNegativeResponseWrongCredentials(ErrorResponse expectedError) {
        response.assertThat().statusCode(404);
        ErrorResponse errorResponseFromApi = response.extract().body().as(ErrorResponse.class);
        assertEquals("Ответ должен быть: Недостаточно данных для входа", expectedError.getMessage(), errorResponseFromApi.getMessage());
        return this;
    }

    public Integer checkGetLoginCourier() {
        response.assertThat().statusCode(200);
        LoginCourierResponse loginCourierResponseFromApi = response.extract().body().as(LoginCourierResponse.class);
        assertNotNull("Ответ сервера должен содержать id", loginCourierResponseFromApi.getId());
        assertTrue("id курьера должен быть положительным числом", loginCourierResponseFromApi.getId() > 0);
        return loginCourierResponseFromApi.getId();
    }

    public CourierSteps deleteCourierResponse(CourierDeleteId courierDeleteIdId, Integer courierId) {
        response = courierApi.deleteCourierResponse(courierDeleteIdId, courierId).then();
        return this;
    }

    public CourierSteps checkDeleteCourier() {
        response.assertThat().statusCode(200);
        CourierDeleteResponse deleteCourierFromApi = response.extract().body().as(CourierDeleteResponse.class);
        assertTrue("Ответ сервера должен содержать ok=true", deleteCourierFromApi.isOk());
        return this;
    }

//    public CourierSteps checkNegativeCreateCourier(ErrorResponse expectedError) {
//        response.assertThat().statusCode(400);
//        ErrorResponse errorResponseFromApi = response.extract().body().as(ErrorResponse.class);
//        assertEquals("Ответ должен быть: Недостаточно данных для создания учетной записи", expectedError.getMessage(), errorResponseFromApi.getMessage());
//        return this;
//    }

    public Integer checkNegativeCreateCourier(ErrorResponse expectedError) {
        response.assertThat().statusCode(400);
        ErrorResponse errorResponseFromApi = response.extract().body().as(ErrorResponse.class);
        assertEquals("Ответ должен быть: Недостаточно данных для создания учетной записи", expectedError.getMessage(), errorResponseFromApi.getMessage());
        return response.extract().statusCode();
    }

    public Integer checkNegativeCreateTwoIdenticalCourier(ErrorResponse expectedError) {
        response.assertThat().statusCode(409);
        ErrorResponse errorResponseFromApi = response.extract().body().as(ErrorResponse.class);
        assertEquals("Ответ должен быть: Этот логин уже используется", expectedError.getMessage(), errorResponseFromApi.getMessage());
        return response.extract().statusCode();
    }
}