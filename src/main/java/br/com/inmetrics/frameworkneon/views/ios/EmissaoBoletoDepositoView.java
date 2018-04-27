package br.com.inmetrics.frameworkneon.views.ios;

import br.com.inmetrics.frameworkneon.Neon;
import br.com.inmetrics.frameworkneon.views.ios.HomeView;
import br.com.inmetrics.frameworkneon.views.ios.UtilView;
import br.com.inmetrics.neon.constants.Constants;
import io.appium.java_client.android.AndroidKeyCode;

public class EmissaoBoletoDepositoView extends Neon{
	
	private double limiteDiarioInicial;
	private double limiteDiarioFinal;
	private double saldoInicial;
	private double saldoFinal;
	HomeView homeView = new HomeView();
	UtilView utilView = new UtilView();
	
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

	}
	
	public void clicarBotaoCopiarLinhaDigitavel() throws Exception {

	}
	
	public double getLimiteDiario() throws NumberFormatException, Exception {
		double a = Double.parseDouble(getTextView().getAttributeById("br.com.neon:id/lblUsedLimits", "text").replace("$", "").replaceAll(" ", "").replaceAll("R", "").replace(".", "").replace(",", "."));
		return a;
	}
	
	public void clicarBotaoDepositoViaBoleto() throws Exception {

	}
	
	public void setValorDeposito (String valor) throws Exception {

	}
	
	public void clicarBotaoGerarBoleto() throws Exception {

	}
	
	public void fecharBoletoGerado() throws Exception {

	}

	public void validarBoletoNeonEmitidoDepositoValorAcimaLimite(boolean b) {
		// TODO Auto-generated method stub
		
	}

	public void validarBoletoNeonEmitidoDeposito(boolean b) {
		// TODO Auto-generated method stub
		
	}

	public void validarCopiaLinhaDigitavelBoleto(boolean b) {
		// TODO Auto-generated method stub
		
	}

	public void validarLinkBoletoGerado() {
		// TODO Auto-generated method stub
		
	}

	public void validarNaoCobrancaTaxaBoleto(boolean resultadoEsperado, String stringOf, String stringOf2) {
		// TODO Auto-generated method stub
		
	}

	public void validarReducaoLimiteDiarioEmissaoBoleto(boolean resultadoEsperado, String stringOf) {
		// TODO Auto-generated method stub
		
	}

	public void validarValorTarifaAposCompensacaoBoleto(boolean resultadoEsperado, String stringOf, String stringOf2) {
		// TODO Auto-generated method stub
		
	}
}
