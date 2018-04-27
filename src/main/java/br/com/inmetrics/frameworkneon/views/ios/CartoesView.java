package br.com.inmetrics.frameworkneon.views.ios;

import static org.junit.Assert.fail;

import android.gesture.GestureUtils;
import br.com.inmetrics.frameworkneon.NeonIOS;
import br.com.inmetrics.frameworkneon.views.ios.HomeView;
import br.com.inmetrics.frameworkneon.views.ios.UtilView;
import br.com.inmetrics.neon.constants.Constants;
import io.appium.java_client.android.AndroidKeyCode;

public class CartoesView extends NeonIOS {
	
	UtilView utilView = new UtilView();
	HomeView homeView = new HomeView();
	
	//
	//	Métodos CT's
	//
	
	public void copiarNumeroCartaoVirtual() throws Exception {
		getScreenshot().CaptureScreenshot();
		homeView.clicarOpcaoMenu("cartões");
		
		getScreenshot().CaptureScreenshot();
		clicarBotaoCartaoVirtual();
		
		getScreenshot().CaptureScreenshot();
		clicaBotaoCopiarNumero();
	}

	public void bloquearCartaoVirtualTemporario() throws Exception {
		getScreenshot().CaptureScreenshot();
		homeView.clicarOpcaoMenu("cartões");
		
		getScreenshot().CaptureScreenshot();
		clicarBotaoCartaoVirtual();
		
		getScreenshot().CaptureScreenshot();
		clicarBotaoBloqueioTemporario();
		
		getScreenshot().CaptureScreenshot();
		confirmaBloqueioCartaoVirtual();
		Thread.sleep(5000);
		
		// Voltar tela inicial
		getScreenshot().CaptureScreenshot();
		getBotao().clickByText("icone voltar");
	}
	
	public void desbloquearCartaoVirtualTemporario() throws Exception {
		getScreenshot().CaptureScreenshot();
		homeView.clicarOpcaoMenu("cartões");
		
		getScreenshot().CaptureScreenshot();
		clicarBotaoCartaoVirtual();
		
		getScreenshot().CaptureScreenshot();
		clicarBotaoDesbloquear();
		
		getScreenshot().CaptureScreenshot();
		confirmaDesbloqueioCartaoVirtual();
		
		Thread.sleep(5000);
		
		// Voltar tela inicial
		getScreenshot().CaptureScreenshot();
		getBotao().clickByText("icone voltar");
	}
	
	private void clicarBotaoDesbloquear() throws Exception {
		getBotao().clickByXpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAButton[2]");
	}

	private void confirmaDesbloqueioCartaoVirtual() throws Exception {
		getBotao().clickByText("SIM");
	}

	private void clicaBotaoCopiarNumero() throws Exception {
		getBotao().clickByText("COPIAR NÚMERO");
	}

	private void confirmaBloqueioCartaoVirtual() throws Exception {
		getBotao().clickByText("SIM");
	}

	private void clicarBotaoBloqueioTemporario() throws Exception {
//		getBotao().clickByText("BLOQUEIO TEMPORÁRIO");
		getBotao().clickByXpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAButton[2]");
	}

	private void clicarBotaoCartaoVirtual() throws Exception {
		getBotao().clickByText("cartão virtual");
//		getBotao().ClickByXpath("//UIAApplication[1]/UIAWindow[1]/UIAButton[4]");
	}

	public void validarCartaoVirtualBloqueadoTemporario(boolean resultadoEsperado) throws Exception {
		getScreenshot().CaptureScreenshot();
		homeView.clicarOpcaoMenu("cartões");
		
		getScreenshot().CaptureScreenshot();
		clicarBotaoCartaoVirtual();
		
		boolean existeTexto = false;
		// Procura se existe texto na tela
		existeTexto = getGenerics().exists("text", "status: bloqueio temporário",Constants.UIASTATICTEXT);
		
		// Registra se fez logout com sucesso ou n�o
		// (dependendo do resultado esperado do teste)
		if (resultadoEsperado) {
			if (existeTexto) {
				pass("Sucesso ao validar bloqueio de cartao!");
				
				getScreenshot().CaptureScreenshot();
				getBotao().clickByText("icone voltar");
			}
			else {
				fail("Falha ao validar bloqueio de cartao.");
				closeAppium();
			}
		}
		else {
			if (existeTexto) {
				pass("Falha ao validar bloqueio de cartao.");
			}
			else {
				fail("Sucesso ao validar bloqueio de cartao!");
				closeAppium();
			}
		}
	}

	public void validarNotificacaoBloqueioCartaoVirtual(boolean resultadoEsperado) throws Exception {
		boolean existeTexto = false;
		// Procura se existe texto na tela
		getBotao().clickByXpath("//UIAApplication[1]/UIAWindow[1]/UIAButton[2]");
		Thread.sleep(4000);
		existeTexto = getGenerics().exists("text", "cartão virtual bloqueado",Constants.UIASTATICTEXT);
		
		// Registra se fez logout com sucesso ou n�o
		// (dependendo do resultado esperado do teste)
		if (resultadoEsperado) {
			if (existeTexto) {
				pass("Sucesso ao validar notificação de bloqueio de cartao!");
				
				getScreenshot().CaptureScreenshot();
				getBotao().clickByText("icone voltar");
			}
			else {
				fail("Falha ao validar notificação de bloqueio de cartao.");
				closeAppium();
			}
		}
		else {
			if (existeTexto) {
				pass("Falha ao validar notificação de bloqueio de cartao.");
			}
			else {
				fail("Sucesso ao validar notificação de bloqueio de cartao!");
				closeAppium();
			}
		}
	}

	public void validarDesbloqueioCartaoVirtual(boolean resultadoEsperado) throws Exception {
		getScreenshot().CaptureScreenshot();
		homeView.clicarOpcaoMenu("cartões");
		
		getScreenshot().CaptureScreenshot();
		clicarBotaoCartaoVirtual();
		
		boolean existeTexto = false;
		// Procura se existe texto na tela
		existeTexto = getGenerics().exists("text", "status: ativo",Constants.UIASTATICTEXT);
		
		// Registra se fez logout com sucesso ou n�o
		// (dependendo do resultado esperado do teste)
		if (resultadoEsperado) {
			if (existeTexto) {
				pass("Sucesso ao validar desbloqueio de cartao!");
				
				getScreenshot().CaptureScreenshot();
				getBotao().clickByText("icone voltar");
			}
			else {
				fail("Falha ao validar desbloqueio de cartao.");
				closeAppium();
			}
		}
		else {
			if (existeTexto) {
				pass("Falha ao validar desbloqueio de cartao.");
			}
			else {
				fail("Sucesso ao validar desbloqueio de cartao!");
				closeAppium();
			}
		}
	}

	public void validarNotificacaoDesbloqueiCartaoVirtual(boolean resultadoEsperado) throws Exception {
		boolean existeTexto = false;
		// Procura se existe texto na tela
		getBotao().clickByXpath("//UIAApplication[1]/UIAWindow[1]/UIAButton[2]");
		Thread.sleep(4000);
		existeTexto = getGenerics().exists("text", "cartão virtual bloqueado",Constants.UIASTATICTEXT);
		
		// Registra se fez logout com sucesso ou n�o
		// (dependendo do resultado esperado do teste)
		if (resultadoEsperado) {
			if (existeTexto) {
				pass("Sucesso ao validar notificação de bloqueio de cartao!");
				
				getScreenshot().CaptureScreenshot();
				getBotao().clickByText("icone voltar");
			}
			else {
				fail("Falha ao validar notificação de bloqueio de cartao.");
				closeAppium();
			}
		}
		else {
			if (existeTexto) {
				pass("Falha ao validar notificação de bloqueio de cartao.");
			}
			else {
				fail("Sucesso ao validar notificação de bloqueio de cartao!");
				closeAppium();
			}
		}
	}

	public void validarCopiaNumeroCartaoVirtual(boolean resultadoEsperado) throws Exception {
		boolean existeTexto = false;
		// Procura se existe texto na tela
		existeTexto = getGenerics().exists("text", "número copiado",Constants.UIASTATICTEXT);
		
		// Registra se fez logout com sucesso ou n�o
		// (dependendo do resultado esperado do teste)
		if (resultadoEsperado) {
			if (existeTexto) {
				pass("Sucesso ao validar notificação de bloqueio de cartao!");
				
				getScreenshot().CaptureScreenshot();
				getBotao().clickByText("OK");
				
				getScreenshot().CaptureScreenshot();
				getBotao().clickByText("icone voltar");
			}
			else {
				fail("Falha ao validar notificação de bloqueio de cartao.");
				closeAppium();
			}
		}
		else {
			if (existeTexto) {
				pass("Falha ao validar notificação de bloqueio de cartao.");
			}
			else {
				fail("Sucesso ao validar notificação de bloqueio de cartao!");
				closeAppium();
			}
		}
	}
}
