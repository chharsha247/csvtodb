package javaapplication2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.Class.forName;
import java.sql.*;

public class CsvtoDb {
    static Connection con;
    static PreparedStatement ps;
    public static void main(String[] args) throws SQLException {
    String csvFile = "C:\\Users\\Harsha\\Documents\\NetBeansProjects\\CsvtoDb\\h.csv";
            BufferedReader br = null;
            String line = "";
            String splitby = ",";
            try{
            forName("org.apache.derby.jdbc.ClientDriver");
            } catch(ClassNotFoundException e){
            }
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/csvtodb", "csv", "csv");
             
            try {

                br = new BufferedReader(new FileReader(csvFile));
                while ((line = br.readLine()) != null) {
                    String[] str = line.split(splitby);
                    ps = con.prepareStatement("Insert into Student(id,name,contactno,address) values(?,?,?,?)");
                                ps.setLong(1,Long.parseLong(str[0]));
                                ps.setString(2,str[1]);
                                ps.setLong(3,Long.parseLong(str[2]));
                                ps.setString(4,str[3]);
                                ps.executeUpdate();
                                ps. close();
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
    }
    
}
