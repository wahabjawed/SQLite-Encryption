package co.techelix.cipher;

/**
 * The type Transposition cipher.
 */
public class DoubleTranspositionCipher implements ICipher {

    private static final char PAD_CHARACTER = '-';
    private final int keyLength;
    private final int[] key1;
    private final int[] key2;

    public DoubleTranspositionCipher(int[] key1, int[] key2) {
        this.keyLength = key1.length;
        this.key1 = key1;
        this.key2 = key2;
    }

    @Override
    public String encode(String data) {
        String paddedData = padData(data);
        char[] dataCharacters = paddedData.toCharArray();
        int gridRows = dataCharacters.length / keyLength;
        char[][] grid = generateGrid(dataCharacters, gridRows, keyLength);
        char[][] permutedGrid1 = permuteGrid(grid, keyLength, gridRows, key1);
        char[][] permutedGrid2 = permuteGrid(permutedGrid1, keyLength, gridRows, key2);

        return extractTextFromGrid(permutedGrid2, gridRows, keyLength);
    }

    @Override
    public String decode(String data) {
        char[] dataCharacters = data.toCharArray();
        int gridRows = dataCharacters.length / keyLength;
        char[][] grid = generateGrid(dataCharacters, gridRows, keyLength);
        char[][] preservedGrid1 = preserveGrid(grid, keyLength, gridRows, key2);
        char[][] preservedGrid2 = preserveGrid(preservedGrid1, keyLength, gridRows, key1);

        return extractTextFromGrid(preservedGrid2, gridRows, keyLength)
                .replace(String.valueOf(PAD_CHARACTER),"");
    }

    private char[][] generateGrid(char[] data, int x, int y) {
        char[][] grid = new char[x][y];
        int k = 0;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                grid[i][j] = data[k];
                k++;
            }
        }
        return grid;
    }

    private char[][] preserveGrid(char[][] grid,int x, int y, int[] key) {
        char[][] gridToBePreserved = new char[y][x];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                gridToBePreserved[j][key[i]] = grid[j][i];
            }
        }
        return gridToBePreserved;
    }

    private char[][] permuteGrid(char[][] grid,int x, int y, int[] key) {
        char[][] gridToBePermuted = new char[y][x];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                gridToBePermuted[j][i] = grid[j][key[i]];
            }
        }
        return gridToBePermuted;
    }

    private String extractTextFromGrid(char[][] grid, int x, int y) {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                text.append(grid[i][j]);
            }
        }
        return text.toString();
    }

    private String padData(String data) {
        StringBuilder dataToBePadded = new StringBuilder(data);
        int dataLength = dataToBePadded.length();
        if (dataLength % keyLength != 0) {
            for (int i = 0; i < (keyLength - (dataLength % keyLength)); i++) {
                dataToBePadded.append(PAD_CHARACTER);
            }
        }
        return dataToBePadded.toString();
    }

}
