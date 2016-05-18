package Imagem;


import br.ufrn.imd.lp2.imagesegmentation.ImageInformation;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Thiago
 */
public class ToColor {
 
    
    
     /**
        Método para escurecer um pixel imagem segmentada.
        @param image Imagem que vai ser segmentada.
        @param x Localização x do pixel.
        @param y Localização y do pixel.
        @return color Pixel escurecido.
    */
    public static int darken(BufferedImage image, int x, int y){

        int dataBuffInt = image.getRGB(x,y); 

        Color c = new Color(dataBuffInt);
        int r = c.getRed()/2;
        int g = c.getGreen()/2;
        int b = c.getBlue()/2;
        int color = (r << 16) | (g << 8) | b;
        
        return color;
        
    }
    
        /**
        Método para escurecer a imagem e clarear as partes selecionas.
        @param seg Informação da imagem segmentada.
        @param region Região seleciona.
        @param list Vetor dizendo quais áreas já estão esbranqueçadas e quais estão escuras.
        @return icc Imagem pintada de acordo com as regiões selecionadas.
    */
    public ImageIcon whitening(ImageInformation seg, int region, ArrayList<Integer> list){
        BufferedImage img    = seg.getRegionMarkedImage();
        int count            = 0;
        int[] markedpixels   = seg.getSegmentedImageMap();

        
        if(list.get(region) == 1)
            list.set(region, 0);
        else
            list.set(region, 1);
        
        for(int j = 0; j < seg.getHeight(); j++){
            for(int i = 0; i < seg.getWidth(); i++){
                int aux = markedpixels[count];
                if(list.get(aux) != 1)
                    img.setRGB(i, j, darken(img, i, j));
                count++;
            }
        }           
        ImageIcon icc = new ImageIcon(img);
        
        
        return icc;
    }
    
    
        /**
        Método para devolver uma determinada tonalidade de cinza para a região.
        @param label Região que está requisitando um tom de cinza.
        @param numRegions Número total de regiões
        @return color Retorna uma cor específica baseado no total de regiões e o
        número da região específica.
    */
    public static int returnColor(int label, int numRegions){
       
       int reason = 255 / numRegions;
       int g      = (label * reason) % 255;
       int r      = (label * reason) % 255;
       int b      = (label * reason) % 255;
       int color  = (r << 16) | (g << 8) | b;
       
       return color;
   
   }
    /**
        Método para colorir de cinza a imagem segmentada.
        @param seg Informação da imagem segmentada.
        @return icc Imagem segmentada com tons de cinza.
    */

    public ImageIcon colorSegmentation(ImageInformation seg) {
        BufferedImage img    = seg.getRegionMarkedImage();
        int count            = 0;
        int[] markedpixels   = seg.getSegmentedImageMap();
        
        for(int j = 0; j < seg.getHeight(); j++){
            for(int i = 0; i < seg.getWidth(); i++){
               img.setRGB(i, j, returnColor(markedpixels[count],seg.getTotalRegions()));
               count++;
            }
        }
        ImageIcon icc = new ImageIcon(img);
        return icc;
    }
    
    
    
}
