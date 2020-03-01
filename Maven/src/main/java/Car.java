public class Car {

    public double initialSpeed;
    public double initialDistance;
    public double aPositive;
    public double aNegative;

    public static double initialSpeedMin = 20*1000/3600; // 5.(5) m/s
    public static double initialSpeedMax = 80*1000/3600; // 22.(2) m/s
    public static double initialSpeedMin2 = 50*1000/3600; // 13.(8) m/s
    public static double initialSpeedMax2 = 100*1000/3600; // 27.(7) m/s

    public static double initialDistanceMin = 10; // m
    public static double initialDistanceMax = 150; // m

    public static double aMin = 1; // m/s^2
    public static double aMax = 3; // m/s^2


    public Car(double speed,double dist,double aPos, double aNeg)
    {
        if(speed<= initialSpeedMax && speed>=initialSpeedMin)
            this.initialSpeed = speed;
        else {
            System.out.println("Please input correct value");
            return;
        }
        if(dist <= initialDistanceMax && dist>=initialDistanceMin)
            this.initialDistance = dist;
        else {
            System.out.println("Please input correct value");
            return;
        }
        if (aPos <= aMax && aPos>= aMin)
            this.aPositive = aPos;
        else {
            System.out.println("Please input correct value");
            return;
        }
        if (aNeg <= aMax && aNeg>= aMin)
            this.aNegative = aNeg;
        else {
            System.out.println("Please input correct value");
        }

    }




}
