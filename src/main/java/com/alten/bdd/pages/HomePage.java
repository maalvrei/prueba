package com.alten.bdd.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage{
	
	private static final Logger LOGGER = LogManager.getLogger(HomePage.class);
	private static final By IMDB_SIGNIN_LINK = By.className("imdb-header__signin-text");
	private static final By IMDB_HOME_PAGE_LOGO	= By.id("home_img_holder");
	private static final By MENU_BUTTON = By.className("ipc-responsive-button__text");
	private static final By OSCARS_AWARDS = By.cssSelector("a.nav-link[role=\"menuitem\"][href=\"/oscars/?ref_=nv_ev_csegosc\"]");
	private static final By COOKIES_BUTTON = By.cssSelector("button[data-testid='accept-button']");
	private static final By INPUT_SEARCH = By.id("suggestion-search");
	private static final By RESULT_BUTTON = By.id("react-autowhatever-navSuggestionSearch--item-0");
	private static final By SEARCH_BUTTON = By.id("suggestion-search-button");
	private static final By ALL_BUTTON = By.xpath("//label[contains(@class, 'ipc-btn ipc-btn--single-padding ipc-btn--center-align-content ipc-btn')]");
	private static final By ADVACED_SEARCH = By.xpath("//a[@role=\"menuitem\" and @class=\"ipc-list__item searchCatSelector__item\"]");

	
	public void verifyHomePageIsLoaded() {
		LOGGER.info("verifyHomePageIsLoaded");
		this.waitToElementBeClickable(IMDB_HOME_PAGE_LOGO);
	}
	
	public void navigateToSignInPage() {
		LOGGER.info("navigateToSignInPage");
		WebElement element = getDriver().findElement(IMDB_SIGNIN_LINK);
		element.click();
	}

	public void acceptCookies() {
		//Aceptamos cookies
		LOGGER.info("acceptCookies");
		WebElement element = getDriver().findElement(COOKIES_BUTTON);
		element.click();
	}

	public void clickSearchBar() {
		LOGGER.info("clickSearchBar");
		WebElement element = getDriver().findElement(INPUT_SEARCH);
		element.click();
	}

	public void typeFilm(String film, String age) {
		LOGGER.info("typeFilm");
		WebElement element = getDriver().findElement(INPUT_SEARCH);
		String type = film + " ("+age+")";
		element.sendKeys(type);
	}

	public void clickResult(String film, String age) {
		LOGGER.info("clickResult");
		WebElement element = getDriver().findElement(RESULT_BUTTON);
		waitToElementBeClickable(RESULT_BUTTON);
		element.click();
	}

	public void searchFilm(String name, String age) {
		LOGGER.info("searchFilm");
		//Cogemos el input
		WebElement element = getDriver().findElement(INPUT_SEARCH);
		String type=name+" ("+age+")";
		//Le añadimos el texto a buscar
		element.sendKeys(type);
		//Cogemos el elemento y buscamos
		element = getDriver().findElement(SEARCH_BUTTON);
		element.click();
	}

	public void writeActorName(String actor) {
		LOGGER.info("writeActorName: " + actor);

		waitToElementBeClickable(INPUT_SEARCH);
		WebElement element = getDriver().findElement(INPUT_SEARCH);
		element.sendKeys(actor);
	}

	public void clickSearch() { // pulsacion del boton de busqueda
		LOGGER.info("clickSearch");
		waitToElementBeClickable(SEARCH_BUTTON);
		WebElement element = getDriver().findElement(SEARCH_BUTTON);

		element.click();
	}

	public void clickMenu() {
		LOGGER.info("clickMenu");
		WebElement element = getDriver().findElement(MENU_BUTTON);
		element.click();
	}

	public void clickOscarsAwards() {
		LOGGER.info("clickOscarsAwards");
		waitToElementBeClickable(OSCARS_AWARDS);
		WebElement element = getDriver().findElement(OSCARS_AWARDS);
		element.click();
	}
	public void clickAllButton() {
		LOGGER.info("Clicking all button");
		this.waitToElementBeClickable(ALL_BUTTON);
		WebElement element = getDriver().findElement(ALL_BUTTON);
		element.click();
	}
	public void clickAdvacedSearch() {
		LOGGER.info("Clicking advaced search");
		this.waitToElementBeClickable(ADVACED_SEARCH);
		WebElement element = getDriver().findElement(ADVACED_SEARCH);
		element.click();
	}
}
