package controller;

import java.util.concurrent.Semaphore;

public class AirportThread extends Thread{

    private static Semaphore semaphore;
    private final String name = "O Avião " + getId();

    public AirportThread (Semaphore semaforo)
    {
        this.semaphore = semaforo;
    }

    public void Decolar ()
    {
        try
        {
            int milli = (int)(Math.random() * (800 - 500) + 500);
            this.semaphore.acquire();
            System.out.println(this.name + " está decolando...");
            sleep(milli);
            System.out.println(this.name + " decolou!");
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

    public void Taxiar ()
    {
        try
        {
            int milli = (int)(Math.random() * (1000 - 500) + 500);
        }
        catch (Exception ex)
        {

        }
        finally
        {

        }
    }

    public void Afastamento () {
        try
        {
            int milli = (int)(Math.random() * (800 - 300) + 300);
            System.out.println(this.name + " está se afastando do aeroporto...");
            sleep(milli);
            System.out.println(this.name + " se afastou do aeroporto!");
        }
        catch (Exception ex)
        {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void run () {

    }
}
