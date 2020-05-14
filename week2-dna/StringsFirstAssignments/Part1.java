
/**
 * Write a description of class Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public String findSimpleGene (String dna){
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1) { return ""; }
        int stopTaa = dna.indexOf("TAA", startIndex);
        int stopTag = dna.indexOf("TAG", startIndex);
        int stopTga = dna.indexOf("TGA", startIndex);
        int temp;
        int stopIndex;
        temp = Math.min(stopTaa, stopTag) == -1 ? Math.max(stopTaa, stopTag) : Math.min(stopTaa, stopTag);
        stopIndex = Math.min(temp, stopTga) == -1 ? Math.max(temp, stopTga) : Math.min(temp, stopTga);
        if (stopIndex == -1) { return ""; }
        if ((stopIndex - startIndex) % 3 == 0) { 
            return dna.substring(startIndex, stopIndex+3); 
        }
        return "";
    }
   
    public void testSimpleGene () {
        String a = "TGGGGTTTAAATAA";
        String result = findSimpleGene(a);
        System.out.println("a1: " + result);
        a = "ATGGGGTTTTAGTAATGA";
        result = findSimpleGene(a);
        System.out.println("a2: " + result);
        a = "ATGGGGTTTTAATGATAG";
        result = findSimpleGene(a);
        System.out.println("a3: " + result);
        a = "ATGGGGTTTTGATAATAG";
        result = findSimpleGene(a);
        System.out.println("a4: " + result);
    }
}
