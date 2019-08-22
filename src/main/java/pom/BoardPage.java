package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import util.Hook;

import java.util.ArrayList;
import java.util.List;

import static util.Tools.waitBy;

public class BoardPage {
    @FindBy(how = How.XPATH, using = "//a[@class='board-header-btn board-header-btn-name js-rename-board']")
    private WebElement boardName;
    @FindBy(how=How.XPATH, using = "//div[div[textarea[@aria-label='To Do']]]/a[@class='open-card-composer js-open-card-composer']")
    private WebElement toDoList;
    @FindBy(how=How.XPATH, using = "//div[div[textarea[@aria-label='To Do']]]/a[@class='open-card-composer js-open-card-composer']")
    private WebElement inProgressList;
    @FindBy(how=How.XPATH, using = "//div[div[textarea[@aria-label='To Do']]]/a[@class='open-card-composer js-open-card-composer']")
    private WebElement doneList;
    @FindBy(how=How.XPATH, using = "//*[@class='list-card-composer-textarea js-card-title']")
    private WebElement inputCardName;
    @FindBy(how=How.XPATH,using = "//input[@value='Adicionar Cartão']")
    private WebElement addCardButton;
    @FindBy(how=How.XPATH, using = "//textarea[@placeholder='Escrever um comentário...']")
    private WebElement txtComment;
    @FindBy(how=How.XPATH, using = "//*[@value='Salvar']")
    private WebElement saveCommentButton;
    @FindBy(how=How.XPATH, using = "//a[@class='icon-lg icon-close dialog-close-button js-close-window']")
    private WebElement closeCardButton;
    @FindBy(how=How.XPATH,using = "//div[div/h2[text()='To Do']]//span[@class='list-card-title js-card-name']")
    private List<WebElement> elementsToDo;
    //compartilhar e mais
    @FindBy(how = How.XPATH, using = "//*[text()='Compartilhar e mais...']")
    private WebElement maisCardLink;
    //excluir
    @FindBy(how = How.XPATH, using = "//a[@class='js-delete']")
    private WebElement excluirCardLink;
    //confirmar exclusao
    @FindBy(how = How.XPATH, using = "//input[@value='Excluir']")
    private WebElement excluirCardButton;


    String cardName;
    //Constructor
    public BoardPage() {
        PageFactory.initElements(Hook.getDriver(), this);
    }

    public BoardPage acessBoard(){
        Hook.getDriver().get("https://trello.com/b/ICLxsmNI/automationprime");
        return this;
    }

    public String getBoardName(){
        return waitBy(boardName).getText();
    }
    public BoardPage clickAddCard(String list){
        if(list.equals("To do")){
            waitBy(toDoList).click();
        }else if(list.equals("In progress")){
            waitBy(inProgressList).click();
        }else {
            waitBy(doneList).click();
        }
        return this;
    }

    public BoardPage addCardName(String cardName) {
        waitBy(inputCardName).sendKeys(cardName);
        waitBy(addCardButton).click();
        this.cardName = cardName;
        return this;
    }

    public BoardPage commentCard(String comment){
        waitBy(Hook.getDriver().findElement(By.xpath("//span[text()='"+cardName+"']"))).click();
        waitBy(txtComment).sendKeys(comment);
        waitBy(saveCommentButton).click();
        closeCardButton.click();
        return this;
    }

    public String checkExistenceCard(){
        List<String> cardList = new ArrayList<>();
        for(WebElement e : elementsToDo){
            cardList.add(e.getText());
        }
        if(cardList.contains(cardName)){
            return "Card existe na lista";
        }else{
            return "Card não existe na lista";
        }
    }

    public BoardPage excludeCard(){
        waitBy(Hook.getDriver().findElement(By.xpath("//span[text()='"+cardName+"']"))).click();
        waitBy(maisCardLink).click();
        waitBy(excluirCardLink).click();
        waitBy(excluirCardButton).click();
        return this;
    }
}
