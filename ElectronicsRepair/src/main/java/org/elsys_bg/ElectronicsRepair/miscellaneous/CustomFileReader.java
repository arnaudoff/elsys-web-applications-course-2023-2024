package org.elsys_bg.ElectronicsRepair.miscellaneous;

import java.io.*;
import java.util.Scanner;

public class CustomFileReader{
    public CustomFileReader(){}
    public static String readFile(String path) throws Exception{
        String fileContent = "";
        File file = new File(path);
        Scanner scanner = new Scanner(file);

        if(path == ""){
            throw new RuntimeException("ERR: Empty path attribute");
        }else if(!file.exists()){
            throw new RuntimeException("ERR: Filepath does not exist");
        }else if(!file.isFile()){
            throw new RuntimeException("ERR: Path is not a file");
        }

        scanner.useDelimiter("\\Z");
        fileContent = scanner.next();

        return fileContent;
    }
}