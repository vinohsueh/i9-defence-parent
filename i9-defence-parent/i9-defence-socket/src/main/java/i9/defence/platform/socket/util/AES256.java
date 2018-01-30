package i9.defence.platform.socket.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class AES256 {

    public static byte[] encrypt(byte[] data, byte[] password) {
        try {
            // "AES"：请求的密钥算法的标准名称
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            // 256：密钥生成参数；securerandom：密钥生成器的随机源
            SecureRandom securerandom = new SecureRandom(password);
            kgen.init(128, securerandom);
            // 生成秘密（对称）密钥
            SecretKey secretKey = kgen.generateKey();
            // 返回基本编码格式的密钥
            byte[] enCodeFormat = secretKey.getEncoded();
            // 根据给定的字节数组构造一个密钥。enCodeFormat：密钥内容；"AES"：与给定的密钥内容相关联的密钥算法的名称
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            // 将提供程序添加到下一个可用位置
            Security.addProvider(new BouncyCastleProvider());
            // 创建一个实现指定转换的 Cipher对象，该转换由指定的提供程序提供。
            // "AES/ECB/PKCS7Padding"：转换的名称；"BC"：提供程序的名称
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding", "BC");

            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] dst = cipher.doFinal(data);
            return dst;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] decrypt(byte[] data, byte[] password) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            SecureRandom securerandom = new SecureRandom(password);
            kgen.init(128, securerandom);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
//            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding", "BC");

            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] dst = cipher.doFinal(data);
            return dst;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] tohash256Deal(String datastr) {
        try {
            MessageDigest digester = MessageDigest.getInstance("SHA-256");
            digester.update(datastr.getBytes());
            byte[] hex = digester.digest();
            return hex;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void main(String[] args) {

        String content = "000001";
        String password = "000001";
        
        System.out.println("明文：" + content);
        System.out.println("：" + password);
        
//        byte[] data0 = Base64.getDecoder().decode(content.getBytes());
//        System.out.println("密文：" + EncryptUtils.bytesToHexString(data0));
//        
//        byte[] data1 = Base64.getDecoder().decode(password.getBytes());
//        System.out.println("密文：" + EncryptUtils.bytesToHexString(data1));

        byte[] encryptResult = AES256.encrypt(content.getBytes(), password.getBytes());
        System.out.println("密文：" + EncryptUtils.bytesToHexString(encryptResult));

        byte[] decryptResult = AES256.decrypt(encryptResult, password.getBytes());
        System.out.println("解密：" + EncryptUtils.bytesToHexString(decryptResult));
        
//        System.out.println(EncryptUtils.bytesToHexString(AES256.tohash256Deal("0")));
    }
}
