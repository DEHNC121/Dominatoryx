package main.util.saveLoad;

public class DataPack {
    //load DataPlayer first!!
    public DataRoundManager drm;
    public DataMap dm;
    DataPack(){
        drm=new DataRoundManager();
        dm=new DataMap();
    }
    static public DataPack save(){
        DataPack out=new DataPack();
        out.drm=DataRoundManager.save();
        out.dm=DataMap.save();
        return out;
    }
}
