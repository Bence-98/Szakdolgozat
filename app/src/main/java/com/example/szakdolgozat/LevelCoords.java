package com.example.szakdolgozat;

public class LevelCoords {
    //left top right bottom
    private int[] levelOne = new int[]{0, 1000, 2332, 1080, 1500, 800, 2332, 1000, 0, 800, 200, 1000, 1800, 600, 2332, 650/*,0,800,2000,850,500,600,2332,650*/};
    private int[] levelTwo = new int[]{1000, 1000, 1200, 1200};


    public int[] getLevelOne() {
        return levelOne;
    }

    public int[] getLevelTwo() {
        return levelTwo;
    }
}