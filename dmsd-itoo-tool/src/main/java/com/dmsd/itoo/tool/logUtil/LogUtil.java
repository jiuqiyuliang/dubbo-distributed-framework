package com.dmsd.itoo.tool.logUtil;

import java.util.Enumeration;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * @Description: 日志的工具类操作
 * @author 牛迁迁
 * @date 2016年3月16日 下午2:43:36
 */
@Deprecated
public class LogUtil
{

    public static void debug(Logger logger, String msg)
    {
        if (logger.isDebugEnabled())
        {
            logger.debug(msg);
        }
    }

    public static void debug(Logger logger, String msg, Object... parms)
    {
        if (logger.isDebugEnabled())
        {
            logger.debug(String.format(msg, parms));
        }
    }

    /**
     * @Description: 判断日志是否为info级别
     * @author 牛迁迁
     * @date 2016年3月16日 下午4:01:19
     * @param logger
     * @param 日志信息
     */
    public static void info(Logger logger, String msg)
    {
        if (logger.isInfoEnabled())
        {
            logger.info(msg);
        }
    }

    public static void info(Logger logger, String msg, Object... params)
    {
        if (logger.isInfoEnabled())
        {
            logger.info(String.format(msg, params));
        }
    }

    public static void warn(Logger logger, String msg, Object... params)
    {
        if (logger.isEnabledFor(Level.WARN))
        {
            logger.warn(String.format(msg, params));
        }
    }

    public static void warn(Logger logger, Throwable e, String msg,
            Object... params)
    {
        if (logger.isEnabledFor(Level.WARN))
        {
            logger.warn(String.format(msg, params), e);
        }
    }

    public static void error(Logger logger, String msg)
    {
        if (logger.isEnabledFor(Level.ERROR))
        {
            logger.error(msg);
        }
    }

    public static void error(Logger logger, String msg, Object... params)
    {
        if (logger.isEnabledFor(Level.ERROR))
        {
            logger.error(String.format(msg, params));
        }
    }

    public static void error(Logger logger, String msg, Throwable e)
    {
        if (logger.isEnabledFor(Level.ERROR))
        {
            logger.error(msg, e);
        }
    }

    public static void error(Logger logger, String msg, Throwable e,
            Object... params)
    {
        if (logger.isEnabledFor(Level.ERROR))
        {
            logger.error(String.format(msg, params), e);
        }
    }

}
