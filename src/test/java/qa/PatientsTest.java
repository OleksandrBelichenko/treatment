package qa;

import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

/**
 * @author Oleksandr Belichenko
 */
public class PatientsTest {

    String url = "http://localhost:8080/";
    String login = "1";
    String password = "1";

    @Test
    public void Patients(){
        open(url);
        $(By.name("username")).val(login);
        $(By.name("password")).val(password);
        $(By.name("butt")).click();

        $(By.id("Button8")).click();

        $(By.id("SiteSearch2")).val("pa");
        $(By.id("SiteSearch1")).val("pa");
        $(By.id("SiteSearch3")).val("pa");
        $(By.id("as")).click();

        $(By.id("SiteSearch4")).val("pa");
        $(By.id("ssss")).click();

        $(By.id("qwe")).click();

        $(By.id("exit")).click();
    }
}
