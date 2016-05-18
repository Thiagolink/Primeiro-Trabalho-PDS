/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Files;

import java.io.FileNotFoundException;

/**
 *
 * @author Thiago
 */
public interface IWriteFiles {
    
    public void writeInFileRegions(String filename, String word, String mouseX1, String mouseY1, String mouseX2, String mouseY2);
    
    public void writeFileValue(String filename, String blur, String color, String size) throws FileNotFoundException;
    
    public void clearFile(String filename) throws FileNotFoundException;
    
}
