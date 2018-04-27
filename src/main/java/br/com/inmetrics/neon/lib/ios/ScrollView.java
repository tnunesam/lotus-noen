package br.com.inmetrics.neon.lib.ios;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import br.com.inmetrics.neon.utilities.FindObject;


public class ScrollView {
	private IOSDriver driver;
	private FindObject fObject;

	@SuppressWarnings("unchecked")
	public ScrollView(IOSDriver Driver) {
		this.driver = Driver;
		fObject = new FindObject(driver);
	}
	
	public void scrollTo(String IdToFind) {
		System.out.println("Efetuando o Scroll no Objeto Scroll View");

		if(fObject.locateObject("UIAScrollView", IdToFind)){
			int x = fObject.getX();
			int y =fObject.getY();;
			int width = fObject.getWidth();
			int height = fObject.getHeight();
			
			//new xmlParser(null, driverios);
			y= y +(height/2);
			System.out.println(x +", y"+y+", width"+ width + ", height " + height);
			new TouchAction(driver).press(x-30, y).waitAction(1000).moveTo(50, y).release().perform();	
			
		}

	}

}

