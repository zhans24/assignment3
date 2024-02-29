package Repo;

import Database.DB;
import category.Client;
import category.Menu;

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

    public static List<Object> Bill() throws Exception {
        List<Client> clients = new ArrayList<>();
        List<Menu> doners = new ArrayList<>();
        try (Connection connection = DB.getconnection()) {
            String query = "SELECT clients.name,clients.phone,menu.doner,menu.price " +
                    "FROM orders JOIN clients ON clients.client_id=orders.client_id" +
                    "JOIN menu ON menu.menu_id=orders.menu_doner";
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Client client = Client.builder().setName(rs.getString("name")).setPhone(rs.getString("phone")).build();
                clients.add(client);
                Menu doner = Menu.builder()
                        .setDoner(rs.getString("doner")).setPrice(rs.getInt("price")).build();
                doners.add(doner);
            }
        }

    }

}
