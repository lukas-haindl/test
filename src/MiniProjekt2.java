import codedraw.CodeDraw;
import codedraw.Palette;

import java.awt.*;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class MiniProjekt2 {

    public static Color determineColor() {
        Random rand = new Random();
        int randNumber = rand.nextInt(100);
        if (randNumber < 40) { // 40% Wahrscheinlichkeit
            return Color.BLUE;
        } else if (randNumber < 70) { // 30% Wahrscheinlichkeit
            return Color.RED;
        } else if (randNumber < 90) { // 20% Wahrscheinlichkeit
            return Color.GREEN;
        } else { // 10% Wahrscheinlichkeit
            return Color.YELLOW;
        }
    }

    public static int calculateWin(Color color, int bet) {
        if (color == Color.BLUE) {
            return bet * 2; // Blau: Häufigste Farbe, niedriger Gewinnmultiplikator
        } else if (color == Color.RED) {
            return bet * 3; // Rot: Moderater Gewinnmultiplikator
        } else if (color == Color.GREEN) {
            return bet * 5; // Grün: Höherer Gewinnmultiplikator
        } else { // Gelb
            return bet * 10; // Gelb: Seltenste Farbe, höchster Gewinnmultiplikator
        }
    }

    public static int spinWheel(int bet) {
        int max = 3;
        int width = 1920;
        int height = 1080;
        CodeDraw myDrawObj = new CodeDraw(width, height);
        myDrawObj.setColor(Palette.RED);
        myDrawObj.drawText(width / 2.0, 10, "Jackpot Multiplier");
        int ws = 600;
        double squareSize = ws / (double) max;
        myDrawObj.setColor(Palette.SANDY_BROWN);
        myDrawObj.fillSquare(width / 2.0 - (ws + squareSize) / 2.0, height / 2.0 - (ws + squareSize) / 2.0, ws + squareSize);
        myDrawObj.setLineWidth(5);
        double x = width / 2.0 - ws / 2.0;
        double y = height / 2.0 - ws / 2.0;

        Color[][] field = new Color[max][max];

        for (int col = 0; col < max; col++) {
            for (int row = 0; row < max; row++) {
                myDrawObj.setColor(determineColor());
                field[col][row] = myDrawObj.getColor();
                myDrawObj.fillSquare(x, y, squareSize);
                myDrawObj.setColor(Color.BLACK);
                myDrawObj.drawSquare(x, y, squareSize);
                y += squareSize;
            }
            myDrawObj.show(1000);
            x += squareSize;
            y = height / 2.0 - ws / 2.0;
        }

        boolean sameRow = true;
        int totalWin = 0;
        Color tmp;

        for (int row = 0; row < max; row++) {
            tmp = field[0][row];
            for (int col = 1; col < max; col++) {
                if (field[col][row] != tmp) {
                    sameRow = false;
                    break;
                }
            }
            if (sameRow) {
                totalWin += calculateWin(tmp, bet); // Beispielhafter Einsatz von 10 pro Reihe
            }
            sameRow = true;
        }

        myDrawObj.show(3000);
        myDrawObj.close();
        return totalWin;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Start mit vorgegebenen Credits
        int credits = 300;
        System.out.printf("Willkommen bei der Slot-Maschine! Sie starten mit %d Credits.\n", credits);

        while (true) {
            if (credits <= 0) {
                System.out.print("Ihr Guthaben ist aufgebraucht. Möchten Sie Credits aufladen? (100 Credits = 10€) (ja/nein): ");
                String response = scanner.next();
                if (response.equalsIgnoreCase("ja")) {
                    System.out.print("Wie viele Credits möchten Sie aufladen? (in Vielfachen von 100): ");
                    try {
                        int additionalCredits = scanner.nextInt();
                        if (additionalCredits % 100 == 0 && additionalCredits > 0) {
                            credits += additionalCredits;
                            System.out.printf("Erfolgreich aufgeladen! Ihr neues Guthaben beträgt: %d Credits.\n", credits);
                        } else {
                            System.out.println("Ungültige Eingabe. Bitte geben Sie einen Betrag in Vielfachen von 100 ein.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Ungültige Eingabe. Aufladung nicht durchgeführt.");
                        scanner.nextLine();
                    }
                } else {
                    System.out.println("Spiel beendet. Vielen Dank fürs Spielen!");
                    break;
                }
            }

            System.out.printf("Ihr aktueller Kontostand: %d Credits\n", credits);
            System.out.print("Mit wie viel Einsatz möchten Sie spielen (mindestens 10 Credits)? ");
            int bet = 0;
            try {
                bet = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Fehler! Geben Sie eine ganze Zahl ein.");
                scanner.nextLine();
                continue;
            }
            if (bet < 10 || bet > credits) {
                System.out.printf("Fehler! Der Einsatz muss zwischen 10 und %d Credits liegen.\n", credits);
                continue;
            }

            credits -= bet;
            int result = spinWheel(bet);

            if (result > 0) {
                System.out.printf("Glückwunsch! Sie haben %d Credits gewonnen!\n", result);
                credits += result;
            } else {
                System.out.println("Schade! Sie haben leider verloren.");
            }
        }

        scanner.close();
    }
}
