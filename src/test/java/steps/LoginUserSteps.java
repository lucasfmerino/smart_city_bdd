package steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;
import services.LoginUserService;

import java.util.List;
import java.util.Map;

public class LoginUserSteps
{
    LoginUserService loginUserService = new LoginUserService();


    @Dado("que eu tenha os seguintes dados de login:")
    public void queEuTenhaOsSeguintesDadosDeLogin(List<Map<String, String>> rows)
    {
        for(Map<String, String> columns : rows)
        {
            loginUserService.setFieldsLogin(columns.get("campo"), columns.get("valor"));
        }
    }


    @Quando("eu enviar a requisição para o endpoint {string} de login de usuário")
    public void euEnviarARequisiçãoParaOEndpointDeLoginDeUsuário(String endpoint)
    {
        loginUserService.loginUser(endpoint);
    }


    @Então("o status code da resposta de login deve ser {int}")
    public void oStatusCodeDaRespostaDeLoginDeveSer(int statusCode)
    {
        Assert.assertEquals(statusCode, loginUserService.response.statusCode());
    }


    @E("a resposta deve conter um token JWT")
    public void aRespostaDeveConterUmTokenJWT()
    {
        String token = loginUserService.response.jsonPath().getString("token");
        Assert.assertNotNull("O token JWT está ausente na resposta", token);
        System.out.println("Token JWT recebido: " + token);
    }


}
