
public class Main {
    public static void main(String args[]) {
        System.out.println("Начало игры.");
        Field field = new Field();
        /*[dev.nikor]
        Кидаем в конструктор
        field.eraseField();
        field.showField();
        */
        char nextChar = 'O';
        //[dev.nikor]
        //Эту строчку лучше бы переместить в начало, а то эта надпись появляется уже после вывода поля для игры
        //System.out.println("Начало игры.");

        //[dev.nikor] Есть ошибка, если все клетки будут заняты и никто не выиграет
        while(!field.checkVictory()) {
            //[dev.nikor]
            //Можно заменить этот if-else на nextChar = (nextChar == 'X' ? 'O' : 'X');
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
