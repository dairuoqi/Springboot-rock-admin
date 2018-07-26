package com.rock.geological.utils;

import java.util.List;

public class PageUtil<T> {
    private static final long serialVersionUID = 1L;
    private int total;
    private List<T> list;

    public PageUtil(List<T> list, int total) {
        this.list = list;
        this.total = total;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
