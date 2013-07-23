import java.util.Scanner;

public class Field {

    private static final int FIELD_SIZE = 3;
    private static final char DEFAULT_CHAR = ' ';
    public char[][] field; //= new char[FIELD_SIZE][FIELD_SIZE];

    /* [dev.nikor]
        Вызовы eraseField() и showField() лучше закинуть в конструктор, чтобы избавить main от лишнего кода.
        А раз есть конструктор, то закинем туда и выделение памяти для field
     */

    public Field() {
        field = new char[FIELD_SIZE][FIELD_SIZE];
        eraseField();
        showField();
    }

    // public
    /*  [dev.nikor]
        То же замечание, что и для eraseField()
     */
    public void showField() {
        System.out.println();
        for (int i = 0; i < FIELD_SIZE; ++i) {
            for (int j = 0; j < FIELD_SIZE; ++j){
                System.out.print("[" + field[i][j] + "]");
            }
            System.out.println();
        }
    }

    /* [dev.nikor]
        Разбивать отчистку поля на 3 части (отчистка поля, строки, клетки) не имеет смысла, так как
        нет отдельных вызовов eraseLine() и eraseCell(). А вот издержки на вызов методов увеличиваются.
     */
    public void eraseField() {
        for (int i = 0; i < FIELD_SIZE; ++i) {
            for (int j = 0; j < FIELD_SIZE; ++j) {
                field[i][j] = DEFAULT_CHAR;
            }
        }
    }

    public boolean checkVictory() {
        boolean victory, altVictory;

        /*  [dev.nikor] А почему бы не совместить эти два цикла? Придётся добавить новую переменную
        // check rows
        for (int i = 0; i < FIELD_SIZE; ++i) {
            victory = checkRow(i);
            if (victory) {
                return victory;
            }
        }
        // check columns
        for (int i = 0; i < FIELD_SIZE; ++i) {
            victory = checkColumn(i);
            if (victory) {
                return victory;
            }
        }
        */

        for (int i = 0; i < FIELD_SIZE; i++){
            victory = checkRow(i);
            altVictory = checkColumn(i);
            if(victory || altVictory){
                return true;
            }
        }

        // [dev.nikor] Тут тоже можно совместить, правда, вроде это не сильно поможет. Мой вариант смотри в конце метода
        // check diagonals
        char firstChar = field[0][0];
        //[dev.nikor] Заменяем ' ' на DEFAULT_CHAR
        if (firstChar != DEFAULT_CHAR) {
            victory = true;
            for (int i = 1; i < FIELD_SIZE; ++i) {
                if (field[i][i] != firstChar) {
                    victory = false;
                    break;
                }
            }
            if (victory) {
                return victory;
            }
        }
        victory = true;
        firstChar = field[0][FIELD_SIZE - 1];
        //[dev.nikor] Заменяем ' ' на DEFAULT_CHAR
        if (firstChar == DEFAULT_CHAR) {
            return false;
        }
        for (int i = 1; i < FIELD_SIZE; ++i) {
            if (field[i][FIELD_SIZE - 1 - i] != firstChar) {
                victory = false;
                break;
            }
        }
        return victory;

        /*
        char firstChar = field[0][0];
        char altFirstChar = field[0][FIELD_SIZE - 1];

        if(firstChar == DEFAULT_CHAR && altFirstChar == DEFAULT_CHAR){
            return false;
        }
        victory = altVictory = true;
        for (int i = 0; i < FIELD_SIZE; i++){
            char temp = field[i][i];
            if(temp != firstChar || temp == DEFAULT_CHAR){
                victory = false;
            }
            temp = field[i][FIELD_SIZE - 1 - i];
            if (temp != firstChar || temp == DEFAULT_CHAR){
                altVictory = false;
            }
        }

        return victory || altVictory;
        */
    }

    public void nextStep(char nextChar) {
        System.out.println("Ходят " + nextChar + ".");
        Scanner scan = new Scanner(System.in);
        //[dev.nikor] Наверное не stringNumber, а lineNumber?
        int lineNumber = 0;
        int columnNumber = 0;
        boolean inputAgain = true;
        while (inputAgain) {
            //[dev.nikor] Вводить, начиная с нуля не особо удобно, поэтому будем вводить,
            //начиная с 1, а потом просто вычтем единицу
            System.out.print("Введите номер строки незанятой ячейки: ");
            lineNumber = scan.nextInt() - 1;
            System.out.print("Введите номер столбца незанятой ячейки: ");
            columnNumber = scan.nextInt() - 1;
            //[dev.nikor] Заменяем ' ' в последнем условии на DEFAULT_CHAR
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
    /*  [dev.nikor]
    private void showLine(int lineNumber) {
        for (int i = 0; i < FIELD_SIZE; ++i) {
            showCell(lineNumber, i);
        }
    }

    private void showCell(int x, int y) {
        System.out.print("[" + field[x][y] + "]");
    }

    private void eraseLine(int lineNumber) {
        for (int i = 0; i < FIELD_SIZE; ++i) {
            eraseCell(lineNumber, i);
        }
    }

    private void eraseCell(int x, int y) {
        field[x][y] = DEFAULT_CHAR;
    }
    */

    private boolean checkRow(int lineNumber) {
        char firstChar = field[lineNumber][0];
        //[dev.nikor] Заменяем ' ' на DEFAULT_CHAR
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
        //[dev.nikor] Заменяем ' ' на DEFAULT_CHAR
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
