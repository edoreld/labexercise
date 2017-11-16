package com.edoreld.elements;

public class Page
{
	String pageName;

	public Page(String pageName) {
		this.pageName = pageName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (pageName == null ? 0 : pageName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Page other = (Page) obj;
		if (pageName == null) {
			if (other.pageName != null) {
				return false;
			}
		} else if (!pageName.equals(other.pageName)) {
			return false;
		}
		return true;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
}
