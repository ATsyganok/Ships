package com.atsyganok.creater.impl;

import com.atsyganok.creater.Creator;
import com.atsyganok.object.*;

public class DockCreatorImpl implements Creator {

    private Dock[] dockArray;

    @Override
    public void create(int i) {
        dockArray = new Dock[i];
        for (int n = 0; n < dockArray.length; ++n)
            dockArray[n] = new Dock("dock_" + n);
        paintDocks();
    }

    private void paintDocks() {
        for (Dock d : this.dockArray) {
            System.out.println(d.toString() + " created");
        }
    }

    public Dock[] getDockArray() {
        return dockArray;
    }

    @Override
    public Object[] getArray() {
        return dockArray;
    }
}
