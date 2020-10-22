package br.unifil.dc.sisop;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Scanner;

/**
 * Write a description of class Jsh here.
 *
 * @author Ricardo Inacio Alvares e Silva
 * @version 180823
 */
public final class Jsh {
    
    /**
    * Funcao principal do Jsh.
    */
    public static void promptTerminal() {

        while (true) {
    		exibirPrompt();
    		ComandoPrompt comandoEntrado = lerComando();
    		executarComando(comandoEntrado);
    	}
    }

    public static String user_name;
    public static String user_diretorio;
    public static int    user_UID;
    public static String barraSistema = System.getProperty("file.separator");

    /**
    * Escreve o prompt na saida padrao para o usuário reconhecê-lo e saber que o
    * terminal está pronto para receber o próximo comando como entrada.
    */


    public static void exibirPrompt() {

        try{
        user_name = System.getProperty("user.name");

        user_diretorio = System.getProperty("user.dir");

        String comando = "id -u " + user_name;
        Process comandoUID = Runtime.getRuntime().exec(comando);

        InputStream saidaProcesso = comandoUID.getInputStream();

        byte[] arrSaida = saidaProcesso.readAllBytes();

        user_UID = arrSaida[0];        

        System.err.print(user_name + "#" + user_UID + ":" + user_diretorio + "%" );

        }catch(Error e){
            System.err.print(e);
        }

    }

    /**
    * Preenche as strings comando e parametros com a entrada do usuario do terminal.
    * A primeira palavra digitada eh sempre o nome do comando desejado. Quaisquer
    * outras palavras subsequentes sao parametros para o comando. A palavras sao
    * separadas pelo caractere de espaco ' '. A leitura de um comando eh feita ate
    * que o usuario pressione a tecla <ENTER>, ou seja, ate que seja lido o caractere
    * EOL (End Of Line).
    *
    * @return 
    */
    public static ComandoPrompt lerComando() {
 
        Scanner input = new Scanner(System.in);
        String comand = input.nextLine();

        ComandoPrompt cmdPrompt = new ComandoPrompt(comand);
        return cmdPrompt;

    
        //throw new RuntimeException("Método ainda não implementado.");
    }

    /**
    * Recebe o comando lido e os parametros, verifica se eh um comando interno e,
    * se for, o executa.
    * 
    * Se nao for, verifica se é o nome de um programa terceiro localizado no atual 
    * diretorio de trabalho. Se for, cria um novo processo e o executa. Enquanto
    * esse processo executa, o processo do uniterm deve permanecer em espera.
    *
    * Se nao for nenhuma das situacoes anteriores, exibe uma mensagem de comando ou
    * programa desconhecido.
    */
    public static void executarComando(ComandoPrompt comando) {


        switch (comando.getNome()){

            case ("encerrar"):{

                System.exit(0);
                break;
                
            }  
            case ("relogio"):{
                ComandosInternos.exibirRelogio();
                break;
            }
            case ("la"):{
                ComandosInternos.escreverListaArquivos(Optional.of(user_diretorio));
                break;
            }
            case ("cd"):{
                ComandosInternos.criarNovoDiretorio(comando.getArgumentos(), barraSistema);
                break;
            }
            case ("ad"):{
                ComandosInternos.apagarDiretorio(comando.getArgumentos(), barraSistema);
                break;
            }
            case ("mdt"):{
                ComandosInternos.mudarDiretorioTrabalho(comando.getArgumentos());
                break;
            }
            default:{
                Jsh.executarPrograma(comando);
            }

        }
        
        //throw new RuntimeException("Método ainda não implementado.");

    }


    public static int executarPrograma(ComandoPrompt comando) {

        Process p;
        int valorSaida = 0;

        try {
            String[] cmd = {"/bin/sh", "-c", ( "./" + comando.getNome() ) };

            p = Runtime.getRuntime().exec(cmd);
            valorSaida = p.waitFor();
            InputStream input = p.getInputStream();

            byte[] saida = input.readAllBytes();

            String stgSaida = new String(saida);

            System.err.println(stgSaida);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return valorSaida;

        //throw new RuntimeException("Método ainda não implementado.");
    }
    
    
    /**
     * Entrada do programa. Provavelmente você não precisará modificar esse método.
     */
    public static void main(String[] args) {

        promptTerminal();
    }
    
    
    /**
     * Essa classe não deve ser instanciada.
     */
    private Jsh() {}
}
