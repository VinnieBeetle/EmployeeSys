package com.example.employeesys;
//counting up to 5 over multiple threads
public class Multithreading extends Thread{

    @Override
    public void run(){
        for (int i = 1; i <= 5; i++){
            System.out.println(i);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }




    }

}
