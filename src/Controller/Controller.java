/*
  Author: Nora Darejati
  Id: aj7718
  Study program: Medieproduktion och processdesign (Valbar kurs)
*/

package Controller;

import Model.*;
import Model.Ship;
import View.MainFrame;
import java.util.Arrays;

public class Controller {
   private MainFrame view;
   private PlayerManager playerManager;
   private Ship[][] gameBoardArray1 = new Ship[10][10];
   private Ship[][] gameBoardArray2 = new Ship[10][10];
   private Ship[][] gameBoard;
   private Ubat ubat;
   private Torpedbat torped;
   private Jagare jagare;
   private Kryssare kryssare;
   private Slagskepp slagskepp;
   private Ship currentPosition;

   private int totalAntalHits = 0; //antalet möjliga träffar på spelplanen
   private int spelaresHits = 0; //antalet träffar i skepp av spelaren
   private int antalSkott = 0; //totala antalet skott (både missar och träffar)
   private int nbrOfPlayersOnHS = 0;

   private int ubatCounter;
   private int torpedCounter;
   private int jagareCounter;
   private int kryssareCounter;
   private int slagskeppCounter;


   // Konstruktor
   // Skapar en instans av MainFrame, PlayerManager, och de olika skeppen.
   // Fyller spelplanerna med skeppen, anger vilken spelplan som ska användas
   // Räknar det totala antalet möjliga träffar på spelplanen
   public Controller() {
      view = new MainFrame(1200, 700, this);
      playerManager = new PlayerManager();

      ubat = new Ubat();
      torped = new Torpedbat();
      jagare = new Jagare();
      kryssare = new Kryssare();
      slagskepp = new Slagskepp();

      // Placerar ut skepp på 2 olika spelplaner + ange vilken plan som ska spelas på
      fillGameBoards();
      gameBoard = gameBoardArray2;

      // Hämta antal rutor för varje skepp
      ubatCounter = ubat.getAntalRutor();
      torpedCounter = torped.getAntalRutor();
      jagareCounter = jagare.getAntalRutor();
      kryssareCounter = kryssare.getAntalRutor();
      slagskeppCounter = slagskepp.getAntalRutor();

      // Kollar vad det totala antalet möjliga träffar blir
      for (int i = 0; i < gameBoard.length; i++) {
         for (int j = 0; j < gameBoard[i].length; j++) {
            if(gameBoard[i][j] != null) {
               totalAntalHits+=1;
            }
         }
      }
   }

   // Anropas varje gång en spelare klickar på en ruta - anropas från LPanel för att skriva ut om det var en träff eller ej
   // Kollar vilken position användaren valt -> om skepp på positionen lägg till
   // +1 på totalt antal träffar och ge "text" värdet av skeppets id (en sträng som representerar namnet)
   // Om platsen på spelplanen är null (alltså inget skepp) blir "text" ett streck.
   // @param row anger raden för den valda positionen/knappen
   // @param col anger kolumnen för den valda positionen/knappen
   public void checkPosition(int row, int col) {
      antalSkott+=1; //totalt antal slag (träffar och missar)
      view.updateAntalSkott("Antal skott: " + antalSkott);

      currentPosition = gameBoard[row][col];
      // Om träff på en skepp - plussa räknaren och returnera "id:et" för skeppet (en bokstav)
      String text;
      if (gameBoard[row][col] != null) {
         spelaresHits+=1;
         text = gameBoard[row][col].getId();
      } else {
         text = "-";
      }
      view.updateButton(row, col, text);
   }

   // Anropas varje gång en spelare klickar på en ruta
   // Kollar om currentPosition (den träffade rutan) innehåller skepp eller ej
   // Om skepp - hämta antalet rutor för det träffade skeppet och beroende på vilken typ räkna ner counter.
   // När counter når 0 -> ett skepp är sänkt - meddela spelaren.
   public void checkNumberOfHits() {
      String namn;

      if (currentPosition != null) {
         // hämtar antalet rutor för skeppet som är träffat
         int storlekTraffatSkepp = currentPosition.getAntalRutor();

         if(ubat.getAntalRutor() == storlekTraffatSkepp) {
            ubatCounter--;
            if(ubatCounter == 0) {
               view.showMessage("Ubåt sänkt!");
            }
         } else if(torped.getAntalRutor() == storlekTraffatSkepp) {
            torpedCounter--;
            if(torpedCounter == 0) {
               view.showMessage("Torpedbåt sänkt!");
            }
         } else if(jagare.getAntalRutor() == storlekTraffatSkepp) {
            jagareCounter--;
            if(jagareCounter == 0) {
               view.showMessage("Jagare sänkt!");
            }
         } else if(kryssare.getAntalRutor() == storlekTraffatSkepp) {
            kryssareCounter--;
            if(kryssareCounter == 0) {
               view.showMessage("Kryssare sänkt!");
            }
         } else if(slagskepp.getAntalRutor() == storlekTraffatSkepp)  {
            slagskeppCounter--;
            if(slagskeppCounter == 0) {
               view.showMessage("Slagskepp sänkt!");
            }
         }
      }

      // När alla skepp är sänkta
      if(spelaresHits == totalAntalHits) {
         view.showMessage("Du har sänkt alla skepp!");

         Player[] playersOnHSList = playerManager.getHighscoreArray(); //sorterad array i stigande ordning
//         System.out.println(Arrays.toString(playersOnHSList));

         // Om array har tomma platser - lägg till spelaren direkt (behöver inte jämföra värden)
         for(int i = 0; i < playersOnHSList.length; i++) {
            //om ledig plats i highscore-lista
            if (playersOnHSList[i] == null) {
               namn = view.inputDialog("Nytt highscore! Ange ditt namn");
               Player player = new Player(namn, antalSkott);
               playersOnHSList[i] = player;
               nbrOfPlayersOnHS++;
               break;
            }
            //om HS-lista är full - kolla om poäng kvalar in på listan och ersätt högsta HS med det nya
            else if(nbrOfPlayersOnHS == playersOnHSList.length) {
               int highest = findHighestHSIndex();
               if(antalSkott < playersOnHSList[highest].getScore()) {
                  namn = view.inputDialog("Nytt highscore! Ange ditt namn");
                  Player player = new Player(namn, antalSkott);
                  playersOnHSList[highest] = player;
                  break;
               }
            }
         }
         view.updateHighscore(playerManager.getInfoStrings());
      }
   }

   // Hittar och returnerar det hösta värdet i HS-arrayen
   public int findHighestHSIndex() {
      Player[] playersOnHSList = playerManager.getHighscoreArray();
      int indexOfHighest = 0;

      for(int i = 0; i < playersOnHSList.length; i++) {
         if (playersOnHSList[i].getScore() > playersOnHSList[indexOfHighest].getScore()) {
            indexOfHighest = i;
         }
      }
      return indexOfHighest;
   }

   // Funktion för att lacera ut skepp vertikalt på spelplan
   // @param rowStart är raden skeppet ska placeras ut på
   // @param colStart är kolumnen skeppet ska placeras ut på
   // @param ship är skepp-objektet som ska placeras ut
   public void placeraShipVertikalt(Ship[][] gameBoard, int rowStart, int colStart, Ship ship) {
      for (int i = 0; i < ship.getAntalRutor(); i++) {
         gameBoard[rowStart][colStart] = ship;
         rowStart++;
      }
   }

   // Funktion för att placera ut skepp horisontellt på spelplan
   // @param rowStart är raden skeppet ska placeras ut på
   // @param colStart är kolumnen skeppet ska placeras ut på
   // @param ship är skepp-objektet som ska placeras ut
   public void placeraShipHoristonellt(Ship[][] gameBoard, int rowStart, int colStart, Ship ship) {
      for (int i = 0; i < ship.getAntalRutor(); i++) {
         gameBoard[rowStart][colStart] = ship;
         colStart++;
      }
   }

   // Skeppen placeras ut
   public void fillGameBoards() {
      placeraShipHoristonellt(gameBoardArray1, 9, 9, ubat);
      placeraShipHoristonellt(gameBoardArray1, 6, 0, torped);
      placeraShipHoristonellt(gameBoardArray1, 2, 5, jagare);
      placeraShipVertikalt(gameBoardArray1, 5, 4, kryssare);
      placeraShipHoristonellt(gameBoardArray1, 0, 5, slagskepp);

      placeraShipHoristonellt(gameBoardArray2, 0, 8, ubat);
      placeraShipHoristonellt(gameBoardArray2, 2, 3, torped);
      placeraShipHoristonellt(gameBoardArray2, 9, 4, jagare);
      placeraShipHoristonellt(gameBoardArray2, 5, 5, kryssare);
      placeraShipVertikalt(gameBoardArray2, 0, 0, slagskepp);
   }

   // Nollställer värden på variabler (counters, spelarens resultat) samt reset:ar antal skott och knapparna i GUI
   public void reset() {
      spelaresHits = 0;
      antalSkott = 0;
      jagareCounter = jagare.getAntalRutor();
      ubatCounter = ubat.getAntalRutor();
      kryssareCounter = kryssare.getAntalRutor();
      slagskeppCounter = slagskepp.getAntalRutor();
      torpedCounter = torped.getAntalRutor();
      view.reset();
      view.updateAntalSkott("Antal skott: " + antalSkott);
   }
}
