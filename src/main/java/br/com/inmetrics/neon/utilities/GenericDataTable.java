package br.com.inmetrics.neon.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;
import java.lang.String;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import br.com.inmetrics.neon.constants.Constants;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFCell;

/**
 * 
 * Represents a datatable similar to QTP datatable.
 * It enables to access external worksheet based on file system.
 * 
 * @author Tiago Samuel Rodrigues Teixeira
 * 
 */
public class GenericDataTable implements DataTable 
{
	public static final int CURSOR_STATE_NONE = -1;
	public static final int CURSOR_STATE_INITIAL = 1;
	public static final int CURSOR_STATE_END = 2;
	public static final int CURSOR_STATE_MIDDLE = 3;
	public static final int WORKSHEET_ROW_HEADER = 0;
	public static final int WORKSHEET_ROW_INIT = 1;
	public static final int WORKSHEET_ROW_END = 65535;
	public static final short WORKSHEET_COL_INIT = 0;
	public static final short WORKSHEET_COL_END = 264;
	
	private String wkPath;
	private String wsName;
	private FileInputStream fi;
	private HSSFWorkbook wk;
	private HSSFSheet ws;
	private int currentRow;
	private int lastValidRow;
	private int currentCursorState;
	private Hashtable columnList;	
	private boolean read;
	
	/**
	 * @return	the workbook path
	 */
	public String getWorkbookPath()
	{
		return wkPath;
	}
	
	/**
	 * @return	the worksheet name 
	 */
	public String getWorksheetName()
	{
		return wsName;
	}
	
	/**
	 * @return	the instance that represents the workbook
	 */
	public HSSFWorkbook getWorkbook()
	{
		return wk;
	}
	
	/**
	 * @return	the row where the cursor is positioned
	 */
	public int getCurrentRow()
	{
		return currentRow;
	}
	
	/**
	 * @return	the current cursor state
	 */
	public int getCurrentCursorState()
	{
		return currentCursorState;
	}
	
	/**
	 * @return	the columns availables in this worksheet
	 */
	public String[] getColumnList()
	{
		java.util.Enumeration keys = columnList.keys();
		String[] list = new String[columnList.size()];		
		
		int i=0;
		{
			while(keys.hasMoreElements())
			{		
				list[i] = (String)keys.nextElement();
				i++;
			}	
		}
		
		return list;		
	}
	
	/**
	 * @return	the number os valid rows that will be used to navigate
	 */
	public int getRowCount()
	{
		return lastValidRow;
	}
	
	/**
	 * @param	workbookPath	Path where the Excel worksheet is stored 
	 */
	public GenericDataTable(String workbookPath)
	{
		try
		{
			this.wkPath = workbookPath;		
			this.fi = new FileInputStream(workbookPath);		
			this.wk = new HSSFWorkbook(fi);
			this.ws = wk.getSheetAt(0);
			this.currentRow = Integer.MIN_VALUE;
			this.columnList = new Hashtable();
			this.wsName = wk.getSheetName(0);
			this.read = false;
			
			this.initialize();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * @param 	workbookPath	path where the Excel worksheet is stored
	 * @param	worksheetName	the name of worksheet to be used
	 */
	public GenericDataTable(String workbookPath, String worksheetName) throws FileNotFoundException, IOException
	{
		this.wkPath = workbookPath;
		this.fi = new FileInputStream(workbookPath);		
		this.wk = new HSSFWorkbook(fi);
		this.ws = wk.getSheet(worksheetName);
		this.currentRow = Integer.MIN_VALUE;
		this.columnList = new Hashtable();
		this.wsName = worksheetName;
		this.read = false;
		
		this.initialize();
	}
	
	/**
	 * return	 if the cursor location is at the and of the worksheet
	 */
	public boolean isAtEnd()
	{
		return currentRow == lastValidRow && currentRow > 0;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isAtTop()
	{
		return currentRow == WORKSHEET_ROW_INIT && currentRow > 0;
	}
	
	/**
	 * Initialize the cursor and sets the basic properties that enables the object navigate into the rows
	 */
	private void initialize()
	{
		if(ws.getRow(WORKSHEET_ROW_HEADER) != null)
		{			
			for(short col = WORKSHEET_COL_INIT; col <= WORKSHEET_COL_END; col ++)
			{			
				HSSFCell currentCell = ws.getRow(WORKSHEET_ROW_HEADER).getCell(col); 
				if(currentCell != null)
				{
					if(currentCell.toString() != Constants.EMPTY_STRING)
					{					
						columnList.put(currentCell.toString(), new Short(col));
					}				
					else
					{
						break;
					}
				}
			}
		}		
		
		boolean hasValidRow = false;
		
		for(int row = WORKSHEET_ROW_INIT; row <= WORKSHEET_ROW_END; row ++)
		{
			if(ws.getRow(row) != null)
			{			
				boolean hasValidFields = false;
				
				for(int col = 0; col < columnList.values().size(); col ++)
				{						
					HSSFCell currentCell = ws.getRow(row).getCell(Short.parseShort(String.valueOf(col)));
					if(currentCell != null)
					{	
						if(currentCell.toString() != Constants.EMPTY_STRING)
						{
							hasValidFields = true;
							hasValidRow = true; 
							break;
						}
					}
				}
			
				if(row == WORKSHEET_ROW_INIT && !hasValidFields)
				{
					currentCursorState = CURSOR_STATE_NONE;
					break;
				}
				else
				{
					lastValidRow = row;
				}
			}
			else
			{
				if(row == WORKSHEET_ROW_INIT)
				{
					currentCursorState = CURSOR_STATE_NONE;
					break;
				}
			}
		}
		
		if(hasValidRow)
		{
			currentRow = WORKSHEET_ROW_INIT;
			refreshCursorState();
		}
	}

	public String getStringOf(String instance, String field)
	{
		return Constants.EMPTY_STRING;
		//return getStringOf(Dictionary.getFieldName(instance, field, Constants.getLanguage()));
	}
	
	/**
	 * @param	columnName	name of the column to get the value
	 * @return				String value of the cell specified
	 */
	public String getStringOf(String columnName)
	{
		if(currentCursorState != CURSOR_STATE_NONE)
		{			
			if(columnList.containsKey(columnName))
			{				
				HSSFCell cell = ws.getRow(currentRow).getCell(Short.parseShort(columnList.get(columnName).toString()));
				if( cell != null )
				{
					String data; 
					if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
					{						
						data = String.valueOf(cell.getNumericCellValue());						
					}
					else
					{
						data = cell.getStringCellValue();
					}
					return data;
				}
			}
		}
		
		return Constants.EMPTY_STRING;		
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param columnName
	 * @return
	 */
	public String[] getValuesFromColumn(String columnName)
	{
		if(currentCursorState != CURSOR_STATE_NONE)
		{
			if(columnList.containsKey(columnName))
			{				
				HSSFRow rowObj;
				HSSFCell cellObj;
				Vector cellValues = new Vector();
				
				for(short row= WORKSHEET_ROW_INIT; row <=  WORKSHEET_ROW_END; row++)
				{
					if((rowObj = ws.getRow(row)) != null)	
					{						
						
						if((cellObj = rowObj.getCell(Short.parseShort(columnList.get(columnName).toString()))) != null)
						{
							String data; 
							if(cellObj.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
							{														
								data = String.valueOf(cellObj.getNumericCellValue());
							}
							else
							{
								data = cellObj.getStringCellValue();
							}							
							cellValues.add(data);							
						}
					}
					else
					{
						break;
					}
				}			
				
				String[] result = new String[cellValues.size()];
			
				for(int i=0; i < cellValues.size(); i ++)
				{
					result[i] = (String)cellValues.get(i);
				}
							
				return result;
			}			
		}
		
		return null;
	}
	
	/**
	 * 
	 * 
	 */
	public String getStringOf(String columnName, int row)
	{
		if(currentCursorState != CURSOR_STATE_NONE)
		{
			if(columnList.containsKey(columnName))
			{				
				HSSFRow rowObj = ws.getRow(row);
				if(rowObj != null)
				{					
					if(rowObj.getCell(Short.parseShort(columnList.get(columnName).toString())) != null)
					{	
						HSSFCell cell = rowObj.getCell(Short.parseShort(columnList.get(columnName).toString()));
												
						String data; 
						if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
						{														
							data = String.valueOf(cell.getNumericCellValue());
						}
						else
						{
							data = cell.getStringCellValue();
						}							
																
						return data;
					}
				}				
			}
		}
		
		return Constants.EMPTY_STRING;
	}
	
	/**
	 * 
	 * 
	 */
	public void setValueOf(String instance, String field, String value)
	{		
		//setValueOf(Dictionary.getFieldName(instance, field, Constants.getLanguage()), value);
	}
	
	/**
	 * @param	columnName	name of the column to set the value
	 * @param	value		the current value of the column specified
	 */
	public void setValueOf(String columnName, String value)
	{
		if(currentCursorState != CURSOR_STATE_NONE)
		{
			if(columnList.containsKey(columnName))
			{				
				HSSFCell cell = ws.getRow(currentRow).getCell(Short.parseShort(columnList.get(columnName).toString()));
				if( cell == null )
				{
					cell = ws.getRow(currentRow).createCell(Short.parseShort(columnList.get(columnName).toString()));
				}
				cell.setCellValue(value);
			}
		}
	}

	/**
	 * The datatable locates the cursor in the next row if the current cursor position is not at end of worksheet.
	 */
	public void setNextRow()
	{	
		if(currentCursorState != CURSOR_STATE_NONE)
		{
			if(currentRow != lastValidRow)
			{
				currentRow ++;
				refreshCursorState();
			}
			else
			{
				read = false;
			}
		}		
	}

	/**
	 * The datatable locates the cursor in the next row or in the top of list if the cursor position is at end of worksheet.
	 * @param	retroative	in case true if the cursor is locate at end of worksheet the cursor is located in the top 
	 */
	public void setNextRow(boolean retroative)
	{
		if(currentCursorState != CURSOR_STATE_NONE)
		{
			if(currentRow == lastValidRow)
			{
				if(retroative)
				{
					currentRow = WORKSHEET_ROW_INIT;
				}
			}
			else
			{
				currentRow ++;
			}
			refreshCursorState();
		}
	}
	
	/**
	 * The datatable locates the cursor in the previous row if the current cursor position is not at begin of worksheet.
	 */
	public void setPreviousRow()
	{
		if(currentCursorState != CURSOR_STATE_NONE)
		{
			if(currentRow != WORKSHEET_ROW_INIT)
			{
				currentRow --;
				refreshCursorState();
			}
		}
	}

	/**
	 * The datatable locates the cursor in the previous row or in the end of the worksheet
	 * @param	retroative	in case true if the cursor is locate at begin of worksheet the cursor is located at end of worksheet 
	 */
	public void setPreviousRow(boolean retroative)
	{
		if(currentCursorState != CURSOR_STATE_NONE)
		{
			if(currentRow == WORKSHEET_ROW_INIT)
			{
				if(retroative)
				{
					currentRow = lastValidRow;	
				}				
			}
			else
			{
				currentRow --;
			}
			refreshCursorState();
		}
	}
	
	/**
	 * Locates the cursor in the first row.
	 */
	public void setFirstRow()
	{
		if(currentCursorState != CURSOR_STATE_NONE)
		{
			currentRow = WORKSHEET_ROW_INIT;
			refreshCursorState();
			read = true;
		}
	}
	
	/**
	 * Locates the cursor in the last row.
	 */
	public void setLastRow()
	{
		if(currentCursorState != CURSOR_STATE_NONE)
		{
			currentRow = lastValidRow;
			refreshCursorState();
		}
	}
	
	/**
	 * Locates the cursor in a specified position
	 * @param	row	the new cursor position to be located
	 */
	public void setCurrentRow(int row)
	{
		if(currentCursorState != CURSOR_STATE_NONE)
		{
			if(row >= CURSOR_STATE_INITIAL && row <= lastValidRow)
			{
				currentRow = row;
				refreshCursorState();
			}
		}
	}
	
	/**
	 * Search a value based on a specific column.
	 */
	public int getRowByColumnSearch(String columnName, String data)
	{
		if(currentCursorState != CURSOR_STATE_NONE)
		{
			for(int row = WORKSHEET_ROW_INIT; row <= lastValidRow; row++)
			{
				if(data.equals(getStringOf(columnName, row)))						
				{					
					return row;
				}				
			}		
		}
		
		return 0;
	}
	
	/**
	 * This methos checks the cursor positions. It`s useful to iterate on the worksheet 
	 * row by row.
	 * 
	 * @return Returns true while the cursor is not at end of worksheet.
	 */
	public boolean read()
	{
		return read;
	}
	
	/**
	 * Refresh the cursor state according to row position
	 */
	private void refreshCursorState()
	{
		if(currentRow == WORKSHEET_ROW_INIT)
		{
			currentCursorState = CURSOR_STATE_INITIAL;
		}
		else if(currentRow == lastValidRow)
		{
			currentCursorState = CURSOR_STATE_END;
		}
		else if(currentRow > WORKSHEET_ROW_INIT && currentRow < WORKSHEET_ROW_END)
		{
			currentCursorState = CURSOR_STATE_MIDDLE;
		}
		else
		{
			currentCursorState = CURSOR_STATE_NONE; 
		}
	}
	
	/**
	 * 
	 * Save the workbook with the current state.
	 *
	 */
	public void save()
	{
		try
		{
			FileOutputStream stream = new FileOutputStream(wkPath);
			wk.write(stream);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			//log(e.getMessage());
		}
	}	
}