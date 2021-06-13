package main.graphics;

import main.states.GameStyle;

import java.awt.*;
import java.util.ArrayList;

public class NumericScrollField extends ScrollField{

    private int begin=-1;
    private int end=-1;

    public NumericScrollField(Rectangle outRectangle, ArrayList<String> fieldsIN, Color color, int showNumberIn) {
        super(outRectangle, fieldsIN, color, showNumberIn);
    }
    public NumericScrollField(Rectangle outRectangle, ArrayList<String> fieldsIN, GameStyle.PALETTE colorStyle, int showNumberIn) {
        super(outRectangle, fieldsIN, colorStyle, showNumberIn);
    }
    public NumericScrollField(ScrollField scrollField) {
        super(scrollField);
    }
    @Override
    public void setArray (ArrayList<String> entries) {
        begin=-1;
        end=-1;
        if (this.fields != entries) {
            this.fields = entries;
            if ( getMiddleIndex() >entries.size()){
                setMiddleIndex(entries.size()-1);
            }
        }
    }

    public void setArray (int begin, int end) {
        this.begin=begin;
        this.end=end;
        ArrayList<String> dataList=new ArrayList<>();
        for (int i=begin; i<=end;i++){
            dataList.add(String.valueOf(i));
        }
        this.fields = dataList;
        if ( getMiddleIndex() >this.end){
            setMiddleIndex(dataList.size()-1);
        }
    }
}
