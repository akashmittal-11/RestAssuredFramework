package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {


    String path = System.getProperty("user.dir")
                    + "/testData/UserData.xlsx";

    @DataProvider(name = "Data")
    public String[][] getAllData() throws IOException {

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
    public String[] getUserNames() throws IOException {

        ExcelUtilities xl = new ExcelUtilities(path);

        int rownum = xl.getRowCount("Sheet1");

        String[] apidata = new String[rownum];

        for (int i = 1; i <= rownum; i++) {

            apidata[i - 1] =
                xl.getCellData("Sheet1", i, 1);
        }

        return apidata;
    }
}
