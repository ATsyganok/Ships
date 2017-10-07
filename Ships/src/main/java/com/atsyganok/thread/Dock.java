package com.atsyganok.thread;

import com.atsyganok.object.*;

// док принимает на вход корабль и разгружает его при этом док занят занят
public class Dock {
    private String name;
    private volatile boolean ready=true;
    private volatile int currentLenght;

    public Dock(String name){
        this.name=name;
    }

    // getters & setters
    public boolean isReady() {
        return ready;
    }
    public void setReady(boolean ready) {
        this.ready = ready;
    }
    public String getName() {
        return name;
    }
    public synchronized int getCurrentLenght() {
        return currentLenght;
    }
    public void setCurrentLenght(int currentLenght) {
        this.currentLenght = currentLenght;
    }

    public synchronized void unloading(Ship Ship){
        currentLenght= Ship.getCapacity(); //set current line(lenght) of dock
        while (Ship.getCapacity()>0) {
            try {
                wait(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Ship.setCapacity(Ship.getCapacity()-1); // set capacity of chip
            currentLenght= Ship.getCapacity(); //set current line(lenght) of dock
            System.out.println(this.name+" Ship: "+ Ship.getId()+" unload 1 box, left: "+ Ship.getCapacity());
        }
        paintLine();
        System.out.println(this.name+" Ship: "+ Ship.getId()+" finished unloading, box, left: "+ Ship.getCapacity());
        System.out.println(this.name+" ready to work");
        paintLine();
        ready=true;
        notify();
    }
    private void paintLine(){
        System.out.println("---------------------------------------");
    }

    @Override
    public String toString() {
        return "Dock: "+name;
    }
}


