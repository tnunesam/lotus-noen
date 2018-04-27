package br.com.inmetrics.neon.config;

import org.openqa.selenium.remote.RemoteWebDriver;
//import br.com.devicelab.rest.BlinkRest;


public class DriversManager {

	public static RemoteWebDriver driver;

	public DriversManager(RemoteWebDriver Driver)
	{
		this.driver = Driver;
	}

	public static RemoteWebDriver setUpLocal(String pacote, String appActivity, String device) throws InterruptedException
	{
		RemoteWebDriver driver =  null;

		try
		{
			driver = new DriverFactory().getAndroidDeviceInstance(pacote,appActivity,device);
			System.out.println("Session created!");
		}
		catch(Exception e)
		{
			System.out.println(e);
			System.out.println("Teste falhou - Setup");	
		}

		return driver;
	}

	public static RemoteWebDriver setUpTestDroid(int option) throws InterruptedException
	{
		RemoteWebDriver driver =  null;

		try
		{
			driver = new DriverFactory().getTestDroidDeviceInstance("oblDSaeLBtXLnzSTgY8dXD2IHSx3Jor6", "http://appium.testdroid.com", "thinunes@inmetrics.com.br", "Inm@2016", "LG Google Nexus 5 6.0 -EU", "C:\\Appium\\app-debug.apk");
			System.out.println("Session created!");
				
		}
		catch(Exception e)
		{
			System.out.println(e);
			System.out.println("Teste falhou - Setup");	
		}

		return driver;
	}
	
//	public static RemoteWebDriver setUpDeviceLabs(String device) throws InterruptedException
//	{
//		RemoteWebDriver driver =  null;
//
//		try
//		{
//			// Device Labs
//			driver = new DriverFactory().getDeviceLabsDeviceInstance("http://192.168.0.100/wd/hub", device, "2a672853-7505-460d-8fce-e4f693bbe111",BlinkRest.upload("http://192.168.0.100", "2a672853-7505-460d-8fce-e4f693bbe111", "C:\\Appium\\app-debug.apk"));
//			System.out.println("Session created!");
//		}
//		catch(Exception e)
//		{
//			System.out.println(e);
//			System.out.println("Teste falhou - Setup");
//		}
//
//		return driver;
//	}
	
	
}
