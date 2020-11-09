package co.techelix;

import co.techelix.cipher.ICipher;
import co.techelix.cipher.ShiftCipher;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Main.
 */
public class Main {

    /**
     * The constant DB_NAME.
     */
    public static final String DB_NAME = "test.db";
    /**
     * The constant FILE_NAME.
     */
    public static final String FILE_NAME = "test.json";

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        Map<String, List<String>> tableColumnMapList = generateTableColMapFromJSON(FILE_NAME);

        ICipher cipher = new ShiftCipher(4);

        DBEncryptor encryptor = new DBEncryptor()
                                .setCipher(cipher)
                                .setDatabaseName(DB_NAME)
                                .setTableColumnMapList(tableColumnMapList);

        encryptor.execute();

    }


    private static Map<String, List<String>> generateTableColMapFromJSON(String fileName) {
        Map<String, List<String>> tableColumnMapList = new HashMap<>();
        tableColumnMapList.put("faham_quran", Arrays.asList("id", "urdu"));

        return tableColumnMapList;
    }







}
