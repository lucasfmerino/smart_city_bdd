package services;

import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.LoginModel;

import static io.restassured.RestAssured.given;

public class LoginUserService {

    final LoginModel loginModel = new LoginModel();
    public Response response;

//    final String BASE_URL = "http://localhost:8080";
    final String BASE_URL = "https://fiap-smartcities-are7dabzbfb7bxe3.eastus2-01.azurewebsites.net";


    /**
     *  SET FIELDS
     *
     */
    public void setFieldsLogin(String field, String value)
    {
        switch (field)
        {
            case "email" -> loginModel.setEmail(value);
            case "password" -> loginModel.setPassword(value);
            default -> throw new IllegalStateException("Unexpected field " + field);
        }
    }


    /**
     *  LOGIN
     *
     */
    public void loginUser(String endpoint)
    {
        String url = BASE_URL + endpoint;

        String bodyToSend = new Gson().toJson(loginModel);

        response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(bodyToSend)
                .when()
                .post(url)
                .then()
                .extract()
                .response();
    }
}
