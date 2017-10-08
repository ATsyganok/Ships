package com.atsyganok.creater.impl;

import com.atsyganok.creater.Creator;
import com.atsyganok.thread.*;
import com.atsyganok.object.*;

public class ShipCreatorImpl implements Creator {

    private Ship[] shipArray;

    private final int[] boxs = new int[]{10, 20, 30, 40, 50, 60};
    private Dock[] dockArray;

    @Override
    public void create(int i) {
        if (dockArray != null) {

            shipArray = new Ship[i];

            for (int n = 0; n < shipArray.length; ++n) {
                int capacity = boxs[(int) (Math.random() * 6)];
                shipArray[n] = new Ship(capacity, dockArray);
            }
        } else {
            System.exit(0);
        }
        paintShips();
    }

    private void paintShips() {
        for (Ship s : shipArray) {
            System.out.println(s.toString() + " created");
        }
    }

    public Ship[] getShipArray() {
        return shipArray;
    }

    public void setDockArray(Dock[] dockArray) {
        this.dockArray = dockArray;
    }

    @Override
    public Object[] getArray() {
        return shipArray;
    }
}
