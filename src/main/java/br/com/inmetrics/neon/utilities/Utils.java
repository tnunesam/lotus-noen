package br.com.inmetrics.neon.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.text.ClipboardManager;


//import org.apache.log4j.Logger;


public class Utils {

	
	//private static final Logger log = Logger.getLogger(Utils.class.getName());

	public static List<String> ListDeviceSerialNumbers= null;

	public static List<String> getListDeviceSerialNumbers() {
		if (ListDeviceSerialNumbers!=null){
			return ListDeviceSerialNumbers;
		}

		Scanner scanner = null;
		try {
			scanner = new Scanner(Runtime.getRuntime().exec("cmd /c adb devices").getInputStream());
			scanner.useDelimiter("\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<String> deviceSerialNumbers =new ArrayList<String>();
		while (scanner != null && scanner.hasNext()){
			String Linha=scanner.next();
			if (Linha.indexOf("device\r")>-1){
				String arrParts[]=Linha.split("\\s+");

				deviceSerialNumbers.add(arrParts[0]);
			};
		}
		ListDeviceSerialNumbers=deviceSerialNumbers;
		return deviceSerialNumbers;
	}

	public static String geraCPF() {  
		String numeros = "";  
		Integer numero;  
		for (int i = 0; i < 9; i++) {  
			numero = new Integer((int) (Math.random() * 10));  
			numeros += numero.toString();  
		}  

		Integer primDig, segDig;  
		int soma = 0, peso = 10;  
		for (int i = 0; i < numeros.length(); i++)  
			soma += Integer.parseInt(numeros.substring(i, i + 1)) * peso--;  
		if (soma % 11 == 0 | soma % 11 == 1)  
			primDig = new Integer(0);  
		else  
			primDig = new Integer(11 - (soma % 11));  
		soma = 0;  
		peso = 11;  
		for (int i = 0; i < numeros.length(); i++)  
			soma += Integer.parseInt(numeros.substring(i, i + 1)) * peso--;  
		soma += primDig.intValue() * 2;  
		if (soma % 11 == 0 | soma % 11 == 1)  
			segDig = new Integer(0);  
		else  
			segDig = new Integer(11 - (soma % 11));  

		return numeros + primDig.toString() + segDig.toString();  
	}  

	public static String geraCNPJ() throws Exception {
		int digito1 = 0, digito2 = 0, resto = 0;
		String numeroGerado="";
		Integer numero;
		for (int i =0;i<12;i++){
			numero = new Integer((int) (Math.random() * 10));  
			numeroGerado += numero.toString(); 
		}

		String arrNumeros[] = numeroGerado.split("");
		int soma = 0;
		int peso = 5;
		for (int i = 0; i < 4; i++) {
			soma+=Integer.parseInt(arrNumeros[i])*peso;
			peso--;
		}
		peso = 9;
		for (int i = 4; i < 12; i++) {
			soma+=Integer.parseInt(arrNumeros[i])*peso;
			peso--;
		}

		int valor = (soma / 11)*11;
		digito1 = soma-valor;
		resto = (digito1 % 11);
		if(digito1 < 2){
			digito1 = 0;
		}
		else {
			digito1 = 11-resto;
		}

		numeroGerado +=String.valueOf(digito1);

		arrNumeros = numeroGerado.split("");

		int soma2 = 0;
		int peso2 = 6;
		for (int i = 0; i < 5; i++) {
			soma2+=Integer.parseInt(arrNumeros[i])*peso2;
			peso2--;
		}
		peso2 = 9;
		for (int i = 5; i < 13; i++) {
			soma2+=Integer.parseInt(arrNumeros[i])*peso2;
			peso2--;
		}

		int valor2 = (soma2 / 11)*11;
		digito2 = soma2-valor2;

		resto = (digito2 % 11);
		if(digito2 < 2){
			digito2 = 0;
		}
		else {
			digito2 = 11-resto;
		}

		numeroGerado +=String.valueOf(digito2);

		return numeroGerado;
	}

	public String geraRG() throws Exception {
		String numeroGerado="";
		Integer numero;
		for (int i =0;i<8;i++){
			numero = new Integer((int) (Math.random() * 10));  
			numeroGerado += numero.toString(); 
		}

		return numeroGerado;
	}

	public static String removeAcentos (String texto){
		return Normalizer.normalize(texto, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	}

	public static String removeCaracteresEspeciais(String texto ){
		String modelo[] = ".,:_+=-'\"!@#$%¨&*()?/;^~¢¹²³¬£§°º|>?[]<{}`".split("");

		for (String string : modelo) {
			if (texto.contains(string)){
				texto = texto.replace(string, ""); 
			}
		}
		return texto;
	}

	public static String expressaoRegular(String texto, String pattern){
		
		Pattern patt = Pattern.compile(pattern);
		Matcher matcher = patt.matcher(texto);
		while(matcher.find()){
			String codigoDoUsuario = matcher.group(1);
			System.out.println(codigoDoUsuario);
		}
		return null;
	}
	
	
}


