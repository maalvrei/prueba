package com.alten.bdd.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class MoviesPage extends BasePage{
	
	private static final Logger LOGGER = LogManager.getLogger(MoviesPage.class);
	private static final By IMDB_HOME_PAGE_LOGO	= By.id("home_img");
	private static final By LINK_REPARTO = By.className("ipc-metadata-list-item__label--link");
	
	public void verifyHomePageIsLoaded() {
		//Comprobamos si carga
		LOGGER.info("verifyPeliculaPageIsLoaded");
		this.waitToElementBeClickable(IMDB_HOME_PAGE_LOGO);
		
	}
	
	public void clickAllCasting() {
		LOGGER.info("clickAllCasting");
		//Llamado a un scroll
		scrollToElement(LINK_REPARTO);
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		//Esperar a el elemento
		waitToElementBeClickable(LINK_REPARTO);
		WebElement element =  getDriver().findElement(LINK_REPARTO);
		//Click en el elemento
		js.executeScript("arguments[0].click();", element);
	}
}
