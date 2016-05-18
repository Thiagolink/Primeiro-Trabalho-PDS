
import Imagem.Segmentation;
import br.ufrn.imd.lp2.imagesegmentation.ImageInformation;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Thiago
 */
public class Checking {
    
    
    private Segmentation image = new Segmentation();

    
    /**
        Método para checar se o nome da região que irá ser adicionada no ArrayList já existe.
        @param list ArrayList que terá os elementos checados.
        @param name String com o nome que deverá ser checado no ArrayList.
        @return true Caso a String name já exista no ArrayList.
        @return false Caso a String name não exista no ArrayList.
    */
    public boolean checkName(ArrayList<String> list, String name){
        if(list.contains(name)){
            return true;
        }
        else{
            return false;
        }
    }
    
    /**
        Método para checar se a região que irá ser adicionada no map já existe.
        @param list ArrayList que checa os índices do map.
        @param Map Map com o nome da região com índice e um vetor de um inteiro com a região de x e y.
        @param region Número da região que deverá ser checada.
        @param seg Informações da imagem segmentada para ser usada com parâmetro em defineRegion.
        @return true Caso a região já exista no map.
        @return false Caso a região não exista no map.
    */
    public boolean checkRegion(ArrayList<String> list, Map<String, Integer[]> map, int region, ImageInformation seg){
        Iterator<String> it = list.iterator();
        Integer[] coordenates;
        for(int i =0; i < list.size(); i++){
            coordenates = map.get(list.get(i));
            if(region == image.defineRegion(coordenates[0], coordenates[1], seg))
                return true;
            
        }
        return false;
    }
    
    
    
}
