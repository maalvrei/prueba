package com.alten.bdd.pages;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class AdvancedSearchPage extends BasePage{
    private static final Logger LOGGER = LogManager.getLogger(AdvancedSearchPage.class);
    private static final By NAME_OPTION = By.xpath("//li[contains(@class, 'ipc-tab--on-base') and .//*[contains(@class, 'ipc-icon--people')]]");
    private static final By EXPAND_ALL_OPTION = By.xpath("//button[@class=\"ipc-btn ipc-btn--no-padding ipc-btn--center-align-content ipc-btn--default-height ipc-btn--core-base ipc-btn--theme-base ipc-btn--on-accent2 ipc-text-button sc-57ba0b6e-4 jOyKZA\"]");
    private static final By FILTERS = By.xpath("//section[@class=\"ipc-page-background ipc-page-background--base sc-57ba0b6e-3 jpWgMY\"]");
    private static final By NAME_INPUT = By.xpath("//input[@class=\"ipc-textfield__input\"]");
    private static final By OSCAR_WINNING_OPTION = By.xpath("//button[@data-testid=\"test-chip-id-oscar_winner\"]");
    private static final By SEE_RESULTS_BUTTON = By.xpath("//button[@class=\"ipc-btn ipc-btn--single-padding ipc-btn--center-align-content ipc-btn--default-height ipc-btn--core-accent1 ipc-btn--theme-base sc-e3ac1175-4 ceNEEH\"]");
    private static final By ACTOR_DETAILS = By.xpath("//ul[@class=\"ipc-metadata-list ipc-metadata-list--dividers-between sc-748571c8-0 jmWPOZ detailed-list-view ipc-metadata-list--base\"]");
    private static final By ACTOR_NAME = By.xpath("//a[@class=\"ipc-title-link-wrapper\"]");

    public void verifyAdvancedSearchPageIsLoaded() {
        LOGGER.info("Verify advanced page is loaded");
        this.waitToElementBeClickable(NAME_OPTION);
    }
    public void clickOnNameOption(){
        LOGGER.info("Clicking on Name Option");
        WebElement element = getDriver().findElement(NAME_OPTION);
        element.click();
    }
    public void clickOnExpandAllOption() {
        LOGGER.info("Clicking on Expand All Option");
        explicitWait(3000);
        WebElement element = getDriver().findElement(EXPAND_ALL_OPTION);
        element.click();
    }
    public void verifyFiltersIsLoaded(){
        LOGGER.info("Verify Filters is loaded");
        this.waitToElementBeClickable(FILTERS);
    }
    public void fillNameInput(String actor){
        LOGGER.info("Filling Name Input");
        this.waitPresenceOfElement(NAME_INPUT);
        WebElement element = getDriver().findElement(NAME_INPUT);
        element.sendKeys(actor);
    }
    public void clickOnOscarWinOption(){
        LOGGER.info("Clicking on Oscar Win Option");
        waitPresenceOfElement(OSCAR_WINNING_OPTION);
        scrollToElement(OSCAR_WINNING_OPTION);
        waitToElementBeClickable(OSCAR_WINNING_OPTION);
        WebElement element = getDriver().findElement(OSCAR_WINNING_OPTION);
        JavascriptExecutor executor = (JavascriptExecutor) getDriver();
        executor.executeScript("arguments[0].click();", element);
    }
    public void clickOnSeeResultsButton(){
        LOGGER.info("Clicking on See Results Button");
        waitPresenceOfElement(SEE_RESULTS_BUTTON);
        WebElement element = getDriver().findElement(SEE_RESULTS_BUTTON);
        element.click();
    }
    public void verifyActorDetails(){
        LOGGER.info("Verify Actor Details");
        this.waitPresenceOfElement(ACTOR_DETAILS);
    }
    public void clickOnActorNameOption(){
        LOGGER.info("Clicking on Actor Name Option");
        waitPresenceOfElement(ACTOR_NAME);
        WebElement element = getDriver().findElement(ACTOR_NAME);
        element.click();
    }
}
