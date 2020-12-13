package com.liushang.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Global {
    public static ObjectMapper DEFAULT_OBJECT_MAPPER = new ObjectMapper();
    public static ObjectMapper DEFAULT_OBJECT_MAPPER_NOT_NULL = new ObjectMapper();
    public static Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
    public static final Calendar DEFAULT_CALENDAR = Calendar.getInstance();
    public static final String DEFAULT_SEPARATOR = ";";
    public static final String DEFAULT_SPLITE_CHARACTER = "\n\r";
    public static HanyuPinyinOutputFormat pinyinFormat = new HanyuPinyinOutputFormat();
    public static final BigDecimal zero = new BigDecimal(0);
    public static final BigDecimal one = new BigDecimal(1);
    public static final BigDecimal hundred = new BigDecimal(100);

    public Global() {
    }

    public static SimpleDateFormat GENERAL_DF() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    public static SimpleDateFormat GENERAL_DF_NOT_SS() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm");
    }

    public static SimpleDateFormat GENERAL_DF_NOT_TIME() {
        return new SimpleDateFormat("yyyy-MM-dd");
    }

    public static SimpleDateFormat DF_YYYYMMDD() {
        return new SimpleDateFormat("yyyyMMdd");
    }

    public static SimpleDateFormat DF_YYYYMMDDHH() {
        return new SimpleDateFormat("yyyyMMddHH");
    }

    public static SimpleDateFormat DF_YYYYMMDDHHmmss() {
        return new SimpleDateFormat("yyyyMMddHHmmss");
    }

    public static SimpleDateFormat DF_YYMMDDHH() {
        return new SimpleDateFormat("yyMMddHH");
    }

    public static SimpleDateFormat DF_YYMMDD() {
        return new SimpleDateFormat("yyMMdd");
    }

    static {
        pinyinFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        pinyinFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        pinyinFormat.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);
        DEFAULT_OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        DEFAULT_OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        DEFAULT_OBJECT_MAPPER.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        DEFAULT_OBJECT_MAPPER.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        DefaultSerializerProvider sp = new DefaultSerializerProvider.Impl();
        sp.setNullValueSerializer(new NullSerializer());
        DEFAULT_OBJECT_MAPPER_NOT_NULL.setSerializerProvider(sp);
    }
}
