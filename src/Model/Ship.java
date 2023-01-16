/*
  Author: Nora Darejati
  Id: aj7718
  Study program: Medieproduktion och processdesign (Valbar kurs)
*/

package Model;

public class Ship {
   private String id;
   private int antalRutor;

   public Ship(int antalRutor, String id) {
      this.antalRutor = antalRutor;
      this.id = id;
   }

   public int getAntalRutor() {
      return antalRutor;
   }

   public void setAntalRutor(int antalRutor) {
      this.antalRutor = antalRutor;
   }

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }
}
