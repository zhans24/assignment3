package Repo;

import Database.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderRep {
    public static void AddOrder(int id,int doner) throws Exception{
        try(Connection connection=DB.getconnection()){
            String query="INSERT INTO orders (client_id,menu_doner) VALUES (?,?);";
            PreparedStatement st=connection.prepareStatement(query);
            st.setInt(1,id);
            st.setInt(2,doner);
            st.execute();
        }
    }

//    public static List<Object> Bill() throws Exception{
//        ArrayList<ArrayList<Object>> bill = new ArrayList<>();
//        try (Connection connection=DB.getconnection()){
//            String query="SELECT clients.name,clients.phone,menu.doner,menu.price " +
//                    "FROM orders JOIN clients ON clients.client_id=orders.client_id" +
//                    "JOIN menu ON menu.menu_id=orders.menu_doner";
//            PreparedStatement st=connection.prepareStatement(query);
//            ResultSet rs=st.executeQuery();
//            while (rs.next()){
//
//            }
//        }
//    }
}
