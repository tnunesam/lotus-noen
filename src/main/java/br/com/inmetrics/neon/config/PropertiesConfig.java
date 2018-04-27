package br.com.inmetrics.neon.config;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesConfig {

	private final static String SELENIUM_PROPERTIES = "resource/selenium_config.properties";
	
	private Properties proterties;

	public PropertiesConfig() {
		this.proterties = new Properties();
	}

	public void carrega() {
		try {
			proterties.load(new FileInputStream(SELENIUM_PROPERTIES));
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println("Não foi possível ler o arquivo em resource/selenium_config.properties");
			System.out.println("---------------------------------------------------------------------");
			System.out.println("Crie uma pasta 'resource' na raiz de seu projeto");
			System.out.println("Dentro da pasta 'resource' crie um arquivo 'selenium_config.properties'");
			System.out.println("---------------------------------------------------------------------");
			System.out.println("O 'selenium_config.properties' de ser no seguinte formato:");
			System.out.println("");
			System.out.println("!-- CONFIGURAÇÂO SELENIUM --!);");
			System.out.println("webdriver.chrome.driver=c:/caminho/do/executavel_do_driver");
			System.out.println("webdriver.ie.driver=c:/caminho/do/executavel_do_driver");
			System.out.println("!-- CONFIGURAÇÂO EVIDÊNCIAS --!");
			System.out.println("automacao.caminho.evidencias=c:/caminho/da/pasta/deevidencias");
			System.out.println("!-- Path de Imagens --!");
			System.out.println("automacao.caminho.imagens=c:/caminho/das/imagens/das/evidencias");
			System.out.println("");
			System.out.println("usarproxy=true/false");
			System.out.println("PROXY = ip_do_proxy");
			System.out.println("proxy.setSocksUsername=username");
			System.out.println("proxy.setSocksPassword=password");
			System.out.println("");
			System.out.println("");
		}
	}
	
	public Properties getProperties() {
		return proterties;
	}

	public String getProperty(String key) {
		return proterties.getProperty(key);
	}
}
