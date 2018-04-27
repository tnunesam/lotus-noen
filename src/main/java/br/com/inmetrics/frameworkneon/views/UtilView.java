package br.com.inmetrics.frameworkneon.views;

import org.openqa.selenium.By;

import br.com.inmetrics.frameworkneon.Neon;

public class UtilView extends Neon{
	
	public void setSenha(String senha) throws Exception{
		for (int i = 0; i < senha.length(); i++) {
			String numero = senha.substring(i, i+1);	

//			getTextView().clickByTextX(numero);
//			Thread.sleep(1000);
//			getDriver().findElement(By.xpath("//*[@text='" + numero + "']")).click();
			getTextView().clickByText(numero);

		}
	}
}
