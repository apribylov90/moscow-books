package ru.alex.bookstore.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "classpath:config/settings.properties",
        "classpath:config/remote.properties"})
public interface GeneralConfig extends Config {

    @Key("browserName")
    @DefaultValue("chrome")
    String browserName();

    @Key("browserSize")
    @DefaultValue("1920x1080")
    String browserSize();

    @Key("browserVersion")
    @DefaultValue("128")
    String browserVersion();

    @Key("baseUrl")
    @DefaultValue("https://www.moscowbooks.ru")
    String baseUrl();

    @Key("headless")
    boolean headless();

    @Key("isRemote")
    boolean isRemote();

    @Key("selenoidHost")
    String selenoidHost();
}
