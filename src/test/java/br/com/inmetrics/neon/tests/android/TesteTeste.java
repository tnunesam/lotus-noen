package br.com.inmetrics.neon.tests.android;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.provider.SyncStateContract.Constants;
import br.com.inmetrics.frameworkneon.Neon;
import br.com.inmetrics.frameworkneon.views.CartoesView;
import br.com.inmetrics.frameworkneon.views.EmissaoBoletoDepositoView;
import br.com.inmetrics.frameworkneon.views.HomeView;
import br.com.inmetrics.frameworkneon.views.LoginView;
import br.com.inmetrics.frameworkneon.views.PagamentosView;
import br.com.inmetrics.frameworkneon.views.SaldoView;
import br.com.inmetrics.frameworkneon.views.TesteView;
import br.com.inmetrics.frameworkneon.views.TransferenciasView;
import br.com.inmetrics.neon.constants.Devices;
import br.com.inmetrics.neon.lib.android.Generics;
import io.appium.java_client.android.AndroidKeyCode;

import static br.com.inmetrics.frameworkneon.Neon.initializeDrivers;
import static org.junit.Assert.*;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class TesteTeste {
	
	LoginView loginView = new LoginView();
	HomeView homeView = new HomeView();
	PagamentosView pagamentosView = new PagamentosView();
	TransferenciasView transfView = new TransferenciasView();
	CartoesView cartoesView = new CartoesView();
	SaldoView saldoView = new SaldoView();
	EmissaoBoletoDepositoView boletoView = new EmissaoBoletoDepositoView();
	
	public static void main(String[] args) {
//		double d = 9965.00;
//		double d2 = 0.01;
//		double d3 = d - d2;
//		DecimalFormat df = new DecimalFormat("###,###.00");
//		System.out.println(df.format(d));
		String teste = "teste";
		System.out.println(teste.substring(0, 2));
	}
	
//	@Test
//	public void ct_065_editarAgendamentoFuturoParaHojeComSucesso () {
//		pagamentosView.getDt().setFirstRow();
//		try {	
//			for (int i = 0; i < pagamentosView.getDt().getRowCount(); i++) {
//				if (pagamentosView.getDt().getStringOf("ID_CT").equals("CT_065")) 	break;
//				else																pagamentosView.getDt().setNextRow();
//			}
//			pagamentosView.getScreenshot().setNome("ct_065_editarAgendamentoFuturoParaHojeComSucesso");
////			loginView.logar(pagamentosView.getDt().getStringOf("EMAIL"), pagamentosView.getDt().getStringOf("SENHA_APP"));
//			pagamentosView.editarAgendamento(pagamentosView.getDt().getStringOf("PAGT_DATA_BOLETO"), true);
//			pagamentosView.validarAtualizacaoTotalAgendado(true, pagamentosView.getDt().getStringOf("PAGT_VALOR"));
//			loginView.logout();
//		} catch (Exception e) {
//			pagamentosView.closeAppium();
//			e.printStackTrace();
//		}
//	}
	
	@Before
	public void setUp() {
		try {
			pagamentosView.activeScreenshot();
			initializeDrivers(br.com.inmetrics.neon.constants.Constants.ID_DEVICE, br.com.inmetrics.neon.constants.Constants.APP_PACKAGE, br.com.inmetrics.neon.constants.Constants.APP_ACTIVITY);
			pagamentosView.initializeDataTable(br.com.inmetrics.neon.constants.Constants.PATH_DATATABLE);

		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	@After
	public void tearDown() throws Exception {
		
	}
	
	
	//@Test
	public void ct_062_efetuarPagamentoAgendadoHojeComSucesso () {
		pagamentosView.getDt().setFirstRow();
		try {				
			for (int i = 0; i < pagamentosView.getDt().getRowCount(); i++) {
				pagamentosView.getScreenshot().setNome("ct_063_efetuarAgendamentoPagamentoDataFuturaAntesVencimentoComSucesso");
				loginView.logar(pagamentosView.getDt().getStringOf("EMAIL"), pagamentosView.getDt().getStringOf("SENHA_APP"));
				pagamentosView.efetuarPagamentoAgendadoParaHoje();
				pagamentosView.validarBotaoPagamentoDisabled(true);
				loginView.logout();
				pagamentosView.getDt().setNextRow();
			}
		} catch (Exception e) {
			pagamentosView.closeAppium();
			e.printStackTrace();
		}
	}
	

	@Test
	public void testLoginOk() throws Exception{
			

		
		
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
//			loginView.logar(saldoView.getDt().getStringOf("EMAIL"), saldoView.getDt().getStringOf("SENHA_APP"));
			saldoView.setSaldoInicial(saldoView.getSaldo());
			pagamentosView.efetuarPagamentoBoletoCobranca(pagamentosView.getDt().getStringOf("PAGT_COD_BARRAS"), false, "");
			saldoView.validarAtualizacaoSaldoAposOperacao(true, saldoView.getDt().getStringOf("PAGT_VALOR"));
			loginView.logout();
		} catch (Exception e) {
			saldoView.closeAppium();
			fail("Falha ao atualizar Saldo da tela \"Saldo\".");
			e.printStackTrace();
		}
	}
}
