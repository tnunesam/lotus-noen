package br.com.inmetrics.frameworkneon.views.ios;

import static org.junit.Assert.fail;

import br.com.inmetrics.frameworkneon.Neon;
import br.com.inmetrics.frameworkneon.NeonIOS;
import br.com.inmetrics.frameworkneon.views.ios.HomeView;
//import br.com.inmetrics.frameworkneon.views.ios.TransferenciasView; s
import br.com.inmetrics.frameworkneon.views.ios.UtilView;
import br.com.inmetrics.neon.constants.Constants;
import io.appium.java_client.android.AndroidKeyCode;

public class SaldoView extends NeonIOS {
	private double saldoInicial;
	private double saldoFinal;
	UtilView utilView = new UtilView();
	HomeView homeView = new HomeView();
//	TransferenciasView transfView = new TransferenciasView();
	
	//
	//	Métodos CT's
	//
	
	
	//
	//	Métodos A�oes tela
	//
//	public double getSaldoToolBar() throws NumberFormatException, Exception {
//		double saldo = Double.parseDouble(getTextView().getAttribute("id", "br.com.neon:id/toolbar_title", "text").replaceAll(",", ".").replace("$", "")
//				.replaceAll(" ", "").replaceAll("R", "").replace("saldo", ""));
//		return saldo;
//	}
	
	public double getSaldo() throws NumberFormatException, Exception {
		double saldo = Double.parseDouble(getStaticText().getAttributeByXpath("//UIAApplication[1]/UIAWindow[1]/UIAStaticText[3]", "value").replace("$", "").replaceAll(" ", "").replaceAll("R", "").replace(".", "").replace(",", "."));
		return saldo;
	}
	
	public void setSaldoInicial(double saldoInicial) {
		this.saldoInicial = saldoInicial;
	}
	
	//
	//	Métodos validadores
	//
	public void validarAtualizacaoSaldoAposOperacao(boolean resultadoEsperado, String valorTransf) throws NumberFormatException, Exception {
		Thread.sleep(4000);
		boolean saldoValido = false;
		
		getScreenshot().CaptureScreenshot();
		homeView.clicarOpcaoMenu("saldo");
		
		getScreenshot().CaptureScreenshot();
//		this.saldoFinal = getSaldoToolBar();
		
		// Valida saldo
//		saldoValido = transfView.validaSaldoComTarifas(saldoInicial, saldoFinal, Double.parseDouble(valorTransf));

		// Registra se transferencia foi realizado
		// (dependendo do resultado esperado do teste)
		if (resultadoEsperado) {
			if (saldoValido) {
				pass("Saldo da tela \"Saldo\" atualizado com sucesso!");
				
				// Volta para tela inicial
				getScreenshot().CaptureScreenshot();
				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
			}
			else {
				closeAppium();
				fail("Falha ao atualizar Saldo da tela \"Saldo\".");
			}
		}
		else {
			if (saldoValido) {
				pass("Falha ao atualizar Saldo da tela \"Saldo\".");
				
				getScreenshot().CaptureScreenshot();
				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
			}
			else {
				closeAppium();
				fail("Saldo da tela \"Saldo\" atualizado com sucesso!");
			}
		}
	}
	
	public void validarExistenciaBoletoNeonETransferencia(boolean resultadoEsperado) throws NumberFormatException, Exception {
		Thread.sleep(4000);
		
		getScreenshot().CaptureScreenshot();
		homeView.clicarOpcaoMenu("saldo");
		
		getScreen().scrolltotext("Enviou Para");
		boolean existeTransf = getGenerics().exists("text", "Enviou Para", Constants.ANDROID_TEXT_VIEW);
		
		getScreen().scrolltotext("Banco Neon");
		boolean existePagamento = getGenerics().exists("text", "Banco Neon", Constants.ANDROID_TEXT_VIEW);
		//

		// Registra se transferencia foi realizado
		// (dependendo do resultado esperado do teste)
		if (resultadoEsperado) {
			if (existeTransf && existePagamento) {
				pass("Exibição de pagamentos na tela de saldo efetuada com sucesso!");
				
				getScreenshot().CaptureScreenshot();
				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
			}
			else {
				closeAppium();
				fail("Falha ao verificar Exibição de pagamentos na tela de saldo.");
			}
		}
		else {
			if (existeTransf && existePagamento) {
				pass("Falha ao verificar Exibição de pagamentos na tela de saldo.");
				
				getScreenshot().CaptureScreenshot();
				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
			}
			else {
				closeAppium();
				fail("Exibição de pagamentos na tela de saldo efetuada com sucesso!");
			}
		}
	}
}
