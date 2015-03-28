
package com.neusoft.util.tools;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.hibernate.util.StringHelper;

public class EapDatabaseMetadata{

	public EapDatabaseMetadata(Connection connection , Properties properties)
			throws SQLException {
        meta = connection.getMetaData();
        this.properties = properties ;
	}
	
	

	private final List<EapTableMetaData> tables = new ArrayList<EapTableMetaData>();
	private DatabaseMetaData meta;
	private Properties properties ;
	private static final String[] TYPES = { "TABLE", "VIEW" };
	/**
	 * 
	 * @return
	 */
	public List<EapTableMetaData> getTables() {
		return this.tables;
	}
	/**
	 * 
	 * @param name
	 * @param schema
	 * @param catalog
	 * @param isQuoted
	 * @return
	 * @throws Exception
	 */
	public List<EapTableMetaData> loadTables(String name, String schema, String catalog,
			boolean isQuoted) throws Exception {
		try {
			if(properties!=null && properties.get("schema")!=null && schema==null){
				schema = properties.get("upcase")!=null?((String)properties.get("schema")).toUpperCase():(String)properties.get("schema") ;
			}
			EapTableMetaData table = null;
			ResultSet rs = null , pkRs = null;
			try {
				if ((isQuoted && meta.storesMixedCaseQuotedIdentifiers())) {
					rs = meta.getTables(catalog, schema, name, TYPES);
				} else if ((isQuoted && meta.storesUpperCaseQuotedIdentifiers())
						|| (!isQuoted && meta.storesUpperCaseIdentifiers())) {
					rs = meta.getTables(StringHelper.toUpperCase(catalog),
							StringHelper.toUpperCase(schema), StringHelper
									.toUpperCase(name), TYPES);
				} else if ((isQuoted && meta.storesLowerCaseQuotedIdentifiers())
						|| (!isQuoted && meta.storesLowerCaseIdentifiers())) {
					rs = meta.getTables(StringHelper.toLowerCase(catalog),
							StringHelper.toLowerCase(schema), StringHelper
									.toLowerCase(name), TYPES);
				} else {
					rs = meta.getTables(catalog, schema, name, TYPES);
				}

				while (rs.next()) {
					String tableName = rs.getString("TABLE_NAME");
					if(tableName.matches("[\\da-zA-Z_-\u4e00-\u9fa5]+")){
						table = new EapTableMetaData(rs, meta, true);
						tables.add(table);
						
						if ((isQuoted && meta.storesMixedCaseQuotedIdentifiers())) {
							pkRs = meta.getPrimaryKeys(catalog, schema, tableName) ;
						} else if ((isQuoted && meta.storesUpperCaseQuotedIdentifiers())
								|| (!isQuoted && meta.storesUpperCaseIdentifiers())) {
							pkRs = meta.getPrimaryKeys(StringHelper.toUpperCase(catalog),
									StringHelper.toUpperCase(schema), StringHelper
									.toUpperCase(tableName)) ;
						} else if ((isQuoted && meta.storesLowerCaseQuotedIdentifiers())
								|| (!isQuoted && meta.storesLowerCaseIdentifiers())) {
							pkRs = meta.getPrimaryKeys(StringHelper.toLowerCase(catalog),
									StringHelper.toLowerCase(schema), StringHelper
									.toLowerCase(tableName)) ;
						} else {
							pkRs = meta.getPrimaryKeys(catalog, schema, tableName) ;
						}
						
						while(pkRs.next()){
							String column = pkRs.getString("COLUMN_NAME") ;
							for(EapColumnMetadata columnMetadata : table.getColumnMetadatas()){
								if(columnMetadata.getName().equalsIgnoreCase(column)){
									columnMetadata.setPk(true) ;
								}
							}
						}
						if(pkRs!=null){
							pkRs.close();
						}
					}
				}

			}catch(Exception ex){
				ex.printStackTrace();
			} finally {
				
				if (rs != null)
					rs.close();
			}
		} catch (SQLException sqle) {
			throw sqle;
		}
		return tables ;
	}
	/**
	 * 
	 * @param name
	 * @param schema
	 * @param catalog
	 * @param isQuoted
	 * @return
	 * @throws Exception
	 */
	public EapTableMetaData loadTable(String name, String schema, String catalog,
			boolean isQuoted) throws Exception {
		EapTableMetaData table = null;
		try {
			if(properties!=null && properties.get("schema")!=null && schema==null){
				schema = (String)properties.get("schema") ;
			}
			ResultSet rs = null;
			try {
				if ((isQuoted && meta.storesMixedCaseQuotedIdentifiers())) {
					rs = meta.getTables(catalog, schema, name, TYPES);
				} else if ((isQuoted && meta.storesUpperCaseQuotedIdentifiers())
						|| (!isQuoted && meta.storesUpperCaseIdentifiers())) {
					rs = meta.getTables(StringHelper.toUpperCase(catalog),
							StringHelper.toUpperCase(schema), StringHelper
									.toUpperCase(name), TYPES);
				} else if ((isQuoted && meta.storesLowerCaseQuotedIdentifiers())
						|| (!isQuoted && meta.storesLowerCaseIdentifiers())) {
					rs = meta.getTables(StringHelper.toLowerCase(catalog),
							StringHelper.toLowerCase(schema), StringHelper
									.toLowerCase(name), TYPES);
				} else {
					rs = meta.getTables(catalog, schema, name, TYPES);
				}

				while (rs.next()) {
					table = new EapTableMetaData(rs, meta, true);
					break ;
				}

			} finally {
				if (rs != null)
					rs.close();
			}
		} catch (SQLException sqle) {
			throw sqle;
		}
		return table ;
	}
	
	/**
	 * 
	 * @param name
	 * @param schema
	 * @param catalog
	 * @param isQuoted
	 * @return
	 * @throws Exception
	 */
	public EapTableMetaData loadSQL(Statement statement ,String datasql, String tableName, String schema, String catalog,
			boolean isQuoted) throws Exception {
		EapTableMetaData table = null;
		if(properties!=null && properties.get("schema")!=null){
			schema = (String)properties.get("schema") ;
		}
		try {
			if(properties!=null && properties.get("schema")!=null && schema==null){
				schema = (String)properties.get("schema") ;
			}
			ResultSet rs = statement.executeQuery(datasql) ;
			try {
				table = new EapTableMetaData(tableName , schema , catalog , rs.getMetaData(), true);
			} finally {
				if (rs != null)
					rs.close();
			}
		} catch (SQLException sqle) {
			throw sqle;
		}
		return table ;
	}


	public static void main(String[] args) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Properties pro = new Properties();
		pro.put("schema", "root") ;
		EapDatabaseMetadata meta = new EapDatabaseMetadata(DriverManager.getConnection("jdbc:mysql://localhost:3306/rivu", "root","123456"),pro);
		meta.loadTables(null , null , null , true);
		System.out.println(meta.tables.size());
		for(EapTableMetaData table:meta.tables) {
			System.out.println(table.getName());
		}
	}

}
