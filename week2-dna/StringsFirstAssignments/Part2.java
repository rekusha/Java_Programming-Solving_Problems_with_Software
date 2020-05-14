
/**
 * Write a description of class Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public String findSimpleGene (String dna, String startCodon, String stopCodon){
        char value1 = dna.toCharArray()[0];
        boolean check1 = Character.isLowerCase(value1);
        System.out.println("check1 : " + check1);
        dna=dna.toUpperCase();
        startCodon=startCodon.toUpperCase();
        stopCodon=stopCodon.toUpperCase();
        int startIndex = dna.indexOf(startCodon);
        if (startIndex == -1) { return ""; }
        int stopIndex = dna.indexOf(stopCodon, startIndex);
        if (stopIndex == -1) { return ""; }
        if ((stopIndex - (startIndex+startCodon.length())) % 3 == 0) { 
            if (check1==true){
                dna = dna.toLowerCase();
                stopCodon=stopCodon.toLowerCase();
                startCodon=startCodon.toLowerCase();
            }
        return dna.substring(startIndex, stopIndex)+stopCodon;
}
return "";
}
    
    public void testSimpleGene () {
        String a = "TGGGGTTTAAaTAA";
        String result = findSimpleGene(a, "ATG", "TAA");
        System.out.println("a1: " + result);
        a = "ATGGGGTTTATA";
        result = findSimpleGene(a, "AtG", "TAA");
        System.out.println("a2: " + result);
        a = "aTGgGGtTTTAA";
        result = findSimpleGene(a, "ATG", "TAA");
        System.out.println("a3: " + result);
        a = "ATGGGGTTTATAA";
        result = findSimpleGene(a, "ATG", "TAA");
        System.out.println("a4: " + result);
    }
}
