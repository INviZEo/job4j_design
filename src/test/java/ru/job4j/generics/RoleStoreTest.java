package ru.job4j.generics;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class RoleStoreTest {

    @Test
    void whenNameEqualsMichail() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Michail"));
        Role result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("Michail");
    }

    @Test
    void whenNameIsReplaceToAugustin() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Michail"));
        store.replace("1", new Role("1", "Augustin"));
        Role result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("Augustin");
    }

    @Test
    void whenNameNotExist() {
        RoleStore store = new RoleStore();
        store.add(new Role("7", "Kiki"));
        store.delete("7");
        Role result = store.findById("7");
        assertThat(result).isEqualTo(null);
    }

    @Test
    void whenFindById() {
        RoleStore store = new RoleStore();
        store.add(new Role("19", "Gas"));
        Role result = store.findById("19");
        assertThat(result).isEqualTo(new Role("19", "Gas"));
    }
}