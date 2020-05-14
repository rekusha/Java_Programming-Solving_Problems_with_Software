import edu.duke.*; 
/**
 * Write a description of class Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class Part4 {
    public void StringsFirstAssignments(){
        URLResource resource = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        for (String s : resource.lines()){
            if (s.toLowerCase().indexOf("youtube.com") != -1){
                int start = s.indexOf("=\"");
                int stop = s.indexOf("\"", start+2);
                System.out.println(s.substring(start+2, stop));
            }
        }
    }
}
