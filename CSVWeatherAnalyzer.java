/**
 * Analyzes any number of files of CSV weather data chosen by the user.
 * 
 * @author pabl0cesar
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class CSVMax {
    public CSVRecord hottestHourInFile(CSVParser parser) {
        CSVRecord largestSoFar = null;
        for (CSVRecord currentRow : parser) {
            largestSoFar = getLargestOfTwo(currentRow, largestSoFar);
        }
        return largestSoFar;
    }

    public void testHottestInDay () {
        FileResource fr = new FileResource("data/2015/weather-2015-01-01.csv");
        CSVRecord largest = hottestHourInFile(fr.getCSVParser());
        System.out.println("hottest temperature was " + largest.get("TemperatureF") +
                   " at " + largest.get("TimeEST"));
    }

    public CSVRecord hottestInManyDays() {
        CSVRecord largestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = hottestHourInFile(fr.getCSVParser());
            largestSoFar = getLargestOfTwo(currentRow, largestSoFar);
        }
        return largestSoFar;
    }

    public CSVRecord getLargestOfTwo (CSVRecord currentRow, CSVRecord largestSoFar) {
        if (largestSoFar == null) {
            largestSoFar = currentRow;
        }
        else {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
            if (currentTemp > largestTemp) {
                largestSoFar = currentRow;
            }
        }
        return largestSoFar;
    }

    public void testHottestInManyDays () {
        CSVRecord largest = hottestInManyDays();
        System.out.println("hottest temperature was " + largest.get("TemperatureF") +
                   " at " + largest.get("DateUTC"));
    }
    
    public CSVRecord coldestHourInFile(CSVParser parser) {
        //start with largestSoFar as nothing
        CSVRecord smallestSoFar = null;
        //For each row (currentRow) in the CSV File
        for (CSVRecord currentRow : parser) {
            // use method to compare two records
            smallestSoFar = getSmallestOfTwo(currentRow, smallestSoFar);
        }
        //The largestSoFar is the answer
        return smallestSoFar;
    }
    
        
    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        //start with largestSoFar as nothing
        CSVRecord smallestSoFar = null;
        //For each row (currentRow) in the CSV File
        for (CSVRecord currentRow : parser) {
            // use method to compare two records
            smallestSoFar = getSmallestOfTwo(currentRow, smallestSoFar);
        }
        //The largestSoFar is the answer
        return smallestSoFar;
    }

    public void testColdestInDay () {
        FileResource fr = new FileResource("data/2014/weather-2014-05-01.csv");
        CSVRecord largest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temperature was " + largest.get("TemperatureF") +
                   " at " + largest.get("TimeEDT"));
    }
    
    public void testLowestHumidityInDay () {
    FileResource fr = new FileResource("data/2014/weather-2014-07-22.csv");
    CSVRecord largest = lowestHumidityInFile(fr.getCSVParser());
    System.out.println("Lowest humidity was " + largest.get("Humidity") +
               " at " + largest.get("DateUTC"));
    }

    public CSVRecord coldestInManyDays() {
        CSVRecord smallestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        // iterate over files
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            // use method to get largest in file.
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            // use method to compare two records
            smallestSoFar = getSmallestOfTwo(currentRow, smallestSoFar);
        }
        //The largestSoFar is the answer
        return smallestSoFar;
    }
    
    
    public CSVRecord lowestHumidityInManyDays() {
        CSVRecord smallestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            smallestSoFar = getSmallestOfTwo(currentRow, smallestSoFar);
        }
        return smallestSoFar;
    }

    public CSVRecord getSmallestOfTwo (CSVRecord currentRow, CSVRecord largestSoFar) {
        if (largestSoFar == null) {
            largestSoFar = currentRow;
        }
        //Otherwise
        else {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
            if (currentTemp < largestTemp) {
                largestSoFar = currentRow;
            }
        }
        return largestSoFar;
    }
    
    public double getAvgTemp (){
        FileResource fr = new FileResource("data/2014/weather-2014-08-10.csv");
        int count = 0;
        double total = 0;
		
        for(CSVRecord record: fr.getCSVParser()){
            count+=1;
            total+=Double.parseDouble(record.get("TemperatureF"));
         }
    
        return total/count;}
        
    public double getAvgTempHumidity (){
        FileResource fr = new FileResource("data/2014/weather-2014-09-02.csv");
        int count = 0;
        double total = 0;
        for(CSVRecord record: fr.getCSVParser()){
            if(Double.parseDouble(record.get("Humidity")) >= 80.0){
            count+=1;
            total+=Double.parseDouble(record.get("TemperatureF"));}
         }
        
        return (total/count);}

    public void testColdestInManyDays () {
        CSVRecord largest = coldestInManyDays();
        System.out.println("Coldest temperature was " + largest.get("TemperatureF") +
                   " at " + largest.get("DateUTC"));
    }
    
    public void testLowestHumidityInManyDays () {
        CSVRecord largest = lowestHumidityInManyDays();
        System.out.println("Lowest humidity was " + largest.get("Humidity") +
                   " at " + largest.get("DateUTC"));
    }
    
    public void testAvgTemp(){
        System.out.println("The avg. temp. for that day was: "+getAvgTemp());
    }
    
    public void testAvgTempHum(){
        System.out.println("the avg. temp. in Fahrenheit when hum. were >= 80 is: "+getAvgTempHumidity());
    }
}
