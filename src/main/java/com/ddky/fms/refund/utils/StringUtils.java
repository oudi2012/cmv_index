package com.ddky.fms.refund.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.Collection;
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author a
 */
public class StringUtils {

    private static final Logger logger = LoggerFactory.getLogger(StringUtils.class);

    private static final Pattern pattern = Pattern.compile("^-?[0-9]+");

    public static boolean isEmpty(String str) {
        return !isNotEmpty(str);
    }

    public static boolean isNotEmpty(String str) {
        return str!= null && str.trim().length() != 0;
    }

    /**
     * 验证是否为数字组成的字符串,不包含空字符串与浮点型数值
     *  @author zhumingzhou
     *  @date 2015年3月26日
     *	@param str
     *	@return
     */
    public static boolean isNumeric(String str) {
        if (isEmpty(str)) {
            return false;
        }
        int sz = str.length();
        for (int i = 0; i < sz; i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isInteger(String str){
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
    }

    public static boolean isBigDecimal(String str){
        try {
            new BigDecimal(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * 过滤html标记，防止注入
     * @param str
     * @return
     */
    public static String filter(String str) {
        if (str != null && str.length() > 0) {
            StringBuilder sb = new StringBuilder();
            char c;
            for (int i = 0; i < str.length(); i++) {
                c = str.charAt(i);
                if (c == '<') {
                    c = '〈';
                } else if (c == '>') {
                    c = '〉';
                } else if (c == '&') {
                    c = '＆';
                } else if (c == '\"') {
                    c = '“';
                } else if (c == '\'') {
                    c = '‘';
                }
                sb.append(c);
            }
            return sb.toString();
        } else {
            return "";
        }
    }

    /**
     * 手机号码打马赛克,中间四位置为*
     *  @author zhumingzhou
     *  @date 2015年12月2日
     *	@param mobile
     *	@return
     */
    public static String formatMobile(String mobile) {
        if(isNumeric(mobile) && mobile.length() == 11) {
            return mobile.substring(0, 4) +
                    "****" +
                    mobile.substring(8);
        }
        return mobile;
    }

    /**
     * 身份证号码打马赛克,中间四位置为*
     *  @author zhumingzhou
     *  @date 2015年12月2日
     *	@param idCard
     *	@return
     */
    public static String formatIdCard(String idCard) {
        if( idCard.length() == 18) {
            return idCard.substring(0, 6) +
                    "********" +
                    idCard.substring(14);
        }
        return idCard;
    }
    /**
     * 比较两个版本号大小， 大于返回1， 等于返回0，小于返回-1
     *  @author zhumingzhou
     *  @date 2016年1月19日
     *	@param v1 版本1
     *	@param v2 版本2
     *	@return
     */
    public static int compareVersion(String v1, String v2) {
        String[] numArr1 = v1.split("\\.");
        String[] numArr2 = v2.split("\\.");
        int length = Math.min(numArr1.length, numArr2.length);

        //比较同级版本号数字大小
        for (int i = 0; i < length; i++) {
            int num1 = Integer.parseInt(numArr1[i]);
            int num2 = Integer.parseInt(numArr2[i]);
            if(num1 > num2) {
                return 1;
            }
            if(num1 < num2) {
                return -1;
            }
        }
        //比较版本号长度
        return Integer.compare(numArr1.length, numArr2.length);
        //版本号相同
    }
    /***
     * 处理金额
     * @param price
     * @return
     */
    public static String formatMoney(String price) {
        if (price == null || "".equals(price)) {
            return price;
        }
        if (price.contains(".")) {
            String[] items = price.split("\\.");
            if (items.length == 0) {
                return "0.00";
            }
            if (items.length == 1) {
                price = Long.valueOf(items[0]) + ".00";
                return price;
            }
            if (isNotEmpty(items[0]) && isNotEmpty(items[1])) {
                if (Long.parseLong(items[1]) < 10 && items[1].length() == 2) {
                    price = Long.valueOf(items[0]) + ".0"
                            + Long.valueOf(items[1]);
                } else if (Long.parseLong(items[1]) < 10
                        && items[1].length() == 1) {
                    price = Long.valueOf(items[0]) + "."
                            + Long.valueOf(items[1]) + "0";
                }
                return price;
            }
            if (price.indexOf(".") == 0) {
                if (Long.parseLong(items[1]) < 10 && items[1].length() == 2) {
                    price = "0.0" + Long.valueOf(items[1]);
                } else if (Long.parseLong(items[1]) < 10
                        && items[1].length() == 1) {
                    price = "0." + Long.valueOf(items[1]) + "0";
                } else {
                    price = "0." + Long.valueOf(items[1]);
                }
                return price;
            }
            price = Long.valueOf(items[0]) + "0.0";
            return price;
        }
        long money = Long.parseLong(price);
        long mod = money % 100;
        long imod = money / 100;
        String temp = "";
        if ((mod < 10) && (mod > -10)) {
            temp = ".0" + mod;
        } else {
            temp = "." + mod;
        }
        return "" + imod + temp;
    }

    /**
     * 将前端显示价格转为数据库存储价格
     * @author zhumingzhou
     * @date 2016年5月12日
     * @param f
     * @return
     */
    public static Long parsePrice(String f) {
        BigDecimal bd = new BigDecimal(f);
        return bd.multiply(new BigDecimal(100)).longValue();
    }

    /**
     *
     * 集合转换成 特字符分隔的字符串
     * @param coll coll
     * @param separator 分隔符
     * @param show 每个数据前后是否需要添加引号 '
     * @return
     *
     */
    public static String collectionToString(Collection<?> coll, String separator, boolean show){
        if(null == coll || coll.isEmpty()){
            return "";
        }
        separator = (null == separator||"".equals(separator))?",":separator;
        StringBuilder buff = new StringBuilder();
        for(Object o : coll){
            if(show){
                buff.append("'").append(o).append("'").append(separator);
            }else{
                buff.append(o).append(separator);
            }
        }
        return buff.substring(0, buff.lastIndexOf(separator));
    }

    /**
     * 按分隔符分割字符
     *
     * @param str
     *            原始字符
     * @param spec
     *            分隔
     * @return 分割后的字符链表
     */
    public static List<Long> str2LongListByE(String str, String spec) {
        if (str == null) {
            str = "";
        }
        Vector<Long> vt = new Vector<>();
        String[] strs = str.split(spec);
        for(String s:strs){
            vt.addElement(Long.valueOf(s));
        }
        return vt;
    }
    /**
     * 将字符串中非GBK字符编码为\\u格式，解决GBK数据库不能储存UTF-8问题
     * @param str
     * @return
     */
    public static String encodeNoGBK(String str) {
        StringBuilder sb = new StringBuilder();
        char c;
        String cstr;
        CharsetEncoder encoder = Charset.forName("GBK").newEncoder();
        for (int i = 0; i < str.length(); i++) {
            c = str.charAt(i);
            if (!encoder.canEncode(c)) {
                cstr = Integer.toHexString((int) c);
                while (cstr.length() < 4) {
                    cstr = '0' + cstr;
                }
                sb.append("\\u").append(cstr);
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
    /**
     * 将字符串中\\u格式字符进行解码，解决GBK数据库不能储存UTF-8问题
     * @param str str
     * @return
     */
    public static String decodeNoGBK(String str) {
        StringBuilder sb = new StringBuilder();
        char c;
        for (int i = 0; i < str.length(); i++) {
            c = str.charAt(i);
            if (c == '\\' && i + 5 < str.length() && str.charAt(i + 1) == 'u') {
                try {
                    c = (char) Integer.parseInt(str.substring(i + 2, i + 6), 16);
                    i += 5;
                } catch (Exception ignored) {
                }
            }
            sb.append(c);
        }
        return sb.toString();
    }

}
