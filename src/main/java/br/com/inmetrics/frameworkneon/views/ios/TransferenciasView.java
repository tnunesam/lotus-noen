//package br.com.inmetrics.frameworkneon.views.ios;
//
//import static org.junit.Assert.fail;
//
//import br.com.inmetrics.frameworkneon.Neon;
//import br.com.inmetrics.frameworkneon.NeonIOS;
//import br.com.inmetrics.frameworkneon.views.ios.HomeView;
//import br.com.inmetrics.frameworkneon.views.ios.UtilView;
//import br.com.inmetrics.neon.constants.Constants;
//import io.appium.java_client.android.AndroidKeyCode;
//
//public class TransferenciasView extends NeonIOS {
//	private double saldoInicial;
//	private double saldoFinal;
//	private double limiteInicial;
//	private double limiteFinal;
//	private boolean comprovanteValido;
//	UtilView utilView = new UtilView();a
//	HomeView homeView = new HomeView();
//	
//	//
//	//	Métodos CT's
//	//
//	public void compartilharComprovante() throws Exception {
//		getScreenshot().CaptureScreenshot();
//		homeView.clicarOpcaoMenu("saldo");
//		
//		getScreenshot().CaptureScreenshot();
//		getImageView().clickById("br.com.neon:id/icon_image");
//		
//		getScreenshot().CaptureScreenshot();
//		getImageView().clickById("br.com.neon:id/receipt_image");
//		
//		getScreenshot().CaptureScreenshot();
//		getTextView().clickByText("COMPARTILHAR");
//	}
//	
//	public void efetuarTransferenciaOutrosBancos(String nomeFavorecido, String cpfcnpjDestino, String bancoDestino, String agenciaDestino,
//			String contaDestino, String tipoContaDestino, String valorTransf) throws Exception {
//		getScreenshot().CaptureScreenshot();
//		this.saldoInicial = getSaldo();
//		
//		getScreenshot().CaptureScreenshot();
//		homeView.clicarOpcaoMenu("transferências");
//		
//		if (verificarContaContatoExiste(nomeFavorecido, contaDestino)) {
//			getScreenshot().CaptureScreenshot();
//			selecionarFavorecido(nomeFavorecido);
//			
//			getScreenshot().CaptureScreenshot();
//			selecionarContaFavorecido(contaDestino);
//			
//			getScreenshot().CaptureScreenshot();
//			preencherValorTransf(valorTransf);
//			
//			getScreenshot().CaptureScreenshot();
//			confirmarTransf();
//			
//			getScreenshot().CaptureScreenshot();
//			swipeConfirmarTransf();
//			
//			getScreenshot().CaptureScreenshot();
//			utilView.setSenha(getDt().getStringOf("SENHA_CARTAO"));
//			
//			getScreenshot().CaptureScreenshot();
//			fechaTransferenciaFeita();
//			
//			getScreenshot().CaptureScreenshot();
//			this.saldoFinal = getSaldo();
//		} 
//		else {
//			getScreenshot().CaptureScreenshot();
//			clicaBotaoNovoContato();
//			
//			getScreenshot().CaptureScreenshot();
//			setCPFCNPJ(cpfcnpjDestino);
//			
//			getScreenshot().CaptureScreenshot();
//			confirmarNomeNovoContato();
//			
//			getScreenshot().CaptureScreenshot();
//			selecionarBancoContato(bancoDestino);
//			
//			getScreenshot().CaptureScreenshot();
//			setAgencia(agenciaDestino);
//			
//			getScreenshot().CaptureScreenshot();
//			setConta(contaDestino, tipoContaDestino);
//			
//			Thread.sleep(2000);
//			
//			if (getGenerics().exists("text", "informações repetidas", Constants.ANDROID_TEXT_VIEW)){
//				getScreenshot().CaptureScreenshot();
//				getTextView().clickByText("OK, ENTENDI");
//			}
//			
//			getScreenshot().CaptureScreenshot();
//			preencherValorTransf(valorTransf);
//			
//			getScreenshot().CaptureScreenshot();
//			confirmarTransf();
//			
//			getScreenshot().CaptureScreenshot();
//			swipeConfirmarTransf();
//			
//			getScreenshot().CaptureScreenshot();
//			utilView.setSenha(getDt().getStringOf("SENHA_CARTAO"));
//			
////		getScreenshot().CaptureScreenshot();
////		validaComprovante(nomeOrigem, contaOrigem, nomeFavorecido, contaDestino, cpfDestino, valorTransf);
//			
//			getScreenshot().CaptureScreenshot();
//			fechaTransferenciaFeita();
//			
//			getScreenshot().CaptureScreenshot();
//			this.saldoFinal = getSaldo();
//		}
//		
//	}
//	
//	public void efetuarTransferenciaOutrosBancosAposHorario(String cpfcnpjDestino, String bancoDestino, String agenciaDestino,
//			String contaDestino, String tipoContaDestino, String valorTransf) throws Exception {
//		getScreenshot().CaptureScreenshot();
//		homeView.clicarOpcaoMenu("transferências");
//		
//		getScreenshot().CaptureScreenshot();
//		clicaBotaoNovoContato();
//		
//		getScreenshot().CaptureScreenshot();
//		setCPFCNPJ(cpfcnpjDestino);
//		
//		getScreenshot().CaptureScreenshot();
//		confirmarNomeNovoContato();
//		
//		getScreenshot().CaptureScreenshot();
//		selecionarBancoContato(bancoDestino);
//		
//		getScreenshot().CaptureScreenshot();
//		setAgencia(agenciaDestino);
//		
//		getScreenshot().CaptureScreenshot();
//		setConta(contaDestino, tipoContaDestino);
//		
//		getScreenshot().CaptureScreenshot();
//		preencherValorTransf(valorTransf);
//		
//		getScreenshot().CaptureScreenshot();
//		confirmarTransf();
//		
//		getScreenshot().CaptureScreenshot();
//		swipeConfirmarTransf();
//		
//		getScreenshot().CaptureScreenshot();
//		utilView.setSenha(getDt().getStringOf("SENHA_CARTAO"));
//	}
//	
//	public void efetuarTransferenciaOutrosBancosAcimaLimiteDiario(String nomeFavorecido, String cpfcnpjDestino, String bancoDestino, String agenciaDestino,
//			String contaDestino, String tipoContaDestino, String valorTransf) throws Exception {
//		getScreenshot().CaptureScreenshot();
//		this.saldoInicial = getSaldo();
//		
//		getScreenshot().CaptureScreenshot();
//		homeView.clicarOpcaoMenu("transferências");
//		
//		if (verificarContaContatoExiste(nomeFavorecido, contaDestino)) {
//			getScreenshot().CaptureScreenshot();
//			selecionarFavorecido(nomeFavorecido);
//			
//			getScreenshot().CaptureScreenshot();
//			selecionarContaFavorecido(contaDestino);
//			
//			getScreenshot().CaptureScreenshot();
//			preencherValorTransf(valorTransf);
//			
//			getScreenshot().CaptureScreenshot();
//			confirmarTransf();
//		} 
//		else {
//			getScreenshot().CaptureScreenshot();
//			clicaBotaoNovoContato();
//			
//			getScreenshot().CaptureScreenshot();
//			setCPFCNPJ(cpfcnpjDestino);
//			
//			getScreenshot().CaptureScreenshot();
//			confirmarNomeNovoContato();
//			
//			getScreenshot().CaptureScreenshot();
//			selecionarBancoContato(bancoDestino);
//			
//			getScreenshot().CaptureScreenshot();
//			setAgencia(agenciaDestino);
//			
//			getScreenshot().CaptureScreenshot();
//			setConta(contaDestino, tipoContaDestino);
//			
//			Thread.sleep(2000);
//			
//			if (getGenerics().exists("text", "informações repetidas", Constants.ANDROID_TEXT_VIEW)){
//				getScreenshot().CaptureScreenshot();
//				getTextView().clickByText("OK, ENTENDI");
//			}
//			
//			getScreenshot().CaptureScreenshot();
//			preencherValorTransf(valorTransf);
//			
//			getScreenshot().CaptureScreenshot();
//			confirmarTransf();
//		}
//		
//	}
//	
//	public void efetuarTransferenciaOutrosBancosAcimaSaldoDisponivel(String nomeFavorecido, String cpfcnpjDestino, String bancoDestino, String agenciaDestino,
//			String contaDestino, String tipoContaDestino, String valorTransf) throws Exception {
//		getScreenshot().CaptureScreenshot();
//		this.saldoInicial = getSaldo();
//		
//		getScreenshot().CaptureScreenshot();
//		homeView.clicarOpcaoMenu("transferências");
//		
//		if (verificarContaContatoExiste(nomeFavorecido, contaDestino)) {
//			getScreenshot().CaptureScreenshot();
//			selecionarFavorecido(nomeFavorecido);
//			
//			getScreenshot().CaptureScreenshot();
//			selecionarContaFavorecido(contaDestino);
//			
//			getScreenshot().CaptureScreenshot();
//			preencherValorTransf(valorTransf);
//			
//			getScreenshot().CaptureScreenshot();
//			confirmarTransf();
//		}
//		else {
//			getScreenshot().CaptureScreenshot();
//			clicaBotaoNovoContato();
//			
//			getScreenshot().CaptureScreenshot();
//			setCPFCNPJ(cpfcnpjDestino);
//			
//			getScreenshot().CaptureScreenshot();
//			confirmarNomeNovoContato();
//			
//			getScreenshot().CaptureScreenshot();
//			selecionarBancoContato(bancoDestino);
//			
//			getScreenshot().CaptureScreenshot();
//			setAgencia(agenciaDestino);
//			
//			getScreenshot().CaptureScreenshot();
//			setConta(contaDestino, tipoContaDestino);
//			
//			Thread.sleep(2000);
//			
//			if (getGenerics().exists("text", "informações repetidas", Constants.ANDROID_TEXT_VIEW)){
//				getScreenshot().CaptureScreenshot();
//				getTextView().clickByText("OK, ENTENDI");
//			}
//			
//			getScreenshot().CaptureScreenshot();
//			preencherValorTransf(valorTransf);
//			
//			getScreenshot().CaptureScreenshot();
//			confirmarTransf();
//		}
//		
//	}
//	
//	public void efetuarTransferenciaCompartilhamentoComprovante(String nomeFavorecido, String contaFavorecido, String valorTransf,
//			String nomeOrigem, String contaOrigem, String contaDestino, String cpfDestino) throws Exception {
//		getScreenshot().CaptureScreenshot();
//		homeView.clicarOpcaoMenu("transferências");
//		
//		getScreenshot().CaptureScreenshot();
//		selecionarFavorecido(nomeFavorecido);
//		
//		getScreenshot().CaptureScreenshot();
//		selecionarContaFavorecido(contaFavorecido);
//		
//		getScreenshot().CaptureScreenshot();
//		preencherValorTransf(valorTransf);
//		
//		getScreenshot().CaptureScreenshot();
//		confirmarTransf();
//		
//		getScreenshot().CaptureScreenshot();
//		swipeConfirmarTransf();
//		
//		getScreenshot().CaptureScreenshot();
//		utilView.setSenha(getDt().getStringOf("SENHA_CARTAO"));
//	}
//	
//	public void efetuarTransferenciaContaNeon(String nomeFavorecido, String contaFavorecido, String valorTransf,
//			boolean validaComprovante, String comprovante) throws Exception {
//		getScreenshot().CaptureScreenshot();
//		this.saldoInicial = getSaldo();
//		
//		getScreenshot().CaptureScreenshot();
//		homeView.clicarOpcaoMenu("transferências");
//		
//		Thread.sleep(6000);
//		
//		if (!verificarContatoExiste(nomeFavorecido)) {
//			fail("Falha ao efetuar transferência entre contas Neon - Nome Favorecido n�o existe na lista de contatos.");
//			closeAppium();
//		} 
//		
//		getScreenshot().CaptureScreenshot();
//		selecionarFavorecido(nomeFavorecido);
//		
//		getScreenshot().CaptureScreenshot();
//		selecionarContaFavorecido(contaFavorecido);
//		
//		getScreenshot().CaptureScreenshot();
//		preencherValorTransf(valorTransf);
//		
//		getScreenshot().CaptureScreenshot();
//		confirmarTransf();
//		
//		getScreenshot().CaptureScreenshot();
//		swipeConfirmarTransf();
//		
//		getScreenshot().CaptureScreenshot();
//		utilView.setSenha(getDt().getStringOf("SENHA_CARTAO"));
//		
////		getScreenshot().CaptureScreenshot();
////		validaComprovante(nomeOrigem, contaOrigem, nomeFavorecido, contaDestino, cpfDestino, valorTransf);
//		
//		if (validaComprovante) {
//			this.comprovanteValido = false;
//			if (getGenerics().validaComprovante(getTextView().findAllStrings(), comprovante))
//				this.comprovanteValido = true;
//		}
//		
//		Thread.sleep(5000);
//		
//		getScreenshot().CaptureScreenshot();
//		fechaTransferenciaFeita();
//		
//		Thread.sleep(2000);
//
//		getScreenshot().CaptureScreenshot();
//		this.saldoFinal = getSaldo();
//	}
//	
//	public void efetuarTransferenciaContaNeon2(String nomeFavorecido, String contaFavorecido, String valorTransf) throws Exception {
//		this.limiteInicial = getLimiteDiario(nomeFavorecido, contaFavorecido);
//		
//		getScreenshot().CaptureScreenshot();
//		homeView.clicarOpcaoMenu("transferências");
//		
//		if (!verificarContatoExiste(nomeFavorecido)) {
//			fail("Falha ao efetuar transferência entre contas Neon - Nome Favorecido não existe na lista de contatos.");
//			closeAppium();
//		} 
//		
//		getScreenshot().CaptureScreenshot();
//		selecionarFavorecido(nomeFavorecido);
//		
//		Thread.sleep(2000);
//		
//		getScreenshot().CaptureScreenshot();
//		selecionarContaFavorecido(contaFavorecido);
//		
//		getScreenshot().CaptureScreenshot();
//		preencherValorTransf(valorTransf);
//		
//		getScreenshot().CaptureScreenshot();
//		confirmarTransf();
//		
//		getScreenshot().CaptureScreenshot();
//		swipeConfirmarTransf();
//		
//		getScreenshot().CaptureScreenshot();
//		utilView.setSenha(getDt().getStringOf("SENHA_CARTAO"));
//		
////		getScreenshot().CaptureScreenshot();
////		validaComprovante(nomeOrigem, contaOrigem, nomeFavorecido, contaDestino, cpfDestino, valorTransf);
//		
//		getScreenshot().CaptureScreenshot();
//		fechaTransferenciaFeita();
//
//		getScreenshot().CaptureScreenshot();
//		this.limiteFinal = getLimiteDiario(nomeFavorecido, contaFavorecido);
//	}
//	
//	public void efetuarTransferenciaContaNeonAcimaSaldoLimiteDisponivel(String nomeFavorecido, 
//								String contaFavorecido, String valorTransf) throws Exception {
//		getScreenshot().CaptureScreenshot();
//		this.saldoInicial = getSaldo();
//		
//		getScreenshot().CaptureScreenshot();
//		homeView.clicarOpcaoMenu("transferências");
//		
//		getScreenshot().CaptureScreenshot();
//		selecionarFavorecido(nomeFavorecido);
//		
//		getScreenshot().CaptureScreenshot();
//		selecionarContaFavorecido(contaFavorecido);
//		
//		getScreenshot().CaptureScreenshot();
//		preencherValorTransf(valorTransf);
//		
//		getScreenshot().CaptureScreenshot();
//		confirmarTransf();
//	}
//	
//	public void efetuarTransferenciaContaNeonRepetida(String nomeFavorecido, String contaFavorecido, String valorTransf) throws Exception {
//		getScreenshot().CaptureScreenshot();
//		homeView.clicarOpcaoMenu("transferências");
//		
//		getScreenshot().CaptureScreenshot();
//		selecionarFavorecido(nomeFavorecido);
//		
//		getScreenshot().CaptureScreenshot();
//		selecionarContaFavorecido(contaFavorecido);
//		
//		getScreenshot().CaptureScreenshot();
//		preencherValorTransf(valorTransf);
//		
//		getScreenshot().CaptureScreenshot();
//		confirmarTransf();
//		
//		getScreenshot().CaptureScreenshot();
//		swipeConfirmarTransf();
//		
//		getScreenshot().CaptureScreenshot();
//		utilView.setSenha(getDt().getStringOf("SENHA_CARTAO"));
//		
//		Thread.sleep(9000);
//	}
//	
//	public void efetuarTransferenciaParaNovoContato(String valorTransf, String bancoDestino, String agenciaDestino, 
//			String contaDestino, String cpfDestino) throws Exception {
//		getScreenshot().CaptureScreenshot();
//		this.saldoInicial = getSaldo();
//		
//		getScreenshot().CaptureScreenshot();
//		homeView.clicarOpcaoMenu("transferências");
//		
//		getScreenshot().CaptureScreenshot();
//		clicaBotaoNovoContato();
//		
//		getScreenshot().CaptureScreenshot();
//		setCPFCNPJ(cpfDestino);
//		
//		getScreenshot().CaptureScreenshot();
//		confirmarNomeNovoContato();
//		
//		getScreenshot().CaptureScreenshot();
//		selecionarBancoContato(bancoDestino);
//		
//		getScreenshot().CaptureScreenshot();
//		setAgencia(agenciaDestino);
//		
//		getScreenshot().CaptureScreenshot();
//		setConta2(contaDestino);
//		
//		getScreenshot().CaptureScreenshot();
//		preencherValorTransf(valorTransf);
//		
//		getScreenshot().CaptureScreenshot();
//		confirmarTransf();
//		
//		getScreenshot().CaptureScreenshot();
//		swipeConfirmarTransf();
//		
//		getScreenshot().CaptureScreenshot();
//		utilView.setSenha(getDt().getStringOf("SENHA_CARTAO"));
//		
////		getScreenshot().CaptureScreenshot();
////		validaComprovante(nomeOrigem, contaOrigem, nomeFavorecido, contaDestino, cpfDestino, valorTransf);
//		
//		getScreenshot().CaptureScreenshot();
//		fechaTransferenciaFeita();
//		
//		getScreenshot().CaptureScreenshot();
//		this.saldoFinal = getSaldo();
//	}
//	
//	public void addContato(String cpfCnpj, String banco, String agencia, String conta, String tipoConta) throws Exception {
//		Thread.sleep(3000);
//		getScreenshot().CaptureScreenshot();
//		homeView.clicarOpcaoMenu("transferências");
//		
//		getScreenshot().CaptureScreenshot();
//		clicaBotaoNovoContato();
//		
//		getScreenshot().CaptureScreenshot();
//		setCPFCNPJ(cpfCnpj);
//		
//		getScreenshot().CaptureScreenshot();
//		confirmarNomeNovoContato();
//		
//		getScreenshot().CaptureScreenshot();
//		selecionarBancoContato(banco);
//		
//		getScreenshot().CaptureScreenshot();
//		setAgencia(agencia);
//		
//		getScreenshot().CaptureScreenshot();
//		setConta2(conta);
//		
//		Thread.sleep(5000);
//		
//		getScreenshot().CaptureScreenshot();
//		fecharTeclado();
//		
//		// Voltar para tela inicial
//		Thread.sleep(1000);
//		getScreenshot().CaptureScreenshot();
//		getGenerics().pressKeyCode(AndroidKeyCode.BACK);
//		
//		getScreenshot().CaptureScreenshot();
//		getGenerics().pressKeyCode(AndroidKeyCode.BACK);
//	}
//	
//	public void alterarNomeContato(String nomeFavorecido, String novoNome) throws Exception {
//		Thread.sleep(3000);
//		getScreenshot().CaptureScreenshot();
//		homeView.clicarOpcaoMenu("transferências");
//		
//		getScreenshot().CaptureScreenshot();
//		selecionarFavorecido(nomeFavorecido);
//		
//		getScreenshot().CaptureScreenshot();
//		clicaBotaoEditar();
//		
//		getScreenshot().CaptureScreenshot();
//		apagarCampoContato();
//		
//		getScreenshot().CaptureScreenshot();
//		setNomeContato(novoNome);
//		
//		getScreenshot().CaptureScreenshot();
//		getScreen().hideKeyboard();	
//		
//		getScreenshot().CaptureScreenshot();
//		clicaBotaoEditar();
//		
//		Thread.sleep(5000);
//		
//		getScreenshot().CaptureScreenshot();
//		getGenerics().pressKeyCode(AndroidKeyCode.BACK);
//		
//		getScreenshot().CaptureScreenshot();
//		getGenerics().pressKeyCode(AndroidKeyCode.BACK);
//		
//		getScreenshot().CaptureScreenshot();
//		getGenerics().pressKeyCode(AndroidKeyCode.BACK);
//		
//		getScreenshot().CaptureScreenshot();
//		getGenerics().pressKeyCode(AndroidKeyCode.BACK);
//	}
//	
//	public void excluirContato(String nomeFavorecido) throws Exception {
//		Thread.sleep(3000);
//		getScreenshot().CaptureScreenshot();
//		homeView.clicarOpcaoMenu("transferências");
//		
//		getScreenshot().CaptureScreenshot();
//		selecionarFavorecido(nomeFavorecido);
//		
//		getScreenshot().CaptureScreenshot();
//		clicaBotaoEditar();
//		
//		getScreenshot().CaptureScreenshot();
//		clicaBotaoExcluirContato();
//		
//		// Voltar tela inicial
//		getScreenshot().CaptureScreenshot();
//		getGenerics().pressKeyCode(AndroidKeyCode.BACK);
//		
//		getScreenshot().CaptureScreenshot();
//		getGenerics().pressKeyCode(AndroidKeyCode.BACK);
//		
//		getScreenshot().CaptureScreenshot();
//		getGenerics().pressKeyCode(AndroidKeyCode.BACK);
//	}
//	
//	public void pesquisarContato(String nomeContato) throws Exception {
//		getScreenshot().CaptureScreenshot();
//		homeView.clicarOpcaoMenu("transferências");
//		
//		getScreenshot().CaptureScreenshot();
//		clicarBotaoPesquisarContato();
//		
//		getScreenshot().CaptureScreenshot();
//		preencherContatoPesquisa(nomeContato);
//	}
//	
//	public void criarNovaContaContato(String nomeFavorecido, String banco, String agencia, String conta, String tipo) throws Exception {
//		Thread.sleep(3000);
//		
//		getScreenshot().CaptureScreenshot();
//		homeView.clicarOpcaoMenu("transferências");
//		
//		getScreenshot().CaptureScreenshot();
//		selecionarFavorecido(nomeFavorecido);
//		
//		getScreenshot().CaptureScreenshot();
//		selecionarAdicionarNovaConta();
//		
//		getScreenshot().CaptureScreenshot();
//		selecionarBancoContato(banco);
//		
//		getScreenshot().CaptureScreenshot();
//		setAgencia(agencia);
//		
//		getScreenshot().CaptureScreenshot();
//		setConta2(conta);
//		
//		Thread.sleep(3000);
//		
//		getScreenshot().CaptureScreenshot();
//		fecharTeclado();
//		
//		// Voltar para tela inicial
//		Thread.sleep(1000);
//		getScreenshot().CaptureScreenshot();
//		getGenerics().pressKeyCode(AndroidKeyCode.BACK);
//		
//		getScreenshot().CaptureScreenshot();
//		getGenerics().pressKeyCode(AndroidKeyCode.BACK);
//		
//		getScreenshot().CaptureScreenshot();
//		getGenerics().pressKeyCode(AndroidKeyCode.BACK);
//		
//		getScreenshot().CaptureScreenshot();
//		getGenerics().pressKeyCode(AndroidKeyCode.BACK);
//	}
//	
//	public void excluirContaContato(String nomeFavorecido, String conta) throws Exception {
//		getScreenshot().CaptureScreenshot();
//		homeView.clicarOpcaoMenu("transferências");
//		
//		getScreenshot().CaptureScreenshot();
//		selecionarFavorecido(nomeFavorecido);
//		
//		getScreenshot().CaptureScreenshot();
//		clicaBotaoEditar();
//		
//		getScreenshot().CaptureScreenshot();
//		selecionarContaExcluir(conta);
//		
//		getScreenshot().CaptureScreenshot();
//		confirmarExclusaoConta();
//		
//		getScreenshot().CaptureScreenshot();
//		getGenerics().pressKeyCode(AndroidKeyCode.BACK);
//		
//		getScreenshot().CaptureScreenshot();
//		getGenerics().pressKeyCode(AndroidKeyCode.BACK);
//		
//		getScreenshot().CaptureScreenshot();
//		getGenerics().pressKeyCode(AndroidKeyCode.BACK);
//		
//		getScreenshot().CaptureScreenshot();
//		getGenerics().pressKeyCode(AndroidKeyCode.BACK);
//	}
//
//	//
//	//	Métodos de a��es da tela
//	//	
//	public double getSaldoInicial() {
//		return this.saldoInicial;
//	}
//	
//	public double getSaldoFinal() {
//		return this.saldoFinal;
//	}
//	
//	
//	
//	public void preencherContatoPesquisa(String nomeContato) throws Exception {
//		getEditText().insertTextById("br.com.neon:id/search_src_text", nomeContato);
//	}
//	
//	public void clicarBotaoPesquisarContato() throws Exception {
//		getTextView().clickById("br.com.neon:id/action_search");
//	}
//	
//	public void confirmarExclusaoConta() throws Exception {
//		getTextView().clickByText("SIM, EXCLUIR");
//	}
//	
//	public void selecionarContaExcluir(String conta) throws Exception {
//		getTextView().clickByText("Conta " + conta);
//	}
//	
//	public void selecionarAdicionarNovaConta () throws Exception {
//		getTextView().clickByText("Adicionar nova conta");
//	}
//	
//	public void apagarCampoContato() throws Exception {
//		getEditText().clear("br.com.neon:id/txtNameEdit");
//	}
//	
//	public void setCPFCNPJ(String cpfcnpj) throws Exception {
//		getEditText().insertTextById("br.com.neon:id/tx_cpf_cnpj", cpfcnpj);
//		getScreenshot().CaptureScreenshot();
//		getTextView().clickByText("PRÓXIMO");
//	}
//	
//	public void confirmarNomeNovoContato() throws Exception {
//		while (!getGenerics().exists("text", "confirma o nome", Constants.ANDROID_TEXT_VIEW)){//android.widget.RelativeLayout[@index='4']
//			Thread.sleep(1000);
//		}
//		Thread.sleep(2000);
//		getScreen().ClickPorcentagemAbaixoCentro(0.61);
////		getScreen().ClickAtScreen(244, 470);
////		getTextView().findAllStrings();
////		getScreen().swipeLeft("//*[@resource-id='br.com.neon:id/button_one']");
////		getTextView().clickByIndex(2);
////		getDriver().findElement(By.xpath("//android.widget.TextView[@text='SIM']")).click();
//	}
//	
//	public void selecionarBancoContato(String banco) throws Exception {
//		Thread.sleep(2000);
//		getImageView().clickById("br.com.neon:id/search_button");
//		
//		getScreenshot().CaptureScreenshot();
//		getEditText().insertTextById("br.com.neon:id/search_src_text", banco);
//		
//		getScreenshot().CaptureScreenshot();
//		getTextView().clickById("br.com.neon:id/tx_banco_name");
//	}
//	
//	public void setAgencia(String agencia) throws Exception {
//		getEditText().insertTextById("br.com.neon:id/tx_agencia_number", agencia);
//		getScreenshot().CaptureScreenshot();
//		getTextView().clickByText("PRÓXIMO");
//	}
//	
//	public void setConta(String conta, String tipo) throws Exception {
//		if (tipo.toLowerCase().equals("conta corrente")) {
//			getRadioButton().clickById("br.com.neon:id/rdContaCorrente");
//			getEditText().insertTextById("br.com.neon:id/tx_cc_account_number", conta);
//		}
//		if (tipo.toLowerCase().equals("poupança")) {
//			getRadioButton().clickById("br.com.neon:id/rdContaPoupanca");
//			getEditText().insertTextById("br.com.neon:id/tx_cp_account_number", conta);
//		}
//				
//		getScreenshot().CaptureScreenshot();
//		getTextView().clickByText("PRÓXIMO");
//	}
//	
//	public void setConta2(String conta) throws Exception {
//		getEditText().insertTextById("br.com.neon:id/tx_cc_account_number", conta);
//		getScreenshot().CaptureScreenshot();
//		getTextView().clickByText("PRÓXIMO");
//	}
//	
//	public void fecharTeclado() throws Exception { 
//		getScreen().hideKeyboard();
//	}
//	
//	public void setNomeContato(String novoNome) throws Exception {
//		getEditText().insertTextById("br.com.neon:id/txtNameEdit", novoNome);
//	}
//	
//	public void clicaBotaoNovoContato() throws Exception {
//		getTextView().clickByText("NOVO CONTATO");
//	}
//	
//	public void clicaBotaoEditar() throws Exception {
//		getImageView().clickById("br.com.neon:id/edit_button");
//	}
//	
//	public void clicaBotaoExcluirContato() throws Exception {
//		getTextView().clickByText("EXCLUIR CONTATO");
//		
//		getScreenshot().CaptureScreenshot();
//		getTextView().clickByText("SIM, EXCLUIR");
//		
//		Thread.sleep(5000);
//	}
//	
//	public void selecionarFavorecido(String nomeFavorecido) throws Exception {
//		Thread.sleep(6000);
//		getTextView().clickById("br.com.neon:id/action_search");
//		getScreenshot().CaptureScreenshot();
//		Thread.sleep(2000);
//		getEditText().insertTextById("br.com.neon:id/search_src_text", nomeFavorecido);
//		getScreenshot().CaptureScreenshot();
//		getGenerics().exists("text", nomeFavorecido, "android.widget.TextView");
//		getTextView().clickByText(nomeFavorecido);
//	}
//	
//	public boolean verificarContatoExiste(String nomeFavorecido) throws Exception {
//		
//		
//		
//		
//		getTextView().clickById("br.com.neon:id/action_search");
//		getScreenshot().CaptureScreenshot();
//		getEditText().insertTextById("br.com.neon:id/search_src_text", nomeFavorecido);
//		getScreenshot().CaptureScreenshot();
//		if (getGenerics().exists("text", nomeFavorecido, "android.widget.TextView")) {
//			getGenerics().pressKeyCode(AndroidKeyCode.BACK);
//			getGenerics().pressKeyCode(AndroidKeyCode.BACK);
//			return true;
//		}
//		else {
//			getGenerics().pressKeyCode(AndroidKeyCode.BACK);
//			getGenerics().pressKeyCode(AndroidKeyCode.BACK);
//			return false;
//		}
//	}
//	
//	public boolean verificarContaContatoExiste(String nomeFavorecido, String contaFavorecido) throws Exception {
//		getTextView().clickById("br.com.neon:id/action_search");
//		getScreenshot().CaptureScreenshot();
//		getEditText().insertTextById("br.com.neon:id/search_src_text", nomeFavorecido);
//		getScreenshot().CaptureScreenshot();
//		if (getGenerics().exists("text", nomeFavorecido, "android.widget.TextView")) {
//			getTextView().clickByText(nomeFavorecido);
//			if (getGenerics().exists("text", contaFavorecido, "android.widget.TextView")) {
//				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
//				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
//				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
//				return true;
//			}
//			else {
//				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
//				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
//				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
//				return false;
//			}
//		}
//		else {
//			getGenerics().pressKeyCode(AndroidKeyCode.BACK);
//			getGenerics().pressKeyCode(AndroidKeyCode.BACK);
//			return false;
//		}
//	}
//	
//	public void selecionarContaFavorecido(String contaFavorecido) throws Exception {
//		getTextView().clickByText(contaFavorecido);
//	}
//	
//	public void preencherValorTransf(String valor) throws Exception {
//		getEditText().insertTextById("br.com.neon:id/tx_value", valor);
//	}
//	
//	public void confirmarTransf() throws Exception {
//		getTextView().clickByText("PRÓXIMO");
//	}
//	
//	public void swipeConfirmarTransf() throws Exception {
//		getScreen().swipeUp("//android.widget.RelativeLayout[@index='4']");
//		//getScreen().swipe(260, 260, 400, 116);
//	}
//	
//	public void fechaTransferenciaFeita() throws Exception {
//		Thread.sleep(6000);
////		getScreen().swipe(400, 25, 30, 30);
//		
//		Thread.sleep(2000);
//		getScreenshot().CaptureScreenshot();
//		getImageView().clickById("br.com.neon:id/iv_close");
//		
//		getScreenshot().CaptureScreenshot();
//		getGenerics().pressKeyCode(AndroidKeyCode.BACK);
//		getScreenshot().CaptureScreenshot();
//		getGenerics().pressKeyCode(AndroidKeyCode.BACK);
//		getScreenshot().CaptureScreenshot();
//		getGenerics().pressKeyCode(AndroidKeyCode.BACK);
//	}
//	
//	public double getSaldo() throws NumberFormatException, Exception {
//		double saldo = Double.parseDouble(getStaticText().getAttribute("xpath", "//UIAApplication[1]/UIAWindow[1]/UIAStaticText[3]", "value").replace("$", "").replaceAll(" ", "").replaceAll("R", "").replace(".", "").replace(",", "."));
//		return saldo;
//	}
//	
//	public double getLimiteDiario(String nomeFavorecido, String contaFavorecido) throws NumberFormatException, Exception {
//		double limite;
//		
//		getScreenshot().CaptureScreenshot();
//		homeView.clicarOpcaoMenu("transferências");
//		
//		getScreenshot().CaptureScreenshot();
//		selecionarFavorecido(nomeFavorecido);
//		
//		getScreenshot().CaptureScreenshot();
//		selecionarContaFavorecido(contaFavorecido);
//		
//		limite = Double.parseDouble(getTextView().getAttribute("id", "br.com.neon:id/lb_limit_total", "text").replace(".", "").replace(",", "."));
//		getScreenshot().CaptureScreenshot();
//		getGenerics().pressKeyCode(AndroidKeyCode.BACK);
//		getScreenshot().CaptureScreenshot();
//		getGenerics().pressKeyCode(AndroidKeyCode.BACK);
//		getScreenshot().CaptureScreenshot();
//		getGenerics().pressKeyCode(AndroidKeyCode.BACK);
//		getScreenshot().CaptureScreenshot();
//		getGenerics().pressKeyCode(AndroidKeyCode.BACK);
//		getScreenshot().CaptureScreenshot();
//		getGenerics().pressKeyCode(AndroidKeyCode.BACK);
//		
//		return limite;
//	}
//
//	public boolean validaSaldo(double saldoInicial, double saldoFinal, double valorTransacao) {
//		boolean saldoValido = false;
//		double calculaDiferencaSaldo = (saldoInicial - saldoFinal) - valorTransacao;
//		if (Math.round(calculaDiferencaSaldo) == 0.0) saldoValido = true;
//		return saldoValido;
//	}
//	
//	public boolean validaSaldoComTarifas(double saldoInicial, double saldoFinal, double valorTransacao) {
//		boolean saldoValido = false;
//		double calculaDiferencaSaldo = (saldoInicial - saldoFinal) - (valorTransacao + 3.50);
//		if (Math.round(calculaDiferencaSaldo) == 0.0) saldoValido = true;
//		return saldoValido;
//	}
//	
//	public void validaComprovante(boolean resultadoEsperado) throws Exception {
//		// Registra se transferencia foi realizado
//		// (dependendo do resultado esperado do teste)
//		if (resultadoEsperado) {
//			if (this.comprovanteValido) {
//				pass("Comprovante validado com sucesso!");
//			}
//			else {
//				closeAppium();
//				fail("Comprovante com inconsistência.");
//			}
//		}
//		else {
//			if (this.comprovanteValido) {
//				pass("Comprovante com inconsistência.");
//			}
//			else {
//				closeAppium();
//				fail("Comprovante validado com sucesso!");
//			}
//		}
//	}
//	
//	//
//	//	Métodos validadores
//	//
//	public void validaSaldoTransferencia(boolean resultadoEsperado, String valorTransf) throws Exception {
//		Thread.sleep(5000);
//		boolean saldoValido = false;
//		// Valida saldo
//		saldoValido = validaSaldo(saldoInicial, saldoFinal, Double.parseDouble(valorTransf));
//
//		// Registra se transferencia foi realizado
//		// (dependendo do resultado esperado do teste)
//		if (resultadoEsperado) {
//			if (saldoValido)	pass("transferência entre contas Neon feita com sucesso!");
//			else {
//				closeAppium();
//				fail("Falha ao efetuar transferência entre contas Neon.");
//			}
//		}
//		else {
//			if (saldoValido)	pass("transferência entre contas Neon não validado com sucesso.");
//			else {
//				closeAppium();
//				fail("Sucesso ao validar transferência entre contas Neon.");
//			}
//		}
//	}
//	
//	public void validaSaldoTransferenciaComTarifa(boolean resultadoEsperado, String valorTransf) throws Exception {
//		Thread.sleep(5000);
//		boolean saldoValido = false;
//		// Valida saldo
//		saldoValido = validaSaldoComTarifas(saldoInicial, saldoFinal, Double.parseDouble(valorTransf));
//
//		// Registra se transferencia foi realizado
//		// (dependendo do resultado esperado do teste)
//		if (resultadoEsperado) {
//			if (saldoValido)	pass("transferência entre contas Neon feita com sucesso!");
//			else {
//				closeAppium();
//				fail("Falha ao efetuar transferência entre contas Neon.");
//			}
//		}
//		else {
//			if (saldoValido)	pass("transferência entre contas Neon não validado com sucesso.");
//			else {
//				closeAppium();
//				fail("Sucesso ao validar transferência entre contas Neon.");
//			}
//		}
//	}
//	
//	public void validarValorTarifaAposTransf(boolean resultadoEsperado, String valorTransf) throws Exception {
//		Thread.sleep(5000);
//		boolean saldoValido = false;
//		// Valida saldo
//		saldoValido = validaSaldoComTarifas(saldoInicial, saldoFinal, Double.parseDouble(valorTransf));
//
//		// Registra se transferencia foi realizado
//		// (dependendo do resultado esperado do teste)
//		if (resultadoEsperado) {
//			if (saldoValido)	pass("transferência entre contas Neon feita com sucesso!");
//			else {
//				closeAppium();
//				fail("Falha ao efetuar transferência entre contas Neon.");
//			}
//		}
//		else {
//			if (saldoValido)	pass("transferência entre contas Neon não validado com sucesso.");
//			else {
//				closeAppium();
//				fail("Sucesso ao validar transferência entre contas Neon.");
//			}
//		}
//	}
//	
//	public void validarNaoCobrancaTaxaTransf(boolean resultadoEsperado, String valorTransf) throws Exception {
//		Thread.sleep(5000);
//		boolean saldoValido = false;
//		// Valida saldo
//		saldoValido = validaSaldo(saldoInicial, saldoFinal, Double.parseDouble(valorTransf));
//
//		// Registra se transferencia foi realizado
//		// (dependendo do resultado esperado do teste)
//		if (resultadoEsperado) {
//			if (saldoValido)	pass("transferência entre contas Neon feita com sucesso!");
//			else {
//				closeAppium();
//				fail("Falha ao efetuar transferência entre contas Neon.");
//			}
//		}
//		else {
//			if (saldoValido)	pass("transferência entre contas Neon não validado com sucesso.");
//			else {
//				closeAppium();
//				fail("Sucesso ao validar transferência entre contas Neon.");
//			}
//		}
//	}
//	
//	public void validaCriacaoNovoContato(boolean resultadoEsperado, String nomeFavorecido) throws Exception {
//		boolean contatoAdicionado = false;
//		
//		Thread.sleep(3000);
//		homeView.clicarOpcaoMenu("transferências");
//		
//		// Procura favorecido
//		if (verificarContatoExiste(nomeFavorecido)) {
//			contatoAdicionado = true;
//		} 
//
//		// Registra se transferencia foi realizado
//		// (dependendo do resultado esperado do teste)
//		if (resultadoEsperado) {
//			if (contatoAdicionado) {
//				pass("Contato adicionado com sucesso");
//				
//				getScreenshot().CaptureScreenshot();
//				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
//				getScreenshot().CaptureScreenshot();
//				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
//			}
//			else {
//				closeAppium();
//				fail("Falha ao tentar adicionar um novo contato.");
//			}
//		}
//		else {
//			if (contatoAdicionado)	pass("Falha ao adicionar um contato");
//			else {
//				closeAppium();
//				fail("Sucesso ao adicionar um contato");
//			}
//		}
//	}
//	
//	public void validaAlteracaoContato(boolean resultadoEsperado, String nomeFavorecido) throws Exception {
//		boolean contatoAdicionado = false;
//		
//		Thread.sleep(3000);
//		homeView.clicarOpcaoMenu("transferências");
//		
//		// Procura favorecido
//		if (verificarContatoExiste(nomeFavorecido)) {
//			contatoAdicionado = true;
//		} 
//
//		// Registra se transferencia foi realizado
//		// (dependendo do resultado esperado do teste)
//		if (resultadoEsperado) {
//			if (contatoAdicionado) {
//				pass("Contato alterado com sucesso");
//				
//				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
//			}
//			else {
//				closeAppium();
//				fail("Falha ao tentar alterar contato.");
//			}
//		}
//		else {
//			if (contatoAdicionado)	pass("Falha ao alterar um contato");
//			else {
//				closeAppium();
//				fail("Sucesso ao alterar um contato");
//			}
//		}
//	}
//	
//	public void validaContatoExcluido(boolean resultadoEsperado, String nomeFavorecido) throws Exception {
//		boolean contatoAdicionado = false;
//		
//		Thread.sleep(3000);
//		homeView.clicarOpcaoMenu("transferências");
//		
//		// Procura favorecido
//		if (verificarContatoExiste(nomeFavorecido)) {
//			contatoAdicionado = true;
//		} 
//
//		// Registra se transferencia foi realizado
//		// (dependendo do resultado esperado do teste)
//		if (resultadoEsperado) {
//			if (!contatoAdicionado)	{
//				pass("Contato exclu�do com sucesso.");
//				
//				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
//			}
//			else {
//				closeAppium();
//				fail("Falha ao tentar excluir um contato.");
//			}
//		}
//		else {
//			if (!contatoAdicionado)	pass("Falha ao tentar excluir um contato.");
//			else {
//				closeAppium();
//				fail("Sucesso ao excluir um contato.");
//			}
//		}
//	}
//	
//	public void validarAdicaoContaContato (boolean resultadoEsperado, String nomeFavorecido, String conta) throws Exception {
//		boolean contaAdicionada = false;
//		
//		Thread.sleep(3000);
//		homeView.clicarOpcaoMenu("transferências");
//		
//		verificarContaContatoExiste(nomeFavorecido, conta);
//		
//		// Procura texto na tela
//		contaAdicionada = verificarContaContatoExiste(nomeFavorecido, conta);
//
//		// Registra se transferencia foi realizado
//		// (dependendo do resultado esperado do teste)
//		if (resultadoEsperado) {
//			if (contaAdicionada) {
//				pass("Conta adicionada com sucesso.");
//				
//				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
//				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
//			}
//			else {
//				closeAppium();
//				fail("Falha ao tentar adicionar conta para contato.");
//			}
//		}
//		else {
//			if (contaAdicionada)	pass("Falha ao tentar adicionar conta para contato.");
//			else {
//				closeAppium();
//				fail("Sucesso ao para incluir conta para contato");
//			}
//		}
//	}
//	
//	public void validarExclusaoConta (boolean resultadoEsperado, String nomeFavorecido, String conta) throws Exception {
//		boolean contaExluida = false;
//		
//		Thread.sleep(3000);
//		homeView.clicarOpcaoMenu("transferências");
//		
//		// Procura texto na tela
//		getScreenshot().CaptureScreenshot();
//		contaExluida = verificarContaContatoExiste(nomeFavorecido, conta);
//
//		// Registra se transferencia foi realizado
//		// (dependendo do resultado esperado do teste)
//		if (resultadoEsperado) {
//			if (!contaExluida) {
//				pass("Conta excluida com sucesso.");
//				
//				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
//				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
//			}
//			else {
//				closeAppium();
//				fail("Falha ao tentar excluir conta para contato.");
//			}
//		}
//		else {
//			if (!contaExluida)	pass("Falha ao tentar excluir conta para contato.");
//			else {
//				closeAppium();
//				fail("Sucesso ao excluir conta para contato");
//			}
//		}
//	}
//	
//	public void validarTransferenciaRepetida(boolean resultadoEsperado) throws Exception {
//		boolean mensagemExibida = false;
//		
//		// Procura texto na tela
//		mensagemExibida = getGenerics().exists("text", "repetir transferência",Constants.ANDROID_TEXT_VIEW);
//
//		// Registra se transferencia foi realizado
//		// (dependendo do resultado esperado do teste)
//		if (resultadoEsperado) {
//			if (mensagemExibida) {
//				pass("transferência repetida validada com sucesso.");
//				
//				getScreenshot().CaptureScreenshot();
//				getTextView().clickByText("OK, ENTENDI");
//				
//				getScreenshot().CaptureScreenshot();
//				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
//				
//				getScreenshot().CaptureScreenshot();
//				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
//				
//				getScreenshot().CaptureScreenshot();
//				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
//				
//				getScreenshot().CaptureScreenshot();
//				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
//				
//				getScreenshot().CaptureScreenshot();
//				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
//			}
//			else {
//				closeAppium();
//				fail("transferência repetida sem sucesso.");
//			}
//		}
//		else {
//			if (mensagemExibida)	pass("transferência repetida sem sucesso.");
//			else {
//				closeAppium();
//				fail("transferência repetida validada com sucesso.");
//			}
//		}
//	}
//	
//	public void validarCompartilhamentoComprovante(boolean resultadoEsperado) throws Exception {
//		boolean mensagemExibida = false;
//		
//		// Procura texto na tela
//		mensagemExibida = getGenerics().exists("text", "Enviar com: ",Constants.ANDROID_TEXT_VIEW);
//
//		// Registra se transferencia foi realizado
//		// (dependendo do resultado esperado do teste)
//		if (resultadoEsperado) {
//			if (mensagemExibida) {
//				pass("Compartilhamento de comprovante validado com sucesso.");
//				
//				// Volta para tela inicial
//				getScreenshot().CaptureScreenshot();
//				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
//				
//				getScreenshot().CaptureScreenshot();
//				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
//				
//				getScreenshot().CaptureScreenshot();
//				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
//				
//				getScreenshot().CaptureScreenshot();
//				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
//			}
//			else {
//				closeAppium();
//				fail("Falha ao compartilhar comprovante.");
//			}
//		}
//		else {
//			if (mensagemExibida)	pass("Falha ao compartilhar comprovante.");
//			else {
//				closeAppium();
//				fail("Compartilhamento de comprovante validado com sucesso.");
//			}
//		}
//	}
//	
//	public void validarPesquisaContato(boolean resultadoEsperado, String nomeContato) throws Exception {
//		boolean existeContato = false;
//		
//		// Procura se existe texto na tela
//		existeContato = getGenerics().exists("text", nomeContato, Constants.ANDROID_TEXT_VIEW);
//		
//		// Registra se fez logout com sucesso ou não
//		// (dependendo do resultado esperado do teste)
//		if (resultadoEsperado) {
//			if (existeContato)	{
//				pass("Teste de pesquisa de contato com sucesso.");
//				
//				getScreenshot().CaptureScreenshot();
//				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
//				getScreenshot().CaptureScreenshot();
//				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
//				getScreenshot().CaptureScreenshot();
//				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
//				getScreenshot().CaptureScreenshot();
//			}
//			else {
//				closeAppium();
//				fail("Falha na pesquisa de contato.");
//			}
//		}
//		else {
//			if (existeContato)	pass("Falha na pesquisa de contato.");
//			else {
//				closeAppium();
//				fail("Teste de pesquisa de contato com sucesso.");
//			}
//		}
//	}
//	
//	public void validarTransfAcimaValorLimiteDiario(boolean resultadoEsperado) throws Exception {
//		boolean existemMsgValorLimite = false;
//		
//		// Procura se existe texto na tela
//		existemMsgValorLimite = getGenerics().exists("text", "fora do valor limite", Constants.ANDROID_TEXT_VIEW);
//		
//		// Registra se fez logout com sucesso ou não
//		// (dependendo do resultado esperado do teste)
//		if (resultadoEsperado) {
//			if (existemMsgValorLimite)	{
//				pass("Teste de validação de mensagem fora do valor limite validado com sucesso.");
//				getScreenshot().CaptureScreenshot();
//				//TODO: bot�o "ok, entendi" perde referencia na tela
////				getDriver().findElement(By.xpath("//android.widget.TextView[contains@text='OK, ENTENDI']"));
//				getScreen().ClickPorcentagemAbaixoCentro(0.66);
////				getTextView().clickByIndex(2);
////				getTextView().clickByText("OK, ENTENDI");
//				// Volta tela inicial
//				
//				Thread.sleep(3000);
//				
//				getScreenshot().CaptureScreenshot();
//				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
//				getScreenshot().CaptureScreenshot();
//				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
//				getScreenshot().CaptureScreenshot();
//				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
//				getScreenshot().CaptureScreenshot();
//				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
//			}
//			else {
//				closeAppium();
//				fail("Falha na validação de mensagem de valor fora do limite.");
//			}
//		}
//		else {
//			if (existemMsgValorLimite)	pass("Falha na validação de mensagem de valor fora do limite.");
//			else {
//				closeAppium();
//				fail("Teste de validação de mensagem fora do valor limite validado com sucesso.");
//			}
//		}
//	}
//	
//	public void validarTransfAcimaSaldoDisponivel(boolean resultadoEsperado) throws Exception {
//		boolean existemMsgValorLimite = false;
//		
//		// Procura se existe texto na tela
//		existemMsgValorLimite = getGenerics().exists("text", "SALDO INSUFICIENTE", Constants.ANDROID_TEXT_VIEW);
//		
//		// Registra se fez logout com sucesso ou não
//		// (dependendo do resultado esperado do teste)
//		if (resultadoEsperado) {
//			if (existemMsgValorLimite)	{
//				pass("Teste de validação de mensagem de saldo insuficiente validado com sucesso.");
//				
////				getDriver().findElement(By.xpath("//android.widget.TextView[contains@text='OK, ENTENDI']"));
//				getScreenshot().CaptureScreenshot();
//				getTextView().clickByText("OK, ENTENDI");
//				// Volta tela inicial
//				getScreenshot().CaptureScreenshot();
//				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
//				getScreenshot().CaptureScreenshot();
//				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
//				getScreenshot().CaptureScreenshot();
//				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
//				getScreenshot().CaptureScreenshot();
//				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
//				getScreenshot().CaptureScreenshot();
//				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
//			}
//			else {
//				closeAppium();
//				fail("Falha na validação de mensagem de saldo insuficiente.");
//			}
//		}
//		else {
//			if (existemMsgValorLimite)	pass("Falha na validação de mensagem de saldo insuficiente.");
//			else {
//				closeAppium();
//				fail("Teste de validação de mensagem de saldo insuficiente validado com sucesso.");
//			}
//		}
//	}
//
//	public void validarLimiteDiario(boolean resultadoEsperado, String valorTransf) throws Exception {
//		Thread.sleep(5000);
//		boolean limiteValido = false;
//		// Valida saldo
//		limiteValido = validaSaldo(limiteInicial, limiteFinal, Double.parseDouble(valorTransf));
//
//		// Registra se transferencia foi realizado
//		// (dependendo do resultado esperado do teste)
//		if (resultadoEsperado) {
//			if (limiteValido) {
//				pass("Limite diário transferencia validado com sucesso!");
//				
//				getScreenshot().CaptureScreenshot();
//				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
//				getScreenshot().CaptureScreenshot();
//				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
//				getScreenshot().CaptureScreenshot();
//				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
//			}
//			else {
//				closeAppium();
//				fail("Falha ao validar limite diário de transferencia.");
//			}
//		}
//		else {
//			if (limiteValido)	pass("TFalha ao validar limite diário de transferencia.");
//			else {
//				closeAppium();
//				fail("Limite diário transferencia validado com sucesso!");
//			}
//		}
//	}
//
//	// per�odo 0h as 07h30
//	public void validarTransferenciaOutrosBancosAposHorario(boolean resultadoEsperado) throws Exception {
//		boolean existemMsgHorarioLimite = false;
//		
//		// Procura se existe texto na tela
//		existemMsgHorarioLimite = getGenerics().exists("text", "fora da hora limite", Constants.ANDROID_TEXT_VIEW);
//		
//		// Registra se fez logout com sucesso ou não
//		// (dependendo do resultado esperado do teste)
//		if (resultadoEsperado) {
//			if (existemMsgHorarioLimite)	{
//				pass("Teste de validação de mensagem transferencia apos horario validado com sucesso!");
//				
//				//TODO: bot�o "ok, entendi" perde referencia na tela
////				getDriver().findElement(By.xpath("//android.widget.TextView[contains@text='OK, ENTENDI']"));
//				
//				getTextView().clickByIndex(2);
////				getTextView().clickByText("OK, ENTENDI");
//				// Volta tela inicial
//				getScreenshot().CaptureScreenshot();
//				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
//				getScreenshot().CaptureScreenshot();
//				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
//				getScreenshot().CaptureScreenshot();
//				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
//				getScreenshot().CaptureScreenshot();
//				getGenerics().pressKeyCode(AndroidKeyCode.BACK);
//			}
//			else {
//				closeAppium();
//				fail("Teste de validação de mensagem transferencia apos horario validado com falha.");
//			}
//		}
//		else {
//			if (existemMsgHorarioLimite)	pass("Teste de validação de mensagem transferencia apos horario validado com falha");
//			else {
//				closeAppium();
//				fail("Teste de validação de mensagem transferencia apos horario validado com sucesso!");
//			}
//		}
//	}
//}
