package br.com.inmetrics.neon.lib.ios;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.inmetrics.neon.constants.Constants;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;

public class StaticText {
	private static IOSDriver driver = null;
	private List<WebElement> elements;

	public StaticText(IOSDriver driver) {
		this.driver = driver;
	}

	public static List<WebElement> findAll() throws Exception {
		List<WebElement> elementos = driver.findElementsByClassName("UIAStaticText");
		if (elementos.size() == 0){
			throw new Exception("Nenhum objeto Static Text encontrado na tela.");
		}

		return elementos;
	}

	public static List<WebElement> findAll(String text) throws Exception {
		List<WebElement> elementos = driver.findElementsByClassName("UIAStaticText");
		if (elementos.size() == 0){
			for (WebElement webElement : elementos) {
				if (!webElement.getText().contains(text)){
					elementos.remove(webElement);
				}
			}

			throw new Exception("Nenhum objeto Static Text encontrado na tela.");
		}

		return elementos;
	}

	public static void Clica(WebElement element){
		MobileElement elemento = (MobileElement)element;
		elemento.tap(1, 1);
	}

	public static void clickByText(String text) throws Exception {
		boolean exist = false;
		WebDriverWait wait = new WebDriverWait(driver, Constants.TIMEOUT_WAIT);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("UIAStaticText")));
		List<WebElement> elementos = findAll();
		if (elementos.size()>0){
			for (WebElement webElement : elementos) {
				if (webElement.getText().toLowerCase().contains(text.toLowerCase())) {
					System.out.println("Clicando no Static Text com o texto " + text);
					Clica(webElement);
					exist = true;
					break;
				}
			}
		}
		if (!exist){
			throw new Exception("Nao existe o Static Text com o texto " + text);
		}

		if (elementos.isEmpty()){
			throw new Exception( "Nenhum objeto Static Text foi localizado na tela");
		}
	}


	public void clickByIndex(String TextToClick, int index) throws Exception {
		boolean exist = false;
		WebDriverWait wait = new WebDriverWait(driver, Constants.TIMEOUT_WAIT);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("UIAStaticText")));
		List<WebElement> elementos = findAll(TextToClick);
		if (elementos.size()>0){
			MobileElement elemento = (MobileElement)elementos.get(index);
			Clica(elemento);
		}

		if (!exist){
			throw new Exception("Nao existe o Static Text com o texto " + TextToClick);
		}

		if (elementos.isEmpty()){
			throw new Exception( "Nenhum objeto Static Text foi localizado na tela");
		}
	}

	public void clickByIndex(int index) throws Exception {
		boolean exist = false;
		WebDriverWait wait = new WebDriverWait(driver, Constants.TIMEOUT_WAIT);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("UIAStaticText")));
		List<WebElement> elementos = findAll();
		if (elementos.size()>0){
			elementos.get(index);
		}
		if (!exist){
			throw new Exception("Nao existe o Static Text com o indice " + index);
		}

		if (elementos.isEmpty()){
			throw new Exception( "Nenhum objeto Static Text foi localizado na tela");
		}
	}

	public void clickById(String id) throws Exception {
		try {
			MobileElement elemento = (MobileElement) driver.findElementByAccessibilityId(id);
			Clica(elemento);

		} catch (Exception e) {
			throw new Exception ("Objeto UIAStaticText nao localizado com o Id " + id);
		}
	}

	public void clickByName(ArrayList<WebElement> names) throws Exception {
		String campo = "";
		try {
			for (WebElement webElement : names) {
				campo = webElement.getText();
				System.out.println("Clicando no UIAStaticText com o texto " + campo);
				Clica(webElement);
			}
		} catch (Exception e) {
			throw new Exception ("Objeto UIAStaticText nao localizado com o texto " + campo);
		}
	}

	public static void clickByAttribute(String atributo , String valorAtributo) throws Exception {
		boolean exist = false;
		WebDriverWait wait = new WebDriverWait(driver, Constants.TIMEOUT_WAIT);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("UIAStaticText")));
		List<WebElement> elementos = findAll();
		if (elementos.size()>0){
			for (WebElement webElement : elementos) {
				if (webElement.getAttribute(atributo).toLowerCase().contains(valorAtributo.toLowerCase())) {
					System.out.println("Clicando no Static Text com o atributo " + atributo + " e valor "+ valorAtributo);
					Clica(webElement);
					exist = true;
					break;
				}
			}
		}
		if (!exist){
			throw new Exception("Nao existe o Static Text com o atributo " + atributo + " e valor "+ valorAtributo);
		}

		if (elementos.isEmpty()){
			throw new Exception( "Nenhum objeto Static Text foi localizado na tela");
		}
	}

	public void clickByXpath(String xpath) throws Exception {
		System.out.println("Clicando no Static Text com o id " + xpath);

		List<WebElement> elements = driver.findElementsByXPath(xpath);
		if (elements.size() > 0) {
			elements.get(0).click();
		}

		else{
			throw new Exception ("Objeto Static Text nao localizado com o Xpath " + xpath);
		}
	}

	public static String getAttribute(String identificador, String atributo) throws Exception {
		boolean exist = false;
		WebDriverWait wait = new WebDriverWait(driver, Constants.TIMEOUT_WAIT);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("UIAStaticText")));
		List<WebElement> elementos = findAll();
		if (elementos.size()>0){
			for (WebElement webElement : elementos) {
				if (webElement.getText().contains(identificador.toLowerCase())) {
					System.out.println("Capturando o Static Text com o atributo " + atributo);
					exist = true;
					return webElement.getAttribute(atributo);
				}
			}
		}
		if (!exist){
			throw new Exception("Nao existe o Static Text com o texto " + identificador );
		}

		if (elementos.isEmpty()){
			throw new Exception( "Nenhum objeto Static Text foi localizado na tela");
		}

		return null;
	}
	
	public static String getAttributeByXpath(String xpath, String atributo ) throws Exception {
		boolean exist = false;
		WebDriverWait wait = new WebDriverWait(driver, Constants.TIMEOUT_WAIT);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
		if (driver.findElement(By.xpath(xpath)) != null){
			exist = true;
			return driver.findElement(By.xpath(xpath)).getAttribute(atributo);
		}

		if (!exist){
			throw new Exception("Nao existe o Static Text com o texto " + xpath );
		}

		return null;
	}


}
