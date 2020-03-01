import java.io.File;
import java.io.FileOutputStream;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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

    // Point 6 for 2 cars
    public static String decisionFor2cars(Car car1, Car car2, Road road)
    {
        double positionCar1;
        double positionCar2;
        double distanceCar1 = car1.initialDistance;
        double distanceCar2 = car2.initialDistance;
        double d = distanceCar2-distanceCar1;
        double width = road.intersectionWidth;
        double vCar1 = car1.initialSpeed;
        double vCar2 = car2.initialSpeed;
        double apCar1 = car1.aPositive;
        double apCar2 = car2.aPositive;
        double anCar1 = car1.aNegative;
        double anCar2 = car2.aNegative;
        double t = road.durationOfYellowLight;

        positionCar1 = vCar1*t +apCar1*Math.pow(t,2);
        positionCar2 = vCar2*t + apCar2*Math.pow(t,2);
        if(!decision(car1,road).equals("Stop" ))
        {
            if(positionCar2+d>positionCar1)
                return  "Car 1 Run, Car 2 Stop.";
            else return "Car 1 Run, Car 2 " + decision(car2,road);
        }
        else if(positionCar2+d>positionCar1)
            return "Car 1 Stop, Car 2 Stop.";
        else return "Car 1 Stop, Car 2 Run.";
    }

    public static void main(String[] RTL) throws Exception
    {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = workbook.createSheet( " Info ");
        XSSFRow row;
        // for point 3
        Map< String, Object[] > decisionInfo = new TreeMap< String, Object[] >();
        decisionInfo.put( "1", new Object[] {
                "Time", "Width", "aP","aN","Distance","Velocity","Decision" });

        // for the point 5 with 50 km/h <= v <=100 km/h
        Map< String, Object[] > decisionInfo2 = new TreeMap< String, Object[] >();
        decisionInfo2.put( "1", new Object[] {
                "Time", "Width", "aP","aN","Distance","Velocity","Decision" });
        int i = 2,j=2;


        // Point 3 experiment results
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

                               // System.out.println("t="+t+",width="+width+",ap="+ap+",an="+an+",dist="+dist+",v="+v);
                                Car car = new Car(v,dist,ap,an);
                                Road road = new Road(t,width);
                               // System.out.println(decision(car,road));
                                decisionInfo.put(String.valueOf(i),new Object[]{t,width,ap,an,dist,v,decision(car,road)});
                                i++;
                            }
                        }
                    }
                }
            }
        }
        // Point 5 experiment results

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
                            for(double v = 14;v<=27;v+=2)
                            {

                                //System.out.println("t="+t+",width="+width+",ap="+ap+",an="+an+",dist="+dist+",v="+v);
                                Car car = new Car(v,dist,ap,an);
                                Road road = new Road(t,width);
                                //System.out.println(decision(car,road));
                                decisionInfo2.put(String.valueOf(j),new Object[]{t,width,ap,an,dist,v,decision(car,road)});
                                j++;
                            }
                        }
                    }
                }
            }
        }

        // Point 3 experiment results to excel file
        Set< String > keyid = decisionInfo.keySet();
        int rowid = 0;
        for (String key : keyid) {
            row = spreadsheet.createRow(rowid++);
            Object [] objectArr = decisionInfo.get(key);
            int cellid = 0;

            for (Object obj : objectArr){
                Cell cell = row.createCell(cellid++);
                cell.setCellValue(String.valueOf(obj));
            }
        }
        FileOutputStream out = new FileOutputStream(
                new File("Point3.xlsx"));

        workbook.write(out);
        out.close();

        // Point 5 experiment results to excel file
        XSSFWorkbook workbook2 = new XSSFWorkbook();
        XSSFSheet spreadsheet2 = workbook2.createSheet( " Info ");
        XSSFRow row2;
        keyid = decisionInfo2.keySet();
        rowid = 0;
        for (String key : keyid) {
            row = spreadsheet2.createRow(rowid++);
            Object [] objectArr2 = decisionInfo2.get(key);
            int cellid = 0;

            for (Object obj : objectArr2){
                Cell cell = row.createCell(cellid++);
                cell.setCellValue(String.valueOf(obj));
            }
        }
        out = new FileOutputStream(
                new File("Point4.xlsx"));

        workbook2.write(out);
        out.close();


        // Point 6 trial
        Car car1 = new Car(22,50,2,2);
        Car car2 = new Car(18,60,2,2);
        Road road = new Road(5,10);
        System.out.println(decisionFor2cars(car1,car2,road));

        car1 = new Car(20,50,2,2);
        car2 = new Car(22,60,2,2);
        System.out.println(decisionFor2cars(car1,car2,road));

        car1 = new Car(5,100,2,2);
        car2 = new Car(22,150,2,2);
        System.out.println(decisionFor2cars(car1,car2,road));
    }
}
