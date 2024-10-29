package steps;

import com.networknt.schema.ValidationMessage;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;
import services.RegisterUserService;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

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


    @E("que o arquivo de contrato esperado é o {string}")
    public void queOArquivoDeContratoEsperadoÉO(String contract) throws IOException
    {
        registerUserService.setContract(contract);
    }


    @Então("a resposta da requisição deve estar em conformidade com o contrato selecionado")
    public void aRespostaDaRequisiçãoDeveEstarEmConformidadeComOContratoSelecionado() throws IOException
    {
        Set<ValidationMessage> validateResponse =
                registerUserService.validateResponseAgainstSchema();
        Assert.assertTrue("O contrato está inválido. Erros encontrados: " +
                validateResponse, validateResponse.isEmpty());
    }

}
