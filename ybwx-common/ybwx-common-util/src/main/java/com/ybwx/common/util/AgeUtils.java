package com.ybwx.common.util;

import com.ybwx.common.enums.MathAgeType;
import com.ybwx.common.enums.MathCoverageType;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.Years;

import java.util.Calendar;
import java.util.Date;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

/**
 * Created by shixin on 7/19/16.
 */
public class AgeUtils {

    public static Date getBirthday(int age) {
        return new Date(DateTime.now().minusMonths(age * 12).getMillis());
    }

    public static Date getBirthdayBySocialId(String socialId) {
        String birthday = socialId.substring(6, 14);
        return DateUtil.parseDate(birthday, DateUtil.DATE2);
    }

    /**
     * 计算年龄, 如果不到1岁, 返回天数
     *
     * @param birthday
     * @return
     */
    public static String getAge(Date birthday) {
        LocalDate birthdayDate = new LocalDate(birthday);
        LocalDate now = new LocalDate();

        checkState(now.isAfter(birthdayDate), "invalid birthday");

        Years age = Years.yearsBetween(birthdayDate, now);

        if (age.getYears() == 0) {
            Days days = Days.daysBetween(birthdayDate, now);
            return days.getDays() + "d";
        }

        return age.getYears() + "a";
    }

    public static Integer getAge(String birthday){
        LocalDate birthdayDate = new LocalDate(birthday);
        LocalDate now = new LocalDate();
        checkState(now.isAfter(birthdayDate), "invalid birthday");
        Years age = Years.yearsBetween(birthdayDate, now);
        return age.getYears();
    }


    public static int getAgeOfYearFromBirth(Date birthday, MathAgeType mathAgeType, MathCoverageType mathCoverageType, Date effectiveDate) {
        LocalDate birthdayDate = new LocalDate(birthday);
        LocalDate now = null;
        if (mathCoverageType == MathCoverageType.CURRENT_DATE) {
            now = new LocalDate();
        } else if (mathCoverageType == MathCoverageType.EFFECTIVE_DATE && effectiveDate != null) {
            now = new LocalDate(effectiveDate);
        }

        checkNotNull(now, "date is null");
        checkState(now.isAfter(birthdayDate), "invalid birthday");

        Years yearDiff = Years.yearsBetween(birthdayDate, now);
        int years = yearDiff.getYears();

        if (birthdayDate.getDayOfMonth() == now.getDayOfMonth() &&
                birthdayDate.getMonthOfYear() == now.getMonthOfYear() &&
                mathAgeType == MathAgeType.BIRTHDAY_SECOND_DAY) {
            years--;
        }
        return years;
    }

    public static int getAgeByBirth(Date birthday) {
        int age;
        Calendar now = Calendar.getInstance();
        now.setTime(new Date());// 当前时间

        Calendar birth = Calendar.getInstance();
        birth.setTime(birthday);

        if (birth.after(now)) {//如果传入的时间，在当前时间的后面，返回0岁
            age = 0;
        } else {
            age = now.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
            if (now.get(Calendar.DAY_OF_YEAR) > birth.get(Calendar.DAY_OF_YEAR)) {
                age += 1;
            }
        }
        return age;
    }

    public static String getAge(Date birthday, MathAgeType mathAgeType, MathCoverageType mathCoverageType, Date effectiveDate) {
        LocalDate birthdayDate = new LocalDate(birthday);

        LocalDate now = null;
        if (mathCoverageType == MathCoverageType.CURRENT_DATE) {
            now = new LocalDate();
        } else if (mathCoverageType == MathCoverageType.EFFECTIVE_DATE && effectiveDate != null) {
            now = new LocalDate(effectiveDate);
        }

        checkNotNull(now, "date is null");
        checkState(now.isAfter(birthdayDate), "invalid birthday");

        Years yearDiff = Years.yearsBetween(birthdayDate, now);
        Days dayDiff = Days.daysBetween(birthdayDate, now);

        int years = yearDiff.getYears();
        if (years == 0) {
            return dayDiff.getDays() + "d";
        } else if (birthdayDate.getDayOfMonth() == now.getDayOfMonth() &&
                birthdayDate.getMonthOfYear() == now.getMonthOfYear() &&
                mathAgeType == MathAgeType.BIRTHDAY_SECOND_DAY) {
            years--;
            if (years == 0) {
                return dayDiff.getDays() - 1 + "d";
            }
        }
        return years + "a";
    }

    /**
     * 检验用户的年龄是否在有效区间内.
     *
     * @param minAge
     * @param maxAge
     * @return
     */
    public static boolean invalidAge(String age, String minAge, String maxAge) {
        checkState(StringUtils.isNotBlank(age), "age can not be blank");
        if (StringUtils.isNotBlank(minAge)) {
            if (minAge.endsWith("d")) {
                if (age.endsWith("d")) {
                    if (Integer.parseInt(age.replace("d", "")) < Integer.parseInt(minAge.replace("d", ""))) {
                        return false;
                    }
                }
            } else if (minAge.endsWith("a")) {
                if (age.endsWith("d")) {
                    if (Integer.parseInt(minAge.replace("a", "")) >= 1) {
                        return false;
                    }
                } else if (Integer.parseInt(minAge.replace("a", "")) > Integer.parseInt(age.replace("a", ""))) {
                    return false;
                }
            }
//            else if ("0".equals(minAge) && StringUtils.isBlank(maxAge)) {
//                return true;
//            } else {
//                if (age.endsWith("d")) {
//                    return false;
//                } else {
//                    if (Integer.parseInt(age) < Integer.parseInt(minAge)) {
//                        return false;
//                    }
//                }
//            }
        }

        if (StringUtils.isNotBlank(maxAge)) {
            if (maxAge.endsWith("d")) {
                if (age.endsWith("d")) {
                    if (Integer.parseInt(age.replace("d", "")) > Integer.parseInt(maxAge.replace("d", ""))) {
                        return false;
                    }
                } else {
                    return false;
                }
            } else if (maxAge.endsWith("a")) {
                if (age.endsWith("d")) {
                    if (Integer.parseInt(maxAge.replace("a", "")) < 1) {
                        return false;
                    }
                } else if (Integer.parseInt(maxAge.replace("a", "")) < Integer.parseInt(age.replace("a", ""))) {
                    return false;
                }
            }
//            else {
//                if (!age.endsWith("d")) {
//                    if (Integer.parseInt(age) > Integer.parseInt(maxAge)) {
//                        return false;
//                    }
//                }
//            }
        }

        return true;
    }

//    /**
//     * 得到比较小的年龄
//     *
//     * @param age1
//     * @param age2
//     * @return
//     */
//    public static String getMinAge(String age1, String age2) {
//        checkState(StringUtils.isNotBlank(age1), "require non-blank age1");
//        checkState(StringUtils.isNotBlank(age2), "require non-blank age2");
//
//        if (age1.endsWith("d") && !age2.endsWith("d")) {
//            return age1;
//        }
//
//        if (age2.endsWith("d") && !age1.endsWith("d")) {
//            return age2;
//        }
//
//        if (age1.endsWith("d") && age2.endsWith("d")) {
//            return Math.min(Integer.parseInt(age1.replace("d", "")), Integer.parseInt(age2.replace("d", ""))) + "d";
//        } else {
//            return Math.min(Integer.parseInt(age1), Integer.parseInt(age2)) + "";
//        }
//    }
//
//    /**
//     * 得到比较大的年龄
//     *
//     * @param age1
//     * @param age2
//     * @return
//     */
//    public static String getMaxAge(String age1, String age2) {
//        checkState(StringUtils.isNotBlank(age1), "require non-blank age1");
//        checkState(StringUtils.isNotBlank(age2), "require non-blank age2");
//
//        if (age1.endsWith("d") && !age2.endsWith("d")) {
//            return age2;
//        }
//
//        if (age2.endsWith("d") && !age1.endsWith("d")) {
//            return age1;
//        }
//
//        if (age1.endsWith("d") && age2.endsWith("d")) {
//            return Math.max(Integer.parseInt(age1.replace("d", "")), Integer.parseInt(age2.replace("d", ""))) + "d";
//        } else {
//            return Math.max(Integer.parseInt(age1), Integer.parseInt(age2)) + "";
//        }
//    }

    /**
     * 当前有两种类型
     * SAME_DAY：生日当天年龄增加1
     * 如果最小年龄为1岁 当前时间为2016-11-23
     * 那么用户的最大生日应该是 2015年-11-23
     * 如果最小年龄为0岁 当前时间为2016-11-23
     * 那么用户最大生日应该是 2016-11-23
     * 如果最小年龄为30d 当前时间为2016-11-23
     * 那么用户最大生日应该是 2016-10-24
     * <p>
     * SECOND_DAY：生日第二天年龄增加1
     * 如果最小年龄为1岁 当前时间为2016-11-23
     * 那么用户的最大生日应该是 2015年-11-22
     * 如果最小年龄为0岁 当前时间为2016-11-23
     * 那么用户最大生日应该是 2016-11-23
     * 如果最小年龄为30d 当前时间为2016-11-23
     * 那么用户最大生日应该是 2016-10-23
     *
     * @param minAge      最小年龄
     * @param mathAgeType SAME_DAY OR SECOND_DAY
     * @return
     */
    public static Date getMaxBirthday(String minAge, MathAgeType mathAgeType, int extraDaysOffset) {
        if (minAge.endsWith("d")) {
            int days = Integer.parseInt(minAge.replace("d", ""));
            if (mathAgeType == MathAgeType.BIRTHDAY_SECOND_DAY) {
                days--;
            }
            return DateTime.now().minusDays(days).plusDays(extraDaysOffset).toDate();
        } else if (minAge.endsWith("a")) {
            //我们这里认为就是年了。
            int days = 0;
            if (mathAgeType == MathAgeType.BIRTHDAY_SECOND_DAY) {
                days--;
            }
            return DateTime.now().minusYears(Integer.parseInt(minAge.replace("a", ""))).minusDays(days + extraDaysOffset).toDate();
        }
        return null;
    }

    /**
     * 当前有两种类型
     * SAME_DAY：生日当天年龄增加1
     * 如果最大年龄为1岁 当前时间为2016-11-23
     * 那么用户的最小生日应该是 2014年-11-24
     * 如果最大年龄为0岁 当前时间为2016-11-23
     * 那么用户最小生日应该是 2015-11-24
     * 如果最大年龄为30d 当前时间为2016-11-23
     * 那么用户最小生日应该是 2016-10-24
     * <p>
     * SECOND_DAY：生日第二天年龄增加1
     * 如果最大年龄为1岁 当前时间为2016-11-23
     * 那么用户的最小生日应该是 2014年-11-23
     * 如果最大年龄为0岁 当前时间为2016-11-23
     * 那么用户最小生日应该是 2015-11-23
     * 如果最大年龄为30d 当前时间为2016-11-23
     * 那么用户最小生日应该是 2016-10-25
     *
     * @param maxAge      最大年龄
     * @param mathAgeType SAME_DAY OR SECOND_DAY
     * @return
     */
    public static Date getMinBirthday(String maxAge, MathAgeType mathAgeType, int extraDaysOffset) {
        if (maxAge.endsWith("d")) {
            int days = Integer.parseInt(maxAge.replace("d", ""));
            if (mathAgeType == MathAgeType.BIRTHDAY_SECOND_DAY) {
                days++;
            }
            return DateTime.now().minusDays(days).plusDays(extraDaysOffset).toDate();
        } else {
            //我们这里认为就是年了。
            int days = 1;
            if (mathAgeType == MathAgeType.BIRTHDAY_SECOND_DAY) {
                days++;
            }
            int i = Integer.parseInt(maxAge.replace("a", "")) + 1;
            return DateTime.now().minusYears(i).plusDays(days + extraDaysOffset).toDate();
        }
    }


    /**
     * 当前有两种类型
     * SAME_DAY：生日当天年龄增加1
     * 如果最大年龄为1岁 当前时间为2016-11-23
     * 那么用户的最小生日应该是 2014年-11-24
     * 如果最大年龄为0岁 当前时间为2016-11-23
     * 那么用户最小生日应该是 2015-11-24
     * 如果最大年龄为30d 当前时间为2016-11-23
     * 那么用户最小生日应该是 2016-10-24
     * <p>
     * SECOND_DAY：生日第二天年龄增加1
     * 如果最大年龄为1岁 当前时间为2016-11-23
     * 那么用户的最小生日应该是 2014年-11-23
     * 如果最大年龄为0岁 当前时间为2016-11-23
     * 那么用户最小生日应该是 2015-11-23
     * 如果最大年龄为30d 当前时间为2016-11-23
     * 那么用户最小生日应该是 2016-10-25
     *
     * @param maxAge      最大年龄
     * @param mathAgeType SAME_DAY OR SECOND_DAY
     * @param mathCoverageType EFFECTIVE_DATE CURRENT_DATE
     * @return
     */
    public static Date getMinBirthday(String maxAge, MathAgeType mathAgeType, MathCoverageType mathCoverageType) {
        if (maxAge.endsWith("d")) {
            int days = Integer.parseInt(maxAge.replace("d", ""));
            if (mathAgeType == MathAgeType.BIRTHDAY_SECOND_DAY) {
                days--;
            }
            if (mathCoverageType == MathCoverageType.EFFECTIVE_DATE) {
                days++;
            }
            return DateTime.now().minusDays(days).toDate();
        } else {
            //我们这里认为就是年了。
            int days = 1;
            if (mathAgeType == MathAgeType.BIRTHDAY_SECOND_DAY) {
                days--;
            }
            if (mathCoverageType == MathCoverageType.EFFECTIVE_DATE) {
                days++;
            }
            int i = Integer.parseInt(maxAge.replace("a", "")) + 1;
            return DateTime.now().minusYears(i).plusDays(days).toDate();
        }
    }

    /**
     * 当前有两种类型
     * SAME_DAY：生日当天年龄增加1
     * 如果最小年龄为1岁 当前时间为2016-11-23
     * 那么用户的最大生日应该是 2015年-11-23
     * 如果最小年龄为0岁 当前时间为2016-11-23
     * 那么用户最大生日应该是 2016-11-23
     * 如果最小年龄为30d 当前时间为2016-11-23
     * 那么用户最大生日应该是 2016-10-24
     * <p>
     * SECOND_DAY：生日第二天年龄增加1
     * 如果最小年龄为1岁 当前时间为2016-11-23
     * 那么用户的最大生日应该是 2015年-11-22
     * 如果最小年龄为0岁 当前时间为2016-11-23
     * 那么用户最大生日应该是 2016-11-23
     * 如果最小年龄为30d 当前时间为2016-11-23
     * 那么用户最大生日应该是 2016-10-23
     *
     * @param minAge      最小年龄
     * @param mathAgeType SAME_DAY OR SECOND_DAY
     * @param mathCoverageType EFFECTIVE_DATE CURRENT_DATE
     * @return
     */
    public static Date getMaxBirthday(String minAge, MathAgeType mathAgeType, MathCoverageType mathCoverageType) {
        if (minAge.endsWith("d")) {
            int days = Integer.parseInt(minAge.replace("d", ""));
            if (mathAgeType == MathAgeType.BIRTHDAY_SECOND_DAY) {
                days++;
            }
            if (mathCoverageType == MathCoverageType.EFFECTIVE_DATE) {
                days--;
            }
            return DateTime.now().minusDays(days).toDate();
        } else if (minAge.endsWith("a")) {
            //我们这里认为就是年了。
            int days = 0;
            if (mathAgeType == MathAgeType.BIRTHDAY_SECOND_DAY) {
                days++;
            }
            if (mathCoverageType == MathCoverageType.EFFECTIVE_DATE) {
                days--;
            }
            return DateTime.now().minusYears(Integer.parseInt(minAge.replace("a", ""))).minusDays(days).toDate();
        }
        return null;
    }

    public static void main(String args[]) {
        Integer a = getAge("2017-01-13");
        System.out.println(a);
        /*//测试特殊年龄加1的情况
        checkState("1".equals(getAge(DateUtils.parseDate("20141104", "yyyyMMdd"),2)));
        checkState("365d".equals(getAge(DateUtils.parseDate("20151104", "yyyyMMdd"),2)));
        checkState("2".equals(getAge(DateUtils.parseDate("20141104", "yyyyMMdd"),1)));
        //测试天和年的比较
        checkState(!invalidAge("30d","1","50"));
        checkState(invalidAge("30d","0","50"));
        checkState(!invalidAge("365d","1","50"));
        //测试天河天的比较
        checkState(invalidAge("1d","1d","50d"));
        checkState(invalidAge("50d","1d","50d"));
        checkState(!invalidAge("0d","1d","50d"));
        checkState(!invalidAge("51d","1d","50d"));
        //测试年和年的比较
        checkState(invalidAge("1","1","50"));
        checkState(invalidAge("50","1","50"));
        checkState(invalidAge("49","1","50"));
        checkState(!invalidAge("51","1","50"));
        //测试年和天的比较
        checkState(!invalidAge("1","1d","50d"));
        checkState(!invalidAge("50","1d","50d"));
        checkState(!invalidAge("49","1d","50d"));
        checkState(!invalidAge("51","1d","50d"));*/

//        System.out.println(DateUtils.formatDate(AgeUtils.getMinBirthday("60", 1)));
//        System.out.println(DateUtils.formatDate(AgeUtils.getMaxBirthday("30d", 1)));

//        System.out.println(DateUtil.formatDate(AgeUtils.getMinBirthday("70a", MathAgeType.BIRTHDAY_SAME_DAY, MathCoverageType.EFFECTIVE_DATE)));
//        System.out.println(DateUtil.formatDate(AgeUtils.getMaxBirthday("46a", MathAgeType.BIRTHDAY_SAME_DAY, MathCoverageType.EFFECTIVE_DATE)));

    }

}
