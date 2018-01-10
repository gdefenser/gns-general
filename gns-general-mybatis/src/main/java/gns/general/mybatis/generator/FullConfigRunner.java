/**
 * gns-general-mybatis
 * FullConfigRunner.java
 * Lovesha 2017年11月12日
 */
package gns.general.mybatis.generator;

import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * @author Lovesha
 *
 */
public class FullConfigRunner {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		String inputFileName = "src\\main\\java\\gns\\general\\mybatis\\generator\\baseGeneratorConfig.xml";
		String outputFileName = "src\\test\\java\\gns\\general\\mybatis\\generatorConfig.xml";

		SAXReader reader = new SAXReader();
		Document doc = reader.read(inputFileName);

		List contextNodes = doc.getRootElement().selectNodes("context");
		if (null == contextNodes || contextNodes.isEmpty()) {
			throw new Exception("Can not find context node");
		}
		Element contextNode = (Element) contextNodes.get(0);
		List jdbcNodes = contextNode.elements("jdbcConnection");
		if (null == jdbcNodes || jdbcNodes.size() != 1) {
			throw new Exception("Can not find jdbcConnection node");
		}

		Element jdbcNode = (Element) jdbcNodes.get(0);

		String driver = jdbcNode.attribute("driverClass").getStringValue();
		String url = jdbcNode.attribute("connectionURL").getStringValue();
		String user = jdbcNode.attribute("userId").getStringValue();
		String password = jdbcNode.attribute("password").getStringValue();

		// Find all table names in db
		String[] elms = url.split("/");
		String dbName = elms[elms.length - 1];

		String tableSql = getTableSql(driver, dbName);

		List<String> tableNames = getStringResult(driver, url, user, password, tableSql);
		System.out.println("Found " + tableNames.size() + " tables");
		if (null == tableNames || tableNames.isEmpty()) {
			throw new Exception("Can not find any table");
		}

		for (String tableName : tableNames) {
			String fieldSql = getFieldSql(driver, dbName, tableName);
			List<String> fields = getStringResult(driver, url, user, password, fieldSql);
			System.out.println("Found " + fields.size() + " fields in table [" + tableName + "]");
			Element tableNode = contextNode.addElement("table");
			// schema="sakila" tableName="address" domainObjectName="Address"
			tableNode.addAttribute("schema", dbName);
			tableNode.addAttribute("tableName", tableName);
			tableNode.addAttribute("domainObjectName", renameToJavaStyle(tableName, true));
			tableNode.addAttribute("enableCountByExample", "false");
			tableNode.addAttribute("enableUpdateByExample", "false");
			tableNode.addAttribute("enableDeleteByExample", "false");
			tableNode.addAttribute("enableSelectByExample", "false");
			tableNode.addAttribute("selectByExampleQueryId", "false");
			for (String field : fields) {
				Element fieldNode = tableNode.addElement("columnOverride");
				fieldNode.addAttribute("column", field);
				fieldNode.addAttribute("property", renameToJavaStyle(field, false));
			}
		}

		FileWriter fileWriter = new FileWriter(outputFileName);
		OutputFormat format = OutputFormat.createPrettyPrint(); // 设置美观的缩进格式，便于阅读
		// format = OutputFormat.createCompactFormat();//设置紧凑格式（消除多余空格），便于下载
		XMLWriter writer = new XMLWriter(System.out, format);
		writer.setWriter(fileWriter);
		writer.write(doc);
		writer.close();
	}

	private static String renameToJavaStyle(String str, boolean isClassName) {
		String[] elms = str.split("_");
		String result = "";
		if (elms.length > 0) {
			for (int i = 1; i < elms.length; i++) {
				result += getFirstCharacterUpperStr(elms[i]);
			}
			result = elms[0] + result;
			if (isClassName) {
				result = getFirstCharacterUpperStr(result);
			}
		} else {
			result = str;
		}

		return result;
	}

	private static String getFirstCharacterUpperStr(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	private static String getTableSql(String driver, String dbName) {
		if ("com.mysql.jdbc.Driver".equals(driver)) {
			return "select table_name from information_schema.tables where table_schema = '" + dbName
					+ "'  and table_type='BASE TABLE';";
		}
		return null;
	}

	private static String getFieldSql(String driver, String dbName, String tableName) {
		if ("com.mysql.jdbc.Driver".equals(driver)) {
			return "select column_name from information_schema.columns where table_schema = '" + dbName
					+ "' and table_name = '" + tableName + "';";
		}
		return null;
	}

	@SuppressWarnings("finally")
	private static List<String> getStringResult(String driver, String url, String user, String password, String sql)
			throws Exception {
		List<String> results = new ArrayList<String>();
		Connection con = null;

		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			if (con.isClosed())
				throw new Exception("Can not connect db : " + url);
			Statement statement = con.createStatement();
			rs = statement.executeQuery(sql);

			while (rs.next()) {
				results.add(rs.getString(1));
			}
		} finally {
			if (null != rs) {
				rs.close();
			}
			if (null != con) {
				con.close();
			}
			return results;
		}
	}

}
