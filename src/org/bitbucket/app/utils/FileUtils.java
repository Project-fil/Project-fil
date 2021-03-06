package org.bitbucket.app.utils;

import org.bitbucket.app.utils.exceptions.WrongFormatException;
import org.bitbucket.app.utils.exceptions.WrongPathException;

import java.io.*;

public class FileUtils {

    public static File createFile(File file){
        try {
            if (file.createNewFile()) {
                return file;
            }
        } catch (IOException err) {
            throw new WrongPathException("File path is incorrect");
        }
        return file;
    }

    public static boolean writeToFile(File file, String content){
        if(content == null){
            return false;
        }
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
            writer.write(content);
            return true;
        }
        catch (IOException err){
            throw new WrongPathException("File not found");
        }
    }

    public static String readFile(File file) {
        StringBuilder fileContent = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!fileContent.toString().equals("")) {
                    fileContent.append("\r\n");
                }
                fileContent.append(line);
            }
            return fileContent.toString();
        } catch (IOException err) {
            throw new WrongPathException("Path is incorrect");
        }
    }

    public static boolean deleteFile(File file) {
        if (file.exists() && file.isFile()) {
            return file.delete();
        } else {
            return false;
        }
    }

    public static byte[] readBinFile(File file) {
        if(!file.exists()){
            throw new WrongPathException("File not exists");
        }
        FileInputStream fis;
        byte[] data = new byte[(int) file.length()];
        try{
            fis = new FileInputStream(file);
            if(fis.read(data) == -1){
                throw new WrongFormatException("Failed to read file");
            }
            fis.close();
            return data;
        } catch (IOException e){
            throw new WrongPathException("Failed to read file");
        }
    }


    public static void writeToBinFile(File file, byte[] data) {
        if(!file.exists()){
            throw new WrongPathException("File not exists", new IllegalArgumentException());
        }
        FileOutputStream fos;
        try{
            fos = new FileOutputStream(file);
            fos.write(data);
            fos.close();
        } catch (IOException e){
            throw new WrongPathException("Failed to write file");
        }
    }
}
