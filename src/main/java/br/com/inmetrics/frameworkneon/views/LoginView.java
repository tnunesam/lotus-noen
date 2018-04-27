package br.com.inmetrics.frameworkneon.views;

import br.com.inmetrics.frameworkneon.Neon;
import br.com.inmetrics.neon.constants.Constants;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;

import static org.junit.Assert.*;

import org.openqa.selenium.By;

public class LoginView extends Neon {
	
	UtilView utilView = new UtilView();
	HomeView homeView = new HomeView();
	TransferenciasView transfView = new TransferenciasView();

	//
	//	Métodos CT's
	//
	public void sincronizarContatosFacebook(String usuario, String senha) throws Exception {
		Thread.sleep(2000);
		homeView.clicarOpcaoMenu("meu neon");
		
		getScreenshot().CaptureScreenshot();
		clicaSwitchFacebook();
		
		loginFacebook(usuario, senha);
	}
	
	public void teste() throws Exception{
//		getTextView().clickByTextX("ENTRAR");
		Thread.sleep(3000);
		getTextView().clickByTextX("2");
		getTextView().clickByTextX("5");
		getTextView().clickByTextX("2");
		getTextView().clickByTextX("5");
		getTextView().clickByTextX("2");
		getTextView().clickByTextX("5");
		Thread.sleep(3000);
	}
	
	public void logar(String usuario, String senha) throws Exception {
		if (getGenerics().exists("id", "br.com.neon:id/toolbar_title",Constants.ANDROID_TEXT_VIEW)) 
			getGenerics().pressKeyCode(AndroidKeyCode.BACK);
		
		getScreenshot().CaptureScreenshot();
		apagarCampoEmail();
		
		getScreenshot().CaptureScreenshot();
		setEmail(usuario);	
		getScreen().hideKeyboard();	
		
		getScreenshot().CaptureScreenshot();
		clicarBotaoEntrar();
		
		Thread.sleep(2000);
		getScreenshot().CaptureScreenshot();
		utilView.setSenha(senha);
		
		Thread.sleep(5000);
		if (getGenerics().exists("text", "alerta de email!", Constants.ANDROID_TEXT_VIEW)) {
			getScreenshot().CaptureScreenshot();
			voltarTelaLogin();
			logar(usuario, senha);
		}
		
		while (!getGenerics().exists("id", "br.com.neon:id/widgetMoneyValue",Constants.ANDROID_TEXT_VIEW)){
			Thread.sleep(1000);
		}
	} 
	
	public void logarEmailInexistente(String usuario, String senha) throws Exception {
		if (getGenerics().exists("id", "br.com.neon:id/toolbar_title",Constants.ANDROID_TEXT_VIEW)) 
			getGenerics().pressKeyCode(AndroidKeyCode.BACK);
		
		getScreenshot().CaptureScreenshot();
		apagarCampoEmail();
		
		getScreenshot().CaptureScreenshot();
		setEmail(usuario);	
		getScreen().hideKeyboard();	
		
		getScreenshot().CaptureScreenshot();
		clicarBotaoEntrar();
		
		Thread.sleep(2000);
		getScreenshot().CaptureScreenshot();
		utilView.setSenha(senha);
	} 
	
	public void logarSenhaInvalida(String usuario, String senha, boolean tentarNovamente) throws Exception {
		if (getGenerics().exists("id", "br.com.neon:id/toolbar_title",Constants.ANDROID_TEXT_VIEW)) 
			getGenerics().pressKeyCode(AndroidKeyCode.BACK);
		
		getScreenshot().CaptureScreenshot();
		apagarCampoEmail();
		
		getScreenshot().CaptureScreenshot();
		setEmail(usuario);	
		getScreen().hideKeyboard();	
		
		getScreenshot().CaptureScreenshot();
		clicarBotaoEntrar();
		
		Thread.sleep(2000);
		getScreenshot().CaptureScreenshot();
		utilView.setSenha(senha);
		
		if (tentarNovamente) {
			while (!getGenerics().exists("text", "TENTAR MAIS UMA VEZ",Constants.ANDROID_TEXT_VIEW))
				Thread.sleep(1000);
			getTextView().clickByText("TENTAR MAIS UMA VEZ");
		}
	} 
	
	public void logout() throws Exception {
		Thread.sleep(5000);
		homeView.clicarOpcaoMenu("meu neon");
		
		getScreenshot().CaptureScreenshot();
		clicarSairConfirmar();
	}
	
	//TODO fluxo nova conta
	public void novaConta() {

	}
	
	//
	//	Métodos a��es da tela
	//
	public void voltarTelaLogin() throws Exception {
		getTextView().clickByText("OK");
		
		getScreenshot().CaptureScreenshot();
		getGenerics().pressKeyCode(AndroidKeyCode.BACK);
	}
	
	public void clicaSwitchFacebook() throws Exception {
		
		getScreen().scrolltotext("facebook");
		
		getScreenshot().CaptureScreenshot();
		getDriver().findElement(By.xpath("//*[@resource-id='br.com.neon:id/fb_switch']")).click();
	}
	
	public void loginFacebook(String login, String senha) throws Exception {
		
		getScreenshot().CaptureScreenshot();
		setUsuarioFacebook(login);
		
		getScreenshot().CaptureScreenshot();
		setSenhaFacebook(senha);
		
		getGenerics().pressKeyCode(AndroidKeyCode.BACK);
		
		getScreenshot().CaptureScreenshot();
		clicaBotaoEntrarFacebook();

		getScreenshot().CaptureScreenshot();
		continuaLoginFacebook();
		
		Thread.sleep(2000);
		getScreenshot().CaptureScreenshot();
		getGenerics().pressKeyCode(AndroidKeyCode.BACK);
	}
	
	public void clicaBotaoEntrarFacebook() throws Exception {
		getBotao().clickByXpath("//android.widget.Button[@index='1']");
	}
	
	public void continuaLoginFacebook() throws Exception {
		getBotao().clickByXpath("//android.widget.Button[@index='0']");
	}
	
	public void setUsuarioFacebook(String login) throws Exception {
		getEditText().insertTextByText("Email ou telefone", login);
	}
	
	public void setSenhaFacebook(String senha) throws Exception {
		getEditText().insertTextByXpath("//android.widget.EditText[@index='1']", senha);
	}
	
	public void clicarSairConfirmar() throws Exception {
				
		getScreen().scrolltotext("sair");
		
		getScreenshot().CaptureScreenshot();
		getTextView().clickByText("sair");
		
	
		getScreenshot().CaptureScreenshot();
		getTextView().clickByText("SIM");
		getScreenshot().CaptureScreenshot();
	}

	public void setEmail(String usuario) throws Exception{		
		getEditText().insertTextById("br.com.neon:id/editText", usuario);
	}	
	
	public void apagarCampoEmail() throws Exception {
		getEditText().clear("br.com.neon:id/editText");
	}
	
	public void clicarBotaoEntrar() throws Exception {
		getTextView().clickByText("ENTRAR");
		//getTextView().clickByText("JÁ TENHO CONTA");
	}

	//
	//	Métodos validadores
	//
	public void validarLoginSenhaInvalida(boolean resultadoEsperado) throws Exception {
		Thread.sleep(6000);
		
		boolean existeTexto = false;
		// Procura se existe texto na tela
		existeTexto = getGenerics().exists("text", "é você mesmo",Constants.ANDROID_TEXT_VIEW);

		// Registra se logou com sucesso ou n�o
		// (dependendo do resultado esperado do teste)
		if (resultadoEsperado) {
			if (existeTexto) {
				pass("Alerta de email invalido validado com sucesso.");
				closeAppium();
			}
			else {
				fail("Falha ao validar alerta de senha invalido.");
				closeAppium();
			}
		}
		else {
			if (existeTexto) {
				pass("Falha ao validar alerta de senha invalido.");
				closeAppium();
			}
			else {
				fail("Alerta de email invalido validado com sucesso.");
				closeAppium();
			}
		}
	}
	
	public void validarLoginEmailInexistente(boolean resultadoEsperado) throws Exception {
		Thread.sleep(6000);
		
		boolean existeTexto = false;
		// Procura se existe texto na tela
		existeTexto = getGenerics().exists("text", "alerta de email",Constants.ANDROID_TEXT_VIEW);

		// Registra se logou com sucesso ou n�o
		// (dependendo do resultado esperado do teste)
		if (resultadoEsperado) {
			if (existeTexto) {
				pass("Alerta de email invalido validado com sucesso.");
				closeAppium();
			}
			else {
				fail("Falha ao validar alerta de email invalido.");
				closeAppium();
			}
		}
		else {
			if (existeTexto) {
				pass("Falha ao validar alerta de email invalido.");
				closeAppium();
			}
			else {
				fail("Alerta de email invalido validado com sucesso.");
				closeAppium();
			}
		}
	}
	
	public void validarLoginSucesso(boolean resultadoEsperado) throws Exception {
		boolean existeTexto = false;
		
		// Procura se existe texto na tela
		existeTexto = getGenerics().exists("text", "meu neon", Constants.ANDROID_TEXT_VIEW);

		// Registra se logou com sucesso ou n�o
		// (dependendo do resultado esperado do teste)
		if (resultadoEsperado) {
			if (existeTexto) {
				pass("Logado com sucesso!");
				closeAppium();
			}
			else {
				fail("Falha ao logar.");
				closeAppium();
			}
		}
		else {
			if (existeTexto) {
				pass("Login n�o validado com sucesso.");
				closeAppium();
			}
			else {
				fail("Sucesso ao validar login.");
				closeAppium();
			}
		}
	}
	
	public void validarLogoutSucesso(boolean resultadoEsperado) throws Exception {
		boolean existeTexto = false;
		// Procura se existe texto na tela
		existeTexto = getGenerics().exists("text", "JÁ TENHO CONTA",Constants.ANDROID_TEXT_VIEW);
		
		// Registra se fez logout com sucesso ou n�o
		// (dependendo do resultado esperado do teste)
		if (resultadoEsperado) {
			if (existeTexto) {
				pass("Logout com sucesso!");
				closeAppium();
			}
			else {
				fail("Falha fazer logout.");
				closeAppium();
			}
		}
		else {
			if (existeTexto) {
				pass("Logout n�o validado com sucesso.");
				closeAppium();
			}
			else {
				fail("Sucesso ao validar logout.");
				closeAppium();
			}
		}
	}
	
	public void validarContatoFacebook(boolean resultadoEsperado, String nomeFavorecido, String contaFavorecido) throws Exception {
		boolean existeContaContato = false;
		
		getScreenshot().CaptureScreenshot();
		homeView.clicarOpcaoMenu("transferências");
		
		getScreenshot().CaptureScreenshot();
		transfView.selecionarFavorecido(nomeFavorecido);
		
		// Procura se existe texto na tela
		existeContaContato = getGenerics().exists("text", contaFavorecido, Constants.ANDROID_TEXT_VIEW);
		
		// Registra se fez logout com sucesso ou n�o
		// (dependendo do resultado esperado do teste)
		if (resultadoEsperado) {
			if (existeContaContato) {
				pass("Contatos facebook sincronizado com sucesso!");
				
				// Voltar tela ini
				getScreenshot().CaptureScreenshot();
				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
				
				getScreenshot().CaptureScreenshot();
				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
			}
			else {
				fail("Contatos facebook sincronizado com falha.");
				closeAppium();
			}
		}
		else {
			if (existeContaContato) {
				pass("Contatos facebook sincronizado com falha.");
				closeAppium();
			}
			else {
				fail("Contatos facebook sincronizado com sucesso!");
				closeAppium();
			}
		}
	}
}
