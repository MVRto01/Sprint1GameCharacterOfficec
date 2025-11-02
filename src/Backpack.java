import java.util.Random;

public class Backpack {
    private String image = "\uD83C\uDF92";
    Random r = new Random();

    private int x, y, amountLive;

    Backpack(int sizeBoard) {
        this.y = r.nextInt(sizeBoard - 1);
        this.x = r.nextInt(sizeBoard);
        amountLive = 1;
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

    public int getAmountLive() {
        return amountLive;
    }

//        System.out.println("Ты нашёл потерянный рюкзак. В нём лежит: ");
//        int amountLive = r.nextInt(3);
//        System.out.println(amountLive + " колличество жизней!");
//        person.live += amountLive


}
