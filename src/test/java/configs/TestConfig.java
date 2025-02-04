package configs;

import org.aeonbits.owner.Config;

@Config.Sources({
        "system:properties",
        "classpath:${config}.properties",
        "classpath:credentials.properties"
})
public interface TestConfig extends Config {
    @Key("browser.name")
    @DefaultValue("chrome")
    String browserName();

    @Key("browser.version")
    @DefaultValue("latest")
    String browserVersion();

    @Key("browser.windowSize")
    @DefaultValue("1920x1080")
    String browserWindowSize();

    @Key("browser.isRemote")
    @DefaultValue("false")
    boolean isRemote();

    @Key("browser.baseUrl")
    @DefaultValue("https://demoqa.com")
    String baseUrl();

    @Key("remote.user")
    @DefaultValue("")
    String remoteUser();

    @Key("remote.password")
    @DefaultValue("")
    String remotePassword();

    @Key("remote.url")
    @DefaultValue("")
    String remoteUrl();
}
}