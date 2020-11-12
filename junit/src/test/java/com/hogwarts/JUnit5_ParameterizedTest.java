package com.hogwarts;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class JUnit5_ParameterizedTest {

    /**
     * ValueSource 可以让你指定一个原生类型（String，int，long或double）的数组，并且只能为每次调用提供一个参数
     *
     * @param var
     */
    @ParameterizedTest
    @ValueSource(strings = {"123", "456"})
    void test_valueSource(String var) {
        System.out.println("传入参数为：" + var);
        assertNotNull(var);
    }

    /**
     * EnumSource 不设置参数，所有的常量将被用在下面的例子中
     *
     * @param timeUnit
     */
    @ParameterizedTest
    @EnumSource(TimeUnit.class)
    void test_enumSource(TimeUnit timeUnit) {
        assertNotNull(timeUnit);
    }

    /**
     * EnumSource 提供的 names 参数，可以指定使用哪些常量
     *
     * @param timeUnit
     */
    @ParameterizedTest
    @EnumSource(value = TimeUnit.class, names = {"DAYS", "HOURS"})
    void test_enumSource_name(TimeUnit timeUnit) {
        assertTrue(EnumSet.of(TimeUnit.DAYS, TimeUnit.HOURS).contains(timeUnit));
    }

    @ParameterizedTest
    @MethodSource("parameterProvider")
    void test_methodSource(String var) {
        assertNotNull(var);
        System.out.println("通过方法传递参数结果：" + var);
    }

    static Stream<String> parameterProvider() {
        return Stream.of("test123", "test456");
    }

    @ParameterizedTest
    @MethodSource("stringIntAndListProvider")
    void testWithMultiArgMethodSource(String str, int num, List<String> list) {
        assertEquals(3, str.length());
        assertTrue(num >= 1 && num <= 2);
        assertEquals(2, list.size());
        System.out.print("多参数传递结果：" + str + "\t" + num + "\t" + list.toString());
    }

    static Stream<Arguments> stringIntAndListProvider() {
        return Stream.of(
                Arguments.of("foo", 1, Arrays.asList("a", "b")),
                Arguments.of("bar", 2, Arrays.asList("x", "y"))
        );
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/two-column.csv")
    void testWithCsvFileSource(String first, int second) {
        System.out.print("通过csv文件传递参数：" + first + "\t" + second);
    }

}
