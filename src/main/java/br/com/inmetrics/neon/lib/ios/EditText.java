package br.com.inmetrics.neon.lib.ios;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.inmetrics.neon.constants.Constants;
import br.com.inmetrics.neon.utilities.FindObject;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;


public class EditText {
	private static IOSDriver driver = null;
	private List<WebElement> elements;

	public EditText(IOSDriver driver) {
		this.driver = driver;
	}

	public static List<WebElement> findAll() throws Exception {
		List<WebElement> elementos = driver.findElementsByClassName("UIATextField");
		if (elementos.size() == 0){
			throw new Exception("Nenhum objeto Text Field encontrado na tela.");
		}

		return elementos;
	}

	public static List<WebElement> findAll(String text) throws Exception {
		List<WebElement> elementos = driver.findElementsByClassName("UIATextField");
		if (elementos.size() == 0){
			for (WebElement webElement : elementos) {
				if (!webElement.getText().contains(text)){
					elementos.remove(webElement);
				}
			}

			throw new Exception("Nenhum objeto Text Field encontrado na tela.");
		}

		return elementos;
	}

	public static void clica(WebElement element){
		MobileElement elemento = (MobileElement)element;
		elemento.tap(1, 1);
	}

	public static void clickByText(String text) throws Exception {
		boolean exist = false;
		WebDriverWait wait = new WebDriverWait(driver, Constants.TIMEOUT_WAIT);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("UIATextField")));
		List<WebElement> elementos = findAll();
		if (elementos.size()>0){
			for (WebElement webElement : elementos) {
				if (webElement.getText().toLowerCase().contains(text.toLowerCase())) {
					System.out.println("Clicando no Text Field com o texto " + text);
					clica(webElement);
					exist = true;
					break;
				}
			}
		}
		if (!exist){
			throw new Exception("Nao existe o Text Field com o texto " + text);
		}

		if (elementos.isEmpty()){
			throw new Exception( "Nenhum objeto Text Field foi localizado na tela");
		}
	}


	public void clickByIndex(String TextToClick, int index) throws Exception {
		boolean exist = false;
		WebDriverWait wait = new WebDriverWait(driver, Constants.TIMEOUT_WAIT);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("UIATextField")));
		List<WebElement> elementos = findAll(TextToClick);
		if (elementos.size()>0){
			MobileElement elemento = (MobileElement)elementos.get(index);
			clica(elemento);
		}

		if (!exist){
			throw new Exception("Nao existe o Text Field com o texto " + TextToClick);
		}

		if (elementos.isEmpty()){
			throw new Exception( "Nenhum objeto Text Field foi localizado na tela");
		}
	}

	public void clickByIndex(int index) throws Exception {
		boolean exist = false;
		WebDriverWait wait = new WebDriverWait(driver, Constants.TIMEOUT_WAIT);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("UIATextField")));
		List<WebElement> elementos = findAll();
		if (elementos.size()>0){
			MobileElement mElement = (MobileElement) elementos.get(index);
			clica(mElement);
		}
		if (!exist){
			throw new Exception("Nao existe o Text Field com o indice " + index);
		}

		if (elementos.isEmpty()){
			throw new Exception( "Nenhum objeto Text Field foi localizado na tela");
		}
	}

	public void clickById(String id) throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, Constants.TIMEOUT_WAIT);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("UIATextField")));
		try {
			MobileElement elemento = (MobileElement) driver.findElementByAccessibilityId(id);
			clica(elemento);

		} catch (Exception e) {
			throw new Exception ("Objeto UIATextField nao localizado com o Id " + id);
		}
	}

	public void clickByName(ArrayList<WebElement> names) throws Exception {
		String campo = "";
		try {
			for (WebElement webElement : names) {
				campo = webElement.getText();
				System.out.println("Clicando no UIATextField com o texto " + campo);
				clica(webElement);
			}
		} catch (Exception e) {
			throw new Exception ("Objeto UIATextField nao localizado com o texto " + campo);
		}
	}

	public static void clickByAttribute(String atributo , String valorAtributo) throws Exception {
		boolean exist = false;
		WebDriverWait wait = new WebDriverWait(driver, Constants.TIMEOUT_WAIT);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("UIATextField")));
		List<WebElement> elementos = findAll();
		if (elementos.size()>0){
			for (WebElement webElement : elementos) {
				if (webElement.getAttribute(atributo).toLowerCase().contains(valorAtributo.toLowerCase())) {
					System.out.println("Clicando no Text Field com o atributo " + atributo + " e valor "+ valorAtributo);
					clica(webElement);
					exist = true;
					break;
				}
			}
		}
		if (!exist){
			throw new Exception("Nao existe o Text Field com o atributo " + atributo + " e valor "+ valorAtributo);
		}

		if (elementos.isEmpty()){
			throw new Exception( "Nenhum objeto Text Field foi localizado na tela");
		}
	}

	public void clickByXpath(String xpath) throws Exception {
		System.out.println("Clicando no Text Field com o id " + xpath);

		List<WebElement> elements = driver.findElementsByXPath(xpath);
		if (elements.size() > 0) {
			elements.get(0).click();
		}

		else{
			throw new Exception ("Objeto Text Field nao localizado com o Xpath " + xpath);
		}
	}

	public static String getAttribute(String identificador, String atributo) throws Exception {
		boolean exist = false;
		WebDriverWait wait = new WebDriverWait(driver, Constants.TIMEOUT_WAIT);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("UIATextField")));
		List<WebElement> elementos = findAll();
		if (elementos.size()>0){
			for (WebElement webElement : elementos) {
				if (webElement.getText().contains(identificador.toLowerCase())) {
					System.out.println("Capturando o Text Field com o atributo " + atributo);
					exist = true;
					return webElement.getAttribute(atributo);
				}
			}
		}
		if (!exist){
			throw new Exception("Nao existe o Text Field com o texto " + identificador );
		}

		if (elementos.isEmpty()){
			throw new Exception( "Nenhum objeto Text Field foi localizado na tela");
		}

		return null;
	}

	public boolean textFieldFocus(int xCenter, int yCenter) {
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


	public static void insertTextByAttribute(String atributo , String valorAtributo, String texto) throws Exception {
		boolean exist = false;
		WebDriverWait wait = new WebDriverWait(driver, Constants.TIMEOUT_WAIT);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("UIATextField")));
		List<WebElement> elementos = findAll();
		if (elementos.size()>0){
			for (WebElement webElement : elementos) {
				if (webElement.getAttribute(atributo).toLowerCase().contains(valorAtributo.toLowerCase())) {
					System.out.println("Inserindo texto no Edit Text com o atributo " + atributo + " e valor "+ valorAtributo);
					webElement.sendKeys(texto);
					exist = true;
					break;
				}
			}
		}
		if (!exist){
			throw new Exception("Nao existe o Text Field com o atributo " + atributo + " e valor "+ valorAtributo);
		}

		if (elementos.isEmpty()){
			throw new Exception( "Nenhum objeto Text Field foi localizado na tela");
		}
	}


	public void insertPassword(String id, String TextToInsert) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//UIASecureTextField[contains(@name,'" + id + "')]")));
		List<WebElement> elements = driver
				.findElements(By.xpath("//UIASecureTextField[contains(@name,'" + id + "')]"));

		if (elements.size() > 0) {
			elements.get(0).sendKeys(TextToInsert);
			;
		} else {
			System.out.println("Objeto UIASecureTextField nao localizado com o ID " + id);

		}
	}

	public static void InserTexttByText(String textIdentify, String textInput) throws Exception {
		boolean exist = false;
		WebDriverWait wait = new WebDriverWait(driver, Constants.TIMEOUT_WAIT);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("UIATextField")));
		List<WebElement> elementos = findAll();
		if (elementos.size()>0){
			for (WebElement webElement : elementos) {
				if (webElement.getText().toLowerCase().contains(textIdentify.toLowerCase())) {
					System.out.println("Inputando valor no  TextField com o texto " + textIdentify);
					webElement.sendKeys(textInput);
					exist = true;
					break;
				}
			}
		}
		if (!exist){
			throw new Exception("Nao existe o Text Field com o texto " + textIdentify);
		}

		if (elementos.isEmpty()){
			throw new Exception( "Nenhum objeto Text Field foi localizado na tela");
		}
	}

	public void insertTextByXpath(String xpath, String text) throws Exception {
		boolean exist = false;
		WebDriverWait wait = new WebDriverWait(driver, Constants.TIMEOUT_WAIT);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("UIATextField")));
		List<WebElement> elementos = driver.findElements(By.className("UIATextField"));
		if (elementos.size()>0){
			if (driver.findElementByXPath(xpath)!=null){
				System.out.println("Digitando o texto " + text + " na Caixa de texto com xpath" + xpath);
				driver.findElementByXPath(xpath).sendKeys(text);
				exist  = true;
			}
		}

		if (!exist) {
			throw new Exception("Nenhum objeto EditText foi localizado usando o xpath " + xpath);
		}
		if (elementos.size() == 0) {
			throw new Exception("Nao foi localizado nenhum objeto do tipo UIATextField");
		}
	}

	public void insertTextById(String id, String texto) throws Exception {
		boolean exist = false;
		WebDriverWait wait = new WebDriverWait(driver, Constants.TIMEOUT_WAIT);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("UIATextField")));
		List<WebElement> elementos = driver.findElements(By.className("UIATextField"));
		if (elementos.size()>0){
			if (driver.findElementByAccessibilityId(id)!=null){
				System.out.println("Digitando o texto " + texto + " na Caixa de texto com o Id " + id);
				driver.findElementByAccessibilityId(id).sendKeys(texto);
				exist  = true;
			}
		}

		if (!exist) {
			throw new Exception("Nenhum objeto EditText foi localizado usando o id " + id);
		}
		if (elementos.size() == 0) {
			throw new Exception("Nao foi localizado nenhum objeto do tipo UIATextField");
		}
	}


	public void clearFieldByXpath(String xpath) throws Exception{
		boolean exist = false;
		System.out.println("Limpando o campo com " + xpath);
		WebDriverWait wait = new WebDriverWait(driver, Constants.TIMEOUT_WAIT);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("UIATextField")));
		List<WebElement> elementos = driver.findElements(By.className("UIATextField"));
		if (elementos.size()> 0){
			driver.findElementByXPath(xpath).clear();
			exist = true;
		}

		if (!exist) {
			throw new Exception( "Nenhum objeto edittext foi localizado usando o ID " + xpath);
		}
	}
	
	public static void clearFieldByText(String text) throws Exception {
		boolean exist = false;
		WebDriverWait wait = new WebDriverWait(driver, Constants.TIMEOUT_WAIT);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("UIATextField")));
		List<WebElement> elementos = findAll();
		if (elementos.size()>0){
			for (WebElement webElement : elementos) {
				if (webElement.getText().toLowerCase().contains(text.toLowerCase())) {
					System.out.println("Limpando Text Field com o texto " + text);
					webElement.clear();
					exist = true;
					break;
				}
			}
		}
		if (!exist){
			throw new Exception("Nao existe o Text Field com o texto " + text);
		}

		if (elementos.isEmpty()){
			throw new Exception( "Nenhum objeto Text Field foi localizado na tela");
		}
	}
	public void clearFieldByIndex(int index) throws Exception {
		boolean exist = false;
		WebDriverWait wait = new WebDriverWait(driver, Constants.TIMEOUT_WAIT);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("UIATextField")));
		List<WebElement> elementos = findAll();
		if (elementos.size()>0){
			elementos.get(index).clear();
		}
		if (!exist){
			throw new Exception("Nao existe o Text Field com o indice " + index);
		}

		if (elementos.isEmpty()){
			throw new Exception( "Nenhum objeto Text Field foi localizado na tela");
		}
	}
}

