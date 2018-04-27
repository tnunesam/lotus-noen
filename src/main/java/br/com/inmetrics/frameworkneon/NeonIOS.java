package br.com.inmetrics.frameworkneon;

import static org.junit.Assert.assertTrue;
import java.io.IOException;
import java.net.URL;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import br.com.inmetrics.neon.reports.Screenshot;
import br.com.inmetrics.neon.utilities.DataTable;
import br.com.inmetrics.neon.utilities.GenericDataTable;
import br.com.inmetrics.neon.utilities.Screen;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import br.com.inmetrics.neon.lib.ios.Button;
import br.com.inmetrics.neon.lib.ios.EditText;
import br.com.inmetrics.neon.lib.ios.FindObject;
import br.com.inmetrics.neon.lib.ios.Generics;
import br.com.inmetrics.neon.lib.ios.PickerWheel;
import br.com.inmetrics.neon.lib.ios.ScrollView;
import br.com.inmetrics.neon.lib.ios.StaticText;

public class NeonIOS {

	static IOSDriver driver;
	protected static NeonIOS instance;
	private static boolean ativaScreenshot = false;
	protected Button botao;
	protected StaticText statictext;
	protected EditText edittext;
	protected Screen screen;
	protected Generics generics;
	protected FindObject findobject;
	protected PickerWheel pickerwheel;
	protected static Screenshot screenshot;
	protected ScrollView scrollview;
	protected static DataTable dt;


	public static void startAppium() throws ExecuteException, IOException{
		CommandLine command = new CommandLine("/usr/local/bin/node");
		command.addArgument("/usr/local/bin/appium");
		command.addArgument("--address");
		command.addArgument("0.0.0.0");
		command.addArgument("--port");
		command.addArgument("4723");
		command.addArgument("--log");
		command.addArgument("argument", false); 
		DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
		DefaultExecutor executor = new DefaultExecutor();
		executor.setExitValue(1);
		executor.execute(command, resultHandler);
	}
	
	public void killNode() throws Exception, IOException{
		CommandLine command = new CommandLine("/usr/local/bin/node");
		command.addArgument("/usr/local/bin/appium");
		command.addArgument("--address");
		command.addArgument("0.0.0.0");
		command.addArgument("--port");
		command.addArgument("4723");
		command.addArgument("--log");
		command.addArgument("argument", false); 
		DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
		DefaultExecutor executor = new DefaultExecutor();
		executor.setExitValue(1);
		executor.execute(command, resultHandler);
	}

	public static void initializeDriversIOS(String appPath,String OsVersion, String deviceName,String udid) throws IOException, Exception{
		instance = new NeonIOS();
		
		startAppium();

		if(Neon.instance != null)
			throw new Exception("Essa classe n�o pode ser novamente inicializada!");

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "iOS");
		capabilities.setCapability("platformVersion", OsVersion);
		capabilities.setCapability("deviceName", deviceName);
//		capabilities.setCapability("bundleId","com.facebook.IntegrationAppss");
		capabilities.setCapability("automationName", "XCUITest");
		capabilities.setCapability("noReset", "True");
		capabilities.setCapability("app", appPath);
		capabilities.setCapability("udid", udid);
//		capabilities.setCapability("realDeviceLogger", "usr/local/lib/node_modules/deviceconsole/deviceconsole");
		//capabilities.setCapability("xcodeconfigFile", "/usr/local/lib/node_modules/appium/node_modules/appium-xcuitest-driver/WebDriverAgent/Config.xcconfig");
		capabilities.setCapability("newCommandTimeout",1000000);
		
		
		driver = new IOSDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
		instance.botao = new Button(driver);
		instance.edittext = new EditText(driver);
		instance.statictext = new StaticText(driver);
		instance.screenshot = new Screenshot((RemoteWebDriver)driver, deviceName, ativaScreenshot);
		instance.findobject = new FindObject(driver);
		instance.screen =  new Screen(driver);
		instance.generics = new Generics(driver);
		instance.pickerwheel = new PickerWheel((AppiumDriver) driver);
		instance.scrollview = new ScrollView((IOSDriver) driver);
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

	public static NeonIOS getInstance(){
		return instance;
	}

	public static void activeScreenshot(){
		ativaScreenshot = true;
	}
	
	public StaticText getStaticText(){
		return instance.statictext;
	}

	public Screenshot getScreenshot(){
		return instance.screenshot;
	}

	public Button getBotao(){
		return instance.botao;
	}


	public EditText getEditText(){
		return instance.edittext;
	}
	
	public FindObject getFindObject(){
		return instance.findobject;
	}
	
	public Screen getScreen(){
		return instance.screen;
	}
	
	public Generics getGenerics(){
		return instance.generics;
	}
	
	public PickerWheel getPickerWheel(){
		return instance.pickerwheel;
	}

	public ScrollView getscrollview(){
		return instance.scrollview;
	}
	public void pass(String message){
		assertTrue(message, true);
	}

	public void closeAppium() {
		Neon.instance = null;
		driver.quit();
	}
}
