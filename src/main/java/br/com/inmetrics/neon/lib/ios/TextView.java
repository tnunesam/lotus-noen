package br.com.inmetrics.neon.lib.ios;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.MobileBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import br.com.inmetrics.neon.utilities.FindObject;

public class TextView {

	// UIAStaticText

	private RemoteWebDriver driver = null;
	private FindObject fObject;
	List<MobileElement> melement;

	public TextView(RemoteWebDriver Driver) {
		this.driver = Driver;
		fObject = new FindObject(driver);
	}

	public List<MobileElement> findAll() {
		List<MobileElement> elements = fObject.findIOSElementXpath("//UIAStaticText");
		for (int i = 0; i < elements.size(); i++) {
			System.out.println("ID = " + elements.get(i).getId());
			System.out.println("Text = " + elements.get(i).getText());
		}
		
		return elements;
	}

	public void clickAt(String TextToclick) throws Exception {
		System.out.println("Clicando no TextView com o texto " + TextToclick);
		List<WebElement> elements = driver.findElementsByXPath("//UIAStaticText[@name='" + TextToclick + "']");
		try {
			if (elements.size() > 0 && fObject.getStatus() == null) {
				elements.get(0).click();
			} else {
				throw new Exception ( "Objeto textview nao localizado com o texto " + TextToclick);
			}
		} catch (Exception e) {
			throw new Exception ("Objeto textview nao localizado com o texto " + TextToclick);
		}
	}

	public void clickByIndex(String TextToclick, int index) throws Exception {
		System.out.println("Clicando no TextView com o texto " + TextToclick);
		List<WebElement> elements = driver.findElements(By.xpath("//UIAStaticText[@name='" + TextToclick + "']"));
		if (elements.size() > 0) {
			elements.get(index - 1).click();
		} else {
			throw new Exception ("Objeto textview nao localizado com o texto " + TextToclick);
		}
	}

	public void clickById(String ID) throws Exception {
		System.out.println("Clicando no textview com o id " + ID);
		List<org.openqa.selenium.WebElement> elements = driver.findElements(By.xpath("//UIAStaticText[@path='" + ID + "']"));
		if (elements.size() > 0) {
			elements.get(0).click();
		} else {
			throw new Exception ("Objeto textview nao localizado com o ID " + ID);
		}
	}

	public void insertText(String ID, String text) throws Exception {
		System.out.println("Clicando no textview com o id " + ID);
		try {
			List<WebElement> elements = driver.findElements(By.xpath("//UIATextView[@path='" + ID + "']"));
			if (elements.size() > 0) {
				elements.get(0).click();
				elements.get(0).sendKeys(text);
			} else {
				throw new Exception ( "Objeto textview nao localizado com o ID " + ID);
			}
		} catch (Exception e) {
			throw new Exception ("Objeto textview nao localizado com o ID " + ID);
		}

	}

	public void clickTextView(String Locator) throws Exception {
		FindObject findOBJ = new FindObject(driver);
		if (findOBJ.locateObject("CheckedTextView", Locator)) {
			findOBJ.clickObject();
		} else {
			throw new Exception ("Objeto UIATextField com o texto " + Locator + "não localizado");
		}

	}

}
