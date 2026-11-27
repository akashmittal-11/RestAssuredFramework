package api.utilities;

import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

public class DataProviders {



    @DataProvider(name = "Data")
    public String[][] getAllData(Method method) throws IOException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {

    	String path = (String) method.getDeclaringClass().getField("path").get(null);
    	/*
    	         Statement										Explanation
    	         
			Method method					TestNG automatically supplies the test method using the DataProvider.
			method.getDeclaringClass()		Identifies the class that declares the test method.
			.getField("path")				Finds the public variable named path in that class.
			.get(null)						Retrieves the value of the static variable.
			(String)						Converts the returned Object into a String.
    	 
    	 */
        ExcelUtilities xl = new ExcelUtilities(path);

        int rownum = xl.getRowCount("Sheet1");
        int colcount = xl.getCellCount("Sheet1", 0);

        String[][] apidata = new String[rownum][colcount];

        for (int i = 1; i <= rownum; i++) {

            for (int j = 0; j < colcount; j++) {

                apidata[i - 1][j] =
                    xl.getCellData("Sheet1", i, j);
            }
        }

        return apidata;
    }

    // Data provider for usernames only
    @DataProvider(name = "UserNames")
    public String[] getUserNames(Method method) throws IOException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {

    	String path = (String) method.getDeclaringClass().getField("path").get(null);

        ExcelUtilities xl = new ExcelUtilities(path);

        int rownum = xl.getRowCount("Sheet1");

        String[] apidata = new String[rownum];

        for (int i = 1; i <= rownum; i++) {

            apidata[i - 1] =
                xl.getCellData("Sheet1", i, 1);
        }

        return apidata;
    }
    
    // Data provider for IDs only
    @DataProvider(name = "IDs")
    public String[] getIDs(Method method) throws IOException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {

    	String path = (String) method.getDeclaringClass().getField("path").get(null);

        ExcelUtilities xl = new ExcelUtilities(path);

        int rownum = xl.getRowCount("Sheet1");

        String[] apidata = new String[rownum];

        for (int i = 1; i <= rownum; i++) {

            apidata[i - 1] =
                xl.getCellData("Sheet1", i, 0);
        }

        return apidata;
    }
}
