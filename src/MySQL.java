import java.sql.*;

public class MySQL {


    public void connecxionMySQL(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/images","root","");

            Statement stmt=con.createStatement();
            System.out.println("Connection à MySQL réussi");
            /*ResultSet rs=stmt.executeQuery("select * from signatures");
            while(rs.next())
                System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getDouble(3));
            con.close();*/
        }catch(Exception e){ System.out.println(e);}
    }


}

