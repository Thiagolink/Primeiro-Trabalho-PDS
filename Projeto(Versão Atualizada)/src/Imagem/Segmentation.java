package Imagem;





import br.ufrn.imd.lp2.imagesegmentation.ImageInformation;
import br.ufrn.imd.lp2.imagesegmentation.ImageSegmentation;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * Classe para realizar segmentação de imagens.
 * 
 * @author Thiago Oliveira
 */
public class Segmentation {
    
    
    // Nível do borrão.
    double blurLevel;
    //Nível da cor.
    int color;
    //Nível mínimo das segmentações.
    int minSize;
    //String com a localização da imagem.
    String filename;
     /**
        Método construtor, do nothing.
     * @param blurLevel
     * @param color
     * @param minSize
     * @param filename
    */
    public Segmentation(double blurLevel, int color, int minSize, String filename){
        this.blurLevel = blurLevel;
        this.color = color;
        this.minSize = minSize;
        this.filename = filename;
    }
      
    
    public Segmentation(){
        
    }
    
    
    /**
        Define a região segmentada de acordo com os valores de x e y.
        @param x Localização x na imagem.
        @param y Localização y na imagem.
        @param segment Informação da imagem segmentada.
     * @return 
    */
    public int defineRegion(int x, int y, ImageInformation segment){
        int[] markedpixels   = segment.getSegmentedImageMap();
        int aux = 0;
        int count = 0;
        for(int j = 0; j < segment.getHeight(); j++){
            for(int i = 0; i < segment.getWidth(); i++){
                if(x == i && y == j)
                    aux= count;
                count++;
            }
        }
        System.out.println(aux);
        
        return markedpixels[aux];
    }

    /**
        Método para devolver a imagem segmentada.
        @param filename Caminho da imagem.
        @param blur Nível do borrão.
        @param color Nível da cor.
        @param size Tamanho da mínimo das segmentações.
        @return seg Informação da segmentação.
    */
    
    
    
    public ImageInformation imageSegmentation(String filename, double blurLvl, int colorLvl, int sizeLvl){
        ImageInformation seg = ImageSegmentation.performSegmentation(filename, blurLvl, colorLvl, sizeLvl);
        return seg;
    }
    
    public ImageInformation imageSegmentantiton(){
        ImageInformation seg;
        seg        = imageSegmentation(getFilename(), getBlurLevel(), getColor(), getMinSize());
        return seg;
    }
    
    /**
        Método para para devolver uma imagem.
        @param filename caminho da imagem.
        @return imagem em formato de ícone.
    */
    public ImageIcon originalImage(String filename){
        ImageIcon iconImage = new ImageIcon(filename);
        return iconImage;
    }
    
    /**
        Método para devolver o número total de segmentações da imagem.
        @param seg Informação da segmentação.
        @return seg.getTotalRegions() Número de regiões da segmentação.
    */
    public int totalRegions(ImageInformation seg){
        return seg.getTotalRegions();
    }
    
    /**
        Método para devolver a imagem segmentada.
        @param seg Informação da segmentação.
        @return iconImage ImageIcon da imagem segmentada.
    */
    public ImageIcon markedImage(ImageInformation segment){
        ImageIcon iconImage        = new ImageIcon(segment.getRegionMarkedImage());
        return iconImage;
    }
    
    
    

    public double getBlurLevel() {
        return blurLevel;
    }

    public int getColor() {
        return color;
    }

    public int getMinSize() {
        return minSize;
    }

    public String getFilename() {
        return filename;
    }

    public void setBlurLevel(double blurLevel) {
        this.blurLevel = blurLevel;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setMinSize(int minSize) {
        this.minSize = minSize;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
    
    
    
}
