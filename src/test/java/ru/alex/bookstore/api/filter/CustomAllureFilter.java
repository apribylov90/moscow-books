package ru.alex.bookstore.api.filter;

import io.qameta.allure.restassured.AllureRestAssured;

public class CustomAllureFilter {

    public static AllureRestAssured withCustomAllureFilter() {
        AllureRestAssured filter = new AllureRestAssured();
        filter.setRequestTemplate("request-template.ftl");
        filter.setResponseTemplate("response-template.ftl");
        return filter;
    }
}
