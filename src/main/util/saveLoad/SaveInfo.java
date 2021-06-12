package main.util.saveLoad;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SaveInfo {
    public String time;
    SaveInfo(){
        SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date date = new Date();
        time=formatter.format(date).toString();
    }
}
