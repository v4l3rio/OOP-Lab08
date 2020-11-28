package it.unibo.oop.lab.mvc;


import java.util.ArrayList;
import java.util.List;

public class ImplController implements Controller {
    
    List<String> history;

    String current;

    public ImplController() {
        this.history = new ArrayList<>();
        this.current = null;
    }

    @Override
    public void setStringToPrint(String s) {
           if(s==null) {
               throw new IllegalArgumentException();
           }
           this.current = s;
           history.add(s);
    }

    @Override
    public String getStringToPrint() {
       
        return this.current;
    }

    @Override
    public void print() {
        if(this.current==null) {
            throw new IllegalStateException();
        }
        System.out.println(this.current);

    }

    @Override
    public List<String> showHistory() {
       return history;
    }

}
