package com.example.szakdolgozat;

public class LevelCoords {
    //left top right bottom
    private int[] levelOne = new int[]{0, 1050, 2332, 1080,300,850,500,900,550,750,800,800,900,600,1500,650,1600,500,2000,550};
    private int[] levelOneObs = new int[]{0,700,100,800};
    private int[] levelTwo = new int[]{1000, 1000, 1200, 1200};


    public int[] getLevelOne() {
        return levelOne;
    }

    public int[] getLevelTwo() {
        return levelTwo;
    }

    public int[] getLevelOneObs(){return levelOneObs;}
}