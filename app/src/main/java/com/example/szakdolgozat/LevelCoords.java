package com.example.szakdolgozat;

public class LevelCoords {
    //left top right bottom
    //Platforms
    //Obstacles
    //Enemies        fifth int is distance
    //Goal
    //Lava
    private int[][] levelCoords = new int[][]
            {/*{0, 1050, 9550, 1080, 0,600,400,625,1050,725,1500,750,1800,700,2200,725,2175,700,2200,1050,2200,825,2500,850,2475,850,2500,1050,3000,850,3200,1050,6000,800,6025,1050,6000,800,6400,825,6700,650,7400,675,7800,600,9000,625,8975,400,9000,600,9000,400,9500,425,9475,0,9550,1080},*/
                    {0, 800, 1000, 0, 1200, 700, 1500, 1},
                        {1800, 300, 1900, 400, 2700, 1000, 2800, 1050},
                            {3400, 949, 3500, 1049, 1500, 200, 200, 300, 300, 500},
                                {9300, 300, 9400, 400},
                                    {200, 900, 800, 700, 700, 800},
                    {0, 1050, 2332, 1080},
                        {0, 800, 200, 1000},
                            {3300, 949, 3400, 1049, 1500},
                                {2000, 950, 2100, 1050},
                                    {200, 900, 800},
                    {0, 1050, 2332, 1080},
                        {2000, 800, 2332, 1000},
                            {3300, 949, 3400, 1049, 1500},
                                {0, 950, 100, 1050},
                                    {200, 900, 800}};

    public int[] getCoords(int line) {
        return levelCoords[line];
    }


}