package Files;


import Files.RenameTextFiles;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Thiago
 */
public class TextFileToTAD implements IFileToTAD{
 
    RenameTextFiles rename= new RenameTextFiles();
    
    CreateTextFiles createFiles = new CreateTextFiles();
    
    WriteTextFiles writeFiles = new WriteTextFiles();
    
    
    
    
    /**
        Método para transformar as informações do arquivo de texto em um Map.
        @param filename Localização do arquivo de texto.
        @return map Map com o name gravado com índice e com um vetor de inteiros com as informações de x e de y.
    */
    
    public Map transformToMap(String filename){
        filename = rename.renameFileNameRegions(filename);
        String name;
        int X1,X2, Y1, Y2;
        
        Map<String, Integer[]> map = new HashMap<>();

        
        try{
            Scanner s = new Scanner(new File(filename));
            while (s.hasNext()){
                name = s.next();
                X1 = Integer.parseInt(s.next());
                Y1 = Integer.parseInt(s.next());
                X2 = Integer.parseInt(s.next());
                Y2 = Integer.parseInt(s.next());
                map.put(name, new Integer[]{ X1, Y1, X2, Y2});
            }
            s.close();
        }
        catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null, "Arquivo não encontrado", "Transform",  JOptionPane.INFORMATION_MESSAGE);
        }
        return map;
    }
    
    /**
        Método para transformar os nomes do arquivo de texto em ArrayList para que seja possível resgatar os valores do Map.
        @param filename Nome do arquivo de texto no qual se vai pegar as informações.
        @return list ArrayList com o nome com os nomes do arquivo de texto.
    */
    public ArrayList transformToArrayList(String filename){
        filename = rename.renameFileNameRegions(filename);
        ArrayList<String> list = new ArrayList<>();
        
        
        try{
            Scanner s = new Scanner(new File(filename));
            while (s.hasNext()){
                list.add(s.next());
                s.next();
                s.next();
                s.next();
                s.next();
            }
            s.close();
        }
        catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null, "Arquivo não encontrado", "transformtoArrayList",  JOptionPane.INFORMATION_MESSAGE);
        }
        return list;
    }
    
    /**
        Método para colocar todos os nomes das regiões de cada imagem um set usando a pasta imgs do projeto.
        @return names Set com o nome de todas as regiões de todas as imagens da pasta imgs do projeto.
    */
    public Set<String> nameAllRegions() throws FileNotFoundException {
        File folder = new File(System.getProperty("user.dir")+"\\imgs");
        File[] listOfFiles = folder.listFiles();
        Set<String> names = new HashSet<>();

        
        for (int i = 0; i < listOfFiles.length; i++) {
            File file = listOfFiles[i];
            if (file.isFile() && file.getName().endsWith("regioes.txt")) {
                Scanner s = new Scanner(file);
                while (s.hasNext()){
                        names.add(s.next());
                        s.next();
                        s.next();
                        s.next();
                        s.next();
                    }
                    
                
                s.close();
            } 
        }
        
        return names;
    }
    
    
}
