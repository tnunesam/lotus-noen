/*
 * Created on Jan 14, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package br.com.inmetrics.neon.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


/**
 * @author tiago.s.teixeira
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface DataTable {
	
	String getWorkbookPath();
	String getWorksheetName();
	HSSFWorkbook getWorkbook();
	int getCurrentRow();
	int getCurrentCursorState();
	String[] getColumnList();
	int getRowCount();
	boolean isAtEnd();
	boolean isAtTop();
	String getStringOf(String instance, String field);
	String getStringOf(String columnName);
	String[] getValuesFromColumn(String columnName);
	String getStringOf(String columnName, int row);
	void setValueOf(String instance, String field, String value);
	void setValueOf(String columnName, String value);
	void setNextRow();
	void setNextRow(boolean retroative);
	void setPreviousRow();
	void setPreviousRow(boolean retroative);
	void setFirstRow();
	void setLastRow();
	void setCurrentRow(int row);
	int getRowByColumnSearch(String columnName, String data);
	boolean read();
	void save();
}
