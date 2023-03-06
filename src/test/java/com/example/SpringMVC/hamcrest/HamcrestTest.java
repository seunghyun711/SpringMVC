package com.example.SpringMVC.hamcrest;

import com.example.CryptoCurrency;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HamcrestTest {

    // 일반 Junit Assertion
    @DisplayName("Hello JUnit Test")
    @Test
    public void assertionTest1() {
        String actual = "hello world";
        String expected = "Hello, jUnit";

        Assertions.assertEquals(expected, actual);
    }

    // hamcrest의 Assertion
    @DisplayName("Hello Junit Test ising hamcrest")
    @Test
    public void assertionTest2() {
        String expected = "hello, junit";
        String actual = "hello, junit";

        assertThat(actual,is(equalTo(expected))); // hamcrest의 매처(Matcher)이용
    }

    @DisplayName("AssertionNull() Test - JUnit")
    @Test
    public void assertionNotNullTest() {
        String currencyName = getCryptoCurrency("Real Madrid");
        assertNotNull(currencyName, "should be not null");
    }

    private String getCryptoCurrency(String unit) {
        return CryptoCurrency.map.get(unit);
    }

    @DisplayName("AssertionNull() Test - Hamcrest")
    @Test
    public void assertionNotNullTest2() {
        String currencyName = getCryptoCurrency("MANCHESTER UNITED");
        MatcherAssert.assertThat(currencyName, is(notNullValue())); // not null 테스트
    }
}
