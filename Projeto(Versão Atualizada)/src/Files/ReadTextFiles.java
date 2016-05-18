package Files;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
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
public class ReadTextFiles implements IReadFiles{
    
    RenameTextFiles rename= new RenameTextFiles();
    
    CreateTextFiles createFiles = new CreateTextFiles();
    
    WriteTextFiles writeFiles = new WriteTextFiles();
    
    /**
        Método para pega o valor do blur do arquivo de texto.
        @param filename Nome do arquivo de texto com o valor do blur.
        @param blur Valor do blur inicial.
        @return blur Valor do blur final.
    */
    @Override
    public double valueImagesBlur(String filename, double blur){
        filename = rename.renameFileNameValue(filename);
        
        try{
            Scanner s = new Scanner(new File(filename));
            while (s.hasNext()){
                blur = Float.parseFloat(s.next());
                s.next();
                s.next();
            }
            s.close();
        }
        catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null, "Arquivo não encontrado", "Transform",  JOptionPane.INFORMATION_MESSAGE);
        }
        return blur;
    }
    /**
        Método para pega o valor da cor do arquivo de texto.
        @param filename Nome do arquivo de texto com o valor da cor.
        @param blur Valor da cor inicial.
        @return blur Valor da cor final.
    */
    public int valueImagesColor(String filename, int color){
        filename = rename.renameFileNameValue(filename);
        
        try{
            Scanner s = new Scanner(new File(filename));
            while (s.hasNext()){
                s.next();
                color = Integer.parseInt(s.next());
                s.next();
            }
            s.close();
        }
        catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null, "Arquivo não encontrado", "Transform",  JOptionPane.INFORMATION_MESSAGE);
        }
        return color;
    }
    
    /**
        Método para pega o valor do tamanho mínimo do arquivo de texto.
        @param filename Nome do arquivo de texto com os valor do tamanho mínimo.
        @param blur Valor do size inicial.
        @return blur Valor do size final.
    */    
    public int valueImagesSize(String filename, int size){
        filename = rename.renameFileNameValue(filename);
        
        try{
            Scanner s = new Scanner(new File(filename));
            while (s.hasNext()){
                s.next();
                s.next();
                size = Integer.parseInt(s.next());
            }
            s.close();
        }
        catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null, "Arquivo não encontrado", "Transform",  JOptionPane.INFORMATION_MESSAGE);
        }
        return size;
    }
    
}
