package co.techelix.cipher;

/**
 * The type Shift cipher.
 */
public class ShiftCipher implements ICipher {

    /**
     * The constant SHIFT_KEY.
     */
    int shiftKey = 3;

    public ShiftCipher(int shiftKey) {
        this.shiftKey = shiftKey;
    }

    @Override
    public String encode(String data) {
        StringBuilder sb = new StringBuilder(data.length());

        for (char c : data.toCharArray())
            sb.append((char) ((int) c + shiftKey));

        return sb.toString();
    }

    @Override
    public String decode(String data) {
        StringBuilder sb = new StringBuilder(data.length());

        for (char c : data.toCharArray())
            sb.append((char) ((int) c - shiftKey));

        return sb.toString();
    }

}
