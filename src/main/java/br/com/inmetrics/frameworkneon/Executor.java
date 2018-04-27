package br.com.inmetrics.frameworkneon;

import br.com.inmetrics.neon.reports.Report;
import br.com.inmetrics.neon.tests.android.TransferenciaTeste;
import br.com.inmetrics.neon.utilities.Utils;


public class Executor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		 Runnable teste = new Runnable()
		 {
		     public void run()
		     {
		    	 try{
		    		
		    		 TransferenciaTeste r = new TransferenciaTeste();
		    		 
		    		 r.ct_004_transferenciaEntreContasNeonComSucesso();
		    	 }catch(Exception e)
		    	 {
		    		 e.printStackTrace();
		    	 }
		     }
		  };
		  
			 Runnable teste2 = new Runnable()
			 {
			     public void run()
			     {
			    	 try{
			    		
			    		 TransferenciaTeste r = new TransferenciaTeste();
			    		 
			    		 r.ct_004_transferenciaEntreContasNeonComSucesso();
			    	 }catch(Exception e)
			    	 {
			    		 e.printStackTrace();
			    	 }
			     }
			  };


		   Thread thread1 = new Thread(teste);
		   Thread thread2 = new Thread(teste2);
		// ---------------------------------------
	
				  
				 thread1.start(); 
				 /*Thread.sleep(3000);
				 thread2.start();
				 Thread.sleep(3000);
				 thread3.start();*/
				  //aux.Teste1("57121281872", "57f50ef0a8b252c367c64d29");
				  //aux.Teste1("57121281872", "57f50e5ea8b252c367c64d28");
				  //aux.Teste1("57121281872", "57fbb07bb3882b236fe453de");
		
				 //57f50ef0a8b252c367c64d29 

	}

	
}
