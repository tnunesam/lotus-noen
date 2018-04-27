package br.com.inmetrics.neon.utilities;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rotatable;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.SwipeElementDirection;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.HideKeyboardStrategy;
import io.appium.java_client.android.AndroidDriver;


public class Screen {
	private RemoteWebDriver driver = null;

	public Screen(RemoteWebDriver Driver) {
		this.driver = Driver;
	}
	
	public void ClickPorcentagemAbaixoCentro(double porcentagem){
		((AppiumDriver)driver).context("NATIVE_APP"); 
		Dimension size = ((AppiumDriver)driver).manage().window().getSize(); 
		int y = (int) (size.height * porcentagem); 
		int x = (int) (size.width / 2); 
		((AndroidDriver)driver).tap(1, x, y, 3000);
	}


	public void ClickAtScreen(int x, int y) throws Exception {
		if (driver!=null){
			if (driver instanceof AndroidDriver) {
				((AndroidDriver)driver).tap(1, x, y, 3000);
			}
			if (driver instanceof IOSDriver) {
				TouchAction action0 = new TouchAction((IOSDriver)driver).press(x, y).waitAction(1000).release().perform();
			}
		}
	}

	public void swipe(int Startx, int Endx, int Starty, int Endy) throws Exception {
		if (driver!=null)
		{
			if (driver instanceof AndroidDriver) {
				int deviceScreenY = driver.manage().window().getSize().getHeight();
				int deviceScreenX = driver.manage().window().getSize().getWidth();
				Startx = (Startx * deviceScreenX) / 1440;
				Starty = (Starty * deviceScreenY) / 2560;
				Endx = (Endx * deviceScreenX) / 1440;
				Endy = (Endy * deviceScreenY) / 2560;
				new TouchAction((AndroidDriver)driver).press(Startx, Starty).waitAction(1000).moveTo(Endx, Endy).release().perform();
			} 

			if (driver instanceof IOSDriver) {
				new TouchAction((IOSDriver)driver).press(Startx, Starty).waitAction(1000).moveTo(Endx, Endy).release()
				.perform();
			}
		}
	}

	public void scrolltotext(String TextToScroll) throws Exception {
		if (driver!=null){
			if (driver instanceof AndroidDriver) {
				((AndroidDriver)driver).scrollTo(TextToScroll);
			}
			if (driver instanceof IOSDriver) {
				((IOSDriver)driver).scrollTo(TextToScroll);
			}
		}
	}

	public void hideKeyboard() throws Exception{
		if (driver != null) {
			if (driver instanceof AndroidDriver)
				((AndroidDriver)driver).hideKeyboard();
			if (driver instanceof IOSDriver) {
				// driverIOS.hideKeyboard();
//				((IOSDriver)driver).hideKeyboard(HideKeyboardStrategy.PRESS_KEY, "Hide keyboard");
				((IOSDriver)driver).getKeyboard().sendKeys(Keys.RETURN); 
			}
		}
	}

	public void rotate() throws Exception {
		if (driver != null) {
			if (driver instanceof AndroidDriver)
				((Rotatable) (AndroidDriver)driver).rotate(org.openqa.selenium.ScreenOrientation.LANDSCAPE);

			if (driver instanceof IOSDriver){
				((Rotatable) (IOSDriver)driver).rotate(org.openqa.selenium.ScreenOrientation.LANDSCAPE);
			}
		}
	}
		
	public void swipeLeft(WebElement objeto){
		MobileElement elemento = (MobileElement) objeto;
		((AppiumDriver)driver).context("NATIVE_APP"); 
		Dimension size = ((AppiumDriver)driver).manage().window().getSize(); 
		Point point = elemento.getLocation();
		int endx = (int) (size.width * 0.8); 
		int startx = (int) (size.width * 0.20); 
		int starty = point.getY() - 100;
		((AppiumDriver)driver).swipe(startx, starty, endx, starty, 1000);
	}
	
	public void swipeUp(WebElement objeto){
		MobileElement elemento = (MobileElement) objeto;
		((AppiumDriver)driver).context("NATIVE_APP"); 
		Dimension size = ((AppiumDriver)driver).manage().window().getSize(); 
		Point point = elemento.getLocation();
		int endy = (int) (size.height * 0.8); 
		int starty = (int) (size.height * 0.20); 
		int startx = point.getX();
		((AppiumDriver)driver).swipe(startx, starty, startx, endy, 1000);
	}
	
	public void swipeLeft(String xpath){
		WebElement elemento = (MobileElement) ((AndroidDriver)driver).findElementByXPath(xpath);
		((AppiumDriver)driver).context("NATIVE_APP"); 
		Dimension size = ((AppiumDriver)driver).manage().window().getSize(); 
		Point point = elemento.getLocation();
		int startx = (int) (size.width * 0.8); 
		int endx = (int) (size.width * 0.1); 
		int starty = point.getY();
		((AppiumDriver)driver).swipe(startx, starty, endx, starty, 1000);
	}
	
	public void swipeUp(String xpath){
		WebElement elemento = (MobileElement) ((AndroidDriver)driver).findElementByXPath(xpath);
		((AppiumDriver)driver).context("NATIVE_APP"); 
		Dimension size = ((AppiumDriver)driver).manage().window().getSize(); 
		Point point = elemento.getLocation();
		int starty = (int) (size.height * 0.8); 
		int endy = (int) (size.height * 0.20); 
		int startx = point.getX() - 10;
		((AppiumDriver)driver).swipe(startx, starty, startx, endy, 1000);
	}
	
	public void swipeRight(WebElement objeto){
		MobileElement elemento = (MobileElement) objeto;
		((AppiumDriver)driver).context("NATIVE_APP"); 
		Dimension size = ((AppiumDriver)driver).manage().window().getSize(); 
		Point point = elemento.getLocation();
		int startx = (int) (size.width * 0.8); 
		int endx = (int) (size.width * 0.20); 
		int starty = point.getY() -100;
		((AppiumDriver)driver).swipe(startx, starty, endx, starty, 1000);
	}
	

	public String ExtractText() throws Exception {
		String text = null;
		if (driver != null) {
			String page = null;

			if (driver instanceof AndroidDriver) {
				page = ((AndroidDriver)driver).getPageSource();
			}
			if (driver instanceof IOSDriver) {
				page = ((IOSDriver)driver).getPageSource();
			}

			if (page != null) {

				if (driver instanceof AndroidDriver) {
					String lines[] = page.split(">");
					for (int i = 0; i < lines.length; i++) {
						String line = lines[i].trim();
						{
							if (line.startsWith("android.widget.TextView")) {
								text = text + "\n" + line.substring(line.indexOf("text=\""), line.indexOf("class="))
								.replace("text=", "").replace("\"", "");
							}

						}
					}
				}

				if (driver instanceof IOSDriver) {
					String lines[] = page.split(">");
					for (int i = 0; i < lines.length; i++) {
						String line = lines[i].trim();
						if (line.startsWith("<UIAStaticText")) {
							text = text + "\n" + line.substring(line.indexOf("name=\""), line.indexOf("label="))
							.replace("name=", "").replace("\"", "");
						}

					}
				}
			}
		}
		return text;
	}

	public void airplaneMode(String mode) throws Exception{
		int widthScreen = 0;
		int heigthScreen = 0;
		if (driver != null) {
			if (driver instanceof AndroidDriver){
				widthScreen = ((AndroidDriver)driver).manage().window().getSize().width;
				heigthScreen = ((AndroidDriver)driver).manage().window().getSize().height;
				int startX = widthScreen / 2;
				int endX = widthScreen / 2;
				int startY = 5;
				int endY = heigthScreen - 10;

				for (int i = 0; i < 2; i++) {
					new TouchAction((AndroidDriver)driver).press(startX, startY).waitAction(1000).moveTo(endX, endY).release()
					.perform();
				}

				Thread.sleep(3);
				// android.widget.TextView
				WebDriverWait wait = new WebDriverWait(driver, 30);
				wait.until(
						ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("android.widget.TextView")));
				List<WebElement> elements = ((AndroidDriver)driver).findElements(By.className("android.widget.TextView"));

				// Aeroplane mode
				for (int i = 0; i < elements.size(); i++) {
					if (elements.get(i).getText().equals("Aeroplane mode")
							|| elements.get(i).getText().equals("Airplane mode")) {
						elements.get(i).click();
						i = elements.size();
					}
				}
				Thread.sleep(3);
				for (int i = 0; i < 2; i++) {
					new TouchAction((AndroidDriver)driver).press(startX, endY).waitAction(1000).moveTo(endX, startY).release()
					.perform();
				}
			}
		}
		if (driver instanceof IOSDriver) {
			widthScreen = ((IOSDriver)driver).manage().window().getSize().width;
			heigthScreen = ((IOSDriver)driver).manage().window().getSize().height;
		}
	}

	public void clickAndHold(String id, int time) throws Exception  {

		// android.widget.TextView
		MobileElement elements = null;
			elements = (MobileElement) driver.findElement(By.id(id));
			//elements = (MobileElement) driver.findElement(By.name(id));

		if (elements != null) {
				new TouchAction((MobileDriver) driver).longPress(elements).release().perform();

		}
	}


}
