package com.alten.bdd.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class AllOscarAwardsPage extends BasePage {

    private static final Logger LOGGER = LogManager.getLogger(AllOscarAwardsPage.class);
    private static final By ALL_YEARS = By.className("event-history-widget__years");

    public void selectYear(String age) {
        LOGGER.info("selectYear");
        List<WebElement> years = getDriver().findElement(ALL_YEARS).findElements(By.tagName("span"));
        WebElement year = null;
        for (WebElement element : years) if (element.getText().equals(age)) year = element;
        wait.until(ExpectedConditions.elementToBeClickable(year));
        year.click();
    }

}
