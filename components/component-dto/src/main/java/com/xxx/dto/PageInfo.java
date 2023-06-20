package com.xxx.dto;


import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * @author tanchusheng
 * @date 2023/6/20
 */
public class PageInfo<T> implements Serializable {
    private static final long serialVersionUID = 2222349771904078391L;

    private List<T> records = Collections.emptyList();
    /**
     * 总条数
     * @mock 10
     */
    @NotNull
    private Long total = 0L;
    /**
     * 每页条数
     * @mock 10
     */
    @NotNull
    private int size = 10;
    /**
     * 当前页
     * @mock 1
     */
    @NotNull
    private int current = 1;

    public PageInfo() {
        this.records = Collections.emptyList();
    }

    public PageInfo(Integer current, Integer size) {
        this(current, size, 0L);
    }

    public PageInfo(int current, int size, Long total) {
        this.records = Collections.emptyList();
        this.total = 0L;
        if (current > 1) {
            this.current = current;
        }
        this.size = size;
        this.total = total;
    }

    /**
     * 总页数
     *
     * @return
     */
    public Integer getPages() {
        if (this.getSize() == 0) {
            return 0;
        } else {
            Integer pages = ((Long) (this.getTotal() / this.getSize())).intValue();
            if (this.getTotal() % this.getSize() != 0) {
                ++pages;
            }
            return pages;
        }
    }

    public boolean hasNext() {
        return this.current < this.getPages();
    }


    public List<T> getRecords() {
        return records;
    }

    public PageInfo<T> setRecords(List<T> records) {
        this.records = records;
        return this;
    }

    public Long getTotal() {
        return total;
    }

    public PageInfo<T> setTotal(Long total) {
        this.total = total;
        return this;
    }

    public int getSize() {
        return size;
    }

    public PageInfo<T> setSize(int size) {
        this.size = size;
        return this;
    }

    public int getCurrent() {
        return current;
    }

    public PageInfo<T> setCurrent(int current) {
        this.current = current;
        return this;
    }
}
