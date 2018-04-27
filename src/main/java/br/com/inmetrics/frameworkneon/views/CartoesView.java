package br.com.inmetrics.frameworkneon.views;

import static br.com.inmetrics.frameworkneon.Neon.initializeDrivers;
import static org.junit.Assert.fail;

import br.com.inmetrics.frameworkneon.Neon;
import br.com.inmetrics.neon.constants.Constants;
import io.appium.java_client.android.AndroidKeyCode;

public class CartoesView extends Neon {
	
	UtilView utilView = new UtilView();
	HomeView homeView = new HomeView();
	
	//
	//	Métodos CT's
 	//
	public void copiarNumeroCartaoVirtual() throws Exception {
		getScreenshot().CaptureScreenshot();
		homeView.clicarOpcaoMenu("cartões");
		
		getScreenshot().CaptureScreenshot();
		clicarBotaoCartaoVirtual();
		
		getScreenshot().CaptureScreenshot();
		clicaBotaoCopiarNumero();
	}
	
	public void desbloquearCartaoVirtualTemporario() throws Exception {
		getScreenshot().CaptureScreenshot();
		homeView.clicarOpcaoMenu("cartões");
		
		getScreenshot().CaptureScreenshot();
		clicarBotaoCartaoVirtual();
		
		getScreenshot().CaptureScreenshot();
		clicarBotaoDesbloquear();
		
		getScreenshot().CaptureScreenshot();
		confirmaDesbloqueioCartaoVirtual();
		
		// Voltar tela inicial
		getScreenshot().CaptureScreenshot();
		getGenerics().pressKeyCode(AndroidKeyCode.BACK);
	}
	
	public void bloquearCartaoVirtualTemporario() throws Exception {
		getScreenshot().CaptureScreenshot();
		homeView.clicarOpcaoMenu("cartões");
		
		getScreenshot().CaptureScreenshot();
		clicarBotaoCartaoVirtual();
		
		getScreenshot().CaptureScreenshot();
		clicarBotaoBloqueioTemporario();
		
		getScreenshot().CaptureScreenshot();
		confirmaBloqueioCartaoVirtual();
		
		// Voltar tela inicial
		getScreenshot().CaptureScreenshot();
		getGenerics().pressKeyCode(AndroidKeyCode.BACK);
	}
	
	//
	// Métodos a��es b�sicas de tela
	//
	public void clicaBotaoCopiarNumero() throws Exception {
		getTextView().clickByText("COPIAR NÚMERO");
	}
	
	public void clicarBotaoDesbloquear() throws Exception {
		getTextView().clickByText("DESBLOQUEAR");
	}
	
	public void clicarBotaoCartaoVirtual() throws Exception {
		getTextView().clickById("br.com.neon:id/txVirtual");
	}
	
	public void clicarBotaoBloqueioTemporario() throws Exception {
		getTextView().clickByText("BLOQUEIO TEMPORÁRIO");
	}
	
	public void confirmaBloqueioCartaoVirtual() throws Exception {
		getTextView().clickByText("SIM");
	}
	
	public void confirmaDesbloqueioCartaoVirtual() throws Exception {
		getTextView().clickByText("SIM");
	}
	
	public void clicarBotaoNotificacoes() throws Exception {
		homeView.clicarBalaoNotificacoes();
	}
	
	//
	// Métodos validadores
	//
	public void validarCartaoVirtualBloqueadoTemporario(boolean resultadoEsperado) throws Exception {
		Thread.sleep(5000);
		boolean existeTexto = false;
		
		getScreenshot().CaptureScreenshot();
		homeView.clicarOpcaoMenu("cartões");
		
		getScreenshot().CaptureScreenshot();
		clicarBotaoCartaoVirtual();
		
		String statusCartao = getTextView().getAttributeById("br.com.neon:id/tv_payment_time", "text");
		
		// Valida saldo
		existeTexto = statusCartao.equals("status: bloqueio temporário");

		// Registra se transferencia foi realizado
		// (dependendo do resultado esperado do teste)
		if (resultadoEsperado) {
			if (existeTexto) {
				for (int i = 0; i < getDt().getRowCount(); i++) {
					if (getDt().getStringOf("ID_CT").equals("CT_038")) 	break;
					else												getDt().setNextRow();
				}
				pass("Cartão bloqueado com sucesso");
				
				// Volta para tela inicial
				getScreenshot().CaptureScreenshot();
				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
			}
			else {
				closeAppium();
				fail("Falha ao bloquear cartão.");
			}
		}
		else {
			if (existeTexto)	pass("Falha ao bloquear cartão.");
			else {
				closeAppium();
				fail("Sucesso ao bloquear cartão.");
			}
		}
	}
	
	public void validarCartaoVirtualDesbloqueadoTemporario(boolean resultadoEsperado) throws Exception {
		Thread.sleep(5000);
		boolean existeTexto = false;
		
		getScreenshot().CaptureScreenshot();
		homeView.clicarOpcaoMenu("cartões");
		
		getScreenshot().CaptureScreenshot();
		clicarBotaoCartaoVirtual();
		
		String statusCartao = getTextView().getAttributeById("br.com.neon:id/tv_payment_time", "text");
		
		// Valida saldo
		existeTexto = statusCartao.equals("status: ativo");

		// Registra se transferencia foi realizado
		// (dependendo do resultado esperado do teste)
		if (resultadoEsperado) {
			if (existeTexto) {
				pass("cartão bloqueado com sucesso");
				
				// Volta para tela inicial
				getScreenshot().CaptureScreenshot();
				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
			}
			else {
				closeAppium();
				fail("Falha ao bloquear cartão.");
			}
		}
		else {
			if (existeTexto)	pass("Falha ao bloquear cartão.");
			else {
				closeAppium();
				fail("Sucesso ao bloquear cartão.");
			}
		}
	}
	
	public void validarNotificacaoBloqueioCartaoVirtual(boolean resultadoEsperado) throws Exception {
		Thread.sleep(5000);
		boolean existeTexto = false;
		
		getScreenshot().CaptureScreenshot();
		homeView.clicarBalaoNotificacoes();
		
		// Procura se existe texto na tela
		getScreenshot().CaptureScreenshot();
		existeTexto = getGenerics().exists("text", "cartão virtual bloqueado",Constants.ANDROID_TEXT_VIEW);

		if (resultadoEsperado) {
			if (existeTexto) {
				pass("Notificação de bloqueio de cartão virtual validada com sucesso.");
				
				// Volta para tela inicial
				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
				getScreenshot().CaptureScreenshot();
			}
			else {
				closeAppium();
				fail("Falha ao tentar validar Notificação de bloqueio de cartão virtual.");
			}
		}
		else {
			if (existeTexto)	pass("Falha ao tentar validar Notificação de bloqueio de cartão virtual.");
			else {
				closeAppium();
				fail("Notificação de bloqueio de cartão virtual validada com sucesso.");
			}
		}
	}
	
	public void validarNotificacaoDesbloqueioCartaoVirtual(boolean resultadoEsperado) throws Exception {
		Thread.sleep(7000);
		boolean existeTexto = false;
		
		getScreenshot().CaptureScreenshot();
		homeView.clicarBalaoNotificacoes();
		
		// Procura se existe texto na tela
		getScreenshot().CaptureScreenshot();
		existeTexto = getGenerics().exists("text", "Cartao virtual desbloqueado!",Constants.ANDROID_TEXT_VIEW);

		if (resultadoEsperado) {
			if (existeTexto) {
				pass("Notificação de desbloqueio de cartão virtual validada com sucesso.");
				
				// Volta para tela inicial
				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
				getScreenshot().CaptureScreenshot();
			}
			else {
				closeAppium();
				fail("Falha ao tentar validar Notificação de desbloqueio de cartão virtual.");
			}
		}
		else {
			if (existeTexto)	pass("Falha ao tentar validar Notificação de desbloqueio de cartão virtual.");
			else {
				closeAppium();
				fail("Notificação de desbloqueio de cartão virtual validada com sucesso.");
			}
		}
	}
	
	public void validarCopiaNumeroCartaoVirtual(boolean resultadoEsperado) throws Exception {
		Thread.sleep(2000);
		boolean existeTexto = false;
		
		getScreenshot().CaptureScreenshot();
		
		// Procura se existe texto na tela
		existeTexto = getGenerics().exists("text", "número copiado",Constants.ANDROID_TEXT_VIEW);

		if (resultadoEsperado) {
			if (existeTexto) {
				pass("Numero cartão virtual copiado com sucesso.");
				getScreenshot().CaptureScreenshot();
				getTextView().clickByText("OK, ENTENDI");
				
				// Volta para tela inicial
				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
				getScreenshot().CaptureScreenshot();
				
				Thread.sleep(2000);
			}
			else {
				closeAppium();
				fail("Falha ao tentar copiar numero do cartão virtual.");
			}
		}
		else {
			if (existeTexto)	pass("Falha ao tentar copiar numero do cartão virtual.");
			else {
				closeAppium();
				fail("Numero cartão virtual copiado com sucesso.");
			}
		}
	}
}
