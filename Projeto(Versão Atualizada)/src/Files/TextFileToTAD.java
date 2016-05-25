package Files;

import Files.RenameTextFiles;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Thiago
 */
public class TextFileToTAD implements IFileToTAD {

    RenameTextFiles rename = new RenameTextFiles();

    CreateTextFiles createFiles = new CreateTextFiles();

    WriteTextFiles writeFiles = new WriteTextFiles();

    /**
     * Método para transformar as informações do arquivo de texto em um Map.
     *
     * @param filename Localização do arquivo de texto.
     * @return map Map com o name gravado com índice e com um vetor de inteiros
     * com as informações de x e de y.
     */
    public Map transformToMap(String filename) {
        filename = rename.renameFileNameRegions(filename);
        String nameImage;
        int X1, X2, Y1, Y2;

        Map<String, Integer[]> mapCoordenadaImage = new HashMap<>();

        try {
            Scanner fileCoordenadaTexto = new Scanner(new File(filename));
            while (fileCoordenadaTexto.hasNext()) {
                nameImage = fileCoordenadaTexto.next();
                X1 = Integer.parseInt(fileCoordenadaTexto.next());
                Y1 = Integer.parseInt(fileCoordenadaTexto.next());
                X2 = Integer.parseInt(fileCoordenadaTexto.next());
                Y2 = Integer.parseInt(fileCoordenadaTexto.next());
                mapCoordenadaImage.put(nameImage, new Integer[]{X1, Y1, X2, Y2});
            }
            fileCoordenadaTexto.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Arquivo não encontrado", "Transform", JOptionPane.INFORMATION_MESSAGE);
        }
        return mapCoordenadaImage;
    }

    /**
     * Método para transformar os nomes do arquivo de texto em ArrayList para
     * que seja possível resgatar os valores do Map.
     *
     * @param filename Nome do arquivo de texto no qual se vai pegar as
     * informações.
     * @return list ArrayList com o nome com os nomes do arquivo de texto.
     */
    public ArrayList transformToArrayList(String filename) {
        filename = rename.renameFileNameRegions(filename);
        ArrayList<String> listArquivoNome = new ArrayList<>();

        try {
            Scanner fileNomeArquivo = new Scanner(new File(filename));
            while (fileNomeArquivo.hasNext()) {
                listArquivoNome.add(fileNomeArquivo.next());
                fileNomeArquivo.next();
                fileNomeArquivo.next();
                fileNomeArquivo.next();
                fileNomeArquivo.next();
            }
            fileNomeArquivo.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Arquivo não encontrado", "transformtoArrayList", JOptionPane.INFORMATION_MESSAGE);
        }
        return listArquivoNome;
    }

    /**
     * Método para colocar todos os nomes das regiões de cada imagem um set
     * usando a pasta imgs do projeto.
     *
     * @return names Set com o nome de todas as regiões de todas as imagens da
     * pasta imgs do projeto.
     */
    public Set<String> nameAllRegions() throws FileNotFoundException {
        File folder = new File(System.getProperty("user.dir") + "\\imgs");
        File[] listOfFiles = folder.listFiles();
        Set<String> setNames = new HashSet<>();

        File file;
        Scanner fileNomeRegiao;
        for (int i = 0; i < listOfFiles.length; i++) {
            file = listOfFiles[i];
            if (file.isFile() && file.getName().endsWith("regioes.txt")) {
                fileNomeRegiao = new Scanner(file);
                while (fileNomeRegiao.hasNext()) {
                    setNames.add(fileNomeRegiao.next());
                    fileNomeRegiao.next();
                    fileNomeRegiao.next();
                    fileNomeRegiao.next();
                    fileNomeRegiao.next();
                }

                fileNomeRegiao.close();

            }
        }

        return setNames;
    }

}
