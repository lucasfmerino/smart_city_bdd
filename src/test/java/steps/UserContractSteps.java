package steps;

import com.networknt.schema.ValidationMessage;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import org.junit.Assert;
import services.UserContractService;

import java.io.IOException;
import java.util.Set;

public class UserContractSteps {

    UserContractService userContractService = new UserContractService();


    @E("que o arquivo de contrato esperado é o {string}")
    public void queOArquivoDeContratoEsperadoÉO(String contract) throws IOException
    {
        userContractService.setContract(contract);
    }


    @Então("a resposta da requisição deve estar em conformidade com o contrato selecionado")
    public void aRespostaDaRequisiçãoDeveEstarEmConformidadeComOContratoSelecionado() throws IOException
    {
        Set<ValidationMessage> validateResponse = userContractService.validateResponseAgainstSchema();
        Assert.assertTrue("O contrato está inválido. Erros encontrados: " + validateResponse, validateResponse.isEmpty());
    }
}
