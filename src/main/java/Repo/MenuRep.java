package Repo;

import Database.DB;
import category.Menu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuRep {
    static Connection connection;

    static {
        try {
            connection = DB.getconnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    public MenuRep() {
    }


    public void AddMenu(Menu menu) {
        try {
            String query="INSERT INTO Menu (doner,price) VALUES (?,?);";

            PreparedStatement st= connection.prepareStatement(query);
            st.setString(1,menu.getDoner());
            st.setInt(2,menu.getPrice());
            st.execute();
        } catch (SQLException kkk) {
            System.out.println(kkk.getMessage());;
        }
    }

    public void DeleteMenu(int id){
        try {
            String query="DELETE FROM menu WHERE menu_id=?;";

            PreparedStatement st=connection.prepareStatement(query);
            st.setInt(1,id);
            st.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());;
        }
    }

    public List<Menu> getAll() {
        List<Menu> menus=new ArrayList<>();
        try {
            String query="SELECT * FROM menu ORDER BY menu_id asc ";
            PreparedStatement st=connection.prepareStatement(query);
            ResultSet rs=st.executeQuery();
            while (rs.next()){
                Menu menu=new Menu();
                menu.setId(rs.getInt("menu_id"));
                menu.setDoner(rs.getString("Doner"));
                menu.setPrice(rs.getInt("price"));
                menus.add(menu);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return menus;
    }

    public Menu getByID(int id) {
        Menu menu=new Menu();
        try {
            String query="SELECT * FROM menu WHERE menu_id=?";
            PreparedStatement st=connection.prepareStatement(query);
            st.setInt(1,id);
            ResultSet rs=st.executeQuery();
            if (rs.next()){
                menu.setId(rs.getInt("menu_id"));
                menu.setDoner(rs.getString("Doner"));
                menu.setPrice(rs.getInt("price"));
            }
            else return null;
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return menu;

    }

    public void Update(Menu menu){
        try {
            String query="UPDATE menu SET doner=? , price=? WHERE menu_id=?;";

            PreparedStatement st=connection.prepareStatement(query);

            st.setString(1,menu.getDoner());
            st.setInt(2,menu.getPrice());
            st.setInt(3,menu.getId());

            st.execute();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
