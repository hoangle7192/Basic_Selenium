package TestNG.LOOP;

import org.testng.annotations.Test;

import java.util.Random;

public class vietlot {

/*    public static void main(String[] args) {
        System.out.println("1: " + soxovietlot());
        System.out.println("2: " + soxovietlot());
        System.out.println("3: " + soxovietlot());
        System.out.println("4: " + soxovietlot());
        System.out.println("5: " + soxovietlot());
        System.out.println("6: " + soxovietlot());

    }*/
    @Test
    public void TC01() {
        System.out.println("1: " + soxovietlot());
        System.out.println("2: " + soxovietlot());
        System.out.println("3: " + soxovietlot());
        System.out.println("4: " + soxovietlot());
        System.out.println("5: " + soxovietlot());
        System.out.println("6: " + soxovietlot());
    }
    public int soxovietlot() {
        Random rand = new Random();
        return rand.nextInt(55);
    }
}
