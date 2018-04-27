package mobile;

import org.openqa.selenium.By;

import io.appium.java_client.android.AndroidDriver;

public class LibMobile {
	
	public void Espera(long tempoEspera) throws Exception
	{
		Thread.sleep(tempoEspera);
	}
	
	public Boolean ObjectExists(String object,AndroidDriver driver)
	{
		boolean flag = false;
		
		if (driver.findElement(By.id(object)).isDisplayed())
		{
			flag = true;
		}
		
		return flag;
	}
	
	public void ClickButton(String object,AndroidDriver driver)
	{
		if (ObjectExists(object, driver))
		{
			driver.findElementById(object);
		}
	}

}
