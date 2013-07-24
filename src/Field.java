import java.util.Scanner;

public class Field {

    public static final int FIELD_SIZE = 3;
    private static final char DEFAULT_CHAR = ' ';
    public char[][] field;

    public Field() {
        field = new char[FIELD_SIZE][FIELD_SIZE];
        eraseField();
        showField();
    }

    public void showField() {
        System.out.println();
        for (int i = 0; i < FIELD_SIZE; ++i) {
            for (int j = 0; j < FIELD_SIZE; ++j){
                System.out.print("[" + field[i][j] + "]");
            }
            System.out.println();
        }
    }

    public void eraseField() {
        for (int i = 0; i < FIELD_SIZE; ++i) {
            for (int j = 0; j < FIELD_SIZE; ++j) {
                field[i][j] = DEFAULT_CHAR;
            }
        }
    }

    public boolean checkVictory() {

        boolean victory, altVictory;

        for (int i = 0; i < FIELD_SIZE; i++){
            victory = checkRow(i);
            altVictory = checkColumn(i);
            if (victory || altVictory) {
                return true;
            }
        }

        char firstChar = field[0][0];
        char altFirstChar = field[0][FIELD_SIZE - 1];
        if(firstChar == DEFAULT_CHAR && altFirstChar == DEFAULT_CHAR){
            return false;
        }
        victory = altVictory = true;
        for (int i = 1; i < FIELD_SIZE; i++){
            char temp = field[i][i];
            victory = !(temp != firstChar || temp == DEFAULT_CHAR);
            temp = field[i][FIELD_SIZE - 1 - i];
            altVictory = !(temp != altFirstChar || temp == DEFAULT_CHAR);
        }
        return victory || altVictory;
    }

    public void nextStep(char nextChar) {
        System.out.println("Ходят " + nextChar + ".");
        Scanner scan = new Scanner(System.in);
        int lineNumber = 0;
        int columnNumber = 0;
        boolean inputAgain = true;
        while (inputAgain) {
            System.out.print("Введите номер строки незанятой ячейки (нумерация начинается с 1): ");
            lineNumber = scan.nextInt() - 1;
            System.out.print("Введите номер столбца незанятой ячейки (нумерация начинается с 1): ");
            columnNumber = scan.nextInt() - 1;
            if (lineNumber >= FIELD_SIZE || lineNumber < 0 || columnNumber >= FIELD_SIZE || columnNumber < 0
                    || field[lineNumber][columnNumber] != DEFAULT_CHAR) {
                System.out.println("Введены неверные значения строки или столбца, либо данная ячейка уже заполнена. Повторите ввод.");
            } else {
                inputAgain = false;
            }
        }
        field[lineNumber][columnNumber] = nextChar;
    }

    // private
    private boolean checkRow(int lineNumber) {
        char firstChar = field[lineNumber][0];
        if (firstChar == DEFAULT_CHAR) {
            return false;
        }
        for (int i = 1; i < FIELD_SIZE; ++i) {
            if (field[lineNumber][i] != firstChar) {
                return false;
            }
        }
        return true;
    }

    private boolean checkColumn(int lineNumber) {
        char firstChar = field[0][lineNumber];
        if (firstChar == DEFAULT_CHAR) {
            return false;
        }
        for (int i = 1; i < FIELD_SIZE; ++i) {
            if (field[i][lineNumber] != firstChar) {
                return false;
            }
        }
        return true;
    }
}
