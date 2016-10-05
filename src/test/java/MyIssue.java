import com.jayway.restassured.RestAssured;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.sessionId;

/**
 * Created by Kate on 26.09.2016.
 */
public class MyIssue {


    String sessionId="";
    @BeforeTest
    public void login(){
        RestAssured.baseURI = "http://soft.it-hillel.com.ua:8080";

        String body = "{\n" +
                "    \"username\": \"katherinebilous\",\n" +
                "    \"password\": \"Polis484)\"\n" +
                "}";

         sessionId = given().
                contentType("application/json").
                body(body).
                when().
                post("/rest/auth/1/session").

                then().
                extract().
                path("session.value");
        System.out.println("JSESSIONID"+sessionId);
    }

    @Test
    public void createIssue() {
        RestAssured.baseURI = "http://soft.it-hillel.com.ua:8080";
        String body = "{\n" +
                "\n" +
                "\t\"fields\": {\n" +
                "\t\t\"project\": {\n" +
                "\t\t\t\"id\": \"10315\"\n" +
                "\t\t},\n" +
                "\t\t\"summary\": \"RESTAssured summary\",\n" +
                "\t\t\"issuetype\": {\n" +
                "\t\t\t\"id\": \"10004\"\n" +
                "\t\t},\n" +
                "\t\t\"assignee\": {\n" +
                "\t\t\t\"name\": \"katherinebilous\"\n" +
                "\t\t},\n" +
                "\t\t\"reporter\": {\n" +
                "\t\t\t\"name\": \"katherinebilous\"\n" +
                "\t\t}\n" +
                "\t}\n" +
                "}\n";
        given().
                header("Cookie", "JSESSIONID="+sessionId).
                header("Content-Type", "application/json").
                body(body).
                when().
                post("/rest/api/2/issue").
                then().
                statusCode(201);
        System.out.println(sessionId);
    }















}


