package qa;

import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

/**
 * @author Oleksandr Belichenko
 */
public class DoctorsTest {

    String url = "http://localhost:8080/";
    String login = "1";
    String password = "1";

    @Test
    public void Doctors(){
        open(url);
        $(By.name("username")).val(login);
        $(By.name("password")).val(password);
        $(By.name("butt")).click();

        $(By.id("Button11111")).click();

        $(By.id("SiteSearch2")).val("Иван");
        $(By.id("SiteSearch1")).val("Игоревич");
        $(By.id("SiteSearch3")).val("Амосов");
        $(By.id("as")).click();

        $(By.id("SiteSearch4")).val("хирург");
        $(By.id("ssss")).click();

        $(By.id("qwe")).click();

        $(By.id("exit")).click();
    }
}
