
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
    
    
    public void openFile(JLabel areaImage, Segmentation imageTreatment, ArrayList<Integer> listInt, JList listRegionSegments, ArrayList<String> listNameRegion, Map<String, Integer[]> mapImageTreatment){
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
                Logger.getLogger(SystemSegmentationGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        
            areaImage.setIcon(imageTreatment.originalImage(imageTreatment.getFilename()));
        
            transformTextListImage(imageTreatment, listRegionSegments, listNameRegion, mapImageTreatment);
            
        }
    }
    
    public void saveInformation(Segmentation imageTreatment, String blurLvl, String colorLvl, String sizeLvl){
            
            imageTreatment.setColor(Integer.parseInt(colorLvl));
            imageTreatment.setBlurLevel(Double.parseDouble(blurLvl));
            imageTreatment.setMinSize(Integer.parseInt(sizeLvl));
    }
    
    /**
        Método que transforma as informações das regiões da imagem do arquivo de texto em estruturas de dados 
        * e coloca os elementos no JList1.
        @param filename Nome do arquivo de texto.
    */    
    public void transformTextListImage(Segmentation imageTreatment, JList listRegionSegments, ArrayList<String> listNameRegion, Map<String, Integer[]> mapImageTreatment) {
        DefaultListModel model = new DefaultListModel();
                
        mapImageTreatment = fileToTAD.transformToMap(imageTreatment.getFilename());
        listNameRegion = fileToTAD.transformToArrayList(imageTreatment.getFilename());

        listNameRegion = tadManipulation.sort(listNameRegion);
     
        Iterator<String> it = listNameRegion.iterator();
        while(it.hasNext()){
            model.addElement(it.next());
        }
        
        listRegionSegments.setModel(model);
    }
    
    /**
        Método que dada a região escolhida, irá colocar na jComboBox todos as imagens que possuem a região selecionada.
        @param region Região que deverá ser procurada nas imagens.
    */
    public void transformTextListRegion(String region, JList listFiles, ArrayList<String> listRegions) throws FileNotFoundException{
        DefaultListModel model = new DefaultListModel();
        
        listRegions = tadManipulation.regionFile(region);
             
        Iterator<String> it = listRegions.iterator();
        while(it.hasNext()){
            model.addElement(it.next());
        }
        
        listFiles.setModel(model);
    }
    
    public void readValues(String filename, Segmentation imageTreatment, JTextField blurLvl, JTextField colorLvl, JTextField sizeLvl){
        imageTreatment.setFilename(filename);
        imageTreatment.setBlurLevel(readFiles.valueImagesBlur(imageTreatment.getFilename(), imageTreatment.getBlurLevel()));
        blurLvl.setText(String.valueOf(imageTreatment.getBlurLevel()).substring(0, 4));
        imageTreatment.setColor(readFiles.valueImagesColor(imageTreatment.getFilename(), imageTreatment.getColor()));
        colorLvl.setText(String.valueOf(imageTreatment.getColor()));
        imageTreatment.setMinSize(readFiles.valueImagesSize(imageTreatment.getFilename(), imageTreatment.getMinSize()));
        sizeLvl.setText(String.valueOf(imageTreatment.getMinSize()));
    }
    
    public void colorDeterminedArea(Object o, JLabel Image, ImageInformation segment, Segmentation imageTreatment, ArrayList<Integer> listInt, Map<String, Integer[]> map){
        Integer coordenadas[] = map.get(o.toString());
            int region1 = imageTreatment.defineRegion(coordenadas[0], coordenadas[1], segment);
                
            int region2;
            if (coordenadas[2] != 0 && coordenadas[3] != 0){
                region2 = imageTreatment.defineRegion(coordenadas[2], coordenadas[3], segment);
                Image.setIcon(toColor.whitening(segment, region2, listInt));
                }
            Image.setIcon(toColor.whitening(segment, region1, listInt));
    }
}

    
