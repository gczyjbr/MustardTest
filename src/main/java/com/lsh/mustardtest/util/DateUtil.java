package com.lsh.mustardtest.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * 刘森华
 * 2019/04/21
 */

public class DateUtil {
    public static void main(String args[]) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS");
        String time = format.format(new Date());
        System.out.println(time);
        Scanner scanner = new Scanner(System.in);
        System.out.print("输入租用月数: ");
        int monthNumber = scanner.nextInt();
        System.out.println("租用月数为: " + monthNumber);
        String endDate = getEndDate(time, monthNumber);
        System.out.println("到期时间为: " + endDate);

    }

    public static String getEndDate(String startDate, int monthNumber) {
        Integer year = Integer.valueOf(startDate.substring(0, 4));
        Integer month = Integer.valueOf(startDate.substring(5, 7));
        Integer day = Integer.valueOf(startDate.substring(8, 10));
        Integer hour = Integer.valueOf(startDate.substring(11, 13));
        Integer minute = Integer.valueOf(startDate.substring(14, 16));
        Integer second = Integer.valueOf(startDate.substring(17, 19));
        Integer millisecond = Integer.valueOf(startDate.substring(20, 22));

        int totalMonth = month + monthNumber;
        int yearNumber = 0;
        if (totalMonth > 12) {
            yearNumber = totalMonth / 12;
            year += yearNumber;
            monthNumber = totalMonth % 12;
            month = monthNumber;
        } else {
            month = totalMonth;
        }
        String endDate = year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second + ":" + millisecond;
        return endDate;
    }
}
