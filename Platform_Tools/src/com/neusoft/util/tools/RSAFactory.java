package com.neusoft.util.tools;

import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

public abstract class RSAFactory extends RSATool {   
    public static final String KEY_ALGORITHM = "RSA";   
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";   
  
    private static String PUBLIC_KEY_GEN = "305C300D06092A864886F70D0101010500034B00304802410091DB2966CF603F69386653330B838DEF2DBA3D0A7E70D7BCFE4606482902E37B00146853A0FD72A022B0D0516F2F80414B5ED85F02D2A16F79C66EEFF5672EC90203010001";
    private static String PRI_KEY_GEN = "30820154020100300D06092A864886F70D01010105000482013E3082013A02010002410091DB2966CF603F69386653330B838DEF2DBA3D0A7E70D7BCFE4606482902E37B00146853A0FD72A022B0D0516F2F80414B5ED85F02D2A16F79C66EEFF5672EC902030100010240793F871B4028F28DB5A4DA65049D47D01C63457B03C12A96A189F48EE808CEB09F2946778BAABC67B80DD92689D8F426864ED870C5ED94D06334BD842435CD01022100D01A2CF36DB1EAE454C0ED63B712CB23D8ABE8B5CB678DECB84A90089161C5ED022100B36D4FBF25AD149DB5C6E7DEBE5AD189700AD4E5BF351E7F42798960E11470CD0220598F7A5B0591B5A2B0EB1AFF7831C9655301A1346426DC08CDC3A13B5EBE327D022010272B22BE3592C18FF7D4300D5B07BA23C815DA89183322A9CD5F3CC5869D91022100AFF7EC333914B65094C967EB7B30D9D360FB79FA6F018C390E6125C9DD1597BA";
  
    /**  
     * 用私钥对信息生成数字签名  
     *   
     * @param data  
     *            加密数据  
     * @param privateKey  
     *            私钥  
     *   
     * @return  
     * @throws Exception  
     */  
    public static String sign(byte[] data) throws Exception {   
        // 解密由base64编码的私钥   
        byte[] keyBytes = decryptBASE64(PRI_KEY_GEN);   
  
        // 构造PKCS8EncodedKeySpec对象   
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);   
  
        // KEY_ALGORITHM 指定的加密算法   
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);   
  
        // 取私钥匙对象   
        PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);   
  
        // 用私钥对信息生成数字签名   
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);   
        signature.initSign(priKey);   
        signature.update(data);   
  
        return encryptBASE64(signature.sign());   
    }   
  
    /**  
     * 校验数字签名  
     *   
     * @param data  
     *            加密数据  
     * @param publicKey  
     *            公钥  
     * @param sign  
     *            数字签名  
     *   
     * @return 校验成功返回true 失败返回false  
     * @throws Exception  
     *   
     */  
    public static boolean verify(byte[] data, String sign)   
            throws Exception {   
  
        // 解密由base64编码的公钥   
        byte[] keyBytes = decryptBASE64(PUBLIC_KEY_GEN);   
  
        // 构造X509EncodedKeySpec对象   
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);   
  
        // KEY_ALGORITHM 指定的加密算法   
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);   
  
        // 取公钥匙对象   
        PublicKey pubKey = keyFactory.generatePublic(keySpec);   
  
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);   
        signature.initVerify(pubKey);   
        signature.update(data);   
  
        // 验证签名是否正常   
        return signature.verify(decryptBASE64(sign));   
    }   
  
    /**  
     * 解密<br>  
     * 用私钥解密  
     *   
     * @param data  
     * @param key  
     * @return  
     * @throws Exception  
     */  
    public static byte[] decryptByPrivateKey(byte[] data, String key)   
            throws Exception {   
        // 对密钥解密   
        byte[] keyBytes = decryptBASE64(key);   
  
        // 取得私钥   
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);   
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);   
        Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);   
  
        // 对数据解密   
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());   
        cipher.init(Cipher.DECRYPT_MODE, privateKey);   
  
        return cipher.doFinal(data);   
    }   
  
    /**  
     * 解密<br>  
     * 用私钥解密  
     *   
     * @param data  
     * @param key  
     * @return  
     * @throws Exception  
     */  
    public static byte[] decryptByPublicKey(byte[] data, String key)   
            throws Exception {   
        // 对密钥解密   
        byte[] keyBytes = decryptBASE64(key);   
  
        // 取得公钥   
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);   
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);   
        Key publicKey = keyFactory.generatePublic(x509KeySpec);   
  
        // 对数据解密   
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());   
        cipher.init(Cipher.DECRYPT_MODE, publicKey);   
  
        return cipher.doFinal(data);   
    }   
  
    /**  
     * 加密<br>  
     * 用公钥加密  
     *   
     * @param data  
     * @param key  
     * @return  
     * @throws Exception  
     */  
    public static byte[] encryptByPublicKey(byte[] data, String key)   
            throws Exception {   
        // 对公钥解密   
        byte[] keyBytes = decryptBASE64(key);   
  
        // 取得公钥   
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);   
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);   
        Key publicKey = keyFactory.generatePublic(x509KeySpec);   
  
        // 对数据加密   
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());   
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);   
  
        return cipher.doFinal(data);   
    }   
  
    /**  
     * 加密<br>  
     * 用私钥加密  
     *   
     * @param data  
     * @param key  
     * @return  
     * @throws Exception  
     */  
    public static byte[] encryptByPrivateKey(byte[] data, String key)   
            throws Exception {   
        // 对密钥解密   
        byte[] keyBytes = decryptBASE64(key);   
  
        // 取得私钥   
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);   
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);   
        Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);   
  
        // 对数据加密   
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());   
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);   
  
        return cipher.doFinal(data);   
    }   
  
  
    
    /**
     * 
     * @param args
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception{
    	String sign = RSAFactory.sign("dataaa".getBytes()) ;
    	System.out.println(RSAFactory.verify("dataaa".getBytes(), sign)) ;
    }
    
}  