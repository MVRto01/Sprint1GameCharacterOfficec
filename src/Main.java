import java.sql.SQLOutput;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String castle = "\uD83C\uDFE2";
        int sizeBoard = 5;

        Person person = new Person(sizeBoard);


        int step = 0;

        String[][] board = new String[sizeBoard][sizeBoard];
        for (int y = 0; y < sizeBoard; y++) {
            for (int x = 0; x < sizeBoard; x++) {
                board[y][x] = "  ";
            }
        }


        int countMonster = sizeBoard * sizeBoard - sizeBoard - 5;
        Random r = new Random();

        // для работы сбольшим количеством монстров воспользуемся массивом
        Monster[] arrMonster = new Monster[countMonster + 1];
        int count = 0;
        Monster test;
        while (count <= countMonster) {
            if (r.nextBoolean()) {
                test = new Monster(sizeBoard);
            } else {
                test = new BigMonster(sizeBoard);
            }
            if (board[test.getY()][test.getX()].equals("  ")) {
                board[test.getY()][test.getX()] = test.getImage();
                arrMonster[count] = test;
                count++;
            }

        }

        // колличесвто рюкзаков на игровом поле

        int countBackpack = 2;

        Backpack[] backPacks = new Backpack[countBackpack + 1];
        int bcount = 0;
        Backpack btest;
        while (bcount <= countBackpack) {
            btest = new Backpack(sizeBoard);
            if (board[btest.getY()][btest.getX()].equals("  ")) {
                board[btest.getY()][btest.getX()] = btest.getImage();
                backPacks[bcount] = btest;
                bcount++;
            }
        }

        int castleX = r.nextInt(sizeBoard);
        int castleY = 0;


        board[castleY][castleX] = castle;

        System.out.println("Привет! Ты готов начать играть в игру? (Напиши: ДА или НЕТ)");

        Scanner sc = new Scanner(System.in);
        String answer = sc.nextLine();
        System.out.println("Ваш ответ:\t" + answer);


        switch (answer) {
            case "ДА" -> {
                System.out.println("Выбери сложность игры (от 1 до 5):");
                int difficultGame = sc.nextInt();
                System.out.println("Выбранная сложность:\t" + difficultGame);
                System.out.println("Привет, мой напарник! Ты знаешь, как мне добраться до нашего штаба? Я запутался в переулках, а телефон разрядился не вовремя. Пожалуйста, покажи куда мне следует идти!" );
                while (true) {
                    board[person.getY() - 1][person.getX() - 1] = person.getImage();
                    outputBoard(board, person.getLive());
                    System.out.println("Введите куда будет ходить персонаж (ход возможен только по вертикали и горизонтали на одну клетку)" +
                            "\nКоординаты персонажа - (x: " + person.getX() + ", y: " + person.getY() + "))");
                    int x = sc.nextInt();
                    int y = sc.nextInt();

                    // проверка
                    if (person.moveCorrect(x, y)) {
                        String next = board[y - 1][x - 1];
                        if (next.equals("  ")) {
                            board[person.getY() - 1][person.getX() - 1] = "  ";
                            person.move(x, y);
                            step++;
                            System.out.println("Ход корректный; Новые координаты: " + person.getX() + ", " + person.getY() +
                                    "\nХод номер: " + step);
                        } else if (next.equals(castle)) {
                            System.out.println("Спасибо тебе огромное за помощь! Ты смог указать мне правильный путь! В следующий раз я точно не потеряюсь с тобой. До скорой встречи!");
                            System.out.println("Вы прошли игру!");
                            break;
                        } else {
                            for (Monster monster : arrMonster) {
                                if (monster.conflictPerson(x, y)) {
                                    if (monster.taskMonster(difficultGame)) {
                                        board[person.getY() - 1][person.getX() - 1] = "  ";
                                        person.move(x, y);
                                    } else {
                                        person.downLive();
                                        board[person.getY() - 1][person.getX() - 1] = "  ";
                                        person.move(x, y);
                                    }
                                    break;
                                }
                            }
                            for (Backpack backPack : backPacks) {
                                if (backPack.conflictPerson(x, y)) {
                                    if (person.getLive() < 3) {
                                        System.out.println("Ого! Ты нашёл потерянный рюкзак! В нём находятся чьи-то вещи. (Вы с Детективом забрали рюкзак, чтобы объявить о его пропаже городу. За это Вам даётся +1 уровень внимательности)");
                                        person.upLive();
                                    } else {
                                        System.out.println("Интересно, ты нашёл рюкзак, но к сожалению нам надо двигаться дальше. Я укажу о его местоположении ближайшему штабу. \uD83D\uDCCD");
                                    }
                                    board[person.getY() - 1][person.getX() - 1] = "  ";
                                    person.move(x, y);
                                    break;
                                }

                            }
                        }
                    } else {
                        System.out.println("Неккоректный ход");
                    }
                }
            }
            case "НЕТ" -> System.out.println("'Ох, куда пропала зацепка о ограблении!' - Игра закончилась. Попробуй ещё раз  провести детектива до штаба." );
            default -> System.out.println("Данные введены неккоректно");
        }

    }

    static void outputBoard(String[][] board, int live) {
        String leftBlock = "| ";
        String rightBlock = "|";
        String wall = "+ —— + —— + —— + —— + —— +";

        for (String[] raw : board) {
            System.out.println(wall);
            for (String col : raw) {
                System.out.print(leftBlock + col + " ");
            }
            System.out.println(rightBlock);
        }
        System.out.println(wall);


        System.out.println("Уровень внимательности:\t" + live + "\n");
    }
}