package org.example;

import static org.junit.Assert.assertTrue;

import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class ReqResTest {

    public static String id;
    public String url = "https://reqres.in";


    @Test
    public void testDadoUmUsuarioQuandoCadastraUmNovoUsuarioEntaoObtemStatusCode201() {
        baseURI = url;
        basePath = "/api/users";
        id = given()
                .body("{\n" +
                        "    \"name\": \"Thomas Anderson\",\n" +
                        "    \"job\": \"Bot\"\n" +
                        "}")
                .contentType(ContentType.JSON)
                .when()
                .post("/api/users")
                .then()
                .log().all()
                .assertThat()
                .statusCode(201)
                .body("name", equalTo("Thomas Anderson"))
                .extract()
                .path("id");
//        System.out.println("O MEU ID: " + id);
    }

    @Test //não persiste
    public void testDadoQueOUsuarioPassaOIdQuandoFazUmGetEntaoObtemUmUsuarioEspecifico() {
        baseURI = url;
        basePath = "/api/users/{id}";
        given()
                .contentType(ContentType.JSON)
                .pathParam("id", id)
                .when()
                .get(basePath,"id")
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void testDadoQueOsUsuariosSaoListadosQuandoFazUmGetEntaoObtemStatusCode200() {
        baseURI = url;
        basePath = "/api/users";

        given()
                .contentType(ContentType.JSON)
                .get()
                .then()
                .log().all()
                .statusCode(200);
    }


}
