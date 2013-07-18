import java.util.Scanner;

public class Field {

    private static final int FIELD_SIZE = 3;
    private static final char DEFAULT_CHAR = ' ';
    public char[][] field = new char[FIELD_SIZE][FIELD_SIZE];

// public
    public void showField() {
        System.out.println();
        for (int i = 0; i < FIELD_SIZE; ++i) {
            showLine(i);
            System.out.println();
        }
    }
    public void eraseField() {
        for (int i = 0; i < FIELD_SIZE; ++i) {
            eraseLine(i);
        }
    }
    public boolean checkVictory() {
        boolean victory = false;
        // check rows
        for (int i = 0; i < FIELD_SIZE; ++i) {
            victory = checkRow(i);
            if(victory){
                return victory;
            }
        }
        // check columns
        for (int i = 0; i < FIELD_SIZE; ++i) {
            victory = checkColumn(i);
            if(victory){
                return victory;
            }
        }
        // check diagonals
        char firstChar = field[0][0];
        if(firstChar != ' '){
            victory = true;
            for (int i = 1; i < FIELD_SIZE; ++i) {
                if(field[i][i] != firstChar){
                    victory = false;
                    break;
                }
            }
            if(victory){
                return victory;
            }
        }
        victory = true;
        firstChar = field[0][FIELD_SIZE-1];
        if(firstChar == ' '){
            return false;
        }
        for (int i = 1; i < FIELD_SIZE; ++i) {
            if(field[i][FIELD_SIZE-1-i] != firstChar){
                victory = false;
                break;
            }
        }
        return victory;
    }
    public void nextStep(char nextChar) {
        System.out.println("Ходят "+nextChar+".");
        Scanner scan = new Scanner(System.in);
        int stringNumber = 0;
        int columnNumber = 0;
        boolean inputAgain = true;
        while(inputAgain) {
            System.out.print("Введите номер строки незанятой ячейки (начиная с 0): ");
            stringNumber = scan.nextInt();
            System.out.print("Введите номер столбца незанятой ячейки (начиная с 0): ");
            columnNumber = scan.nextInt();
            if(stringNumber >= FIELD_SIZE || stringNumber < 0 || columnNumber >= FIELD_SIZE || columnNumber < 0 || field[stringNumber][columnNumber] != ' '){
                System.out.println("Введены неверные значения строки или столбца, либо данная ячейка уже заполнена. Повторите ввод.");
            } else {
                inputAgain = false;
            }
        }
        field[stringNumber][columnNumber] = nextChar;
    }

// private
    private void showLine(int lineNumber) {
        for (int i = 0; i < FIELD_SIZE; ++i) {
            showCell(lineNumber, i);
        }
    }
    private void showCell(int x, int y) {
        System.out.print("["+field[x][y]+"]");
    }
    private void eraseLine(int lineNumber) {
        for (int i = 0; i < FIELD_SIZE; ++i) {
            eraseCell(lineNumber, i);
        }
    }
    private void eraseCell(int x, int y) {
        field[x][y] = DEFAULT_CHAR;
    }
    private boolean checkRow(int lineNumber) {
        char firstChar = field[lineNumber][0];
        if(firstChar == ' '){
            return false;
        }
        for (int i = 1; i < FIELD_SIZE; ++i) {
            if(field[lineNumber][i] != firstChar) {
                return false;
            }
        }
        return true;
    }
    private boolean checkColumn(int lineNumber) {
        char firstChar = field[0][lineNumber];
        if(firstChar == ' '){
            return false;
        }
        for (int i = 1; i < FIELD_SIZE; ++i) {
            if(field[i][lineNumber] != firstChar) {
                return false;
            }
        }
        return true;
    }

}
