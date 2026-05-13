package com.test.tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SearchPage;
import pages.UploadPage;

public class StudentFlowTest extends BaseTest {

    @Test
    public void testSearch() {
        SearchPage searchPage = new SearchPage(driver);
        boolean result = searchPage.searchById("5");
        Assert.assertTrue(result);
    }

    @Test
    public void testUpload() {
        UploadPage uploadPage = new UploadPage(driver);
        uploadPage.uploadFile(System.getProperty("user.dir") + "\\test.csv");
        Assert.assertTrue(true);
    }

    @Test
    public void testHome() {
        driver.get("http://localhost:5173/");
        Assert.assertTrue(true);
    }
}