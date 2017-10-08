package com.atsyganok.object;

import com.atsyganok.thread.*;

// док принимает на вход корабль и разгружает его при этом док занят занят
public class Dock {
    private String name;
    private volatile boolean ready = true;
    private volatile int currentLenght;

    public Dock(String name) {
        this.name = name;
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

    public synchronized void unloading(Ship ship) {
        currentLenght = ship.getCapacity(); //set current line(lenght) of dock
        while (ship.getCapacity() > 0) {
            try {
                wait(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ship.setCapacity(ship.getCapacity() - 1); // set capacity of chip
            currentLenght = ship.getCapacity(); //set current line(lenght) of dock
            System.out.println(this.name + " Ship: " + ship.getId() + " unload 1 box, left: " + ship.getCapacity());
        }
        paintLine();
        System.out.println(this.name + " Ship: " + ship.getId() + " finished unloading, box, left: " + ship.getCapacity());
        System.out.println(this.name + " ready to work");
        paintLine();
        setReady(true);
        //notify();
        notifyAll();
    }

    private void paintLine() {
        System.out.println("---------------------------------------");
    }

    @Override
    public String toString() {
        return "Dock: " + name;
    }
}

