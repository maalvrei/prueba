package com.alten.bdd.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class OscarInTheYearPage extends BasePage {

    /** BEST_ACTOR_SECTION es un div que contiene otros div, donde cada uno se corresponde con un actor que fue
     nominado al Oscar ese año, con su foto, etcétera... Utilizamos una expresion de xPath para determinar que queremos llegar al div
     que contenga como primer hijo un div con la clase event-widgets__award-category-name y con un texto 'Best Actor in a Leading Role'
     o 'Best Performance by an Actor in a Leading Role' para confirmar que estamos en la categoría de mejor actor */
    
    private static final String xpathExpression = "//div[div[1][@class='event-widgets__award-category-name' and (text()='Best Actor in a Leading Role' or text()='Best Performance by an Actor in a Leading Role')]]";
    private static final By BEST_ACTOR_SECTION = By.xpath(xpathExpression);
    private static final Logger LOGGER = LogManager.getLogger(OscarInTheYearPage.class);

    public void verifyBestActorSectionIsShowed() {
        LOGGER.info("Verifying if best actor section is showed");
        scrollIntoView(getDriver(),getDriver().findElement(BEST_ACTOR_SECTION));
        wait.until(ExpectedConditions.visibilityOfElementLocated(BEST_ACTOR_SECTION));
    }


    public static void scrollIntoView(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /** Este método crea una lista donde cada WebElement es el div individual de cada nomninado al Oscar, el que mencionábamos
    anteriormente, que está dentro de BEST_ACTOR_SECTION */
    public List<WebElement> nominationsList () {
        return getDriver().
                findElement(BEST_ACTOR_SECTION).
                findElement(By.className("event-widgets__award-category-nominations")).
                findElements(By.className("event-widgets__award-nomination"));
    }

    /** Este método devuelve sólamente el nombre del ganador, para después en los steps comprobar si cada actor fue o no
    ganador. Lo hacemos de la siguiente forma. */

    public String winnerName() {
        // Inicializamos un webElement llamado winner a null al que después asignaremos datos
        WebElement winner = null;
        for (WebElement nomination : nominationsList()) {
            try {
                // Para saber si una nominación de la lista de nominaciones es la ganadora vamos a comprobar que contenga un div
                // que sólo tiene el ganador, que se corresponde con un texto con fondo dorado y la palabra "winner"
                // Este div tiene la clase event-widgets__winner-badge que es única para él lo que nos facilita la tarea
                By winnerDiv = By.className("event-widgets__winner-badge");
                nomination.findElement(winnerDiv);
                winner = nomination;
                // Rompemos el bucle manualmente en el caso de que hayamos dado con el ganador. Si la nominacion iterada no es
                // la ganadora se produce una excepción ya que findElement lanza una excepción si no encuentra el webElement que
                // le estemos pidiendo
                break;
            } catch (Exception e) {
                LOGGER.info("Buscando al ganador");
            }
        }
        // Devolvemos el nombre del actor, es una etiqueta a que se encuentra dentro del div de la nominacion, en otro div
        return winner.findElement(By.className("event-widgets__nominee-name")).findElement(By.tagName("a")).getText();
    }

    /** En este método recorremos todas las etiquetas a de las nominaciones, que se corresponden siempre con nombres de actores
     y sus películas. Si aparecen nombre y película, el método devolverá true, ya que por cada una que encontremos sumaremos
     uno a la variable dataFound */
    public boolean nominationContainsFilmAndActor (WebElement nomination, String actor, String film) {
        List<WebElement> nominationDates = nomination.findElements(By.tagName("a"));
        int dataFound = 0;
        for (WebElement date : nominationDates)
            if (date.getText().trim().equalsIgnoreCase(actor) || date.getText().trim().equalsIgnoreCase(film)) dataFound++;
        return dataFound == 2;
    }

    /** Recorremos la lista de nominaciones para verificar que la nominacion que contiene nuestro actor y película esté presente
    con el método anterior */
    public boolean verifyActorAndFilmAreDisplayed (String actor, String film) {
        for (WebElement nomination : nominationsList())
            if (nominationContainsFilmAndActor(nomination, actor, film)) return true;
        return false;
    }

    public boolean verifyActorWasTheWinner (String actor) {
        return winnerName().equalsIgnoreCase(actor);
    }

}
