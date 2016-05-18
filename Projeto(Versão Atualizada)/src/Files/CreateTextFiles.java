package Files;


import java.io.File;
import java.util.Formatter;
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
public class CreateTextFiles implements ICreateFiles{
    
    RenameTextFiles rename= new RenameTextFiles();

    
    /**
        Método para criar um arquivo de texto para as regiões da imagem.
        @param filename Nome do arquivo a ser criado.
    */
    
    @Override
    public void createFileRegions(String filename){
        filename = rename.renameFileNameRegions(filename);
        try{
            Formatter saida = new Formatter(filename);
            saida.close();
            JOptionPane.showMessageDialog(null,"Arquivo '"+filename+"' criado!","Arquivo", JOptionPane.INFORMATION_MESSAGE);
        }
        catch(Exception erro){
            JOptionPane.showMessageDialog(null,"Arquivo nao pode ser gerado!", "Arquivo",  JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    /**
        Método para criar um arquivo de texto para os valores da imagem.
        @param filename Nome do arquivo a ser criado.
    */
    @Override
    public void createFileValue(String filename){
        filename = rename.renameFileNameValue(filename);
        try{
            Formatter saida = new Formatter(filename);
            saida.close();
            JOptionPane.showMessageDialog(null,"Arquivo '"+filename+"' criado!","Arquivo", JOptionPane.INFORMATION_MESSAGE);
        }
        catch(Exception erro){
            JOptionPane.showMessageDialog(null,"Arquivo nao pode ser gerado!", "Arquivo",  JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    @Override
    public void createFolder(){
        File f = new File(System.getProperty("user.dir")+"\\imgs");
        try{
            if(f.mkdir()) { 
                System.out.println("Directory Created");
            }
            else {
                System.out.println("Directory is not created");
            }
        } catch(Exception e){
            e.printStackTrace();
        } 
    }
    
}
