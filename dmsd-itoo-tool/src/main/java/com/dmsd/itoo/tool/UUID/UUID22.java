package com.dmsd.itoo.tool.UUID;

import java.util.UUID;

/***
 * 随机生成22位
 * @author xj
 * 2015年7月12日
 */
public class UUID22 {

	/** 
     * 采用URL Base64字符，即把“+/”换成“-_” 
     */  
    static private char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_=".toCharArray();  
    
	public static String getUUID22() {  
		
        UUID uuid = UUID.randomUUID();  
        long msb = uuid.getMostSignificantBits();  
        long lsb = uuid.getLeastSignificantBits();  
        char[] out = new char[24];  
        int tmp = 0, idx = 0;  
        // 循环写法  
        int bit = 0, bt1 = 8, bt2 = 8;  
        int mask = 0x00, offsetm = 0, offsetl = 0;  
          
        for(; bit < 16; bit += 3, idx += 4) {  
            offsetm = 64 - (bit + 3) * 8;  
            offsetl = 0;  
            tmp = 0;  
              
            if(bt1 > 3) {  
                mask = (1 << 8 * 3) - 1;  
            } else if(bt1 >= 0) {  
                mask = (1 << 8 * bt1) - 1;  
                bt2 -= 3 - bt1;  
            } else {  
                mask = (1 << 8 * ((bt2 > 3) ? 3 : bt2)) - 1;  
                bt2 -= 3;  
            }  
            if(bt1 > 0) {  
                bt1 -= 3;  
                tmp = (int) ((offsetm < 0) ? msb : (msb >>> offsetm) & mask);  
                if(bt1 < 0) {  
                    tmp <<= Math.abs(offsetm);  
                    mask = (1 << 8 * Math.abs(bt1)) - 1;  
                }  
            }  
            if(offsetm < 0) {  
                offsetl = 64 + offsetm;  
                tmp |= ((offsetl < 0) ? lsb : (lsb >>> offsetl)) & mask;  
            }  
              
            if(bit == 15) {  
                out[idx + 3] = alphabet[64];  
                out[idx + 2] = alphabet[64];  
                tmp <<= 4;  
            } else {  
                out[idx + 3] = alphabet[tmp & 0x3f];  
                tmp >>= 6;  
                out[idx + 2] = alphabet[tmp & 0x3f];  
                tmp >>= 6;  
            }  
            out[idx + 1] = alphabet[tmp & 0x3f];  
            tmp >>= 6;  
            out[idx] = alphabet[tmp & 0x3f];  
        }  
	
        return new String(out, 0, 22); 
	}
}
