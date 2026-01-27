package ru.yandex.practicum;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public abstract class BaseHttpClient {
    public static RequestSpecification baseRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(Config.BASE_URL)
                .addHeader("Content-type", "application/json")
                .setRelaxedHTTPSValidation()
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new ErrorLoggingFilter())
                .build();
    }

    protected Response doGetRequest(String path, Integer courierId, List<String> nearestStation, Integer limit, Integer page) throws JsonProcessingException {
        String nearestStationJson = null;
        if (nearestStation != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            nearestStationJson = objectMapper.writeValueAsString(nearestStation);
        }
        return given()
                .spec(baseRequestSpec())
                .queryParam("courierId", courierId)
                .queryParam("nearestStation", nearestStationJson)
                .queryParam("limit", limit)
                .queryParam("page", page)
                .get(path)
                .thenReturn();
    }

//    protected Response doGetRequest(String path, Integer courierId, List<String> nearestStation, Integer limit, Integer page) {
//        RequestSpecification request = given().spec(baseRequestSpec());
//
//        if (courierId != null) {
//            request.queryParam("courierId", courierId);
//        }
//        if (nearestStation != null) {
//            request.queryParam("nearestStation", nearestStation);
//        }
//        if (limit != null) {
//            request.queryParam("limit", limit);
//        }
//        if (page != null) {
//            request.queryParam("page", page);
//        }
//
//        return request.get(path).thenReturn();
//    }

    protected Response doGetRequest(String path) {
        return given()
                .spec(baseRequestSpec())
                .get(path)
                .thenReturn();
    }

    protected Response doPostRequest(String path, Object body) {
        return given()
                .spec(baseRequestSpec())
                .body(body)
                .post(path)
                .thenReturn();
    }

    protected Response doPostRequestWithoutBody(String path) {
        return given()
                .spec(baseRequestSpec())
                .post(path)
                .thenReturn();
    }

    protected Response doDeleteRequest(String path, Object body, Integer courierId) {
        return given()
                .spec(baseRequestSpec())
                .body(body)
                .pathParam("id", courierId)
                .delete(path)
                .thenReturn();
    }

    protected Response doPostRequest(String path) {
        return given()
                .spec(baseRequestSpec())
                .post(path)
                .thenReturn();
    }

    protected Response doPostRequest(String path, Object body, Map<String, String> params) {
        return given()
                .spec(baseRequestSpec())
                .body(body)
                .params(params)
                .post(path)
                .thenReturn();
    }
}