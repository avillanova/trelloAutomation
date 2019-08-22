package steps.api;

import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class TrelloStep {
    final String BASE_URL = "https://api.trello.com/1/";
    final String AUTH ="key=782d0ab7808cb95632827cd828a615d2&token=4726548c899b8eb9a978620164b2255d46150081a76c535374bb2ea7d29c0036";
    final String BOARD_ID="ICLxsmNI";
    final String TODO_ID="5bb8c3f4f634931873d8ffa6";
    private Response response;
    private String cardId;

    @Dado("^que acesse a API com sucesso$")
    public void queAcesseAAPIComSucesso() {
        response = given().get(BASE_URL+"boards/"+BOARD_ID+"?"+AUTH);
        response.prettyPrint();
        Assert.assertEquals(200, response.statusCode());
    }

    @Quando("^crio um card com o nome \"([^\"]*)\" usando a API$")
    public void crioUmCardComONomeUsandoAAPI(String nome) {
        response = given().contentType("application/json").post(
                BASE_URL+"cards?name="+nome+
                        "&desc=Descricao&idList="+TODO_ID+"&keepFromSource=all&"+AUTH);
        Map<String, String> card = response.jsonPath().getMap("$");
        cardId=card.get("id");
        Assert.assertEquals(200, response.statusCode());
    }

    @E("^comento \"([^\"]*)\" no card gerado$")
    public void comentoNoCardGerado(String comment){
        response = given().contentType("application/json").post(
                BASE_URL+"cards/"+cardId+"/actions/comments?text="+comment+"&"+AUTH);
        Assert.assertEquals(200, response.statusCode());
    }

    @Entao("^o card deve ser criado com sucesso$")
    public void oCardDeveSerCriadoComSucesso() {
        response = given().contentType("application/json").get(
                BASE_URL+"cards/"+cardId+"?"+AUTH);
        Assert.assertEquals(200, response.statusCode());
    }

    @Quando("^excluo usando a API$")
    public void excluoUsandoAAPI() {
        response = given().contentType("application/json").delete(
                BASE_URL+"cards/"+cardId+"?"+AUTH);
        Assert.assertEquals(200, response.statusCode());
        response = given().contentType("application/json").get(
                BASE_URL+"cards/"+cardId+"?"+AUTH);
    }

    @Entao("^eu tenho o resultado com status \"([^\"]*)\"$")
    public void euTenhoOResultadoComStatus(int status) {
        Assert.assertEquals(status, response.statusCode());
    }



}
