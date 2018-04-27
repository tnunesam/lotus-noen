package br.com.inmetrics.neon.lib.ios;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class FindObject {

	private int height;
	private int width;
	private int y;
	private int x;
	private String path;
	private String value;
	private String label;
	private String name;
	private RemoteWebDriver driver;
	private IOSDriver driverIOS;
	private int xCenter;
	private int yCenter;
	private String locator;
	private WebDriverWait wait;
	private String status;
	private int widthScreen;
	private int heigthScreen;
	private String ObjType;
	private String Locator;

	public FindObject(RemoteWebDriver driver) {
		this.driver = driver;
	}

	public List<WebElement> findAndroidElementXpath(String Xpath) {
		List<WebElement> elements = null;
		if (driver != null) {
			wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Xpath)));
			elements = driver.findElements(By.xpath(Xpath));
		}

		return elements;
	}

	public List<WebElement> findIOSElementXpath(String Xpath) {
		List<WebElement> elements = null;
		if (driverIOS != null) {
			try {
				wait = new WebDriverWait(driverIOS, 30);
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Xpath)));
				elements = driverIOS.findElements(By.xpath(Xpath));
			} catch (Exception e) {
				status = "Objeto com o xpath " + Xpath + " nao foi localizado";
			}
		}

		return elements;
	}

	public void clickAtXpath(String Xpath) {
		String page = "";
		for (int i = 0; i < 30; i++) {
			if (driver != null) {
				page = driver.getPageSource();
			}
			if (driverIOS != null) {
				page = driverIOS.getPageSource();
			}

			if (page.contains(Xpath)) {
				i = 30;
			} else {
				page = null;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		if (page != null) {
			String lines[] = page.split("\\r?\\n");
			for (int i = 0; i < lines.length; i++) {
				if (lines[i].contains("path=" + Xpath)) {
					String line = lines[i];
					path = line.substring(line.indexOf("path=\""), line.indexOf("x=")).replace("path=", "")
							.replace("\"", "");
					Double xDouble = Double.parseDouble(line.substring(line.indexOf("x=\""), line.indexOf("y="))
							.replace("x=", "").replace("\"", "").replace(" ", ""));
					x = xDouble.intValue();
					Double yDouble = Double.parseDouble(line.substring(line.indexOf("y=\""), line.indexOf("width="))
							.replace("y=", "").replace("\"", "").replace(" ", ""));
					y = yDouble.intValue();
					Double widthDouble = Double
							.parseDouble(line.substring(line.indexOf("width=\""), line.indexOf("height="))
									.replace("width=", "").replace("\"", "").replace(" ", ""));
					width = widthDouble.intValue();
					Double heightDouble = Double.parseDouble(line.substring(line.indexOf("height=\""), line.length())
							.replace("height=", "").replace("\"", "").replace(" ", ""));
					height = heightDouble.intValue();
					xCenter = x + (width / 2);
					yCenter = y + (height / 2);
					i = lines.length;
					new TouchAction(driverIOS).press(xCenter, yCenter).waitAction(1000).release().perform();
				}
			}
		} else {
			status = "Objeto Xpath não localizado";
		}
	}

	public boolean locateObject(String objType, String locator) {
		this.ObjType = objType.trim();
		this.Locator = locator.trim();
		String page = "";
		boolean objFound = false;
		for (int i = 0; i < 30; i++) {
			if (driver != null) {
				page = driver.getPageSource();
			}
			if (driverIOS != null) {
				page = driverIOS.getPageSource();

				try {
					page = new String(page.getBytes("UTF-8"), "ISO-8859-1");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (page.contains(Locator) && !page.contains("Carregando...")) {
				
				break;
			} else {
				//new xmlParser(null, driverIOS);
				page = null;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		if (page != null) {
			String lines[] = page.split("\\r?\\n");
			for (int i = 0; i < lines.length; i++) {

				String line = lines[i].replace("?", "").replace("<" + objType, "").replace(">", "");
				if (line.contains("\"" + Locator + "\"")) {
					 name = line.substring(line.indexOf("name=\""),
					 line.indexOf(" label=")).replace("name=", "")
					 .replace("\"", "");
					 label = line.substring(line.indexOf("label=\""),
					 line.indexOf("value=")).replace("label=", "")
					 .replace("\"", "");
					// value = line.substring(line.indexOf("value=\""),
					// line.indexOf("dom=")).replace("value=", "")
					// .replace("\"", "");
					// path = line.substring(line.indexOf("path=\""),
					// line.indexOf("x=")).replace("path=", "")
					// .replace("\"", "");
					Double xDouble = Double.parseDouble(line.substring(line.indexOf("x=\""), line.indexOf("y="))
							.replace("x=", "").replace("\"", "").replace(" ", ""));
					x = xDouble.intValue();
					Double yDouble = Double.parseDouble(line.substring(line.indexOf("y=\""), line.indexOf("width="))
							.replace("y=", "").replace("\"", "").replace(" ", ""));
					y = yDouble.intValue();
					 Double widthDouble = Double
					 .parseDouble(line.substring(line.indexOf("width=\""),
					 line.indexOf("height="))
					 .replace("width=", "").replace("\"", "").replace(" ",
					 ""));
					 width = widthDouble.intValue();
					 Double heightDouble = Double
					 .parseDouble(line.substring(line.indexOf("height=\""),
					 line.length())
					 .replace("height=", "").replace("\"", "").replace(" ",
					 ""));
					 height = heightDouble.intValue();
					 xCenter = x + (width / 2);
					 yCenter = y + (height / 2);
					// i = lines.length;
					objFound = true;
					break;
				}
			}
		} else {
			objFound = false;
		}
		return objFound;
	}

	public void clickObject() {
		if (driver != null) {
			((AppiumDriver) driver).tap(1, x, y, 3000);
		}
		if (driverIOS != null) {
//			this.widthScreen = driverIOS.manage().window().getSize().width;
//			this.heigthScreen = driverIOS.manage().window().getSize().height;
//
//			while (yCenter > heigthScreen || (x == 0 && y == 0)) {
//				new TouchAction(driverIOS).press(widthScreen / 2, heigthScreen - 20).waitAction(1000)
//						.moveTo(widthScreen / 2, heigthScreen / 2).release().perform();
//				locateObject(ObjType, Locator);
//			}
		//	new TouchAction(driverIOS).press(x, y).perform();
			try{
			driverIOS.tap(1, xCenter-5, yCenter, 3000);
			}catch(Exception e){
				status = "Nao foi possivel clicar na tela";
			}
		}

	}

	public int getxCenter() {
		return xCenter;
	}

	public void setxCenter(int xCenter) {
		this.xCenter = xCenter;
	}

	public int getyCenter() {
		return yCenter;
	}

	public void setyCenter(int yCenter) {
		this.yCenter = yCenter;
	}

	public void showIOSObjectDetails() {
		System.out.println("Name = " + name);
		System.out.println("Label =" + label);
		System.out.println("Value=" + value);
		System.out.println("Path=" + path);
		System.out.println("Posicao X=" + x);
		System.out.println("Posicao Y=" + y);
		System.out.println("Posicao width=" + width);
		System.out.println("Posicao Heigth=" + height);
		System.out.println("Centro de X=" + xCenter);
		System.out.println("Centro de Y=" + yCenter);
		
	}

	public void setLocator(String locator) {
		this.locator = locator;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public int getY() {
		return y;
	}

	public int getX() {
		return x;
	}

	public String getPath() {
		return path;
	}

	public String getValue() {
		return value;
	}

	public String getLabel() {
		return label;
	}

	public String getName() {
		return name;
	}

	public boolean locateObjectbyIndex(String objType, String locator, int index) {
		String page = "";
		int indexCount = index;
		boolean objFound = false;
		if (driver != null) {
			page = driver.getPageSource();
		}
		if (driverIOS != null) {
			page = driverIOS.getPageSource();
		}

		String lines[] = page.split("\\r?\\n");
		for (int i = 0; i < lines.length; i++) {
			if (lines[i].contains(objType) && !lines[i].contains("</" + objType + ">")
					&& !lines[i].contains("name=\"\" label=\"\" value=\"\" dom=\"\"")) {
				String line = lines[i].replace("?", "").replace("<" + objType, "").replace(">", "");
				if (line.contains(locator)) {
					name = line.substring(line.indexOf("name=\""), line.indexOf(" label=")).replace("name=", "")
							.replace("\"", "");
					label = line.substring(line.indexOf("label=\""), line.indexOf("value=")).replace("label=", "")
							.replace("\"", "");
					value = line.substring(line.indexOf("value=\""), line.indexOf("dom=")).replace("value=", "")
							.replace("\"", "");
					path = line.substring(line.indexOf("path=\""), line.indexOf("x=")).replace("path=", "")
							.replace("\"", "");
					Double xDouble = Double.parseDouble(line.substring(line.indexOf("x=\""), line.indexOf("y="))
							.replace("x=", "").replace("\"", "").replace(" ", ""));
					x = xDouble.intValue();
					Double yDouble = Double.parseDouble(line.substring(line.indexOf("y=\""), line.indexOf("width="))
							.replace("y=", "").replace("\"", "").replace(" ", ""));
					y = yDouble.intValue();
					Double widthDouble = Double
							.parseDouble(line.substring(line.indexOf("width=\""), line.indexOf("height="))
									.replace("width=", "").replace("\"", "").replace(" ", ""));
					width = widthDouble.intValue();
					Double heightDouble = Double.parseDouble(line.substring(line.indexOf("height=\""), line.length())
							.replace("height=", "").replace("\"", "").replace(" ", ""));
					height = heightDouble.intValue();
					xCenter = x + (width / 2);
					yCenter = y + (height / 2);

					indexCount -= 1;
					if (indexCount == 0) {
						objFound = true;
						i = lines.length;
					}

				}
			}
		}
		return objFound;
	}

	public String getStatus() {
		return status;
	}
}

