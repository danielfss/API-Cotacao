package br.com.compasso.cotacao.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import br.com.compasso.cotacao.model.Cotacao;

public class Conexao {
	
	public static HttpURLConnection conectar() throws IOException {
		String api = "http://data.fixer.io/api/latest?access_key=3c308f01b64de553ce6b867791261326&symbols=USD,BRL,BTC&base=EUR";
		URL url = new URL(api);
		HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

		conexao.setRequestMethod("GET");
		conexao.setRequestProperty("Accept", "application/json");
		return conexao;
	}
    
    public static Cotacao pegarJson() throws IOException{
        HttpURLConnection conectar = conectar();
        
        BufferedReader leitura = new BufferedReader(new InputStreamReader(conectar.getInputStream()));
        StringBuilder resposta = new StringBuilder();

        String linha;
        while ((linha = leitura.readLine()) != null) {
            resposta.append(linha);
        }

        leitura.close();

        JSONObject json = new JSONObject(resposta.toString());
        JSONObject ratesJson = new JSONObject(json.getJSONObject("rates").toString());
        
        Cotacao cotacao = new Cotacao(ratesJson.getDouble("USD"), ratesJson.getDouble("BRL"), ratesJson.getDouble("BTC"),
                                         json.getString("base"), json.getString("date"));
        conectar.disconnect();
        return cotacao;
    }
}
