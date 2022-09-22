package com.example.szakdolgozat;

public class LevelCoords {
    //left top right bottom
    //platforms
    //Obstacles
    //Goal
    private int[][] levelCoords = new int[][]{{0, 1050, 50000, 1080, 300, 850, 500, 900, 550, 750, 800, 800, 900, 600, 1500, 650, 1600, 500, 2000, 550,2500,500,3000,550},
            {0, 700, 100, 800, 2000,500,2200,800},
            {1800, 400, 2000, 500},
            {0, 1050, 2332, 1080},
            {0,800,200,1000},
            {2000,800,2332,1000},
            {0, 1050, 2332, 1080},
            {2000,800,2332,1000},
            {0,800,200,1000}};

    public int[] getCoords(int line) {
        return levelCoords[line];
    }



}