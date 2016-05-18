package Files;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Thiago
 */
public interface FileTreatement extends IRenameFiles, ICreateFiles, IFileToTAD, IReadFiles, IWriteFiles{

    public String renameFileNameRegions(String filename);
    
    public String renameFileNameValue(String filename);
    
    public void createFileRegions(String filename);
    
    public void createFileValue(String filename);
    
    public void createFolder();
            
    public void writeInFileRegions(String filename, String word, String mouseX1, String mouseY1, String mouseX2, String mouseY2);
            
    public void writeFileValue(String filename, String blur, String color, String size);
            
    public void clearFile(String filename);
            
    public double valueImagesBlur(String filename, double blur);        
            
    public int valueImagesColor(String filename, int color);         
            
    public int valueImagesSize(String filename, int size);          
            
    public Map transformToMap(String filename);
           
    public ArrayList transformToArrayList(String filename);        
           
    public Set<String> nameAllRegions();         
}
