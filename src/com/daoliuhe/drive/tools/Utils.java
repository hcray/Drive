package com.daoliuhe.drive.tools;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * 工具类
 * @author 21829
 *
 */
public class Utils {
    /** 
     * SHA-256消息摘要算法 
     */  
    public static String encodeSHA256(String str) {
    	StringBuilder sb = new StringBuilder();
		try {
			// 初始化MessageDigest,SHA即SHA-1的简称
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] data = str.getBytes();
			// 执行摘要方法  
			byte[] digest = md.digest(data);
			for (byte b : digest) {
				sb.append(String.format("%02x", b));
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}  
    	return sb.toString();  
    }
}
