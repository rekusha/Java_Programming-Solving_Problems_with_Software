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
    public void printNames(){
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)){
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn <= 100){
                System.out.println("Name " +  rec.get(0) + 
                                   " Gender " + rec.get(1) + 
                                   " Num Born " + rec.get(2));
            }
        }
    }
    
    private void totalBirths(FileResource fr){
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        int boysName = 0;
        int girlsName = 0;
        for (CSVRecord rec : fr.getCSVParser(false)){
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if (rec.get(1).equals("M")){ 
                totalBoys += numBorn; 
                boysName += 1;
            }
            else { 
                totalGirls += numBorn; 
                girlsName += 1;
            }
        }
        System.out.println("total births = " + totalBirths);
        System.out.println("total girls = " + totalGirls);
        System.out.println("total boys = " + totalBoys);
        System.out.println("number of girls names = " + girlsName);
        System.out.println("number of boys names = " + boysName);
        System.out.println("total names in the file = " + (girlsName+boysName));
    }
    
    public void testTotalBirths(){
        FileResource fr = new  FileResource();
        totalBirths(fr);
    }
    
    private int getRank(int year, String name, String gender){
        int rank = 1;
        int nums = 0;
        String fileName = "data/yob"+year+".csv";
        FileResource fr = new FileResource(fileName);
        for (CSVRecord rec : fr.getCSVParser(false)){
            if (name.equals(rec.get(0)) && gender.equals(rec.get(1))) {
                nums = Integer.parseInt(rec.get(2));
                break;
            }
        }
        if (nums == 0){return -1;}
        for (CSVRecord rec : fr.getCSVParser(false)){
            if (nums < Integer.parseInt(rec.get(2)) && rec.get(1).equals(gender)) {
                rank += 1;
            }
        }
        return rank;
    }
    
    public void testGetRank(){
        System.out.println(getRank(1960, "Emily", "F"));
        //System.out.println(getRank(2012, "Mason", "F"));
    }
    
    private String getName(int year, int rank, String gender){
        String name = "NO NAME";
        int currentrank = 0;
        String fileName = "data/yob"+year+".csv";
        FileResource fr = new FileResource(fileName);
        for (CSVRecord rec : fr.getCSVParser(false)){
            if (gender.equals(rec.get(1))) {
                currentrank += 1;
                if (currentrank == rank){
                    name = rec.get(0);
                }
            }
        }
        
        return name;
    }
    
    public void testGetName(){
        System.out.println(getName(1980, 350, "F"));
        System.out.println(getName(1982, 450, "M"));
    }
    
    private void whatIsNameInYear(String name, int year, int newYear, String gender){
        int rank = getRank(year, name, gender);
        String name2 = getName(newYear, rank, gender);
        System.out.println(name + " born in " + year + " would be " + name2 + " if she was born in " + newYear + ".");
    }
    
    public void testWhatIsNameInYear(){
        whatIsNameInYear("Susan",1972,2014,"F");
        whatIsNameInYear("Owen",1974,2014,"M");
        //whatIsNameInYear("Owen",1974,2014,"M");
    }
    
    private int yearOfHighestRank(String name,String gender){
            int currentMaxRankYear = -1;
            int currentMaxRank = -1;
        
           DirectoryResource dr= new DirectoryResource();
           for(File f:dr.selectedFiles()){
               FileResource fr = new FileResource(f);
               CSVParser parser = fr.getCSVParser(false);
               String currentYears = f.getName().substring(3,7);
               int currentYear = Integer.parseInt(currentYears);
               int tempRank =0;
               int rank= -1;
               
               for(CSVRecord record:parser){
                   if(record.get(1).equals(gender)){
                       tempRank =tempRank+1;
                       if(record.get(0).equals(name)){
                           rank = tempRank;
                           break;
                        }
                    
                    
                    }
                
                }
                
               if(currentMaxRank== -1 && rank != -1){
                   currentMaxRank = rank;
                   currentMaxRankYear = currentYear;
                }
               
               else if(rank < currentMaxRank && rank != -1){
                   currentMaxRank = rank;
                   currentMaxRankYear = currentYear;
                }
                
               
           }
           
           return currentMaxRankYear;
    
    }
    
    public void testYearOfHighestRank(){
        //System.out.println(yearOfHighestRank("Mary", "F")); 
        System.out.println(yearOfHighestRank("Genevieve", "F"));
        System.out.println(yearOfHighestRank("Mich", "M"));
    }
    
    private double getAverageRank(String name,String gender){
       double sumRank=0.0;
       double countFiles=0.0;
       
       DirectoryResource dr= new DirectoryResource();
       for(File f:dr.selectedFiles()){
           FileResource fr = new FileResource(f);
           CSVParser parser = fr.getCSVParser(false);
           String currentYears = f.getName().substring(3,7);
           int currentYear = Integer.parseInt(currentYears);
           int tempRank =0;
           int rank= -1;
           
           for(CSVRecord record:parser){
                   if(record.get(1).equals(gender)){
                       tempRank =tempRank+1;
                       if(record.get(0).equals(name)){
                           rank = tempRank;
                           break;
                        }
                    
                    
                    }
                    
                  
           }
           
           if(rank !=-1){
                sumRank += rank; 
                    
            }
                   
           
 
           countFiles = countFiles+1;
           System.out.println("sunRank:"+sumRank);
           System.out.println("countFiles:"+countFiles);
           System.out.println(" ");
       }
       
       double averageRank = sumRank / countFiles;
       System.out.println("averageRank: "+ averageRank);
       return averageRank;
    }
    
    public void testgetAverageRank(){
        double averageRankResults = getAverageRank("Robert","M");
        System.out.println("Robert's average Rank: "+averageRankResults);
    }
    
   
    private int getTotalBirthsRankedHigher(int year,String name,String gender){
        int sumHigherNumBorn =0;
        int numBorn=0;
        int tempRank =0;
        String fileName = "data/yob"+year+ ".csv";
        FileResource fr = new FileResource(fileName);
        CSVParser parser = fr.getCSVParser(false);
        int inputRank = getRank(year,name,gender);
        
        for(CSVRecord record:parser)
        {
            if(record.get(1).equals(gender))
            {
                 tempRank +=1;
                 if(tempRank < inputRank)
                 {
                    sumHigherNumBorn += Integer.parseInt(record.get(2));
                 }
            }
            
        }
        return sumHigherNumBorn;   
       }
        
    public void testGetTotalBirthsRankedHigher(){
        int resultSumBorn = getTotalBirthsRankedHigher(1990,"Emily","F");
        System.out.println("The numbers of Higer Born than Emily is: "+ resultSumBorn);
        
        resultSumBorn = getTotalBirthsRankedHigher(1990,"Drew","M");
        System.out.println("The numbers of Higer Born than Drew is: "+ resultSumBorn);
    }
}
