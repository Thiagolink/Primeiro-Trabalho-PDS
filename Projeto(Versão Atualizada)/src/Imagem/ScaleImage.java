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
    public String getScaledImage(String filename, int width , int height) throws IOException{
        File file = new File(filename);
        BufferedImage imageIcon = ImageIO.read(file);
        BufferedImage resizedImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = resizedImg.createGraphics();

        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.drawImage(imageIcon, 0, 0, width, height, null);
        graphics2D.dispose();
        
        
        String diretorioImagemCaminho = System.getProperty("user.dir")+"\\imgs\\";
        File fileImagem;
        String imagemCaminho;
        for(int i =0; i<100; i++){
            fileImagem = new File(diretorioImagemCaminho+"model"+i+".jpg");
            imagemCaminho = diretorioImagemCaminho+"model"+i+".jpg";
            if(!(fileImagem.exists() && !fileImagem.isDirectory())){
                diretorioImagemCaminho = imagemCaminho;
                break;
            }
        }
        File outputfile = new File(diretorioImagemCaminho);
        System.err.append(diretorioImagemCaminho);
        ImageIO.write(resizedImg, "jpg", outputfile);
        
        return diretorioImagemCaminho;
        
    }
    
    
}
