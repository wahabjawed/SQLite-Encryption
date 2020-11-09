package co.techelix.cipher;

/**
 * The interface Cipher.
 */
public interface ICipher {

    /**
     * Encode string.
     *
     * @param data the data
     * @return the string
     */
    String encode(String data);

    /**
     * Decode string.
     *
     * @param data the data
     * @return the string
     */
    String decode(String data);


}
