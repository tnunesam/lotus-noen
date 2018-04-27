package br.com.inmetrics.frameworkneon;

public class StandardView {
	
	private Neon neonObj;
		
	public StandardView(){
		neonObj = Neon.getInstance();
	}
	
	public Neon getNeon(){
		return neonObj;
	}

}
