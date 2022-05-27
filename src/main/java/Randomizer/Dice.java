package Randomizer;

import java.util.Arrays;
import java.util.Random;

public class Dice {


    public static int diceRoll (int countOfDices, int diceValue){
        int[] diceArray = new int[countOfDices];
        Random randomGenerator = new Random();

        for (int i=0;i<countOfDices;i++){
            diceArray[i] = (randomGenerator.nextInt(diceValue)+1);
        }

        int sum=0;

        for (int i=0;i<countOfDices;i++){
            sum = sum+diceArray[i];
        }

        return sum;
    }

    public static int diceRoll (int diceValue){
        Random randomGenerator = new Random();

        if (diceValue>20){
            int greaterDice = randomGenerator.nextInt(10);
            int lowerDice = randomGenerator.nextInt(10);
            int sum = greaterDice*10+lowerDice;

            if (greaterDice==0 && lowerDice==0){
                sum=100;
                return sum;
            } else return sum;
        } else {
            int rolledDice = (randomGenerator.nextInt(diceValue)+1);
            return rolledDice;
        }
    }
}
