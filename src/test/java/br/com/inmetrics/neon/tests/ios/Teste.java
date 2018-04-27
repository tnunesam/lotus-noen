package br.com.inmetrics.neon.tests.ios;

import static br.com.inmetrics.frameworkneon.NeonIOS.initializeDriversIOS;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import br.com.inmetrics.frameworkneon.NeonIOS;
import br.com.inmetrics.frameworkneon.views.ios.CartoesView;
import br.com.inmetrics.frameworkneon.views.ios.LoginView;
import br.com.inmetrics.frameworkneon.views.ios.SaldoView;
//import br.com.inmetrics.frameworkneon.views.ios.TransferenciasView;
import br.com.inmetrics.neon.constants.Constants;
import br.com.inmetrics.neon.utilities.GenericDataTable;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;


public class Teste extends NeonIOS {
	
	LoginView loginView = new LoginView();
	CartoesView cartoesView = new CartoesView();
	SaldoView saldoView = new SaldoView();
//	TransferenciasView transfView = new TransferenciasView();
	
	GenericDataTable dt = new GenericDataTable(Constants.PATH_DATATABLE);
	
	@Before
	public void setUp() {
		try {
			//loginView.activeScreenshot();
			initializeDriversIOS(Constants.APP_PATH, Constants.OS_VERSION, Constants.DEVICE_NAME, Constants.UDID);
			// oginView.initializeDataTable(Constants.PATH_DATATABLE);
			
			List<WebElement> listaBotoes = getBotao().findAll();
			
			
			//List<WebElement> listaEditText = getGenerics().mapeiaTela("UIATextField");
			//List<MobileElement> elements = (List<MobileElement>) getDriver().findElements(MobileBy.xpath("//UIAWindow[1]/*"));
//			List<WebElement> listaSeciureEditText = getBotao().mapeiaTela("UIASecureTextField");
			for (WebElement webElement : listaBotoes) {
				MobileElement elemento = (MobileElement)webElement;

				
				System.out.println(webElement.getText());
				webElement.getAttribute("identifier");
				//List<MobileElement> elements = driver.findElement(MobileBy.xpath("//*"));

			}
			
			
			Thread.sleep(1000);
		}
		catch (Exception e){
			fail("Driver n�o iniciado");
			fail("Exception");
			loginView.closeAppium();
			e.printStackTrace();
		}
	}
	
	@Test
	public void ct_038_efetuarBloqueioTemporarioCartaoVirtual () {
		cartoesView.getDt().setFirstRow();
		try {	
			for (int i = 0; i < cartoesView.getDt().getRowCount(); i++) {
				if (dt.getStringOf("ID_CT").equals("CT_038")) 	break;
				else											dt.setNextRow();
			}
			cartoesView.getScreenshot().setNome("ct_038_efetuarBloqueioTemporarioCartaoVirtual");
			loginView.logar(dt.getStringOf("EMAIL"), dt.getStringOf("SENHA_APP"));
			cartoesView.bloquearCartaoVirtualTemporario();
			cartoesView.validarCartaoVirtualBloqueadoTemporario(true);
			loginView.logout();
		} catch (Exception e) {
			cartoesView.closeAppium();
			fail("Falha ao bloquear cartão.");
			e.printStackTrace();
		}
	}
}
