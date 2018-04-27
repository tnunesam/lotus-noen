package br.com.inmetrics.frameworkneon;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import br.com.inmetrics.neon.lib.android.*;
import br.com.inmetrics.neon.reports.Screenshot;
import br.com.inmetrics.neon.utilities.*;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import br.com.inmetrics.neon.utilities.Screen;
import br.com.inmetrics.neon.config.DriversManager;
import static org.junit.Assert.assertTrue;
import java.io.IOException;
import java.net.URL;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Neon {

	static RemoteWebDriver driver;
	protected static Neon instance;
	private static boolean ativaScreenshot = false;
	protected Button botao;
	protected TextView textview;
	protected EditText edittext;
	protected Screen screen;
	protected ImageView imageview;
	protected ImageButton imagebutton;
	protected RadioButton radiobutton;
	protected Generics generics;
	protected static Screenshot screenshot;
	protected static DataTable dt;


	public static void initializeDrivers(String device, String appPackage, String appActivity) throws Exception{

		Neon instance = new Neon();

		if(Neon.instance != null)
			throw new Exception("Essa classe n�o pode ser novamente inicializada!");

		driver = DriversManager.setUpLocal(appPackage, appActivity, device);
		instance.botao = new Button(driver);
		instance.textview = new TextView(driver);
		//instance.edittext = new EditText(driver);
		instance.screen = new Screen(driver);
		instance.imageview = new ImageView(driver);
		instance.imagebutton = new ImageButton(driver);
		instance.radiobutton = new RadioButton(driver);
		instance.generics = new Generics(driver);
		instance.screenshot = new Screenshot(driver, device, ativaScreenshot);
				
		if(Neon.instance == null)
			Neon.instance = instance;

	}
	
	public static void initializeDriver2(String plataforma, String device, String appPackage, String appActivity) throws Exception{

		Neon instance = new Neon();

		if(Neon.instance != null)
			throw new Exception("Essa classe n�o pode ser novamente inicializada!");
		
		DesiredCapabilities cap = new DesiredCapabilities();
		
		cap.setCapability("platformName", "Android");
		cap.setCapability("deviceName", device);
		cap.setCapability("androidPackage", appPackage);
		cap.setCapability("appActivity", appActivity);
		cap.setCapability("newCommandTimeout", "6000");
		cap.setCapability("serial", device);
		
		cap.setCapability("chromedriverExecutable", "Desktop/chromedriver");

		driver = new RemoteWebDriver( new URL("http://0.0.0.0:4725/wd/hub"),cap);
		instance.botao = new Button(driver);
		instance.textview = new TextView(driver);
		//instance.edittext = new EditText(driver);
		instance.screen = new Screen(driver);
		instance.imageview = new ImageView(driver);
		instance.imagebutton = new ImageButton(driver);
		instance.radiobutton = new RadioButton(driver);
		instance.generics = new Generics(driver);
		instance.screenshot = new Screenshot(driver, device, ativaScreenshot);

		if(Neon.instance == null)
			Neon.instance = instance;

	}
	
	public void startAppium() throws ExecuteException, IOException{
		/*		DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
				Executor executor = new DefaultExecutor();
				executor.setExitValue(1);
				CommandLine command = new CommandLine("cmd");
				command.addArgument("/c");
				command.addArgument("C:/Program Files (x86)/Appium/node.exe");
				command.addArgument("C:/Program Files (x86)/Appium/node_modules/appium/bin/Appium.js");*/
				CommandLine command = new CommandLine("/bin/sh");
				command.addArgument("/Applications/Appium.app/Contents/Resources/node/bin/node");
				command.addArgument("/Applications/Appium.app/Contents/Resources/node_modules/appium/bin/appium.js");
				command.addArgument("--address");
				command.addArgument("127.0.0.1");
				command.addArgument("--port");
				command.addArgument("4723");
				command.addArgument("--no-reset");
				command.addArgument("--log");
				
				command.addArgument("argument", false);
				DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
				DefaultExecutor executor = new DefaultExecutor();
				executor.setExitValue(1);
				executor.execute(command, resultHandler);
					
			}
	
	public static void initializeDrivers2(String device, String appPackage, String appActivity) throws Exception{

		Neon instance = new Neon();

		if(Neon.instance != null)
			throw new Exception("Essa classe n�o pode ser novamente inicializada!");
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.BROWSER_NAME, ""); //Name of mobile web browser to automate. Should be an empty string if automating an app instead.
		capabilities.setCapability("platformName", "Android");
		//capabilities.setCapability(CapabilityType.VERSION, "5.1.1");	
		capabilities.setCapability("deviceName", device);
		capabilities.setCapability("androidPackage", appPackage);
		capabilities.setCapability("appActivity", appActivity);	
		capabilities.setCapability("newCommandTimeout", "6000");

		driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		instance.botao = new Button(driver);
		instance.textview = new TextView(driver);
		//instance.edittext = new EditText(driver);
		instance.screen = new Screen(driver);
		instance.imageview = new ImageView(driver);
		instance.imagebutton = new ImageButton(driver);
		instance.radiobutton = new RadioButton(driver);
		instance.generics = new Generics(driver);
		instance.screenshot = new Screenshot(driver, device, ativaScreenshot);
		
		if(Neon.instance == null)
			Neon.instance = instance;

	}

	public static RemoteWebDriver getDriver(){
		if (driver!=null){
			return driver;
		}
		return null;
	}
	
	public void initializeDataTable(String caminho){
		dt = new GenericDataTable(caminho); 
		dt.setFirstRow();
	}
	
	public DataTable getDt(){
		return dt;
	}
	
	public static Neon getInstance(){
		return instance;
	}
		
	public static void activeScreenshot(){
		ativaScreenshot = true;
	}
	
	public br.com.inmetrics.neon.lib.android.RadioButton getRadioButton(){
		return instance.radiobutton;
	}

	public Screenshot getScreenshot(){
		return instance.screenshot;
	}

	public Button getBotao(){
		return instance.botao;
	}

	public TextView getTextView(){
		return instance.textview;
	}

	public EditText getEditText(){
		return instance.edittext;
	}
	
	public Screen getScreen(){
		return instance.screen;
	}

	public ImageView getImageView(){
		return instance.imageview;
	}
	
	public ImageButton getImageButton(){
		return instance.imagebutton;
	}
	

	public Generics getGenerics(){
		return instance.generics;
	}

	public void pass(String message){
		assertTrue(message, true);
	}

	public void closeAppium() {
		Neon.instance = null;
		driver.quit();
	}
}
