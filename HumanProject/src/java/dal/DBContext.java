/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Human;
import model.HumanType;

/**
 *
 * @author AnhVuNAV
 */
public class DBContext {

    Connection connection;

    public DBContext() {
        try {
            String user = "sa";
            String pass = "anhvu2004";
            String url = "jdbc:sqlserver://localhost:1433;databaseName= Human";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public ArrayList<Human> getHumans() {
        ArrayList<Human> humans = new ArrayList<>();
        if (connection == null) {
            System.out.println("Database connection is null.");
            return humans;
        }
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT h.humanid, h.humanname, h.humandob, h.humangender, ht.typeid, ht.typename "
                    + "FROM Human h INNER JOIN HumanType ht ON h.typeid = ht.typeid";
            statement = connection.prepareStatement(sql);
            rs = statement.executeQuery();
            while (rs.next()) {
                Human h = new Human();
                h.setId(rs.getInt("humanid"));
                h.setName(rs.getString("humanname"));
                h.setDob(rs.getDate("humandob"));
                h.setGender(rs.getBoolean("humangender"));

                HumanType ht = new HumanType();
                ht.setTypeId(rs.getInt("typeid"));
                ht.setName(rs.getString("typename"));
                h.setType(ht);

                humans.add(h);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return humans;
    }

    public ArrayList<HumanType> getTypes() {
        ArrayList<HumanType> types = new ArrayList<>();
        if (connection == null) {
            System.out.println("Database connection is null.");
            return types;
        }
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT typeid, typename FROM HumanType";
            statement = connection.prepareStatement(sql);
            rs = statement.executeQuery();
            while (rs.next()) {
                HumanType ht = new HumanType();
                ht.setTypeId(rs.getInt("typeid"));
                ht.setName(rs.getString("typename"));
                types.add(ht);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return types;
    }

    public int getNextId() {
        int nextId = 1;  // Mặc định bắt đầu từ 1
        String sql = "SELECT MAX(humanid) AS max_id FROM Human";
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.prepareStatement(sql);
            rs = statement.executeQuery();
            if (rs.next()) {
                nextId = rs.getInt("max_id") + 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return nextId;
    }

    public void insertHuman(Human human) {
        if (connection == null) {
            System.out.println("Database connection is null.");
            return;
        }
        PreparedStatement statement = null;
        try {
            int nextId = getNextId();  // Lấy ID tiếp theo

            String sql = "INSERT INTO Human (humanid, humanname, humandob, humangender, typeid) VALUES (?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, nextId);  // Đặt ID mới
            statement.setString(2, human.getName());
            statement.setDate(3, (Date) human.getDob());
            statement.setBoolean(4, human.isGender());
            statement.setInt(5, human.getType().getTypeId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Human getHumanById(int id) {
        Human human = null;
        if (connection == null) {
            System.out.println("Database connection is null.");
            return human;
        }
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT h.humanid, h.humanname, h.humandob, h.humangender, ht.typeid, ht.typename "
                    + "FROM Human h INNER JOIN HumanType ht ON h.typeid = ht.typeid WHERE h.humanid = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            rs = statement.executeQuery();
            if (rs.next()) {
                human = new Human();
                human.setId(rs.getInt("humanid"));
                human.setName(rs.getString("humanname"));
                human.setDob(rs.getDate("humandob"));
                human.setGender(rs.getBoolean("humangender"));

                HumanType ht = new HumanType();
                ht.setTypeId(rs.getInt("typeid"));
                ht.setName(rs.getString("typename"));
                human.setType(ht);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return human;
    }

    public void deleteHuman(int id) {
        if (connection == null) {
            System.out.println("Database connection is null.");
            return;
        }
        PreparedStatement statement = null;
        try {
            String sql = "DELETE FROM Human WHERE humanid = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void updateHuman(Human human) {
        if (connection == null) {
            System.out.println("Database connection is null.");
            return;
        }
        PreparedStatement statement = null;
        try {
            String sql = "UPDATE Human SET humanname = ?, humandob = ?, humangender = ?, typeid = ? WHERE humanid = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, human.getName());
            statement.setDate(2, (Date) human.getDob());
            statement.setBoolean(3, human.isGender());
            statement.setInt(4, human.getType().getTypeId());
            statement.setInt(5, human.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
