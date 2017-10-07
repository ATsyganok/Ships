package com.atsyganok;


import com.atsyganok.creater.Creator;
import com.atsyganok.creater.impl.DockCreatorImpl;
import com.atsyganok.creater.impl.ShipCreatorImpl;
import com.atsyganok.object.Ship;
import com.atsyganok.reader.impl.ReaderImpl;
import com.atsyganok.thread.Dock;
import com.atsyganok.reader.*;

public class Logik {
    public static void main(String[] args) throws InterruptedException{

        ReaderImpl reader =new ReaderImpl();
        reader.read();

        DockCreatorImpl dockCreator=new DockCreatorImpl();
        dockCreator.create(reader.getDockCount());
        Dock[] dockArray=dockCreator.getDockArray();

        ShipCreatorImpl shipCreator=new ShipCreatorImpl();
        shipCreator.setDockArray(dockArray);
        shipCreator.create(reader.getShipCount());
        Ship[] shipArray=shipCreator.getShipArray();

        start(shipArray);

    }

    public static void start(Ship[] shipArray) throws InterruptedException{
        System.out.println("---------------------------------------");
        /*for(Ship s:shipArray){
            s.start();
        }
        for(Ship s:shipArray){
            try{
                s.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/

        for (Ship ship:shipArray){
            ship.start();
            ship.join();
        }

        System.out.println("All ships are unloaded");
    }
}
