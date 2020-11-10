package co.techelix;

import co.techelix.cipher.ICipher;
import co.techelix.cipher.ShiftCipher;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

/**
 * The type Main.
 */
public class App {

    /**
     * The constant DATABASE_NAME.
     */
    public static final String DATABASE_NAME = "test.sqlite";
    /**
     * The constant CONFIGURATION_FILE_NAME.
     */
    public static final String CONFIG_FILE_NAME = "table_col_map.json";

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        Map<String, List<String>> tableColumnMapList = generateTableColMapFromJSON(CONFIG_FILE_NAME);

        ICipher cipher = new ShiftCipher(4);

        DBEncryptor encryptor = new DBEncryptor()
                .setCipher(cipher)
                .setDatabaseName(DATABASE_NAME)
                .setTableColumnMapList(tableColumnMapList);

        encryptor.execute();
    }


    private static Map<String, List<String>> generateTableColMapFromJSON(String fileName) {
        Map<String, List<String>> tableColumnMapList = new HashMap<>();
        String fileContent = Util.openAndReadFile(fileName);

        if(fileContent !=null){

            JSONArray jsonArr = new JSONArray(fileContent);

            JSONObject tableObj;
            List colList;
            for (int i = 0; i < jsonArr.length(); i++) {
                tableObj = jsonArr.getJSONObject(i);
                colList = new ArrayList();

                colList.add(tableObj.getString("primaryKey"));
                colList.addAll(tableObj.getJSONArray("toEncrypt").toList());

                tableColumnMapList.put(tableObj.getString("tableName"), colList);
            }

        }

        return tableColumnMapList;
    }




}
