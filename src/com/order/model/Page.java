package com.order.model;

import javax.servlet.http.HttpServletRequest;
// 分页
public class Page
{
	public static final String ROWS = "rows";
	public static final String FOOTER = "footer";
	public static final String TOTAL = "total";

	public static final int DEFAULT_EVERY_PAGE = 20;

	private int currentPage = 1; // 当前页数

	private int everyPage = DEFAULT_EVERY_PAGE; // 每页显示数量

	private int totalPage; // 总页数

	private int totalCount; // 总数量

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getEveryPage() {
		return everyPage;
	}

	public void setEveryPage(int everyPage) {
		this.everyPage = everyPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public Page()
	{
	}

	public Page(HttpServletRequest request)
	{
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");

		this.currentPage = NumberUtils.defaultInt(page, 1);
		if (this.currentPage < 1)
			this.currentPage = 1;

		this.everyPage = NumberUtils.defaultInt(rows, DEFAULT_EVERY_PAGE);
		if (this.everyPage < 1)
			this.everyPage = DEFAULT_EVERY_PAGE;
	}

	public Page(String currentPage, String everyPage)
	{
		this.currentPage = NumberUtils.defaultInt(currentPage, 1);
		if (this.currentPage < 1)
			this.currentPage = 1;

		this.everyPage = NumberUtils.defaultInt(everyPage, DEFAULT_EVERY_PAGE);
		if (this.everyPage < 1)
			this.everyPage = DEFAULT_EVERY_PAGE;
	}

	public Page(int currentPage, int everyPage)
	{
		this.currentPage = currentPage;
		this.everyPage = everyPage;
	}

	public int getOffset()
	{
		return everyPage * (currentPage - 1);
	}

	// 设置总数量的同时，设置总页数
	public void setCount(int totalCount)
	{
		this.totalCount = totalCount;
		this.totalPage = (int)Math.ceil((double)totalCount / this.everyPage);
	}

}