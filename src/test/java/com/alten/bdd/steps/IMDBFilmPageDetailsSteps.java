package com.alten.bdd.steps;

import com.alten.bdd.pages.HomePage;
import com.alten.bdd.pages.FilmPage;
import com.alten.bdd.pages.MediaViewerPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

public class IMDBFilmPageDetailsSteps {


    private static final Logger LOGGER 					= LogManager.getLogger(IMDBLoginSteps.class);
    private static final Object MESSAGE_USER_NOT_FOUND 	= "there was a problem";
    private static final Boolean BOOLEAN = true;
    private static String film = "";
    private static String age = "";
    private int imagesnumFilm = 0;
    private int imagesnumMedia = 0;

//    @Given("^IMDB home page is showed$")
//    public void imdbHomePageIsShowed() throws Throwable {
//        LOGGER.info("imdb_home_page_is_showed");
//        HomePage homePage = new HomePage();
//        homePage.verifyHomePageIsLoaded();
//    }

    @When("^I click Search and type (.*) (.*)$")
    public void searchFilm(String film,String age) throws Throwable {
        LOGGER.info("searchFilm");
        IMDBFilmPageDetailsSteps.film = film;
        IMDBFilmPageDetailsSteps.age = age;
        HomePage homePage = new HomePage();
        homePage.clickSearchBar();
        homePage.typeFilm(film, age);
    }

    @And("^click on film$")
    public void clickOnFilm(){
        LOGGER.info("clickOnFilm");
        HomePage homePage = new HomePage();
        homePage.clickResult(film, age);

    }

    @Then("^Film page is showed$")
    public void filmPageIsShowed(){
        LOGGER.info("filmPageIsShowed");
        FilmPage filmPage = new FilmPage();
        filmPage.verifyFilmPageIsLoaded();
        filmPage.clickImagesNumber();
        imagesnumFilm = filmPage.saveImagesNum();

    }

    @When("I navigate to Photo Gallery clicking on Images\\(number\\)$")
    public void navigateToPhotoGalleryClickingOnImages(){
        LOGGER.info("navigateToPhotoGalleryClickingOnImages");
        MediaViewerPage mediaViewerPage = new MediaViewerPage();
        mediaViewerPage.verifyMediaViewerPageIsLoaded();
        imagesnumMedia = mediaViewerPage.getNumOfTotalImages();

    }

    @Then("^Verify to number of photos are equals to number$")
    public void VerifyToNumberOfPhotosAreEqualsToNumber() {
        LOGGER.info("VerifyToNumberOfPhotosAreEqualsToNumber");
        Assert.assertEquals(imagesnumFilm, imagesnumMedia);
    }

}
