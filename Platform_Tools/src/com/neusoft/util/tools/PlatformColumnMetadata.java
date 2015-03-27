/**
 * 
 */
package com.neusoft.util.tools;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.StringTokenizer;

/**
 * @author iceworld
 *
 */
public class PlatformColumnMetadata{
	private boolean pk = false;
	private String name;
    private String typeName;
    private int columnSize;
    private int decimalDigits;
    private String isNullable;
    private int typeCode;

    PlatformColumnMetadata(ResultSet rs) throws SQLException {
            name = rs.getString("COLUMN_NAME");
            columnSize = rs.getInt("COLUMN_SIZE");
            decimalDigits = rs.getInt("DECIMAL_DIGITS");
            isNullable = rs.getString("IS_NULLABLE");
            typeCode = rs.getInt("DATA_TYPE");
            StringTokenizer typeNameStr = new StringTokenizer( rs.getString("TYPE_NAME"), "() " ) ;
            if(typeNameStr.hasMoreTokens()){
            	typeName = typeNameStr.nextToken();
            }
    }
    
    PlatformColumnMetadata(String name , String typeName , int typeCode) throws SQLException {
        this.name = name ;
        this.typeCode = typeCode;
        this.typeName = typeName;
}

    public String getName() {
            return name;
    }

    public String getTypeName() {
            return typeName;
    }

    public int getColumnSize() {
            return columnSize;
    }

    public int getDecimalDigits() {
            return decimalDigits;
    }

    public String getNullable() {
            return isNullable;
    }

    public String toString() {
            return "ColumnMetadata(" + name + ')';
    }

    public int getTypeCode() {
            return typeCode;
    }

	public boolean isPk() {
		return pk;
	}

	public void setPk(boolean pk) {
		this.pk = pk;
	}

}
