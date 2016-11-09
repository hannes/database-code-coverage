package wrappers;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;

public class PreparedWrapper implements PreparedStatement {
  private PreparedStatement wrapped;

  public PreparedWrapper(PreparedStatement prepareStatement) {
    wrapped = prepareStatement;
  }

  // HM: this is the important method here.

  public ResultSet executeQuery(String sql) throws SQLException {
    return new ResultWrapper(wrapped.executeQuery(sql));
  }

  public ResultSet executeQuery() throws SQLException {
    return new ResultWrapper(wrapped.executeQuery());
  }

  // boilerplate below

  public <T> T unwrap(Class<T> iface) throws SQLException {
    return wrapped.unwrap(iface);
  }

  public boolean execute(String sql) throws SQLException {
    return wrapped.execute(sql);
  }

  public boolean isWrapperFor(Class<?> iface) throws SQLException {
    return wrapped.isWrapperFor(iface);
  }

  public int executeUpdate(String sql) throws SQLException {
    return wrapped.executeUpdate(sql);
  }

  public int executeUpdate() throws SQLException {
    return wrapped.executeUpdate();
  }

  public void setNull(int parameterIndex, int sqlType) throws SQLException {
    wrapped.setNull(parameterIndex, sqlType);
  }

  public void close() throws SQLException {
    wrapped.close();
  }

  public int getMaxFieldSize() throws SQLException {
    return wrapped.getMaxFieldSize();
  }

  public void setBoolean(int parameterIndex, boolean x) throws SQLException {
    wrapped.setBoolean(parameterIndex, x);
  }

  public void setByte(int parameterIndex, byte x) throws SQLException {
    wrapped.setByte(parameterIndex, x);
  }

  public void setMaxFieldSize(int max) throws SQLException {
    wrapped.setMaxFieldSize(max);
  }

  public void setShort(int parameterIndex, short x) throws SQLException {
    wrapped.setShort(parameterIndex, x);
  }

  public int getMaxRows() throws SQLException {
    return wrapped.getMaxRows();
  }

  public void setInt(int parameterIndex, int x) throws SQLException {
    wrapped.setInt(parameterIndex, x);
  }

  public void setMaxRows(int max) throws SQLException {
    wrapped.setMaxRows(max);
  }

  public void setLong(int parameterIndex, long x) throws SQLException {
    wrapped.setLong(parameterIndex, x);
  }

  public void setFloat(int parameterIndex, float x) throws SQLException {
    wrapped.setFloat(parameterIndex, x);
  }

  public void setEscapeProcessing(boolean enable) throws SQLException {
    wrapped.setEscapeProcessing(enable);
  }

  public void setDouble(int parameterIndex, double x) throws SQLException {
    wrapped.setDouble(parameterIndex, x);
  }

  public int getQueryTimeout() throws SQLException {
    return wrapped.getQueryTimeout();
  }

  public void setBigDecimal(int parameterIndex, BigDecimal x)
      throws SQLException {
    wrapped.setBigDecimal(parameterIndex, x);
  }

  public void setQueryTimeout(int seconds) throws SQLException {
    wrapped.setQueryTimeout(seconds);
  }

  public void setString(int parameterIndex, String x) throws SQLException {
    wrapped.setString(parameterIndex, x);
  }

  public void setBytes(int parameterIndex, byte[] x) throws SQLException {
    wrapped.setBytes(parameterIndex, x);
  }

  public void cancel() throws SQLException {
    wrapped.cancel();
  }

  public void setDate(int parameterIndex, Date x) throws SQLException {
    wrapped.setDate(parameterIndex, x);
  }

  public SQLWarning getWarnings() throws SQLException {
    return wrapped.getWarnings();
  }

  public void setTime(int parameterIndex, Time x) throws SQLException {
    wrapped.setTime(parameterIndex, x);
  }

  public void setTimestamp(int parameterIndex, Timestamp x)
      throws SQLException {
    wrapped.setTimestamp(parameterIndex, x);
  }

  public void clearWarnings() throws SQLException {
    wrapped.clearWarnings();
  }

  public void setCursorName(String name) throws SQLException {
    wrapped.setCursorName(name);
  }

  public void setAsciiStream(int parameterIndex, InputStream x, int length)
      throws SQLException {
    wrapped.setAsciiStream(parameterIndex, x, length);
  }

  @SuppressWarnings("deprecation")
  public void setUnicodeStream(int parameterIndex, InputStream x, int length)
      throws SQLException {
    wrapped.setUnicodeStream(parameterIndex, x, length);
  }

  public void setBinaryStream(int parameterIndex, InputStream x, int length)
      throws SQLException {
    wrapped.setBinaryStream(parameterIndex, x, length);
  }

  public ResultSet getResultSet() throws SQLException {
    return wrapped.getResultSet();
  }

  public int getUpdateCount() throws SQLException {
    return wrapped.getUpdateCount();
  }

  public void clearParameters() throws SQLException {
    wrapped.clearParameters();
  }

  public boolean getMoreResults() throws SQLException {
    return wrapped.getMoreResults();
  }

  public void setObject(int parameterIndex, Object x, int targetSqlType)
      throws SQLException {
    wrapped.setObject(parameterIndex, x, targetSqlType);
  }

  public void setFetchDirection(int direction) throws SQLException {
    wrapped.setFetchDirection(direction);
  }

  public void setObject(int parameterIndex, Object x) throws SQLException {
    wrapped.setObject(parameterIndex, x);
  }

  public int getFetchDirection() throws SQLException {
    return wrapped.getFetchDirection();
  }

  public void setFetchSize(int rows) throws SQLException {
    wrapped.setFetchSize(rows);
  }

  public int getFetchSize() throws SQLException {
    return wrapped.getFetchSize();
  }

  public boolean execute() throws SQLException {
    return wrapped.execute();
  }

  public int getResultSetConcurrency() throws SQLException {
    return wrapped.getResultSetConcurrency();
  }

  public int getResultSetType() throws SQLException {
    return wrapped.getResultSetType();
  }

  public void addBatch(String sql) throws SQLException {
    wrapped.addBatch(sql);
  }

  public void addBatch() throws SQLException {
    wrapped.addBatch();
  }

  public void setCharacterStream(int parameterIndex, Reader reader, int length)
      throws SQLException {
    wrapped.setCharacterStream(parameterIndex, reader, length);
  }

  public void clearBatch() throws SQLException {
    wrapped.clearBatch();
  }

  public int[] executeBatch() throws SQLException {
    return wrapped.executeBatch();
  }

  public void setRef(int parameterIndex, Ref x) throws SQLException {
    wrapped.setRef(parameterIndex, x);
  }

  public void setBlob(int parameterIndex, Blob x) throws SQLException {
    wrapped.setBlob(parameterIndex, x);
  }

  public void setClob(int parameterIndex, Clob x) throws SQLException {
    wrapped.setClob(parameterIndex, x);
  }

  public void setArray(int parameterIndex, Array x) throws SQLException {
    wrapped.setArray(parameterIndex, x);
  }

  public Connection getConnection() throws SQLException {
    return wrapped.getConnection();
  }

  public ResultSetMetaData getMetaData() throws SQLException {
    return wrapped.getMetaData();
  }

  public void setDate(int parameterIndex, Date x, Calendar cal)
      throws SQLException {
    wrapped.setDate(parameterIndex, x, cal);
  }

  public boolean getMoreResults(int current) throws SQLException {
    return wrapped.getMoreResults(current);
  }

  public void setTime(int parameterIndex, Time x, Calendar cal)
      throws SQLException {
    wrapped.setTime(parameterIndex, x, cal);
  }

  public ResultSet getGeneratedKeys() throws SQLException {
    return wrapped.getGeneratedKeys();
  }

  public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal)
      throws SQLException {
    wrapped.setTimestamp(parameterIndex, x, cal);
  }

  public int executeUpdate(String sql, int autoGeneratedKeys)
      throws SQLException {
    return wrapped.executeUpdate(sql, autoGeneratedKeys);
  }

  public void setNull(int parameterIndex, int sqlType, String typeName)
      throws SQLException {
    wrapped.setNull(parameterIndex, sqlType, typeName);
  }

  public int executeUpdate(String sql, int[] columnIndexes)
      throws SQLException {
    return wrapped.executeUpdate(sql, columnIndexes);
  }

  public void setURL(int parameterIndex, URL x) throws SQLException {
    wrapped.setURL(parameterIndex, x);
  }

  public ParameterMetaData getParameterMetaData() throws SQLException {
    return wrapped.getParameterMetaData();
  }

  public void setRowId(int parameterIndex, RowId x) throws SQLException {
    wrapped.setRowId(parameterIndex, x);
  }

  public int executeUpdate(String sql, String[] columnNames)
      throws SQLException {
    return wrapped.executeUpdate(sql, columnNames);
  }

  public void setNString(int parameterIndex, String value) throws SQLException {
    wrapped.setNString(parameterIndex, value);
  }

  public void setNCharacterStream(int parameterIndex, Reader value, long length)
      throws SQLException {
    wrapped.setNCharacterStream(parameterIndex, value, length);
  }

  public boolean execute(String sql, int autoGeneratedKeys)
      throws SQLException {
    return wrapped.execute(sql, autoGeneratedKeys);
  }

  public void setNClob(int parameterIndex, NClob value) throws SQLException {
    wrapped.setNClob(parameterIndex, value);
  }

  public void setClob(int parameterIndex, Reader reader, long length)
      throws SQLException {
    wrapped.setClob(parameterIndex, reader, length);
  }

  public boolean execute(String sql, int[] columnIndexes) throws SQLException {
    return wrapped.execute(sql, columnIndexes);
  }

  public void setBlob(int parameterIndex, InputStream inputStream, long length)
      throws SQLException {
    wrapped.setBlob(parameterIndex, inputStream, length);
  }

  public void setNClob(int parameterIndex, Reader reader, long length)
      throws SQLException {
    wrapped.setNClob(parameterIndex, reader, length);
  }

  public boolean execute(String sql, String[] columnNames) throws SQLException {
    return wrapped.execute(sql, columnNames);
  }

  public void setSQLXML(int parameterIndex, SQLXML xmlObject)
      throws SQLException {
    wrapped.setSQLXML(parameterIndex, xmlObject);
  }

  public void setObject(int parameterIndex, Object x, int targetSqlType,
      int scaleOrLength) throws SQLException {
    wrapped.setObject(parameterIndex, x, targetSqlType, scaleOrLength);
  }

  public int getResultSetHoldability() throws SQLException {
    return wrapped.getResultSetHoldability();
  }

  public boolean isClosed() throws SQLException {
    return wrapped.isClosed();
  }

  public void setPoolable(boolean poolable) throws SQLException {
    wrapped.setPoolable(poolable);
  }

  public void setAsciiStream(int parameterIndex, InputStream x, long length)
      throws SQLException {
    wrapped.setAsciiStream(parameterIndex, x, length);
  }

  public boolean isPoolable() throws SQLException {
    return wrapped.isPoolable();
  }

  public void closeOnCompletion() throws SQLException {
    wrapped.closeOnCompletion();
  }

  public void setBinaryStream(int parameterIndex, InputStream x, long length)
      throws SQLException {
    wrapped.setBinaryStream(parameterIndex, x, length);
  }

  public boolean isCloseOnCompletion() throws SQLException {
    return wrapped.isCloseOnCompletion();
  }

  public void setCharacterStream(int parameterIndex, Reader reader, long length)
      throws SQLException {
    wrapped.setCharacterStream(parameterIndex, reader, length);
  }

  public void setAsciiStream(int parameterIndex, InputStream x)
      throws SQLException {
    wrapped.setAsciiStream(parameterIndex, x);
  }

  public void setBinaryStream(int parameterIndex, InputStream x)
      throws SQLException {
    wrapped.setBinaryStream(parameterIndex, x);
  }

  public void setCharacterStream(int parameterIndex, Reader reader)
      throws SQLException {
    wrapped.setCharacterStream(parameterIndex, reader);
  }

  public void setNCharacterStream(int parameterIndex, Reader value)
      throws SQLException {
    wrapped.setNCharacterStream(parameterIndex, value);
  }

  public void setClob(int parameterIndex, Reader reader) throws SQLException {
    wrapped.setClob(parameterIndex, reader);
  }

  public void setBlob(int parameterIndex, InputStream inputStream)
      throws SQLException {
    wrapped.setBlob(parameterIndex, inputStream);
  }

  public void setNClob(int parameterIndex, Reader reader) throws SQLException {
    wrapped.setNClob(parameterIndex, reader);
  }

}
