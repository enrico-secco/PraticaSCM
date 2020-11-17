package br.unifil.dc.sisop;

import java.util.List;
import java.util.Arrays;
import java.util.Collections;

/**
 * Write a description of class ComandoPrompt here.
 *
 * @author Ricardo Inacio Alvares e Silva
 * @version 180823
 */
public class ComandoPrompt {
    
    public ComandoPrompt(String comando) {
        
         if (comando.contains(" ")){
            //Pega a primeira palavra da linha
            nome = comando.substring( 0, comando.indexOf(' '));

            //Retira o nome da string, deixando só os argumentos
            comando = comando.substring(comando.indexOf(' ')+1);

            //transforma em array os elementos separados por ' '
            argumentos = (comando.split(" "));
        } else {
            nome = comando;
            argumentos = new String[0];
        }

    }
    
    /**
     * Método acessor get para o nome do comando.
     * 
     * @return o nome do comando, exatamente como foi entrado.
     */
    public String getNome() { 

        return nome;
    }
    
    /**
     * Método acessor get para os argumentos que seguram ao nome do comando.
     * 
     * @return Lista de argumentos do comando, protegida contra modificações externas.
     */
    public List<String> getArgumentos() {

        return Collections.unmodifiableList(Arrays.asList(argumentos));
    }
    
    private final String nome;
    private final String[] argumentos;
}
