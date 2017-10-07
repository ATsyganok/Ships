package com.atsyganok.reader.impl;

import com.atsyganok.reader.Reader;

import java.util.Scanner;

/**
 * Created by Andrey on 07.10.2017.
 */
public class ReaderImpl implements Reader {
        private Scanner s;
        private int shipCount;
        private int dockCount;

        public void read(){
            s=new java.util.Scanner(System.in);
            System.out.print("Enter col of Ship: ");
            this.shipCount=s.nextInt();
            System.out.print("Enter col of Dock: ");
            this.dockCount=s.nextInt();
        }

        public int getShipCount() {
            return shipCount;
        }

        public int getDockCount() {
            return dockCount;
        }
    }