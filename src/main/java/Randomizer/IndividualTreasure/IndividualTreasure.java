package Randomizer.IndividualTreasure;

import PostgreSQLManager.DBManager;
import Randomizer.Dice;
import Resources.Coins.BasicCoinClass;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class IndividualTreasure {

    private final String individualTreasure0_4      = "individualTreasure[0-4]";
    private final String individualTreasure5_10     = "individualTreasure[5-10]";
    private final String individualTreasure11_16    = "individualTreasure[11-16]";
    private final String individualTreasure17       = "individualTreasure[17+]";

    DBManager dataBaseManager;
    ResultSet resultSet;
    Map <String,Integer> mapOfCoins;

    String[] coinsNamesArray = {"CopperCoins","SilverCoins","ElectrumCoins","GoldenCoins","PlatinumCoins"};

    private String getDangerClass(int dangerClass){
        if (dangerClass>=0 && dangerClass<5){
            return individualTreasure0_4;
        } else if (dangerClass>4 && dangerClass<11){
            return individualTreasure5_10;
        } else if (dangerClass>10 && dangerClass<17){
            return individualTreasure11_16;
        } else return individualTreasure17; // if danger Class > 16
    }

    private void setResultSet(ResultSet resultSet){
        this.resultSet = resultSet;
    }

    public IndividualTreasure(int dangerClass) throws SQLException {
        String resultDangerClass = getDangerClass(dangerClass);
        dataBaseManager = new DBManager(resultDangerClass);

        int rolledDice = Dice.diceRoll(100);

        resultSet = dataBaseManager.getResultSetFromTable(rolledDice);

        resultSet.next();

        mapOfCoins = new HashMap<>();

        for (int i=0;i< coinsNamesArray.length;i++){
            String columnFromTable = resultSet.getString(coinsNamesArray[i]);
            if (resultSet.wasNull()) continue;
            int countOfDices = Integer.valueOf(columnFromTable);
            Integer sumOfDices = Dice.diceRoll(countOfDices,6);
            mapOfCoins.put(coinsNamesArray[i],sumOfDices);
        }

        System.out.println("U get " +mapOfCoins);
    }

    public static void main(String[] args) throws SQLException {
        IndividualTreasure individualTreasure = new IndividualTreasure(1);
    }
}
