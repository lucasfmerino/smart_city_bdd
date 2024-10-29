package services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.UserModel;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;

import static io.restassured.RestAssured.given;

public class RegisterUserService
{
    final UserModel userModel = new UserModel();

    public final Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    public Response response;

    final String BASE_URL = "http://localhost:8080";

    String schemasPath = "src/test/resources/schemas/";

    JSONObject jsonSchema;

    private final ObjectMapper mapper = new ObjectMapper();


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


    private JSONObject loadJsonFromFile(String filePath) throws IOException {
        try (InputStream inputStream = Files.newInputStream(Paths.get(filePath))) {
            JSONTokener tokener = new JSONTokener(inputStream);
            return new JSONObject(tokener);
        }
    }


    public void setContract(String contract) throws IOException {
        switch (contract) {
            case "UserContract" -> jsonSchema =
                    loadJsonFromFile(schemasPath + "UserContract.json");
            default -> throw new IllegalStateException("Unexpected contract " + contract);
        }
    }


    public Set<ValidationMessage> validateResponseAgainstSchema() throws IOException {

        // Obter o corpo da resposta como String e converter para JSONObject
        JSONObject jsonResponse = new JSONObject(response.getBody().asString());

        // Configurar o JsonSchemaFactory e criar o JsonSchema
        JsonSchemaFactory schemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
        JsonSchema schema = schemaFactory.getSchema(jsonSchema.toString());

        // Converter o JSON de resposta para JsonNode
        JsonNode jsonResponseNode = mapper.readTree(jsonResponse.toString());

        // Validar o JSON de resposta contra o esquema
        Set<ValidationMessage> schemaValidationErrors = schema.validate(jsonResponseNode);

        return schemaValidationErrors;

    }


}
