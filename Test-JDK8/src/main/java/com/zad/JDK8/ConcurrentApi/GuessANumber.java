package com.zad.JDK8.ConcurrentApi;

/**
 * 描述:
 *
 * @author zad
 * @create 2018-10-04 14:55
 */
public class GuessANumber extends Thread {
    private int number;

    public GuessANumber(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        int counter = 0;
        int guess;
        do {
            guess = (int) (Math.random() * 100 + 1);
            System.out.println(guess);
            counter++;
        } while (guess != number);

        System.out.println("** Correct! " + this.getName() + " in " + counter + " guesses.**");
    }

    public static void main(String[] args) {
        GuessANumber guessANumber = new GuessANumber(50);
        guessANumber.start();
    }
}
