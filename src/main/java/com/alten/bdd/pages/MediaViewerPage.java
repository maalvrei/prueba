package com.alten.bdd.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MediaViewerPage extends BasePage {

    private static final Logger LOGGER = LogManager.getLogger(MediaViewerPage.class);
    private static final By FILM_IMAGES_COUNT = By.cssSelector(".sc-4e84196d-10");


    public void verifyMediaViewerPageIsLoaded() {
        LOGGER.info("verifyMediaViewerPageIsLoaded");
        this.waitPresenceOfElement(FILM_IMAGES_COUNT);
    }


    public int getNumOfTotalImages() {
        WebElement element = getDriver().findElement(FILM_IMAGES_COUNT);
        String num = element.getText();
        String[] arrayNum = num.split(" ");

        // Eliminar los espacios en blanco
        for (int i = 0; i < arrayNum.length; i++) {
            arrayNum[i] = arrayNum[i].trim();
        }

        LOGGER.info("numero arraynum "+arrayNum[2]);
        int arrayNumSave = Integer.parseInt(arrayNum[2]);
        return arrayNumSave; // Devuelvo el texto de la posición dos del array que debería ser 417
    }
}
