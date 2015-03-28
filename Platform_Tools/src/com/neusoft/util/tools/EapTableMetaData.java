package com.neusoft.util.tools;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.tool.hbm2ddl.TableMetadata;
import org.hibernate.util.StringHelper;

/**
 * JDBC table metadata
 * 
 * @author Christoph Sturm
 */
public class EapTableMetaData {

	private static final Log log = LogFactory.getLog(TableMetadata.class);

	private final String catalog;
	private final String schema;
	private final String name;
	private final List<EapColumnMetadata> columnMetaData = new ArrayList<EapColumnMetadata>();
	private final Map columName = new HashMap() ;
	/**
	 * 
	 * @param rs
	 * @param meta
	 * @param extras
	 * @throws SQLException
	 */
	EapTableMetaData(ResultSet rs, DatabaseMetaData meta, boolean extras)
			throws SQLException {
		catalog = rs.getString("TABLE_CAT");
		schema = rs.getString("TABLE_SCHEM");
		name = rs.getString("TABLE_NAME");
		initColumns(meta);
	}
	/**
	 * 
	 * @param tablename
	 * @param meta
	 * @param extras
	 * @throws SQLException
	 */
	EapTableMetaData(String tableName , String tableCatalog , String tableSchema, ResultSetMetaData meta, boolean extras)
			throws SQLException {
		catalog = tableCatalog;
		schema = tableSchema;
		name = tableName;
		initColumns(meta);
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return "TableMetadata(" + name + ')';
	}

	public List<EapColumnMetadata> getColumnMetadatas() {
		return columnMetaData;
	}

	/**
	 * 
	 * @param rs
	 * @throws SQLException
	 */
	public void addColumn(ResultSet rs) throws SQLException {
		String column = rs.getString("COLUMN_NAME");

		if (column == null)
			return;

		if (columName.get(column) == null) {
			EapColumnMetadata info = new EapColumnMetadata(rs);
			columnMetaData.add(info) ;
			columName.put(info.getName().toLowerCase(),"");
		}
	}
	/**
	 * 
	 * @param rs
	 * @throws SQLException
	 */
	public void addSqlColumn(String name , String typeName , int typeCode) throws SQLException {

		if (name == null)
			return;

		if (columName.get(name) == null) {
			EapColumnMetadata info = new EapColumnMetadata(name , typeName , typeCode);
			columnMetaData.add(info) ;
			columName.put(info.getName().toLowerCase(),"");
		}
	}
	/**
	 * 
	 * @param meta
	 * @throws SQLException
	 */
	private void initColumns(DatabaseMetaData meta) throws SQLException {
		ResultSet rs = null;

		try {
			if (meta.storesUpperCaseIdentifiers()) {
				rs = meta.getColumns(StringHelper.toUpperCase(catalog),
						StringHelper.toUpperCase(schema), StringHelper
								.toUpperCase(name), "%");
			} else if (meta.storesLowerCaseIdentifiers()) {
				rs = meta.getColumns(StringHelper.toLowerCase(catalog),
						StringHelper.toLowerCase(schema), StringHelper
								.toLowerCase(name), "%");
			} else {
				rs = meta.getColumns(catalog, schema, name, "%");
			}
			while (rs.next())
				addColumn(rs);
		}catch(Exception ex){
			ex.printStackTrace();
		}finally {
			if (rs != null)
				rs.close();
		}
	}
	/**
	 * 
	 * @param meta
	 * @throws SQLException
	 */
	private void initColumns(ResultSetMetaData meta) throws SQLException {
		for(int i=1 ; i<=meta.getColumnCount(); i++){
			addSqlColumn(meta.getColumnName(i) , meta.getColumnTypeName(i) , meta.getColumnType(i));
		}
	}

}
