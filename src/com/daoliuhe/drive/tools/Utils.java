package com.daoliuhe.drive.tools;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * ������
 * @author 21829
 *
 */
public class Utils {
    /** 
     * SHA-256��ϢժҪ�㷨 
     */  
    public static String encodeSHA256(String str) {
    	StringBuilder sb = new StringBuilder();
		try {
			// ��ʼ��MessageDigest,SHA��SHA-1�ļ��
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] data = str.getBytes();
			// ִ��ժҪ����  
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
