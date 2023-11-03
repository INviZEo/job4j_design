package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.InstanceOfAssertFactories.BOOLEAN;

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

    @Test
    void testExist() {
        Box box = new Box(4, 10);
        boolean result = box.isExist();
        assertThat(result).isTrue();

    }

    @Test
    void testNotExist() {
        Box box = new Box(1, 10);
        boolean result = box.isExist();
        assertThat(result).isFalse();
    }

    @Test
    void checkArea() {
        Box box = new Box(4, 10);
        double result = box.getArea();
        assertThat(result).isEqualTo(173d, withPrecision(0.6d))
                .isLessThan(200);
    }

    @Test
    void checkAreas() {
        Box box = new Box(4, 6);
        double result = box.getArea();
        assertThat(result).isNotCloseTo(5d, withPrecision(0.6d))
                .isLessThan(200)
                .isGreaterThan(10);
    }
}