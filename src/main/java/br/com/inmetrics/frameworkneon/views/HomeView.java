package br.com.inmetrics.frameworkneon.views;

import br.com.inmetrics.frameworkneon.Neon;
import br.com.inmetrics.neon.constants.Constants;

public class HomeView extends Neon {
	
	public void clicarOpcaoMenu(String menu) throws Exception {
//		if (menu.equals("meu neon")) {
//			getTextView().clickByText(menu);
//			if (getGenerics().exists("text", "fazer dep�sitos", Constants.ANDROID_TEXT_VIEW)) {
//				getTextView().clickByText("OK, ENTENDI");
//			}
//		} 
//		
//		if (menu.equals("transfer�ncias")) {
//			getTextView().clickByText(menu);
//			if (getGenerics().exists("text", "sincronizar com Facebook", Constants.ANDROID_TEXT_VIEW)) {
//				getTextView().clickByText("AINDA N�O");
//			}
//		}
		getTextView().clickByText(menu);
	}
	
	public void clicarBalaoNotificacoes() throws Exception {
		getImageView().clickById("br.com.neon:id/iv_balloon");
	}
}
