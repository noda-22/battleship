/*
  Author: Nora Darejati
  Id: aj7718
  Study program: Medieproduktion och processdesign (Valbar kurs)
*/

package Model;

public class Player {
   private String name;
   private int score;

   public Player(String name, int score) {
      this.name = name;
      this.score = score;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public int getScore() {
      return score;
   }

   public void setScore(int score) {
      this.score = score;
   }

   public String toString() {
      String strOut = String.format("Namn: %-6s Antal skott: %d", name, score);
      return strOut;
   }
}
