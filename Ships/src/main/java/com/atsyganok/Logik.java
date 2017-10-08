package com.atsyganok;


import com.atsyganok.creater.Creator;
import com.atsyganok.creater.impl.DockCreatorImpl;
import com.atsyganok.creater.impl.ShipCreatorImpl;
import com.atsyganok.thread.Ship;
import com.atsyganok.reader.impl.ReaderImpl;
import com.atsyganok.object.Dock;

import java.util.Arrays;

class Logik {
    public static void main(String[] args) {

        ReaderImpl reader = new ReaderImpl();
        reader.read();

        DockCreatorImpl dockCreator = new DockCreatorImpl();
        //Creator dockCreator=new DockCreatorImpl();
        dockCreator.create(reader.getDockCount());
        Dock[] dockArray = dockCreator.getDockArray();
        //Dock[] dockArray=(Dock[]) dockCreator.getArray();

        System.out.println(Arrays.toString(dockArray));

        ShipCreatorImpl shipCreator = new ShipCreatorImpl();
        //Creator shipCreator=new ShipCreatorImpl();
        shipCreator.setDockArray(dockArray);
        //((ShipCreatorImpl)shipCreator).setDockArray(dockArray);
        shipCreator.create(reader.getShipCount());
        Ship[] shipArray = shipCreator.getShipArray();
        //Ship[] shipArray=(Ship[]) shipCreator.getArray();

        System.out.println(shipArray[1].toString());

        begin(shipArray);
    }

    private static void begin(Ship[] shipArray) {
        System.out.println("---------------------------------------");
        for (Ship s : shipArray) {
            s.start();
        }
        for (Ship s : shipArray) {
            try {
                s.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("All ships are unloaded");
    }
}
