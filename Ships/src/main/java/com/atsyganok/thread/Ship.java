package com.atsyganok.thread;

import com.atsyganok.object.*;

import java.util.Arrays;

/**
 * Created by Андрей on 18.04.2016.
 */
public class Ship extends Thread {
    private volatile int capacity;
    private Dock[] dockArray;

    public Ship(int capacity,Dock[] dockArray){
        this.capacity=capacity;
        this.dockArray=dockArray;
    }

    // getters & setters
    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    public boolean isEmpty(){
        return capacity==0;
    }

    @Override
    public void run() {
        messageStart(this); // message that ship-Thread is run;
        while (!(dockStatus())){// check which dock is free
            Dock cDock;
            if((cDock=minDockLine())!=null){ // selection of shortest dock line and set it wait;
                setWait(cDock,this);
            }
        }
        enterDoc(freeDock());  // enter to dock for unload
    }

    private void messageStart(Ship ship){
        System.out.println("ship "+ship.getId()+" near of the docks");
    }

    private void messageBussy(Ship ship,Dock dock){
        System.out.println("all docks are busy ship " + ship.getId() + " wait... for "+dock.getName()+" it lenght: "+dock.getCurrentLenght());
    }

    private void setWait(Dock dock,Ship ship){
        messageBussy(ship,dock);
        dock.setCurrentLenght(dock.getCurrentLenght()+ship.capacity); //change the dock line
        try {
            synchronized (dock) {
                dock.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void enterDoc(Dock dock){
        dock.setReady(false);
        System.out.println("Ship: "+this.getId()+" went on unloading to "+dock.getName());
        dock.unloading(this);
    }

    private Boolean dockStatus(){    // check which dock is free
        for(int n=0;n<dockArray.length;++n){
            if(dockArray[n].isReady())
                return true;
        }
        return false;
    }

    private Dock minDockLine(){
        int min=Integer.MAX_VALUE;
        int currentLenght;
        Dock minDock=null;
        for(Dock d:dockArray){
            if(min>=(currentLenght=d.getCurrentLenght())){
                min=currentLenght;
                minDock=d;
            }
        }
        return minDock;
    }

    private int summ(int a,int b){
        return a+b;
    }

    private Dock freeDock(){
        for(Dock d:dockArray){
            if(d.isReady())
                return d;
        }
        return null;
    }

    @Override
    public String toString(){
        return "Ship: "+this.getId()+" Box: "+this.capacity +" docks "+ Arrays.toString(dockArray);
    }
}
