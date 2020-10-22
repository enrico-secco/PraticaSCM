package br.unifil.dc.sisop;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;


/**
 * Write a description of class ComandosInternos here.
 *
 * @author Ricardo Inacio Alvares e Silva
 * @version 180823
 */
public final class ComandosInternos {
    
    public static int exibirRelogio() {

        try{
            //Define os formatos de Hora e data
            DateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
            DateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

            //Pega a data atual
            Date dataAtual = new Date();

            //Gera as strings de data e hora
            String stringHora = formatoHora.format(dataAtual);
            String stringData = formatoData.format(dataAtual);


            System.out.println("São " + stringHora + " de " + stringData + ".");
            return 1;
        } catch (Exception erro){
            System.out.println("Ouve uma falha na chamada/formatação da data e hora");
            System.out.println(erro.getMessage());
            return 1;
        }

        
    }
    
    public static int escreverListaArquivos(Optional<String> nomeDir) {

        //Cria um objeto File do diretorio atual
        File diretorio = new File(nomeDir.get());

        //Gera uma Lista de Files dos arquivos do diretorio
        List<File> listaArquivos = Arrays.asList(diretorio.listFiles());

        //For each que pega o nome cada arquivo da lista e printa em uma linha
        for (File f : listaArquivos) System.out.println(f.getName());


        return 1;
    }
    
    public static int criarNovoDiretorio(List<String> nomeDir, String pasta) {

        File file = new File(nomeDir + "/" + pasta);
        if (!file.exists()) {
            file.mkdirs();
        }
        return 1;
    }
    
    public static int apagarDiretorio(List<String> nomeDir, String pasta) {
        
        File file = new File(nomeDir + "/" + pasta);
        if ((file.exists()) && (file.isDirectory())) {
            file.delete();
        }
        return (1);
         
    }
    
    public static int mudarDiretorioTrabalho(List<String> list){

         throw new RuntimeException("Método ainda não implementado");
    }
    
    /**
     * Essa classe não deve ser instanciada.
     */
    private ComandosInternos() {}
}
