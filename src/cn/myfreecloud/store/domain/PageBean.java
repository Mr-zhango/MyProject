package cn.myfreecloud.store.domain;

import java.util.List;

public class PageBean<T> {
	
	private List<T> data;//当前的数据
	private int pageNumber;//当前页码
	private int pageSize;//每页显示条数
	private int total;//总条数
	private int totalPage;//总页数
	
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getTotalPage() {
		//13 5 13/5
		//直接计算出总页数
		return (int) Math.ceil(total*1.0/pageSize);
	}
}
