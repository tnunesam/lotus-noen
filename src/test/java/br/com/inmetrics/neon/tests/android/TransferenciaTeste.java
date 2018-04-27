package br.com.inmetrics.neon.tests.android;

import static br.com.inmetrics.frameworkneon.Neon.initializeDrivers;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.inmetrics.frameworkneon.Neon;
import br.com.inmetrics.frameworkneon.views.HomeView;
import br.com.inmetrics.frameworkneon.views.LoginView;
import br.com.inmetrics.frameworkneon.views.TransferenciasView;
import br.com.inmetrics.neon.constants.Constants;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TransferenciaTeste {
	
	LoginView loginView = new LoginView();
	HomeView homeView = new HomeView();
	TransferenciasView transfView = new TransferenciasView();

	@Before
	public void setUp() {
		try {
			transfView.activeScreenshot();
			initializeDrivers(Constants.ID_DEVICE, Constants.APP_PACKAGE, Constants.APP_ACTIVITY);
			transfView.initializeDataTable(Constants.PATH_DATATABLE);
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
	public void ct_003_transferenciaEntreContasNeonAcimaSaldoDisponivelComSucesso () {
		transfView.getDt().setFirstRow();
		try {
			for (int i = 0; i < transfView.getDt().getRowCount(); i++) {
				if (transfView.getDt().getStringOf("ID_CT").equals("CT_003")) 	break;
				else															transfView.getDt().setNextRow();
			}
			transfView.getScreenshot().setNome("ct_003_transferenciaEntreContasNeonAcimaSaldoDisponivelComSucesso");
			loginView.logar(transfView.getDt().getStringOf("EMAIL"), transfView.getDt().getStringOf("SENHA_APP"));
			transfView.efetuarTransferenciaContaNeonAcimaSaldoLimiteDisponivel(	
												transfView.getDt().getStringOf("TRANSF_FAVORECIDO"),
												transfView.getDt().getStringOf("TRANSF_CONTA_FAVORECIDO"),
												transfView.getDt().getStringOf("TRANS_VALOR"));
			transfView.validarTransfAcimaSaldoDisponivel(true);
			loginView.logout();
		} catch (Exception e) {
			transfView.closeAppium();
			fail("Falha na validação de mensagem de valor fora do limite.");
			e.printStackTrace();
		}
	}
	
	@Test
	public void ct_004_transferenciaEntreContasNeonComSucesso () {
		transfView.getDt().setFirstRow();
		try {				
			for (int i = 0; i < transfView.getDt().getRowCount(); i++) {
				if (transfView.getDt().getStringOf("ID_CT").equals("CT_004")) 	break;
				else															transfView.getDt().setNextRow();
			}
			
			transfView.getScreenshot().setNome("ct_004_transferenciaEntreContasNeonComSucesso");
			loginView.logar(transfView.getDt().getStringOf("EMAIL"), transfView.getDt().getStringOf("SENHA_APP"));
			transfView.efetuarTransferenciaContaNeon(	
												transfView.getDt().getStringOf("TRANSF_FAVORECIDO"),
												transfView.getDt().getStringOf("TRANSF_CONTA_FAVORECIDO"),
												transfView.getDt().getStringOf("TRANS_VALOR"),
												false,
												"");
			transfView.validaSaldoTransferencia(true, transfView.getDt().getStringOf("TRANS_VALOR"));
			loginView.logout();
		} catch (Exception e) {
			transfView.closeAppium();
			fail("Falha ao efetuar transferência entre contas Neon.");
			e.printStackTrace();
		}
	}
	
	@Test
	public void ct_005_transferenciaOutrosBancosMesmoCPFAcimaSaldoDisponComSucesso() {
		transfView.getDt().setFirstRow();
		try {				
			for (int i = 0; i < transfView.getDt().getRowCount(); i++) {
				if (transfView.getDt().getStringOf("ID_CT").equals("CT_005")) 	break;
				else															transfView.getDt().setNextRow();
			}
			transfView.getScreenshot().setNome("ct_005_transferenciaOutrosBancosMesmoCPFAcimaSaldoDisponComSucesso");
			loginView.logar(transfView.getDt().getStringOf("EMAIL"), transfView.getDt().getStringOf("SENHA_APP"));
			transfView.efetuarTransferenciaOutrosBancos(
					transfView.getDt().getStringOf("TRANSF_FAVORECIDO"),
					transfView.getDt().getStringOf("TRANS_CPF_CNPJ"), 
					transfView.getDt().getStringOf("TRANS_BANCO"), 
					transfView.getDt().getStringOf("TRANS_AGENCIA"), 
					transfView.getDt().getStringOf("TRANS_CONTA"), 
					transfView.getDt().getStringOf("TRANS_TIPO_CONTA"),
					transfView.getDt().getStringOf("TRANS_VALOR"));
			transfView.validarTransfAcimaValorLimiteDiario(true);
			loginView.logout();
		} catch (Exception e) {
			transfView.closeAppium();
			fail("Falha na validação de mensagem de valor fora do limite.");
			e.printStackTrace();
		}
	}

	
	@Test
	public void ct_006_transferenciaOutrosBancosMesmoCPFComSucesso () {
		transfView.getDt().setFirstRow();
		try {	
			for (int i = 0; i < transfView.getDt().getRowCount(); i++) {
				if (transfView.getDt().getStringOf("ID_CT").equals("CT_006")) 	break;
				else															transfView.getDt().setNextRow();
			}
			transfView.getScreenshot().setNome("ct_006_transferenciaOutrosBancosMesmoCPFComSucesso");
			loginView.logar(transfView.getDt().getStringOf("EMAIL"), transfView.getDt().getStringOf("SENHA_APP"));
			transfView.efetuarTransferenciaOutrosBancos(
					transfView.getDt().getStringOf("TRANSF_FAVORECIDO"),
					transfView.getDt().getStringOf("TRANS_CPF_CNPJ"), 
					transfView.getDt().getStringOf("TRANS_BANCO"), 
					transfView.getDt().getStringOf("TRANS_AGENCIA"), 
					transfView.getDt().getStringOf("TRANS_CONTA"), 
					transfView.getDt().getStringOf("TRANS_TIPO_CONTA"),
					transfView.getDt().getStringOf("TRANS_VALOR"));
			transfView.validaSaldoTransferencia(true, transfView.getDt().getStringOf("TRANS_VALOR"));
			loginView.logout();
		} catch (Exception e) {
			transfView.closeAppium();
			fail("Falha ao efetuar transferência entre contas Neon.");
			e.printStackTrace();
		}
	}

	@Test
	public void ct_007_transferenciaOutrosBancosCPFDiferenteAcimaLimiteDiarioComSucesso () {
		transfView.getDt().setFirstRow();
		try {
			for (int i = 0; i < transfView.getDt().getRowCount(); i++) {
				if (transfView.getDt().getStringOf("ID_CT").equals("CT_007")) 	break;
				else															transfView.getDt().setNextRow();
			}
			transfView.getScreenshot().setNome("ct_007_transferenciaOutrosBancosCPFDiferenteAcimaLimiteDiarioComSucesso");
			loginView.logar(transfView.getDt().getStringOf("EMAIL"), transfView.getDt().getStringOf("SENHA_APP"));
			transfView.efetuarTransferenciaOutrosBancosAcimaLimiteDiario(
												transfView.getDt().getStringOf("TRANSF_FAVORECIDO"),
												transfView.getDt().getStringOf("TRANS_CPF_CNPJ"), 
												transfView.getDt().getStringOf("TRANS_BANCO"),
												transfView.getDt().getStringOf("TRANS_AGENCIA"), 
												transfView.getDt().getStringOf("TRANS_CONTA"), 
												transfView.getDt().getStringOf("TRANS_TIPO_CONTA"), 
												transfView.getDt().getStringOf("TRANS_VALOR"));
			transfView.validarTransfAcimaValorLimiteDiario(true);
			loginView.logout();
		} catch (Exception e) {
			transfView.closeAppium();
			fail("Falha na validação de mensagem de valor fora do limite.");
			e.printStackTrace();
		}
	}
	
	@Test
	public void ct_008_transferenciaOutrosBancosComCPFDiferenteSucesso () {
		transfView.getDt().setFirstRow();
		try {				
			for (int i = 0; i < transfView.getDt().getRowCount(); i++) {
				if (transfView.getDt().getStringOf("ID_CT").equals("CT_008")) 	break;
				else															transfView.getDt().setNextRow();
			}
			transfView.getScreenshot().setNome("ct_008_transferenciaOutrosBancosComCPFDiferenteSucesso");
			loginView.logar(transfView.getDt().getStringOf("EMAIL"), transfView.getDt().getStringOf("SENHA_APP"));
			transfView.efetuarTransferenciaOutrosBancos(
												transfView.getDt().getStringOf("TRANSF_FAVORECIDO"),
												transfView.getDt().getStringOf("TRANS_CPF_CNPJ"), 
												transfView.getDt().getStringOf("TRANS_BANCO"),
												transfView.getDt().getStringOf("TRANS_AGENCIA"), 
												transfView.getDt().getStringOf("TRANS_CONTA"), 
												transfView.getDt().getStringOf("TRANS_TIPO_CONTA"), 
												transfView.getDt().getStringOf("TRANS_VALOR"));
			transfView.validaSaldoTransferencia(true, transfView.getDt().getStringOf("TRANS_VALOR"));
			loginView.logout();
		} catch (Exception e) {
			transfView.closeAppium();
			fail("Falha ao efetuar transferência entre contas Neon.");
			e.printStackTrace();
		}
	}
	
	@Test
	public void ct_009_validarMensagemTransfComMesmoValorComSucesso () {
		transfView.getDt().setFirstRow();
		try {		
			for (int i = 0; i < transfView.getDt().getRowCount(); i++) {
				if (transfView.getDt().getStringOf("ID_CT").equals("CT_009")) 	break;
				else															transfView.getDt().setNextRow();
			}
			transfView.getScreenshot().setNome("ct_009_validarMensagemTransfComMesmoValorComSucesso");
			loginView.logar(transfView.getDt().getStringOf("EMAIL"), transfView.getDt().getStringOf("SENHA_APP"));
			transfView.efetuarTransferenciaContaNeon(	
												transfView.getDt().getStringOf("TRANSF_FAVORECIDO"),
												transfView.getDt().getStringOf("TRANSF_CONTA_FAVORECIDO"),
												transfView.getDt().getStringOf("TRANS_VALOR"),
												false,
												"");
			transfView.efetuarTransferenciaContaNeonRepetida(
												transfView.getDt().getStringOf("TRANSF_FAVORECIDO"),
												transfView.getDt().getStringOf("TRANSF_CONTA_FAVORECIDO"),
												transfView.getDt().getStringOf("TRANS_VALOR"));
			transfView.validarTransferenciaRepetida(true);
			loginView.logout();
		} catch (Exception e) {
			transfView.closeAppium();
			fail("transferência repetida sem sucesso.");
			e.printStackTrace();
		}
	}
	
	@Test
	public void ct_010_criacaoNovoContatoComSucesso () {
		transfView.getDt().setFirstRow();
		try {	
			for (int i = 0; i < transfView.getDt().getRowCount(); i++) {
				if (transfView.getDt().getStringOf("ID_CT").equals("CT_010")) 	break;
				else															transfView.getDt().setNextRow();
			}
			transfView.getScreenshot().setNome("ct_010_criacaoNovoContatoComSucesso");
			loginView.logar(transfView.getDt().getStringOf("EMAIL"), transfView.getDt().getStringOf("SENHA_APP"));
			transfView.addContato(
					transfView.getDt().getStringOf("TRANS_CPF_CNPJ"),
					transfView.getDt().getStringOf("TRANS_BANCO"),
					transfView.getDt().getStringOf("TRANS_AGENCIA"),
					transfView.getDt().getStringOf("TRANS_CONTA"),
					transfView.getDt().getStringOf("TRANS_TIPO_CONTA"));
			transfView.validaCriacaoNovoContato(true, transfView.getDt().getStringOf("TRANS_NOME_FAVORECIDO"));
			loginView.logout();
		} catch (Exception e) {
			transfView.closeAppium();
			fail("Falha ao tentar adicionar um novo contato.");
			e.printStackTrace();
		}
	}
	
	@Test
	public void ct_011_alterarNomeContatoComSucesso () {
		transfView.getDt().setFirstRow();
		try {	
			for (int i = 0; i < transfView.getDt().getRowCount(); i++) {
				if (transfView.getDt().getStringOf("ID_CT").equals("CT_011")) 	break;
				else															transfView.getDt().setNextRow();
			}
			transfView.getScreenshot().setNome("ct_011_alterarNomeContatoComSucesso");
			loginView.logar(transfView.getDt().getStringOf("EMAIL"), transfView.getDt().getStringOf("SENHA_APP"));
			transfView.alterarNomeContato(transfView.getDt().getStringOf("TRANSF_FAVORECIDO"), transfView.getDt().getStringOf("TRANSF_FAVORECIDO") + "z");
			transfView.validaAlteracaoContato(true, transfView.getDt().getStringOf("TRANSF_FAVORECIDO") + "z");
			loginView.logout();
		} catch (Exception e) {
			transfView.closeAppium();
			fail("Falha ao tentar alterar contato.");
			e.printStackTrace();
		}
	}
	
	@Test
	public void ct_012_exlcuirContatoComSucesso () {
		transfView.getDt().setFirstRow();
		try {	
			for (int i = 0; i < transfView.getDt().getRowCount(); i++) {
				if (transfView.getDt().getStringOf("ID_CT").equals("CT_012")) 	break;
				else															transfView.getDt().setNextRow();
			}
			transfView.getScreenshot().setNome("ct_012_exlcuirContatoComSucesso");
			loginView.logar(transfView.getDt().getStringOf("EMAIL"), transfView.getDt().getStringOf("SENHA_APP"));
			transfView.excluirContato(transfView.getDt().getStringOf("TRANSF_FAVORECIDO"));
			transfView.validaContatoExcluido(true, transfView.getDt().getStringOf("TRANSF_FAVORECIDO"));
			loginView.logout();
		} catch (Exception e) {
			transfView.closeAppium();
			fail("Falha ao tentar excluir um contato.");
			e.printStackTrace();
		}
	}
	
	@Test
	public void ct_013_criacaoNovaContaParaContatoComSucesso () {
		transfView.getDt().setFirstRow();
		try {	
			for (int i = 0; i < transfView.getDt().getRowCount(); i++) {
				if (transfView.getDt().getStringOf("ID_CT").equals("CT_013")) 	break;
				else															transfView.getDt().setNextRow();
			}
			transfView.getScreenshot().setNome("ct_013_criacaoNovaContaParaContatoComSucesso");
			loginView.logar(transfView.getDt().getStringOf("EMAIL"), transfView.getDt().getStringOf("SENHA_APP"));
			transfView.criarNovaContaContato(
					transfView.getDt().getStringOf("TRANSF_FAVORECIDO"),
					transfView.getDt().getStringOf("TRANS_BANCO"),
					transfView.getDt().getStringOf("TRANS_AGENCIA"),
					transfView.getDt().getStringOf("TRANS_CONTA"),
					transfView.getDt().getStringOf("TRANS_TIPO_CONTA"));
			transfView.validarAdicaoContaContato(true, 
					transfView.getDt().getStringOf("TRANSF_FAVORECIDO"), 
					transfView.getDt().getStringOf("TRANS_CONTA"));
			loginView.logout();
		} catch (Exception e) {
			transfView.closeAppium();
			fail("Falha ao tentar adicionar conta para contato.");
			e.printStackTrace();
		}
	}
	
	@Test
	public void ct_014_exclusaoContaParaContatoComSucesso () {
		transfView.getDt().setFirstRow();
		try {	
			for (int i = 0; i < transfView.getDt().getRowCount(); i++) {
				if (transfView.getDt().getStringOf("ID_CT").equals("CT_014")) 	break;
				else															transfView.getDt().setNextRow();
			}
			transfView.getScreenshot().setNome("ct_014_exclusaoContaParaContatoComSucesso");
			loginView.logar(transfView.getDt().getStringOf("EMAIL"), transfView.getDt().getStringOf("SENHA_APP"));
			transfView.excluirContaContato(transfView.getDt().getStringOf("TRANSF_FAVORECIDO"),
					transfView.getDt().getStringOf("TRANS_CONTA"));
			transfView.validarExclusaoConta(true, 
					transfView.getDt().getStringOf("TRANSF_FAVORECIDO"),
					transfView.getDt().getStringOf("TRANS_CONTA"));
			loginView.logout();
		} catch (Exception e) {
			transfView.closeAppium();
			fail("Falha ao tentar excluir conta para contato.");
			e.printStackTrace();
		}
	}
	
	@Test
	public void ct_015_transferenciaParaContatoComSucesso () {
		transfView.getDt().setFirstRow();
		try {		
			for (int i = 0; i < transfView.getDt().getRowCount(); i++) {
				if (transfView.getDt().getStringOf("ID_CT").equals("CT_015")) 	break;
				else															transfView.getDt().setNextRow();
			}
			transfView.getScreenshot().setNome("ct_015_transferenciaParaContatoComSucesso");
			loginView.logar(transfView.getDt().getStringOf("EMAIL"), transfView.getDt().getStringOf("SENHA_APP"));
			transfView.efetuarTransferenciaContaNeon(	
												transfView.getDt().getStringOf("TRANSF_FAVORECIDO"),
												transfView.getDt().getStringOf("TRANSF_CONTA_FAVORECIDO"),
												transfView.getDt().getStringOf("TRANS_VALOR"),
												false,
												"");
			transfView.validaSaldoTransferencia(true, transfView.getDt().getStringOf("TRANS_VALOR"));
			loginView.logout();
		} catch (Exception e) {
			transfView.closeAppium();
			fail("Falha ao efetuar transferência entre contas Neon.");
			e.printStackTrace();
		}
	}
	
	@Test
	public void ct_016_efetuarTransfNovoContatoComSucesso () {
		transfView.getDt().setFirstRow();
		try {	
			for (int i = 0; i < transfView.getDt().getRowCount(); i++) {
				if (transfView.getDt().getStringOf("ID_CT").equals("CT_016")) 	break;
				else															transfView.getDt().setNextRow();
			}
			transfView.getScreenshot().setNome("ct_016_efetuarTransfNovoContatoComSucesso");
			loginView.logar(transfView.getDt().getStringOf("EMAIL"), transfView.getDt().getStringOf("SENHA_APP"));
			transfView.efetuarTransferenciaParaNovoContato(
					transfView.getDt().getStringOf("TRANS_VALOR"), 
					transfView.getDt().getStringOf("TRANS_BANCO"),
					transfView.getDt().getStringOf("TRANS_AGENCIA"),
					transfView.getDt().getStringOf("TRANS_CONTA"),
					transfView.getDt().getStringOf("TRANS_CPF_CNPJ"));
			transfView.validaSaldoTransferencia(true, 
					transfView.getDt().getStringOf("TRANS_VALOR"));
			loginView.logout();
		} catch (Exception e) {
			transfView.closeAppium();
			fail("Falha ao efetuar transferência entre contas Neon.");
			e.printStackTrace();
		}
	}
	
	@Test
	public void ct_017_transferenciaEntreContasNeonAntesFechamentoHorarioComSucesso () {
		transfView.getDt().setFirstRow();
		try {		
			for (int i = 0; i < transfView.getDt().getRowCount(); i++) {
				if (transfView.getDt().getStringOf("ID_CT").equals("CT_017")) 	break;
				else															transfView.getDt().setNextRow();
			}
			transfView.getScreenshot().setNome("ct_017_transferenciaEntreContasNeonAntesFechamentoHorarioComSucesso");
			loginView.logar(transfView.getDt().getStringOf("EMAIL"), transfView.getDt().getStringOf("SENHA_APP"));
			transfView.efetuarTransferenciaContaNeon(	
												transfView.getDt().getStringOf("TRANSF_FAVORECIDO"),
												transfView.getDt().getStringOf("TRANSF_CONTA_FAVORECIDO"),
												transfView.getDt().getStringOf("TRANS_VALOR"),
												false,
												"");
			transfView.validaSaldoTransferencia(true, transfView.getDt().getStringOf("TRANS_VALOR"));
			loginView.logout();
		} catch (Exception e) {
			transfView.closeAppium();
			fail("Falha ao efetuar transferência entre contas Neon.");
			e.printStackTrace();
		}
	}
	
//	@Test
	public void ct_018_transferenciaOutrosBancosAposFechamentoHorarioComSucesso () {
		transfView.getDt().setFirstRow();
		try {	
			for (int i = 0; i < transfView.getDt().getRowCount(); i++) {
				if (transfView.getDt().getStringOf("ID_CT").equals("CT_018")) 	break;
				else															transfView.getDt().setNextRow();
			}
			transfView.getScreenshot().setNome("ct_018_transferenciaOutrosBancosAposFechamentoHorarioComSucesso");
			loginView.logar(transfView.getDt().getStringOf("EMAIL"), transfView.getDt().getStringOf("SENHA_APP"));

			transfView.efetuarTransferenciaOutrosBancosAposHorario(
													transfView.getDt().getStringOf("TRANS_CPF_CNPJ"), 
													transfView.getDt().getStringOf("TRANS_BANCO"),
													transfView.getDt().getStringOf("TRANS_AGENCIA"), 
													transfView.getDt().getStringOf("TRANS_CONTA"), 
													transfView.getDt().getStringOf("TRANS_TIPO_CONTA"), 
													transfView.getDt().getStringOf("TRANS_VALOR"));
			transfView.validarTransferenciaOutrosBancosAposHorario(true);
			loginView.logout();
		} catch (Exception e) {
			transfView.closeAppium();
			fail("Teste de validação de mensagem transferencia apos horario validado com falha.");
			e.printStackTrace();
		}
	}
	
//	@Test
	public void ct_043_validarValorTarifaTransf () {
		transfView.getDt().setFirstRow();
		try {	
			for (int i = 0; i < transfView.getDt().getRowCount(); i++) {
				if (transfView.getDt().getStringOf("ID_CT").equals("CT_043")) 	break;
				else															transfView.getDt().setNextRow();
			}
			transfView.getScreenshot().setNome("ct_043_validarValorTarifaTransf");
			loginView.logar(transfView.getDt().getStringOf("EMAIL"), transfView.getDt().getStringOf("SENHA_APP"));
			transfView.efetuarTransferenciaOutrosBancos(
												transfView.getDt().getStringOf("TRANSF_FAVORECIDO"),
												transfView.getDt().getStringOf("TRANS_CPF_CNPJ"), 
												transfView.getDt().getStringOf("TRANS_BANCO"),
												transfView.getDt().getStringOf("TRANS_AGENCIA"), 
												transfView.getDt().getStringOf("TRANS_CONTA"), 
												transfView.getDt().getStringOf("TRANS_TIPO_CONTA"), 
												transfView.getDt().getStringOf("TRANS_VALOR"));
			transfView.validarValorTarifaAposTransf(true, transfView.getDt().getStringOf("TRANS_VALOR"));
			loginView.logout();
		} catch (Exception e) {
			transfView.closeAppium();
			fail("Falha ao efetuar transferência entre contas Neon.");
			e.printStackTrace();
		}
	}
	
//	@Test
	public void ct_045_validarNaoCobrancaTaxaPrimeiraTransfMes () {
		transfView.getDt().setFirstRow();
		try {	
			for (int i = 0; i < transfView.getDt().getRowCount(); i++) {
				if (transfView.getDt().getStringOf("ID_CT").equals("CT_045")) 	break;
				else															transfView.getDt().setNextRow();
			}
			transfView.getScreenshot().setNome("ct_045_validarNaoCobrancaTaxaPrimeiraTransfMes");
			loginView.logar(transfView.getDt().getStringOf("EMAIL"), transfView.getDt().getStringOf("SENHA_APP"));
			transfView.efetuarTransferenciaOutrosBancos(
												transfView.getDt().getStringOf("TRANSF_FAVORECIDO"),
												transfView.getDt().getStringOf("TRANS_CPF_CNPJ"), 
												transfView.getDt().getStringOf("TRANS_BANCO"),
												transfView.getDt().getStringOf("TRANS_AGENCIA"), 
												transfView.getDt().getStringOf("TRANS_CONTA"), 
												transfView.getDt().getStringOf("TRANS_TIPO_CONTA"), 
												transfView.getDt().getStringOf("TRANS_VALOR"));
			transfView.validarNaoCobrancaTaxaTransf(true, transfView.getDt().getStringOf("TRANS_VALOR"));
			loginView.logout();
		} catch (Exception e) {
			transfView.closeAppium();
			fail("Falha ao efetuar transferência entre contas Neon.");
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void ct_047_validarComprovanteTransferenciaComSucesso () {
		transfView.getDt().setFirstRow();
		try {	
			for (int i = 0; i < transfView.getDt().getRowCount(); i++) {
				if (transfView.getDt().getStringOf("ID_CT").equals("CT_047")) 	break;
				else															transfView.getDt().setNextRow();
			}
			transfView.getScreenshot().setNome("ct_047_validarComprovanteTransferenciaComSucesso");
			loginView.logar(transfView.getDt().getStringOf("EMAIL"), transfView.getDt().getStringOf("SENHA_APP"));
			transfView.efetuarTransferenciaContaNeon(	
													transfView.getDt().getStringOf("TRANSF_FAVORECIDO"),
													transfView.getDt().getStringOf("TRANSF_CONTA_FAVORECIDO"),
													transfView.getDt().getStringOf("TRANS_VALOR"),
													true,
													transfView.getDt().getStringOf("TRANS_COMPROVANTE"));
			transfView.validaComprovante(true);
			loginView.logout();
		} catch (Exception e) {
			transfView.closeAppium();
			fail("Comprovante com inconsistência.");
			e.printStackTrace();
		}
	}
	
	@Test
	public void ct_049_validarCompartilhamentoComprovanteComSucesso () {
		transfView.getDt().setFirstRow();
		try {	
			for (int i = 0; i < transfView.getDt().getRowCount(); i++) {
				if (transfView.getDt().getStringOf("ID_CT").equals("CT_049")) 	break;
				else															transfView.getDt().setNextRow();
			}
			transfView.getScreenshot().setNome("ct_049_validarCompartilhamentoComprovanteComSucesso");
			loginView.logar(transfView.getDt().getStringOf("EMAIL"), transfView.getDt().getStringOf("SENHA_APP"));
			transfView.compartilharComprovante();
			transfView.validarCompartilhamentoComprovante(true);
			loginView.logout();
		} catch (Exception e) {
			transfView.closeAppium();
			fail("Falha ao compartilhar comprovante.");
			e.printStackTrace();
		}
	}
	
	@Test
	public void ct_052_validarPesquisaContato () {
		transfView.getDt().setFirstRow();
		try {		
			for (int i = 0; i < transfView.getDt().getRowCount(); i++) {
				if (transfView.getDt().getStringOf("ID_CT").equals("CT_052")) 	break;
				else															transfView.getDt().setNextRow();
			}
			transfView.getScreenshot().setNome("ct_052_validarPesquisaContato");
			loginView.logar(transfView.getDt().getStringOf("EMAIL"), transfView.getDt().getStringOf("SENHA_APP"));
			transfView.pesquisarContato(transfView.getDt().getStringOf("TRANSF_FAVORECIDO"));
			transfView.validarPesquisaContato(true, transfView.getDt().getStringOf("TRANSF_FAVORECIDO"));
			loginView.logout();
		} catch (Exception e) {
			transfView.closeAppium();
			fail("Falha na pesquisa de contato.");
			e.printStackTrace();
		}
	}
	
	@Test
	public void ct_053_validarAtualizacaoSaldoAposOperacaoComSucesso () {
		transfView.getDt().setFirstRow();
		try {
			for (int i = 0; i < transfView.getDt().getRowCount(); i++) {
				if (transfView.getDt().getStringOf("ID_CT").equals("CT_053")) 	break;
				else															transfView.getDt().setNextRow();
			}
			transfView.getScreenshot().setNome("ct_053_validarAtualizacaoSaldoAposOperacaoComSucesso");
			loginView.logar(transfView.getDt().getStringOf("EMAIL"), transfView.getDt().getStringOf("SENHA_APP"));
			transfView.efetuarTransferenciaContaNeon(	
												transfView.getDt().getStringOf("TRANSF_FAVORECIDO"),
												transfView.getDt().getStringOf("TRANSF_CONTA_FAVORECIDO"),
												transfView.getDt().getStringOf("TRANS_VALOR"),
												false,
												"");
			transfView.validaSaldoTransferencia(true, transfView.getDt().getStringOf("TRANS_VALOR"));
			loginView.logout();
		} catch (Exception e) {
			transfView.closeAppium();
			fail("Falha ao efetuar transferência entre contas Neon.");
			e.printStackTrace();
		}
	}
	
	@Test
	public void ct_055_transferenciaEntreContasNeonAcimaLimiteDiarioComSucesso () {
		transfView.getDt().setFirstRow();
		try {		
			for (int i = 0; i < transfView.getDt().getRowCount(); i++) {
				if (transfView.getDt().getStringOf("ID_CT").equals("CT_055")) 	break;
				else															transfView.getDt().setNextRow();
			}
			transfView.getScreenshot().setNome("ct_055_transferenciaEntreContasNeonAcimaLimiteDiarioComSucesso");
			loginView.logar(transfView.getDt().getStringOf("EMAIL"), transfView.getDt().getStringOf("SENHA_APP"));
			transfView.efetuarTransferenciaContaNeonAcimaSaldoLimiteDisponivel(	
												transfView.getDt().getStringOf("TRANSF_FAVORECIDO"),
												transfView.getDt().getStringOf("TRANSF_CONTA_FAVORECIDO"),
												transfView.getDt().getStringOf("TRANS_VALOR"));
			transfView.validarTransfAcimaValorLimiteDiario(true);
			loginView.logout();
		} catch (Exception e) {
			transfView.closeAppium();
			fail("Falha na validação de mensagem de valor fora do limite.");
			e.printStackTrace();
		}
	}
	
	@Test
	public void ct_056_transferenciaOutrosBancosMesmoCPFAcimaLimiteDiarioComSucesso () {
		transfView.getDt().setFirstRow();
		try {		
			for (int i = 0; i < transfView.getDt().getRowCount(); i++) {
				if (transfView.getDt().getStringOf("ID_CT").equals("CT_056")) 	break;
				else															transfView.getDt().setNextRow();
			}
			transfView.getScreenshot().setNome("ct_056_transferenciaOutrosBancosMesmoCPFAcimaLimiteDiarioComSucesso");
			loginView.logar(transfView.getDt().getStringOf("EMAIL"), transfView.getDt().getStringOf("SENHA_APP"));
			transfView.efetuarTransferenciaOutrosBancosAcimaLimiteDiario(
												transfView.getDt().getStringOf("TRANSF_FAVORECIDO"),
												transfView.getDt().getStringOf("TRANS_CPF_CNPJ"), 
												transfView.getDt().getStringOf("TRANS_BANCO"),
												transfView.getDt().getStringOf("TRANS_AGENCIA"), 
												transfView.getDt().getStringOf("TRANS_CONTA"), 
												transfView.getDt().getStringOf("TRANS_TIPO_CONTA"), 
												transfView.getDt().getStringOf("TRANS_VALOR"));
			transfView.validarTransfAcimaValorLimiteDiario(true);
			loginView.logout();
		} catch (Exception e) {
			transfView.closeAppium();
			fail("Falha na validação de mensagem de valor fora do limite.");
			e.printStackTrace();
		}
	}

	@Test
	public void ct_057_transferenciaOutrosBancosCPFDiferenteAcimaLimiteDiarioComSucesso () {
		transfView.getDt().setFirstRow();
		try {		
			for (int i = 0; i < transfView.getDt().getRowCount(); i++) {
				if (transfView.getDt().getStringOf("ID_CT").equals("CT_057")) 	break;
				else															transfView.getDt().setNextRow();
			}
			transfView.getScreenshot().setNome("ct_057_transferenciaOutrosBancosCPFDiferenteAcimaLimiteDiarioComSucesso");
			loginView.logar(transfView.getDt().getStringOf("EMAIL"), transfView.getDt().getStringOf("SENHA_APP"));
			transfView.efetuarTransferenciaOutrosBancosAcimaLimiteDiario(
												transfView.getDt().getStringOf("TRANSF_FAVORECIDO"), 
												transfView.getDt().getStringOf("TRANS_CPF_CNPJ"), 
												transfView.getDt().getStringOf("TRANS_BANCO"),
												transfView.getDt().getStringOf("TRANS_AGENCIA"), 
												transfView.getDt().getStringOf("TRANS_CONTA"), 
												transfView.getDt().getStringOf("TRANS_TIPO_CONTA"), 
												transfView.getDt().getStringOf("TRANS_VALOR"));
			transfView.validarTransfAcimaValorLimiteDiario(true);
			loginView.logout();
		} catch (Exception e) {
			transfView.closeAppium();
			fail("Falha na validação de mensagem de valor fora do limite.");
			e.printStackTrace();
		}
	}
	
	@Test
	public void ct_059_validarReducaoLimiteDiarioAposOperacaoTransfComSucesso () {
		transfView.getDt().setFirstRow();
		try {	
			for (int i = 0; i < transfView.getDt().getRowCount(); i++) {
				if (transfView.getDt().getStringOf("ID_CT").equals("CT_059")) 	break;
				else															transfView.getDt().setNextRow();
			}
			transfView.getScreenshot().setNome("ct_059_validarAtualizacaoLimiteDiarioAposOperacaoComSucesso");
			loginView.logar(transfView.getDt().getStringOf("EMAIL"), transfView.getDt().getStringOf("SENHA_APP"));
			transfView.efetuarTransferenciaContaNeon2(	
												transfView.getDt().getStringOf("TRANSF_FAVORECIDO"),
												transfView.getDt().getStringOf("TRANSF_CONTA_FAVORECIDO"),
												transfView.getDt().getStringOf("TRANS_VALOR"));
			transfView.validarLimiteDiario(true, transfView.getDt().getStringOf("TRANS_VALOR"));
			loginView.logout();
		} catch (Exception e) {
			transfView.closeAppium();
			fail("Falha ao validar limite diário de transferencia.");
			e.printStackTrace();
		}
	}
}
