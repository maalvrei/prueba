package com.alten.bdd.pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SectionCastingPage extends BasePage{
	
	private static final Logger LOGGER = LogManager.getLogger(SectionCastingPage.class);
	private static final By IMDB_HOME_PAGE_LOGO	= By.id("home_img_holder");
	private static final By TABLA= By.cssSelector("table.cast_list tbody");
	
	public void verifySectionPageIsLoaded() {
		LOGGER.info("verifySectionPageIsLoaded");
		this.waitToElementBeClickable(IMDB_HOME_PAGE_LOGO);
	}
	
	public boolean searchActor(String nombre) {
		
		LOGGER.info("searchActor");
		
		//Cogemos la tabla entera
        WebElement tbody = getDriver().findElement(TABLA);
        
        //Cogemos los actores
        List<WebElement> filasActores = tbody.findElements(By.tagName("tr"));
        
        LOGGER.info("Actores: "+filasActores.size());
        
        //Recorremos todos los actores
        for (WebElement fila : filasActores) {
        	
        	//Cogemos los datos de los actores
        	 List<WebElement> columnas = fila.findElements(By.tagName("td"));
        	 
        	 //Comprobamos si es una fila de margen
        	 if(columnas.size()==1) {
        		 continue;
        	 }
        	 
        	 //Cogemos el nombre del actor
             String nombreFila = columnas.get(1).getText();            
             
             //Comprobamos si esta
            if (nombreFila.equalsIgnoreCase(nombre)) {
            	LOGGER.info("El actor se encuentra en el reparto");
            	return true;
            }
        }
        
        //Comprobamos si no esta
        LOGGER.error("El actor no se encuentra en el reparto");
		return false;
	}
}
