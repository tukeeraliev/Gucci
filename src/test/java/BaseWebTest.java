import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.gucci.config.AppConfig;
import com.gucci.config.ConfigurationManager;
import com.gucci.layers.web.manager.WebDriverManager;
import com.gucci.layers.web.page.home.HomePage;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static com.gucci.config.ConfigurationManager.getAppConfig;

public class BaseWebTest {

    static {
        AppConfig config = ConfigurationManager.getAppConfig();

        Configuration.baseUrl = config.baseUrl();
        Configuration.headless = config.headless();
    }

private final String BASE_URL = getAppConfig().baseUrl();
    public <T> T open(String endPoint, Class<T> clazz){
        Selenide.open(BASE_URL, HomePage.class)
                .waitForPageLoaded();
        return Selenide.open(String.format("%s/%s", BASE_URL, endPoint), clazz);
    }

    @BeforeAll
    public static void setUp(){
        WebDriverManager.configureBasicWebDriver();
        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide()
                        .screenshots(true)
                        .savePageSource(true));
    }

    @AfterAll
    public static void tearDown(){
        SelenideLogger.removeListener("AllureSelenide");
    }
}
