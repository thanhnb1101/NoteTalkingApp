package net.notetalking.util;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Paging implements Serializable{

	public static final int PAGE = 1;
	public static final int SIZE = 10;
	
	public static final String SORT =  "id";
	
	private int page = PAGE;
	private int size = SIZE;
	private long totalRows = -1;
	private int totalPages = -1;
	private String sortField = SORT;
	private int sortDirection = -1;// DESC - ASC
	
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		if (size >= 1) {
			this.size = size;
		}
		
	}
	
	public Paging() {
	}
	public Paging(int page, int size) {
		this.page = page;
		this.size = size;
	}
	public Paging(int page, int size, int sortDirection, String sortField) {
		this.page = page;
		this.size = size;
		this.sortDirection = sortDirection;
		this.sortField = sortField;
	}
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		if (page >= 1) {
			this.page = page;
		}
	}

	/**
	 * Validate and set default page request
	 * @param page
	 * @param size
	 * @param sortDirection
	 * @param sortProperty
	 * @return
	 */
	
	public String toString(){
		return  " Page:["+ page +"] Limit:["+size+"]"+" TotalPages:["+getTotalPages()+"]"+" TotalRows:["+totalRows+"]";
	}
	
}
