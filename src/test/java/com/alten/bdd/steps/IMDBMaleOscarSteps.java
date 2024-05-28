package com.alten.bdd.steps;

import com.alten.bdd.pages.HomePage;
import com.alten.bdd.pages.AllOscarAwardsPage;
import com.alten.bdd.pages.OscarAwardsPage;
import com.alten.bdd.pages.OscarInTheYearPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

public class IMDBMaleOscarSteps {

    private static final Logger LOGGER = LogManager.getLogger(IMDBMaleOscarSteps.class);
    private String actorName = null;

    @When("^I click menu$")
    public void i_Click_on_menu() throws Throwable {
        HomePage homePage = new HomePage();
        homePage.acceptCookies();
        homePage.clickMenu();
    }

    @And("^select Oscar Awards$")
    public void select_oscar_awards() {
        HomePage homePage = new HomePage();
        homePage.clickOscarsAwards();
    }

    @Then("^I navigate to Oscar (.*)$")
    public void navigate_to_Oscar_year(String age) {
        OscarAwardsPage oscarAwardsPage = new OscarAwardsPage();
        oscarAwardsPage.navigateToAllOscarsPage();
        AllOscarAwardsPage allOscarAwardsPage = new AllOscarAwardsPage();
        allOscarAwardsPage.selectYear(age);
    }

    @When("^best actor section is showed$")
    public void check_best_actor_section_is_showed() {
        OscarInTheYearPage oscarInTheYearPage = new OscarInTheYearPage();
        oscarInTheYearPage.verifyBestActorSectionIsShowed();
    }

    @Then ("^(.*) and (.*) was displayed$")
    public void check_actor_and_film_is_displayed (String actor, String film) {
        OscarInTheYearPage oscarInTheYearPage = new OscarInTheYearPage();
        actorName = actor;
        Assert.assertTrue(oscarInTheYearPage.verifyActorAndFilmAreDisplayed(actor,film));
    }

    @And ("^verify if he won the oscar (.*)$")
    public void verify_he_won_oscar (String condition) {
        OscarInTheYearPage oscarInTheYearPage = new OscarInTheYearPage();
        if (condition.equals("false"))
            Assert.assertFalse(oscarInTheYearPage.verifyActorWasTheWinner(actorName));
        else if (condition.equals("true"))
            Assert.assertTrue(oscarInTheYearPage.verifyActorWasTheWinner(actorName));
    }
}
