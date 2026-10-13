package com.grocery.ui;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.Assert.*;

public class GroceryUITest {

    private WebDriver driver;
    private String baseUrl = "http://localhost:8080/online-grocery-shop";

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testHomePageLoads() {
        driver.get(baseUrl + "/products/");
        String title = driver.getTitle();
        assertTrue("Home page should load", title.contains("Grocery"));
    }

    @Test
    public void testProductListDisplayed() {
        driver.get(baseUrl + "/products/");
        WebElement productList = driver.findElement(By.id("product-list"));
        assertNotNull("Product list should be visible", productList);
    }

    @Test
    public void testSearchFunctionality() {
        driver.get(baseUrl + "/products/");
        WebElement searchBox = driver.findElement(By.id("search-input"));
        searchBox.sendKeys("Rice");
        WebElement searchBtn = driver.findElement(By.id("search-btn"));
        searchBtn.click();
        assertTrue(driver.getCurrentUrl().contains("query=Rice"));
    }

    @Test
    public void testAddToCart() {
        driver.get(baseUrl + "/products/1");
        WebElement addBtn = driver.findElement(By.id("add-to-cart-btn"));
        addBtn.click();
        driver.get(baseUrl + "/cart/");
        WebElement cartItems = driver.findElement(By.id("cart-items"));
        assertNotNull("Cart should have items", cartItems);
    }

    @Test
    public void testCartPageLoads() {
        driver.get(baseUrl + "/cart/");
        String title = driver.getTitle();
        assertTrue("Cart page should load", title.contains("Cart"));
    }
}