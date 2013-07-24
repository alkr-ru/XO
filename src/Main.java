
public class Main {
    public static void main(String args[]) {
        System.out.println("Начало игры.");
        Field field = new Field();
        char nextChar = 'O';
        int stepNumber = 0;

        while(!field.checkVictory() && stepNumber < Field.FIELD_SIZE * Field.FIELD_SIZE) {
            nextChar = (nextChar == 'X' ? 'O' : 'X');
            field.nextStep(nextChar);
            stepNumber++;
            field.showField();
        }
        System.out.println("Игра завершена.");
        if (stepNumber < Field.FIELD_SIZE * Field.FIELD_SIZE) {
            System.out.println("Победили "+nextChar+".");
        } else {
            System.out.println("Ничья.");
        }
    }
}
