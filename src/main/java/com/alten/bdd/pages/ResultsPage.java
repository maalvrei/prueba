package com.alten.bdd.pages;

import java.text.Normalizer;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ResultsPage extends BasePage{
	private static final Logger LOGGER = LogManager.getLogger(ResultsPage.class);
	private static final By IMDB_HOME_PAGE_LOGO	= By.id("home_img");
	
	public void verifyHomePageIsLoaded() {
		LOGGER.info("verifyResultPageIsLoaded");
		this.waitToElementBeClickable(IMDB_HOME_PAGE_LOGO);
	}
	
	public void searchFilm(String nombrePelicula,String anyo) {
		 boolean esta=false;
		 
		//Cogemos los divs de las peliculas
		List<WebElement> elementosPeliculas = getDriver().findElements(By.className("ipc-metadata-list-summary-item"));
		
		//Recorremos todas las peliculas
		for (WebElement pelicula : elementosPeliculas) {
			
			//Cogemos el link y el nombre de la pelicula
		    WebElement elemento = pelicula.findElement(By.tagName("a"));
		    String nombre = elemento.getText();
		    
		    //Cogemos el año de la pelicula
		    WebElement listaAnyo = pelicula.findElement(By.className("ipc-metadata-list-summary-item__tl"));
		    WebElement elementoAnyo = listaAnyo.findElement(By.tagName("span"));
		    String anyoElemento=elementoAnyo.getText();
		    
		    LOGGER.info("Este es la pelicula: "+removeAccents(nombre)+" Año: "+anyoElemento+" La que buscas es: "+removeAccents(nombrePelicula)+" del año: "+anyo);
		    
		    //Comprobamos si es la pelicula que buscamos
		    if (removeAccents(nombre).equals(removeAccents(nombrePelicula))&anyoElemento.equals(anyo)) {
		        elemento.click();
		        esta=true;
		        break;
		    }
		}
		//Comprobamos si no encontro la pelicula
		if(!esta) {
			LOGGER.error("No se encontro la pelicula");
		}
		
	}
	
	private static String removeAccents(String input) {
	    // Normalizar para quitar acentos
	    String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
	    Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
	    String sinAcentos = pattern.matcher(normalized).replaceAll("").toLowerCase();

	    // Eliminar texto entre paréntesis
	    Pattern patron = Pattern.compile("\\s*\\([^)]*\\)\\s*");
	    Matcher matcher = patron.matcher(sinAcentos);
	    String sinParentesis = matcher.replaceAll("").trim();

	    return sinParentesis;
	}

}
