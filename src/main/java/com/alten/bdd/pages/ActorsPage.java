package com.alten.bdd.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ActorsPage extends BasePage {
    private static final Logger LOGGER = LogManager.getLogger(ActorsPage.class);
    private static final By IMDB_ACTORS_HEADER = By.xpath("//h1");
    private static final By MORE_BUTTON = By.xpath("//div[@id='accordion-item-producer-previous-projects']//div//span//button//span//span");
    private static final By PREVIOUS_PROJECTS = By.xpath("//div[@id='producer-previous-projects']//ul[@class='ipc-metadata-list ipc-metadata-list--dividers-between ipc-metadata-list--base']/li");
    private static final By AWARDS_OPTION = By.xpath("//a[contains(@class, 'ipc-link--baseAlt') and contains(@href, 'awards')]");

    public void clickAwardsOption() {
        LOGGER.info("Clicking Awars Option");
        this.waitToElementBeClickable(AWARDS_OPTION);
        WebElement element = getDriver().findElement(AWARDS_OPTION);
        element.click();
    }
    public boolean verifyActorsPageIsLoaded(String actor) {
        LOGGER.info("verifyActorsPageIsLoaded");

        waitPresenceOfElement(IMDB_ACTORS_HEADER);
        WebElement element = getDriver().findElement(IMDB_ACTORS_HEADER);

        return (element.getText().contains(actor));
    }

    public void clickMoviesButton() {
        LOGGER.info("clickMoviesButton");


        waitToElementBeClickable(MORE_BUTTON);
        WebElement elementMoreButton = getDriver().findElement(MORE_BUTTON);
        int position = (elementMoreButton.getLocation().getY() - getDriver().manage().window().getSize().getHeight()/2);
        ((JavascriptExecutor) getDriver()).executeScript("window.scrollTo(0," + position + ")");

        explicitWait(1000);
        elementMoreButton.click();
    }

    public boolean searchData(String film, String age)  {
        LOGGER.info("searchData");

        explicitWait(2000);

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(PREVIOUS_PROJECTS));
        List<WebElement> projectsList = getDriver().findElements(PREVIOUS_PROJECTS);

        boolean finded = false;
        for (WebElement project : projectsList) {
            String[] proyecto = project.getText().split("\\n");
            String movie = proyecto[0];
            String year = proyecto[3];

            if (movie.equals(film.trim()) && year.equals(age.trim())) {
                finded = true;
                int position = (project.getLocation().getY() - getDriver().manage().window().getSize().getHeight()/2);
                ((JavascriptExecutor) getDriver()).executeScript("window.scrollTo(0," + position + ")"); // colocamos la pelicula en el centro de la pantalla
                wait.until(ExpectedConditions.visibilityOf(project)); // esperamos que este en la pantalla para la foto
             }
        }

        return finded;
    }
}
