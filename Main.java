package tictactoe;

import java.util.Scanner;

public class Main {
    private static final char[][] symbols = new char[3][3];
    private static int xs = 0;
    private static int os = 0;
    private static boolean xw = false;
    private static boolean ow = false;
    private static char play = 'X';

    public static void main(String[] args) {
        printTicktacktoe(symbols);
        fillTick();
        do {
            do {
                checkCoords();
            }
            while (!coords);
            playCoords();
            printTicktacktoe(symbols);
            choosingWinner();
        } while (gameOngoing());

        printResult();
    }

    public static void fillTick() {
        Scanner rd = new Scanner(System.in);
        for (int i = 0; i < 9; i++) {
            symbols[i / 3][i % 3] = ' ';
        }
        rd.close();
    }

    private static int coords_x, coords_y;
    private static boolean coords = true;
    static Scanner rc = new Scanner(System.in);

    public static void checkCoords() {
        System.out.print("Enter the coordinates: ");
        coords_x = rc.nextInt();
        coords_y = rc.nextInt();

        if (coords_x > 3 || coords_y > 3) {
            System.out.println("Coordinates should be from 1 to 3!");
            coords = false;
        } else if (symbols[3 - coords_y][coords_x - 1] != ' ') {
            System.out.println("This cell is occupied! Choose another one!");
            coords = false;
        } else {
            coords = true;
        }
    }

    public static void playCoords() {
        symbols[3 - coords_y][coords_x - 1] = play;
        if (play == 'X') {
            xs += 1;
        } else if (play == 'O') {
            os += 1;
        }
        play = (play == 'X') ? 'O' : 'X';

    }

    public static void choosingWinner() {
        int diag = 0;
        int diagI = 0;
        for (int i = 0; i < 3; i++) {
            int row = 0;
            int clm = 0;
            for (int j = 0; j < 3; j++) {
                row += symbols[i][j];
                clm += symbols[j][i];
            }
            diag += symbols[i][i];
            diagI += symbols[i][2 - i];

            xw = xw || row == 264 | clm == 264;
            ow = ow || row == 237 | clm == 237;
        }

        xw = xw || diag == 264 || diagI == 264;
        ow = ow || diag == 237 || diagI == 237;
    }


    public static void printTicktacktoe(char[][] symbols) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.printf("| %s %s %s |\n", symbols[i][0], symbols[i][1], symbols[i][2]);
        }
        System.out.println("---------");
    }

    public static void printResult() {
        if (Math.abs(xs - os) > 1 || xw && ow) {
            System.out.println("Impossible");
        } else if (xw) {
            System.out.println("X wins");
        } else if (ow) {
            System.out.println("O wins");
        } else if (xs + os == 9) {
            System.out.println("Draw");
        }
    }

    private static boolean check = true;

    public static boolean gameOngoing() {
        if (Math.abs(xs - os) > 1 || xw && ow) {
            check = false;
        } else if (xw) {
            check = false;
        } else if (ow) {
            check = false;
        } else if (xs + os == 9) {
            check = false;
        }
        return check;
    }

}

