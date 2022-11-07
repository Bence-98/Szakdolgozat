package com.example.szakdolgozat;

public class LevelCoords {
    //Player        starting point x,y
    //Platforms     firstLeft top lastRight float
    //Obstacles     left top rotate
    //Enemies       left top distance
    //Goal          left top
    //Lava          firstleft top lastRight
    private int[][] levelCoords = new int[][]
            {       {200,440},
                        {0, 500, 700, 0, 900, 300, 1400, 1,1600,800,2000,1,2000,700,2200,1,2400,900,2600,0,2600,1000,3500,1,2900,800,3200,1,3500,900,5200,0,5200,1000,6100,1,5300,600,5800,1,6000,800,6200,0,6300,500,6800,1,7000,400,7500,1,7900,600,8400,1,8700,700,9600,1,9600,700,10000,0},
                            {1600,750,0,1700,750,0,1800,750,0,1900,750,0,3900,850,0,4300,850,0,7100,350,0},
                                {8800,600,600},
                                    {9700, 500},
                                        {2600,900,3500,5200,900,6000},
                    {200,840},
                        {0,900,500,0,600,900,900,1,900,700,1300,1,1500,900,1800,1,1900,800,2000,1,2100,700,2200,1,2300,500,2400,1,2500,300,2600,1,2500,700,2600,1,2700,800,2800,1,2800,500,2900,1,2900,700,3800,1,4000,900,4500,0,4500,700,4800,1,5000,600,5300,1,5400,500,5600,1,5700,400,5800,1,5900,800,6200,1,6100,400,6200,1,6400,900,6800,0,6900,700,7300,1,7200,500,7500,1,7500,300,7700,1,7800,500,8000,1,8100,300,8200,1,8300,400,8400,1,8500,600,8600,1,8700,500,8800,1,8900,500,9000,1,9100,700,9300,1,9400,600,9700,1,9800,500,10000,1},
                            {800,850,0,1600,850,0,2500,400,180,2750,501,270,2800,800,90},
                                {3000,600,600},
                                    {9800,300},
                                        {500,1000,4000,4500,1000,6400,6800,900,10000},
                    {200,440},
                        {0, 1050, 2332, 0},
                            {1000,1000,0},
                                {1100,950,500},
                                    {1900,850},
                                        {0,100,100}};

    public int[] getCoords(int line) {
        return levelCoords[line];
    }
}