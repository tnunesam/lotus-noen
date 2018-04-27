package br.com.inmetrics.neon.tests.android;

import static br.com.inmetrics.frameworkneon.Neon.initializeDrivers;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.inmetrics.frameworkneon.views.CartoesView;
import br.com.inmetrics.frameworkneon.views.HomeView;
import br.com.inmetrics.frameworkneon.views.LoginView;
import br.com.inmetrics.frameworkneon.views.PagamentosView;
import br.com.inmetrics.neon.constants.Constants;

public class CartoesTeste {

	LoginView loginView = new LoginView();
	HomeView homeView = new HomeView();
	CartoesView cartoesView = new CartoesView();
	
	@Before
	public void setUp() {
		try {
			cartoesView.activeScreenshot();
			initializeDrivers(Constants.ID_DEVICE, Constants.APP_PACKAGE, Constants.APP_ACTIVITY);
			cartoesView.initializeDataTable(Constants.PATH_DATATABLE);

		}
		catch (Exception e){
			fail("Driver não iniciado");
			fail("Exception");
			cartoesView.closeAppium();
			e.printStackTrace();
		}
	}
	
	@After
	public void tearDown() throws Exception {
		cartoesView.closeAppium();
	}
	
	@Test
	public void ct_038_efetuarBloqueioTemporarioCartaoVirtual () {
		cartoesView.getDt().setFirstRow();
		try {	
			for (int i = 0; i < cartoesView.getDt().getRowCount(); i++) {
				if (cartoesView.getDt().getStringOf("ID_CT").equals("CT_038")) 	break;
				else															cartoesView.getDt().setNextRow();
			}
			cartoesView.getScreenshot().setNome("ct_038_efetuarBloqueioTemporarioCartaoVirtual");
			loginView.logar(cartoesView.getDt().getStringOf("EMAIL"), cartoesView.getDt().getStringOf("SENHA_APP"));
			cartoesView.bloquearCartaoVirtualTemporario();
			cartoesView.validarCartaoVirtualBloqueadoTemporario(true);
			loginView.logout();
		} catch (Exception e) {
			cartoesView.closeAppium();
			fail("Falha ao bloquear cartão.");
			e.printStackTrace();
		}
	}
	
	@Test
	public void ct_039_efetuarDesbloqueioTemporarioCartaoVirtual () {
		cartoesView.getDt().setFirstRow();
		try {		
			for (int i = 0; i < cartoesView.getDt().getRowCount(); i++) {
				if (cartoesView.getDt().getStringOf("ID_CT").equals("CT_039")) 	break;
				else															cartoesView.getDt().setNextRow();
			}
			cartoesView.getScreenshot().setNome("ct_039_efetuarDesbloqueioTemporarioCartaoVirtual");
			loginView.logar(cartoesView.getDt().getStringOf("EMAIL"), cartoesView.getDt().getStringOf("SENHA_APP"));
			cartoesView.desbloquearCartaoVirtualTemporario();
			cartoesView.validarCartaoVirtualBloqueadoTemporario(true);
			loginView.logout();
		} catch (Exception e) {
			cartoesView.closeAppium();
			fail("Falha ao bloquear cartão.");
			e.printStackTrace();
		}
	}
	
	@Test
	public void ct_040_validarNotificacaoBloqueioCartaoVirtual () {
		cartoesView.getDt().setFirstRow();
		try {	
			for (int i = 0; i < cartoesView.getDt().getRowCount(); i++) {
				if (cartoesView.getDt().getStringOf("ID_CT").equals("CT_040")) 	break;
				else															cartoesView.getDt().setNextRow();
			}
			cartoesView.getScreenshot().setNome("ct_040_validarNotificacaoBloqueioCartaoVirtual");
			loginView.logar(cartoesView.getDt().getStringOf("EMAIL"), cartoesView.getDt().getStringOf("SENHA_APP"));
			cartoesView.bloquearCartaoVirtualTemporario();
			cartoesView.validarNotificacaoBloqueioCartaoVirtual(true);
			loginView.logout();
		} catch (Exception e) {
			cartoesView.closeAppium();
			fail("Falha ao bloquear cartão.");
			e.printStackTrace();
		}
	}
	
	@Test
	public void ct_041_validarNotificacaoDesbloqueioCartaoVirtual () {
		cartoesView.getDt().setFirstRow();
		try {	
			for (int i = 0; i < cartoesView.getDt().getRowCount(); i++) {
				if (cartoesView.getDt().getStringOf("ID_CT").equals("CT_041")) 	break;
				else															cartoesView.getDt().setNextRow();
			}
			cartoesView.getScreenshot().setNome("ct_041_validarNotificacaoDesbloqueioCartaoVirtual");
			loginView.logar(cartoesView.getDt().getStringOf("EMAIL"), cartoesView.getDt().getStringOf("SENHA_APP"));
			cartoesView.desbloquearCartaoVirtualTemporario();
			cartoesView.validarNotificacaoDesbloqueioCartaoVirtual(true);
			loginView.logout();
		} catch (Exception e) {
			cartoesView.closeAppium();
			fail("Falha ao tentar validar Notificação de desbloqueio de cartão virtual.");
			e.printStackTrace();
		}
	}
	
	@Test
	public void ct_042_validarCopiaNumeroCartaoVirtual () {
		cartoesView.getDt().setFirstRow();
		try {			
			for (int i = 0; i < cartoesView.getDt().getRowCount(); i++) {
				if (cartoesView.getDt().getStringOf("ID_CT").equals("CT_042")) 	break;
				else															cartoesView.getDt().setNextRow();
			}
			cartoesView.getScreenshot().setNome("ct_042_validarCopiaNumeroCartaoVirtual");
			loginView.logar(cartoesView.getDt().getStringOf("EMAIL"), cartoesView.getDt().getStringOf("SENHA_APP"));
			cartoesView.copiarNumeroCartaoVirtual();
			cartoesView.validarCopiaNumeroCartaoVirtual(true);
			loginView.logout();
		} catch (Exception e) {
			cartoesView.closeAppium();
			fail("Falha ao tentar copiar numero do cartão virtual.");
			e.printStackTrace();
		}
	}
}
