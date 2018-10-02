package util;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class Tools {
    public static WebElement waitBy(WebElement element) {
        try {
            new WebDriverWait( Hook.driver, 40 )
                    .until(  ExpectedConditions.visibilityOf( element ));
        }catch(Exception e){
            System.err.println(element+" Não encontrado....");
        }
        return element;
    }

    private static void switchTab(int tabId){
        ArrayList<String> tabs = new ArrayList<String>( Hook.driver.getWindowHandles() );
        Hook.driver.switchTo().window( tabs.get( tabId ) );
    }
}
