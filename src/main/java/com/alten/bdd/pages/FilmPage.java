package com.alten.bdd.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class FilmPage extends BasePage {

    private static final Logger LOGGER = LogManager.getLogger(FilmPage.class);
    private static final By IMDB_HOME_PAGE_LOGO = By.id("home_img");
    private static final By FILM_IMAGES = By.xpath("//span[contains(.,'417')]");
    private static final By INPUT_PHOTOS = By.cssSelector(".sc-df9f4be9-0 > div:nth-child(1) > a:nth-child(1)");

    public void verifyFilmPageIsLoaded() {
        LOGGER.info("verifyFilmPageIsLoaded");
        this.waitToElementBeClickable(IMDB_HOME_PAGE_LOGO);
    }

    public void clickImagesNumber() {
        LOGGER.info("clickImagesNumber");
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollTo(0, 1500)");
        explicitWait(2000);

        saveImagesNum();

        WebElement elementPhotos = getDriver().findElement(INPUT_PHOTOS);
        elementPhotos.click();

    }

    public int saveImagesNum() {
        WebElement elementNumImages = getDriver().findElement(FILM_IMAGES);
        String num = elementNumImages.getText();

        // Dividir la cadena por espacios y tomar el último elemento
        String[] parts = num.split(" ");
        String numString = parts[parts.length - 1];

        // Eliminar cualquier carácter no numérico del número
        numString = numString.replaceAll("[^\\d]", "");

        // Convertir la cadena numérica a entero
        int arrayNumSave = Integer.parseInt(numString);
        return arrayNumSave;
    }

}
