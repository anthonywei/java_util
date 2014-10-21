
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.*;

public class SecurityUtils {

    public static String hmacSHA1(String key, String data) {
        return hmacSHA1(key, data, null);
    }

    public static String hmacSHA1(String key, String data, SecretKeySpec spec) {
        byte[] byteHMAC = null;
        try {
            Mac mac = Mac.getInstance("HmacSHA1");
            if (spec == null) {
                spec = new SecretKeySpec(key.getBytes(), "HmacSHA1");
            }
            mac.init(spec);
            byteHMAC = mac.doFinal(data.getBytes());
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException ignore) {
        }
        return new BASE64Encoder().encode(byteHMAC);
    }

}
