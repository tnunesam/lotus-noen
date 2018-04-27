package br.com.inmetrics.neon.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.Map.Entry;

public class funcoes {
	public static String appiumInstallPath="C:\\Program Files (x86)\\Appium\\";
	public static List<String> ListDeviceSerialNumbers= null;
	
	public static void startNodeJSAppiumServer() throws InterruptedException {

		
		new Thread(new Runnable() {
			public void run() {
				//String appiumInstallPath = "C:\\Program Files (x86)\\Appium\\";
				String startServerCommand = getStartNodeJSAppiumServerCommand(appiumInstallPath, getNodeJSAppiumDefaultConfiguration());
				
				executeCommand(startServerCommand);
			}
		}).start();
		
		whaitAppiumServerStart("4723",60);
	}
	
	

	
	
	public static void startNodeJSAppiumServer(String setAppiumInstallPath) throws InterruptedException {

		appiumInstallPath=setAppiumInstallPath;
		new Thread(new Runnable() {
			public void run() {
				String startServerCommand = getStartNodeJSAppiumServerCommand(appiumInstallPath, getNodeJSAppiumDefaultConfiguration());
				
				executeCommand(startServerCommand);
			}
		}).start();
		
		whaitAppiumServerStart("4723",60);
	}
	

	
	
	private static Map<String, String> getNodeJSAppiumDefaultConfiguration() {
		Map<String, String> configuration = new HashMap<String, String>();
		configuration.put("local-timezone", "");
		configuration.put("session-override", "");
		configuration.put("no-reset", "");
		configuration.put("address", "127.0.0.1");
		configuration.put("port", "4723");
		configuration.put("platform-name", "Android");
		configuration.put("platform-version", "18");
		configuration.put("automation-name", "Appium");
		
		return configuration;
	}
	
	

	
	private static Map<String, String> getNodeJSAppiumDefaultConfiguration(String port) {
		Map<String, String> configuration = new HashMap<String, String>();
		configuration.put("local-timezone", "");
		configuration.put("session-override", "");
		configuration.put("no-reset", "");
		configuration.put("address", "127.0.0.1");
		configuration.put("port", port);
		configuration.put("platform-name", "Android");
		configuration.put("platform-version", "18");
		configuration.put("automation-name", "Appium");
		
		return configuration;
	}
	
	
	private static String getStartNodeJSAppiumServerCommand(String appiumInstallPath, Map<String, String> configuration) {
		
		StringBuilder sb = new StringBuilder("\"" + appiumInstallPath + "node.exe\" \"" 
												  + appiumInstallPath + "node_modules\\appium\\bin\\Appium.js\" ");
		
		if (configuration != null && !configuration.isEmpty()) {
			for (Entry<String, String> entry : configuration.entrySet()) {
				sb.append(" --")
					.append(entry.getKey())
					.append(" ")
					.append(entry.getValue());
			}
		}

		return sb.toString();
	}
	

	
	public static void closeAppium(RemoteWebDriver driver)
	{
	    try 
	    {
	    	if (driver != null && driver != null) {
	    			driver.quit();
	    		if (funcoes.deviceConected()) {
	    			funcoes.executeCommand(funcoes.getKillNodeJSAppiumServerCommand("4723"));
	    		}
	    		
	    	}
	    }
	    catch (Exception anException) 
	    {
	        anException.printStackTrace();
	    }
	}

	public static boolean deviceConected() {
		String deviceSerialNumber = getDeviceSerialNumber();
		return StringUtils.isNotBlank(deviceSerialNumber) 
				&& !StringUtils.equals(deviceSerialNumber.toLowerCase(), "unknown");
	}
	
	public static String getDeviceSerialNumber() {
		//getListDeviceSerialNumbers.
		return getListDeviceSerialNumbers().get(0);
	}
	
	

	
	public static List<String> getListDeviceSerialNumbers() {
		if (ListDeviceSerialNumbers!=null){
			return ListDeviceSerialNumbers;
		}
		
		Scanner scanner = null;
		try {
			scanner = new Scanner(Runtime.getRuntime().exec("cmd /c adb devices").getInputStream());
			scanner.useDelimiter("\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<String> deviceSerialNumbers =new ArrayList<String>();
		while (scanner != null && scanner.hasNext()){
			String Linha=scanner.next();
			if (Linha.indexOf("device\r")>-1){
				String arrParts[]=Linha.split("\\s+");
				
				deviceSerialNumbers.add(arrParts[0]);
			};
		}
		ListDeviceSerialNumbers=deviceSerialNumbers;
		return deviceSerialNumbers;
	}
	
	
	
	public static String getKillNodeJSAppiumServerCommand(String serverPortNumber) {
		StringBuilder command = new StringBuilder();
		command.append("cmd /c echo off")
				.append(" & ")
				.append("FOR /F \"usebackq tokens=5\" %p in (`netstat -nao ^| findstr /R /C:\""+ serverPortNumber +"\"`) ")
					.append("do (FOR /F \"usebackq\" %t in (`TASKLIST /FI \"PID eq %p\" ^| findstr /I node.exe`) ")
						.append("do taskkill /F /PID %p)");
		
		return command.toString();
	}
	
	
	private static String getStartedNodeJSAppiumServerCommand(String serverPortNumber) {
		StringBuilder command = new StringBuilder();
		command.append("cmd /c echo off")
				.append(" & ")
				.append("FOR /F \"usebackq tokens=5\" %p in (`netstat -nao ^| findstr /R /C:\""+ serverPortNumber +"\"`) ")
					.append("do (FOR /F \"usebackq\" %t in (`TASKLIST /FI \"PID eq %p\" ^| findstr /I node.exe`) ")
						.append("do @echo APPIUMOK)");
		
		return command.toString();
	}
	
	
	public static void whaitAppiumServerStart(String port, int timout_seconds) throws InterruptedException{
		System.out.println("Aguardando Appium server...");
		Thread.sleep(3000);	
		int tick=0;			
		while(funcoes.appiumStarted()==false && tick<=timout_seconds){
			Thread.sleep(1000);	
			tick++;
		}
		if (tick>=timout_seconds){
			System.out.println("whaitAppiumServerStart: timout excedido! " + timout_seconds + " segundos");
		}else{
			System.out.println("Appium server iniciado!");
		}
	}
	
	
	public static boolean appiumStarted(){
		try {
			return funcoes.executeCommandReturn(funcoes.getStartedNodeJSAppiumServerCommand("4723")).indexOf("APPIUMOK")>-1;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public static void executeCommand(String command) {

		
        String s = null;
        
        try {
            Process p = Runtime.getRuntime().exec(command);
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

            // read the output from the command
            System.out.println("Standard output of the command:\n");
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }

            // read any errors from the attempted command
            System.out.println("Standard error of the command (if any):\n");
            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }
            
        } catch (IOException e) {
            System.out.println("exception happened: ");
            e.printStackTrace();
        }
    }


	
	public static String executeCommandReturn(String command) throws IOException  {
        ProcessBuilder builder = new ProcessBuilder(
                "cmd.exe", "/c", command);
            builder.redirectErrorStream(true);
            Process p = builder.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            String output="";
            while (true) {
                line = r.readLine();
                if (line == null) { break; }
                if (output==""){
                	output=line;
                }else{
                	output=output + "\r\n" + line;
                }
            }
            return output;
    }


	
	public static String getLocalDeviceApp(String appPackage) throws IOException{	
		
		return getLocalDeviceApp(appPackage,"");
	}
	
	
	
	
	public static String getLocalDeviceApp(String appPackage, String device) throws IOException{
		String result="";
		if (device=="" || device==null){
			result= executeCommandReturn("adb -s " + getDeviceSerialNumber()  + " shell pm path " + appPackage);
		}else{
			result= executeCommandReturn("adb -s " + device + " shell pm path " + appPackage);
		}

		System.out.println(result);
		
		String caminho;
		caminho=result.substring(result.indexOf(":")+1);

		System.out.println("");
		File f = new File(System.getProperty("user.dir")+ "/" + appPackage + ".apk");
		if(!f.exists() && !f.isDirectory()) { 
			result= executeCommandReturn("adb pull " + caminho + " " + appPackage +".apk");
		}
					
		result =System.getProperty("user.dir").replace("\\", "/")+ "/" + appPackage + ".apk";
		System.out.println(result);


		
		return result;
	}
	
	
	
	
	
	
	public static packageObject putLocalApp(String appPackagePath) throws IOException{

		
		return putLocalApp(appPackagePath,"");


	}
	
	
	
	
	public static packageObject putLocalApp(String appPackagePath, String device) throws IOException{
		String result="";
		if (device=="" || device==null){
			result= executeCommandReturn("adb -s " + getDeviceSerialNumber()  + " install " + appPackagePath);
		}else{
			result= executeCommandReturn("adb -s " + device + " install " + appPackagePath);
		}
		
		
		System.out.println(result);

		

		String arrResult[] = executeCommandReturn("dir %ANDROID_HOME%\\build-tools\\ /b /A:d").split("\\r?\\n");

		System.out.println("Diretório da versão do android SDK: '" +arrResult[0] +"'" );
		
		
		result= executeCommandReturn("%ANDROID_HOME%\\build-tools\\" + arrResult[0] + "\\aapt.exe dump badging " + appPackagePath);
		System.out.println(result);
		

		
		String launchableActivity;
		String searchString="launchable-activity: name='";
		int startPoint=result.indexOf(searchString)+searchString.length();
		int endPoint=result.indexOf("'", startPoint);
		launchableActivity=result.substring(startPoint, endPoint);
		
		String packageName;
		searchString="package: name='";
		startPoint=result.indexOf(searchString)+searchString.length();
		endPoint=result.indexOf("'", startPoint);
		packageName=result.substring(startPoint, endPoint);
		
		
		packageObject obj=new packageObject();
		obj.launchableActivity=launchableActivity;
		obj.packageName=packageName;
		
		
		return obj;


	}
	
	
	
	public static String getLauchableActivity(String appPackage, String  device) throws IOException{
		String result="";
		if (device=="" || device==null){
			result= executeCommandReturn("adb  -s " + getDeviceSerialNumber()  + " shell pm path " + appPackage);
		}else{
			result= executeCommandReturn("adb -s " + device + " shell pm path " + appPackage);
		}
		
		System.out.println(result);
		
		String caminho;
		caminho=result.substring(result.indexOf("package:")+"package:".length()+1);
		
		
		if (device=="" || device==null){
			result=  executeCommandReturn("adb  -s " + getDeviceSerialNumber() + " pull " + caminho);
		}else{
			result= executeCommandReturn("adb -s " + device + " pull " + caminho);
		}
		
		System.out.println(result);
		
		String arrResult[] = executeCommandReturn("dir %ANDROID_HOME%\\build-tools\\ /b /A:d").split("\\r?\\n");

		System.out.println("Diretório da versão do android SDK: '" +arrResult[0] +"'" );
		
		String arrCaminho[]=caminho.split("/");
		String apkFile=arrCaminho[arrCaminho.length-1];
		
		result= executeCommandReturn("%ANDROID_HOME%\\build-tools\\" + arrResult[0] + "\\aapt.exe dump badging " + apkFile);
		System.out.println(result);
		

		
		String launchableActivity;
		String searchString="launchable-activity: name='";
		int startPoint=result.indexOf("launchable-activity: name='")+searchString.length();
		int endPoint=result.indexOf("'", startPoint);
		launchableActivity=result.substring(startPoint, endPoint);
		//System.out.println("LaunchableActivity:" + launchableActivity);		
				
				
		result= executeCommandReturn("del " + apkFile);	

		//System.out.println(result);
		
		return launchableActivity;
	}
	
	
	
	public static String getLauchableActivity(String appPackage) throws IOException{		
		
		return getLauchableActivity(appPackage,"");
	}
	
}


