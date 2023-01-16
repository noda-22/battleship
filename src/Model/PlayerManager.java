/*
  Author: Nora Darejati
  Id: aj7718
  Study program: Medieproduktion och processdesign (Valbar kurs)
*/

package Model;

import java.util.Arrays;

public class PlayerManager {
   private Player[] highscoreArray;

   public PlayerManager() {
      highscoreArray = new Player[10];
   }

   public Player[] getHighscoreArray() {
      return highscoreArray;
   }

   public void setHighscoreArray(Player[] highscoreArray) {
      this.highscoreArray = highscoreArray;
   }


   // Sortera array med Selection Sort (i stigande ordning)
   // Letar efter det minsta värdet och flyttar fram det.
   // En osorterad lista och en sorterad lista. Alla index med samma värde som variabeln eller större
   // är osorterat.
   // Börjar med det första indexet och för varje element i arrayen kollar den om det värdet är mindre än något annat.
   // -1 för att inte hamna utanför när man kollar i+1
   public Player[] sortHighscoreArray() {
      // ??
      for (int i = 0; i < highscoreArray.length - 1; i++) {
         int minIndex = i;

         for (int j = i + 1; j < highscoreArray.length; j++) {
            // Hitta det lägsta indexet i unsorted array
            if(highscoreArray[j] != null) {
               if (highscoreArray[j].getScore() < highscoreArray[minIndex].getScore()) {
                  minIndex = j;
               }
               // Swap the found minimum element with the first element
               // Byt plats på det hittade lägsta elementet med det första elementet
               Player temp = highscoreArray[minIndex];
               highscoreArray[minIndex] = highscoreArray[i];
               highscoreArray[i] = temp;
            }
         }
      }
      return highscoreArray;
   }


   // Gör om arrayen med Skepp-objekt till vanlig sträng-array
   // Returnerar en array av strängar där varje element representerar info om ett skepp, genom
   // att kalla på varje Skepp-objekts toString-metod
   public String[] getInfoStrings() {
      Player[] highscoreArraySorted = sortHighscoreArray();
      String[] infoStrings = new String[highscoreArraySorted.length];
      for (int i = 0; i < infoStrings.length; i++) {
         if(highscoreArraySorted[i] != null) {
            infoStrings[i] = highscoreArraySorted[i].toString(); //anropar Skepp-objektets toString
         }
      }
       System.out.println("infoStrings " + Arrays.toString(infoStrings));
      return infoStrings;
   }

}
