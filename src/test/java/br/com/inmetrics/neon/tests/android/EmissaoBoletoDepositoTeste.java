package br.com.inmetrics.neon.tests.android;

import static br.com.inmetrics.frameworkneon.Neon.initializeDrivers;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.inmetrics.frameworkneon.views.EmissaoBoletoDepositoView;
import br.com.inmetrics.frameworkneon.views.HomeView;
import br.com.inmetrics.frameworkneon.views.LoginView;
import br.com.inmetrics.neon.constants.Constants;

public class EmissaoBoletoDepositoTeste {
	
	LoginView loginView = new LoginView();
	HomeView homeView = new HomeView();
	EmissaoBoletoDepositoView boletoView = new EmissaoBoletoDepositoView();
	
	@Before
	public void setUp() {
		try {
			boletoView.activeScreenshot();
			initializeDrivers(Constants.ID_DEVICE, Constants.APP_PACKAGE, Constants.APP_ACTIVITY);
			boletoView.initializeDataTable(Constants.PATH_DATATABLE);

		}
		catch (Exception e){
			fail("Driver n�o iniciado");
			fail("Exception");
			boletoView.closeAppium();
			e.printStackTrace();
		}
	}
	
	@After
	public void tearDown() throws Exception {
		boletoView.closeAppium();
	}
	
	@Test
	public void CT_030_efetuarEmissaoBoletoNeonParaDepositoAcimaLimiteDisponivel () {
		boletoView.getDt().setFirstRow();
		try {	
			for (int i = 0; i < boletoView.getDt().getRowCount(); i++) {
				if (boletoView.getDt().getStringOf("ID_CT").equals("CT_030")) 	break;
				else															boletoView.getDt().setNextRow();
			}
			boletoView.getScreenshot().setNome("ct_030_efetuarEmissaoBoletoNeonParaDepositoAcimaLimiteDisponivel");
			loginView.logar(boletoView.getDt().getStringOf("EMAIL"), boletoView.getDt().getStringOf("SENHA_APP"));
			boletoView.emissaoBoletoNeonDepositoAcimaLimite(boletoView.getDt().getStringOf("EMISSAO_BOLETO_VALOR"));
			boletoView.validarBoletoNeonEmitidoDepositoValorAcimaLimite(true);
			loginView.logout();
		} catch (Exception e) {
			boletoView.closeAppium();
			fail("Falha na emiss�o de boleto acima do limite disponível.");
			e.printStackTrace();
		}
	}

	@Test
	public void CT_031_efetuarEmissaoBoletoNeonParaDepositoComSucesso () {
		boletoView.getDt().setFirstRow();
		try {	
			for (int i = 0; i < boletoView.getDt().getRowCount(); i++) {
				if (boletoView.getDt().getStringOf("ID_CT").equals("CT_031")) 	break;
				else															boletoView.getDt().setNextRow();
			}
			boletoView.getScreenshot().setNome("CT_031_efetuarEmissaoBoletoNeonParaDepositoComSucesso");
			loginView.logar(boletoView.getDt().getStringOf("EMAIL"), boletoView.getDt().getStringOf("SENHA_APP"));
			boletoView.emissaoBoletoNeonDeposito(boletoView.getDt().getStringOf("EMISSAO_BOLETO_VALOR"));
			boletoView.validarBoletoNeonEmitidoDeposito(true);
			loginView.logout();
		} catch (Exception e) {
			boletoView.closeAppium();
			fail("Falha ao emitir boleto Neon de depósito.");
			e.printStackTrace();
		}
	}
	
	@Test
	public void CT_032_efetuarCopiaLinhaDigitavelBoletoCobrancaComSucesso () {
		boletoView.getDt().setFirstRow();
		try {		
			for (int i = 0; i < boletoView.getDt().getRowCount(); i++) {
				if (boletoView.getDt().getStringOf("ID_CT").equals("CT_032")) 	break;
				else															boletoView.getDt().setNextRow();
			}
			boletoView.getScreenshot().setNome("CT_032_efetuarCopiaLinhaDigitavelBoletoCobrancaComSucesso");
			loginView.logar(boletoView.getDt().getStringOf("EMAIL"), boletoView.getDt().getStringOf("SENHA_APP"));
			boletoView.efetuarCopiaLinhaDigitavelBoletoEmitido(boletoView.getDt().getStringOf("EMISSAO_BOLETO_VALOR"));
			boletoView.validarCopiaLinhaDigitavelBoleto(true);
			loginView.logout();
		} catch (Exception e) {
			boletoView.closeAppium();
			fail("Falha na copia da linha DIGITÁVEL do boleto.");
			e.printStackTrace();
		}
	}
	
	@Test
	public void CT_033_acessarLinkBoletoGeradoComSucesso () {
		boletoView.getDt().setFirstRow();
		try {	
			for (int i = 0; i < boletoView.getDt().getRowCount(); i++) {
				if (boletoView.getDt().getStringOf("ID_CT").equals("CT_033")) 	break;
				else															boletoView.getDt().setNextRow();
			}
			boletoView.getScreenshot().setNome("CT_033_acessarLinkBoletoGeradoComSucesso");
			loginView.logar(boletoView.getDt().getStringOf("EMAIL"), boletoView.getDt().getStringOf("SENHA_APP"));
			boletoView.acessarLinkBoletoGerado(boletoView.getDt().getStringOf("EMISSAO_BOLETO_VALOR"));
			boletoView.validarLinkBoletoGerado();
			loginView.logout();
		} catch (Exception e) {
			boletoView.closeAppium();
			fail("Falha ao tentar acessar link boleto.");
			e.printStackTrace();
		}
	}

	// Pré-requisito: 
	// 	- Ser primeiro boleto emitido no mês atual
	//	- Ter efetuado o pagamento de um boleto previamente
	//	- Saber saldo antes da transação para usar como parametro
//	@Test
	public void CT_035_validarNaoCobrancaTaxaPrimeiroBoletoMes () {
		boletoView.getDt().setFirstRow();
		try {		
			for (int i = 0; i < boletoView.getDt().getRowCount(); i++) {
				if (boletoView.getDt().getStringOf("ID_CT").equals("CT_035")) 	break;
				else															boletoView.getDt().setNextRow();
			}
			boletoView.getScreenshot().setNome("CT_035_validarNaoCobrancaTaxaPrimeiroBoletoMes");
			loginView.logar(boletoView.getDt().getStringOf("EMAIL"), boletoView.getDt().getStringOf("SENHA_APP"));
			boletoView.validarNaoCobrancaTaxaBoleto(true, 
										boletoView.getDt().getStringOf("EMISSAO_BOLETO_SALDO_INICIAL"),
										boletoView.getDt().getStringOf("EMISSAO_BOLETO_VALOR"));
			loginView.logout();
		} catch (Exception e) {
			boletoView.closeAppium();
			e.printStackTrace();
		}
	}
	
	@Test
	public void CT_036_validarReducaoLimiteDiarioAposEmissaoBoleto () {
		boletoView.getDt().setFirstRow();
		try {		
			for (int i = 0; i < boletoView.getDt().getRowCount(); i++) {
				if (boletoView.getDt().getStringOf("ID_CT").equals("CT_036")) 	break;
				else															boletoView.getDt().setNextRow();
			}
			boletoView.getScreenshot().setNome("CT_036_validarReducaoLimiteDiarioAposEmissaoBoleto");
			loginView.logar(boletoView.getDt().getStringOf("EMAIL"), boletoView.getDt().getStringOf("SENHA_APP"));
			boletoView.emissaoBoletoNeonDeposito(boletoView.getDt().getStringOf("EMISSAO_BOLETO_VALOR"));
			boletoView.validarReducaoLimiteDiarioEmissaoBoleto(true, boletoView.getDt().getStringOf("EMISSAO_BOLETO_VALOR"));
			loginView.logout();
		} catch (Exception e) {
			boletoView.closeAppium();
			fail("Falha na redu��o de limite di�rio de emiss�o de boleto.");
			e.printStackTrace();
		}
	}
	
	@Test
	public void CT_037_validarNotificacaoBoletoEmitido () {
		boletoView.getDt().setFirstRow();
		try {			
			for (int i = 0; i < boletoView.getDt().getRowCount(); i++) {
				if (boletoView.getDt().getStringOf("ID_CT").equals("CT_037")) 	break;
				else															boletoView.getDt().setNextRow();
			}
			boletoView.getScreenshot().setNome("CT_037_validarNotificacaoBoletoEmitido");
			loginView.logar(boletoView.getDt().getStringOf("EMAIL"), boletoView.getDt().getStringOf("SENHA_APP"));
			boletoView.emissaoBoletoNeonDeposito(boletoView.getDt().getStringOf("EMISSAO_BOLETO_VALOR"));
			boletoView.validarBoletoNeonEmitidoDeposito(true);
			loginView.logout();
		} catch (Exception e) {
			boletoView.closeAppium();
			fail("Falha ao emitir boleto Neon de dep�sito.");
			e.printStackTrace();
		}
	}
	
//	@Test
	public void CT_044_validarValorTarifaBoleto() {
		boletoView.getDt().setFirstRow();
		try {		
			for (int i = 0; i < boletoView.getDt().getRowCount(); i++) {
				if (boletoView.getDt().getStringOf("ID_CT").equals("CT_044")) 	break;
				else															boletoView.getDt().setNextRow();
			}
			boletoView.getScreenshot().setNome("CT_060_validarNaoCobrancaTaxaPrimeiroBoletoMes");
			loginView.logar(boletoView.getDt().getStringOf("EMAIL"), boletoView.getDt().getStringOf("SENHA_APP"));
			boletoView.validarValorTarifaAposCompensacaoBoleto(true, 
										boletoView.getDt().getStringOf("EMISSAO_BOLETO_SALDO_INICIAL"),
										boletoView.getDt().getStringOf("EMISSAO_BOLETO_VALOR"));
			loginView.logout();
		} catch (Exception e) {
			boletoView.closeAppium();
			e.printStackTrace();
		}
	}
	
	// Pré-requisito: 
	// 	- Ser o primeiro boelto emitido no mês atual
	//	- Ter efetuado o pagamento de um boleto previamente
	//	- Saber saldo antes da transação para usar como parametro, pois compensação só será feita no dia seguinte
//	@Test
	public void CT_060_validarNaoCobrancaTaxaPrimeiroBoletoMes () {
		boletoView.getDt().setFirstRow();
		try {
			for (int i = 0; i < boletoView.getDt().getRowCount(); i++) {
				if (boletoView.getDt().getStringOf("ID_CT").equals("CT_060")) 	break;
				else															boletoView.getDt().setNextRow();
			}
			boletoView.getScreenshot().setNome("CT_060_validarNaoCobrancaTaxaPrimeiroBoletoMes");
			loginView.logar(boletoView.getDt().getStringOf("EMAIL"), boletoView.getDt().getStringOf("SENHA_APP"));
			boletoView.validarNaoCobrancaTaxaBoleto(true, 
										boletoView.getDt().getStringOf("EMISSAO_BOLETO_SALDO_INICIAL"),
										boletoView.getDt().getStringOf("EMISSAO_BOLETO_VALOR"));
			loginView.logout();
		} catch (Exception e) {
			boletoView.closeAppium();
			e.printStackTrace();
		}
	}
}
