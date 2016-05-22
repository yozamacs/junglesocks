package com.maryum;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.*;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;

@RunWith(DataProviderRunner.class)
public class JungleSocksTest {
    private static WebDriver webDriver;

    @BeforeClass
    public static void setUp() {
        webDriver = new FirefoxDriver();
    }

    @AfterClass
    public static void tearDown() {
        webDriver.quit();
    }


    @Test
    @UseDataProvider("salesTaxData")
    public void shouldTestSalesTax(String state, Double taxRate) {
        webDriver.get(JungleSocksConstants.TEST_URL);
        fillOutForm(state);
        webDriver.findElement(By.name(JungleSocksConstants.SUBMIT)).click();
        assertThatPricesAreCorrect(taxRate);
    }

    private void fillOutForm(String state) {
        List<WebElement> inputs = webDriver.findElements(By.name(JungleSocksConstants.INPUT_FIELDS));
        List<String> values = Arrays.asList("1","3","4","7");
        int x = 0;
        for(WebElement element: inputs) {
            element.sendKeys(values.get(x));
            x++;
        }
        new Select(webDriver.findElement(By.name(JungleSocksConstants.STATE_DROPDOWN))).selectByValue(state);

    }

    private void assertThatPricesAreCorrect(Double taxRate) {
        String subtotal = webDriver.findElement(By.id("subtotal")).getText().replace("$","");
        double sub = Double.valueOf(subtotal);
        double tax = sub * taxRate;
        double sum = sub + tax;
        double taxRounding = Math.round(tax*100.0)/100.0;
        double sumRounding = Math.round(sum*100.0)/100.0;
        String taxes = String.valueOf(taxRounding);
        String total = String.valueOf(sumRounding);
        assertThat(webDriver.findElement(By.id("taxes")).getText(), containsString(taxes));
        assertThat(webDriver.findElement(By.id("total")).getText(), containsString(total));
    }

    @DataProvider
    public static Object[][] salesTaxData() {
        return new Object[][] {
        {"AL", .05},
        {"AK", .05},
        {"AZ", .05},
        {"AR", .05},
        {"CA", .08},
        {"CO", .05},
        {"CT", .05},
        {"DE", .05},
        {"FL", .05},
        {"GA", .05},
        {"HI", .05},
        {"ID", .05},
        {"IL", .05},
        {"IN", .05},
        {"IA", .05},
        {"KS", .05},
        {"KY", .05},
        {"LA", .05},
        {"ME", .05},
        {"MD", .05},
        {"MA", .05},
        {"MI", .05},
        {"MN", .00},
        {"MS", .05},
        {"MO", .05},
        {"MT", .05},
        {"NE", .05},
        {"NV", .05},
        {"NH", .05},
        {"NJ", .05},
        {"NM", .05},
        {"NY", .06},
        {"NC", .05},
        {"ND", .05},
        {"OH", .05},
        {"OK", .05},
        {"OR", .05},
        {"PA", .05},
        {"RI", .05},
        {"SC", .05},
        {"SD", .05},
        {"TN", .05},
        {"TX", .05},
        {"UT", .05},
        {"VT", .05},
        {"VA", .05},
        {"WA", .05},
        {"WV", .05},
        {"WI", .05},
        {"WY", .05}};
    }
}
