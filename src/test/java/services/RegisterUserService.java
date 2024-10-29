package services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.UserModel;

import static io.restassured.RestAssured.given;

public class RegisterUserService
{
    final UserModel userModel = new UserModel();

    public final Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    public Response response;

    final String BASE_URL = "http://localhost:8080";


    /**
     *  SET FIELDS
     *
     */
    public void setFieldsUser(String field, String value)
    {
        switch (field)
        {
            case "name" -> userModel.setName(value);
            case "email" -> userModel.setEmail(value);
            case "password" -> userModel.setPassword(value);
            case "role" -> userModel.setRole(value);
            case "accountNonExpired" -> userModel.setAccountNonExpired(Boolean.parseBoolean(value));
            case "credentialsNonExpired" -> userModel.setCredentialsNonExpired(Boolean.parseBoolean(value));
            case "accountNonLocked" -> userModel.setAccountNonLocked(Boolean.parseBoolean(value));
            case "enabled" -> userModel.setEnabled(Boolean.parseBoolean(value));
            default -> throw new IllegalStateException("Unexpected field" + field);
        }
    }


    /**
     *  REGISTER USER
     *
     */
    public void registerUser(String endpoint)
    {
        String url = BASE_URL + endpoint;

        String bodyToSend = gson.toJson(userModel);

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
