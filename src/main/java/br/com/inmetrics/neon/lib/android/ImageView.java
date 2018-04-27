package br.com.inmetrics.neon.lib.android;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.inmetrics.neon.config.Elementos;
import br.com.inmetrics.neon.constants.Constants;

public class ImageView {

	private static RemoteWebDriver driver = null;
	
	public ImageView(RemoteWebDriver Driver) {
		this.driver = Driver;
	}
	
	public List<Elementos> findAll() throws Exception {
		System.out.println("Capturando todos os elementos ImageView da tela." );
		List<Elementos> lista = new ArrayList<Elementos>();
		WebDriverWait wait = new WebDriverWait(driver, Constants.TIMEOUT_WAIT);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("android.widget.ImageView")));
		List<WebElement> elementos = driver.findElements(By.className("android.widget.ImageView"));
		if (elementos.size()>0){
			for (WebElement webElement : elementos) {
				Elementos e = new Elementos();{
					e.setElemento("ID = " + webElement.getAttribute("resourceId"));
					e.setValue("Text = " + webElement.getText());
					lista.add(e);
					System.out.println("ID = " + webElement.getAttribute("resourceId"));
					System.out.println("Text = " + webElement.getText());					
				}
			}
			return lista;
		}
		else{

			throw new Exception( "Nenhum objeto imageView foi localizado na tela");
		}
	}

	public List<Elementos> findAll(String attribute) throws Exception {
		System.out.println("Capturando todos os elementos ImageView da tela." );
		List<Elementos> lista = new ArrayList<Elementos>();
		WebDriverWait wait = new WebDriverWait(driver, Constants.TIMEOUT_WAIT);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("android.widget.ImageView")));
		List<WebElement> elementos = driver.findElements(By.className("android.widget.ImageView"));
		for (int i = 0; i < elementos.size(); i++) {
			Elementos e = new Elementos();{
				e.setElemento("ID = " + elementos.get(i).getAttribute(attribute));
				e.setValue("Text = " + elementos.get(i).getText());
			}
			lista.add(e);
			System.out.println("ID = " + elementos.get(i).getAttribute(attribute));
			System.out.println("Text = " + elementos.get(i).getText());
		}

		if (elementos.size() != 0) {
			return lista;
		}
		else{

			throw new Exception( "Nenhum objeto Image View foi  localizado na tela");
		}
	}


	
	public static void clickByIdElement(String id) throws Exception {
		boolean exist = false;
		WebDriverWait wait = new WebDriverWait(driver, Constants.TIMEOUT_WAIT);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("android.widget.ImageView")));
		List<WebElement> elementos = driver.findElements(By.className("android.widget.ImageView"));
		if (elementos.size()>0){
			for (WebElement webElement : elementos) {
				if (webElement.getAttribute("resource-id").toLowerCase().contains(id.toLowerCase())) {
					System.out.println("Clicando no ImageView com o id: " + id);
					webElement.click();
					exist = true;
					break;
				}
			}
		}
		if (!exist){
			throw new Exception("Nao existe o ImageView com o id " + id);
		}
		
		if (elementos.isEmpty()){
			throw new Exception( "Nenhum objeto ImageView foi localizado na tela");
		}
	}

	public static void clickById(String id) throws Exception {
		boolean exist = false;
		WebDriverWait wait = new WebDriverWait(driver, Constants.TIMEOUT_WAIT);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("android.widget.ImageView")));
		List<WebElement> elementos = driver.findElements(By.className("android.widget.ImageView"));
		if (elementos.size()>0){
			if (Generics.exists("id",id,"android.widget.ImageView")){
				System.out.println("Clicando no ImageView com o id: " + id);
				driver.findElement(By.id(id)).click();
				exist = true;
			}
		}
		if (!exist){
			throw new Exception("Nao existe o ImageView com o id " + id);
		}	
		if (elementos.isEmpty()){
			throw new Exception( "Nenhum objeto ImageView foi localizado na tela");
		}
	}
	
	public static void clickByIndex(int index) throws Exception {
		boolean exist = false;
		System.out.println("Clicando no ImageView de indice " + index);
		WebDriverWait wait = new WebDriverWait(driver, Constants.TIMEOUT_WAIT);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("android.widget.ImageView")));
		List<WebElement> elementos = driver.findElements(By.className("android.widget.ImageView"));
		if (elementos.size()>0){
			if (elementos.get(index)!=null){
				System.out.println("Clicando no ImageView com o índice: " + index);
				elementos.get(index).click();
			}
		}
		if (!exist) {
			throw new Exception("Nenhum objeto ImageView foi localizado usando o index " + index);
		}
		if(elementos.isEmpty())
		{
			throw new Exception( "Nenhum objeto ImageView foi localizado na tela.");
		}
	}
	
	public static void clickByXpath(String xpath) throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, Constants.TIMEOUT_WAIT);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("android.widget.ImageView")));
		List<WebElement> elementos = driver.findElements(By.className("android.widget.ImageView"));
		if (elementos.size()>0){
			if ( driver.findElement(By.xpath(xpath)) != null){
				System.out.println("Clicando no ImageView com o id: " + xpath);
				driver.findElement(By.xpath(xpath)).click();
			}
			else{		
				throw new Exception("Nao existe o ImageView com o xpath " + xpath);
			}
		}
		else
		{
			throw new Exception( "Nenhum objeto ImageView foi localizado na tela");
		}
	}
	
	public String getAttribute(String tipo, String objectName, String atributo) throws Exception{
		boolean exist = false;
		System.out.println("Obter propriedade ImageView " + objectName);
		WebDriverWait wait = new WebDriverWait(driver, Constants.TIMEOUT_WAIT);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("android.widget.ImageView")));
		List<WebElement> elementos = driver.findElements(By.className("android.widget.ImageView"));
		if (elementos.size()>0){
			if(tipo.toLowerCase() == "text"){
				for (WebElement webElement : elementos) {
					if (webElement.getText().contains(objectName)){
						exist=true;
						return webElement.getAttribute(atributo).toString();
					}
				}
				if (!exist){
					throw new Exception("Nao existe o ImageView com o texto " + objectName);
				}
			}
			if(tipo.toLowerCase() == "xpath"){
				List<WebElement> webElementsXpath = driver.findElements(By.xpath(objectName));
				if (webElementsXpath.size()>0){
					exist = true;
					return webElementsXpath.get(0).getAttribute(atributo).toString();
				}
				else
				{
					throw new Exception("Nao existe o ImageView com o xpath " + objectName);
				}
			}
			if(tipo.toLowerCase() == "id"){
				for (WebElement webElement : elementos) {
					if (webElement.getAttribute("resource-id").contains(objectName)){
						exist=true;
						return webElement.getAttribute(atributo).toString();
					}
				}
				if (!exist){
					throw new Exception("Nao existe o ImageView com o id " + objectName);
				}
			}

		}else
		{
			throw new Exception( "Nenhum objeto ImageView foi localizado na tela.");
		}
		return null;
	}

	
}
