@AdvancedSearch
Feature: IMDB Advanced Search
  As a user
  I want to check Advanced Search feature/fun

@advancedSearchActorOscarWinner
Scenario Outline: Search Actor with an Oscar
	Given IMDB home page is showed
	When I click All and select Advanced Search
	Then Advanced Search page is shown
	When I click Names section
	And  I click on Expand All
	Then All filters is shown
	When I type on Actor's name input <actor>
	And  I select Oscar-Winning
	And  Click on See Results button
	Then Actor is show on results section
	When Click on actor's name
	Then Actor page is shown
	When I click on awards 
	Then I verify oscar won is shown
 Examples:
    | 	actor |
    | 	Denzel Washington |
