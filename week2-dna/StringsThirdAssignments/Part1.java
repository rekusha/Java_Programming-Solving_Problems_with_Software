import edu.duke.*;
/**
 * Write a description of class Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
  //String dna = "atggatcctccatatacaacggtatctccacctcaggtttagatctcaacaacggaaccattgccgacatgagacagttaggtatcgtcgagagttacaagctaaaacgagcagtagtcagctctgcatctgaagccgctgaagttctactaagggtggataacatcatccgtg";
  public int findStopCodon(String dnaStr, int startIndex, String stopCodon){
    int currentIndex = dnaStr.indexOf(stopCodon, startIndex+3);
    while (currentIndex != -1){
      if ((currentIndex - startIndex) % 3 == 0){
          return currentIndex;
        }
        else {
          currentIndex = dnaStr.indexOf(stopCodon, currentIndex+1);
        }
    }
    return -1;
  }
  
  //public void testFindStopCodon (){
  //  int startIndex = 0;
  //  System.out.println(findStopCodon(dna, startIndex, "taa"));
  //  System.out.println(findStopCodon(dna, startIndex, "tga"));
  //  System.out.println(findStopCodon(dna, startIndex, "tag"));
  //  System.out.println(findStopCodon(dna, startIndex, "txg"));
  //  System.out.println(findGene(dna));
  //}
  
  public String findGene(String dna){
    int startIndex = dna.indexOf("atg");
    if (startIndex == -1){ return ""; }
    int taaIndex = findStopCodon(dna, startIndex, "taa");
    int tgaIndex = findStopCodon(dna, startIndex, "tga");
    int tagIndex = findStopCodon(dna, startIndex, "tag");
    int minIndex = 0;
    if (taaIndex == -1 || (tgaIndex != -1 && tgaIndex < taaIndex)){
      minIndex = tgaIndex;
    } else {
      minIndex = taaIndex;
    }
    
    if (minIndex == -1 || (tagIndex != -1 && tagIndex < minIndex)){
      minIndex = tagIndex;
    }
    
    if (minIndex == -1){ return ""; }
    
    return dna.substring(startIndex, minIndex+3);
  }
  
  public void testFindGene(){
    String dna1 = "aatgcgtaattaatcg";
    String dna2 = "cgatggttgataagcctaagctataa";
    String dna3 = "cgatggttgataagcctaagctaaa";
    String dna4 = "atggatcct";
    String dna5 = "atggatcctccatatacaacggtatctccacctcaggtttagatctcaacaacggaaccattgca";
    System.out.println(findGene(dna1));
    System.out.println(findGene(dna2));
    System.out.println(findGene(dna3));
    System.out.println(findGene(dna4));
    System.out.println(findGene(dna5));
  }
  
  private void printAllGenes(String dna){
    int startPos = 0;
    while (true){
      String gene = findGene(dna.substring(startPos));
      if (gene.isEmpty()) { break; }
      System.out.print(">" + startPos + ":" + gene + ":" + (startPos + gene.length())+"<");
      startPos = dna.indexOf(gene, startPos) + gene.length();
    }
  }
  
  private StorageResource getAllGenes(String dna){
    StorageResource sr = new StorageResource();
    int startPos = 0;
    while (true){
      String gene = findGene(dna.substring(startPos));
      if (gene.isEmpty()) { break; }
      sr.add(gene);
      startPos = dna.indexOf(gene, startPos) + gene.length();
    }
    return sr;
  }
  
  private float cgRatio(String dna){
      String gene = findGene(dna);
      int geneLen = gene.length();
      float CGLen = geneLen - gene.replace("c", "").replace("g", "").length();
    return CGLen/geneLen;
  }
  
  private int countCTG(String dna){
      int count = 0;
      int currentPos = 0;
    while (true){
      currentPos = dna.indexOf("ctg", currentPos);
      if (currentPos != -1){
        count += 1;
      } else {break;}
      currentPos += 1;
    }
    return count;  
  }
  
  private void processGenes(StorageResource sr){
    int count = 0;
    int countCG = 0;
    int longestDNA = 0;
    for (String x : sr.data()) {
      if (x.length() > 60){
          count += 1;
      }
      if (findGene(x).length() > longestDNA){
        longestDNA = findGene(x).length();
      }
    }
    
    System.out.println("count longest 60: " + count);
    for (String x : sr.data()) {
      if (cgRatio(x) > 0.35){
          countCG += 1;
      }
    }
    System.out.println("countCG ratio " + countCG);
    System.out.println("longestDNA " + longestDNA);
  }
  
  public void testProcessGenes(){
    URLResource ur = new URLResource("https://www.cs.duke.edu/~rodger/GRch38dnapart.fa");
    FileResource fr = new FileResource("GRch38dnapart.fa");
    //String dna = fr.asString().toLowerCase();
    String dna = ur.asString().toLowerCase();
    StorageResource allgene = getAllGenes(dna);
    System.out.println("TOTAL GENES: " + allgene.size());
    processGenes(allgene);
    System.out.println("countCTG " + countCTG(dna));
    //printAllGenes(dna);
  }
}
