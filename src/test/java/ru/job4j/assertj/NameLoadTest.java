package ru.job4j.assertj;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void checkArrayIsEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("array is empty");
    }

    @Test
    void checkNameNotContainsSymbol() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("does not contain the symbol '='");
    }

    @Test
    void checkSymbolNotContainsKey() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("="))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("not contain a key");
    }

    @Test
    void checkNotContainsValue() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("bara="))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("not contain a value");
    }
}