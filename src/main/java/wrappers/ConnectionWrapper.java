package wrappers;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.hsqldb.jdbc.JDBCConnection;
import org.hsqldb.persist.HsqlProperties;

public class ConnectionWrapper extends JDBCConnection {

  public ConnectionWrapper(HsqlProperties props) throws SQLException {
    super(props);
  }

  public Statement createStatement() throws SQLException {
    return new StatementWrapper(super.createStatement());
  }

  public PreparedStatement prepareStatement(String sql) throws SQLException {
    return new PreparedWrapper(super.prepareStatement(sql));
  }
}
