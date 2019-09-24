package qa;

import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

/**
 * @author Oleksandr Belichenko
 */
public class LoginTest {

    String url = "http://localhost:8080/";
    String login = "1";
    String password = "1";

    @Test
    public void LogIn(){
        open(url);
        $(By.name("username")).val(login);
        $(By.name("password")).val(password);
        $(By.name("butt")).click();

        $(By.id("Buttoffn9")).click();
    }
}
