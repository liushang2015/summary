//package com.liushang.test;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class Demo2 {
//    static  String sql = "insert into persons (s1, s2 , s3, s4 ) values(?,?,?,?)";
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//    public static void main(String[] args) {
//        File file = new File("C:a.txt");
//        BufferedReader buf = null;
//        try {
//            buf = new BufferedReader(new FileReader(file));
//            String temp = null;
//            List<Object[]> sqlList = new ArrayList<>();
//            while ((temp = buf.readLine()) != null) {
//                System.out.println(temp);
//                if (temp != null && temp != "") {
//                    String[] ss = temp.split(",");
//                    if (ss.length == 4) {
//                        Object[] obj = new Object[4];
//                        for (int i = 0; i < obj.length; i++) {
//                            obj[i]=ss[i];
//                        }
//                        sqlList.add(obj);
//                    }
//                    if(sqlList.size()%1000==0){
//                        jdbcTemplate.batchUpdate(sql, sqlList);
//                        sqlList.clear();
//                    }
//
//                }
//            }
//            if(sqlList.size()>0){
//                jdbcTemplate.batchUpdate(sql, sqlList);
//                sqlList.clear();
//            }
//        } catch (Exception e) {
//            e.getStackTrace();
//        } finally {
//            if (buf != null) {
//                try {
//                    buf.close();
//                } catch (IOException e) {
//                    e.getStackTrace();
//                }
//            }
//        }
//    }
//}
