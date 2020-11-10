package co.techelix.cipher;

/**
 * SimpleSubstitutionCipher uses a lookup table (Key) to encrypt/decrypt the data
 */
public class SimpleSubstitutionCipher implements ICipher {

    /**
     * The constant KEY.
     */
    final static String KEY = "]kYV}(!7P$n5_0i R:?jOWtF/=-pe'AD&@r6%ZXs\"v*N"
            + "[#wSl9zq2^+g;LoB`aGh{3.HIu4fbK)mU8|dMET><,Qc\\C1yxJ";

    @Override
    public String encode(String data) {
        StringBuilder sb = new StringBuilder(data.length());

        for (char c : data.toCharArray())
            sb.append(KEY.charAt((int) c - 32));

        return sb.toString();
    }

    @Override
    public String decode(String data) {
        StringBuilder sb = new StringBuilder(data.length());

        for (char c : data.toCharArray())
            sb.append((char) (KEY.indexOf(c) + 32));

        return sb.toString();
    }

}
