package wrappers;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

public class ResultWrapper implements ResultSet {
  public static boolean PRETEND_UNKNOWN_FIELD = false;

  private CachedRowSet wrappedResult = null;

  public static boolean NEXT_THROW_EXCEPTION = false;
  public static boolean CHECK_ALL_FETCHED = false;

  public int nextInv = 0;

  public ResultWrapper(ResultSet rs) {
    try {
      wrappedResult = RowSetProvider.newFactory().createCachedRowSet();
      wrappedResult.populate(rs);

      // wrappedResult.
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  public boolean next() throws SQLException {
    if (NEXT_THROW_EXCEPTION) {
      throw new RuntimeException("THROW_EXCEPTION_NEXT");
    }
    nextInv++;
    return wrappedResult.next();
  }

  public void close() throws SQLException {
    if (CHECK_ALL_FETCHED) {
      if (nextInv < wrappedResult.size()) {
        throw new RuntimeException("Not all rows were fetched! "
            + wrappedResult.size() + " vs " + nextInv);
      }
    }
    wrappedResult.close();
  }

  public String getString(int columnIndex) throws SQLException {
    return wrappedResult.getString(columnIndex);
  }

  public boolean getBoolean(int columnIndex) throws SQLException {
    return wrappedResult.getBoolean(columnIndex);
  }

  public byte getByte(int columnIndex) throws SQLException {
    return wrappedResult.getByte(columnIndex);
  }

  public short getShort(int columnIndex) throws SQLException {
    return wrappedResult.getShort(columnIndex);
  }

  public int getInt(int columnIndex) throws SQLException {
    return wrappedResult.getInt(columnIndex);
  }

  public long getLong(int columnIndex) throws SQLException {
    return wrappedResult.getLong(columnIndex);
  }

  public float getFloat(int columnIndex) throws SQLException {
    return wrappedResult.getFloat(columnIndex);
  }

  public double getDouble(int columnIndex) throws SQLException {
    return wrappedResult.getDouble(columnIndex);
  }

  public BigDecimal getBigDecimal(int columnIndex, int scale)
      throws SQLException {
    return wrappedResult.getBigDecimal(columnIndex, scale);
  }

  public byte[] getBytes(int columnIndex) throws SQLException {
    return wrappedResult.getBytes(columnIndex);
  }

  public Date getDate(int columnIndex) throws SQLException {
    return wrappedResult.getDate(columnIndex);
  }

  public Time getTime(int columnIndex) throws SQLException {
    return wrappedResult.getTime(columnIndex);
  }

  public Timestamp getTimestamp(int columnIndex) throws SQLException {
    return wrappedResult.getTimestamp(columnIndex);
  }

  public InputStream getAsciiStream(int columnIndex) throws SQLException {
    return wrappedResult.getAsciiStream(columnIndex);
  }

  public InputStream getUnicodeStream(int columnIndex) throws SQLException {
    return wrappedResult.getUnicodeStream(columnIndex);
  }

  public InputStream getBinaryStream(int columnIndex) throws SQLException {
    return wrappedResult.getBinaryStream(columnIndex);
  }

  public String getString(String columnLabel) throws SQLException {
    if (PRETEND_UNKNOWN_FIELD) {
      throw new SQLException("You asked me not to know a field");
    }
    return wrappedResult.getString(columnLabel);
  }

  public boolean getBoolean(String columnLabel) throws SQLException {
    return wrappedResult.getBoolean(columnLabel);
  }

  public byte getByte(String columnLabel) throws SQLException {
    return wrappedResult.getByte(columnLabel);
  }

  public short getShort(String columnLabel) throws SQLException {
    return wrappedResult.getShort(columnLabel);
  }

  public int getInt(String columnLabel) throws SQLException {
    return wrappedResult.getInt(columnLabel);
  }

  public long getLong(String columnLabel) throws SQLException {
    return wrappedResult.getLong(columnLabel);
  }

  public float getFloat(String columnLabel) throws SQLException {
    return wrappedResult.getFloat(columnLabel);
  }

  public double getDouble(String columnLabel) throws SQLException {
    return wrappedResult.getDouble(columnLabel);
  }

  public BigDecimal getBigDecimal(String columnLabel, int scale)
      throws SQLException {
    return wrappedResult.getBigDecimal(columnLabel, scale);
  }

  public byte[] getBytes(String columnLabel) throws SQLException {
    return wrappedResult.getBytes(columnLabel);
  }

  public Date getDate(String columnLabel) throws SQLException {
    return wrappedResult.getDate(columnLabel);
  }

  public Time getTime(String columnLabel) throws SQLException {
    return wrappedResult.getTime(columnLabel);
  }

  public Timestamp getTimestamp(String columnLabel) throws SQLException {
    return wrappedResult.getTimestamp(columnLabel);
  }

  public InputStream getAsciiStream(String columnLabel) throws SQLException {
    return wrappedResult.getAsciiStream(columnLabel);
  }

  public InputStream getUnicodeStream(String columnLabel) throws SQLException {
    return wrappedResult.getUnicodeStream(columnLabel);
  }

  public InputStream getBinaryStream(String columnLabel) throws SQLException {
    return wrappedResult.getBinaryStream(columnLabel);
  }

  public SQLWarning getWarnings() throws SQLException {
    return wrappedResult.getWarnings();
  }

  public void clearWarnings() throws SQLException {
    wrappedResult.clearWarnings();
  }

  public String getCursorName() throws SQLException {
    return wrappedResult.getCursorName();
  }

  public ResultSetMetaData getMetaData() throws SQLException {
    return wrappedResult.getMetaData();
  }

  public Object getObject(int columnIndex) throws SQLException {
    return wrappedResult.getObject(columnIndex);
  }

  public Object getObject(String columnLabel) throws SQLException {
    return wrappedResult.getObject(columnLabel);
  }

  public int findColumn(String columnLabel) throws SQLException {
    return wrappedResult.findColumn(columnLabel);
  }

  public Reader getCharacterStream(int columnIndex) throws SQLException {
    return wrappedResult.getCharacterStream(columnIndex);
  }

  public Reader getCharacterStream(String columnLabel) throws SQLException {
    return wrappedResult.getCharacterStream(columnLabel);
  }

  public BigDecimal getBigDecimal(int columnIndex) throws SQLException {
    return wrappedResult.getBigDecimal(columnIndex);
  }

  public BigDecimal getBigDecimal(String columnLabel) throws SQLException {
    return wrappedResult.getBigDecimal(columnLabel);
  }

  public boolean isBeforeFirst() throws SQLException {
    return wrappedResult.isBeforeFirst();
  }

  public boolean isAfterLast() throws SQLException {
    return wrappedResult.isAfterLast();
  }

  public boolean isFirst() throws SQLException {
    return wrappedResult.isFirst();
  }

  public boolean isLast() throws SQLException {
    return wrappedResult.isLast();
  }

  public void beforeFirst() throws SQLException {
    wrappedResult.beforeFirst();
  }

  public void afterLast() throws SQLException {
    wrappedResult.afterLast();
  }

  public boolean first() throws SQLException {
    return wrappedResult.first();
  }

  public boolean last() throws SQLException {
    return wrappedResult.last();
  }

  public int getRow() throws SQLException {
    return wrappedResult.getRow();
  }

  public boolean absolute(int row) throws SQLException {
    return wrappedResult.absolute(row);
  }

  public boolean relative(int rows) throws SQLException {
    return wrappedResult.relative(rows);
  }

  public boolean previous() throws SQLException {
    return wrappedResult.previous();
  }

  public void setFetchDirection(int direction) throws SQLException {
    wrappedResult.setFetchDirection(direction);
  }

  public int getFetchDirection() throws SQLException {
    return wrappedResult.getFetchDirection();
  }

  public void setFetchSize(int rows) throws SQLException {
    wrappedResult.setFetchSize(rows);
  }

  public int getFetchSize() throws SQLException {
    return wrappedResult.getFetchSize();
  }

  public int getType() throws SQLException {
    return wrappedResult.getType();
  }

  public int getConcurrency() throws SQLException {
    return wrappedResult.getConcurrency();
  }

  public boolean rowUpdated() throws SQLException {
    return wrappedResult.rowUpdated();
  }

  public boolean rowInserted() throws SQLException {
    return wrappedResult.rowInserted();
  }

  public boolean rowDeleted() throws SQLException {
    return wrappedResult.rowDeleted();
  }

  public void updateNull(int columnIndex) throws SQLException {
    wrappedResult.updateNull(columnIndex);
  }

  public void updateBoolean(int columnIndex, boolean x) throws SQLException {
    wrappedResult.updateBoolean(columnIndex, x);
  }

  public void updateByte(int columnIndex, byte x) throws SQLException {
    wrappedResult.updateByte(columnIndex, x);
  }

  public void updateShort(int columnIndex, short x) throws SQLException {
    wrappedResult.updateShort(columnIndex, x);
  }

  public void updateInt(int columnIndex, int x) throws SQLException {
    wrappedResult.updateInt(columnIndex, x);
  }

  public void updateLong(int columnIndex, long x) throws SQLException {
    wrappedResult.updateLong(columnIndex, x);
  }

  public void updateFloat(int columnIndex, float x) throws SQLException {
    wrappedResult.updateFloat(columnIndex, x);
  }

  public void updateDouble(int columnIndex, double x) throws SQLException {
    wrappedResult.updateDouble(columnIndex, x);
  }

  public void updateBigDecimal(int columnIndex, BigDecimal x)
      throws SQLException {
    wrappedResult.updateBigDecimal(columnIndex, x);
  }

  public void updateString(int columnIndex, String x) throws SQLException {
    wrappedResult.updateString(columnIndex, x);
  }

  public void updateBytes(int columnIndex, byte[] x) throws SQLException {
    wrappedResult.updateBytes(columnIndex, x);
  }

  public void updateDate(int columnIndex, Date x) throws SQLException {
    wrappedResult.updateDate(columnIndex, x);
  }

  public void updateTime(int columnIndex, Time x) throws SQLException {
    wrappedResult.updateTime(columnIndex, x);
  }

  public void updateTimestamp(int columnIndex, Timestamp x)
      throws SQLException {
    wrappedResult.updateTimestamp(columnIndex, x);
  }

  public void updateAsciiStream(int columnIndex, InputStream x, int length)
      throws SQLException {
    wrappedResult.updateAsciiStream(columnIndex, x, length);
  }

  public void updateBinaryStream(int columnIndex, InputStream x, int length)
      throws SQLException {
    wrappedResult.updateBinaryStream(columnIndex, x, length);
  }

  public void updateCharacterStream(int columnIndex, Reader x, int length)
      throws SQLException {
    wrappedResult.updateCharacterStream(columnIndex, x, length);
  }

  public void updateObject(int columnIndex, Object x, int scaleOrLength)
      throws SQLException {
    wrappedResult.updateObject(columnIndex, x, scaleOrLength);
  }

  public void updateObject(int columnIndex, Object x) throws SQLException {
    wrappedResult.updateObject(columnIndex, x);
  }

  public void updateNull(String columnLabel) throws SQLException {
    wrappedResult.updateNull(columnLabel);
  }

  public void updateBoolean(String columnLabel, boolean x) throws SQLException {
    wrappedResult.updateBoolean(columnLabel, x);
  }

  public void updateByte(String columnLabel, byte x) throws SQLException {
    wrappedResult.updateByte(columnLabel, x);
  }

  public void updateShort(String columnLabel, short x) throws SQLException {
    wrappedResult.updateShort(columnLabel, x);
  }

  public void updateInt(String columnLabel, int x) throws SQLException {
    wrappedResult.updateInt(columnLabel, x);
  }

  public void updateLong(String columnLabel, long x) throws SQLException {
    wrappedResult.updateLong(columnLabel, x);
  }

  public void updateFloat(String columnLabel, float x) throws SQLException {
    wrappedResult.updateFloat(columnLabel, x);
  }

  public void updateDouble(String columnLabel, double x) throws SQLException {
    wrappedResult.updateDouble(columnLabel, x);
  }

  public void updateBigDecimal(String columnLabel, BigDecimal x)
      throws SQLException {
    wrappedResult.updateBigDecimal(columnLabel, x);
  }

  public void updateString(String columnLabel, String x) throws SQLException {
    wrappedResult.updateString(columnLabel, x);
  }

  public void updateBytes(String columnLabel, byte[] x) throws SQLException {
    wrappedResult.updateBytes(columnLabel, x);
  }

  public void updateDate(String columnLabel, Date x) throws SQLException {
    wrappedResult.updateDate(columnLabel, x);
  }

  public void updateTime(String columnLabel, Time x) throws SQLException {
    wrappedResult.updateTime(columnLabel, x);
  }

  public void updateTimestamp(String columnLabel, Timestamp x)
      throws SQLException {
    wrappedResult.updateTimestamp(columnLabel, x);
  }

  public void updateAsciiStream(String columnLabel, InputStream x, int length)
      throws SQLException {
    wrappedResult.updateAsciiStream(columnLabel, x, length);
  }

  public void updateBinaryStream(String columnLabel, InputStream x, int length)
      throws SQLException {
    wrappedResult.updateBinaryStream(columnLabel, x, length);
  }

  public void updateCharacterStream(String columnLabel, Reader reader,
      int length) throws SQLException {
    wrappedResult.updateCharacterStream(columnLabel, reader, length);
  }

  public void updateObject(String columnLabel, Object x, int scaleOrLength)
      throws SQLException {
    wrappedResult.updateObject(columnLabel, x, scaleOrLength);
  }

  public void updateObject(String columnLabel, Object x) throws SQLException {
    wrappedResult.updateObject(columnLabel, x);
  }

  public void insertRow() throws SQLException {
    wrappedResult.insertRow();
  }

  public void updateRow() throws SQLException {
    wrappedResult.updateRow();
  }

  public void deleteRow() throws SQLException {
    wrappedResult.deleteRow();
  }

  public void refreshRow() throws SQLException {
    wrappedResult.refreshRow();
  }

  public void cancelRowUpdates() throws SQLException {
    wrappedResult.cancelRowUpdates();
  }

  public void moveToInsertRow() throws SQLException {
    wrappedResult.moveToInsertRow();
  }

  public void moveToCurrentRow() throws SQLException {
    wrappedResult.moveToCurrentRow();
  }

  public Statement getStatement() throws SQLException {
    return wrappedResult.getStatement();
  }

  public Object getObject(int columnIndex, Map<String, Class<?>> map)
      throws SQLException {
    return wrappedResult.getObject(columnIndex, map);
  }

  public Ref getRef(int columnIndex) throws SQLException {
    return wrappedResult.getRef(columnIndex);
  }

  public Blob getBlob(int columnIndex) throws SQLException {
    return wrappedResult.getBlob(columnIndex);
  }

  public Clob getClob(int columnIndex) throws SQLException {
    return wrappedResult.getClob(columnIndex);
  }

  public Array getArray(int columnIndex) throws SQLException {
    return wrappedResult.getArray(columnIndex);
  }

  public Object getObject(String columnLabel, Map<String, Class<?>> map)
      throws SQLException {
    return wrappedResult.getObject(columnLabel, map);
  }

  public Ref getRef(String columnLabel) throws SQLException {
    return wrappedResult.getRef(columnLabel);
  }

  public Blob getBlob(String columnLabel) throws SQLException {
    return wrappedResult.getBlob(columnLabel);
  }

  public Clob getClob(String columnLabel) throws SQLException {
    return wrappedResult.getClob(columnLabel);
  }

  public Array getArray(String columnLabel) throws SQLException {
    return wrappedResult.getArray(columnLabel);
  }

  public Date getDate(int columnIndex, Calendar cal) throws SQLException {
    return wrappedResult.getDate(columnIndex, cal);
  }

  public Date getDate(String columnLabel, Calendar cal) throws SQLException {
    return wrappedResult.getDate(columnLabel, cal);
  }

  public Time getTime(int columnIndex, Calendar cal) throws SQLException {
    return wrappedResult.getTime(columnIndex, cal);
  }

  public Time getTime(String columnLabel, Calendar cal) throws SQLException {
    return wrappedResult.getTime(columnLabel, cal);
  }

  public Timestamp getTimestamp(int columnIndex, Calendar cal)
      throws SQLException {
    return wrappedResult.getTimestamp(columnIndex, cal);
  }

  public Timestamp getTimestamp(String columnLabel, Calendar cal)
      throws SQLException {
    return wrappedResult.getTimestamp(columnLabel, cal);
  }

  public URL getURL(int columnIndex) throws SQLException {
    return wrappedResult.getURL(columnIndex);
  }

  public URL getURL(String columnLabel) throws SQLException {
    return wrappedResult.getURL(columnLabel);
  }

  public void updateRef(int columnIndex, Ref x) throws SQLException {
    wrappedResult.updateRef(columnIndex, x);
  }

  public void updateRef(String columnLabel, Ref x) throws SQLException {
    wrappedResult.updateRef(columnLabel, x);
  }

  public void updateBlob(int columnIndex, Blob x) throws SQLException {
    wrappedResult.updateBlob(columnIndex, x);
  }

  public void updateBlob(String columnLabel, Blob x) throws SQLException {
    wrappedResult.updateBlob(columnLabel, x);
  }

  public void updateClob(int columnIndex, Clob x) throws SQLException {
    wrappedResult.updateClob(columnIndex, x);
  }

  public void updateClob(String columnLabel, Clob x) throws SQLException {
    wrappedResult.updateClob(columnLabel, x);
  }

  public void updateArray(int columnIndex, Array x) throws SQLException {
    wrappedResult.updateArray(columnIndex, x);
  }

  public void updateArray(String columnLabel, Array x) throws SQLException {
    wrappedResult.updateArray(columnLabel, x);
  }

  public RowId getRowId(int columnIndex) throws SQLException {
    return wrappedResult.getRowId(columnIndex);
  }

  public RowId getRowId(String columnLabel) throws SQLException {
    return wrappedResult.getRowId(columnLabel);
  }

  public void updateRowId(int columnIndex, RowId x) throws SQLException {
    wrappedResult.updateRowId(columnIndex, x);
  }

  public void updateRowId(String columnLabel, RowId x) throws SQLException {
    wrappedResult.updateRowId(columnLabel, x);
  }

  public int getHoldability() throws SQLException {
    return wrappedResult.getHoldability();
  }

  public boolean isClosed() throws SQLException {
    return wrappedResult.isClosed();
  }

  public void updateNString(int columnIndex, String nString)
      throws SQLException {
    wrappedResult.updateNString(columnIndex, nString);
  }

  public void updateNString(String columnLabel, String nString)
      throws SQLException {
    wrappedResult.updateNString(columnLabel, nString);
  }

  public void updateNClob(int columnIndex, NClob nClob) throws SQLException {
    wrappedResult.updateNClob(columnIndex, nClob);
  }

  public void updateNClob(String columnLabel, NClob nClob) throws SQLException {
    wrappedResult.updateNClob(columnLabel, nClob);
  }

  public NClob getNClob(int columnIndex) throws SQLException {
    return wrappedResult.getNClob(columnIndex);
  }

  public NClob getNClob(String columnLabel) throws SQLException {
    return wrappedResult.getNClob(columnLabel);
  }

  public SQLXML getSQLXML(int columnIndex) throws SQLException {
    return wrappedResult.getSQLXML(columnIndex);
  }

  public SQLXML getSQLXML(String columnLabel) throws SQLException {
    return wrappedResult.getSQLXML(columnLabel);
  }

  public void updateSQLXML(int columnIndex, SQLXML xmlObject)
      throws SQLException {
    wrappedResult.updateSQLXML(columnIndex, xmlObject);
  }

  public void updateSQLXML(String columnLabel, SQLXML xmlObject)
      throws SQLException {
    wrappedResult.updateSQLXML(columnLabel, xmlObject);
  }

  public String getNString(int columnIndex) throws SQLException {
    return wrappedResult.getNString(columnIndex);
  }

  public String getNString(String columnLabel) throws SQLException {
    return wrappedResult.getNString(columnLabel);
  }

  public Reader getNCharacterStream(int columnIndex) throws SQLException {
    return wrappedResult.getNCharacterStream(columnIndex);
  }

  public Reader getNCharacterStream(String columnLabel) throws SQLException {
    return wrappedResult.getNCharacterStream(columnLabel);
  }

  public void updateNCharacterStream(int columnIndex, Reader x, long length)
      throws SQLException {
    wrappedResult.updateNCharacterStream(columnIndex, x, length);
  }

  public void updateNCharacterStream(String columnLabel, Reader reader,
      long length) throws SQLException {
    wrappedResult.updateNCharacterStream(columnLabel, reader, length);
  }

  public void updateAsciiStream(int columnIndex, InputStream x, long length)
      throws SQLException {
    wrappedResult.updateAsciiStream(columnIndex, x, length);
  }

  public void updateBinaryStream(int columnIndex, InputStream x, long length)
      throws SQLException {
    wrappedResult.updateBinaryStream(columnIndex, x, length);
  }

  public void updateCharacterStream(int columnIndex, Reader x, long length)
      throws SQLException {
    wrappedResult.updateCharacterStream(columnIndex, x, length);
  }

  public void updateAsciiStream(String columnLabel, InputStream x, long length)
      throws SQLException {
    wrappedResult.updateAsciiStream(columnLabel, x, length);
  }

  public void updateBinaryStream(String columnLabel, InputStream x, long length)
      throws SQLException {
    wrappedResult.updateBinaryStream(columnLabel, x, length);
  }

  public void updateCharacterStream(String columnLabel, Reader reader,
      long length) throws SQLException {
    wrappedResult.updateCharacterStream(columnLabel, reader, length);
  }

  public void updateBlob(int columnIndex, InputStream inputStream, long length)
      throws SQLException {
    wrappedResult.updateBlob(columnIndex, inputStream, length);
  }

  public void updateBlob(String columnLabel, InputStream inputStream,
      long length) throws SQLException {
    wrappedResult.updateBlob(columnLabel, inputStream, length);
  }

  public void updateClob(int columnIndex, Reader reader, long length)
      throws SQLException {
    wrappedResult.updateClob(columnIndex, reader, length);
  }

  public void updateClob(String columnLabel, Reader reader, long length)
      throws SQLException {
    wrappedResult.updateClob(columnLabel, reader, length);
  }

  public void updateNClob(int columnIndex, Reader reader, long length)
      throws SQLException {
    wrappedResult.updateNClob(columnIndex, reader, length);
  }

  public void updateNClob(String columnLabel, Reader reader, long length)
      throws SQLException {
    wrappedResult.updateNClob(columnLabel, reader, length);
  }

  public void updateNCharacterStream(int columnIndex, Reader x)
      throws SQLException {
    wrappedResult.updateNCharacterStream(columnIndex, x);
  }

  public void updateNCharacterStream(String columnLabel, Reader reader)
      throws SQLException {
    wrappedResult.updateNCharacterStream(columnLabel, reader);
  }

  public void updateAsciiStream(int columnIndex, InputStream x)
      throws SQLException {
    wrappedResult.updateAsciiStream(columnIndex, x);
  }

  public void updateBinaryStream(int columnIndex, InputStream x)
      throws SQLException {
    wrappedResult.updateBinaryStream(columnIndex, x);
  }

  public void updateCharacterStream(int columnIndex, Reader x)
      throws SQLException {
    wrappedResult.updateCharacterStream(columnIndex, x);
  }

  public void updateAsciiStream(String columnLabel, InputStream x)
      throws SQLException {
    wrappedResult.updateAsciiStream(columnLabel, x);
  }

  public void updateBinaryStream(String columnLabel, InputStream x)
      throws SQLException {
    wrappedResult.updateBinaryStream(columnLabel, x);
  }

  public void updateCharacterStream(String columnLabel, Reader reader)
      throws SQLException {
    wrappedResult.updateCharacterStream(columnLabel, reader);
  }

  public void updateBlob(int columnIndex, InputStream inputStream)
      throws SQLException {
    wrappedResult.updateBlob(columnIndex, inputStream);
  }

  public void updateBlob(String columnLabel, InputStream inputStream)
      throws SQLException {
    wrappedResult.updateBlob(columnLabel, inputStream);
  }

  public void updateClob(int columnIndex, Reader reader) throws SQLException {
    wrappedResult.updateClob(columnIndex, reader);
  }

  public void updateClob(String columnLabel, Reader reader)
      throws SQLException {
    wrappedResult.updateClob(columnLabel, reader);
  }

  public void updateNClob(int columnIndex, Reader reader) throws SQLException {
    wrappedResult.updateNClob(columnIndex, reader);
  }

  public void updateNClob(String columnLabel, Reader reader)
      throws SQLException {
    wrappedResult.updateNClob(columnLabel, reader);
  }

  public <T> T getObject(int columnIndex, Class<T> type) throws SQLException {
    return wrappedResult.getObject(columnIndex, type);
  }

  public <T> T getObject(String columnLabel, Class<T> type)
      throws SQLException {
    return wrappedResult.getObject(columnLabel, type);
  }

  public <T> T unwrap(Class<T> iface) throws SQLException {
    return wrappedResult.unwrap(iface);
  }

  public boolean isWrapperFor(Class<?> iface) throws SQLException {
    return wrappedResult.isWrapperFor(iface);
  }

  public boolean wasNull() throws SQLException {
    return wrappedResult.wasNull();
  }

}
