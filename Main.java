package tictactoe;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Input
        boolean winsX = false;
        boolean winsO = false;
        boolean freeFields = false;
        boolean valid = false;
        boolean gameNotFinished = false;
        int[] userMove = new int[2];
        char[][] tttField = new char[3][3];
        char actualPlayer = 'X';
        int counterChar = 0;

        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {
                tttField[i][j] = ' ';
                counterChar += 1;
            }
        }

        // Process
        printField(tttField);
        while (!gameNotFinished) {

            userMove = movesOfUser(tttField);
            int userHasMovedCollum = userMove[0];
            int userHasMovedLine = userMove[1];

            tttField[userHasMovedCollum][userHasMovedLine] = actualPlayer;
            switch (actualPlayer) {
                case 'X':
                    actualPlayer = 'O';
                    break;
                case 'O':
                    actualPlayer = 'X';
                    break;
            }

            winsX = checkWinsX(tttField);
            winsO = checkWinsO(tttField);
            freeFields = checkFreeFields(tttField);
            valid = checkGameCondition(tttField);

            printField(tttField);
            statusMsgGame(winsX, winsO, freeFields, valid);
            gameNotFinished = finishedGame(winsX, winsO, freeFields);
        }



    }

    public static boolean checkGameCondition (char[][] gameField) {
        boolean valid = true;
        int counterX = 0;
        int counterO = 0;
        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField[i].length; j++) {
                if (gameField[i][j] == 'X') {
                    counterX++;
                }
                if (gameField[i][j] == 'O') {
                    counterO++;
                }
            }
        }
        if (counterX - counterO > 1 || counterO - counterX > 1) {
            valid = false;
        }
        return valid;
    }

    public static boolean checkWinsX (char[][] gameField) {
        boolean winsX = false;
        int counterX = 0;
        for (int i = 0; i < gameField.length; i++) {
            counterX = 0;
            for (int j = 1; j < gameField[i].length; j++) {
                if (gameField[i][j] == 'X' && gameField[i][j - 1] == 'X') {
                    counterX++;
                } else {
                    counterX = 0;
                }
                if (counterX == 2) {
                    winsX = true;
                }

            }
        }

        for (int i = 0; i < gameField.length; i++) {
            counterX = 0;
            for (int j = 1; j < gameField[i].length; j++) {
                if (gameField[j][i] == 'X' && gameField[j - 1][i] == 'X') {
                    counterX++;
                } else {
                    counterX = 0;
                }
                if (counterX == 2) {
                    winsX = true;
                }

            }
        }

        if ((gameField[0][0] == 'X' && gameField[1][1] == 'X' && gameField[2][2] == 'X') ||
                (gameField[0][2] == 'X' && gameField[1][1] == 'X' && gameField[2][0] == 'X')) {
            winsX = true;
        }



        return winsX;
    }

    public static boolean checkWinsO (char[][] gameField) {
        boolean winsO = false;
        int counterO = 0;
        for (int i = 0; i < gameField.length; i++) {
            counterO = 0;
            for (int j = 1; j < gameField[i].length; j++) {
                if (gameField[i][j] == 'O' && gameField[i][j - 1] == 'O') {
                    counterO++;
                } else {
                    counterO = 0;
                }
                if (counterO == 2) {
                    winsO = true;
                }

            }

        }
        for (int i = 0; i < gameField.length; i++) {
            counterO = 0;
            for (int j = 1; j < gameField[i].length; j++) {
                if (gameField[j][i] == 'O' && gameField[j - 1][i] == 'O') {
                counterO++;
                } else {
                counterO = 0;
                }
            if (counterO == 2) {
                winsO = true;
                }

            }
        }
        if ((gameField[0][0] == 'O' && gameField[1][1] == 'O' && gameField[2][2] == 'O') ||
                (gameField[0][2] == 'O' && gameField[1][1] == 'O' && gameField[2][0] == 'O')) {
            winsO = true;
        }


        return winsO;
    }

    public static boolean checkFreeFields (char[][] gameField) {
        boolean freeFields = false;
        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField[i].length; j++) {
                if (gameField[i][j] == ' ') {
                    freeFields = true;
                }

            }
        }


        return freeFields;
    }

    public static void statusMsgGame(boolean winsX, boolean winsO, boolean freeFields, boolean gameCondition) {

        if (!gameCondition || (winsX && winsO)) {
            System.out.println("Impossible");
        }

        if (winsX && !winsO) {
            System.out.println("X wins");
        }

        if (!winsX && winsO) {
            System.out.println("O wins");
        }

        /*if (!winsX && !winsO && freeFields) {
            return "Game not finished";
        } */

        if (!winsX && !winsO && !freeFields) {
            System.out.println("Draw");
        }


    }

    public static boolean finishedGame(boolean winsX, boolean winsO, boolean freeFields) {
        boolean gameFinished = false;

        if (winsX && !winsO) {
            gameFinished = true;
        }

        if (!winsX && winsO) {
            gameFinished = true;
        }
        if (!winsX && !winsO && !freeFields) {
            gameFinished = true;
        }
        return gameFinished;
    }

    public static int[] movesOfUser (char[][] gameField) {


        Scanner scanner = new Scanner(System.in);
        int outputCollum = 0;
        int outputLine = 0;
        boolean validField = false;

        while (!validField) {
        boolean checkValue = false;
        boolean checkNumber = false;

        String userMoveCollum = scanner.next();
        String userMoveLine = scanner.next();
        char userMoveLetterCollum = userMoveCollum.charAt(0);
        char userMoveLetterLine = userMoveLine.charAt(0);

            while (!checkNumber) {
            if (!Character.isDigit(userMoveLetterCollum) || !Character.isDigit(userMoveLetterLine)) {
                System.out.println("Invalid input. Enter digits! Try again!");
                userMoveCollum = scanner.next();
                userMoveLine = scanner.next();
                userMoveLetterCollum = userMoveCollum.charAt(0);
                userMoveLetterLine = userMoveLine.charAt(0);
            } else {
                checkNumber = true;
            }
        }

        int collum = Character.getNumericValue(userMoveLetterCollum);
        int line = Character.getNumericValue(userMoveLetterLine);

        while (!checkValue) {

            if (collum < 0 || collum > 3 || line < 0 || line > 3) {
                System.out.println("Invalid input. Try again!");
                collum = scanner.nextInt();
                line = scanner.nextInt();
            } else {
                checkValue = true;
            }
        }

        collum--;
        outputLine = collum;

        if (line == 1) {
            outputCollum = 2;
        } else if (line == 2) {
            outputCollum = 1;
        } else if (line == 3) {
            outputCollum = 0;
        }
        if (gameField[outputCollum][outputLine] == 'X' ||
                gameField[outputCollum][outputLine] == 'O') {
            System.out.println("Field blocked! Please input another Field!");
        } else {
            validField = true;
        }
    }
        int[] gameFieldPosition = {outputCollum, outputLine};


        return gameFieldPosition;
    }

    public static void printField (char[][] gameField) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(gameField[i][j] + " ");
            }
            System.out.print("| \n");
        }
        System.out.println("---------");
        }


}


