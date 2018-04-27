package br.com.inmetrics.neon.lib.ios;


import java.io.UnsupportedEncodingException;
import java.text.Normalizer;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSElement;


public class PickerWheel {

	private AppiumDriver driver;
	private int startX;
	private int startY;
	private int endY;

	public PickerWheel(AppiumDriver Driver) {
		this.driver = Driver;
		org.openqa.selenium.Dimension dimensions = driver.manage().window().getSize();
		int screenWidth = dimensions.getWidth();
		int screenHeight = dimensions.getHeight();
		startX = screenWidth / 2;
		startY = screenHeight-10;
		endY = screenHeight-50;
		
	}

	public void selectItem(String Value, String TextToClick) throws Exception {
		System.out.println("Clicando no PickerWheel com o texto " + TextToClick.trim());
		List<WebElement> elements = null;
		try {
			//new xmlParser(null, driverios);
			elements = driver.findElements(By.xpath("//UIAPickerWheel[contains(@value,'" + Value + "')]"));
		} catch (Exception e) {
			throw new Exception("PickerWheel não foi localizado com o valor " + Value);
		}
		while (true) {
			if (elements.size() > 0) {
				String CurrentText = elements.get(0).getText();
//				try {
//				try {
//				//	CurrentText = new String(CurrentText.getBytes("UTF-8"), "ISO-8859-1");
//				} catch (UnsupportedEncodingException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//					//TextToClick = new String(TextToClick.getBytes("UTF-8"), "ISO-8859-1");
//				} catch (UnsupportedEncodingException e) {
//					e.printStackTrace();
//				}
				CurrentText = Normalizer.normalize(CurrentText, Normalizer.Form.NFD);
				CurrentText = CurrentText.replaceAll("[^\\p{ASCII}]", "");
				
				TextToClick = Normalizer.normalize(TextToClick, Normalizer.Form.NFD);
				TextToClick = TextToClick.replaceAll("[^\\p{ASCII}]", "");
				
				
				System.out.println(CurrentText.toLowerCase().trim());
				System.out.println(TextToClick.toLowerCase().trim());
				
				if(CurrentText.toLowerCase().trim().equals(TextToClick.toLowerCase().trim())){
					break;
				}else{
					TouchAction action = new TouchAction(driver).press(startX, startY).waitAction(1000).moveTo(startX,endY).release();
					action.perform();		
				}
			} else {
				throw new Exception("PickerWheel não foi localizado com o valor " + Value);
				
			}
		}
	}
}
