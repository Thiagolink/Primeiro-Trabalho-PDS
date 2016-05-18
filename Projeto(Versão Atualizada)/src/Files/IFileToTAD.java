/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Files;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Thiago
 */
public interface IFileToTAD {

    public Map transformToMap(String filename);
    
    public ArrayList transformToArrayList(String filename);
    
    public Set<String> nameAllRegions() throws FileNotFoundException;
}
