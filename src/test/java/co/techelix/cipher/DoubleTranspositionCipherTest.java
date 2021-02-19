package co.techelix.cipher;

import junit.framework.TestCase;

public class DoubleTranspositionCipherTest extends TestCase {

    private static final String TEXT = "enemyattackstonight";
    private static final String CIPHER_TEXT = "yenemcattanksto-ight";

    public void testEncode() {
        DoubleTranspositionCipher doubleTranspositionCipher =
                new DoubleTranspositionCipher(new int[]{2, 0, 3, 4, 1}, new int[]{3, 1, 4, 0, 2});
        String encryptedText = doubleTranspositionCipher.encode(TEXT);
        assertEquals(CIPHER_TEXT, encryptedText);
    }

    public void testDecode() {
        DoubleTranspositionCipher doubleTranspositionCipher =
                new DoubleTranspositionCipher(new int[]{2, 0, 3, 4, 1}, new int[]{3, 1, 4, 0, 2});
        String decryptedText = doubleTranspositionCipher.decode(CIPHER_TEXT);
        assertEquals(TEXT, decryptedText);
    }

}