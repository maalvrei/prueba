package com.alten.bdd.steps;

import com.alten.bdd.pages.HomePage;
import com.alten.bdd.pages.ActorsPage;
import com.alten.bdd.pages.SearchPage;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

public class IMDBActorSearchSteps {

	
	private static final Logger LOGGER 					= LogManager.getLogger(IMDBActorSearchSteps.class);
	private static String actorName;

	@When("^I Search the actor (.*)$")
	public void i_Search_the_actor(String actor) throws Throwable{
		LOGGER.info("i_Search_the_actor: " + actor);
		this.actorName = actor;

		HomePage homePage = new HomePage();
		homePage.writeActorName(actor);
		homePage.clickSearch();
	}

	@Then("^the results page is show$")
	public void the_results_page_is_show() throws Throwable{
		LOGGER.info("the_results_page_is_show");

		SearchPage searchPage = new SearchPage();

		searchPage.clickExactMatchButton();

		searchPage.verifySearchPageIsLoaded();
		Assert.assertTrue(
				searchPage.verifySearchHeader(actorName)
		);
	}


	@When("^I navigate to filmography$")
	public void i_navigate_to_filmography() throws Throwable{
		LOGGER.info("i_navigate_to_filmography");

		SearchPage searchPage = new SearchPage();

		Assert.assertTrue(
				searchPage.navigateToFilmography(actorName)
		);
	}

	@Then("^I search (.*),(.*)$")
	public void i_search_film_age(String film, String age) throws  Throwable{
		LOGGER.info("i_search_film_age: " + film + " " + age);

		ActorsPage actorsPage = new ActorsPage();

		Assert.assertTrue(
				actorsPage.verifyActorsPageIsLoaded(actorName)
		);
		actorsPage.clickMoviesButton();
		Assert.assertTrue(
				actorsPage.searchData(film,age)
		);
	}
}
