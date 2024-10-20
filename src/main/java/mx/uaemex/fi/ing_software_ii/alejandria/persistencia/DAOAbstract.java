package mx.uaemex.fi.ing_software_ii.alejandria.persistencia;

import java.sql.Connection;

public abstract class DAOAbstract {
	protected Connection connection;
    
    public void setConnection(Connection connection){
    	this.connection = connection;
    }
    
}
