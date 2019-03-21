package com.ybwx.stock.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalculateData {
                                                        // 初始化家庭结构的变量
    Integer familyTotalIncome;                          // 家庭总收入
    Integer backboneNum;                                // 家庭支柱数量
    Boolean lessThanbackbone;                           // 是否有一个人的收入低于家庭收入的30%
    Integer childrenNum;                                // 孩子数量
    List<RoleAge> littleChildNumList;                           // 未成年孩子年龄和身份标识
    List<RoleAge> elderRoleAgeList;                         // 老人的年龄和身份列表
    Integer noSocialNum;                                // 没社保成员数量
    Map<String, List<String>> roleKey;                  // 一个角色对应的测算key列表
    Map<String, List<String>> keyRowData;               // 计算key  ==>> 数据列表
    Map<String, String> identityToRole;                 // "1_自己" ==>> "支柱-中-男"
    Map<String, Integer> identityAgeMap;                // "1_自己" ==>> 14
    Map<String, Integer> identityBackbone;              // 判断当前角色是否为非支柱配偶 "1_自己" ===>> 1(1:支柱 other:非支柱)
    Map<String, String> identityBackboneKey;            // 非支柱配偶的身份信息
    Map<Integer, Map<Integer, Double>> ageValueMap;     // 年龄对应分值
                                                        // 以下变量是全局的，动态赋值
    String primaryChildScore;                         // 初中等孩子教育水平分值
    String seniorChildScore ;                         // 高等孩子教育水平分值
    Integer defaultLifeLevel;                         // 初始化家庭的时候默认的生活水平
    Integer defaultEducationLevel ;                   // 初始化家庭的时候默认的教育水平

}
