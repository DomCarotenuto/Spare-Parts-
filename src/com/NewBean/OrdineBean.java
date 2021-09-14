package com.NewBean;

import java.io.Serializable;
import java.util.ArrayList;


public class OrdineBean implements Serializable {
	
private static final long serialVersionUID = 1L;
   
	int code;
	float importo;
	String data_ordine;
	int cod_utente;
	
	
	public int getCod_utente() {
		return cod_utente;
	}
	public void setCod_utente(int cod_utente) {
		this.cod_utente = cod_utente;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public float getImporto() {
		return importo;
	}
	public void setImporto(float importo) {
		this.importo = importo;
	}
	public String getData_ordine() {
		return data_ordine;
	}
	public void setData_ordine(String data_ordine) {
		this.data_ordine = data_ordine;
	}
	
	
		
	
}