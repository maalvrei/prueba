package com.alten.bdd.steps;
import com.alten.bdd.pages.*;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

public class IMDBAdvancedSearchSteps {
    private static Logger LOGGER = LogManager.getLogger(IMDBAdvancedSearchSteps.class);


    @When("^I click All and select Advanced Search$")
    public void i_click_all_and_select_advanced_search() {
        HomePage homePage = new HomePage();
        homePage.clickAllButton();
        homePage.clickAdvacedSearch();
    }
    @Then("^Advanced Search page is shown$")
    public void advanced_search_page_is_shown() {
        AdvancedSearchPage advancedSearchPage = new AdvancedSearchPage();
        advancedSearchPage.verifyAdvancedSearchPageIsLoaded();
    }
    @When("^I click Names section$")
    public void i_click_names_section() {
        AdvancedSearchPage advancedSearchPage = new AdvancedSearchPage();
        advancedSearchPage.clickOnNameOption();
    }
    @And("^I click on Expand All$")
    public void i_click_on_expand_all() throws InterruptedException {
        AdvancedSearchPage advancedSearchPage = new AdvancedSearchPage();
        advancedSearchPage.clickOnExpandAllOption();
    }
    @Then("^All filters is shown$")
    public void all_filters_is_shown() {
        AdvancedSearchPage advancedSearchPage = new AdvancedSearchPage();
        advancedSearchPage.verifyFiltersIsLoaded();
    }
    @When("^I type on Actor's name input (.*)$")
    public void i_type_on_Actors_name_input(String actor) {
        AdvancedSearchPage advancedSearchPage = new AdvancedSearchPage();
        advancedSearchPage.fillNameInput(actor);
    }
    @And("^I select Oscar-Winning$")
    public void i_select_Oscar_Winning() {
        AdvancedSearchPage advancedSearchPage = new AdvancedSearchPage();
        advancedSearchPage.clickOnOscarWinOption();
    }
    @And("^Click on See Results button$")
    public void click_on_See_Results_button() {
        AdvancedSearchPage advancedSearchPage = new AdvancedSearchPage();
        advancedSearchPage.clickOnSeeResultsButton();
    }
    @Then("^Actor is show on results section$")
    public void actor_is_show_on_results_section() {
        AdvancedSearchPage advancedSearchPage = new AdvancedSearchPage();
        advancedSearchPage.verifyActorDetails();
    }
    @When("^Click on actor's name$")
    public void click_on_actors_name() {
        AdvancedSearchPage advancedSearchPage = new AdvancedSearchPage();
        advancedSearchPage.clickOnActorNameOption();
    }
    @Then("^Actor page is shown$")
    public void actor_page_is_shown() {
        HomePage homePage = new HomePage();
        homePage.verifyHomePageIsLoaded();
    }
    @When("^I click on awards$")
    public void i_click_on_awards() {
        ActorsPage actorsPage = new ActorsPage();
        actorsPage.clickAwardsOption();
    }
    @Then("^I verify oscar won is shown$")
    public void i_verify_oscar_won_is_shown() {
        AwardsPage awardsPage = new AwardsPage();
        boolean isOscarWon = awardsPage.verifyOscarWon();
        Assert.assertTrue("El premio Oscar debería estar presente.", isOscarWon);
    }
}
