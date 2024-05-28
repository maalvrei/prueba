package com.alten.bdd.pages;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AwardsPage extends BasePage {
    private static final Logger LOGGER = LogManager.getLogger(AwardsPage.class);
    private static final By MORE_BUTTON = By.xpath("//section[@class='ipc-page-section ipc-page-section--base' and contains(., 'Academy Awards')]//span[@class='ipc-see-more sc-f55e45dc-0 gbpBtg single-page-see-more-button-ev0000003']" );
    private static final By OSCAR_AWARD = By.xpath("//li[contains(@class, 'ipc-metadata-list-summary-item sc-15fc9ae6-1 kZSOHj') and contains(., 'Oscar')]");
    private static ResourceBundle messages;

    static {
        try {
            messages = ResourceBundle.getBundle("messages", Locale.getDefault());
        } catch (MissingResourceException e) {
            LOGGER.error("messages.properties file not found!", e);
        }
    }
    public boolean verifyOscarWon() {
        LOGGER.info("verifyOscarWon");
        WebDriverWait wait = new WebDriverWait(getDriver(), 10);

        try {
            WebElement moreButton = getDriver().findElement(MORE_BUTTON);
            scrollToElement(MORE_BUTTON);// Metodo scroll que navega hacia el elemento
            explicitWait(5000); // Metodo con sleep para poder hacer click en el boton

            if (moreButton.isDisplayed()) {
                moreButton.click();
                LOGGER.info("Clicked on the 'More' button.");
                explicitWait(3000);
            } else {
                LOGGER.warn("The 'More' button is not clickable.");
            }
        } catch (Exception e) {
            LOGGER.warn("The 'More' button is not present on the page.");
        }

        List<WebElement> oscarAwardElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(OSCAR_AWARD));

        boolean isOscarWon = false;

        for (WebElement element : oscarAwardElements) {
            String text = element.getText().toLowerCase();

            String regex = messages.getString("winnerRegex");
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(text);
            if (matcher.find()) {
                LOGGER.info("Oscar won found");
                isOscarWon = true;
                break;
            }
        }

        if (!isOscarWon) {
            LOGGER.error("Oscar won not found");
        }
        return isOscarWon;
    }
}


