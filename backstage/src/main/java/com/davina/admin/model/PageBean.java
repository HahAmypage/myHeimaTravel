package com.davina.admin.model;

import java.util.List;

public class PageBean<T> {

    private List<T> dataList;   //当前页列表数据   （业务封装，需要查询数据库当前页的数据）
    private int fristPage=1;    //首页             （可有可无，默认值永远是1）
    private int prePage;        //上一页           （内部封装，当前页-1）
    private int curPage;        //当前页           （业务封装，因为要查询哪一页是由前端用户指定的。）
    private int nextPage;       //下一页            (内部封装，当前页+1)
    private int totalPage;      //总页数，末页      (内部封装，总页数是有总条数与每页大小决定的)
    private int count;          //总条数            (业务封装，因为要查询数据库有多少条才是多少条)
    private int pageSize;       //每页大小         （业务封装，因为可以根据业务需要设置分页每页大小）

    private int beginPage;
    private int endPage;

    public int getBeginPage() {
        //情况一：总页数小于10
        if (getTotalPage() <= 10){
            beginPage = 1;
        }else {
            //情况二：总页数大于10
            //正常
            beginPage = curPage - 5;
            //头溢出
            if (beginPage < 1){
                beginPage = 1;
            }else if (beginPage + 9 > getTotalPage()){
                //尾溢出
                beginPage = getTotalPage() - 9;
            }
        }
        return beginPage;
    }

    public void setBeginPage(int beginPage) {
        this.beginPage = beginPage;
    }

    public int getEndPage() {

        //情况一：总页数小于10
        if (getTotalPage() <= 10){
            endPage = getTotalPage();
        }else {
            //情况一：总页数小于10
            //正常
            endPage = curPage + 4;
            //头溢出
            if (this.endPage - 9 < 1){
                endPage = 10;
            }else if (endPage > getTotalPage()){
                endPage = getTotalPage();
            }
        }

        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public PageBean() {
    }

    public PageBean(List<T> dataList, int fristPage, int prePage, int curPage, int nextPage, int totalPage, int count, int pageSize) {
        this.dataList = dataList;
        this.fristPage = fristPage;
        this.prePage = prePage;
        this.curPage = curPage;
        this.nextPage = nextPage;
        this.totalPage = totalPage;
        this.count = count;
        this.pageSize = pageSize;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public int getFristPage() {
        return fristPage;
    }

    public void setFristPage(int fristPage) {
        this.fristPage = fristPage;
    }

    public int getPrePage() {
        if(curPage>1) {
            prePage = curPage - 1;
        }
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getNextPage() {
        if(curPage<totalPage){
            nextPage=curPage+1;
        }
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getTotalPage() {
        totalPage=count%pageSize==0?count/pageSize:count/pageSize+1;
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
