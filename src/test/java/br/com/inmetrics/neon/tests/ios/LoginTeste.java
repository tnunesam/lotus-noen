package br.com.inmetrics.neon.tests.ios;

import static br.com.inmetrics.frameworkneon.NeonIOS.initializeDriversIOS;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.inmetrics.frameworkneon.views.ios.LoginView;
import br.com.inmetrics.neon.constants.Constants;
import br.com.inmetrics.neon.utilities.GenericDataTable;

public class LoginTeste {
	
	LoginView loginView = new LoginView();
	
	GenericDataTable dt = new GenericDataTable("C:\\Planilhas\\ParametrosNioin.xls");
	
	@Before
	public void setUp() {
		try {
			loginView.activeScreenshot();
			initializeDriversIOS(Constants.APP_PATH, Constants.OS_VERSION, Constants.DEVICE_NAME, Constants.UDID);
//			loginView.initializeDataTable(Constants.PATH_DATATABLE);
		}
		catch (Exception e){
			fail("Driver não iniciado");
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
				if (dt.getStringOf("ID_CT").equals("CT_001")) 	break;
				else											dt.setNextRow();
			}
			loginView.getScreenshot().setNome("ct_001_testLoginOk");
			loginView.logar(loginView.getDt().getStringOf("EMAIL"), loginView.getDt().getStringOf("SENHA_APP"));
			loginView.validarLoginSucesso(true);
			loginView.closeAppium();
		} catch (Exception e) {
			loginView.closeAppium();
			e.printStackTrace();
		}
	}
	
	@Test
	public void ct_002_testeLogoutOk() {
		loginView.getDt().setFirstRow();
		try {
			for (int i = 0; i < loginView.getDt().getRowCount(); i++) {
				if (dt.getStringOf("ID_CT").equals("CT_002")) 	break;
				else											dt.setNextRow();
			}
				loginView.getScreenshot().setNome("ct_002_testeLogoutOk");
				loginView.logar(dt.getStringOf("EMAIL"), dt.getStringOf("SENHA_APP"));
				loginView.logout();
				loginView.validarLogoutSucesso(true);
				loginView.closeAppium();
		} catch (Exception e) {
				loginView.closeAppium();
				e.printStackTrace();
		}
	}
	
//	@Test
	public void ct_050_sincronizarContatoFacebook() {
		for (int i = 0; i < dt.getRowCount(); i++) {
			try {
				loginView.getScreenshot().setNome("ct_002_testeLogoutOk");
				loginView.logar(dt.getStringOf("EMAIL"), dt.getStringOf("SENHA_APP"));
				loginView.sincronizarContatosFacebook(dt.getStringOf("LOGIN_FACEBOOK"), dt.getStringOf("SENHA_FACEBOOK"));
//				loginView.validarContatoFacebook(true, dt.getStringOf("TRANSF_FAVORECIDO"), dt.getStringOf("TRANSF_CONTA_FAVORECIDO"));
				loginView.closeAppium();
			} catch (Exception e) {
				loginView.closeAppium();
				e.printStackTrace();
			}
		}
	}
	
	@Test
	public void ct_073_efetuarLoginEmailInexistente() {
		loginView.getDt().setFirstRow();
		try {
			for (int i = 0; i < loginView.getDt().getRowCount(); i++) {
				if (dt.getStringOf("ID_CT").equals("CT_073")) 	break;
				else											dt.setNextRow();
			}
				loginView.getScreenshot().setNome("ct_073_efetuarLoginEmailInexistente");
				loginView.logarEmailInexistente(dt.getStringOf("EMAIL"), dt.getStringOf("SENHA_APP"));
				loginView.validarLoginEmailInexistente(true);
				loginView.closeAppium();
		} catch (Exception e) {
				loginView.closeAppium();
				e.printStackTrace();
		}
	}
	
	@Test
	public void ct_074_efetuarLoginSenhaInvalida() {
		loginView.getDt().setFirstRow();
		try {
			for (int i = 0; i < loginView.getDt().getRowCount(); i++) {
				if (dt.getStringOf("ID_CT").equals("CT_074")) 	break;
				else											dt.setNextRow();
			}
				loginView.getScreenshot().setNome("ct_074_efetuarLoginSenhaInvalida");
				loginView.logarSenhaInvalida(dt.getStringOf("EMAIL"), dt.getStringOf("SENHA_APP"), false);
				loginView.validarLoginSenhaInvalida(true);
				loginView.closeAppium();
		} catch (Exception e) {
				loginView.closeAppium();
				e.printStackTrace();
		}
	}

//	@Test
	public void ct_075_efetuarLoginSenhaInvalida5Vezes() {
		for (int i = 0; i < 5; i++) {
			try {
				loginView.getScreenshot().setNome("ct_075_efetuarLoginSenhaInvalida5Vezes");
				if (i == 4) {
					loginView.logarSenhaInvalida(dt.getStringOf("EMAIL"), dt.getStringOf("SENHA_APP"), false);
//					loginView.validarLoginSenhaInvalida(true);
					loginView.closeAppium();
					break;
				}
				loginView.logarSenhaInvalida(dt.getStringOf("EMAIL"), dt.getStringOf("SENHA_APP"), true);
			} catch (Exception e) {
				loginView.closeAppium();
				e.printStackTrace();
			}
		}
	}
}
