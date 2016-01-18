package com.zto.entity;
import java.util.List;
import java.util.Map;

public class PageEntity<T> {

    private int records;
    private int total;
    private int page;
    private int pageSize;
    private int startIndex;
    private List<T> data;

    public PageEntity() {
    }

    public PageEntity(int pageSize, int page) {
        this.pageSize = pageSize;
        this.page = page;
    }

    public int getRecords() {
        return records;
    }

    public void setRecords(int records) {
        this.records = records;
    }

    public int getTotal() {
        return (records + pageSize - 1) / pageSize;
    }

    public int getPage() {
        return page = (page == 0) ? 1 : page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize = (pageSize == 0) ? 10 : pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getStartIndex() {
        return (pageSize * (page - 1)) + 1;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
