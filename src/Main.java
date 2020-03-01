
public class Main {


    public static String decision(Car car,Road road)
    {
        double distanceIfRun;
        double distanceIfStop;
        double v = car.initialSpeed;
        double t = road.durationOfYellowLight;
        double ap = car.aPositive;
        double an = car.aNegative;
        double distance = car.initialDistance + road.intersectionWidth;
        distanceIfRun = v*t + ap*Math.pow(t,2);
        distanceIfStop = v*t - an*Math.pow(t,2);
        if ( distanceIfRun - distance >=0 && distanceIfStop - distance >=0)
            return "Can both run or stop";
        else if(distanceIfRun - distance >=0)
            return "Run";
        else return "Stop";
    }

    public static void main(String[] RTL) throws Exception
    {

        for(int t=2;t<=5;t++)
        {
            for(int width = 5;width<=20;width+=5)
            {
                for(int ap = 1;ap<=3;ap++)
                {
                    for(int an = 1;an<=3;an++)
                    {
                        for(int dist = 10;dist<=150;dist+=20)
                        {
                            for(double v = 6;v<=22;v+=2)
                            {


                                System.out.println("t="+t+",width="+width+",ap="+ap+",an="+an+",dist="+dist+",v="+v);
                                Car car = new Car(v,dist,ap,an);
                                Road road = new Road(t,width);
                                System.out.println(decision(car,road));
                            }
                        }
                    }
                }
            }
        }

    }
}
