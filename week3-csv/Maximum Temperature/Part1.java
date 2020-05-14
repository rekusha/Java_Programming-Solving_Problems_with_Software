import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

/**
 * Write a description of class Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    private CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord coldestTemp =  null;
        for (CSVRecord currentTemp : parser){
            if (coldestTemp == null) {
                coldestTemp = currentTemp;
            } else {
                double current = Double.parseDouble(currentTemp.get("TemperatureF"));
                double coldest = Double.parseDouble(coldestTemp.get("TemperatureF"));
                if (current < coldest) {
                    coldestTemp = currentTemp;
                }
            }
        }
        return coldestTemp;
    }
    
    public void testColdestHourInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord record = coldestHourInFile(parser);
        System.out.println(record.get("DateUTC")+": "+record.get("TemperatureF"));
    }
    
    private String fileWithColdestTemperature(){
        DirectoryResource dr = new DirectoryResource();
        CSVRecord coldestTemp = null;
        File coldestFile = null;
        String res = null;
        String tempDynamic = "\nAll the Temperatures on the coldest day were:";
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            for (CSVRecord currentTemp : parser){
                if (coldestTemp == null){
                    coldestTemp = coldestHourInFile(parser);
                    coldestFile = f;
                } else {
                    double current = Double.parseDouble(currentTemp.get("TemperatureF"));
                    if (current == -9999){
                        continue;
                    }
                    double coldest = Double.parseDouble(coldestTemp.get("TemperatureF"));
                    if (current < coldest) {
                        coldestTemp = currentTemp;
                        coldestFile = f;
                    }
                }
            }
        }
        
        for (CSVRecord currentTemp : new FileResource(coldestFile).getCSVParser()){
            tempDynamic += "\n" + currentTemp.get("DateUTC")+": "+currentTemp.get("TemperatureF");
        }
        return "Coldest day was in file " + coldestFile.getName() + "\n" + "Coldest temperature on that day was " + coldestTemp.get("TemperatureF") + tempDynamic;
    }
    
    public void testFileWithColdestTemperature(){
        System.out.println(fileWithColdestTemperature());
    }
    
    private CSVRecord lowestHumidityInFile (CSVParser parser){
        CSVRecord lowestHumidity =  null;
        for (CSVRecord currentHumidity : parser){
            try {
                double current = Double.parseDouble(currentHumidity.get("Humidity"));
            } catch (Exception e){ 
                continue; 
            }
            
            if (lowestHumidity == null) {
                lowestHumidity = currentHumidity;
            } else {
                double current = Double.parseDouble(currentHumidity.get("Humidity"));
                double coldest = Double.parseDouble(lowestHumidity.get("Humidity"));
                if (current < coldest) {
                    lowestHumidity = currentHumidity;
                }
            }
        }
        return lowestHumidity;
    }
    
    public void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
    }
    
    private CSVRecord lowestHumidityInManyFiles(){
        DirectoryResource dr = new DirectoryResource();
        CSVRecord lowestHumidity = null;
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            for (CSVRecord currentHumidity : parser){
                if (currentHumidity.get("Humidity").equals("N/A")){ 
                    continue; 
                }
                if (lowestHumidity == null){
                    lowestHumidity = currentHumidity;
                } else {
                    double current = Double.parseDouble(currentHumidity.get("Humidity"));
                    double coldest = Double.parseDouble(lowestHumidity.get("Humidity"));
                    if (current < coldest) {
                        lowestHumidity = currentHumidity;
                    }
                }
            }
        }
        return lowestHumidity;
    }
    
    public void testLowestHumidityInManyFiles(){
        CSVRecord res = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + res.get("Humidity") + " at " + res.get("DateUTC"));
    }
    
    private Double averageTemperatureInFile(CSVParser parser){
        double TSum = 0;
        double TCount = 0;
        for (CSVRecord record : parser){
            double temp = Double.parseDouble(record.get("TemperatureF"));
            if (temp == -9999){
                continue;
            }
            TCount += 1;
            TSum += temp;
        }
        return TSum/TCount;
    } 
    
    public void testAverageTemperatureInFile(){
        CSVParser parser = (new FileResource()).getCSVParser();
        System.out.println("Average temperature in file is " + averageTemperatureInFile(parser));
    }
    
    private Double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
        double sum = 0;
        double number = 0;
        double humidity = 0;
        for (CSVRecord record: parser) {
            if (record.get("Humidity").equals("N/A")) {
                humidity = -999;
            }
            else {
                humidity = Double.parseDouble(record.get("Humidity"));
            }
            
            if (humidity >= value) {
                number = number + 1;
                sum = sum + Double.parseDouble(record.get("TemperatureF"));
            }
        }
       
        return sum/number;
    }
    
    public void testAverageTemperatureWithHighHumidityInFile() {
        double average = averageTemperatureWithHighHumidityInFile((new FileResource()).getCSVParser(), 80);
        if (Double.isNaN(average)) {
            System.out.println("No temperatures with that humidity");
        }
        else {
            System.out.println("Average temperature with high Humidity is " + average);
        }
    }
}
