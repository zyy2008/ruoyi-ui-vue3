package com.ruoyi.util;

import com.ruoyi.admin.domain.vo.TDcIndustryTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IndustryTreeBuilder {

    public static List<TDcIndustryTree> buildTree(List<TDcIndustryTree> nodes) {
        Map<String, TDcIndustryTree> codeMap = new HashMap<>();
        List<TDcIndustryTree> roots = new ArrayList<>();

        // 建立映射
        for (TDcIndustryTree node : nodes) {
            String code = safeTrim(node.getCode());
            if (code != null) {
                codeMap.put(code, node);
            }
        }

        // 构造树
        for (TDcIndustryTree node : nodes) {
            String code = safeTrim(node.getCode());
            String pCode = safeTrim(node.getPCode());

            if (code != null && code.equals(pCode)) {
                roots.add(node); // 作为一级类根节点
            } else {
                TDcIndustryTree parent = codeMap.get(pCode);
                if (parent != null) {
                    if (parent.getChildren() == null) {
                        parent.setChildren(new ArrayList<>());
                    }
                    parent.getChildren().add(node);
                }
            }
        }

        // ✅ 排序整个树
        sortTree(roots);
        return roots;
    }

    private static void sortTree(List<TDcIndustryTree> list) {
        if (list == null || list.isEmpty()) return;

        list.sort((a, b) -> compareCode(a.getCode(), b.getCode()));

        for (TDcIndustryTree node : list) {
            sortTree(node.getChildren()); // 递归对子节点排序
        }
    }

    // ✅ 代码比较逻辑
    private static int compareCode(String a, String b) {
        if (a == null) return -1;
        if (b == null) return 1;

        boolean aIsNum = a.matches("\\d+");
        boolean bIsNum = b.matches("\\d+");

        if (aIsNum && bIsNum) {
            return Integer.compare(Integer.parseInt(a), Integer.parseInt(b));
        } else if (!aIsNum && !bIsNum) {
            return a.compareToIgnoreCase(b);
        } else {
            // 字母排在前，数字排在后（可调整）
            return aIsNum ? 1 : -1;
        }
    }

    private static String safeTrim(String str) {
        return (str == null || str.trim().isEmpty()) ? null : str.trim();
    }
}
