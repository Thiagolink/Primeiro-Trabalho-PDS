/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Files;

/**
 *
 * @author Thiago
 */
public interface IReadFiles {
 
    public double valueImagesBlur(String filename, double blur);
    
    public int valueImagesColor(String filename, int color);
    
    public int valueImagesSize(String filename, int size);
}
