package cn.edu.scu.jiangpeyton.keyshoot.caclhash;

import org.apache.commons.codec.binary.Hex;
import soot.SootClass;
import soot.SootMethod;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class ClassHashMap {
    /**
     * 对于系统Class, 其类名的SHA-256值即作为改Class的哈希值
     * 对于非系统Class, 则通过Merkle树计算哈希值
     * method的处理规则同上
     */
    public static Map<String, SootClass> classHashMap = new HashMap<String, SootClass>();
    public static Map<SootClass, String> classHashMapRe = new HashMap<SootClass, String>();
    public static Map<String, SootMethod> methodHashMap = new HashMap<String, SootMethod>();
    public static Map<SootMethod, String> methodHashMapRe = new HashMap<SootMethod, String>();

    public static void addClassMap(SootClass sootClass, String hash) {
        classHashMap.put(hash, sootClass);
        classHashMapRe.put(sootClass, hash);
    }

    public static void addMethodMap(SootMethod sootMethod, String hash) {
        methodHashMap.put(hash, sootMethod);
        methodHashMapRe.put(sootMethod, hash);
    }

    private static String SHA256(String input) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            digest.update(input.getBytes());
            return Hex.encodeHexString(digest.digest());
        } catch (NoSuchAlgorithmException e) {
            //e.printStackTrace();
            return "";
        }
    }
}
