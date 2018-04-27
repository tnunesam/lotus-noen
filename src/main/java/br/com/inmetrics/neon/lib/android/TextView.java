package br.com.inmetrics.neon.lib.android;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import android.R.id;
import br.com.inmetrics.neon.config.Elementos;
import br.com.inmetrics.neon.constants.Constants;
import io.appium.java_client.android.AndroidDriver;

public class TextView {
	private static RemoteWebDriver driver = null;

	public TextView(RemoteWebDriver Driver) {
		this.driver = Driver;
	}

	public List<Elementos> findAll() throws Exception {
		System.out.println("Capturando todos os elementos TextView" );
		List<Elementos> lista = new ArrayList<Elementos>();
		WebDriverWait wait = new WebDriverWait(driver, Constants.TIMEOUT_WAIT);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("android.widget.TextView")));
		List<WebElement> elementos = driver.findElements(By.className("android.widget.TextView"));
		if (elementos.size()> 0){
			for (WebElement element : elementos) {
				Elementos e = new Elementos();{
					e.setElemento("ID = " + element.getAttribute("resourceId"));
					e.setValue("Text = " + element.getText());
				}
				lista.add(e);
				System.out.println("ID = " + element.getAttribute("resourceId"));
				System.out.println("Text = " + element.getText());
			}
		}
		List<WebElement> elementosChecked = driver.findElements(By.className("android.widget.CheckedTextView"));
		if (elementosChecked.size()> 0){
			for (WebElement element : elementosChecked) {
				Elementos e = new Elementos();{
					e.setElemento("ID = " + element.getAttribute("resourceId"));
					e.setValue("Text = " + element.getText());
				}
				lista.add(e);
				System.out.println("ID = " + element.getAttribute("resourceId"));
				System.out.println("Text = " + element.getText());
			}
		}

		if (lista.size() > 0) {
			return lista;
		}
		else{

			throw new Exception( "Nenhum objeto TextView foi  localizado na tela");
		}
	}

	public String findAllStrings() throws Exception {
		StringBuilder sb = new StringBuilder();
		System.out.println("Capturando todos os elementos TextView" );
		List<Elementos> lista = new ArrayList<Elementos>();
		WebDriverWait wait = new WebDriverWait(driver, Constants.TIMEOUT_WAIT);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("android.widget.TextView")));
		List<WebElement> elementos = driver.findElements(By.className("android.widget.TextView"));
		if (elementos.size()> 0){
			for (WebElement element : elementos) {
				sb.append("ID = " + element.getAttribute("resourceId")+"\n");
				sb.append("Text = " + element.getText() + "\n");
				System.out.println("ID = " + element.getAttribute("resourceId"));
				System.out.println("Text = " + element.getText());
			}
		}
		List<WebElement> elementosChecked = driver.findElements(By.className("android.widget.CheckedTextView"));
		if (elementosChecked.size()> 0){
			for (WebElement element : elementosChecked) {
				sb.append("ID = " + element.getAttribute("resourceId")+"\n");
				sb.append("Text = " + element.getText() + "\n");
				System.out.println("ID = " + element.getAttribute("resourceId"));
				System.out.println("Text = " + element.getText());
			}
		}

		if (elementos.size() > 0 || elementosChecked.size()>0 ) {
			return sb.toString();
		}
		else{

			throw new Exception( "Nenhum objeto TextView foi  localizado na tela");
		}
	}

	public void clickByTextX(String text) throws Exception {

		System.out.println("Clicando no TextView com o texto " + text);
		List<WebElement> elementos = null;
		WebDriverWait wait = new WebDriverWait(driver, Constants.TIMEOUT_WAIT);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("android.widget.TextView")));
		elementos = driver.findElements(By.className("android.widget.TextView"));
		if (elementos.size()>0){
			if (driver.findElementByXPath("//android.widget.TextView[@text='"+text+"']") != null){
				driver.findElementByXPath("//android.widget.TextView[@text='"+text+"']").click();
			}
			else
			{
				throw new Exception("Nao existe o TextView com o texto " + text);
			}


		}
		else
		{
			throw new Exception( "Nenhum objeto TextView foi localizado na tela");
		}

		if (elementos.size()==0) {
			if (elementos.size()>0){
				if (driver.findElementByXPath(text) != null){
					driver.findElementByXPath(text).click();
				}
				else
				{
					throw new Exception("Nao existe o TextView com o xpath " + text);
				}
			}
			else
			{
				throw new Exception( "Nenhum objeto TextView foi localizado na tela");
			}
		}

	}

	public void clickByText(String text) throws Exception {
		boolean exist= false;
		System.out.println("Clicando no TextView com o texto " + text);
		List<WebElement> elementos =  null;
		WebDriverWait wait = new WebDriverWait(driver, Constants.TIMEOUT_WAIT);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("android.widget.TextView")));
		elementos =  driver.findElements(By.className("android.widget.TextView"));
		if (elementos.size()>0){
			for (WebElement webElement : elementos) {
				if (webElement.getText().toLowerCase().contains(text.toLowerCase())) {
					webElement.click();
					exist = true;
					break;
				}
			}
		}

		if (elementos.size()==0) {
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("android.widget.CheckedTextView")));
			elementos = driver.findElements(By.className("android.widget.CheckedTextView"));
			for (WebElement webElement : elementos) {
				if (webElement.getText().toLowerCase().contains(text.toLowerCase())) {
					webElement.click();
					exist = true;
					break;
				}
			}
		}

		if (!exist)
		{
			throw new Exception ("Elemento TextView " + text + " n�o encontrado.");
		}

		if (elementos.size()==0) {
			throw new Exception( "N�o h� elementos TextView na tela.");
		}

	}

	public void clickByIdElement(String id) throws Exception {
		boolean exist = false;
		System.out.println("Clicando no TextView com o id " + id);
		List<WebElement> elementos = null;
		WebDriverWait wait = new WebDriverWait(driver, Constants.TIMEOUT_WAIT);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("android.widget.TextView")));
		elementos = driver.findElements(By.className("android.widget.TextView"));
		if (elementos.size()>0){
			for (WebElement webElement : elementos) {
				if (webElement.getAttribute("resourceId").toString().toLowerCase().contains(id.toLowerCase())) {
					webElement.click();
					exist=true;
					break;
				}
			}
		}
		if (elementos.size()==0) {
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("android.widget.CheckedTextView")));
			elementos = driver.findElements(By.className("android.widget.CheckedTextView"));
			for (WebElement element : elementos) {
				if (element.getAttribute("resourceId").toLowerCase().contains(id.toLowerCase())) {
					element.click();
					exist=true;
					break;
				}
			}
		}
		if (!exist){
			throw new Exception("Elemento TextView n�o encontrado.");
		}

		if (elementos.size()==0){
			throw new Exception("Elemento TextView n�o encontrado.");
		}
	}

	public static void clickById(String id) throws Exception {
		boolean exist = false;
		WebDriverWait wait = new WebDriverWait(driver, Constants.TIMEOUT_WAIT);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("android.widget.TextView")));
		List<WebElement> elementos = driver.findElements(By.className("android.widget.TextView"));
		if (elementos.size()>0){
			if (Generics.exists("id",id,"android.widget.TextView")){
				System.out.println("Clicando no Bot�o com o id: " + id);
				driver.findElement(By.id(id)).click();
				exist = true;
			}

		}
		if (!exist){
			throw new Exception("Nao existe o bot�o com o id " + id);
		}	
		if (elementos.isEmpty()){
			throw new Exception( "Nenhum objeto TextView foi localizado na tela");
		}
	}

	public void clickByXpath(String xpath) throws Exception {
		System.out.println("Clicando no TextView com o id " + xpath);
		List<WebElement> elementos = null;
		WebDriverWait wait = new WebDriverWait(driver, Constants.TIMEOUT_WAIT);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("android.widget.TextView")));
		elementos = driver.findElements(By.className("android.widget.TextView"));
		if (elementos.size()>0){
			if (driver.findElementByXPath(xpath) != null){
				driver.findElementByXPath(xpath).click();
			}
			else
			{
				throw new Exception("Nao existe o TextView com o xpath " + xpath);
			}
		}
		else
		{
			throw new Exception( "Nenhum objeto TextView foi localizado na tela");
		}

		if (elementos.size()==0) {
			if (elementos.size()>0){
				if (driver.findElementByXPath(xpath) != null){
					driver.findElementByXPath(xpath).click();
				}
				else
				{
					throw new Exception("Nao existe o TextView com o xpath " + xpath);
				}
			}
			else
			{
				throw new Exception( "Nenhum objeto TextView foi localizado na tela");
			}
		}

	}

	public static void clickByIndex(int index) throws Exception {
		boolean exist = false;
		System.out.println("Clicando no Text de indice " + index);
		WebDriverWait wait = new WebDriverWait(driver, Constants.TIMEOUT_WAIT);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("android.widget.TextView")));
		List<WebElement> elementos = null;
		elementos = driver.findElements(By.className("android.widget.TextView"));
		if (elementos.size()!=0 && elementos != null){
			if (elementos.get(index)!=null){
//				elementos.get(index).click();
				((AndroidDriver)driver).tap(1, elementos.get(index), 3000);
				exist = true;
			}
		}

		if (elementos.size()!=0 && elementos != null){
			elementos = driver.findElements(By.className("android.widget.CheckedTextView"));
			if (elementos.size()>0){
				if (elementos.get(index)!=null){
					elementos.get(index).click();
					exist=true;
				}
			}
		}

		if (!exist){
			throw new Exception( "Objeto n�o foi localizado na tela.");
		}
		if (elementos.size()==0){
			throw new Exception( "Nenhum objeto TextView foi localizado na tela.");
		}
	}
	
	public static String getAttributeByIndex(int index, String atributo) throws Exception {
		boolean exist = false;
		System.out.println("Capturando atributo do Texto de indice " + index);
		WebDriverWait wait = new WebDriverWait(driver, Constants.TIMEOUT_WAIT);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("android.widget.TextView")));
		List<WebElement> elementos = null;
		elementos = driver.findElements(By.className("android.widget.TextView"));
		if (elementos.size()!=0 && elementos != null){
			return elementos.get(index).getAttribute(atributo).toString();
		}

		if (!exist){
			throw new Exception( "Objeto n�o foi localizado na tela.");
		}
		if (elementos.size()==0){
			throw new Exception( "Nenhum objeto TextView foi localizado na tela.");
		}
		
		return null;
	}
	
	public String getAttribute(String tipo, String objectName, String atributo) throws Exception{
		boolean exist = false;
		System.out.println("Obter propriedade bot�o " + objectName);
		WebDriverWait wait = new WebDriverWait(driver, Constants.TIMEOUT_WAIT);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("android.widget.TextView")));
		List<WebElement> elementos = driver.findElements(By.className("android.widget.TextView"));
		if (elementos.size()>0){
			if(tipo.toLowerCase() == "text"){
				for (WebElement webElement : elementos) {
					if (webElement.getText().contains(objectName)){
						exist=true;
						return webElement.getAttribute(atributo).toString();
					}
				}
				if (!exist){
					throw new Exception("Nao existe o bot�o com o texto " + objectName);
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
					throw new Exception("Nao existe o bot�o com o xpath " + objectName);
				}
			}
			if(tipo.toLowerCase() == "id"){
				for (WebElement webElement : elementos) {
					if (webElement.getAttribute("resourceId").equals(objectName)){
						exist=true;
						return webElement.getAttribute(atributo).toString();
					}
				}
				if (!exist){
					throw new Exception("Nao existe o bot�o com o id " + objectName);
				}
			}

		}else
		{
			throw new Exception( "Nenhum objeto TextView foi localizado na tela.");
		}
		return null;
	}

	public String getAttributeById( String objectName, String atributo){
		return driver.findElement(By.id(objectName)).getAttribute(atributo).toString();

	}

}
