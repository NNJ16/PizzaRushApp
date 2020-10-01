package com.example.pizzarush.Entity;

public class CustomerUtil {
    private static String cid;
    private static String pwd;
    private static String mobile;

    public static void setCid(String custId)
    {
        cid = custId;
    }
    public static void setPwd(String pass)
    {
        pwd=pass;
    }

    public static void setMobile(String m)
    {
        mobile = m;
    }
    public static String getCid()
    {
        return cid;
    }
    public static String getPwd()
    {
        return pwd;
    }
    public static String getMobile(){return mobile;}
    public static String generateCid(String email)
    {
        String prefix ="IDPiZzAR@";
        int p = email.lastIndexOf("@");
        if(p != -1)
        {
            String cid = email.substring(0,p);
            return prefix + cid.toLowerCase();
        }else
        {
            return null;
        }
    }
}
