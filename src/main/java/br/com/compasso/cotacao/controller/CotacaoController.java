package br.com.compasso.cotacao.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.compasso.cotacao.config.Conexao;


@Controller
@RequestMapping("/cotacao")
public class CotacaoController {

    @GetMapping
    public String listar(Model model){
         try {
            model.addAttribute(Conexao.pegarJson());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "cotacao";
    }
}
