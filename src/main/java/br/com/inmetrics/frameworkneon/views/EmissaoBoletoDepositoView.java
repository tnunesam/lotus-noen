package br.com.inmetrics.frameworkneon.views;

import static org.junit.Assert.fail;

import android.content.Context;
import br.com.inmetrics.frameworkneon.Neon;
import br.com.inmetrics.neon.constants.Constants;
import io.appium.java_client.android.AndroidKeyCode;

public class EmissaoBoletoDepositoView extends Neon {

	private double limiteDiarioInicial;
	private double limiteDiarioFinal;
	private double saldoInicial;
	private double saldoFinal;
	HomeView homeView = new HomeView();
	UtilView utilView = new UtilView();
	
	public static void main(String[] args) {
		
		String a = ("R$ 200,00").replaceAll(",", ".").replace("$", "").replaceAll(" ", "").replaceAll("R", "");
		System.out.println(a + "");
		System.out.println(Double.parseDouble(a)+"");
	}
	
	//
	//	Métodos CT's
	//
	public void emissaoBoletoNeonDeposito(String valor) throws Exception {
		getScreenshot().CaptureScreenshot();
		homeView.clicarOpcaoMenu("meu neon");
		
		getScreenshot().CaptureScreenshot();
		clicarBotaoDepositoViaBoleto();
		
		getScreenshot().CaptureScreenshot();
		this.limiteDiarioInicial = getLimiteDiario();
		
		getScreenshot().CaptureScreenshot();
		setValorDeposito(valor);
		
		getScreenshot().CaptureScreenshot();
		clicarBotaoGerarBoleto();
		
		while (!getGenerics().exists("text", "boleto gerado",Constants.ANDROID_TEXT_VIEW)){
			Thread.sleep(1000);
		}
		
		getScreenshot().CaptureScreenshot();
		fecharBoletoGerado();
	}
	
	public void efetuarCopiaLinhaDigitavelBoletoEmitido(String valor) throws Exception {
		getScreenshot().CaptureScreenshot();
		homeView.clicarOpcaoMenu("meu neon");
		
		getScreenshot().CaptureScreenshot();
		clicarBotaoDepositoViaBoleto();
		
		getScreenshot().CaptureScreenshot();
		setValorDeposito(valor);
		
		getScreenshot().CaptureScreenshot();
		clicarBotaoGerarBoleto();
		
		while (!getGenerics().exists("text", "boleto gerado",Constants.ANDROID_TEXT_VIEW)){
			Thread.sleep(1000);
		}
		
		getScreenshot().CaptureScreenshot();
		clicarBotaoCopiarLinhaDigitavel();
	}
	
	public void acessarLinkBoletoGerado(String valor) throws Exception {
		getScreenshot().CaptureScreenshot();
		homeView.clicarOpcaoMenu("meu neon");
		
		getScreenshot().CaptureScreenshot();
		clicarBotaoDepositoViaBoleto();
		
		getScreenshot().CaptureScreenshot();
		setValorDeposito(valor);
		
		getScreenshot().CaptureScreenshot();
		clicarBotaoGerarBoleto();
		
		while (!getGenerics().exists("text", "boleto gerado",Constants.ANDROID_TEXT_VIEW)){
			Thread.sleep(1000);
		}
	}
	
	public void emissaoBoletoNeonDepositoAcimaLimite(String valor) throws Exception {
		getScreenshot().CaptureScreenshot();
		homeView.clicarOpcaoMenu("meu neon");
		
		getScreenshot().CaptureScreenshot();
		clicarBotaoDepositoViaBoleto();
		
		getScreenshot().CaptureScreenshot();
		setValorDeposito(valor);
		
		getScreenshot().CaptureScreenshot();
		clicarBotaoGerarBoleto();
	}
	
	public boolean validaLimite(double limiteInicial, double limiteFinal, double valorBoleto) {
		boolean limiteValido = false;
		double calculaDiferencaLimite = (limiteInicial - limiteFinal) - valorBoleto;
		if (Math.round(calculaDiferencaLimite) == 0.0) limiteValido = true;
		return limiteValido;
	}
	
	//
	//	Métodos de a��es da tela
	//	
	public double getSaldo() throws NumberFormatException, Exception {
		return Double.parseDouble(getTextView().getAttribute("id", "br.com.neon:id/widgetMoneyValue", "text").replaceAll(",", "."));
	}
	
	public boolean validaSaldo(double saldoInicial, double saldoFinal, double valorBoleto) {
		boolean saldoValido = false;
		double calculaDiferencaSaldo = (saldoInicial - saldoFinal) - valorBoleto;
		if (Math.round(calculaDiferencaSaldo) == 0.0) saldoValido = true;
		return saldoValido;
	}
	
	public boolean validaSaldoComTarifa(double saldoInicial, double saldoFinal, double valorBoleto) {
		boolean saldoValido = false;
		double calculaDiferencaSaldo = (saldoInicial - saldoFinal) - (valorBoleto + 2.5);
		if (Math.round(calculaDiferencaSaldo) == 0.0) saldoValido = true;
		return saldoValido;
	}
	
	public void clicarBotaoAcessarLinkBoleto() throws Exception {
		getTextView().clickByText("ACESSAR LINK DO BOLETO");
	}
	
	public void clicarBotaoCopiarLinhaDigitavel() throws Exception {
		getTextView().clickByText("COPIAR LINHA DIGITÁVEL");
	}
	
	public double getLimiteDiario() throws NumberFormatException, Exception {
		double a = Double.parseDouble(getTextView().getAttributeById("br.com.neon:id/lblUsedLimits", "text").replace("$", "").replaceAll(" ", "").replaceAll("R", "").replace(".", "").replace(",", "."));
		return a;
	}
	
	public void clicarBotaoDepositoViaBoleto() throws Exception {
		getScreen().scrolltotext("depósitos via boleto");
		
		getScreenshot().CaptureScreenshot();
		getTextView().clickByText("depósitos via boleto");
	}
	
	public void setValorDeposito (String valor) throws Exception {
		getEditText().insertTextByIdElement("br.com.neon:id/txt_valor_carregar_cartao", valor);	
	}
	
	public void clicarBotaoGerarBoleto() throws Exception {
		getTextView().clickByText("GERAR BOLETO");
	}
	
	public void fecharBoletoGerado() throws Exception {
		getGenerics().pressKeyCode(AndroidKeyCode.BACK);
		getScreenshot().CaptureScreenshot();
		getGenerics().pressKeyCode(AndroidKeyCode.BACK);
	}
	
	//
	//	Métodos validadores
	//
	public void validarBoletoNeonEmitidoDeposito(boolean resultadoEsperado) throws Exception {
		boolean existeTexto = false;
		
		homeView.clicarBalaoNotificacoes();
		getScreenshot().CaptureScreenshot();
		
		// Procura se existe texto na tela
		existeTexto = getGenerics().exists("text", "Novo boleto emitido",Constants.ANDROID_TEXT_VIEW);
		
		// Registra se fez logout com sucesso ou n�o
		// (dependendo do resultado esperado do teste)
		if (resultadoEsperado) {
			if (existeTexto) {
				pass("Boleto deposito Neon emitido com sucesso.");
				
				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
				getScreenshot().CaptureScreenshot();
			}
			else {
				closeAppium();
				fail("Falha ao emitir boleto Neon de dep�sito.");
			}
		}
		else {
			if (existeTexto)	pass("Falha ao emitir boleto Neon de dep�sito.");
			else {
				closeAppium();
				fail("Sucesso ao emitir boleto Neon de dep�sito.");
			}
		}
	}
	
	public void validarReducaoLimiteDiarioEmissaoBoleto(boolean resultadoEsperado, String valorBoleto) throws Exception {
		boolean limiteValido = false;
		
		getScreenshot().CaptureScreenshot();
		homeView.clicarOpcaoMenu("meu neon");
		
		getScreenshot().CaptureScreenshot();
		clicarBotaoDepositoViaBoleto();
		
		getScreenshot().CaptureScreenshot();
		this.limiteDiarioFinal = getLimiteDiario();
		
		// Valida limite
		limiteValido = validaLimite(limiteDiarioInicial, this.limiteDiarioFinal, Double.parseDouble(valorBoleto.replace(",", ".")));
		
		// Registra se fez logout com sucesso ou n�o
		// (dependendo do resultado esperado do teste)
		if (resultadoEsperado) {
			if (limiteValido) {
				pass("Redu��o limite di�rio de emiss�o de boleto validado com sucesso.");
				
				getScreenshot().CaptureScreenshot();
				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
				getScreenshot().CaptureScreenshot();
				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
				getScreenshot().CaptureScreenshot();
				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
			}
			else {
				closeAppium();
				fail("Falha na redu��o de limite di�rio de emiss�o de boleto.");
			}
		}
		else {
			if (limiteValido) pass("Falha na redu��o de limite di�rio de emiss�o de boleto.");
			else {
				closeAppium();
				fail("Redu��o limite di�rio de emiss�o de boleto validado com sucesso.");
			}
		}
	}
	
	public void validarBoletoNeonEmitidoDepositoValorAcimaLimite(boolean resultadoEsperado) throws Exception {
		boolean existeTexto = false;
		
		// Procura se existe texto na tela
		existeTexto = getGenerics().exists("text", "limite ultrapassado",Constants.ANDROID_TEXT_VIEW);
		
		// Registra se fez logout com sucesso ou n�o
		// (dependendo do resultado esperado do teste)
		if (resultadoEsperado) {
			if (existeTexto) {
				pass("Teste de emiss�o de boleto acima do limite dispon�vel com sucesso.");
				
				getScreenshot().CaptureScreenshot();
				getTextView().clickByText("OK, ENTENDI");
				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
				getScreenshot().CaptureScreenshot();
				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
				getScreenshot().CaptureScreenshot();
			}
			else {
				closeAppium();
				fail("Falha na emiss�o de boleto acima do limite disponível.");
			}
		}
		else {
			if (existeTexto)	pass("Falha na emiss�o de boleto acima do limite dispon�vel.");
			else {
				closeAppium();
				fail("Teste de emiss�o de boleto acima do limite dispon�vel com sucesso.");
			}
		}
	}
	
	public void validarCopiaLinhaDigitavelBoleto(boolean resultadoEsperado) throws Exception {
//		String textoClipBoard = getGenerics().getTextFromClipboard();
//		String codBarrasBoleto = getTextView().getAttribute("id", "br.com.neon:id/tv_bar_code", "text");
		
		boolean existeTexto = getGenerics().exists("text", "COPIAR LINHA DIGITÁVEL",Constants.ANDROID_TEXT_VIEW);

		
		// Registra se fez logout com sucesso ou n�o
		// (dependendo do resultado esperado do teste)
		if (resultadoEsperado) {
			if (existeTexto) {
				pass("Teste de copia da linha DIGITÁVEL do boleto efetuado com sucesso!");
				
				// Volta para tela inicial
				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
				getScreenshot().CaptureScreenshot();
				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
				getScreenshot().CaptureScreenshot();
			}
			else {
				closeAppium();
				fail("Falha na copia da linha DIGITÁVEL do boleto.");
			}
		}
		else {
			if (existeTexto)	pass("Falha na emiss�o de boleto acima do limite dispon�vel.");
			else {
				closeAppium();
				fail("Teste de emiss�o de boleto acima do limite dispon�vel com sucesso.");
			}
		}
	}
	
	public void validarLinkBoletoGerado() throws Exception {
		try {
			getScreenshot().CaptureScreenshot();
			clicarBotaoAcessarLinkBoleto();
			Thread.sleep(6000);
			getScreenshot().CaptureScreenshot();
			pass("Link boleto acessado com sucesso!");
			closeAppium();
		} catch (Exception e) {
			fail("Falha ao tentar acessar link boleto.");
			closeAppium();
		}
	}
	
	public void validarNaoCobrancaTaxaBoleto(boolean resultadoEsperado, String saldoInicial, String valorBoleto) throws Exception {
		boolean saldoValido;
		
		this.saldoFinal = getSaldo();
		
		getScreenshot().CaptureScreenshot();
		homeView.clicarOpcaoMenu("saldo");
		
		saldoValido = validaSaldo(Double.parseDouble(saldoInicial), saldoFinal, Double.parseDouble(valorBoleto));

		// Registra se fez logout com sucesso ou não
		// (dependendo do resultado esperado do teste)
		if (resultadoEsperado) {
			if (saldoValido) {
				pass("Não cobrança taxa do primeiro boleto emitido no mês validado com sucesso!");
				
				// Volta para tela inicial
				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
				getScreenshot().CaptureScreenshot();
			}
			else {
				closeAppium();
				fail("Falha validação da não cobrança de taxa do primeiro boleto emitido no mês.");
			}
		}
		else {
			if (saldoValido)	pass("Falha validação da não cobrança de taxa do primeiro boleto emitido no mês.");
			else {
				closeAppium();
				fail("Não cobrança taxa do primeiro boleto emitido no mês validado com sucesso!");
			}
		}
	}
	
	public void validarValorTarifaAposCompensacaoBoleto(boolean resultadoEsperado, String saldoInicial, String valorBoleto) throws Exception {
		boolean saldoValido;
		
		this.saldoFinal = getSaldo();
		
		getScreenshot().CaptureScreenshot();
		homeView.clicarOpcaoMenu("saldo");
		
		saldoValido = validaSaldoComTarifa(Double.parseDouble(saldoInicial), saldoFinal, Double.parseDouble(valorBoleto));

		// Registra se fez logout com sucesso ou não
		// (dependendo do resultado esperado do teste)
		if (resultadoEsperado) {
			if (saldoValido) {
				pass("Não cobrança taxa do primeiro boleto emitido no mês validado com sucesso!");
				
				// Volta para tela inicial
				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
				getScreenshot().CaptureScreenshot();
			}
			else {
				closeAppium();
				fail("Falha validação da não cobrança de taxa do primeiro boleto emitido no mês.");
			}
		}
		else {
			if (saldoValido)	pass("Falha validação da não cobrança de taxa do primeiro boleto emitido no mês.");
			else {
				closeAppium();
				fail("Não cobrança taxa do primeiro boleto emitido no mês validado com sucesso!");
			}
		}
	}
}
