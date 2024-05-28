package com.alten.bdd.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class SearchPage extends BasePage {
    private static final Logger LOGGER = LogManager.getLogger(SearchPage.class);

    private static final By IMDB_SEARCH_HEADER 	= By.xpath("//h1");
    private static final By IMDB_HOME_PAGE_LOGO	= By.id("home_img_holder");
    private static final By IMDB_ACTORS_NAMES = By.xpath("//li[@class='ipc-metadata-list-summary-item ipc-metadata-list-summary-item--click find-result-item find-name-result']");
    private static final By EXACT_MATCH_BUTTON = By.xpath("//div[@class='ipc-title ipc-title--base ipc-title--section-title ipc-title--on-textPrimary sc-ffc93fc1-1 dGLVfz']//div[@class='ipc-title__wrapper']//div[@class='ipc-title__actions']");


    public void verifySearchPageIsLoaded() {
        LOGGER.info("verifySearchPageIsLoaded");

        this.waitToElementBeClickable(IMDB_HOME_PAGE_LOGO);
    }

    public boolean verifySearchHeader(String actor) {
        LOGGER.info("verifySearchHeader");

        waitPresenceOfElement(IMDB_SEARCH_HEADER);
        WebElement element = getDriver().findElement(IMDB_SEARCH_HEADER);

        return (element.getText().contains(actor.toLowerCase()));
    }

    public void clickExactMatchButton() {
        LOGGER.info("clickExactMatchButton");

        waitToElementBeClickable(EXACT_MATCH_BUTTON);
        WebElement element = getDriver().findElement(EXACT_MATCH_BUTTON);
        element.click();
    }

    public boolean navigateToFilmography(String actor) {
        LOGGER.info("navigateToFilmography");

        wait.until(ExpectedConditions.presenceOfElementLocated(IMDB_ACTORS_NAMES));

        List<WebElement> listOfActors = getDriver().findElements(IMDB_ACTORS_NAMES);

        boolean finded = false;
        for(WebElement actorInList:listOfActors){
            if(actorInList.getText().contains(actor)) {
                actorInList.click();
                finded = true;
                break;
            }
        }

        return(finded);
    }
}
