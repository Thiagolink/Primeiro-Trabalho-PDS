package Files;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
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
public class WriteTextFiles implements IWriteFiles{
 
    RenameTextFiles rename= new RenameTextFiles();
    
    CreateTextFiles createFiles = new CreateTextFiles();
    
    
    /**
        Método para escrever as informações das regiões das imagens no arquivo de texto, no caso guardando no máximo a posição de duas regiões.
        @param filename Nome do arquivo de texto.
        @param word Nome da palavra a ser guardada.
        @param mouseX1 Localização x do mouse na imagem da primeira região a ser guardado.
        @param mouseY1 Localização y do mouse na imagem da primeira região a ser guardado.
        @param mouseX2 Localização x do mouse na imagem da segunda região a ser guardado.
        @param mouseY2 Localização y do mouse na imagem da segunda região a ser guardado.
    */
    
    public void writeInFileRegions(String filename, String word, String mouseX1, String mouseY1, String mouseX2, String mouseY2){
        filename = rename.renameFileNameRegions(filename);
        File file = new File(filename);
        if (file.exists()){
            try{
                FileWriter writer = new FileWriter(filename, true);
                PrintWriter saveFile = new PrintWriter(writer);
                writer.write(word);
                writer.write(System.getProperty("line.separator"));
                writer.write(mouseX1);
                writer.write(System.getProperty("line.separator"));
                writer.write(mouseY1);
                writer.write(System.getProperty("line.separator"));
                writer.write(mouseX2);
                writer.write(System.getProperty("line.separator"));
                writer.write(mouseY2);
                writer.write(System.getProperty("line.separator"));
                writer.close();
                
            }
            catch(Exception erro) {
                JOptionPane.showMessageDialog(null,"writeInTextFile","Arquivo...", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        
        else{
            createFiles.createFileRegions(filename);
            try{
                FileWriter writer = new FileWriter(filename, true);
                PrintWriter saveFile = new PrintWriter(writer);
                writer.write(word);
                writer.write(System.getProperty("line.separator"));
                writer.write(mouseX1);
                writer.write(System.getProperty("line.separator"));
                writer.write(mouseY1);
                writer.write(System.getProperty("line.separator"));
                writer.write(mouseX2);
                writer.write(System.getProperty("line.separator"));
                writer.write(mouseY2);
                writer.write(System.getProperty("line.separator"));
                writer.close();
                
            }
            catch(Exception erro) {
                JOptionPane.showMessageDialog(null,"writeInTextFile", "Arquivo", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    
    /**
        Método para guardar os valores para a segmentação da imagem.
        @param filename Nome do arquivo de texto.
        @param blur Valor do blur.
        @param color Valor da cor.
        @param size Valor mínimo do tamanho.
    */
    @Override
    public void writeFileValue(String filename, String blur, String color, String size) throws FileNotFoundException{
        filename = rename.renameFileNameValue(filename);
        File file = new File(filename);
        if (file.exists()){
            clearFile(filename);
            try{
                FileWriter writer = new FileWriter(filename, true);
                PrintWriter saveFile = new PrintWriter(writer);
                writer.write(blur);
                writer.write(System.getProperty("line.separator"));
                writer.write(color);
                writer.write(System.getProperty("line.separator"));
                writer.write(size);
                writer.write(System.getProperty("line.separator"));
                writer.close();
                
            }
            catch(Exception erro) {
                JOptionPane.showMessageDialog(null,"writeInTextFile","Arquivo...", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        
        else{
            createFiles.createFileValue(filename);
            try{
                FileWriter writer = new FileWriter(filename, true);
                PrintWriter saveFile = new PrintWriter(writer);
                writer.write(blur);
                writer.write(System.getProperty("line.separator"));
                writer.write(color);
                writer.write(System.getProperty("line.separator"));
                writer.write(size);
                writer.write(System.getProperty("line.separator"));
                writer.close();
                                
            }
            catch(Exception erro) {
                JOptionPane.showMessageDialog(null,"writeInTextFile", "Arquivo", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    
    /**
        Método para limpar o arquivo de texto na hora de sobrescrever os valores da imagem para segmentação.
        @param filename Arquivo a ser limpado.
     * @throws java.io.FileNotFoundException
    */
    @Override
    public void clearFile(String filename) throws FileNotFoundException{
        PrintWriter writer = new PrintWriter(filename);
        writer.print("");
        writer.close();
    }
}
