package br.com.inmetrics.neon.tests.android;

import static br.com.inmetrics.frameworkneon.Neon.initializeDrivers;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.testdroid.api.sample.GetDevicesSample;

import br.com.inmetrics.frameworkneon.views.HomeView;
import br.com.inmetrics.frameworkneon.views.LoginView;
import br.com.inmetrics.frameworkneon.views.PagamentosView;
import br.com.inmetrics.frameworkneon.views.SaldoView;
import br.com.inmetrics.frameworkneon.views.TransferenciasView;
import br.com.inmetrics.neon.constants.Constants;

public class SaldoTeste {

	LoginView loginView = new LoginView();
	HomeView homeView = new HomeView();
	SaldoView saldoView = new SaldoView();
	TransferenciasView transfView = new TransferenciasView();
	PagamentosView pagamentoView = new PagamentosView();
	
	@Before
	public void setUp() {
		try {
			saldoView.activeScreenshot();
			initializeDrivers(Constants.ID_DEVICE, Constants.APP_PACKAGE, Constants.APP_ACTIVITY);
			saldoView.initializeDataTable(Constants.PATH_DATATABLE);

		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@After
	public void tearDown() throws Exception {
		transfView.closeAppium();
	}
	
	@Test
	public void ct_053_validarAtualizacaoSaldoAposTransferenciaComSucesso () {
		saldoView.getDt().setFirstRow();
		try {		
			for (int i = 0; i < transfView.getDt().getRowCount(); i++) {
				if (transfView.getDt().getStringOf("ID_CT").equals("CT_053")) 	break;
				else															transfView.getDt().setNextRow();
			}
			saldoView.getScreenshot().setNome("ct_053_validarAtualizacaoSaldoAposOperacaoComSucesso");
			loginView.logar(saldoView.getDt().getStringOf("EMAIL"), saldoView.getDt().getStringOf("SENHA_APP"));
			saldoView.setSaldoInicial(saldoView.getSaldo());
			transfView.efetuarTransferenciaContaNeon(	
					transfView.getDt().getStringOf("TRANSF_FAVORECIDO"),
					transfView.getDt().getStringOf("TRANSF_CONTA_FAVORECIDO"),
					transfView.getDt().getStringOf("TRANS_VALOR"),
					false,
					"");
			saldoView.validarAtualizacaoSaldoAposOperacao(true, saldoView.getDt().getStringOf("TRANS_VALOR"));
			loginView.logout();
	} catch (Exception e) {
			saldoView.closeAppium();
			fail("Falha ao atualizar Saldo da tela \"Saldo\".");
			e.printStackTrace();
		}
	}
	
	@Test
	public void ct_054_validarAtualizacaoSaldoAposPagamentoBoletoComSucesso () {
		saldoView.getDt().setFirstRow();
		try {			
			for (int i = 0; i < transfView.getDt().getRowCount(); i++) {
				if (transfView.getDt().getStringOf("ID_CT").equals("CT_054")) 	break;
				else															transfView.getDt().setNextRow();
			}
			saldoView.getScreenshot().setNome("ct_054_validarAtualizacaoSaldoAposPagamentoBoletoComSucesso");
			loginView.logar(saldoView.getDt().getStringOf("EMAIL"), saldoView.getDt().getStringOf("SENHA_APP"));
			saldoView.setSaldoInicial(saldoView.getSaldo());
			pagamentoView.efetuarPagamentoBoletoCobranca(pagamentoView.getDt().getStringOf("PAGT_COD_BARRAS"), false, "");
			saldoView.validarAtualizacaoSaldoAposOperacao(true, saldoView.getDt().getStringOf("PAGT_VALOR"));
			loginView.logout();
		} catch (Exception e) {
			saldoView.closeAppium();
			fail("Falha ao atualizar Saldo da tela \"Saldo\".");
			e.printStackTrace();
		}
	}
	
	@Test
	public void ct_061_validarExibicaoBoletoETransfTelaSaldoComSucesso () {
		saldoView.getDt().setFirstRow();
		try {			
			for (int i = 0; i < transfView.getDt().getRowCount(); i++) {
				if (transfView.getDt().getStringOf("ID_CT").equals("CT_061")) 	break;
				else															transfView.getDt().setNextRow();
			}
			saldoView.getScreenshot().setNome("ct_054_validarAtualizacaoSaldoAposPagamentoBoletoComSucesso");
			loginView.logar(saldoView.getDt().getStringOf("EMAIL"), saldoView.getDt().getStringOf("SENHA_APP"));
			saldoView.validarExistenciaBoletoNeonETransferencia(true);
			loginView.logout();
		} catch (Exception e) {
			saldoView.closeAppium();
			fail("Falha ao verificar Exibição de pagamentos na tela de saldo.");
			e.printStackTrace();
		}
	}
}
