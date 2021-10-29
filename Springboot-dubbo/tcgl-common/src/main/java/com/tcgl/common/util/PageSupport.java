package com.tcgl.common.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tcgl.common.constant.SymbolConstant;
import org.apache.commons.lang3.StringUtils;

public final class PageSupport {

    private static volatile PageSupport instance;

    private static boolean flag = true;


    private PageSupport() {

        if (flag) {
            flag = false;
        } else {
            throw new IllegalStateException("Already initialized.");
        }
    }

    public <T> IPage<T> buildPage() {
        Integer pageNum = ServletUtils.getParameterToInt("pageNum");
        Integer pageSize = ServletUtils.getParameterToInt("pageSize");
        return new Page<T>(pageNum == null ? 1 : pageNum, pageSize == null ? 10 : pageSize);
    }

    public static PageSupport getInstance() {
        var result = instance;
        if (result == null) {
            synchronized (PageSupport.class) {
                result = instance;
                if (result == null) {
                    instance = result = new PageSupport();
                }
            }
        }
        return result;
    }

    public <T> QueryWrapper<T> buildSortWrapper() {
        String field = ServletUtils.getParameter("field");
        String sort = ServletUtils.getParameter("sort");

        QueryWrapper<T> queryWrapper = new QueryWrapper<>();

        if(StringUtils.isNotEmpty(field) && StringUtils.isNotEmpty(sort)){
            String[] fields = field.split(SymbolConstant.ID_SEPARATOR);
            String[] sorts = sort.split(SymbolConstant.ID_SEPARATOR);

            if (fields.length != sorts.length) {
                String[] sortsNew = new String[fields.length];
                for (int i = 0; i < fields.length; i++) {
                    sortsNew[i] = i < sorts.length ? sorts[i] : "asc";
                }
                sorts = sortsNew;
            }
            for (int i = 0, len = fields.length; i < len; i++) {
                queryWrapper.orderBy(true, StringUtils.equals(sorts[i], "asc"),
                        CommonStringUtils.toUnderScoreCase(fields[i]).toUpperCase());
            }
//            queryWrapper.orderBy(true, StringUtils.equals(sort, "asc"),
//                     StringUtils.toUnderScoreCase(field).toUpperCase());
        }
        return queryWrapper;
    }
}
