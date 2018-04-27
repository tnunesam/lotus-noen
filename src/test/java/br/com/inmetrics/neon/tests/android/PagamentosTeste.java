package br.com.inmetrics.neon.tests.android;

import static br.com.inmetrics.frameworkneon.Neon.initializeDrivers;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import br.com.inmetrics.frameworkneon.views.HomeView;
import br.com.inmetrics.frameworkneon.views.LoginView;
import br.com.inmetrics.frameworkneon.views.PagamentosView;
import br.com.inmetrics.neon.constants.Constants;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PagamentosTeste {

	LoginView loginView = new LoginView();
	HomeView homeView = new HomeView();
	PagamentosView pagamentosView = new PagamentosView();
	
	@Before
	public void setUp() {
		try {
			pagamentosView.activeScreenshot();
			initializeDrivers(Constants.ID_DEVICE, Constants.APP_PACKAGE, Constants.APP_ACTIVITY);
			pagamentosView.initializeDataTable(Constants.PATH_DATATABLE);

		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@After
	public void tearDown() throws Exception {
		pagamentosView.closeAppium();
	}
	
	@Test
	public void ct_023_efetuarPagamentoBoletoCobrancaAcimaLimiteDisp () {
		pagamentosView.getDt().setFirstRow();
		try {		
			for (int i = 0; i < pagamentosView.getDt().getRowCount(); i++) {
				if (pagamentosView.getDt().getStringOf("ID_CT").equals("CT_023")) 	break;
				else																pagamentosView.getDt().setNextRow();
			}
			pagamentosView.getScreenshot().setNome("ct_023_efetuarPagamentoBoletoCobrancaAcimaLimiteDisp");
			loginView.logar(pagamentosView.getDt().getStringOf("EMAIL"), pagamentosView.getDt().getStringOf("SENHA_APP"));
			pagamentosView.efetuarPagamentoBoletoCobrancaAcimaDisponivel(pagamentosView.getDt().getStringOf("PAGT_COD_BARRAS"));
			pagamentosView.validarMsgPagamentoBoletoCobrancaAcimaDisponivel(true);
			loginView.logout();
		} catch (Exception e) {
			pagamentosView.closeAppium();
			fail("Não foi possível validar mensagem de limite ultrapassado.");
			e.printStackTrace();
		}
	}
	
	@Test
	public void ct_024_efetuarPagamentoBoletoCobrancaComSucesso () throws Exception {
		pagamentosView.getDt().setFirstRow();
		try {	
			for (int i = 0; i < pagamentosView.getDt().getRowCount(); i++) {
				if (pagamentosView.getDt().getStringOf("ID_CT").equals("CT_024")) 	break;
				else																pagamentosView.getDt().setNextRow();
			}
			pagamentosView.getScreenshot().setNome("ct_024_efetuarPagamentoBoletoCobrancaComSucesso");
			loginView.logar(pagamentosView.getDt().getStringOf("EMAIL"), pagamentosView.getDt().getStringOf("SENHA_APP"));
			pagamentosView.efetuarPagamentoBoletoCobranca(pagamentosView.getDt().getStringOf("PAGT_COD_BARRAS"), false, "");
			pagamentosView.validarPagamentoBoletoCobranca(true, pagamentosView.getDt().getStringOf("EMISSAO_BOLETO_VALOR"));
			loginView.logout();
		} catch (Exception e) {
			pagamentosView.closeAppium();
			fail("Falha ao efetuar pagamento de boleto neon.");
			e.printStackTrace();
		}
	}
	
	@Test
	public void ct_027_efetuarPagamentoBoletoCobrancaAntesHorarioComSucesso () {
		pagamentosView.getDt().setFirstRow();
		try {	
			for (int i = 0; i < pagamentosView.getDt().getRowCount(); i++) {
				if (pagamentosView.getDt().getStringOf("ID_CT").equals("CT_027")) 	break;
				else																pagamentosView.getDt().setNextRow();
			}
			pagamentosView.getScreenshot().setNome("ct_027_efetuarPagamentoBoletoCobrancaComSucesso");
			loginView.logar(pagamentosView.getDt().getStringOf("EMAIL"), pagamentosView.getDt().getStringOf("SENHA_APP"));
			pagamentosView.efetuarPagamentoBoletoCobranca(pagamentosView.getDt().getStringOf("PAGT_COD_BARRAS"), false, "");
			pagamentosView.validarPagamentoBoletoCobranca(true, pagamentosView.getDt().getStringOf("EMISSAO_BOLETO_VALOR"));
			loginView.logout();
		} catch (Exception e) {
			pagamentosView.closeAppium();
			fail("Falha ao efetuar pagamento de boleto neon.");
			e.printStackTrace();
		}
	}
	
//	@Test
	public void ct_028_efetuarPagamentoBoletoCobrancaAposHorario20h30ComSucesso () {
		pagamentosView.getDt().setFirstRow();
		try {	
			for (int i = 0; i < pagamentosView.getDt().getRowCount(); i++) {
				if (pagamentosView.getDt().getStringOf("ID_CT").equals("CT_028")) 	break;
				else																pagamentosView.getDt().setNextRow();
			}
			pagamentosView.getScreenshot().setNome("ct_028_efetuarPagamentoBoletoCobrancaAposHorario20h30ComSucesso");
			loginView.logar(pagamentosView.getDt().getStringOf("EMAIL"), pagamentosView.getDt().getStringOf("SENHA_APP"));
			pagamentosView.efetuarPagamentoBoletoCobranca(pagamentosView.getDt().getStringOf("PAGT_COD_BARRAS"), false, "");
			//TODO validar mensagem
			pagamentosView.validarPagamentoBoletoAposHorario20h30(true);
			loginView.logout();
		} catch (Exception e) {
			pagamentosView.closeAppium();
			fail("Teste de validação de mensagem pagamento apos período 20h30 validado com falha.");
			e.printStackTrace();
		}
	}
	
	@Test
	public void ct_029_efetuarPagamentoBoletoCobrancaLinhaDigitavelComSucesso () {
		pagamentosView.getDt().setFirstRow();
		try {		
			for (int i = 0; i < pagamentosView.getDt().getRowCount(); i++) {
				if (pagamentosView.getDt().getStringOf("ID_CT").equals("CT_029")) 	break;
				else																pagamentosView.getDt().setNextRow();
			}
			pagamentosView.getScreenshot().setNome("ct_029_efetuarPagamentoBoletoCobrancaLinhaDigitavelComSucesso");
			loginView.logar(pagamentosView.getDt().getStringOf("EMAIL"), pagamentosView.getDt().getStringOf("SENHA_APP"));
			pagamentosView.efetuarPagamentoBoletoCobranca(pagamentosView.getDt().getStringOf("PAGT_COD_BARRAS"), false, "");
			pagamentosView.validarPagamentoBoletoCobranca(true, pagamentosView.getDt().getStringOf("EMISSAO_BOLETO_VALOR"));
			loginView.logout();
		} catch (Exception e) {
			pagamentosView.closeAppium();
			fail("Falha ao efetuar pagamento de boleto neon.");
			e.printStackTrace();
		}
	}
	
	@Test
	public void ct_048_validarComprovantePagamentoComSucesso () {
		pagamentosView.getDt().setFirstRow();
		try {	
			for (int i = 0; i < pagamentosView.getDt().getRowCount(); i++) {
				if (pagamentosView.getDt().getStringOf("ID_CT").equals("CT_048")) 	break;
				else																pagamentosView.getDt().setNextRow();
			}
			pagamentosView.getScreenshot().setNome("ct_048_validarComprovantePagamentoComSucesso");
			loginView.logar(pagamentosView.getDt().getStringOf("EMAIL"), pagamentosView.getDt().getStringOf("SENHA_APP"));
			pagamentosView.efetuarPagamentoBoletoCobranca(pagamentosView.getDt().getStringOf("PAGT_COD_BARRAS"), true,
												pagamentosView.getDt().getStringOf("PAGT_COMPROVANTE"));
			pagamentosView.validarComprovante(true);
			loginView.logout();
		} catch (Exception e) {
			pagamentosView.closeAppium();
			fail("Comprovante com inconsistência.");
			e.printStackTrace();
		}
	}
	
	@Test
	public void ct_058_efetuarPagamentoBoletoCobrancaAcimaSaldoDisponivelComSucesso() {
		pagamentosView.getDt().setFirstRow();
		try {	
			for (int i = 0; i < pagamentosView.getDt().getRowCount(); i++) {
				if (pagamentosView.getDt().getStringOf("ID_CT").equals("CT_058")) 	break;
				else																pagamentosView.getDt().setNextRow();
			}
			pagamentosView.getScreenshot().setNome("ct_058_efetuarPagamentoBoletoCobrancaAcimaSaldoDisponivelComSucesso");
			loginView.logar(pagamentosView.getDt().getStringOf("EMAIL"), pagamentosView.getDt().getStringOf("SENHA_APP"));
			pagamentosView.efetuarPagamentoBoletoCobrancaAcimaSaldoDisponivel(pagamentosView.getDt().getStringOf("PAGT_COD_BARRAS"));
			pagamentosView.validarMsgPagamentoBoletoCobrancaAcimaSaldoDisponivel(true);
			loginView.logout();
		} catch (Exception e) {
			pagamentosView.closeAppium();
			fail("Não foi possível validar mensagem de saldo insuficiente.");
			e.printStackTrace();
		}
	}
	
//	@Test
	public void ct_062_efetuarPagamentoAgendamentoAgendadoParaHojeComSucesso () {
		pagamentosView.getDt().setFirstRow();
		try {
			for (int i = 0; i < pagamentosView.getDt().getRowCount(); i++) {
				if (pagamentosView.getDt().getStringOf("ID_CT").equals("CT_062")) 	break;
				else																pagamentosView.getDt().setNextRow();
			}
			pagamentosView.getScreenshot().setNome("ct_062_efetuarPagamentoAgendamentoAgendadoParaHojeComSucesso");
			loginView.logar(pagamentosView.getDt().getStringOf("EMAIL"), pagamentosView.getDt().getStringOf("SENHA_APP"));
			pagamentosView.efetuarPagamentoAgendamentoAgendadoParaHoje();
			pagamentosView.validarBotaoPagamentoDisabled(true);
			loginView.logout();
		} catch (Exception e) {
			pagamentosView.closeAppium();
			fail("Pagamento de agendamento agendado para hoje com falha.");
			e.printStackTrace();
		}
	}

	@Test
	public void ct_063_efetuarAgendamentoPagamentoDataFuturaAntesVencimentoComSucesso () {
		pagamentosView.getDt().setFirstRow();
		try {	
			for (int i = 0; i < pagamentosView.getDt().getRowCount(); i++) {
				if (pagamentosView.getDt().getStringOf("ID_CT").equals("CT_063")) 	break;
				else																pagamentosView.getDt().setNextRow();
			}
			pagamentosView.getScreenshot().setNome("ct_063_efetuarAgendamentoPagamentoDataFuturaAntesVencimentoComSucesso");
			loginView.logar(pagamentosView.getDt().getStringOf("EMAIL"), pagamentosView.getDt().getStringOf("SENHA_APP"));
			pagamentosView.efetuarAgendamentoPagamentoDataFuturaAntesVencimento(
															pagamentosView.getDt().getStringOf("PAGT_COD_BARRAS"),
															pagamentosView.getDt().getStringOf("PAGT_DATA_BOLETO"),
															false,
															"");
			pagamentosView.validarAtualizacaoTotalAgendado(true, pagamentosView.getDt().getStringOf("PAGT_VALOR"));
			loginView.logout();
		} catch (Exception e) {
			pagamentosView.closeAppium();
			fail("Falha ao atualizar saldo agendamento.");
			e.printStackTrace();
		}
	}
	
	@Test
	public void ct_064_efetuarAgendamentoPagamentoAposVencimentoComSucesso () {
		pagamentosView.getDt().setFirstRow();
		try {	
			for (int i = 0; i < pagamentosView.getDt().getRowCount(); i++) {
				if (pagamentosView.getDt().getStringOf("ID_CT").equals("CT_064")) 	break;
				else																pagamentosView.getDt().setNextRow();
			}
			pagamentosView.getScreenshot().setNome("ct_064_efetuarAgendamentoPagamentoAposVencimentoComSucesso");
			loginView.logar(pagamentosView.getDt().getStringOf("EMAIL"), pagamentosView.getDt().getStringOf("SENHA_APP"));
			pagamentosView.efetuarAgendamentoPagamentoAposVencimento(
															pagamentosView.getDt().getStringOf("PAGT_COD_BARRAS"), 
															pagamentosView.getDt().getStringOf("PAGT_DATA_VENCIMENTO_BOLETO"));
			pagamentosView.validarMsgAgendamentoNaoConcluido(true);
			loginView.logout();
		} catch (Exception e) {
			pagamentosView.closeAppium();
			fail("Não foi possível validar mensagem de agendamento não concluído.");
			e.printStackTrace();
		}
	}
	
	@Test
	public void ct_065_editarAgendamentoFuturoParaHojeComSucesso () {
		pagamentosView.getDt().setFirstRow();
		try {	
			for (int i = 0; i < pagamentosView.getDt().getRowCount(); i++) {
				if (pagamentosView.getDt().getStringOf("ID_CT").equals("CT_065")) 	break;
				else																pagamentosView.getDt().setNextRow();
			}
			pagamentosView.getScreenshot().setNome("ct_065_editarAgendamentoFuturoParaHojeComSucesso");
			loginView.logar(pagamentosView.getDt().getStringOf("EMAIL"), pagamentosView.getDt().getStringOf("SENHA_APP"));
			pagamentosView.editarAgendamento(pagamentosView.getDt().getStringOf("PAGT_DATA_BOLETO"), true);
			pagamentosView.validarAtualizacaoTotalAgendado(true, pagamentosView.getDt().getStringOf("PAGT_VALOR"));
			loginView.logout();
		} catch (Exception e) {
			pagamentosView.closeAppium();
			fail("Falha ao atualizar saldo agendamento.");
			e.printStackTrace();
		}
	}
	
	@Test
	public void ct_066_editarAgendamentoAposDataAgendamentoInicialComSucesso () {
		pagamentosView.getDt().setFirstRow();
		try {	
			for (int i = 0; i < pagamentosView.getDt().getRowCount(); i++) {
				if (pagamentosView.getDt().getStringOf("ID_CT").equals("CT_066")) 	break;
				else																pagamentosView.getDt().setNextRow();
			}
			pagamentosView.getScreenshot().setNome("ct_066_editarAgendamentoAposDataAgendamentoInicialComSucesso");
			loginView.logar(pagamentosView.getDt().getStringOf("EMAIL"), pagamentosView.getDt().getStringOf("SENHA_APP"));
			pagamentosView.editarAgendamento(pagamentosView.getDt().getStringOf("PAGT_DATA_BOLETO"), false);
			pagamentosView.validarEdicaoDataAgendamento(true, pagamentosView.getDt().getStringOf("PAGT_DATA_BOLETO"));
			loginView.logout();
		} catch (Exception e) {
			pagamentosView.closeAppium();
			fail("Edição agendamento falhou.");
			e.printStackTrace();
		}
	}
	
	@Test
	public void ct_067_excluirAgendamentoComSucesso () {
		pagamentosView.getDt().setFirstRow();
		try {		
			for (int i = 0; i < pagamentosView.getDt().getRowCount(); i++) {
				if (pagamentosView.getDt().getStringOf("ID_CT").equals("CT_067")) 	break;
				else																pagamentosView.getDt().setNextRow();
			}
			pagamentosView.getScreenshot().setNome("ct_067_excluirAgendamentoComSucesso");
			loginView.logar(pagamentosView.getDt().getStringOf("EMAIL"), pagamentosView.getDt().getStringOf("SENHA_APP"));
			pagamentosView.excluirAgendamento();
			pagamentosView.validarAtualizacaoTotalAgendado(true, pagamentosView.getDt().getStringOf("PAGT_VALOR"));
			loginView.logout();
		} catch (Exception e) {
			pagamentosView.closeAppium();
			fail("Falha ao atualizar saldo agendamento.");
			e.printStackTrace();
		}
	}
	
	@Test
	public void ct_068_validarComprovanteAgendamentoComSucesso () {
		pagamentosView.getDt().setFirstRow();
		try {	
			for (int i = 0; i < pagamentosView.getDt().getRowCount(); i++) {
				if (pagamentosView.getDt().getStringOf("ID_CT").equals("CT_068")) 	break;
				else																pagamentosView.getDt().setNextRow();
			}
			pagamentosView.getScreenshot().setNome("ct_068_validarComprovanteAgendamentoComSucesso");
			loginView.logar(pagamentosView.getDt().getStringOf("EMAIL"), pagamentosView.getDt().getStringOf("SENHA_APP"));
			pagamentosView.efetuarAgendamentoPagamentoDataFuturaAntesVencimento(
															pagamentosView.getDt().getStringOf("PAGT_COD_BARRAS"),
															pagamentosView.getDt().getStringOf("PAGT_DATA_BOLETO"),
															true,
															pagamentosView.getDt().getStringOf("PAGT_COMPROVANTE_AGENDAMENTO"));
			pagamentosView.validarComprovante(true);
			loginView.logout();
		} catch (Exception e) {
			pagamentosView.closeAppium();
			fail("Comprovante com inconsistência.");
			e.printStackTrace();
		}
	}
	
//	@Test
	public void ct_070_efetuarPagamentoBoletoCobrancaAposHorario00hComSucesso () {
		pagamentosView.getDt().setFirstRow();
		try {			
			for (int i = 0; i < pagamentosView.getDt().getRowCount(); i++) {
				if (pagamentosView.getDt().getStringOf("ID_CT").equals("CT_070")) 	break;
				else																pagamentosView.getDt().setNextRow();
			}
			pagamentosView.getScreenshot().setNome("ct_070_efetuarPagamentoBoletoCobrancaAposHorario00hComSucesso");
			loginView.logar(pagamentosView.getDt().getStringOf("EMAIL"), pagamentosView.getDt().getStringOf("SENHA_APP"));
			pagamentosView.efetuarPagamentoBoletoCobranca(pagamentosView.getDt().getStringOf("PAGT_COD_BARRAS"), false, "");
			//TODO validar mensagem
			pagamentosView.validarPagamentoBoletoAposHorario00h(true);
			loginView.logout();
		} catch (Exception e) {
			pagamentosView.closeAppium();
			fail("Teste de validação de mensagem pagamento apos período 00h validado com falha.");
			e.printStackTrace();
		}
	}
	
	@Test
	public void ct_071_validarAtualizacaoTotalAgendadoComSucesso () {
		pagamentosView.getDt().setFirstRow();
		try {
			for (int i = 0; i < pagamentosView.getDt().getRowCount(); i++) {
				if (pagamentosView.getDt().getStringOf("ID_CT").equals("CT_071")) 	break;
				else																pagamentosView.getDt().setNextRow();
			}
			pagamentosView.getScreenshot().setNome("ct_071_validarAtualizacaoTotalAgendadoComSucesso");
			loginView.logar(pagamentosView.getDt().getStringOf("EMAIL"), pagamentosView.getDt().getStringOf("SENHA_APP"));
			pagamentosView.efetuarAgendamentoPagamentoDataFuturaAntesVencimento(
															pagamentosView.getDt().getStringOf("PAGT_COD_BARRAS"),
															pagamentosView.getDt().getStringOf("PAGT_DATA_BOLETO"),
															false,
															"");
			pagamentosView.validarAtualizacaoTotalAgendado(true, pagamentosView.getDt().getStringOf("PAGT_VALOR"));
			loginView.logout();
		} catch (Exception e) {
			pagamentosView.closeAppium();
			fail("Falha ao atualizar saldo agendamento.");
			e.printStackTrace();
		}
	}
	
	@Test
	public void ct_072_efetuarAgendamentoPagamentoValidarAgendamentosAbaFuturosComSucesso () {
		pagamentosView.getDt().setFirstRow();
		try {		
			for (int i = 0; i < pagamentosView.getDt().getRowCount(); i++) {
				if (pagamentosView.getDt().getStringOf("ID_CT").equals("CT_072")) 	break;
				else																pagamentosView.getDt().setNextRow();
			}
			pagamentosView.getScreenshot().setNome("ct_072_efetuarAgendamentoPagamentoValidarAgendamentosAbaFuturosComSucesso");
			loginView.logar(pagamentosView.getDt().getStringOf("EMAIL"), pagamentosView.getDt().getStringOf("SENHA_APP"));
			pagamentosView.efetuarAgendamentoPagamentoDataFuturaAntesVencimento(
															pagamentosView.getDt().getStringOf("PAGT_COD_BARRAS"),
															pagamentosView.getDt().getStringOf("PAGT_DATA_BOLETO"),
															false,
															"");
			pagamentosView.validarAgendamentosAbaFuturos(true);
			loginView.logout();
		} catch (Exception e) {
			pagamentosView.closeAppium();
			fail("Teste de validação de mensagem pagamento apos período 00h validado com falha.");
			e.printStackTrace();
		}
	}
}
