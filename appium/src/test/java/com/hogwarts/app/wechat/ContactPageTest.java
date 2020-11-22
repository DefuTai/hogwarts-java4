package com.hogwarts.app.wechat;

import com.hogwarts.app.wechat.po.ContactPage;
import com.hogwarts.app.wechat.po.MainPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ContactPageTest {

    private static MainPage mainPage;
    private static ContactPage contactPage;

    @BeforeAll
    static void beforeAll() throws MalformedURLException {
        mainPage = new MainPage();
        contactPage = mainPage.goToContactPage();
    }

    @AfterEach
    void afterEach() {
        contactPage.goBack();
    }

    @ParameterizedTest
    @ValueSource(strings = {"DF-测试部门01"})
    void search(String keyword) {
        assertTrue(contactPage.search(keyword).containsFromCurrentList(keyword));
    }

    @ParameterizedTest
    @ValueSource(strings = {"DF测试部门02", "DF测试部门03"})
    void addDepartment(String departmentName) {
        assertTrue(contactPage.addDepartment(departmentName).search(departmentName).containsFromCurrentList(departmentName));
    }

    @ParameterizedTest
    @ValueSource(strings = {"DF测试部门02", "DF测试部门03"})
    void removeDepartment(String departmentName) {
        assertFalse(contactPage.removeDepartment(departmentName).search(departmentName).containsFromCurrentList(departmentName));
    }

}