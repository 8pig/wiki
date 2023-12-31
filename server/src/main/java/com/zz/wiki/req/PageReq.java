package com.zz.wiki.req;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * @author zhou
 */

public class PageReq {

    @NotNull(message = "【页码】不能为空")
    private int page;

    @NotNull(message = "【页码】不能为空")
    @Max(value = 100, message = "【每页条数】不能超过1000")
    private int size;


    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "PageReq{" +
                "page=" + page +
                ", size=" + size +
                '}';
    }
}