public class Road {

    public double durationOfYellowLight;
    public double intersectionWidth;

    public static double durationOfYellowLightMin = 2; // s
    public static double durationOfYellowLightMax = 5; //s
    public static double widthMin = 5; // m
    public static double widthMax = 20; // m

    public Road(double yellowLight, double width)
    {
        if(yellowLight<=durationOfYellowLightMax && yellowLight>= durationOfYellowLightMin)
            this.durationOfYellowLight = yellowLight;
        else {
            System.out.println("Please input correct value");
            return;
        }
        if(width<= widthMax && width>= widthMin)
            this.intersectionWidth = width;
        else {
            System.out.println("Please input correct value");
        }
    }
}
