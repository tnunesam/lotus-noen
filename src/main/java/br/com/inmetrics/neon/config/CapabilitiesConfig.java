package br.com.inmetrics.neon.config;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.remote.MobileCapabilityType;

public class CapabilitiesConfig {

	private DesiredCapabilities desiredCapabilities;
	private PropertiesConfig properties;
	private String myRemoteURL = "http://127.0.0.1:4723/wd/hub"; 
	
	/**
	 * Retorna a URL remota do Selenium server 
	 * @return String
	 * @default http://127.0.0.1:4723/wd/hub
	 */
	public String getRemoteURL() {
		return myRemoteURL;
	}
	
	/**
	 * Seta qual é a URL remota do Selenium server configurada
	 * @default http://127.0.0.1:4723/wd/hub
	 */
	public void setRemoteURL(String remoteURL) {
		myRemoteURL= remoteURL;
	}

	
	/**
	 * Retorna a DesiredCapabilities default para chamar o browser Google Chrome
	 * @return DesiredCapabilities
	 */
	public DesiredCapabilities getConfigChrome() {
		System.setProperty("webdriver.chrome.driver", properties.getProperty("webdriver.chrome.driver"));

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--use-fake-ui-for-media-stream=true");
		options.addArguments("--enable-media-stream");
		options.addArguments("--enable-peer-connection");
		options.addArguments("--disable-web-security chrome://version/");

		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("download.prompt_for_download", true);
		options.setExperimentalOption("prefs", chromePrefs);
		options.addArguments("--test-type");

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);

		return capabilities;
	}

	
	/**
	 * Retorna a DesiredCapabilities default para chamar o browser Internet Explorer
	 * @return DesiredCapabilities
	 */
	public DesiredCapabilities getConfigIE() {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		System.setProperty("webdriver.ie.driver", properties.getProperty("webdriver.ie.driver"));
		
		capabilities = DesiredCapabilities.internetExplorer();
		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
		return capabilities;
	}
	
	/**
	 * Retorna a DesiredCapabilities default para automatizar Android
	 * @return DesiredCapabilities
	 * A activity do dialer será a activity inicial
	 */
	public DesiredCapabilities getConfigAndroid() {		
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.BROWSER_NAME, ""); //Name of mobile web browser to automate. Should be an empty string if automating an app instead.
		capabilities.setCapability("platformName", "Android");
		//capabilities.setCapability(CapabilityType.VERSION, "5.1.1");	
		capabilities.setCapability("deviceName", funcoes.getDeviceSerialNumber());
		capabilities.setCapability("androidPackage", "com.google.android.dialer");
		capabilities.setCapability("appActivity", ".extensions.GoogleDialtactsActivity");	
		capabilities.setCapability("newCommandTimeout", "6000");
		capabilities.setCapability(MobileCapabilityType.TAKES_SCREENSHOT, "true");
		
			
		return capabilities;
	}
	
	
	
	/**	 
	 * Retorna a DesiredCapabilities default para automatizar Android passando-se qual device executar a automação
	 * Para passar o device crie uma variável do tipo device e configure sua propriedade Serial
	 * Use: import auxiliares.device;
	 * Ex:
	 * 	device dev= new device();
	 * 	dev.Serial="número_serial"; //Obter no terminal digitando: adb devices
	 * 	//Use: .getConfigAndroid(dev);
	 * @param dev
	 * @return DesiredCapabilities
	 * A activity do dialer será a activity inicial
	 */
	public DesiredCapabilities getConfigAndroid(device dev) {		
			
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.BROWSER_NAME, ""); //Name of mobile web browser to automate. Should be an empty string if automating an app instead.
		capabilities.setCapability("platformName", "Android");
		//capabilities.setCapability(CapabilityType.VERSION, "5.1.1");	
		capabilities.setCapability("deviceName", dev.Serial);
		capabilities.setCapability("androidPackage", "com.google.android.dialer");
		capabilities.setCapability("appActivity", ".extensions.GoogleDialtactsActivity");	
		capabilities.setCapability("newCommandTimeout", "6000");
			
		return capabilities;
	}
	
	
	/**	 
	 * Retorna a DesiredCapabilities para automatizar um browser do Android
	 * passando-se qual browserName 
	 * Para passar o device crie uma variável do tipo device e configure sua propriedade Serial
	 * Use: import auxiliares.device;
	 * Ex:
	 * 	device dev= new device();
	 * 	dev.Serial="número_serial"; //Obter no terminal digitando: adb devices
	 * 	//Use: .getConfigAndroid(dev);
	 * @param browserName (exemplo "Chrome")
	 * @param dev
	 * @return DesiredCapabilities
	 * A activity do browser mobile será a activity inicial
	 */
	public DesiredCapabilities getConfigAndroid(String browserName) {		
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		//capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "4.4");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, funcoes.getDeviceSerialNumber());
		capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
		capabilities.setCapability("newCommandTimeout", "60000");
		
		return capabilities;
	}
	
	
	/**	 
	 * Retorna a DesiredCapabilities para automatizar um browser do Android
	 * passando-se qual browserName 
	 * passando-se qual device executar a automação
	 * Para passar o device crie uma variável do tipo device e configure sua propriedade Serial
	 * Use: import auxiliares.device;
	 * Ex:
	 * 	device dev= new device();
	 * 	dev.Serial="número_serial"; //Obter no terminal digitando: adb devices
	 * 	//Use: .getConfigAndroid(dev);
	 * @param browserName (exemplo "Chrome")
	 * @param dev
	 * @return DesiredCapabilities
	 * A activity do browser mobile será a activity inicial
	 */
	public DesiredCapabilities getConfigAndroid(String browserName, device dev) {		
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		//capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "4.4");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, dev.Serial);
		capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, browserName);
		capabilities.setCapability("newCommandTimeout", "60000");
		
		return capabilities;
	}
	
	
	/**	 
	 * Retorna a DesiredCapabilities para automatizar uma aplicação do Android
	 * passando-se qual start_androidPackage Ex. "com.google.android.dialer"
	 * passando-se qual start_appActivity será a activity inicial Ex. ".extensions.GoogleDialtactsActivity"
	 * @param start_androidPackage
	 * @param start_appActivity
	 * @return DesiredCapabilities
	 * A activity start_appActivity será a activity inicial
	 */
	public DesiredCapabilities getConfigAndroid(String start_androidPackage, String start_appActivity) {		
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		//capabilities.setCapability(CapabilityType.BROWSER_NAME, ""); //Name of mobile web browser to automate. Should be an empty string if automating an app instead.
		capabilities.setCapability("platformName", "Android");
		//capabilities.setCapability(CapabilityType.VERSION, "5.1.1");	
		capabilities.setCapability("deviceName", funcoes.getDeviceSerialNumber());
		capabilities.setCapability("androidPackage", start_androidPackage);
		capabilities.setCapability("appActivity", start_appActivity);	
		capabilities.setCapability("newCommandTimeout", "6000");
		//0012949852	
		return capabilities;
	}
	
	

	/**	 
	 * Retorna a DesiredCapabilities para automatizar uma aplicação do Android
	 * passando-se qual start_androidPackage Ex. "com.google.android.dialer"
	 * passando-se qual start_appActivity será a activity inicial Ex. ".extensions.GoogleDialtactsActivity"
	 * passando-se qual device executar a automação
	 * Para passar o device crie uma variável do tipo device e configure sua propriedade Serial
	 * Use: import auxiliares.device;
	 * Ex:
	 * 	device dev= new device();
	 * 	dev.Serial="número_serial"; //Obter no terminal digitando: adb devices
	 * @param start_androidPackage
	 * @param start_appActivity
	 * @param dev
	 * @return DesiredCapabilities
	 * A activity start_appActivity será a activity inicial
	 */
	public DesiredCapabilities getConfigAndroid(String start_androidPackage, String start_appActivity, String dev) {		
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.BROWSER_NAME, ""); //Name of mobile web browser to automate. Should be an empty string if automating an app instead.
		capabilities.setCapability("platformName", "Android");
		//capabilities.setCapability(CapabilityType.VERSION, "5.1.1");	
		capabilities.setCapability("deviceName", dev);
		capabilities.setCapability("androidPackage", start_androidPackage);
		capabilities.setCapability("appActivity", start_appActivity);	
		capabilities.setCapability("newCommandTimeout", "6000");
			
		return capabilities;
	}
	
	
	
/**
 * Retorna a DesiredCapabilities para automatizar uma aplicação do Android
 * passando-se o arquivo do APK
 * @param appPackageFile
 * @return DesiredCapabilities
 * @throws IOException
 */
	public DesiredCapabilities getConfigAndroid(File appPackageFile) throws IOException {		
		packageObject packObj =funcoes.putLocalApp(appPackageFile.getAbsolutePath());
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.BROWSER_NAME, ""); //Name of mobile web browser to automate. Should be an empty string if automating an app instead.
		capabilities.setCapability("platformName", "Android");
		//capabilities.setCapability(CapabilityType.VERSION, "5.1.1");	
		capabilities.setCapability("deviceName", funcoes.getDeviceSerialNumber());
		capabilities.setCapability("androidPackage", packObj.packageName);
		capabilities.setCapability("appActivity", packObj.launchableActivity);	
		capabilities.setCapability("newCommandTimeout", "6000");
			
		return capabilities;
	}
	
	/**
	 * Retorna a DesiredCapabilities para automatizar uma aplicação do Android
	 * passando-se o arquivo APK
	 * passando-se qual device executar a automação
	 * Para passar o device crie uma variável do tipo device e configure sua propriedade Serial
	 * Use: import auxiliares.device;
	 * Ex:
	 * 	device dev= new device();
	 * 	dev.Serial="número_serial"; //Obter no terminal digitando: adb devices
	 * @param appPackageFile
	 * @param dev
	 * @return DesiredCapabilities
	 * @throws IOException
	 */
	public DesiredCapabilities getConfigAndroid(File appPackageFile, device dev) throws IOException {		
		packageObject packObj =funcoes.putLocalApp(appPackageFile.getAbsolutePath());
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.BROWSER_NAME, ""); //Name of mobile web browser to automate. Should be an empty string if automating an app instead.
		capabilities.setCapability("platformName", "Android");
		//capabilities.setCapability(CapabilityType.VERSION, "5.1.1");	
		capabilities.setCapability("deviceName", dev.Serial );
		capabilities.setCapability("androidPackage", packObj.packageName);
		capabilities.setCapability("appActivity", packObj.launchableActivity);	
		capabilities.setCapability("newCommandTimeout", "6000");
			
		return capabilities;
	}
	
	
	/**
	 * Retorna a DesiredCapabilities para automatizar uma aplicação de um device Apple
	 * @param platformVersion (versão do iOS, Ex. "8.1")
	 * @param deviceName (nome do device, Ex. "iPhone 6")
	 * @param appPath (caminho do APP  na máquina, Ex. "C:\automacao\meuapp.app")
	 * @return DesiredCapabilities
	 */
	public DesiredCapabilities getConfigIOS(String platformVersion, String deviceName, String appPath) {
		desiredCapabilities = new DesiredCapabilities();
		File app = new File(appPath);
				//("C:/", "TestApp.app");
		desiredCapabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		desiredCapabilities.setCapability("platformVersion", platformVersion);//"8.1");
		desiredCapabilities.setCapability("deviceName", deviceName);//"iPhone 6");
		desiredCapabilities.setCapability("app",  app.getAbsolutePath());
		desiredCapabilities.setCapability("newCommandTimeout", "60000");
		
		return desiredCapabilities;
	}
	
	
	
	
	/**
	 * Retorna a DesiredCapabilities para automatizar uma aplicação do Android no DeviceLabs
	 * passando-se qual o id do terminal
	 * passando-se qual a chave da api
	 * passando-se qual o pacote a ser iniciado 
	 * @param terminalId
	 * @param apiKey
	 * @param start_androidPackage
	 * @return DesiredCapabilities
	 */
	public DesiredCapabilities getConfigDeviceLabs(String terminalId, String apiKey, String start_androidPackage) {		
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		//capabilities.setCapability(CapabilityType.BROWSER_NAME, ""); //Name of mobile web browser to automate. Should be an empty string if automating an app instead.
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("deviceName", "Android");
		capabilities.setCapability("noReset", true);
		capabilities.setCapability("fullReset", false);
		capabilities.setCapability("terminalId",terminalId);
		capabilities.setCapability("apiKey", apiKey);
		capabilities.setCapability("app", start_androidPackage);
		capabilities.setCapability("noReport", false);
		
		// pega os status de cpu e log
//		capabilities.setCapability("stepLog", true);

		return capabilities;
	}
	

	/**
	 * Retorna a DesiredCapabilities para automatizar uma aplicação no TestDroid
	 * @param fileUUID (após fazer o upload do app no site do testdroid, o arquivo recebe um fileUUD, veja a documentação no site)
	 * @param testdroid_apiKey (chave API da sua conta no site)
	 * @param DeviceName (nome do device que vai executar a automação Ex. "Samsung Galaxy Nexus SPH-L700 4.3",
	 *  veja os devices gratuítos em https://cloud.testdroid.com/#public/devices)
	 * @return DesiredCapabilities
	 * @throws IOException
	 */
	public DesiredCapabilities getConfigAndroidTestDroid(String testdroid_apiKey, String DeviceName) throws IOException {		
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("testdroid_target", "Android");
		capabilities.setCapability("deviceName", DeviceName);
        
		capabilities.setCapability("testdroid_apiKey", testdroid_apiKey);
        
		capabilities.setCapability("testdroid_project", "Teste 2");
		capabilities.setCapability("testdroid_testrun", "Test Run Teste");
		//capabilities.setCapability("testdroid_description", "Test description");
        
        // See available devices at: https://cloud.testdroid.com/#public/devices
		capabilities.setCapability("testdroid_device", DeviceName);//"Samsung Galaxy Nexus SPH-L700 4.3"); // Freemium device
		
	    
		capabilities.setCapability("testdroid_app", "latest");
		
		System.out.println("Capabilities:" + capabilities.toString());
			
		return capabilities;
	}
	
	
	/**
	 * Retorna a DesiredCapabilities para automatizar uma aplicação no TestDroid
	 * @param fileUUID (após fazer o upload do app no site do testdroid, o arquivo recebe um fileUUD, veja a documentação no site)
	 * @param testdroid_apiKey (chave API da sua conta no site)
	 * @param DeviceName (nome do device que vai executar a automação Ex. "Samsung Galaxy Nexus SPH-L700 4.3",
	 *  veja os devices gratuítos em https://cloud.testdroid.com/#public/devices)
	 * @param project (Nome do seu projeto Ex. "Projecto 1")
	 * @param run  (Nome da execução Ex. "Teste de login 1")
	 * @param description   (Descrição Ex. "Teste do login com user e senha corretos")
	 * @return DesiredCapabilities
	 * @throws IOException
	 */
	public DesiredCapabilities getConfigAndroidTestDroid(String fileUUID, String testdroid_apiKey, String DeviceName, String project, String run, String description) throws IOException {		
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("testdroid_target", "Android");
		capabilities.setCapability("deviceName", "Android Device");
        
		capabilities.setCapability("testdroid_apiKey", testdroid_apiKey);
        
		capabilities.setCapability("testdroid_project", project);
		capabilities.setCapability("testdroid_testrun",run);
		capabilities.setCapability("testdroid_description", description);
        
        // See available devices at: https://cloud.testdroid.com/#public/devices
		capabilities.setCapability("testdroid_device", DeviceName);//"Samsung Galaxy Nexus SPH-L700 4.3"); // Freemium device
		
	    
		capabilities.setCapability("testdroid_app", fileUUID);
		
		System.out.println("Capabilities:" + capabilities.toString());
			
		return capabilities;
	}
	

	
//	private boolean isProxyAtivado() {
//		return Boolean.valueOf(properties.getProperty("usarproxy"));
//	}
//
//	private Proxy getProxy() {
//		if (isProxyAtivado()) {
//			Proxy proxy = new Proxy();
//			String PROXY = properties.getProperty("PROXY");
//			proxy.setHttpProxy(PROXY);
//			proxy.setSocksUsername(properties.getProperty("proxy.setSocksUsername"));
//			proxy.setSocksPassword(properties.getProperty("proxy.setSocksPassword"));
//			return proxy;
//		}
//		return null;
//	}	

}


