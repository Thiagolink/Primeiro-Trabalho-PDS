package Files;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Thiago
 */
public class RenameTextFiles implements IRenameFiles{
    
    /**
        Método para renomear o arquivo de imagem para separar as regiões das imagens em um arquivo de texto.
        @param filename Nome do arquivo a ser renomeado.
        @return filename Novo nome do arquivo.
    */
    public String renameFileNameRegions(String filename){
        if (filename.endsWith(".jpg")) {
            filename = filename.substring(0, filename.length() - 4);
            filename = filename + "regioes.txt";
        }
        return filename;
    }
    
    /**
        Método para renomear o arquivo de imagem para separar os valores da imagem para a segmentação em um arquivo de texto.
        @param filename Nome do arquivo a ser renomeado.
        @return filename Novo nome do arquivo.
    */
    @Override
    public String renameFileNameValue(String filename){
        if (filename.endsWith(".jpg")) {
            filename = filename.substring(0, filename.length() - 4);
            filename = filename + "valores.txt";
        }
        return filename;
    }
    
    
}
