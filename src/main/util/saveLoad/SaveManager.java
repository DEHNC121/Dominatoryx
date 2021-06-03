package main.util.saveLoad;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class SaveManager {
    static public void save(String path){
        DataPack pack=DataPack.save();
        try{
            TypeReference<DataPack> reference= new TypeReference<DataPack>() {};
            ObjectMapper mapper= new ObjectMapper();
            InputStream stream= new FileInputStream(new File(path));
            mapper.writeValue(new File(path),pack);
        }catch (Exception e){
            System.out.println("Save error");
            e.printStackTrace();
        }
    }
    static public DataPack load(String path){
        try{
            TypeReference<DataPack> reference= new TypeReference<DataPack>() {};
            ObjectMapper mapper= new ObjectMapper();
            InputStream stream= new FileInputStream(new File(path));
            DataPack pack=mapper.readValue(stream,reference);
            stream.close();
            return pack;
        }catch (Exception e){
            System.out.println("Load error");
            e.printStackTrace();
        }
        return null;
    }
}
