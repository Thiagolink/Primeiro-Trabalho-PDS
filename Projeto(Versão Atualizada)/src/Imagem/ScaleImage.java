package Imagem;


import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Thiago
 */
public class ScaleImage {
    /**
     * Método para pegar a imagem selecionada, redimensioná-la e salvar na pasta de imagens do projeto.
     * @param filename Caminho da imagem selecionada.
     * @param w Largura da nova imagem.
     * @param h Altura da nova imagem.
     * @return aux Caminho da imagem salva na pasta de imagens.
     * @throws IOException 
     */
    public String getScaledImage(String filename, int w, int h) throws IOException{
        File file = new File(filename);
        BufferedImage icc = ImageIO.read(file);
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(icc, 0, 0, w, h, null);
        g2.dispose();
        
        
        String aux = System.getProperty("user.dir")+"\\imgs\\";
        for(int i =0; i<100; i++){
            File f = new File(aux+"model"+i+".jpg");
            String aux2 = aux+"model"+i+".jpg";
            if(!(f.exists() && !f.isDirectory())){
                aux = aux2;
                break;
            }
        }
        File outputfile = new File(aux);
        System.err.append(aux);
        ImageIO.write(resizedImg, "jpg", outputfile);
        
        return aux;
        
    }
    
    
}
