package ru.job4j.io;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.*;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "data/app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.url"))
                .isEqualTo("jdbc:postgresql://127.0.0.1:5432/trackstudio");
    }

    @Test
    void whenKeyEqualValueEqual() {
        String path = "data/keyvaluetwoequals.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("key")).isEqualTo("value=1");
    }
    @Test
    void whenKeyEqualValueal() {
        String path = "data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("#blant")).isEqualTo("joint");
    }

    @Test
    void whenPairWithoutValue() {
        String path = "data/comment.properties";
        Config config = new Config(path);
        Assert.assertThrows(IllegalArgumentException.class, config::load);
    }

    @Test
    void whenPairWithoutKey() {
        String path = "data/valuenokey.properties";
        Config config = new Config(path);
        Assert.assertThrows(IllegalArgumentException.class, config::load);
    }

    @Test
    void whenPairHaveNothing() {
        String path = "data/emptypair.properties";
        Config config = new Config(path);
        Assert.assertThrows(IllegalArgumentException.class, config::load);
    }

    @Test
    void whenPairKeyValueWithoutEquals() {
        String path = "data/keyvaluewithoutequals.properties";
        Config config = new Config(path);
        Assert.assertThrows(IllegalArgumentException.class, config::load);
    }

}