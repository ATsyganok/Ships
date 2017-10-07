package com.atsyganok.object;

import com.atsyganok.thread.*;

public class Ship extends Thread {
    private volatile int capacity;
    private Dock[] dockArray;

    public Ship(int capacity, Dock[] dockArray){
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

    private void messageStart(Ship Ship){
        System.out.println("ship "+ Ship.getId()+" near of the docks");
    }

    private void messageBussy(Ship Ship,Dock Dock){
        System.out.println("all docks are busy ship " + Ship.getId() + " wait... for "+ Dock.getName()+" it lenght: "+ Dock.getCurrentLenght());
    }

    private void setWait(Dock Dock,Ship Ship){
        messageBussy(Ship, Dock);
        Dock.setCurrentLenght(Dock.getCurrentLenght()+ Ship.capacity); //change the dock line
        try {
            synchronized (Dock) {
                Dock.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void enterDoc(Dock Dock){
        Dock.setReady(false);
        System.out.println("Ship: "+this.getId()+" went on unloading to "+ Dock.getName());
        Dock.unloading(this);
    }

    private Boolean dockStatus(){    // check which dock is free
        for(int n=0;n<dockArray.length;++n){
            if(dockArray[n].isReady()==true)
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

    private Dock freeDock(){
        for(Dock d:dockArray){
            if(d.isReady())
                return d;
        }
        return null;
    }

    @Override
    public String toString(){
        return "Ship: "+this.getId()+" Box: "+this.capacity;
    }
}
