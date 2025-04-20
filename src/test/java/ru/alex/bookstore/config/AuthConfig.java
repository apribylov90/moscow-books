package ru.alex.bookstore.config;

import org.aeonbits.owner.Config;


@Config.Sources("classpath:config/auth.properties")
public interface AuthConfig extends Config {

    @Key("login")
    String login();

    @Key("password")
    String password();

    @Key("token")
    String token();

}
