
import Imagem.Segmentation;
import Files.IFileToTAD;
import Files.TextFileToTAD;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Thiago
 */
public class TADManipulation implements IFileToTAD{
    
    Segmentation image = new Segmentation();
    
    TextFileToTAD fileToTAD = new TextFileToTAD();
    
    /**
     * Método para orndenar um ArrayList de Strings.
     * @param list ArrayList a ser ornadados.
     * @return 
     */
    public ArrayList sort(ArrayList<String> list){
        Collections.sort(list);
        return list;
    }
    
    /**
        Método para adicionar elementos em um ArrayList de acordo com a quantidade de segmentações.
        @param list Arraylist que terá elementos inseridos.
        @param total Número de elementos a serem adicionados.
        @return list ArrayList com todos os elementos adicionados.
    */
    public ArrayList addValue(ArrayList<Integer> list, int total){
        list.clear();
        for(int i =0; i < total; i++)
           list.add(0);       
        return list;
    }    
    
    /**
        Método para procurar região seleciona em todas as imagens e devolver um set com o caminho de todas as imagens
        que possuem tal região.
        @param region Região a ser procurada.
        @return list ArrayList com o caminho de todas as imagens que possuem a região selecionada.
    */
    public ArrayList<String> regionFile(String region) throws FileNotFoundException{
        ArrayList<String> listCaminhoImagem = new ArrayList<>();
        ArrayList<String> listCaminhoImagemAuxiliar = new ArrayList<>();
        File folder = new File(System.getProperty("user.dir")+"\\imgs");
        File file;
        String caminhoImagem;
        File[] listOfFiles = folder.listFiles();
        
        for (int i = 0; i < listOfFiles.length; i++) {
            file = listOfFiles[i];
            if (file.isFile() && file.getName().endsWith("regioes.txt")) {
                listCaminhoImagemAuxiliar = fileToTAD.transformToArrayList(file.getAbsolutePath());
                if(listCaminhoImagemAuxiliar.contains(region)){
                    caminhoImagem = file.getAbsolutePath();
                    caminhoImagem = caminhoImagem.substring(0, caminhoImagem.length() - 11);
                    caminhoImagem = caminhoImagem +".jpg";
                    listCaminhoImagem.add(caminhoImagem);
                }
            } 
        }     

        return listCaminhoImagem;
    }       

    @Override
    public Map transformToMap(String filename) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList transformToArrayList(String filename) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<String> nameAllRegions() throws FileNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}



