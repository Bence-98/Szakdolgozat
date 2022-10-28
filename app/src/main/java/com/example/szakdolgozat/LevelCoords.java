package com.example.szakdolgozat;

public class LevelCoords {
    //Player        starting point
    //Platforms     firstleft top lastleft float
    //Obstacles     left top
    //Enemies       left top right bottom distance
    //Goal          left top
    //Lava          firstleft top lastleft
    private int[][] levelCoords = new int[][]
            {       {200,440},
                        {0, 500, 700, 0, 1000, 300, 1300, 1,1600,800,1900,1,2000,700,2100,1,2400,900,2500,0,2600,1000,3400,1,3000,800,3000,1,3500,900,5100,0,5200,1000,6000,1,5300,600,5700,1,6000,800,6100,0,6300,500,6700,1,7000,400,7400,1,7900,600,8300,1,8700,700,9500,1,9600,700,10000,0},
                            {1600,750,0,1700,750,0,1800,750,0,1900,750,0,3900,850,0,4300,850,0,7100,350,0},
                                {8800,600,8900,700,600},
                                    {9700, 500},
                                        {2600,900,3400,5200,900,5900},
                    {200,440},
                        {0, 1050, 2332, 0},
                            {1000,1000},
                                {1100,950,1200,1050,500},
                                    {1900,850},
                                        {},
                    {200,440},
                        {0, 1050, 2332, 0},
                            {1000,1000},
                                {1100,950,1200,1050,500},
                                    {1900,850},
                                        {}};

    public int[] getCoords(int line) {
        return levelCoords[line];
    }
}