package com.entity;

import java.io.Serializable;
import java.util.List;

public class Pager<T> implements Serializable {

    private static final long serialVersionUID = 5894421171405528138L;
    private int pageSize;//每页记录条数
    private int currentPage;//当前页数
    private int totalPage;//总页数
    private int totalRecord;//总记录数
    private List<T> dataList;//数据集合

    public Pager() {
    }

    public Pager(int pageSize, int currentPage, int totalPage, int totalRecord, List<T> dataList) {
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.totalPage = totalPage;
        this.totalRecord = totalRecord;
        this.dataList = dataList;
    }

    @Override
    public String toString() {
        return "Pager{" +
                "pageSize=" + pageSize +
                ", currentPage=" + currentPage +
                ", totalPage=" + totalPage +
                ", totalRecord=" + totalRecord +
                ", dataList=" + dataList +
                '}';
    }

    public Pager(int pageNum, int pageSize, List<T> sourceList) {
        if (sourceList == null) {
            return;
        }
        //总记录条数
        this.totalRecord = sourceList.size();
        //每页记录数
        this.pageSize = pageSize;
        //总页数
        this.totalPage = this.totalRecord / this.pageSize;
        if (this.totalRecord % this.pageSize != 0) {
            this.totalPage = this.totalPage + 1;
        }
        //当前第几页
        this.currentPage = this.totalPage < pageNum ? this.totalPage : pageNum;
        /*if(this.totalPage<pageNum){
            this.currentPage=this.totalPage;
        }else {
            this.currentPage=pageNum;
        }*/
        //起始索引
        int fromIndex = this.pageSize * (this.currentPage - 1);
        //结束索引（不包括）
        int toIndex = this.pageSize * this.currentPage > this.totalRecord ? this.totalRecord : this.pageSize * this.currentPage;
        /*if(this.pageSize*this.currentPage>this.totalRecord){
            toIndex=this.totalRecord;
        }else {
            toIndex=this.pageSize*this.currentPage;
        }*/
        this.dataList = sourceList.subList(fromIndex, toIndex);
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }
}
