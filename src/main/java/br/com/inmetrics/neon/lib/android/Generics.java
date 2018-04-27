package br.com.inmetrics.neon.lib.android;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import android.os.Bundle;
import android.R;
import android.app.Activity;
import android.app.ActivityManager;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import br.com.inmetrics.neon.config.Elementos;
import br.com.inmetrics.neon.config.funcoes;
import br.com.inmetrics.neon.constants.Constants;

public class Generics {

	private static RemoteWebDriver driver = null;
	
	//private static final Logger logger = 
	//Logger.getLogger(Generics.class);

	@SuppressWarnings("static-access")
	public Generics(RemoteWebDriver Driver) {
		this.driver = Driver;
	}
	
	public List<WebElement> mapeiaTela(String classe){
		List<WebElement> objetos = driver.findElements(MobileBy.className(classe));
		return objetos;
	}
	
	
	public String getTextFromClipboard() {
		try{
//			String testedoido = Context.CLIPBOARD_SERVICE;
//			
//			String context = ((AndroidDriver) driver).getContext();
			
			//android.content.ClipboardManager clipboard = (android.content.ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
			//testedoido = (String) clipboard.getText();
			
		}catch (Exception e){
			e.printStackTrace();
		}

		return null;
	}
	
	public void scollUp(){
		
		((AndroidDriver) driver).context("NATIVE_APP");
		Dimension dimensions = driver.manage().window().getSize();
        int screenWidth = dimensions.getWidth();
        int screenHeight = dimensions.getHeight();

        ((AndroidDriver) driver).swipe(screenWidth-1, screenHeight-1, screenWidth-5, 0  , 500);
	}
	
	public void scollDown(){
		((AndroidDriver) driver).context("NATIVE_APP");
		Dimension dimensions = driver.manage().window().getSize();
        int screenWidth = dimensions.getWidth();
        int screenHeight = dimensions.getHeight();

        ((AndroidDriver) driver).swipe(screenWidth-1, 0, screenWidth-1, screenHeight-5  , 500);
	}
	
	public void scollLeft(){
		((AndroidDriver) driver).context("NATIVE_APP");
		Dimension dimensions = driver.manage().window().getSize();
        int screenWidth = dimensions.getWidth();
        int screenHeight = dimensions.getHeight();

        ((AndroidDriver) driver).swipe(screenWidth-1, screenHeight-5, 0, screenHeight-5  , 500);
	}
	
	public void scollRight(){
		((AndroidDriver) driver).context("NATIVE_APP");
		Dimension dimensions = driver.manage().window().getSize();
        int screenWidth = dimensions.getWidth();
        int screenHeight = dimensions.getHeight();
            
        ((AndroidDriver) driver).swipe(0, screenHeight-5, screenWidth-1, screenHeight-5  , 500);
	}

	public boolean validaComprovante(String atual, String esperado) throws Exception {
		System.out.println("Validando comprovante." );
		boolean result = false;
		List<String> listaEsperados = Arrays.asList(esperado.split(";"));

		for (String lista : listaEsperados) {
			result = false;
			if (atual.toLowerCase().contains(lista.toLowerCase())){				

				result = true;
				System.out.println(String.format("Validando campo %s : %s", lista, result));
			}	
			else {
				System.out.println(String.format("Validando campo %s : %s", lista, result));
				return false;

			}
		}

		return result;
	}

	public void clickPosicional(int posicaoX, int posicaoY)
			throws InterruptedException {
		try{
			TouchAction touchAction = new TouchAction((MobileDriver) driver);
			int deviceScreenY = driver.manage().window().getSize().getHeight();
			int deviceScreenX = driver.manage().window().getSize().getWidth();
			posicaoX = (posicaoX * deviceScreenX) / 1440;
			posicaoY = (posicaoY * deviceScreenY) / 2560;
			touchAction.press(posicaoX, posicaoY);
			touchAction.release().perform();
		}
		catch(Exception e){
			//logger.trace("");

		}
	}

	public void pressKeyCode(int tecla){

		((AndroidDriver) driver).sendKeyEvent(tecla);

	}

	public static void wait(String tipo, String name,String classe) throws Exception{
		while (!exists(tipo, name, classe)){
			Thread.sleep(Constants.TIMEOUT_WAIT);
		}
	}

	public boolean isClickable(String objeto){
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElementById(objeto)));
			return true;
		}
		catch (Exception e)
		{
			try{
				WebDriverWait wait = new WebDriverWait(driver, 5);
				wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath(objeto)));
				return true;
			}
			catch (Exception ex){
				return false;
			}
		}
	}

	public static boolean exists(String tipo, String name, String classe){
		System.out.println("Verificando existencia " + name);
		WebDriverWait wait = new WebDriverWait(driver, Constants.TIMEOUT_WAIT);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className(classe)));
		List<WebElement> elementos = driver.findElements(By.className(classe));
		if (elementos.size()>0){
			if (tipo.toLowerCase()=="id"){
				for (WebElement element : elementos) {
					if (element.getAttribute("resourceId").toLowerCase().contains(name.toLowerCase())){
						return true;
					}
				}
			}
			if (tipo.toLowerCase()=="xpath"){
				if (driver.findElementByXPath(name)!=null){
					return true;
				}
			}
			if (tipo.toLowerCase()=="text"){
				for (WebElement element : elementos) {
					if (element.getText().toLowerCase().contains(name.toLowerCase())){
						return true;
					}
				}
			}

		}
		return false;
	}

	public static boolean isEnabled(String object, int opcao){

		switch(opcao){
		case 1:
			return driver.findElementByXPath(object).isEnabled();
		case 2:
			return driver.findElementById(object).isEnabled();			

		}

		return false;
	}

	public boolean isSelected(String object, int opcao){
		switch(opcao)
		{
		case 1:
			return driver.findElementByXPath(object).isSelected();
		case 2:
			return driver.findElementById(object).isSelected();
		}
		return false;
	}


	public static void closeAppium(RemoteWebDriver driver)
	{
		try 
		{
			if (driver != null && driver != null) {
				driver.quit();
				if (funcoes.deviceConected()) {
					funcoes.executeCommand(funcoes.getKillNodeJSAppiumServerCommand("4723"));
				}
				driver.close();

			}
		}
		catch (Exception anException) 
		{
			anException.printStackTrace();
		}
	}
}
