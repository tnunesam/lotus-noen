package br.com.inmetrics.frameworkneon.views.ios;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import br.com.inmetrics.frameworkneon.NeonIOS;
import io.appium.java_client.MobileBy;

public class UtilView extends NeonIOS {
	public void setSenha(String senha) throws Exception{
	
		List<WebElement> objetos = getGenerics().mapeiaTela("UIAButton");
		ArrayList<WebElement> lista = new ArrayList<WebElement>() ;
		
		String[] numeros = senha.split("");

		for (String valorNumero : numeros) {
			for (WebElement webElement : objetos) {
				if (webElement.getText().equals(valorNumero)){
					lista.add(webElement);
					break;
				}
			}
			
		}
		//for (int i = 0; i < senha.length(); i++) {
		//	String numero = senha.substring(i, i+1);	

//			getTextView().clickByTextX(numero);
//			Thread.sleep(1000);
//			getDriver().findElement(By.xpath("//*[@text='" + numero + "']")).click();
			getBotao().clickByName(lista);
		}
	
}
