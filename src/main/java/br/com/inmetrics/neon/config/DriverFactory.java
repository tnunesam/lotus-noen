package br.com.inmetrics.neon.config;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JOptionPane;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.Executor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;

import br.com.inmetrics.neon.constants.Browser;
import br.com.inmetrics.neon.constants.Device;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class DriverFactory {

	private CapabilitiesConfig capabilities;

	public DriverFactory() {
		this.capabilities = new CapabilitiesConfig();
	}

	/**
	 * Retorna um WebDriver para uma automa��o em browser
	 * @param browser (qual browser a automa��o vai rodar, Ex. CHROME)
	 * @return WebDriver
	 */
	public WebDriver getBrowserInstance(Browser browser) {
		switch (browser) {
		case CHROME:
			return new ChromeDriver(capabilities.getConfigChrome());
		case INTERNET_EXPLORER:
			return new InternetExplorerDriver(capabilities.getConfigIE());
		default:
			return null;
		}
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

	public AndroidDriver getAndroidDeviceInstance() throws IOException {

		try {
			return new AndroidDriver(new URL(capabilities.getRemoteURL()), capabilities.getConfigAndroid());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		return null;
	}	


	/**
	 * Retorna um AndroidDriver para uma automa��o em browser dentro de um device Android
	 * @param browserName (exemplo "Chrome")
	 * @return AndroidDriver
	 * @throws IOException
	 */
	public AndroidDriver getAndroidBrowserInstance(String browserName) throws IOException {
		try {

			//return new AndroidDriver(new URL(capabilities.getRemoteURL()), capabilities.getConfigAndroid());
			return new AndroidDriver(new URL(capabilities.getRemoteURL()), capabilities.getConfigAndroid(browserName));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		return null;

	}

	/**
	 * Retorna um AndroidDriver para uma automa��o de um device Android 
	 * usando como default a aplica��o Dialer como aplica��o inicial
	 * passando um device espec�fico em que a automa��o ser� executada
	 * Para passar o device crie uma vari�vel do tipo device e configure sua propriedade Serial
	 * Use: import auxiliares.device;
	 * Ex:
	 * 	device dev= new device();
	 * 	dev.Serial="n�mero_serial"; //Obter no terminal digitando: adb devices
	 * 	//Use: .getConfigAndroid(dev);
	 * @param dev
	 * @return AndroidDriver
	 * @throws IOException
	 */
	public AndroidDriver getAndroidDeviceInstance(device dev) throws IOException {

		try {
			return new AndroidDriver(new URL(capabilities.getRemoteURL()), capabilities.getConfigAndroid(dev));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		return null;
	}	



	/**
	 * Retorna o AndroidDriver para automatizar uma aplica��o do Android
	 * passando-se qual start_androidPackage Ex. "com.google.android.dialer"
	 * passando-se qual start_appActivity ser� a activity inicial Ex. ".extensions.GoogleDialtactsActivity"
	 * @param start_androidPackage
	 * @param start_appActivity (start_appActivity ser� a activity inicial)
	 * @return AndroidDriver
	 * @throws IOException
	 */
	public AndroidDriver getAndroidDeviceInstance(String start_androidPackage,String start_appActivity) throws IOException {

		try {
			return new AndroidDriver(new URL(capabilities.getRemoteURL()), capabilities.getConfigAndroid(start_androidPackage, start_appActivity));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		return null;

	}

	/**
	 * Retorna o AndroidDriver para automatizar uma aplica��o do Android
	 * passando-se qual start_androidPackage Ex. "com.google.android.dialer"
	 * passando-se qual start_appActivity ser� a activity inicial Ex. ".extensions.GoogleDialtactsActivity"
	 * passando um device espec�fico em que a automa��o ser� executada
	 * Para passar o device crie uma vari�vel do tipo device e configure sua propriedade Serial
	 * Use: import auxiliares.device;
	 * Ex:
	 * 	device dev= new device();
	 * 	dev.Serial="n�mero_serial"; //Obter no terminal digitando: adb devices
	 * 	//Use: .getConfigAndroid(dev);
	 * @param start_androidPackage
	 * @param start_appActivity (start_appActivity ser� a activity inicial)
	 * @param dev
	 * @return AndroidDriver
	 * @throws IOException
	 */
	public AndroidDriver getAndroidDeviceInstance(String start_androidPackage,String start_appActivity, String dev) throws IOException {

		try {
			return new AndroidDriver(new URL(capabilities.getRemoteURL()), capabilities.getConfigAndroid(start_androidPackage, start_appActivity, dev));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		return null;

	}


	/**
	 * Retorna um AndroidDriver para automatizar uma aplica��o do Android
	 * passando-se o arquivo do APK
	 * @param appPackageFile
	 * @return AndroidDriver
	 * @throws IOException
	 */
	public AndroidDriver getAndroidDeviceInstance(File appPackageFile) throws IOException {

		try {
			return new AndroidDriver(new URL(capabilities.getRemoteURL()), capabilities.getConfigAndroid(appPackageFile));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		return null;

	}

	/**
	 * Retorna um AndroidDriver para automatizar uma aplica��o do Android
	 * passando-se o arquivo do APK
	 * passando um device espec�fico em que a automa��o ser� executada
	 * Para passar o device crie uma vari�vel do tipo device e configure sua propriedade Serial
	 * Use: import auxiliares.device;
	 * Ex:
	 * 	device dev= new device();
	 * 	dev.Serial="n�mero_serial"; //Obter no terminal digitando: adb devices
	 * 	//Use: .getConfigAndroid(dev);
	 * @param appPackageFile
	 * @param dev
	 * @return AndroidDriver
	 * @throws IOException
	 */
	public AndroidDriver getAndroidDeviceInstance(File appPackageFile, device dev) throws IOException {

		try {
			return new AndroidDriver(new URL(capabilities.getRemoteURL()), capabilities.getConfigAndroid(appPackageFile, dev));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		return null;

	}

	/**
	 * Retorna um AndroidDriver para automatizar uma aplica��o do Android j� previamente instalada
	 * passando-se qual � o package
	 * @param start_androidPackage
	 * @return AndroidDriver
	 * @throws IOException
	 */
	public AndroidDriver getAndroidDeviceInstance(String start_androidPackage) throws IOException {

		try {
			String LauchableActivity =funcoes.getLauchableActivity(start_androidPackage);
			return new AndroidDriver(new URL(capabilities.getRemoteURL()), capabilities.getConfigAndroid(start_androidPackage, LauchableActivity));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		return null;

	}

	/**
	 * Retorna um AndroidDriver para automatizar uma aplica��o do Android j� previamente instalada
	 * passando-se qual � o package
	 * passando um device espec�fico em que a automa��o ser� executada
	 * Para passar o device crie uma vari�vel do tipo device e configure sua propriedade Serial
	 * Use: import auxiliares.device;
	 * Ex:
	 * 	device dev= new device();
	 * 	dev.Serial="n�mero_serial"; //Obter no terminal digitando: adb devices
	 * 	//Use: .getConfigAndroid(dev);
	 * @param start_androidPackage
	 * @param dev
	 * @return AndroidDriver
	 * @throws IOException
	 */
	/*public AndroidDriver getAndroidDeviceInstance(String start_androidPackage, String dev) throws IOException {

		try {
			String LauchableActivity =funcoes.getLauchableActivity(start_androidPackage);
			return new AndroidDriver(new URL(capabilities.getRemoteURL()), capabilities.getConfigAndroid(start_androidPackage, LauchableActivity, dev));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		return null;

	}*/

	/**
	 * Retorna um IOSDriver para automatizar uma aplica��o de um device Apple
	 * @param platformVersion (vers�o do iOS, Ex. "8.1")
	 * @param deviceName (nome do device, Ex. "iPhone 6")
	 * @param appPath (caminho do APP  na m�quina, Ex. "C:\automacao\meuapp.app")
	 * @return IOSDriver
	 */
	public IOSDriver getIOSDeviceInstance(String platformVersion, String deviceName, String appPath) throws IOException {
		try {
			//"platformVersion="8.1"
			//"deviceName=iPhone 6"		
			//"appPath=C:/TestApp.app"

			return new IOSDriver(new URL(capabilities.getRemoteURL()), capabilities.getConfigIOS(platformVersion, deviceName, appPath));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * Retorna um AndroidDriver para automatizar uma aplica��o do Android no DeviceLabs
	 * passando-se o start_androidPackage
	 * @param start_androidPackage
	 * @return AndroidDriver
	 */
	public AndroidDriver getDeviceLabsDeviceInstance(String RemoteURL, String terminalId, String apiKey, String androidAPKFullPath) throws IOException {

		try {
			capabilities.setRemoteURL(RemoteURL);
			return new AndroidDriver(new URL(capabilities.getRemoteURL()),					
					capabilities.getConfigDeviceLabs(terminalId,apiKey,androidAPKFullPath));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		return null;

	}

	/**
	 * Retorna a AndroidDriver para automatizar uma aplica��o no TestDroid
	 * @param remote_apiKey (chave API da sua conta no site)
	 * @param remoteURL (URL da sua conta bno site)
	 * @param testDroidUser
	 * @param testDroidPassword
	 * @param DeviceName (nome do device que vai executar a automa��o Ex. "Samsung Galaxy Nexus SPH-L700 4.3",
	 *  veja os devices gratu�tos em https://cloud.testdroid.com/#public/devices)
	 * @param start_androidPackage (caminho do APK  na m�quina, Ex. "C:\automacao\meuapp.apk")
	 * @return AndroidDriver
	 * @throws IOException
	 */
	public AndroidDriver getTestDroidDeviceInstance(String remote_apiKey, String remoteURL, String testDroidUser, String testDroidPassword, String deviceName, String start_androidPackage) throws IOException {

		try {
			//String deviceName ="Samsung Galaxy Nexus GT-I9250 4.2.2";
			//String deviceName ="Samsung Galaxy Nexus SPH-L700 4.3";
			//String appApth = funcoes.getLocalDeviceApp("net.natura.semprepresente");
			//String appApth = funcoes.getLocalDeviceApp(start_androidPackage);

			//String fileUUID = BaseTest.uploadFile(start_androidPackage, remoteURL, remote_apiKey);

			System.out.println("Creating Appium session, this may take couple minutes..");


			return new AndroidDriver(new URL(remoteURL + "/wd/hub"), capabilities.getConfigAndroidTestDroid(remote_apiKey, deviceName));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}


		return null;
	}

}