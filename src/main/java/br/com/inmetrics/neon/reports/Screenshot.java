package br.com.inmetrics.neon.reports;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;

import br.com.inmetrics.neon.constants.Constants;
import br.com.inmetrics.neon.constants.Devices;

public class Screenshot {
	private static RemoteWebDriver driver = null;
	private static String device = "";
	private static String nomeTeste= "";
	int number = 0;
	private static String fileName2 = "";
	private static boolean active = false;


	public Screenshot (RemoteWebDriver Driver,String Device, boolean Active)
	{
		Screenshot.driver = Driver;
		Screenshot.device = Device;
		Screenshot.active = Active;

	}

	public void setNome(String nomeTeste){
		Screenshot.nomeTeste = nomeTeste;
		Screenshot.fileName2 = "Execucao_"+ new SimpleDateFormat("HH.mm.ss").format(Calendar.getInstance().getTime());
	}
	
	private String identificaDevice(String serial){
		if (Devices.LG_K4_NEON == serial)
			return "LG K4 NEON";
		if (Devices.MOTO_E1_INMETRICS == serial)
			return "MOTO E1 INMETRICS";
		if (Devices.MOTO_G3_NEON==serial)
			return "MOTO G3 NEON";
		if (Devices.MOTO_G_INMETRICS==serial)
			return "MOTO G INMETRICS";
		
		
		return "";
		
	}

	public void CaptureScreenshot () throws Exception
	{
		Thread.sleep(1000);
		String nomeDevice = identificaDevice(device);
		String fileName = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
		String path = Constants.PATH_SCREENSHOT +"\\"+fileName +"\\"+ nomeTeste + "\\"+ nomeDevice + "\\" + fileName2;
		CreateDirectory(path);	
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(path + "\\" + number + ".png"));
		number++;
	}

	public void CreateDirectory(String directoryName) throws Exception
	{
		try
		{
			File directory = new File(directoryName);

			if (!directory.exists()) {
				System.out.println("creating directory: " + directoryName);


				try{
					directory.mkdirs();

				} 
				catch(Exception e){
					e.printStackTrace();
					throw e;
				}        
			}
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}

	}



}
