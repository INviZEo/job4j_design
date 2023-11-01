package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Cube")
                .doesNotContain("Sphere");
    }

    @Test
    void isNotTetrahedron() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).doesNotContain("Tetrahedron")
                .isEqualTo("Sphere");
    }

    @Test
    void checkInt() {
        Box box = new Box(8, 10);
        int result = box.getNumberOfVertices();
        assertThat(result).isNotZero()
                .isPositive()
                .isGreaterThan(1)
                .isLessThan(9)
                .isEven()
                .isEqualTo(8);
    }
}