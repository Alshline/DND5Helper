package Resources.Coins;

import Randomizer.Dice;

public abstract class BasicCoinClass {

    private int count;
    protected String coinName;

    public BasicCoinClass (int diceCount){
        this.count = Dice.diceRoll(diceCount,6);
    }

    public int getCoinCount(){
        return count;
    }
}
