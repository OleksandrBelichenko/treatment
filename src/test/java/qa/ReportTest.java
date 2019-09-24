package qa;

import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

/**
 * @author Oleksandr Belichenko
 */
public class ReportTest {

    String url = "http://localhost:8080/";
    String login = "1";
    String password = "1";

    @Test
    public void Reports() {
        open(url);
        $(By.name("username")).val(login);
        $(By.name("password")).val(password);
        $(By.name("butt")).click();

        $(By.id("Button1111sdcsad1")).click();

        $(By.id("Button11111")).click();

        $(By.id("dropSpec")).selectOption("pr");

        $(By.id("Button1")).click();

        $(By.id("exit")).click();

        ////////////////////////////////
        $(By.id("Button8")).click();

        executeJavaScript(("document.getElementById('SiteSearch2').setAttribute('type', 'text')"));
        executeJavaScript(("document.getElementById('SiteSearch1').setAttribute('type', 'text')"));

        $(By.id("SiteSearch2")).val("2012-01-01");
        $(By.id("SiteSearch1")).val("2019-01-06");

        $(By.id("reer")).click();

        $(By.id("exit")).click();
        ////////////////////////////////
        $(By.id("Button1111sdcsad1")).click();

        $(By.id("SiteSearch2")).val("pa");
        $(By.id("SiteSearch1")).val("pa");
        $(By.id("SiteSearch3")).val("pa");
        $(By.id("as")).click();

        $(By.id("exit")).click();
    }
}
