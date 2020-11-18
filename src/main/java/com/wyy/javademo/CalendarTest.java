package com.wyy.javademo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarTest {

    public static void main(String[] args) throws ParseException {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Long startDate = 1585670400000L;
//
//        Long endDate = 1597803772188L;
        Calendar cal1 =Calendar.getInstance();
        cal1.setTime(new Date(startDate));
        System.out.println(cal1.get(Calendar.DAY_OF_MONTH) ==1);


//
//        Calendar cal2 = Calendar.getInstance();
//        cal2.setTime(new Date(endDate));
//
//        System.out.println(isBeginDate(cal1));

//        System.out.println(isSameDay(cal1,cal2));
//
//        System.out.println(getSameDayOfLastMonth(cal1,dateFormat));


        String statistcDate = "2020-04-01";
//        Date queryDate=dateFormat.parse(statistcDate);

        Date queryDate = dateFormat.parse(statistcDate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(queryDate);

        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
        //查询日期不能早于2020-03-01，不能晚于当日
//        Date earliest = dateFormat.parse("2020-03-01");
//
//        Calendar earliestCal = Calendar.getInstance();
//        earliestCal.setTime(earliest);
//        //当前日期
//        Date currentDate = dateFormat.parse(dateFormat.format(new Date()));
//        Calendar currentCal = Calendar.getInstance();
//        currentCal.setTime(currentDate);
//        System.out.println(calendar.getTime());
//        System.out.println(earliestCal.getTime());
//        System.out.println(calendar.before(earliestCal));
//        System.out.println(calendar.after(currentCal));
//        if(calendar.before(earliestCal) || calendar.after(currentCal)){
//            System.out.println("hhhhhhh");
//        }



        //获取前月的第一天
//        Calendar   cal_1=Calendar.getInstance();//获取当前日期
//        cal_1.add(Calendar.MONTH, -1);
//        cal_1.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
//        String firstDay = dateFormat.format(cal_1.getTime());
//        System.out.println("-----1------firstDay:"+firstDay);
//
//        //获取前月的最后一天
//        Calendar cale = Calendar.getInstance();
//        cale.set(Calendar.DAY_OF_MONTH,0);//设置为1号,当前日期既为本月第一天
//        String lastDay = dateFormat.format(cale.getTime());
//        System.out.println("-----2------lastDay:"+lastDay);



//        int first = calendar.getActualMinimum(Calendar.DAY_OF_MONTH); //给定日期的第一天
//
//        calendar.set(Calendar.DAY_OF_MONTH, first);
//
//        String startDate = dateFormat.format(calendar.getTime());
//
//        int last = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
//
//        calendar.set(Calendar.DAY_OF_MONTH,last);
//        String endDate = dateFormat.format(calendar.getTime());
//
//
//        System.out.println(startDate);
//        System.out.println(endDate);

//        System.out.println(getLastDayOfMonth());

//        DateFormat sdf = new SimpleDateFormat("M-d");
//
//        System.out.println(sdf.format(dateFormat.parse("2020-03-03")));

//        Long l = 1590854400000L;
//
//        Calendar calendar1 = Calendar.getInstance();
//        calendar1.setTime(new Date(l));
//
//        System.out.println(getSameDayOfLastMonth(calendar1,dateFormat));
//
//        System.out.println(Integer.MAX_VALUE);


        int i = 1_0000;
        int j = 10000;

        System.out.println(true);


        System.out.println(getFirstSecondOneDay(1598602067000L));

    }

    public static long getFirstSecondOneDay(long data) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(data);
        cal.set(11, 0);
        cal.set(12, 0);
        cal.set(13, 0);
        cal.set(14, 0);
        return cal.getTimeInMillis();
    }


    public  static String getSameDayOfLastMonth(Calendar calendar,DateFormat sdf){
        Calendar sameDayOfLastCalendar = Calendar.getInstance();
        sameDayOfLastCalendar.setTime(calendar.getTime());

        int month=sameDayOfLastCalendar.get(Calendar.MONTH);
//        System.out.println(month);
        // 获取当前日期的前一个月
        sameDayOfLastCalendar.set(Calendar.MONTH,month-1);

        //由于日历计算前一个月是减去前一个月的天数，所以有特殊情况要处理
        Date date = sameDayOfLastCalendar.getTime();
        System.out.println(date);
        if(month ==sameDayOfLastCalendar.get(Calendar.MONTH)){
            sameDayOfLastCalendar.set(Calendar.YEAR,sameDayOfLastCalendar.get(Calendar.YEAR));
            month = --month;
            System.out.println(month);
            sameDayOfLastCalendar.set(Calendar.MONTH,month);
            int lastDay = sameDayOfLastCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            System.out.println(lastDay);
            sameDayOfLastCalendar.set(Calendar.DAY_OF_MONTH, lastDay);

            return sdf.format(sameDayOfLastCalendar.getTime());
        }

        return sdf.format(date);

    }
    public static  String getFirstDay(Calendar calendar,DateFormat dateFormat){
        int first = calendar.getActualMinimum(Calendar.DAY_OF_MONTH); //给定日期的第一天

        calendar.set(Calendar.DAY_OF_MONTH, first);

        return dateFormat.format(calendar.getTime());
    }



    public static String getLastDay(Calendar calendar,DateFormat dateFormat){
        int last = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        calendar.set(Calendar.DAY_OF_MONTH,last);
        return dateFormat.format(calendar.getTime());
    }


    public static Date getLastDayOfMonth() {

        Calendar gregorianCalendar = Calendar.getInstance();
        gregorianCalendar.setTime(new Date());
        gregorianCalendar.set(5, 1);
        gregorianCalendar.add(2, 1);
        gregorianCalendar.add(5, -1);
        return gregorianCalendar.getTime();
    }
    public static boolean isSameDay(Calendar cal1, Calendar cal2) {
        if(cal1 != null && cal2 != null) {
            return cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) && cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
        } else {
            return false;
        }
    }


//    public  static String getSameDayOfLastMonth(Calendar calendar,DateFormat sdf){
//        int month=calendar.get(Calendar.MONTH);
//        System.out.println(month);
//        // 获取当前日期的前一个月
//        calendar.set(Calendar.MONTH,month-1);
//
//        //由于日历计算前一个月是减去前一个月的天数，所以有特殊情况要处理
//        Date date = calendar.getTime();
//        System.out.println(calendar.get(Calendar.MONTH));
//        if(month ==calendar.get(Calendar.MONTH)){
//            calendar.set(Calendar.YEAR,calendar.get(Calendar.YEAR));
//            month = --month;
//            System.out.println(month);
//            calendar.set(Calendar.MONTH,month);
//            int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
//            calendar.set(Calendar.DAY_OF_MONTH, lastDay);
//
//            return sdf.format(calendar.getTime());
//        }
//
//        return sdf.format(date);
//
//    }


   public static  boolean isBeginDate(Calendar calendar){
        return calendar.get(Calendar.YEAR) == 2020 && calendar.get(Calendar.MONTH) == Calendar.MARCH && calendar.get(Calendar.DAY_OF_MONTH) == 1;
   }

}
