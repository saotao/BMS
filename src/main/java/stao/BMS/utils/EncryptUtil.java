package stao.BMS.utils;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.UUID;

@Slf4j
public class EncryptUtil {
    private static final String HmacMD5 = "HmacMD5";

    /**编码格式；默认使用uft-8*/
    private String charset = "utf-8";

    private static EncryptUtil me;

    private EncryptUtil(){
        //单例
    }
    //双重锁
    public static EncryptUtil getInstance(){
        if (me==null) {
            synchronized (EncryptUtil.class) {
                if(me == null){
                    me = new EncryptUtil();
                }
            }
        }
        return me;
    }

    /**
     * 使用KeyGenerator进行单向/双向加密（可设密码）
     * @param res 被加密的原文
     * @param algorithm  加密使用的算法名称
     * @param key 加密使用的秘钥
     * @return
     */
    private String keyGeneratorMac(String res,String algorithm,String key){
        try {
            SecretKey sk = null;
            if (key==null) {
                KeyGenerator kg = KeyGenerator.getInstance(algorithm);
                sk = kg.generateKey();
            }else {
                byte[] keyBytes = charset==null?key.getBytes():key.getBytes(charset);
                sk = new SecretKeySpec(keyBytes, algorithm);
            }
            Mac mac = Mac.getInstance(algorithm);
            mac.init(sk);
            byte[] result = mac.doFinal(res.getBytes());
            return base64(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String base64(byte[] res){
        return Base64.encode(res);
    }

    /**
     * md5加密算法进行加密（不可逆）
     * @param res 需要加密的原文
     * @return
     */
    public String MD5(String res) {
        return keyGeneratorMac(res,HmacMD5,ConfigProperties.salt);
    }

    public static String getUUID(){
       return UUID.randomUUID().toString().replaceAll("-","");
    }

    public static void main(String[] args) {
        String uuid = getUUID();
        System.out.println(uuid);
    }
}
