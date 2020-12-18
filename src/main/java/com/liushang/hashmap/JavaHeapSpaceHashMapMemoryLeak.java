//package com.liushang.hashmap;
//
//import java.util.HashMap;
//
///**
// * HashMap内存泄漏问题
// * -Xmx3M -Xms3M
// * 重写equals的对象每个值比较，解决内存泄漏问题
// */
//public class JavaHeapSpaceHashMapMemoryLeak {
//    public static void main(String[] args) {
//        HashMap<HashKey2, Integer> map = new HashMap<HashKey2, Integer>(1000);
//        int counter = 0;
//        while (true) {
////            Thread.sleep(50);
//            // new 多个不同对象 参数都是相同的内容，但是对象的内存地址不同。
//            HashKey2 p = new HashKey2("mayikt", "644064779");
//            // 如果没有重写 equals 底层默认采用==比较两个对象内存地址 强引用
//            map.put(p, 1);
////            System.out.println(p);
//            counter++;
//            if (counter % 1000 == 0) {
//                System.out.println("map size: " + map.size());
//                System.out.println("运行" + counter
//                        + "次后，可用内存剩余" + Runtime.getRuntime().freeMemory() / (1024 * 1024) + "MB");
//            }
//        }
//    }
//    }
//    class HashKey2 {
//        private final String id;
//        private String name;
//
//        public HashKey2(String name, String id) {
//            this.name = name;
//            this.id = id;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//        @Override
//        public int hashCode() {
//            return id.hashCode();
//        }
//
//        /**
//         * hashMap 不管怎么new 多少次 只会key 只会引入一次 不会继续添加。
//         *
//         * @param obj
//         * @return //
//         */
//    @Override
//        public boolean equals(Object obj) {
//            if (obj instanceof HashKey2)
//                return name.equals(((HashKey2) obj).name) && id.equals(((HashKey2) obj).id);
//            else
//                return false;
//        }
//}
