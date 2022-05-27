package PostgreSQLManager;

import Randomizer.Dice;

import java.sql.*;

public class DBManager {

    static final String DB_URL = "jdbc:postgresql://localhost:5432/TreasuresDataBase";
    static final String USER = "postgres";
    static final String PASS = "";

    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    String tableName;

    public DBManager (String tableName){

        connectToDataBase();
        selectTable(tableName);


    }

    private void selectTable (String tableName){
        this.tableName = tableName;
    }
    
    public ResultSet getResultSetFromTable(int rolledDice) throws SQLException {

        String SQL = String.format("SELECT * FROM \"%s\" WHERE \"idRange\" @> %d;", this.tableName, rolledDice);
        ResultSet resultSet = this.statement.executeQuery(SQL);
        return resultSet;
    }

    private void connectToDataBase(){
        System.out.println("Test connection");

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException classNotFoundException) {
            System.out.println("Driver not found");
            classNotFoundException.printStackTrace();
            return;
        }

        try {
            this.connection = DriverManager.getConnection(DB_URL,USER,PASS);
        } catch (SQLException sqlException) {
            System.out.println("Failed connection");
            sqlException.printStackTrace();
            return;
        }

        if (connection != null){
            System.out.println("Connected");
        } else {
            System.out.println("Failed");
        }

        try {
            this.statement = this.connection.createStatement();
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
        }

        if (statement != null){
            System.out.println("Statement created");
        } else {
            System.out.println("Failed to create statement");
        }
    }

    public static void main(String[] args) throws SQLException {
        DBManager dbManager = new DBManager("individualTreasure[0-4]");

        ResultSet resultSet = dbManager.getResultSetFromTable(50);

        resultSet.next();
        String result = resultSet.getString("SilverCoins");

        System.out.println(result);


    }
    
}
