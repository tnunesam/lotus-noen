package br.com.inmetrics.neon.lib.ios;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import android.R.string;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.SwipeElementDirection;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import br.com.inmetrics.neon.constants.Constants;
import br.com.inmetrics.neon.utilities.*;


public class Button {
	
	
	private static IOSDriver driver = null;

	private WebDriverWait wait;

	public Button(IOSDriver driver) {
		this.driver = driver;
	}

	public static List<WebElement> findAll() throws Exception {
		List<WebElement> elementos = driver.findElementsByClassName("UIAButton");
		if (elementos.size() == 0){
			throw new Exception("Nenhum objeto Button encontrado na tela.");
		}

		return elementos;
	}

	public static List<WebElement> findAll(String text) throws Exception {
		List<WebElement> elementos = driver.findElementsByClassName("UIAButton");
		if (elementos.size() == 0){
			for (WebElement webElement : elementos) {
				if (!webElement.getText().contains(text)){
					elementos.remove(webElement);
					
				}
			}
			
			throw new Exception("Nenhum objeto Button encontrado na tela.");
		}

		return elementos;
	}
	
//	public static void forcaClick(String teste){

//		driver.findElement(MobileBy.IosUIAutomation(".textFields()[0]"));
//		
//	}
//	
//	private List<String> getAllElements(){
//		
//		driver.findElement(MobileBy.IosUIAutomation(".textFields()[0]"));
//		
//		ArrayList<String> list = new ArrayList<String>();
//		
//	}

	public static void Clica(WebElement element){
		MobileElement elemento = (MobileElement)element;
		elemento.tap(1, 1);
	}

	public static void clickByText(String text) throws Exception {
		boolean exist = false;
		WebDriverWait wait = new WebDriverWait(driver, Constants.TIMEOUT_WAIT);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("UIAButton")));
		List<WebElement> elementos = findAll();
		if (elementos.size()>0){
			for (WebElement webElement : elementos) {
				if (webElement.getText().toLowerCase().contains(text.toLowerCase())) {
					System.out.println("Clicando no Button com o texto " + text);
					Clica(webElement);
					exist = true;
					break;
				}
			}
		}
		if (!exist){
			throw new Exception("Nao existe o botão com o texto " + text);
		}

		if (elementos.isEmpty()){
			throw new Exception( "Nenhum objeto Button foi localizado na tela");
		}
	}


	public void clickByIndex(String TextToClick, int index) throws Exception {
		boolean exist = false;
		WebDriverWait wait = new WebDriverWait(driver, Constants.TIMEOUT_WAIT);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("UIAButton")));
		List<WebElement> elementos = findAll(TextToClick);
		if (elementos.size()>0){
			MobileElement elemento = (MobileElement)elementos.get(index);
			Clica(elemento);
		}

		if (!exist){
			throw new Exception("Nao existe o botão com o texto " + TextToClick);
		}

		if (elementos.isEmpty()){
			throw new Exception( "Nenhum objeto Button foi localizado na tela");
		}
	}

	public void clickByIndex(int index) throws Exception {
		boolean exist = false;
		WebDriverWait wait = new WebDriverWait(driver, Constants.TIMEOUT_WAIT);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("UIAButton")));
		List<WebElement> elementos = findAll();
		if (elementos.size()>0){
			elementos.get(index);
		}
		if (!exist){
			throw new Exception("Nao existe o botão com o indice " + index);
		}

		if (elementos.isEmpty()){
			throw new Exception( "Nenhum objeto Button foi localizado na tela");
		}
	}

	public void clickById(String id) throws Exception {
		try {
			MobileElement elemento = (MobileElement) driver.findElementByAccessibilityId(id);
			Clica(elemento);

		} catch (Exception e) {
			throw new Exception ("Objeto UIAButton nao localizado com o Id " + id);
		}
	}

	public void clickByName(ArrayList<WebElement> names) throws Exception {
		String campo = "";
		try {
			for (WebElement webElement : names) {
				campo = webElement.getText();
				System.out.println("Clicando no UIAButton com o texto " + campo);
				Clica(webElement);
			}
		} catch (Exception e) {
			throw new Exception ("Objeto UIAButton nao localizado com o texto " + campo);
		}
	}

	public static void clickByAttribute(String atributo , String valorAtributo) throws Exception {
		boolean exist = false;
		WebDriverWait wait = new WebDriverWait(driver, Constants.TIMEOUT_WAIT);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("UIAButton")));
		List<WebElement> elementos = findAll();
		if (elementos.size()>0){
			for (WebElement webElement : elementos) {
				if (webElement.getAttribute(atributo).toLowerCase().contains(valorAtributo.toLowerCase())) {
					System.out.println("Clicando no Button com o atributo " + atributo + " e valor "+ valorAtributo);
					Clica(webElement);
					exist = true;
					break;
				}
			}
		}
		if (!exist){
			throw new Exception("Nao existe o botão com o atributo " + atributo + " e valor "+ valorAtributo);
		}

		if (elementos.isEmpty()){
			throw new Exception( "Nenhum objeto Button foi localizado na tela");
		}
	}

	public void clickByXpath(String xpath) throws Exception {
		System.out.println("Clicando no Button com o id " + xpath);

		List<WebElement> elements = driver.findElementsByXPath(xpath);
		if (elements.size() > 0) {
			elements.get(0).click();
		}

		else{
			throw new Exception ("Objeto Button nao localizado com o Xpath " + xpath);
		}
	}


	public void clickAt2(String IOSButton) throws Exception {
		FindObject findOBJ = new FindObject(driver);
		if (findOBJ.locateObject("UIAButton", IOSButton)) {
			buttonFocus(findOBJ.getxCenter(), findOBJ.getyCenter());
			while (!buttonFocus(findOBJ.getxCenter(), findOBJ.getyCenter())) {
				findOBJ.locateObject("UIAButton", IOSButton);
			}
			findOBJ.clickObject();
		} else {
			throw new Exception ("Objeto botão com o texto " + IOSButton + " não localizado");
		}

	}

	public static String getAttribute(String identificador, String atributo) throws Exception {
		boolean exist = false;
		WebDriverWait wait = new WebDriverWait(driver, Constants.TIMEOUT_WAIT);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("UIAButton")));
		List<WebElement> elementos = findAll();
		if (elementos.size()>0){
			for (WebElement webElement : elementos) {
				if (webElement.getText().contains(identificador.toLowerCase())) {
					System.out.println("Capturando o Button com o atributo " + atributo);
					exist = true;
					return webElement.getAttribute(atributo);
				}
			}
		}
		if (!exist){
			throw new Exception("Nao existe o botão com o texto " + identificador );
		}

		if (elementos.isEmpty()){
			throw new Exception( "Nenhum objeto Button foi localizado na tela");
		}

		return null;
	}

	public boolean buttonFocus(int xCenter, int yCenter) {
		boolean returnStatus = false;
		org.openqa.selenium.Dimension dimensions = driver.manage().window().getSize();
		int screenWidth = dimensions.getWidth();
		int screenHeight = dimensions.getHeight();
		if (yCenter > screenHeight) {
			int startX = screenWidth / 2;
			int startY = screenHeight - 10;
			int endY = screenHeight / 2;
			TouchAction action = new TouchAction((MobileDriver) driver).press(startX, startY).waitAction(1000).moveTo(startX, endY)
					.release();
			action.perform();
			returnStatus = false;
		} else {
			returnStatus = true;
		}
		return returnStatus;
	}
}
