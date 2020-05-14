
/**
 * Write a description of class Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
  public int howMany(String stringa, String stringb){
      int currentIndex = stringb.indexOf(stringa);
      if (currentIndex == -1) {return 0;}
      int counter = 1;
      currentIndex += stringa.length();
      while (currentIndex != -1){
        currentIndex = stringb.indexOf(stringa, currentIndex);
        if (currentIndex == -1) {return counter;}
        counter += 1;
        currentIndex = currentIndex + stringa.length();
      }
    return counter;
  }
  
  public void testHowMany(){
    System.out.println(howMany("GAA", "ATGAACGAATTGAATC"));
    System.out.println(howMany("AA", "ATAAAA"));
  }
}
