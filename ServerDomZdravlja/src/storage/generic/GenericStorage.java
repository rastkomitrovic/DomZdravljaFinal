/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storage.generic;

import domain.AbstractDomainObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import storage.database.DatabaseConnection;

/**
 *
 * @author Rastko
 */
public class GenericStorage {

    public  List<AbstractDomainObject> getAll(AbstractDomainObject ado) throws SQLException {
        List<AbstractDomainObject> list = new LinkedList<>();
        String query = ado.getTableName();
        Connection conn = DatabaseConnection.getInstance().getConnection();
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery(query);
        list = ado.getAll(rs);
        return list;
    }

    public synchronized boolean add(AbstractDomainObject ado) throws SQLException {

        Connection conn = DatabaseConnection.getInstance().getConnection();
        PreparedStatement ps= ado.getInsertQuery(conn);
            if(!ps.execute()){
                ado.setId(ps.getGeneratedKeys());
                conn.commit();
                return true;
            }
        
        conn.rollback();
        return false;

    }

    public synchronized boolean edit(AbstractDomainObject ado) throws SQLException {

        Connection conn = DatabaseConnection.getInstance().getConnection();
        String query = ado.getEditQuery();
        Statement stat = conn.createStatement();
        if (!stat.execute(query)) {
            conn.commit();
            return true;
        }
        conn.rollback();
        return false;

    }

    public synchronized boolean delete(AbstractDomainObject ado) throws SQLException {

        Connection conn = DatabaseConnection.getInstance().getConnection();
        String query = ado.getDeleteQuery();
        Statement stat = conn.createStatement();
        if (!stat.execute(query)) {
            conn.commit();
            return true;
        }
        conn.rollback();
        return false;
    }

    public  AbstractDomainObject findById(AbstractDomainObject ado) throws SQLException {

        AbstractDomainObject result;
        Connection conn = DatabaseConnection.getInstance().getConnection();
        String query = ado.getFindByIdQuery();
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery(query);
        return ado.getFindByIdObject(rs);

    }
}
