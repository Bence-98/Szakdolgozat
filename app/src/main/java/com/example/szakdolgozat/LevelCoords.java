package com.example.szakdolgozat;

public class LevelCoords {
    //Player        starting point x,y
    //Platforms     firstleft top lastleft float
    //Obstacles     left top rotate
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
                    {200,840},
                        {0,900,400,0,600,900,800,1,900,700,1200,1,1500,900,1700,1,1900,800,1900,1,2100,700,2100,1,2300,500,2300,1,2500,300,2500,1,2500,700,2500,1,2700,800,2700,1,2800,500,2800,1,2900,700,3700,1,4000,900,4400,0,4500,700,4700,1,5000,600,5200,1,5400,500,5500,1,5700,400,5700,1,5900,800,6100,1,6100,400,6100,1,6400,900,6700,0,6900,700,7200,1,7200,500,7400,1,7500,300,7600,1,7800,500,7900,1,8100,300,8100,1,8300,400,8300,1,8500,600,8500,1,8700,500,8700,1,8900,500,8900,1,9100,700,9200,1,9400,600,9600,1,9800,500,9900,1},
                            {800,950,0,1600,850,0,2500,400,180,2750,500,270,2800,800,90},
                                {1100,950,1200,1050,500},
                                    {1900,850},
                                        {}};

    public int[] getCoords(int line) {
        return levelCoords[line];
    }
}