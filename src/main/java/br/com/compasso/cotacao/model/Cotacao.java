package br.com.compasso.cotacao.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Cotacao {

	Double dolar;
	Double real;
	Double bitcoin;
	String valorBase;
    String baseReal = "Real";
    LocalDate data;
    
    DateTimeFormatter formatadorDataInternacional = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	DateTimeFormatter formatadorDataNacional = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Cotacao(Double dolar, Double real, Double bitcoin, String valorBase, String data) {
        this.dolar = dolar;
        this.real = real;
        this.bitcoin = bitcoin;
        this.valorBase = valorBase;
        this.data = LocalDate.parse(data, formatadorDataInternacional);
    }
    
    public Double converterParaReal(Double conversao){
        return (this.real/conversao);
    }

    public String getDolar() {
        return  String.format("%.2f",converterParaReal(this.dolar));
    }

    public String getReal() {
        return String.format("%.2f", this.real);
    }

    public String getBitcoin() {
        return String.format("%.2f",converterParaReal(this.bitcoin));
    }

    public String getBase() {
        return baseReal;
    }

    public String getData() {
        return data.format(formatadorDataNacional);
    }
    
}
