package com.web.utils;

public class RandomKey {

    static String listKey = "1234567890qwertyuiopasdfghjklzxcvbnm";

    public static String genKey(){
        String key = "";
        Integer doDai = listKey.length() - 1;

        for(int i=0; i< 6; i++){
            Integer viTri = (int) (Math.random() * doDai);
            Character kiTu = listKey.charAt(viTri);
            String s = String.valueOf(kiTu);
            key += s;
        }
        return key;
    }

    public static void main(String[] args) {
        System.out.println(genKey());
    }
}
