package steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;
import services.RegisterUserService;

import java.util.List;
import java.util.Map;

public class RegisterUserSteps {

    RegisterUserService registerUserService = new RegisterUserService();


    @Dado("que eu tenha os seguintes dados de usuário:")
    public void queEuTenhaOsSeguintesDadosDeUsuário(List<Map<String, String>> rows)
    {
        for(Map<String, String> columns : rows)
        {
            registerUserService.setFieldsUser(columns.get("campo"), columns.get("valor"));
        }
    }


    @Quando("eu enviar a requisição para o endpoint {string} de cadastro de usuário")
    public void euEnviarARequisiçãoParaOEndpointDeCadastroDeUsuário(String endpoint)
    {
        registerUserService.registerUser(endpoint);
    }


    @Então("o status code da resposta deve ser {int}")
    public void oStatusCodeDaRespostaDeveSer(int statusCode)
    {
        Assert.assertEquals(statusCode, registerUserService.response.statusCode());
    }

}
