package com.ybwx.stock.vo;

import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonVector {

    Integer vectorAnnualIncome;                     // 个人年收入
    Integer vectorAnnualHouseholdIncome;            // 家庭总收入
    String annualIncomeRangeKey;                    // 个人年收入范围获取key
    String extendAgeStr;                          // 年龄Key
    String vectorAmountDevideByHouseholdIncome;     // 	房贷比家庭年收入分值
    Map<String, Object> totalVariable = Maps.newHashMap();
    String annualHouseholdIncome;                 //家庭年收入
    Integer vectorJobType;                        // 职业类型
    Integer familyNeedLevel;                        // 家庭需求等级
    Integer familyInputCapability;                // 家庭投入能力
    Integer vectorMortageAmount;                 // 还贷总额
    Integer cityType;                               // 城市类型
    Integer supportManNum;                          //赡养老人个数
    Integer elderLifeNeedLevel;                     // 老人生活水平挡位
    Integer childrenLifeNeedLevel;                  // 孩子生活水平挡位
    Integer childrenEducationPrimaryNeedLevel;      // 孩子初中等教育生活水平挡位
    Integer childrenEducationSeniorNeedLevel;       // 孩子高等教育生活水平挡位
    Integer spouseLifeNeedLevel;                    //配偶生活水平挡位
    Integer housingLoanRemainingYears;              //房贷剩余还款年限
    Boolean noBackboneSpouse;                       // 是否存在非支柱配偶
}
