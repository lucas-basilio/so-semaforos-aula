package view;

import controller.KnightThread;

import java.util.*;
import java.util.concurrent.Semaphore;

public class Main
{
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(1);
        Random rnd = new Random();

        int rightDoor = rnd.nextInt(4);
        List<Integer> options = new ArrayList<>();

        for (int x = 1; x <= 4; x++)
        {
            options.add(x);
        }

        Collections.shuffle(options);

        for (int x =0; x < 4; x++)
        {
            KnightThread t = new KnightThread(rightDoor, semaphore, options.get(x));
            t.start();
        }
    }
}
