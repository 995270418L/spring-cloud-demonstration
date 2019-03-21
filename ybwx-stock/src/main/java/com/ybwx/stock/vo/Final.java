package com.ybwx.stock.vo;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableMultiset;
import com.ybwx.common.util.ListBuilder;
import com.ybwx.common.util.MapBuilder;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class Final {

    public static final Pattern CHINESE_PATTERN = Pattern.compile("[\\u4e00-\\u9fa5]");   //匹配中文
    public static final Pattern NUMBER_POINT_PATTERN = Pattern.compile("[0-9\\.]");       // 匹配小数
    public static final Pattern OPERATOR_PATTERN = Pattern.compile("[0-9]+([.]{1}[0-9]+){0,1}");       // 匹配小数
    public static final Pattern OPERATOR_CHARSET_PATTERN = Pattern.compile("[+|\\-|*|/]");       // 匹配小数
    public static final String DEATH_INCOME_COMPENSATION_RISK = "身故收入补偿";
    public static final String EDUCATION_DEATH_RISK = "教育身故";
    public static final ImmutableMultiset<String> RISKS = ImmutableMultiset.of(DEATH_INCOME_COMPENSATION_RISK, EDUCATION_DEATH_RISK);

    public static final ImmutableMultimap<Integer, Double> EDUCATION_PRIMARY_SCORE_MAP = ImmutableMultimap.<Integer, Double>builder()
            .put(1,0.1)
            .put(2,0.5)
            .put(3,1.2)
            .put(4,2.0)
            .build();

    public static final ImmutableMultimap<Integer, Double> EDUCATION_SENIOR_SCORE_MAP = ImmutableMultimap.<Integer, Double>builder()
            .put(1,0.2)
            .put(2,3.0)
            .put(3,5.0)
            .put(4,8.0)
            .build();

    public static final Map<Integer, Integer> AGE_MAP = MapBuilder.<Integer, Integer>builder()
            .put(0, 5)
            .put(6, 12)
            .put(13, 17)
            .put(18, 30)
            .put(31, 35)
            .put(36, 39)
            .put(40, 45)
            .put(46, 49)
            .put(50, 55)
            .put(56, 60)
            .put(61, 65)
            .put(66, 75)
            .build();

    public static final Map<Integer, Integer> SELF_AGE_MAP = MapBuilder.<Integer, Integer>builder()
            .put(0, 3)
            .put(4, 6)
            .put(7, 12)
            .put(13, 17)
            .put(18, 30)
            .put(31, 35)
            .put(36, 39)
            .put(40, 45)
            .put(46, 49)
            .put(50, 55)
            .put(56, 60)
            .put(61, 65)
            .put(66, 75)
            .build();

    public static final Map<Integer, Integer> BIG_AGE_MAP = MapBuilder.<Integer, Integer>builder()
            .put(0, 17)
            .put(18, 45)
            .put(46, 9999)
            .build();

    public static final Map<Integer, String> ANNUAL_INCOME_MAP = MapBuilder.<Integer, String>builder()
            .put(5, "10万以下")
            .put(15, "10-20万")
            .put(25, "20-30万")
            .put(40, "30-50万")
            .put(75, "50-100万")
            .put(100, "100万以上")
            .build();

    public static final Map<Integer, String> JOB_TYPE_MAP = MapBuilder.<Integer, String>builder()
            .put(-1, "其他")
            .put(0, "其他")
            .put(1, "企业主")
            .put(2, "司机")
            .put(3, "医护人员")
            .put(4, "IT从业者")
            .put(5, "媒体广告人")
            .put(6, "煤矿石油化工钢铁等行业工作者")
            .build();

    public static final Map<String, String> NEED_LEVEL_MAP = MapBuilder.<String, String>builder()
            .put("健康", "healthy_need_level")
            .put("生活", "life_need_level")
            .put("财富", "wealth_need_level")
            .put("教育", "education_need_level")
            .build();

    public static final Map<Integer, Double> SPOUSE_OLDMAN_GEARS_SCORE_MAP = MapBuilder.<Integer, Double>builder()
            .put(1, 0.1)
            .put(2, 0.3)
            .put(3, 0.7)
            .put(4, 1.5)
            .build();

    public static final Map<Integer, Double> CHILD_GEARS_SCORE_MAP = MapBuilder.<Integer, Double>builder()
            .put(1, 0.2)
            .put(2, 0.4)
            .put(3, 0.8)
            .put(4, 1.2)
            .build();

    public static final Map<Integer, Integer> RATE_RANGE_MAP = MapBuilder.<Integer, Integer>builder()
            .put(0,3)
            .put(3, 6)
            .put(6, 11)
            .put(11, 9999)
            .build();

    public static final Map<Integer, String> RATE_RANGE_VALUE_MAP = MapBuilder.<Integer, String>builder()
            .put(0,"<3")
            .put(3, "3-5")
            .put(6, "6-10")
            .put(11, ">10")
            .build();

    public static final Map<Integer, String> CITY_TYPE_MAP = MapBuilder.<Integer, String>builder()
            .put(0, "未知")
            .put(1, "一线城市")
            .put(2, "省会城市")
            .put(3, "地级市")
            .put(4, "县级市")
            .build();

    public static final Map<Integer, Integer> HOUSEHOLD_INCOME_RANGE_MAP = MapBuilder.<Integer, Integer>builder()
            .put(0, 10)
            .put(11, 20)
            .put(21, 30)
            .put(31, 50)
            .put(51, 100)
            .put(101, 200)
            .build();

    public static final Map<Integer, String> HOUSEHOLD_INCOME_RANGE_VALUE_MAP = MapBuilder.<Integer, String>builder()
            .put(0, "1")
            .put(11, "2")
            .put(21, "3")
            .put(31, "4")
            .put(51, "5")
            .put(101, "6")
            .build();

    public static final Map<String, String> ROLE_MAP = MapBuilder.<String, String>builder()
            .put("1_自己","4_配偶")
            .put("3_妈妈","2_爸爸")
            .put("3_自己妈妈", "2_自己爸爸")
            .put("9_配偶爸爸", "10_配偶妈妈")
            .build();

    public static final Map<String, List<String>> PRODUCT_LIST_MAP = MapBuilder.<String, List<String>>builder()
            .put("定额定期寿险", ListBuilder.<String>builder().add("瑞和定期寿险").build())
            .put("减额定期寿险",ListBuilder.<String>builder().add("安心定期寿险B款").build())
            .put("终身寿险", ListBuilder.<String>builder().add("弘利终身寿险").build())
            .build();

    public static final Map<String, Long> P2P_ID_MAP = MapBuilder.<String, Long>builder()
            .put("瑞和定期寿险",346L)
            .put("安心定期寿险B款",386L)
            .build();

    public static final Map<String, Long> P2PACKAGE_ID_MAP = MapBuilder.<String, Long>builder()
            .put("瑞和定期寿险",854L)
            .put("安心定期寿险B款",914L)
            .build();

    public static final Map<String, String> P2P_STR_MAP = MapBuilder.<String, String>builder()
            .put("瑞和定期寿险","保额作为家庭支柱的身故收入补偿")
            .put("安心定期寿险B款","赔付金额=基本保额*剩余保障期间，随剩余房贷金额每年递减，解决房贷风险")
            .build();

    public static final Map<Integer, Integer> SPOUSE_ELDER_NEED_LEVEL_MAP = MapBuilder.<Integer, Integer>builder()
            .put(0, 2)
            .put(3, 6)
            .put(7, 12)
            .put(13, 15)
            .build();


    public static final Map<Integer, Integer> SPOUSE_ELDER_GEARS_MAP = MapBuilder.<Integer, Integer>builder()
            .put(0, 1)
            .put(3, 2)
            .put(7, 3)
            .put(13, 4)
            .build();

    public static final Map<Integer, Integer> CHILD_NEED_LEVEL_MAP = MapBuilder.<Integer, Integer>builder()
            .put(0, 3)
            .put(4, 6)
            .put(7, 10)
            .put(11, 12)
            .build();

    public static final Map<Integer, Integer> CHILD_GEARS_MAP = MapBuilder.<Integer, Integer>builder()
            .put(0, 1)
            .put(4, 2)
            .put(7, 3)
            .put(11, 4)
            .build();

    public static final Map<String, String> ROLE_TRANSFORM_MAP = MapBuilder.<String, String>builder()
            .put("1_自己","你")
            .put("4_配偶","她")
            .put("3_妈妈", "她")
            .put("3_自己妈妈", "她")
            .put("2_爸爸", "他")
            .put("2_自己爸爸", "他")
            .put("10_配偶妈妈", "她")
            .put("9_配偶爸爸", "他")
            .put("7_孩子", "他")
            .put("7_二孩儿", "他")
            .put("7_三孩儿", "他")
            .build();

    public static final Map<String, String> RISK_AGE_MAN_MAP = MapBuilder.<String, String>builder()
            .put("18-30","较高#很长#更多H")
            .put("31-44","较高#较长#较多M")
            .put("45-48", "较高#较长#一定L")
            .put("49-60", "不高#较短#较少LL")
            .build();


    public static final Map<Integer, Integer> LOGIC_COVERAGE_MAN_AGE_RANGE_MAP = MapBuilder.<Integer, Integer>builder()
            .put(18, 30)
            .put(31, 44)
            .put(45, 48)
            .put(49, 60)
            .build();

    public static final Map<Integer, Integer> LOGIC_COVERAGE_WOMAN_AGE_RANGE_MAP = MapBuilder.<Integer, Integer>builder()
            .put(18, 30)
            .put(31, 44)
            .put(45, 49)
            .build();

    public static final Map<String, String> RISK_AGE_WOMAN_MAP = MapBuilder.<String, String>builder()
            .put("18-30","不高#却很长#较多M")
            .put("31-44","不高#较长#一定L")
            .put("45-49", "较低#较短#较少LL")
            .build();

    public static final Map<Integer, String> RISK_GENDER_MAP = MapBuilder.<Integer, String>builder()
            .put(1,"根据保险公司理赔经验数据，男性身故风险是女性的4倍H")
            .put(2,"根据保险公司理赔经验数据，女性身故风险远低于男性L")
            .build();

    public static final Map<String, String> RISK_MORTAGE_MAP = MapBuilder.<String, String>builder()
            .put("≤3","较低L")
            .put("4-10","较高M")
            .put(">10", "很高H")
            .build();

    public static final Map<String, String> RISK_NICKNAME_MAP = MapBuilder.<String, String>builder()
            .put("1_自己","你#自己")
            .put("4_配偶","你的配偶#他（她）")
            .put("3_自己妈妈", "你的母亲#她")
            .put("2_自己爸爸", "你的父亲#他")
            .put("10_配偶妈妈", "你配偶的母亲#她")
            .put("9_配偶爸爸", "你配偶的父亲#他")
            .build();

    public static final Map<Integer, Integer> CHILD_COVERAGE_AGE_RANGE_MAP = MapBuilder.<Integer, Integer>builder()
            .put(0, 6)
            .put(7, 12)
            .put(13, 18)
            .build();

    public static final Map<String, String> CHILD_COVERAGE_AGE_MAP = MapBuilder.<String, String>builder()
            .put("0-6", "很小#很多H")
            .put("7-12", "不大#较多M")
            .put("13-17","偏大#不多L")
            .build();

    public static final Map<Integer, String> RISK_JOB_MAP = MapBuilder.<Integer, String>builder()
            .put(1, "较低L")
            .put(2, "较低L")
            .put(3,"不高M")
            .put(4,"不高M")
            .put(5,"较高H")
            .put(6,"很高HH")
            .build();
    public static final Map<Integer, String> RISK_CITY_MAP = MapBuilder.<Integer, String>builder()
            .put(1, "一线城市#很高#较高H")
            .put(2, "二线城市#较高#相对较高M")
            .put(3,"普通地级市#不高#相对较低L")
            .put(4,"四线城市#较低#较低LL")
            .build();
    public static final Map<String, String> RISK_GEARS_CHANGE_MAP = MapBuilder.<String, String>builder()
            .put("+1", "${角色}的生活目标对于你目前的家庭年收入而言较高，因此会增加一定的身故收入补偿风险M")
            .put("+2", "${角色}的生活目标对于你目前的家庭年收入而言过高，因此会增加较高的身故收入补偿风险H")
            .put("-1","${角色}的生活目标对于你目前的家庭年收入而言较低，因此会减少一定的身故收入补偿风险L")
            .put("-2","${角色}的生活目标对于你目前的家庭年收入而言很低，因此会减少较大的身故收入补偿风险LL")
            .build();
    public static final Map<Integer, String> RISK_COVERAGE_ROLE_MAP = MapBuilder.<Integer, String>builder()
            .put(1, "自己")
            .put(2, "父亲")
            .put(3,"母亲")
            .put(4,"配偶")
            .put(9,"配偶爸爸")
            .put(10,"配偶妈妈")
            .build();
    public static final Map<Integer, Integer> EDUCATION_MAN_AGE_RANGE_MAP = MapBuilder.<Integer, Integer>builder()
            .put(18, 30)
            .put(31, 48)
            .put(49, 60)
            .build();

    public static final Map<String, String> EDUCATION_MAN_AGE_MAP = MapBuilder.<String, String>builder()
            .put("18-30", "中等")
            .put("31-48", "中等")
            .put("49-60","较高")
            .build();
    public static final Map<Integer, Integer> EDUCATION_CHILD_AGE_MAP = MapBuilder.<Integer, Integer>builder()
            .put(4, 9)
            .put(10, 16)
            .put(17, 22)
            .build();
    public static final Map<String, String> EDUCATION_MAN_OR_WOMAN_AGE_SECOND_MAP = MapBuilder.<String, String>builder()
            .put("4-9", "较短#较少L")
            .put("10-16", "中等#相对中等M")
            .put("17-22","较长#较多H")
            .build();
    public static final Map<Integer, Integer> EDUCATION_WOMAN_AGE_RANGE_MAP = MapBuilder.<Integer, Integer>builder()
            .put(18, 30)
            .put(31, 44)
            .put(45, 60)
            .build();
    public static final Map<String, String> EDUCATION_WOMAN_AGE_MAP = MapBuilder.<String, String>builder()
            .put("18-30", "中等")
            .put("31-44", "中等")
            .put("45-60", "较高")
            .build();
    public static final Map<Integer, String> EDUCATION_GENDER_MAP = MapBuilder.<Integer, String>builder()
            .put(1, "根据保险公司理赔经验数据，男性身故风险是女性的4倍H")
            .put(2, "根据保险公司理赔经验数据，女性身故风险远低于男性L")
            .build();

    public static final Map<Integer, Integer> EDUCATION_COVERAGE_CHILD_AGE_RANGE_MAP = MapBuilder.<Integer, Integer>builder()
            .put(0, 6)
            .put(7, 12)
            .put(13, 18)
            .build();
    public static final Map<String, String> EDUCATION_AGE_STR_MAP = MapBuilder.<String, String>builder()
            .put("0-6", "很高HH")
            .put("7-12", "较高H")
            .put("13-18", "中等M")
            .build();
    public static final Map<Integer, String> EDUCATION_JOB_MAP = MapBuilder.<Integer, String>builder()
            .put(1, "较低L")
            .put(2, "较低L")
            .put(3,"中等M")
            .put(4,"中等M")
            .put(5,"较高H")
            .put(6,"很高HH")
            .build();
    public static final Map<Integer, String> EDUCATION_CITY_MAP = MapBuilder.<Integer, String>builder()
            .put(1, "一线城市#很高#较高H")
            .put(2, "较高#相对较高M")
            .put(3,"普通地级市#中等#相对较低L")
            .put(4,"县级市#较低#较低LL")
            .build();
    public static final Map<String, String> EDUCATION_GEARS_CHANGE_MAP = MapBuilder.<String, String>builder()
            .put("+1", "孩子的${教育目标}对于你目前的家庭年收入而言较高，因此会增加一定的身故收入补偿风险M")
            .put("+2", "孩子的${教育目标}对于你目前的家庭年收入而言过高，因此会增加较高的身故收入补偿风险H")
            .put("-1","孩子的${教育目标}对于你目前的家庭年收入而言较低，因此会减少一定的身故收入补偿风险L")
            .put("-2","孩子的${教育目标}对于你目前的家庭年收入而言很低，因此会减少较大的身故收入补偿风险LL")
            .build();
    /**
     * var education_change_level_map = map[string]string{
     * 	"+1": "孩子的${教育目标}对于你目前的家庭年收入而言较高，因此会增加一定的身故收入补偿风险M",
     * 	"+2": "孩子的${教育目标}对于你目前的家庭年收入而言过高，因此会增加较高的身故收入补偿风险H",
     * 	"-1": "孩子的${教育目标}对于你目前的家庭年收入而言较低，因此会减少一定的身故收入补偿风险L",
     * 	"-2": "孩子的${教育目标}对于你目前的家庭年收入而言很低，因此会减少较大的身故收入补偿风险LL",
     * }
     */
    /**
     * 文案字符串
     */
    public static final String RISK_AGE_MAN_STR = "${角色}的年龄为${年龄}岁，相对其他年龄段，到退休年龄期间的死亡概率${描述}，承担家庭责任的年限${描述}，还需要为家人提供${描述}的生活费用";
    public static final String RISK_AGE_WOMAN_STR = "${角色}的年龄为${年龄}岁，相对其他年龄段，到退休年龄期间的死亡概率${描述}，承担家庭责任的年限${描述}，还需要为家人提供${描述}的生活费用";
    public static final String RISK_MORTAGE_STR = "你的家庭房贷为${房贷金额}万，房贷金额是家庭年收入的${房贷金额/家庭年收入}倍，贷款偿还压力${描述}";
    public static final String RISK_MORTAGE_BIG_STR = "你的家庭房贷为${房贷金额}万，金额较高，身故发生后，无法偿还房贷的可能性很大H";
    public static final String RISK_OLD_MAN_NUM1_STR = "家中有${赡养老人个数}个老人需要赡养，赡养老人的责任${描述}，${角色}发生意外会导致老人无所依靠L";
    public static final String RISK_OLD_MAN_NUM2_STR = "家中有${赡养老人个数}个老人需要赡养，赡养老人的责任${描述}，${角色}发生意外会导致老人无所依靠M";
    public static final String RISK_OLD_MAN_NUM3_STR = "家中有${赡养老人个数}个老人需要赡养，赡养老人的责任${描述}，${角色}发生意外会导致老人无所依靠H";

    public static final String RISK_LITTLE_CHILD_NUM_STR = "家中孩子年龄${描述}，要为他准备的花费${描述}，一旦意身故，孩子的生活费用问题不容忽视";
    public static final String RISK_NOT_BACKBONE_STR = "${角色}不是家庭支柱，需要考虑${称呼}未来的生活费用，因此风险增加M";
    public static final String RISK_JOB_STR= "根据保险精算数据，你${角色}的职业发生意外或疾病导致身故的风险${描述}";
    public static final String RISK_CITY_STR = "家庭生活在${城市}，生活成本${描述}，需要为家庭准备的收入补偿费用${描述}";

    public static final String COVERAGE_MAN_STR = "你${角色}的年龄为${年龄}岁，到退休年龄期间发生身故的概率${描述}，需要承担家庭责任的年限${描述}，需要为家人提供的收入补偿储备${描述}";
    public static final String COVERAGE_MORTAGE_STR = "你的家庭房贷为${房贷金额}万，房贷金额较高，需要增加与房贷金额等额的身故收入补偿保额以保证家人能够继续偿还房贷H";
    public static final String COVERAGE_SUPPORT_MAN_STR = "你${角色}需要赡养家中的${赡养老人个数}个老人，希望达到的生活目标为${档位}，需要根据每个老人未来每年的花销来储备对应的身故收入补偿保额";
    public static final String COVERAGE_CHILD_STR = "你的${孩子角色}目前${孩子年龄}岁，每年花费${描述}，如果想要达到${档位}的生活目标，因此需要准备${描述}的保额来抵御风险";
    public static final String COVERAGE_JOB_STR = "你${角色}的职业发生意外或疾病导致身故的风险${描述}，因此需要储备${描述}的保额来维护风险发生后家人的生活花销";
    public static final String COVERAGE_CITY_STR = "你的${角色}生活在${描述}，生活成本${描述}，因此需要储备${描述}的保额来维护风险发生后家人的生活花销";
    public static final String COVERAGE_INCOME_STEADY_STR = "由于你${角色}的收入不稳定，所以相较于收入稳定的人可以储备一半的保额来保障家人L";

    public static final String EDUCATION_MAN_AGE_STR = "你${角色}的年龄为${年龄}岁，到孩子大学毕业的死亡概率${描述}，需要为孩子提供教育花费的年限${描述}，需要储备的教育费用${描述}";
    public static final String EDUCATION_WOMAN_AGE_STR = "你${角色}的年龄为${年龄}岁，到孩子大学毕业的死亡概率${描述}，需要为孩子提供教育花费的年限${描述}，需要储备的教育费用${描述}";
    public static final String EDUCATION_WOMAN_SEX_STR = "你${角色}的年龄为${年龄}岁，到孩子大学毕业的死亡概率${描述}，需要为孩子提供教育花费的年限${描述}，需要储备的教育费用${描述}";
    public static final String EDUCATION_JOB_STR = "根据保险精算数据，你${角色}的职业发生意外或疾病导致身故的风险${描述}";
    public static final String EDUCATION_CITY_STR = "你${角色}生活在${城市}，教育成本${描述}，需要为孩子准备的教育费用${描述}";
    public static final String EDUCATION_CHILD_STR = "你的${角色}${孩子年龄}岁，需要为他（她）储备的教育费用${描述}";

    public static final String NOT_BACKBONE_SPOUSE_AGE_SCORE = "非支柱配偶年龄分值";
    public static final String YOUNG_CHILD_SCORE = "未成年孩子分值";
    public static final String AGE = "年龄";
    public static final String AGE_SCORE = "年龄分值";
    public static final String AGE_CONTAINS = "$年龄$";
    public static final String AGE_SCORE_REF = "${年龄分值}";
    public static final String INCOME_SCORE = "收入分值";
    public static final String INCOME_SCORE_REF = "${收入分值}";
    public static final String INCOME_NOT_STEADY_SCORE = "收入不稳定分值";
    public static final String INCOME_WHETHER_STEADY_CONTAINS = "$收入是否稳定$";
    public static final String FAMILY_TOTAL_INCOME_REF = "${家庭总收入}";
    public static final String FAMILY_TOTAL_INCOME = "家庭总收入";
    public static final String FAMILY_YEAR_INCOME = "家庭年收入";
    public static final String SELF_INCOME = "本人收入";
    public static final String PERSONAL_YEAR_INCOME = "个人年收入";
    public static final String PERSONAL_YEAR_INCOME_CONTAINS = "$个人年收入$";
    public static final String FAMILY_KEY = "8_家庭";
    public static final String FAMILY_VARIABLE_HEADER = "$非自己$非支柱$家庭";
    public static final String FAMILY_YEAR_INCOME_SCORE = "家庭年收入分值";
    public static final String FAMILY_YEAR_INCOME_SCORE_REF = "${家庭年收入分值}";
    public static final String FAMILY_YEAR_INCOME_CONTAINS = "$家庭年收入$";
    public static final String RISK_POINT_SCORE = "风险点分值";
    public static final String RISK_POINT_SCORE_REF = "${风险点分值}";
    public static final String INCOME = "收入";
    public static final String INCOME_WHETHER_STEADY = "收入是否稳定";
    public static final String INCOME_REF = "${收入}";
    public static final String CHILD = "孩";
    public static final String CHILD_LIFE = "孩子生活";
    public static final String CHILD_PRIMARY_EDUCATION = "初中等孩子教育";
    public static final String CHILD_SENIOR_EDUCATION = "高等孩子教育";
    public static final String  CHILD_LIFE_LEVEL        = "孩子生活水平";
    public static final String  CHILD_LIFE_LEVEL_GEARS  = "孩子生活水平挡位";
    public static final String  CHILD_LIFE_LEVEL_REF    = "${孩子生活水平}";
    public static final String  SPOUSE                  = "配偶";
    public static final String  SPOUSE_LIFE             = "配偶生活";
    public static final String  SPOUSE_LIFE_LEVEL       = "配偶生活水平";
    public static final String  SPOUSE_LIFE_LEVEL_GEARS = "配偶生活水平挡位";
    public static final String SPOUSE_LIFE_LEVEL_REF   = "${配偶生活水平}";
    public static final String ELDER_LIFE_LEVEL        = "老人生活水平";
    public static final String ELDER_LIFE_LEVEL_GEARS  = "老人生活水平挡位";
    public static final String     ELDER_LIFE_LEVEL_REF    = "${老人生活水平}";
    public static final String ELDER_LIFE              = "老人生活";

    public static final String JOB_SCORE                              = "职业分值";
    public static final String MORTGAGE                               = "房贷";
    public static final String MORTGAGE_CONTAINS                      = "$房贷$";
    public static final String MORTGAGE_DEVIDE_FAMILY_INCOME_SCORE    = "房贷比家庭年收入分值";
    public static final String MORTGAGE_DEVIDE_FAMILY_INCOME_CONTAINS = "$房贷比家庭年收入$";
    public static final String  DISEASE_RISK                           = "疾病危险性$职业风险系数";
    public static final String ACCIDENT_RISK                          = "意外危险性$职业风险系数";
    public static final String FINANCIAL_RISK                         = "财务风险$职业风险系数";
    public static final String ANNUAL_INCOME_ENG                      = "annual_income";
    public static final String ANNUAL_INCOME_STEADY_ENG               = "annual_income_steady";
    public static final String CITY                                   = "城市";
    public static final String CITY_SCORE                             = "城市分值";
    public static final String CITY_CONTAINS                          = "$城市$";
    public static final String SUPPORT_SCORE                          = "赡养分值";
    public static final String SUPPORT_OLD_MAN_NUM_CONTAINS           = "$有几个老人需要赡养$";
    public static final String SUPPORT_OLD_MAN_NUM                    = "赡养老人个数";
    public static final String SUPPORT_OLD_MAN_NUM_REF                = "${赡养老人个数}";

    public static final String SPLIT_DOLLOR       = "\\$";
    public static final String DOLLOR       = "$";
    public static final String SHARP      = "\\#";
    public static final String YES          = "是";
    public static final String NO           = "否";
    public static final String MAN          = "男";
    public static final String WOMAN        = "女";
    public static final String OLD          = "老";
    public static final String SELF         = "自己";
    public static final String NOT_SELF     = "非自己";
    public static final String BACKBONE     = "支柱";
    public static final String NOT_BACKBONE = "非支柱";
    public static final String TRUE_STR     = "true";
    public static final String FALSE_STR    = "false";
    public static final String HAVE         = "有";
    public static final String NOT_HAVE     = "无";
    public static final String EMPTY_STR    = "";
    public static final String ZERO_STR     = "0";

    public static final String LIFE                         = "生活";
    public static final String DIE_INCOME_COMPENSATION_RISK = "身故收入补偿";

    public static final String R_H_D_Q_S_X     = "瑞和定期寿险";
    public static final String A_X_D_Q_S_X_B_K = "安心定期寿险B款";
    public static final String J_E_D_Q_S_X     = "减额定期寿险";
    public static final String D_E_D_Q_S_X     = "定额定期寿险";

    public static final String DEFAULT = "default";

    public static final String EDUCATION          = "教育";
    public static final String EDUCATION_LEVEL    = "教育水平";
    public static final String EDUCATION_DIE_RISK = "教育身故";

    public static final String EDUCATION_PRIMARY_LEVEL     = "初中等教育水平";
    public static final String EDUCATION_PRIMARY_LEVEL_REF = "${初中等教育水平}";
    public static final String EDUCATION_SENIOR_LEVEL      = "高等教育水平";
    public static final String EDUCATION_SENIOR_LEVEL_REF  = "${高等教育水平}";

    public static final ImmutableMultimap<String, String> ROLE_REPLACE_PAIRS_MAP = ImmutableMultimap.<String, String>builder()
            .put(AGE_SCORE_REF, AGE_SCORE)
            .put(CHILD_LIFE_LEVEL_REF, CHILD_LIFE_LEVEL)
            .put(EDUCATION_PRIMARY_LEVEL_REF, EDUCATION_PRIMARY_LEVEL)
            .put(EDUCATION_SENIOR_LEVEL_REF, EDUCATION_SENIOR_LEVEL)
            .put(ELDER_LIFE_LEVEL_REF, ELDER_LIFE_LEVEL)
            .put(FAMILY_TOTAL_INCOME_REF, FAMILY_TOTAL_INCOME)
            .put(FAMILY_YEAR_INCOME_SCORE_REF, FAMILY_YEAR_INCOME_SCORE)
            .put(INCOME_REF, INCOME)
            .put(INCOME_SCORE_REF, INCOME_SCORE)
            .put(RISK_POINT_SCORE_REF, RISK_POINT_SCORE)
            .put(SPOUSE_LIFE_LEVEL_REF, SPOUSE_LIFE_LEVEL)
            .build();
}
