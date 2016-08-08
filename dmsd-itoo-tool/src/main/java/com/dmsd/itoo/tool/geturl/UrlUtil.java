package com.dmsd.itoo.tool.geturl;

import javax.servlet.http.HttpServletRequest;

/**
 * 获得客户端IP，处理了HTTP代理服务器和Nginx的反向代理截取了ip
* @ClassName: UrlUtil 
* @Description: 
* @author [Administrator] 
* @date 2015年7月8日 上午8:51:23 
*
 */
public class UrlUtil {	
	/**
     * 从Request对象中获得客户端IP，处理了HTTP代理服务器和Nginx的反向代理截取了ip
     * @param request
     * @return ip
     */
   public static String getLocalIp(HttpServletRequest request) {
       String remoteAddr = request.getRemoteAddr();
       String forwarded = request.getHeader("X-Forwarded-For");
       String realIp = request.getHeader("X-Real-IP");

       String ip = null;
       if (realIp == null) {
           if (forwarded == null) {
               ip = remoteAddr;
           } else {
               ip = remoteAddr + "/" + forwarded.split(",")[0];
           }
       } else {
           if (realIp.equals(forwarded)) {
               ip = realIp;
           } else {
               if(forwarded != null){
                   forwarded = forwarded.split(",")[0];
               }
               ip = realIp + "/" + forwarded;
           }
       }
       return ip;
   }

}

