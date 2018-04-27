package br.com.inmetrics.frameworkneon.views;

import static org.junit.Assert.fail;

import java.sql.Date;
import java.text.SimpleDateFormat;

import org.apache.commons.lang.SystemUtils;
import org.openqa.selenium.By;

import br.com.inmetrics.frameworkneon.Neon;
import br.com.inmetrics.neon.constants.Constants;
import io.appium.java_client.android.AndroidKeyCode;

public class PagamentosView extends Neon {

	private Date dataAtual;
	private double saldoInicial;
	private double saldoFinal;
	private double totalAgendadoInicial;
	private double totalAgendadoFinal;
	private boolean comprovanteValido;
	UtilView utilView = new UtilView();
	HomeView homeView = new HomeView();
	
	//
	//	Métodos CT's
 	//
	public void excluirAgendamento() throws Exception {
		this.totalAgendadoInicial = getTotalAgendado();
		
		getScreenshot().CaptureScreenshot();
		homeView.clicarOpcaoMenu("saldo");
		
		getScreenshot().CaptureScreenshot();
		swipeParaAbaFuturos();
		
		getScreenshot().CaptureScreenshot();
		clicaPrimeiroAgendamentoLista();
		
		getScreenshot().CaptureScreenshot();
		clicaBotaoExcluir();
		
		getScreenshot().CaptureScreenshot();
		confirmaExclusao();
		
		this.totalAgendadoFinal = getTotalAgendado();
	}
	
	public void efetuarAgendamentoPagamentoDataFuturaAntesVencimento(String codBarras, String dataAntesVencimentoBoleto, boolean validaComprovante, String comprovante) throws Exception {
		this.totalAgendadoInicial = getTotalAgendado();
		
		getScreenshot().CaptureScreenshot();
		homeView.clicarOpcaoMenu("pagamentos");
		
		getScreenshot().CaptureScreenshot();
		clicarBotaoDigitaCodBarras();
		
		getScreenshot().CaptureScreenshot();
		preencherCodBarras(codBarras);
		
		getScreenshot().CaptureScreenshot();
		clicarBotaoProx();
		
		Thread.sleep(2000);
		
		getScreenshot().CaptureScreenshot();
		clicarBotaoSelecioneCategoria();
		
		getScreenshot().CaptureScreenshot();
		escolheCategoria();
		
		getScreenshot().CaptureScreenshot();
		clicaDataAtual();
		
		getScreenshot().CaptureScreenshot();
		selecionaDataPagamento(dataAntesVencimentoBoleto);
		
		getScreenshot().CaptureScreenshot();
		clicarAgendarPagamento();
		
		getScreenshot().CaptureScreenshot();
		utilView.setSenha(getDt().getStringOf("SENHA_CARTAO"));		
		
		while (!getGenerics().exists("text", "agendamento feito",Constants.ANDROID_TEXT_VIEW)){
			Thread.sleep(1000);
		}
		
		if (validaComprovante) {
			this.comprovanteValido = false;
			if (getGenerics().validaComprovante(getTextView().findAllStrings(), comprovante));
				this.comprovanteValido = true;
		}
		
		getScreenshot().CaptureScreenshot();
		fecharAgendamentoFeito();
		
		Thread.sleep(5000);
		
		while (!getGenerics().exists("id", "br.com.neon:id/widgetMoneyValue",Constants.ANDROID_TEXT_VIEW)){
			Thread.sleep(1000);
		}
		
		Thread.sleep(3000);
		
		if (!validaComprovante) this.totalAgendadoFinal = getTotalAgendado();
	}
	
	public void blabla() throws Exception {
		getGenerics().validaComprovante(getTextView().findAllStrings(), getDt().getStringOf("PAGT_COMPROVANTE_AGENDAMENTO"));
	}
	
	public void efetuarPagamentoAgendadoParaHoje() throws Exception {
		getScreenshot().CaptureScreenshot();
		homeView.clicarOpcaoMenu("saldo");
		
		getScreenshot().CaptureScreenshot();
		swipeParaAbaFuturos();
		
		getScreenshot().CaptureScreenshot();
		clicaPrimeiroAgendamentoLista();
		
		getScreenshot().CaptureScreenshot();
		clicaBotaoCalendario();
	}
	
	public void efetuarAgendamentoPagamentoAposVencimento(String codBarras, String dataAposVencimento) throws Exception {
		getScreenshot().CaptureScreenshot();
		homeView.clicarOpcaoMenu("pagamentos");
		
		getScreenshot().CaptureScreenshot();
		clicarBotaoDigitaCodBarras();
		
		getScreenshot().CaptureScreenshot();
		preencherCodBarras(codBarras);
		
		getScreenshot().CaptureScreenshot();
		clicarBotaoProx();
		
		getScreenshot().CaptureScreenshot();
		clicarBotaoSelecioneCategoria();
		
		getScreenshot().CaptureScreenshot();
		escolheCategoria();
		
		getScreenshot().CaptureScreenshot();
		clicaDataAtual();
		
		getScreenshot().CaptureScreenshot();
		selecionaDataPagamento(dataAposVencimento);
		
		getScreenshot().CaptureScreenshot();
		clicarAgendarPagamento();
		
		getScreenshot().CaptureScreenshot();
		utilView.setSenha(getDt().getStringOf("SENHA_CARTAO"));		
	}
	
	public void editarAgendamento(String dataAgendamento, boolean pagamentoHoje) throws Exception {
		this.totalAgendadoInicial = getTotalAgendado();
		
		getScreenshot().CaptureScreenshot();
		homeView.clicarOpcaoMenu("saldo");
		
		getScreenshot().CaptureScreenshot();
		swipeParaAbaFuturos();
		
		getScreenshot().CaptureScreenshot();
		clicaPrimeiroAgendamentoLista();
		
		getScreenshot().CaptureScreenshot();
		clicaBotaoCalendario();
		
		getScreenshot().CaptureScreenshot();
		clicaDataAtual();
		
		getScreenshot().CaptureScreenshot();
		selecionaDataPagamento(dataAgendamento);
		
		if (pagamentoHoje) {
			getScreenshot().CaptureScreenshot();
			clicarBotaoPagarHoje();
			
			getScreenshot().CaptureScreenshot();
			utilView.setSenha(getDt().getStringOf("SENHA_CARTAO"));
			
			getScreenshot().CaptureScreenshot();
			fecharPagamentoFeito();
		}
		else {
			getScreenshot().CaptureScreenshot();
			clicaBotaoSalvarAlteracao(); 
			
			getScreenshot().CaptureScreenshot();
			utilView.setSenha(getDt().getStringOf("SENHA_CARTAO"));	
			
			// Voltar tela inicial
			getScreenshot().CaptureScreenshot();
			getGenerics().pressKeyCode(AndroidKeyCode.BACK);
			
			getScreenshot().CaptureScreenshot();
			getGenerics().pressKeyCode(AndroidKeyCode.BACK);
		}
		
		this.totalAgendadoFinal = getTotalAgendado();
	}
	
	public void efetuarPagamentoAgendamentoAgendadoParaHoje() throws Exception {
		getScreenshot().CaptureScreenshot();
		homeView.clicarOpcaoMenu("saldo");
		
		getScreenshot().CaptureScreenshot();
		swipeParaAbaFuturos();
		
		getScreenshot().CaptureScreenshot();
		clicaPrimeiroAgendamentoLista();
		
		getScreenshot().CaptureScreenshot();
		clicaBotaoCalendario();
	}
	
	public void efetuarPagamentoBoletoCobrancaAcimaDisponivel (String codBarras) throws Exception {
		getScreenshot().CaptureScreenshot();
		homeView.clicarOpcaoMenu("pagamentos");
		
		getScreenshot().CaptureScreenshot();
		clicarBotaoDigitaCodBarras();
		
		getScreenshot().CaptureScreenshot();
		preencherCodBarras(codBarras);
		
		getScreenshot().CaptureScreenshot();
		clicarBotaoProx();
		
		getScreenshot().CaptureScreenshot();
		clicarBotaoSelecioneCategoria();
		
		getScreenshot().CaptureScreenshot();
		escolheCategoria();
		
		getScreenshot().CaptureScreenshot();
		clicarBotaoPagarHoje();
		
		getScreenshot().CaptureScreenshot();
		utilView.setSenha(getDt().getStringOf("SENHA_CARTAO"));		
	}
	
	public void efetuarPagamentoBoletoCobrancaAcimaSaldoDisponivel (String codBarras) throws Exception {
		getScreenshot().CaptureScreenshot();
		homeView.clicarOpcaoMenu("pagamentos");
		
		getScreenshot().CaptureScreenshot();
		clicarBotaoDigitaCodBarras();
		
		getScreenshot().CaptureScreenshot();
		preencherCodBarras(codBarras);
		
		getScreenshot().CaptureScreenshot();
		clicarBotaoProx();
		
		getScreenshot().CaptureScreenshot();
		clicarBotaoSelecioneCategoria();
		
		getScreenshot().CaptureScreenshot();
		escolheCategoria();
		
		getScreenshot().CaptureScreenshot();
		clicarBotaoPagarHoje();
		
		getScreenshot().CaptureScreenshot();
		utilView.setSenha(getDt().getStringOf("SENHA_CARTAO"));		
	}
	
	public void efetuarPagamentoBoletoCobranca (String codBarras, boolean validaComprovante, String comprovante) throws Exception {
		getScreenshot().CaptureScreenshot();
		this.saldoInicial = getSaldo();
		
		getScreenshot().CaptureScreenshot();
		homeView.clicarOpcaoMenu("pagamentos");
		
		getScreenshot().CaptureScreenshot();
		clicarBotaoDigitaCodBarras();
		
		getScreenshot().CaptureScreenshot();
		preencherCodBarras(codBarras);
		
		getScreenshot().CaptureScreenshot();
		clicarBotaoProx();
		
		getScreenshot().CaptureScreenshot();
		clicarBotaoSelecioneCategoria();
		
		getScreenshot().CaptureScreenshot();
		escolheCategoria();
		
		getScreenshot().CaptureScreenshot();
		clicarBotaoPagarHoje();
		
		getScreenshot().CaptureScreenshot();
		utilView.setSenha(getDt().getStringOf("SENHA_CARTAO"));		
		
		while (!getGenerics().exists("text", "pagamento feito",Constants.ANDROID_TEXT_VIEW)){
			Thread.sleep(1000);
		}
		
		if (validaComprovante) {
			this.comprovanteValido = false;
			if (getGenerics().validaComprovante(getTextView().findAllStrings(), comprovante))
				this.comprovanteValido = true;
		}
		
		getScreenshot().CaptureScreenshot();
		fecharPagamentoFeito();
		
		getScreenshot().CaptureScreenshot();
		this.saldoFinal = getSaldo();
	}
	
	//
	//	Métodos de a��es da tela
	//
	public double getTotalAgendado() throws NumberFormatException, Exception {
		double totalAgendado = 0.0;
		
		Thread.sleep(2000);
		
		getScreenshot().CaptureScreenshot();
		homeView.clicarOpcaoMenu("saldo");
		
		getScreenshot().CaptureScreenshot();
		swipeParaAbaFuturos();
		
		String textoTela = getTextView().findAllStrings();
		
		if (!textoTela.contains("Você não possui despesas agendadas")) {
			String teste = getTextView().getAttributeByIndex(getTextView().findAll().size()-3, "text");
			teste = teste.replaceAll(",", ".");
			teste = teste.replace("$", "");
			teste = teste.replaceAll("R", "");
			teste = teste.replaceAll(" ", "");
			teste = teste.replaceAll("-", "");
			totalAgendado = Double.parseDouble(teste);
		}
		
		// Voltar tela inicial
		getScreenshot().CaptureScreenshot();
		getGenerics().pressKeyCode(AndroidKeyCode.BACK);
		
		return totalAgendado;
	}
	
	public void confirmaExclusao() throws Exception {
		getTextView().clickByText("SIM, EXCLUIR");
	}
	
	public void clicaBotaoExcluir() {
		getDriver().findElement(By.xpath("//*[@resource-id='br.com.neon:id/rl_btn_delete']")).click();
	}
	
	public void clicaBotaoSalvarAlteracao() throws Exception {
		getTextView().clickByText("AGENDAR PAGAMENTO");
	}
	
	public void clicaBotaoCalendario() {
		getDriver().findElement(By.xpath("//*[@resource-id='br.com.neon:id/rl_btn_schedule']")).click();
	}
	
	public void swipeParaAbaFuturos() throws Exception {
		Thread.sleep(6000);
		getScreen().swipeLeft("//*[@resource-id='br.com.neon:id/tpiMonths']");
	}
	
	public void clicaPrimeiroAgendamentoLista() {
		getDriver().findElement(By.xpath("//*[@resource-id='br.com.neon:id/rl_container_transaction']")).click();
	}
	
	public void clicarAgendarPagamento() throws Exception {
		getTextView().clickByText("AGENDAR PAGAMENTO");
	}
	
	public void selecionaDataPagamentoParaDiaSeguinte() throws Exception {
		Date dataAtual = (Date) new Date(System.currentTimeMillis());
		SimpleDateFormat formatadorData = new SimpleDateFormat("dd");
		int diaSeguinte = Integer.parseInt(formatadorData.format(dataAtual)) + 1;
		
		getDriver().findElement(By.xpath("//*[@content-desc='" + diaSeguinte + " dezembro 2016']")).click();
		getBotao().clickById("android:id/button1");
	}
	
	// @Param: 	dataAgendada: deve ser passada no formato  "<dia> <nome mes> <ano>"
	//			Exemplo de data a ser passada com input: "16 dezembro 2016"
	public void selecionaDataPagamento(String dataAgendada) throws Exception {
		getDriver().findElement(By.xpath("//*[@content-desc='" + dataAgendada + "']")).click();
//		getDriver().findElement(By.xpath("//*[@content-desc='" + dataAgendada + " selecionado(s)']")).click();
		getBotao().clickById("android:id/button1");
	}
	
	public void clicaDataAtual() throws Exception {
//		dataAtual = (Date) new Date(System.currentTimeMillis());
//		SimpleDateFormat formatadorData = new SimpleDateFormat("dd/MM/yyyy");
		getTextView().clickById("br.com.neon:id/tv_date");
	}
	
	public void clicarBotaoPagarHoje() throws Exception {
		getTextView().clickById("br.com.neon:id/btn_text");
	}
	
	public void escolheCategoria() throws Exception {
		getTextView().clickByText("BARES E RESTAURANTES");
		
		getScreenshot().CaptureScreenshot();
		getTextView().clickByText("PRÓXIMO");
	}
	
	public void clicarBotaoSelecioneCategoria() throws Exception {
		while (!getGenerics().exists("text", "SELECIONE A CATEGORIA",Constants.ANDROID_TEXT_VIEW)){
			Thread.sleep(1000);
		}
		
		getTextView().clickById("br.com.neon:id/text_category");
	}
	
	public void clicarBotaoDigitaCodBarras() throws Exception {
		getImageButton().clickById("br.com.neon:id/manual_button");
	}
	
	public void preencherCodBarras(String codBarras) throws Exception {
		getEditText().insertTextById("br.com.neon:id/bar_code_edit_text", codBarras);
	}
	
	public void clicarBotaoProx() throws Exception {
		getTextView().clickByText("PRÓXIMO");
	}
	
	public void clicarBotaoConfirmarPag() throws Exception {
		getTextView().clickByText("CONFIRMAR PAGAMENTO");
	}
	
	public void fecharPagamentoFeito() throws Exception {
//		Thread.sleep(6000);
//		getScreen().swipe(400, 25, 30, 30);
		
		Thread.sleep(2000);
		getScreenshot().CaptureScreenshot();
		getImageView().clickById("br.com.neon:id/iv_close");
		
		getScreenshot().CaptureScreenshot();
		getGenerics().pressKeyCode(AndroidKeyCode.BACK);
	}
	
	public void fecharAgendamentoFeito() throws Exception {
		Thread.sleep(2000);
		getScreenshot().CaptureScreenshot();
		getImageView().clickById("br.com.neon:id/iv_close");
	}
	
	public double getSaldo() throws NumberFormatException, Exception {
		return Double.parseDouble(getTextView().getAttribute("id", "br.com.neon:id/widgetMoneyValue", "text").replaceAll(",", "."));
	}
	
	public boolean validaSaldo(double saldoInicial, double saldoFinal, double valorTransacao) {
		boolean saldoValido = false;
		double calculaDiferencaSaldo = (saldoInicial - saldoFinal) - valorTransacao;
		if (Math.round(calculaDiferencaSaldo) == 0.0) saldoValido = true;
		return saldoValido;
	}
	
	public boolean validaTotalAgendado(double saldoInicial, double saldoFinal, double valorTransacao) {
		boolean saldoValido = false;
		double calculaDiferencaSaldo;
		
		if (saldoFinal <= 0)  calculaDiferencaSaldo = saldoInicial - valorTransacao;
		else calculaDiferencaSaldo = (saldoFinal - saldoInicial) - valorTransacao;
		
		if (Math.round(calculaDiferencaSaldo) == 0.0) saldoValido = true;
		return saldoValido;
	}
	
	//
	//	Métodos validadores
	//
	public void validarMsgPagamentoBoletoCobrancaAcimaSaldoDisponivel (boolean resultadoEsperado) throws Exception {
		boolean existemMsgSaldoInsuficiente;
		Thread.sleep(5000);

		existemMsgSaldoInsuficiente = getGenerics().exists("text", "SALDO INSUFICIENTE", Constants.ANDROID_TEXT_VIEW);
		
		// Registra se transferencia foi realizado
		// (dependendo do resultado esperado do teste)
		if (resultadoEsperado) {
			if (existemMsgSaldoInsuficiente) {
				pass("Mensagem de saldo insuficiente validada com sucesso!");
				
				getScreenshot().CaptureScreenshot();
				getTextView().clickByText("OK, ENTENDI");
				
				// Volta para tela inicial
				getScreenshot().CaptureScreenshot();
				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
				getScreenshot().CaptureScreenshot();
				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
				getScreenshot().CaptureScreenshot();
				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
			}
			else {
				closeAppium();
				fail("Não foi possível validar mensagem de saldo insuficiente.");
			}
		}
		else {
			if (existemMsgSaldoInsuficiente)	pass("Não foi possível validar mensagem de saldo insuficiente.");
			else {
				closeAppium();
				fail("Mensagem de saldo insuficiente validada com sucesso!");
			}
		}
	}
	public void validarMsgPagamentoBoletoCobrancaAcimaDisponivel (boolean resultadoEsperado) throws Exception {
		boolean existemMsgLimiteUltrapassado;
		Thread.sleep(5000);

		existemMsgLimiteUltrapassado = getGenerics().exists("text", "limite ultrapassado", Constants.ANDROID_TEXT_VIEW);
		
		// Registra se transferencia foi realizado
		// (dependendo do resultado esperado do teste)
		if (resultadoEsperado) {
			if (existemMsgLimiteUltrapassado) {
				pass("Mensagem de limite ultrapassado validada com sucesso!");
				
				getTextView().clickByText("OK, ENTENDI");
				
				// Volta para tela inicial
				getScreenshot().CaptureScreenshot();
				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
				getScreenshot().CaptureScreenshot();
				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
				getScreenshot().CaptureScreenshot();
				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
			}
			else {
				closeAppium();
				fail("Não foi possível validar mensagem de limite ultrapassado.");
			}
		}
		else {
			if (existemMsgLimiteUltrapassado)	pass("Falha ao validar mensagem de limite ultrapassado.");
			else {
				closeAppium();
				fail("Sucesso ao validar mensagem de limite ultrapassado.");
			}
		}
	}
	
	public void validarPagamentoBoletoCobranca (boolean resultadoEsperado, String valorPagamento) throws InterruptedException {
		Thread.sleep(5000);
		boolean saldoValido = false;
		// Valida saldo
		saldoValido = validaSaldo(saldoInicial, saldoFinal, Double.parseDouble(valorPagamento));

		// Registra se transferencia foi realizado
		// (dependendo do resultado esperado do teste)
		if (resultadoEsperado) {
			if (saldoValido)	pass("Pagamento de boleto Neon feita com sucesso!");
			else {
				closeAppium();
				fail("Falha ao efetuar pagamento de boleto neon.");
			}
		}
		else {
			if (saldoValido)	pass("Falha ao efetuar pagamento de boleto neon.");
			else {
				closeAppium();
				fail("Pagamento de boleto Neon feita com sucesso!") ;
			}
		}
	}
	
	public void validarMsgAgendamentoNaoConcluido (boolean resultadoEsperado) throws Exception {
		boolean existeMsgErroAgendamento;
		Thread.sleep(5000);

		existeMsgErroAgendamento = getGenerics().exists("text", "agendamento não concluído", Constants.ANDROID_TEXT_VIEW);
		
		// Registra se transferencia foi realizado
		// (dependendo do resultado esperado do teste)
		if (resultadoEsperado) {
			if (existeMsgErroAgendamento) {
				pass("Mensagem de agendamento não concluído validada com sucesso!");
				
				getScreenshot().CaptureScreenshot();
				getTextView().clickByText("OK, ENTENDI");
				
				// Volta para tela inicial
				getScreenshot().CaptureScreenshot();
				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
				getScreenshot().CaptureScreenshot();
				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
				getScreenshot().CaptureScreenshot();
				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
			}
			else {
				closeAppium();
				fail("Não foi possível validar mensagem de agendamento não concluído.");
			}
		}
		else {
			if (existeMsgErroAgendamento)	pass("Não foi possível validar mensagem de agendamento não concluído.");
			else {
				closeAppium();
				fail("Mensagem de agendamento não concluído validada com sucesso!");
			}
		}
	}
	
	
	public void validarEdicaoDataAgendamento (boolean resultadoEsperado, String dataAlterada) throws Exception {
			boolean diaAlterado;
			String dataTvDate;
			
			Thread.sleep(5000);
			
			getScreenshot().CaptureScreenshot();
			homeView.clicarOpcaoMenu("saldo");
			
			getScreenshot().CaptureScreenshot();
			swipeParaAbaFuturos();
			
			getScreenshot().CaptureScreenshot();
			clicaPrimeiroAgendamentoLista();
			
			getScreenshot().CaptureScreenshot();
			clicaBotaoCalendario();

			dataTvDate = getTextView().getAttribute("id", "br.com.neon:id/tv_date", "text");
			
			if (dataTvDate.substring(0, 2).equals(dataAlterada.substring(0, 2))) diaAlterado = true; 
			else diaAlterado = false;
			
			// Registra se transferencia foi realizado
			// (dependendo do resultado esperado do teste)
			if (resultadoEsperado) {
				if (diaAlterado) {
					pass("Edição data agendamento realizada com sucesso!");
					
					getScreenshot().CaptureScreenshot();
					getGenerics().pressKeyCode(AndroidKeyCode.BACK);
					getScreenshot().CaptureScreenshot();
					getGenerics().pressKeyCode(AndroidKeyCode.BACK);
				}
				else {
					closeAppium();
					fail("Edição data agendamento falhou.");
				}
			}
			else {
				if (diaAlterado)	pass("Edição data agendamento falhou");
				else {
					closeAppium();
					fail("Edição data agendamento realizada com sucesso!");
				}
			}
	}
	
	public void validarEdicaoAgendamento (boolean resultadoEsperado) throws Exception {
		boolean existeMsgErroAgendamento;
		Thread.sleep(5000);

		existeMsgErroAgendamento = getGenerics().exists("id", "br.com.neon:id/rl_btn_schedule", Constants.ANDROID_RELATIVE_LAYOUT);
		
		// Registra se transferencia foi realizado
		// (dependendo do resultado esperado do teste)
		if (resultadoEsperado) {
			if (existeMsgErroAgendamento) {
				pass("Edição agendamento realizada com sucesso!");
				
				getScreenshot().CaptureScreenshot();
				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
				getScreenshot().CaptureScreenshot();
				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
			}
			else {
				closeAppium();
				fail("Edição agendamento falhou.");
			}
		}
		else {
			if (existeMsgErroAgendamento)	pass("Edição agendamento falhou");
			else {
				closeAppium();
				fail("Edição agendamento realizada com sucesso!");
			}
		}
	}

	public void validarAtualizacaoTotalAgendado(boolean resultadoEsperado, String valorTransf) throws Exception {
		Thread.sleep(5000);
		boolean saldoTotalAgendado = false;
		// Valida total agendado
		saldoTotalAgendado = validaTotalAgendado(totalAgendadoInicial, totalAgendadoFinal, Double.parseDouble(valorTransf));

		// Registra se transferencia foi realizado
		// (dependendo do resultado esperado do teste)
		if (resultadoEsperado) {
			if (saldoTotalAgendado)	pass("Agendamento efetuado com sucesso!");
			else {
				closeAppium();
				fail("Falha ao atualizar saldo agendamento.");
			}
		}
		else {
			if (saldoTotalAgendado)	pass("Agendamento efetuado com sucesso!");
			else {
				closeAppium();
				fail("Falha ao atualizar saldo agendamento.");
			}
		}
	}
	
	public void validarBotaoPagamentoDisabled (boolean resultadoEsperado) throws Exception {
		boolean mudouTela;
		Thread.sleep(5000);

		mudouTela = getGenerics().exists("text", "pagar quando", Constants.ANDROID_TEXT_VIEW);
		
		// Registra se transferencia foi realizado
		// (dependendo do resultado esperado do teste)
		if (resultadoEsperado) {
			if (mudouTela) {
				pass("Pagamento de agendamento agendado para hoje com sucesso!");
				
				// Voltar tela inicial
				getScreenshot().CaptureScreenshot();
				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
				getScreenshot().CaptureScreenshot();
				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
				getScreenshot().CaptureScreenshot();
				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
			}
			else {
				closeAppium();
				fail("Pagamento de agendamento agendado para hoje com falha.");
			}
		}
		else {
			if (mudouTela)	pass("Pagamento de agendamento agendado para hoje com sucesso!");
			else {
				closeAppium();
				fail("Pagamento de agendamento agendado para hoje com falha.");
			}
		}
	}

	public void validarComprovante(boolean resultadoEsperado) {
		// Registra se transferencia foi realizado
		// (dependendo do resultado esperado do teste)
		if (resultadoEsperado) {
			if (this.comprovanteValido) {
				pass("Comprovante validado com sucesso!");
			}
			else {
				closeAppium();
				fail("Comprovante com inconsistência.");
			}
		}
		else {
			if (this.comprovanteValido) {
				pass("Comprovante com inconsistência.");
			}
			else {
				closeAppium();
				fail("Comprovante validado com sucesso!");
			}
		}
	}

	public void validarPagamentoBoletoAposHorario20h30(boolean resultadoEsperado) throws Exception {
		boolean existemMsgHorarioLimite = false;
		
		// Procura se existe texto na tela
		existemMsgHorarioLimite = getGenerics().exists("text", "agendar pagamento", Constants.ANDROID_TEXT_VIEW);
		
		// Registra se fez logout com sucesso ou não
		// (dependendo do resultado esperado do teste)
		if (resultadoEsperado) {
			if (existemMsgHorarioLimite)	{
				pass("Teste de validação de mensagem pagamento apos período 20h30 validado com sucesso!");
				
				//TODO: bot�o "ok, entendi" perde referencia na tela
//				getDriver().findElement(By.xpath("//android.widget.TextView[contains@text='OK, ENTENDI']"));
				
				getTextView().clickByIndex(2);
//				getTextView().clickByText("OK, ENTENDI");
				// Volta tela inicial
				getScreenshot().CaptureScreenshot();
				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
				getScreenshot().CaptureScreenshot();
				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
				getScreenshot().CaptureScreenshot();
				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
				getScreenshot().CaptureScreenshot();
				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
			}
			else {
				closeAppium();
				fail("Teste de validação de mensagem pagamento apos período 20h30 validado com falha.");
			}
		}
		else {
			if (existemMsgHorarioLimite)	pass("Teste de validação de mensagem pagamento apos período 20h30 validado com falha.");
			else {
				closeAppium();
				fail("Teste de validação de mensagem pagamento apos período 20h30 validado com sucesso!");
			}
		}
	}
	
	public void validarPagamentoBoletoAposHorario00h(boolean resultadoEsperado) throws Exception {
		boolean existemMsgHorarioLimite = false;
		
		// Procura se existe texto na tela
		existemMsgHorarioLimite = getGenerics().exists("text", "agendar pagamento", Constants.ANDROID_TEXT_VIEW);
		
		// Registra se fez logout com sucesso ou não
		// (dependendo do resultado esperado do teste)
		if (resultadoEsperado) {
			if (existemMsgHorarioLimite)	{
				pass("Teste de validação de mensagem pagamento apos período 00h validado com sucesso!");
				
				//TODO: bot�o "ok, entendi" perde referencia na tela
//				getDriver().findElement(By.xpath("//android.widget.TextView[contains@text='OK, ENTENDI']"));
				
				getTextView().clickByIndex(2);
//				getTextView().clickByText("OK, ENTENDI");
				// Volta tela inicial
				getScreenshot().CaptureScreenshot();
				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
				getScreenshot().CaptureScreenshot();
				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
				getScreenshot().CaptureScreenshot();
				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
				getScreenshot().CaptureScreenshot();
				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
			}
			else {
				closeAppium();
				fail("Teste de validação de mensagem pagamento apos período 00h validado com falha.");
			}
		}
		else {
			if (existemMsgHorarioLimite)	pass("Teste de validação de mensagem pagamento apos período 00h validado com falha.");
			else {
				closeAppium();
				fail("Teste de validação de mensagem pagamento apos período 00h validado com sucesso!");
			}
		}
	}

	public void validarAgendamentosAbaFuturos(boolean resultadoEsperado) throws Exception {
		boolean existemBoletoAbaFuturos = false;
		
		getScreenshot().CaptureScreenshot();
		homeView.clicarOpcaoMenu("saldo");
		
		getScreenshot().CaptureScreenshot();
		swipeParaAbaFuturos();
		
		// Procura se existe texto na tela
		getScreen().scrolltotext("Banco Neon S/A");
		existemBoletoAbaFuturos  = getGenerics().exists("text", "Banco Neon S/A", Constants.ANDROID_TEXT_VIEW);
		
		// Registra se fez logout com sucesso ou não
		// (dependendo do resultado esperado do teste)
		if (resultadoEsperado) {
			if (existemBoletoAbaFuturos)	{
				pass("Teste de validação de mensagem pagamento apos período 00h validado com sucesso!");
				
				getScreenshot().CaptureScreenshot();
				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
			}
			else {
				closeAppium();
				fail("Teste de validação de mensagem pagamento apos período 00h validado com falha.");
			}
		}
		else {
			if (existemBoletoAbaFuturos)	pass("Teste de validação de mensagem pagamento apos período 00h validado com falha.");
			else {
				closeAppium();
				fail("Teste de validação de mensagem pagamento apos período 00h validado com sucesso!");
			}
		}
	}

	
//	public void validarAtualizacaoSaldoFuturo (boolean resultadoEsperado, String valorPagamento) throws InterruptedException {
//		Thread.sleep(5000);
//		boolean saldoValido = false;
//		// Valida saldo
//		saldoValido = validaSaldo(saldoInicial, saldoFinal, Double.parseDouble(valorPagamento));
//
//		// Registra se transferencia foi realizado
//		// (dependendo do resultado esperado do teste)
//		if (resultadoEsperado) {
//			if (saldoValido)	pass("Transferências entre contas Neon feita com sucesso!");
//			else {
//				closeAppium();
//				fail("Falha ao efetuar transferência entre contas Neon.");
//			}
//		}
//		else {
//			if (saldoValido)	pass("Transferências entre contas Neon não validado com sucesso.");
//			else {
//				closeAppium();
//				fail("Sucesso ao validar transferência entre contas Neon.");
//			}
//		}
//	}
}
