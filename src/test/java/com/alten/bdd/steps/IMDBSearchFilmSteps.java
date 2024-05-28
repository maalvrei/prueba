package com.alten.bdd.steps;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.alten.bdd.pages.HomePage;
import com.alten.bdd.pages.MoviesPage;
import com.alten.bdd.pages.ResultsPage;
import com.alten.bdd.pages.SectionCastingPage;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

public class IMDBSearchFilmSteps {
	private static final Logger LOGGER = LogManager.getLogger(IMDBSearchFilmSteps.class);
	
	private static String nombre="";
	private static String anyo="";
	
//	@Given("^IMDB home page is showed$")
//	public void imdb_home_page_is_showed() throws Throwable {
//
//		HomePage homePage = new HomePage();
//		homePage.verifyHomePageIsLoaded();
//	}
	
	@When("^I Search the film (.*) and (.*)$")
	public void searchFilm(String nombre,String age) throws Throwable {
		this.nombre=nombre;
		this.anyo=age;
		HomePage homePage = new HomePage();
		homePage.searchFilm(nombre, age);
	}
	
	@Then("^film page is showed$")
	public void film_page_is_showed() throws Throwable {
		ResultsPage results = new ResultsPage();
		results.verifyHomePageIsLoaded();
	}
	
	@When("^I navigate to All casting$")
	public void navigate_all_casting() throws Throwable {
		ResultsPage results = new ResultsPage();
		results.searchFilm(nombre, anyo);
		MoviesPage pelicula = new MoviesPage();
		pelicula.verifyHomePageIsLoaded();
		pelicula.clickAllCasting();
	}
	
	@Then("^All casting section is showed$")
	public void sectioN_casting_showed() throws Throwable {
		SectionCastingPage section = new SectionCastingPage();
		section.verifySectionPageIsLoaded();
	}
	
	@Then("^(.*) is found into actor's List$")
	public void is_found_into_actor(String actor) throws Throwable {
		SectionCastingPage section = new SectionCastingPage();
		section.searchActor(actor);
		Assert.assertTrue(section.searchActor(actor));
	}
}
