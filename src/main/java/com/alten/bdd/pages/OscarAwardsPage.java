package com.alten.bdd.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class OscarAwardsPage extends BasePage {

    private static final Logger LOGGER = LogManager.getLogger(OscarAwardsPage.class);
    private static final By ALL_OSCARS = By.cssSelector("div[class='sc-e0a81431-0 drnsiD'] a:nth-child(2) span:nth-child(1)");

    public void navigateToAllOscarsPage() {
        LOGGER.info("navigateToAllOscarsPage");
        waitToElementBeClickable(ALL_OSCARS);
        WebElement element = getDriver().findElement(ALL_OSCARS);
        element.click();
    }


}
