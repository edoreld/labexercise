package com.edoreld.elements;

import java.util.Date;

public class Frame
{
	private Page	page;
	long			recentness;

	public Frame() {
		recentness = new Date().getTime();
	}

	public Frame(Page page) {
		this();
		this.page = page;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public long getRecentness() {
		return recentness;
	}

	public void setRecentness(long recentness) {
		this.recentness = recentness;
	}
}
