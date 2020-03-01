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

    public static void main(String[] RTL) throws Exception
    {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = workbook.createSheet( " Employee Info ");
        XSSFRow row;

        Map< String, Object[] > decisionInfo = new TreeMap< String, Object[] >();
        decisionInfo.put( "1", new Object[] {
                "Time", "Width", "aP","aN","Distance","Velocity","Decision" });
        int i = 2;
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
                                decisionInfo.put(String.valueOf(i),new Object[]{t,width,ap,an,dist,v,decision(car,road)});
                                i++;
                            }
                        }
                    }
                }
            }
        }

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
                new File("Writesheet.xlsx"));

        workbook.write(out);
        out.close();




    }
}
