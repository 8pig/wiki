package com.zz.wiki.resp;

import java.util.List;

/**
 * @author zhou
 */
public class PageResp<T> {

    private Long total;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    private List<T> list;


}