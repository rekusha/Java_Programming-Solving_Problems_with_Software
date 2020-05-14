
/**
 * Write a description of class Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public boolean twoOccurrences(String stringa, String stringb){
        String temp = stringb.replace(stringa, "");
        if ((stringb.length()-temp.length())/stringa.length()>=2){
            return true;
        }
    return false;
    }
    
    public void testing (){
        String stringa = "a";
        String stringb = "banana";
        boolean x = twoOccurrences(stringa, stringb);
        System.out.println(stringa + " in " + stringb + " is " + x);

        stringa = "by";
        stringb = "A story by Abby Long";
        x = twoOccurrences(stringa, stringb);
        System.out.println(stringa + " in " + stringb + " is " + x);
        
        stringa = "atg";
        stringb = "ctgtatgta";
        x = twoOccurrences(stringa, stringb);
        System.out.println(stringa + " in " + stringb + " is " + x);
    }
    
    public String lastPart(String stringa, String stringb){
        String temp = stringb.replace(stringa, "");
        if ((stringb.length()-temp.length())/stringa.length()==0){
            return stringb;
        }
    return stringb.substring(stringb.indexOf(stringa)+stringa.length());
    }
    
    public void testing2 (){
        String stringa = "an";
        String stringb = "banana";
        String x = lastPart(stringa, stringb);
        System.out.println(stringa + " in " + stringb + " = " + x);
        
        stringa = "zoo";
        stringb = "forest";
        x = lastPart(stringa, stringb);
        System.out.println(stringa + " in " + stringb + " = " + x);
    }
}
