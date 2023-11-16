package ru.job4j.generics;

import java.util.Objects;

public class Role extends Base {

    private final String username;

    public Role(String id, String name) {
        super(id);
        this.username = name;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Role role = (Role) o;
        return username.equals(role.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
