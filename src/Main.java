
public class Main {
    public static void main(String args[]) {
        Field field = new Field();
        field.eraseField();
        field.showField();
        char nextChar = 'O';
        System.out.println("Начало игры.");
        while(!field.checkVictory()) {
            if(nextChar == 'X') {
                nextChar = 'O';
            } else {
                nextChar = 'X';
            }
            field.nextStep(nextChar);
            field.showField();
        }
        System.out.println("Игра завершена.");
        System.out.println("Победили "+nextChar+".");
    }
}
