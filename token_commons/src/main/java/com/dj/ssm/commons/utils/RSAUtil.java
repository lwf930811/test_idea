package com.dj.ssm.commons.utils;

import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;

public class RSAUtil {
	/*私玥
	 * MIICXQIBAAKBgQDlOJu6TyygqxfWT7eLtGDwajtNFOb9I5XRb6khyfD1Yt3YiCgQ
	WMNW649887VGJiGr/L5i2osbl8C9+WJTeucF+S76xFxdU6jE0NQ+Z+zEdhUTooNR
	aY5nZiu5PgDB0ED/ZKBUSLKL7eibMxZtMlUDHjm4gwQco1KRMDSmXSMkDwIDAQAB
	AoGAfY9LpnuWK5Bs50UVep5c93SJdUi82u7yMx4iHFMc/Z2hfenfYEzu+57fI4fv
	xTQ//5DbzRR/XKb8ulNv6+CHyPF31xk7YOBfkGI8qjLoq06V+FyBfDSwL8KbLyeH
	m7KUZnLNQbk8yGLzB3iYKkRHlmUanQGaNMIJziWOkN+N9dECQQD0ONYRNZeuM8zd
	8XJTSdcIX4a3gy3GGCJxOzv16XHxD03GW6UNLmfPwenKu+cdrQeaqEixrCejXdAF
	z/7+BSMpAkEA8EaSOeP5Xr3ZrbiKzi6TGMwHMvC7HdJxaBJbVRfApFrE0/mPwmP5
	rN7QwjrMY+0+AbXcm8mRQyQ1+IGEembsdwJBAN6az8Rv7QnD/YBvi52POIlRSSIM
	V7SwWvSK4WSMnGb1ZBbhgdg57DXaspcwHsFV7hByQ5BvMtIduHcT14ECfcECQATe
	aTgjFnqE/lQ22Rk0eGaYO80cc643BXVGafNfd9fcvwBMnk0iGX0XRsOozVt5Azil
	psLBYuApa66NcVHJpCECQQDTjI2AQhFc1yRnCU/YgDnSpJVm1nASoRUnU8Jfm3Oz
	uku7JUXcVpt08DFSceCEX9unCuMcT72rAQlLpdZir876*/
//	private static String REQUEST_PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALuYr8WlpQbDmQ1Q" + "O5Wz0f85+UhIH1GHbIwCXU+LoPIX2bqHkzE3B3ZIMSvbCWT5pErv0lyM+7X77TSU" + "42kUC36UysQEu9MwdlVD6RaBVfT6MYoJpvvGgq+VlM82OU3P03e7iDoppsENchlw" + "lr93x6xm/MuyzzRI1BTz0H6+rU1lAgMBAAECgYAdcXN1A/CIxT5KVqNjdZuqAUFc" + "1OUFeMnSl7RpfbK/DHtByXGSsd5b9Cyzg2dQD9Z3ZHiRyhbfkzDBpfSjU2ASMrR0" + "xHGLdJx7DSLd3k75ifF/DECAQA/CzIfiCKa9IgA2Cj//OVLcxjGAw4iEnE9Umsa7" + "n2wyR8bouDarHwifwQJBAPT4IcVaChm0Q8GEZwtyf/FIS0kEQ+u6r2zHja4Nlz+s" + "rR3EwggtV+Me9v2xyJfpcO/mbVyOCdaav17Wr7yD+tkCQQDECzFh0cjviS6wFw8Z" + "+vSWv4IBtGBVbhVB30LQFBMg96b3Av89pULpKRl2mIEVOtfo5IhLaGWhiB4z4Jar" + "QhdtAkEAlZcAaFc3W8LsrTuBAUiGQHz5HDlykHyLq02gguzhs4xqmocQRZYK2TKL"
//            + "eRgbekifIp//oElMULRmsC9BWUju4QJAL5qwMRqp+lCLf8L5rctcnUZ/oT5Vrij/" + "DHHUXYaiZnz8lDqsFCIPL2MFheDeZ3NUfn8QAY+mLiVJgDtnGsr/uQJAPmlbiqyY" + "oqrN0Th78PnzOiOmJokLSzOKfg9xpyp4Ae5dGk2tyHsaieIdT+otGAnib5suxodr" + "cSTlMXhIikLimg==";

	private static String REQUEST_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCct96pRbRbycZRZbOstqd6EqHTcd9m5JqhjjKGXqD/NzrRitnMjojofJyMQrCqpUL/FIT1A6i/Z9bIKcl8o0lR/tLl9lh7JLuMACcdJjpaYqzTjji+CZfSMomTMzn6sEpdawVMTi84++Hd9FkuR9Ynjf63bD48voWvVEfeU96GToZrLsKO3ZVfFkNBHOZs9VcODOmtHUvcZ+FL48gdLyBsEfLuzoOrxk6hdMh6nkXbaDMQL02mIdsum2zN+k55fVpWm8t20sabTpYT/ccZmRgrhL7wvcPDoR32pwiv8CVy7jIljBQ0+oPJJ02lSYzh4FyLZYalG+lYG5HstQEkHyJnAgMBAAECggEAAgrLVf1YilGoyeq/WqLOr+KKRU0Ykiiscyp9Mg8NngXEOHLMv8OjuRPWnmVIFBEvPE+yI9c2XbgmrlkNDRi7ndvqN6DsKj6iSwK9f729yOu8QM17o85QMicCb+6c9M1TSsBYQ1RJfT6bBN9oQhghjEZnaVB5xyTcbRp2DUmefrWGBnYthsxPI6pdMejKwQQBmzJGJ40TaO0pX/lrHkdzyIE21io2VeTe2L/XOnboVZEUjmgOOWnZYwbQBW/9HSFKT5v0XDYyUIIOJ+y8a597N7Pz0Y0RXxD0w41bdQWFk1YWNYEYrOIdBWVDRvyHcsNUSmWXIvB+qBfkFPclvvjLYQKBgQDKHNGYAgLiQ2OGWiYV2rsjaLKyhDvsXNnEuXL8ZgT8FvRguzZdEY47jZ6aG0QLKe/X7AadSBz3OeVzzASJmRBHB9NdcUfd0LlarES8CcqE/DYRxhgAHQgDWToCXZgQLatk3WdbXo+wVojcJXbcWxM36lWdGzTelvGRENcqGH9PXwKBgQDGgKno6KHDTZuyoNKk3PDfWdjpReN7rPK+GuVgfXBEuFa5W3LUb0pwHmwSGl2WR4dOtHZwYGUs1hMvfXXnUGrYSLVBAcdN5qZrX6laF6FbrEI6zg3OZvMVqpRKjgD41dlrrkWkdagBwkWCz55mZZgRhrfrxypPhj7vNzn0Yv5x+QKBgQC7SCkPag2yDC/U9wQDjEOMdB/iO5YrV36TRCTArOhBkCOy3sdpRjM+C+TDeDB9B8cvlY4rf/8XN5bezlYGCseCikXZeHbXm+mlk6u9cnUu9WZE4gKrWDtsnumZ95BIrYj8VgzIJb+P7x+ynySc2+fbEFF02xkKiWQewDQg1cJikQKBgGGpo+8Hpa7lXBlCruCWI5yAJAyBai7LJJMLwJb8oGPvHhwMNi72onUPQrSVbD4n6xZ6C1u20ZCBsubGn1+zXR4Uh7j4Gwrk1DQ32KaOwcDMu7ka8AgA9tBOIK+dtjS3YkVsdK85g/N5laGKbLCMg4j78zl5wANoVUZ0sXrfybhhAoGASh7DspfEtSaAD7NuYuU1CmDBxJe34DACmGJnc8unO7jsaqQg+mp+vajLGBhxOuFpD20pvWLPAsdyVVgH4UapwPl8iE81qKNkevWxSi3FAPrVw/liEL2fhQXEaNbhG1Sjjj3c3GFF1fN6hTfELhnk4WMs/MKPrupNJomvEV5kfbQ=";
    /**
     * 解密算法
     * cryptograph:密文
     */
    public static String decryptRequestParamValue(String cryptograph) throws Exception {
        PrivateKey key = stringToPrivateKey(REQUEST_PRIVATE_KEY);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, key);
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] b1 = decoder.decodeBuffer(cryptograph);
        byte[] b = cipher.doFinal(b1);
        return new String(b);
    }
	
    public static PrivateKey stringToPrivateKey(String s) {
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] c = null;
        KeyFactory keyFact = null;
        PrivateKey returnKey = null;

        try {

            c = decoder.decodeBuffer(s);
            keyFact = KeyFactory.getInstance("RSA");
        } catch (Exception e) {

            System.out.println("Error in first try catch of stringToPrivateKey");
            e.printStackTrace();
        }

        PKCS8EncodedKeySpec x509KeySpec = new PKCS8EncodedKeySpec(c);
        try { // the next line causes the crash
            returnKey = keyFact.generatePrivate(x509KeySpec);
        } catch (Exception e) {

            System.out.println("Error in stringToPrivateKey");
            e.printStackTrace();
        }

        return returnKey;

    }

}
