package controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class KnightThread extends Thread
{
    private int ms = 50;
    private int distance;
    private int basePace = 0;
    private boolean hasItem = false;
    private static int rightDoor;
    private static HashMap<String, Boolean> items = new HashMap<>();
    private int exit;
    private String name = "O Cavaleiro " + getId();
    private static Semaphore semaphore;

    public KnightThread (int exit, Semaphore semaphore, int opt)
    {
        this.rightDoor = exit;
        this.semaphore = semaphore;
        this.exit = opt;
        items.put("Tocha", true);
        items.put("Pedra Brilhante", true);
    }

    private void race ()
    {
        //int pace = this.basePace + (int)((Math.random() * (4 - 1) + 1) + 1);
        int pace = this.basePace + (new Random().nextInt(4) + 1);

        while (this.distance < 2000)
        {
            if (this.distance >= 500 && this.items.get("Tocha"))
            {
                try
                {
                    this.semaphore.acquire();
                    System.out.println(this.name + " capturou a Tocha!");
                    this.basePace += 2;
                    this.items.replace("Tocha", false);
                    this.hasItem = true;
                }
                catch (Exception ex)
                {
                    System.err.println(ex.getMessage());
                }
                finally
                {
                    this.semaphore.release();
                }
            }

            if (this.distance >= 1500 && this.items.get("Pedra Brilhante") && !this.hasItem)
            {
                try
                {
                    this.semaphore.acquire();
                    System.out.println(this.name + " capturou a Pedra Brilhante!");
                    this.basePace += 2;
                    this.items.replace("Pedra Brilhante", false);
                    this.hasItem = true;
                }
                catch (Exception ex)
                {
                    System.err.println(ex.getMessage());
                }
                finally
                {
                    this.semaphore.release();
                }
            }

            if (this.distance + pace >= 2000)
            {
                try
                {
                    semaphore.acquire();
                    System.out.println(this.name + " escolheu a porta " + exit);

                    if (this.rightDoor == this.exit)
                    {
                        System.out.println(this.name + " venceu a corrida!");
                    }
                    else
                    {
                        System.out.println(this.name + " morreu");
                    }
                }
                catch (Exception ex)
                {
                    System.err.println(ex.getMessage());
                }
                finally
                {
                    semaphore.release();
                    return;
                }
            }

            System.out.println(this.name + " percorreu " + pace + " e já andou " + distance + "m");
            this.distance += pace;
            try
            {
                //sleep(50);
            }
            catch (Exception ex)
            {
                System.err.println(ex.getMessage());
            }
        }
    }

    @Override
    public void run ()
    {
        race();
    }
}
