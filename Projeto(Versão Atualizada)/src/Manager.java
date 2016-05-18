
import Imagem.Segmentation;
import Files.IFileToTAD;
import Files.IReadFiles;
import Files.ReadTextFiles;
import Files.TextFileToTAD;
import Imagem.ScaleImage;
import Imagem.ToColor;
import br.ufrn.imd.lp2.imagesegmentation.ImageInformation;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Thiago
 */
public class Manager {
    
    Segmentation imageTreatments = new Segmentation(); 
    
    IFileToTAD fileToTAD =  new TextFileToTAD();
 
    TADManipulation tadManipulation = new TADManipulation();
    
    ScaleImage scaleImage = new ScaleImage();
    
    IReadFiles readFiles = new ReadTextFiles();
    
    ToColor toColor = new ToColor();
    
    public Manager(){
        
    }
    
    
    public void openFile(JLabel Image, Segmentation imageTreatment, ArrayList<Integer> listInt, JList jList1, ArrayList<String> list, Map<String, Integer[]> map){
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File file = new File("C:\\Users\\Thiago\\Desktop\\ProjetoLP2\\ProjetoLP2\\imgs");
        chooser.setCurrentDirectory(file);
        File f   = chooser.getSelectedFile();
        
        if(f.isDirectory() || f.getAbsolutePath().endsWith(".jpg")){
            imageTreatment.setFilename(f.getAbsolutePath());
            
            try {
                imageTreatment.setFilename(scaleImage.getScaledImage(imageTreatment.getFilename(), 340, 414));
            } catch (IOException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        
            Image.setIcon(imageTreatment.originalImage(imageTreatment.getFilename()));
        
            transformTextJList1(imageTreatment, jList1, list, map);
            
        }
    }
    
    public void saveInformation(Segmentation image, String jTextField1, String jTextField2, String jTextField3){
            
            image.setColor(Integer.parseInt(jTextField2));
            image.setBlurLevel(Double.parseDouble(jTextField1));
            image.setMinSize(Integer.parseInt(jTextField3));
    }
    
    /**
        Método que transforma as informações das regiões da imagem do arquivo de texto em estruturas de dados 
        * e coloca os elementos no JList1.
        @param filename Nome do arquivo de texto.
    */    
    public void transformTextJList1(Segmentation imageTreatment, JList jList1, ArrayList<String> list, Map<String, Integer[]> map) {
        DefaultListModel model = new DefaultListModel();
                
        map = fileToTAD.transformToMap(imageTreatment.getFilename());
        list = fileToTAD.transformToArrayList(imageTreatment.getFilename());

        list = tadManipulation.sort(list);
     
        Iterator<String> it = list.iterator();
        while(it.hasNext()){
            model.addElement(it.next());
        }
        
        jList1.setModel(model);
    }
    
    /**
        Método que dada a região escolhida, irá colocar na jComboBox todos as imagens que possuem a região selecionada.
        @param region Região que deverá ser procurada nas imagens.
    */
    public void transformTextJList2(String region, JList jList2, ArrayList<String> listRegions) throws FileNotFoundException{
        DefaultListModel model = new DefaultListModel();
        
        listRegions = tadManipulation.regionFile(region);
             
        Iterator<String> it = listRegions.iterator();
        while(it.hasNext()){
            model.addElement(it.next());
        }
        
        jList2.setModel(model);
    }
    
    public void readValues(String filename, Segmentation imageTreatment, JTextField jTextField1, JTextField jTextField2, JTextField jTextField3){
        imageTreatment.setFilename(filename);
        imageTreatment.setBlurLevel(readFiles.valueImagesBlur(imageTreatment.getFilename(), imageTreatment.getBlurLevel()));
        jTextField1.setText(String.valueOf(imageTreatment.getBlurLevel()).substring(0, 4));
        imageTreatment.setColor(readFiles.valueImagesColor(imageTreatment.getFilename(), imageTreatment.getColor()));
        jTextField2.setText(String.valueOf(imageTreatment.getColor()));
        imageTreatment.setMinSize(readFiles.valueImagesSize(imageTreatment.getFilename(), imageTreatment.getMinSize()));
        jTextField3.setText(String.valueOf(imageTreatment.getMinSize()));
    }
    
    public void colorDeterminedArea(Object o, JLabel Image, ImageInformation seg, Segmentation imageTreatment, ArrayList<Integer> listInt, Map<String, Integer[]> map){
        Integer coordenadas[] = map.get(o.toString());
            int region1 = imageTreatment.defineRegion(coordenadas[0], coordenadas[1], seg);
                
            int region2;
            if (coordenadas[2] != 0 && coordenadas[3] != 0){
                region2 = imageTreatment.defineRegion(coordenadas[2], coordenadas[3], seg);
                Image.setIcon(toColor.whitening(seg, region2, listInt));
                }
            Image.setIcon(toColor.whitening(seg, region1, listInt));
    }
}

    
