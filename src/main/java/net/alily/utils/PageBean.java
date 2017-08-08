/*
   Class PageBean
   Author Mr.Z
   Date 2016.06.02
 */

package net.alily.utils;

import java.util.ArrayList;

//封装分页参数
public class PageBean<T> {
	//当前页
	private int currentPage = 0;
	//每页显示的条数
	private int pageCount = 10;
	//总记录数
	private int totalCount;
	//总页数
	private int totalPage;
	//每页的数据
	private ArrayList<T> pageData;
	//封装查询条件
	private Condition condition;
	
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalPage() {
		if (totalCount % pageCount == 0){
			totalPage = totalCount / pageCount;
		} else {
			totalPage = totalCount / pageCount +1;
		}
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public ArrayList<T> getPageData() {
		return pageData;
	}
	public void setPageData(ArrayList<T> pageData) {
		this.pageData = pageData;
	}
	public Condition getCondition() {
		return condition;
	}
	public void setCondition(Condition condition) {
		this.condition = condition;
	}

}
