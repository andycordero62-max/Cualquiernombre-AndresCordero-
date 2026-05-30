package com.wintux.skibidisigmaponmi.Controller;

import com.wintux.skibidisigmaponmi.Tools.Calculadora;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Queue;

@RestController
public class CalculadoraController {

    @GetMapping("/")
    public String inicio() {

        return "Calculadora Infix funcionando";
    }

    @PostMapping("/expresion")
public String calcularInfix(@RequestBody ExpresionRequest request) {
    
    String infix = request.infix(); 

    Queue<String> postfix = Calculadora.convertirAPostfix(infix);

    Queue<String> copiaParaResolver = new java.util.LinkedList<>(postfix);

    double resultadocausa = Calculadora.resolverExpresionPostfix(copiaParaResolver);

    return "Infix: " + infix + 
            "Postfix: " + postfix + 
            "Resultado: " + resultadocausa;
}


}