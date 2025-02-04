package tests;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import helpers.Attach;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;
import configs.TestConfig;
import org.aeonbits.owner.ConfigFactory;
import java.util.Map;
public class TestBase {
    protected static final String bookStoreLogin = System.getProperty("bookStoreLogin");
    protected static final String bookStorePassword = System.getProperty("bookStorePassword");

    private static final TestConfig config = ConfigFactory.create(TestConfig.class);

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        RestAssured.baseURI = "https://demoqa.com";
        Configuration.browser = config.browserName();
        Configuration.browserVersion = config.browserVersion();
        Configuration.browserSize = config.browserWindowSize();
        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 6000;
        if (config.isRemote()) {
            Configuration.remote = String.format(
                    "https://%s:%s@%s/wd/hub",
                    config.remoteUser(),
                    config.remotePassword(),
                    config.remoteUrl()
            );
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                    "enableVNC", true,
                    "enableVideo", true
            ));
            Configuration.browserCapabilities = capabilities;
        }
    }
    @AfterEach
    void afterEach() {
        Attach.screenshotAs("Финальный скриншот");
        Attach.pageSource();
        Attach.browserConsoleLogs();

        if (config.isRemote()) {
            Attach.addVideo();
        }

        Selenide.closeWebDriver();
    }
}