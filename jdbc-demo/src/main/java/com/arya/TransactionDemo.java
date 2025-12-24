package com.arya;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//here transaction, not autocommit
//batch processing
public class TransactionDemo {
    private static final String URL = "jdbc:postgresql://localhost:5432/learn_jdbc";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";
     //meowww
    
    public static void main(String[] args) {
        bankTransferTransaction();
    }

    //1.Transactions
    private static void bankTransferTransaction(){
        System.out.println("\n---- Starting transaction demo will do bank transfer");

        String withdrawSQL = "UPDATE accounts SET balance = balance - ? WHERE owner = ?";
        String depositSQL = "UPDATE accounts SET balance = balance + ? WHERE owner = ?";

        Connection conn = null;
        //prefer try with resource
        try{
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            //DISABLE AUTOCOMMIT
            conn.setAutoCommit(false);
            try(
                PreparedStatement withdrawStmt = conn.prepareStatement(withdrawSQL);

                PreparedStatement depositStmt = conn.prepareStatement(depositSQL);
            )
            {
                //withdraw 10 from sura
                withdrawStmt.setDouble(1, 1000);
                withdrawStmt.setString(2, "Sura");
                int rowsA = withdrawStmt.executeUpdate();

                //deposit 5 to arya
                depositStmt.setDouble(1, 1000);
                depositStmt.setString(2, "Arya");
                int rowsB = depositStmt.executeUpdate();

                //by uncommenting below, can simulate error], money deducted but not updated?
                // throw new SQLException("Network crash simulate");

                if(rowsA > 0 && rowsB > 0){
                    //commit
                    conn.commit();
                    System.out.println("success, transaction commited");
                }else{
                    conn.rollback();
                    System.out.println("invalid ids, rolled back");
                }
            }
        }catch (SQLException | RuntimeException e){
            System.err.println("trans failed, rolling back changes....");
            if(conn != null){
                try{
                    conn.rollback();
                    System.out.println("rollback complete");
                }catch(SQLException ex){
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        }finally{
            //clean up
            if(conn != null){
                try{
                    conn.setAutoCommit(true);// reset
                    conn.close();
                }catch(SQLException e){
                    System.out.println("final catch");
                    e.printStackTrace();
                }
            }
        }
    }
}
