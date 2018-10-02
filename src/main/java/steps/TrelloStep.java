package steps;

import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import org.junit.Assert;
import pom.BoardPage;
import pom.LoginPage;

public class TrelloStep {
    LoginPage loginPage;
    BoardPage boardPage;

    @Dado("^que esteja logado no trello$")
    public void queEstejaLogadoNoTrello(){
        loginPage = new LoginPage();
        loginPage.access();
        loginPage.doLogin("usuarii", "senha");
        Assert.assertEquals("Página Inicial do Trello",loginPage.checkInitialPage());
    }

    @E("^acesse o board$")
    public void acesseOBoard(){
        boardPage=new BoardPage();
        boardPage.acessBoard();
        Assert.assertEquals("PRIME_AUTOMACAO", boardPage.getBoardName());
    }

    @Quando("^crio um card com o nome \"([^\"]*)\"$")
    public void crioUmCardComONome(String cardName) throws Throwable {
        boardPage.clickAddCard("To do");
        boardPage.addCardName(cardName);
        Assert.assertEquals("Card existe na lista", boardPage.checkExistenceCard());
    }

    @E("^comento \"([^\"]*)\"$")
    public void comento(String comment) throws Throwable {
        boardPage.commentCard(comment);
        //check o momentario
    }

    @Entao("^o card deve estar na lista$")
    public void oCardDeveEstarNaLista() throws Throwable {
        Assert.assertEquals("Card existe na lista", boardPage.checkExistenceCard());
    }

    @Quando("^excluo com sucesso$")
    public void excluoComSucesso() throws Throwable {
        boardPage.excludeCard();
    }

    @Entao("^o card não existe mais$")
    public void oCardNãoExisteMais() throws Throwable {
        Assert.assertEquals("Card não existe na lista", boardPage.checkExistenceCard());
    }


}
