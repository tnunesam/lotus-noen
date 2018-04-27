package br.com.inmetrics.frameworkneon.views.ios;

import static org.junit.Assert.fail;

import com.thoughtworks.selenium.webdriven.commands.GetAllButtons;

import br.com.inmetrics.frameworkneon.NeonIOS;
import br.com.inmetrics.frameworkneon.views.HomeView;
import br.com.inmetrics.frameworkneon.views.TransferenciasView;
import br.com.inmetrics.frameworkneon.views.UtilView;
import br.com.inmetrics.neon.constants.Constants;
import io.appium.java_client.android.AndroidKeyCode;

public class LoginView extends NeonIOS {
	
	br.com.inmetrics.frameworkneon.views.ios.UtilView utilView = new br.com.inmetrics.frameworkneon.views.ios.UtilView();
	br.com.inmetrics.frameworkneon.views.ios.HomeView homeView = new br.com.inmetrics.frameworkneon.views.ios.HomeView();
	TransferenciasView transfView = new TransferenciasView();

	//
	//	M�todos CT's
	//
	public void logar(String usuario, String senha) throws Exception {
//		if (getGenerics().exists("name", "icone fechar", Constants.UIABUTTON)) 
//			getBotao().clickByName("icone fechar");
//		
//		//getScreenshot().CaptureScreenshot();
//		apagarCampoEmail();
//		
//		//getScreenshot().CaptureScreenshot();
//		setEmail(usuario);	
//		getScreen().hideKeyboard();	
//		
//		//getScreenshot().CaptureScreenshot();
//		clicarBotaoEntrar();
		
		Thread.sleep(2000);
		//getScreenshot().CaptureScreenshot();
		utilView.setSenha(senha);
		
		
		
//		Thread.sleep(5000);
//		if (getGenerics().exists("text", "alerta de email!", Constants.ANDROID_TEXT_VIEW)) {
//			voltarTelaLogin();
//			logar(usuario, senha);
//		}
		
		while (!getGenerics().exists("xpath", "//UIAApplication[1]/UIAWindow[1]/UIAStaticText[3]", Constants.UIASTATICTEXT)){
			Thread.sleep(1000);
		}
	}
	
	public void logarEmailInexistente(String usuario, String senha) throws Exception {
		if (getGenerics().exists("xpath", "//UIAApplication[1]/UIAWindow[1]/UIAButton[1]", Constants.UIABUTTON)) 
			getBotao().clickByText("icone fechar");
		
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
		if (getGenerics().exists("xpath", "//UIAApplication[1]/UIAWindow[1]/UIAButton[1]", Constants.UIABUTTON)) 
			getBotao().clickByText("icone fechar");
		
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
			while (!getGenerics().exists("text", "TENTAR MAIS UMA VEZ",Constants.UIABUTTON))
				Thread.sleep(1000);
//			getTextView().clickByText("TENTAR MAIS UMA VEZ");
		}
	} 
	
	public void logout() throws Exception {
		Thread.sleep(5000);
		homeView.clicarOpcaoMenu("meu neon");
		
		getScreenshot().CaptureScreenshot();
		clicarSairConfirmar();
	}
	
	public void sincronizarContatosFacebook(String usuario, String senha) throws Exception {
		Thread.sleep(2000);
		homeView.clicarOpcaoMenu("meu neon");
		
		getScreenshot().CaptureScreenshot();
		clicaSwitchFacebook();
		
		loginFacebook(usuario, senha);
	}


	//
	// Acoes tela
	//
	private void loginFacebook(String usuario, String senha) {
		// TODO Auto-generated method stub
		
	}
	
	private void clicaSwitchFacebook() {
		// TODO Auto-generated method stub
		
	}
	
	private void clicarSairConfirmar() throws Exception {
		getScreen().scrolltotext("sair");
		
		getScreenshot().CaptureScreenshot();
		getStaticText().clickByText("sair");
	
		getScreenshot().CaptureScreenshot();
		getBotao().clickByText("SIM");
		getScreenshot().CaptureScreenshot();
	}
	
	private void voltarTelaLogin() {
		// TODO Auto-generated method stub
		
	}

	private void clicarBotaoEntrar() throws Exception {
		// TODO Auto-generated method stub
		getBotao().clickByText("JÁ TENHO CONTA");
	}

	private void setEmail(String usuario) throws Exception {
		getEditText().insertTextByXpath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]", usuario);
	}

	private void apagarCampoEmail() throws Exception {
		getEditText().clearFieldByXpath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]");
	}

	//
	// Metodos validadores
	//
	public void validarLoginSucesso(boolean resultadoEsperado) {
		boolean existeTexto = false;
		
		// Procura se existe texto na tela
		existeTexto = getGenerics().exists("name", "meu neon", Constants.UIASTATICTEXT);

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
				pass("Login não validado com sucesso.");
				closeAppium();
			}
			else {
				fail("Sucesso ao validar login.");
				closeAppium();
			}
		} 
	}

	public void validarLogoutSucesso(boolean resultadoEsperado) throws Exception {
		if (getGenerics().exists("name", "icone fechar", Constants.UIABUTTON)) 
			getBotao().clickByText("icone fechar");
		
		boolean existeTexto = false;
		// Procura se existe texto na tela
		existeTexto = getGenerics().exists("name", "QUERO UMA CONTA",Constants.UIABUTTON);
		
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

	public void validarLoginEmailInexistente(boolean resultadoEsperado) {
		boolean existeTexto = false;
		// Procura se existe texto na tela
		existeTexto = getGenerics().exists("name", "alerta de email",Constants.UIASTATICTEXT);
		
		// Registra se fez logout com sucesso ou n�o
		// (dependendo do resultado esperado do teste)
		if (resultadoEsperado) {
			if (existeTexto) {
				pass("Login com email invalida validado com sucesso!");
				closeAppium();
			}
			else {
				fail("Falha ao validar login com email invalido");
				closeAppium();
			}
		}
		else {
			if (existeTexto) {
				pass("Falha ao validar login com email invalido");
				closeAppium();
			}
			else {
				fail("Login com email invalida validado com sucesso!");
				closeAppium();
			}
		}
	}

	public void validarLoginSenhaInvalida(boolean resultadoEsperado) {
		boolean existeTexto = false;
		// Procura se existe texto na tela
		existeTexto = getGenerics().exists("name", "TENTAR MAIS UMA VEZ",Constants.UIABUTTON);
		
		// Registra se fez logout com sucesso ou n�o
		// (dependendo do resultado esperado do teste)
		if (resultadoEsperado) {
			if (existeTexto) {
				pass("Login com senha invalida validado com sucesso!");
				closeAppium();
			}
			else {
				fail("Falha ao validar login com senha invalida");
				closeAppium();
			}
		}
		else {
			if (existeTexto) {
				pass("Falha ao validar login com senha invalida");
				closeAppium();
			}
			else {
				fail("Login com senha invalida validado com sucesso!");
				closeAppium();
			}
		}
	}
}
