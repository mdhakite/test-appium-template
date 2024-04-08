package com.appName.utils;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;

public class Crypt {
    /*
    * To encrypt texts outside this framework, use:
    * https://devglan.com/online-tools/jasypt-online-encryption-decryption
    * Use Secret Text as : Xponent@123
    * */

    public static StringEncryptor getPasswordEncryptor() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        String key = System.getenv("JASYPT_ENCRYPTOR_PASSWORD");
        config.setPassword(key); // encryptor's private key
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);
        return encryptor;
    }

    public static String encrypt(String plainText) {
        return getPasswordEncryptor().encrypt(plainText);
    }

    public static String decrypt(String encryptedText) {
        return getPasswordEncryptor().decrypt(encryptedText);
    }
}