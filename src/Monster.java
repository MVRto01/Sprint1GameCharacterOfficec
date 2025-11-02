import java.util.Random;
import java.util.Scanner;

public class Monster {
    private String image = "\uD83D\uDCD1";
    private final int x, y;
    Random r = new Random();

    Monster(int sizeBoard){
        this.y = r.nextInt(sizeBoard - 1);
        this.x = r.nextInt(sizeBoard);
    }

    public String getImage() {
        return image;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public boolean conflictPerson(int perX, int perY){
        return perY - 1 == this.y && perX - 1 == this.x;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean taskMonster(int difficultGame){
        System.out.println("Решите задачу:");
        int x = r.nextInt(100);
        int y = r.nextInt(100);
        int trueAnswer = x + y;
        System.out.println("Реши пример: " + x + " + " + y + " = ?");
        Scanner sc = new Scanner(System.in);
        int ans = sc.nextInt();
        if (trueAnswer == ans) {
            System.out.println("Верно! Ты обнаружил другую зацепку");
            return true;
        }
        System.out.println("Это оказался чек от покупки чего-то! (Вы потеряли один уровень внимательности)");
        return false;
    }
}