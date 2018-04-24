package com.stylefeng.guns.encode;

import org.junit.Test;

public class EncodeTest {
    @Test
    public void testHtml(){
        String str = "<a href='http://www.qq.com'>QQ</a><script>";
        /**
         *  Spring的HtmlUtils进行转义
         */
        //&lt;a href=&#39;http://www.qq.com&#39;&gt;QQ&lt;/a&gt;&lt;script&gt;
        System.out.println(org.springframework.web.util.HtmlUtils.htmlEscape(str));
        //<a href=&#39;http://www.qq.com&#39;>QQ</a><script>
        System.out.println(org.springframework.web.util.HtmlUtils.htmlEscapeDecimal(str));
        //&#x3c;a href=&#x27;http://www.qq.com&#x27;&#x3e;QQ&#x3c;/a&#x3e;&#x3c;script&#x3e;
        System.out.println(org.springframework.web.util.HtmlUtils.htmlEscapeHex(str));

        /**
         *  Spring的HtmlUtils进行还原
         */
        //<a href='http://www.qq.com'>QQ</a><script>
        System.out.println(org.springframework.web.util.HtmlUtils.htmlUnescape("&lt;a href=&#39;http://www.qq.com&#39;&gt;QQ&lt;/a&gt;&lt;script&gt;"));
        //<a href='http://www.qq.com'>QQ</a><script>
        System.out.println(org.springframework.web.util.HtmlUtils.htmlUnescape("<a href=&#39;http://www.qq.com&#39;>QQ</a><script>"));
        //&#60;a href='http://www.qq.com'&#62;QQ&#60;/a&#62;&#60;script&#62;
        System.out.println(org.springframework.web.util.HtmlUtils.htmlUnescape("&#x3c;a href=&#x27;http://www.qq.com&#x27;&#x3e;QQ&#x3c;/a&#x3e;&#x3c;script&#x3e;"));

        /**
         *  apache的StringEscapeUtils进行转义
         */
        //&lt;a href='http://www.qq.com'&gt;QQ&lt;/a&gt;&lt;script&gt;
        System.out.println(org.apache.commons.lang.StringEscapeUtils.escapeHtml(str));

        /**
         *  apache的StringEscapeUtils进行还原
         */
        //&#60;a href='http://www.qq.com'&#62;QQ&#60;/a&#62;&#60;script&#62;
        System.out.println(org.apache.commons.lang.StringEscapeUtils.unescapeHtml("&lt;a href='http://www.qq.com'&gt;QQ&lt;/a&gt;&lt;script&gt;"));
    }
}
