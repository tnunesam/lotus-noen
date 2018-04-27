package br.com.inmetrics.neon.tests.android;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static br.com.inmetrics.frameworkneon.Neon.*;

import br.com.inmetrics.frameworkneon.views.LoginView;
import br.com.inmetrics.neon.constants.Constants;
import br.com.inmetrics.neon.utilities.GenericDataTable;

import static org.junit.Assert.*;

public class LoginTeste  {
	
	LoginView loginView = new LoginView();
	
	//GenericDataTable dt = new GenericDataTable("C:\\Planilhas\\ParametrosNioin.xls");
	
	@Before
	public void setUp() {
		try {
			loginView.activeScreenshot();
			initializeDrivers(Constants.ID_DEVICE, Constants.APP_PACKAGE, Constants.APP_ACTIVITY);
			loginView.initializeDataTable(Constants.PATH_DATATABLE);

		}
		catch (Exception e){
			fail("Driver n�o iniciado");
			fail("Exception");
			loginView.closeAppium();
			e.printStackTrace();
		}
	}
	
	@After
	public void tearDown() throws Exception {
		loginView.closeAppium();
	}
	
	@Test
	public void ct_001_testLoginOk(){
		loginView.getDt().setFirstRow();
		try {
			for (int i = 0; i < loginView.getDt().getRowCount(); i++) {
				if (loginView.getDt().getStringOf("ID_CT").equals("CT_001")) 	break;
				else															loginView.getDt().setNextRow();
			}
			loginView.getScreenshot().setNome("ct_001_testLoginOk");
			loginView.logar(loginView.getDt().getStringOf("EMAIL"), loginView.getDt().getStringOf("SENHA_APP"));
			loginView.validarLoginSucesso(true);
		} catch (Exception e) {
			loginView.closeAppium();
			fail("Falha ao logar.");
			e.printStackTrace();
		}
	}
	
	@Test
	public void ct_002_testeLogoutOk() {
		loginView.getDt().setFirstRow();
		try {
			for (int i = 0; i < loginView.getDt().getRowCount(); i++) {
				if (loginView.getDt().getStringOf("ID_CT").equals("CT_002")) 	break;
				else															loginView.getDt().setNextRow();
			}
			loginView.getScreenshot().setNome("ct_002_testeLogoutOk");
			loginView.logar(loginView.getDt().getStringOf("EMAIL"), loginView.getDt().getStringOf("SENHA_APP"));
			loginView.logout();
			loginView.validarLogoutSucesso(true);
		} catch (Exception e) {
			loginView.closeAppium();
			fail("Falha fazer logout.");
			e.printStackTrace();
		}
	}
	
	@Test
	public void ct_050_sincronizarContatoFacebook() {
		loginView.getDt().setFirstRow();
		try {
			for (int i = 0; i < loginView.getDt().getRowCount(); i++) {
				if (loginView.getDt().getStringOf("ID_CT").equals("CT_050")) 	break;
				else															loginView.getDt().setNextRow();
			}
			loginView.getScreenshot().setNome("ct_050_sincronizarContatoFacebook");
			loginView.logar(loginView.getDt().getStringOf("EMAIL"), loginView.getDt().getStringOf("SENHA_APP"));
			loginView.sincronizarContatosFacebook(loginView.getDt().getStringOf("LOGIN_FACEBOOK"), loginView.getDt().getStringOf("SENHA_FACEBOOK"));
			loginView.validarContatoFacebook(true, loginView.getDt().getStringOf("TRANSF_FAVORECIDO"), loginView.getDt().getStringOf("TRANSF_CONTA_FAVORECIDO"));
			loginView.logout();
		} catch (Exception e) {
			loginView.closeAppium();
			fail("Contatos facebook sincronizado com falha.");
			e.printStackTrace();
		}
	}
	
	@Test
	public void ct_073_efetuarLoginEmailInexistente() {
		loginView.getDt().setFirstRow();
		try {
			for (int i = 0; i < loginView.getDt().getRowCount(); i++) {
				if (loginView.getDt().getStringOf("ID_CT").equals("CT_073")) 	break;
				else															loginView.getDt().setNextRow();
			}
			loginView.getScreenshot().setNome("ct_073_efetuarLoginEmailInexistente");
			loginView.logarEmailInexistente(loginView.getDt().getStringOf("EMAIL"), loginView.getDt().getStringOf("SENHA_APP"));
			loginView.validarLoginEmailInexistente(true);
		} catch (Exception e) {
			loginView.closeAppium();
			fail("Falha ao validar alerta de email invalido.");
			e.printStackTrace();
		}
	}
	
	@Test
	public void ct_074_efetuarLoginSenhaInvalida() {
		loginView.getDt().setFirstRow();
		try {
			for (int i = 0; i < loginView.getDt().getRowCount(); i++) {
				if (loginView.getDt().getStringOf("ID_CT").equals("CT_074")) 	break;
				else															loginView.getDt().setNextRow();
			}
			loginView.getScreenshot().setNome("ct_074_efetuarLoginSenhaInvalida");
			loginView.logarSenhaInvalida(loginView.getDt().getStringOf("EMAIL"), loginView.getDt().getStringOf("SENHA_APP"), false);
			loginView.validarLoginSenhaInvalida(true);
		} catch (Exception e) {
			loginView.closeAppium();
			fail("Falha ao validar alerta de email invalido.");
			e.printStackTrace();
		}
	}

//	@Test
	public void ct_075_efetuarLoginSenhaInvalida5Vezes() {
		loginView.getDt().setFirstRow();
		try {
			for (int i = 0; i < loginView.getDt().getRowCount(); i++) {
				if (loginView.getDt().getStringOf("ID_CT").equals("CT_075")) 	break;
				else															loginView.getDt().setNextRow();
			}
			loginView.getScreenshot().setNome("ct_075_efetuarLoginSenhaInvalida5Vezes");
			for (int j = 0; j < loginView.getDt().getRowCount(); j++) {
				if (j == 4) {
					loginView.logarSenhaInvalida(loginView.getDt().getStringOf("EMAIL"), loginView.getDt().getStringOf("SENHA_APP"), false);
					loginView.validarLoginSenhaInvalida(true);
					break;
				}
			}
			loginView.logarSenhaInvalida(loginView.getDt().getStringOf("EMAIL"), loginView.getDt().getStringOf("SENHA_APP"), true);
		} catch (Exception e) {
			loginView.closeAppium();
			fail("Falha ao validar alerta de senha invalido.");
			e.printStackTrace();
		}
	}

}
