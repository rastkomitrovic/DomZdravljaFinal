/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Rastko
 */
public abstract class AbstractDomainObject implements Serializable{
    public abstract String getTableName();
    public abstract PreparedStatement getInsertQuery(Connection conn) throws SQLException;
    public abstract String getDeleteQuery();
    public abstract String getEditQuery();
    public abstract String getFindByIdQuery();
    public abstract List<AbstractDomainObject> getAll(ResultSet rs);
    public abstract AbstractDomainObject getFindByIdObject(ResultSet rs);
    public abstract void setId(ResultSet generatedKeys) ;
    
}
