package com.spring.pds.service.impl;

import com.spring.pds.vo.PdsVo;

//ServiceImpl에서 사용할 클래스
public class BoardPaging {
	
	//fields
	private int totalCount;       //전체 row 수
	private int nowPage;          //현재 페이지번호
	private int prevNowPage;      
	private int nextNowPage;
	private int pageCount;        //1페이지에 보여줄 줄 수
	
	private int pageTotalCount;   //한화면에 보여줄 총 페이지 숫자
	private int pageGrpNum;       //한 화면에 보여지고 있는 페이지 그룹의 페이지 숫자
	
	private int startPageNum;     //페이지 시작숫자
	private int endPageNum;       //페이지 끝 숫자
	
	private int totalRecordPageCount; //총 row 갯수를 가지고 표현가능한 총 페이지 수
	
	private boolean isshowPagePrev = true;
	private boolean isshowPageNext = true;

	
	//Constructor
	public BoardPaging() {
		
	}
	
	public BoardPaging(int nowpage, int pagecount, int totalcount, int pagetotalcount, int pagegrpnum) {
		this.nowPage        = nowpage;
		this.pageCount      = pagecount;
		this.totalCount     = totalcount;
		this.pageTotalCount = pagetotalcount;
		this.pageGrpNum     = pagegrpnum;
	}
	
	//Getter Setter
	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getNowPage() {
		return nowPage;
	}

	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}

	public int getPrevNowPage() {
		return prevNowPage;
	}

	public void setPrevNowPage(int prevNowPage) {
		this.prevNowPage = prevNowPage;
	}

	public int getNextNowPage() {
		return nextNowPage;
	}

	public void setNextNowPage(int nextNowPage) {
		this.nextNowPage = nextNowPage;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPageTotalCount() {
		return pageTotalCount;
	}

	public void setPageTotalCount(int pageTotalCount) {
		this.pageTotalCount = pageTotalCount;
	}

	public int getPageGrpNum() {
		return pageGrpNum;
	}

	public void setPageGrpNum(int pageGrpNum) {
		this.pageGrpNum = pageGrpNum;
	}

	public int getStartPageNum() {
		return startPageNum;
	}

	public void setStartPageNum(int startPageNum) {
		this.startPageNum = startPageNum;
	}

	public int getEndPageNum() {
		return endPageNum;
	}

	public void setEndPageNum(int endPageNum) {
		this.endPageNum = endPageNum;
	}

	public int getTotalRecordPageCount() {
		return totalRecordPageCount;
	}

	public void setTotalRecordPageCount(int totalRecordPageCount) {
		this.totalRecordPageCount = totalRecordPageCount;
	}

	public boolean isIsshowPagePrev() {
		return isshowPagePrev;
	}

	public void setIsshowPagePrev(boolean isshowPagePrev) {
		this.isshowPagePrev = isshowPagePrev;
	}

	public boolean isIsshowPageNext() {
		return isshowPageNext;
	}

	public void setIsshowPageNext(boolean isshowPageNext) {
		this.isshowPageNext = isshowPageNext;
	}

	
	//Method
	//pagePdsVo : Paging.jspf
	public PdsVo getPdsPagingInfo() {
		PdsVo vo = new PdsVo();  
		
		//(totalCount)/pageCount =  263줄 / 10 -> 27페이지
		totalRecordPageCount = (int)Math.ceil((double)totalCount/(double)pageCount);
		//Math.ceil : 소수점 올림
		
		//페이지 시작번호
		//pageTotalCount : 10 - 한 줄에 10개 출력
		//startPageNum                             endPageNum      pageGrpNum
		//     1        2  3  4  5  6  7  8  9         10     ▶        1
		// ◀  11       12 13 14 15 16 17 18 19         20     ▶        2
		// ◀  21       22 23 24 25 26 27                               3
		startPageNum = (pageGrpNum -1) * pageTotalCount + 1;
		
		System.out.println("pageGrpNum:" + pageGrpNum);
		System.out.println("pageTotalCount:" + pageTotalCount);
		System.out.println("(pageGrpNum -1) * pageTotalCount + 1 :"+ startPageNum);
		//페이지 끝 번호
		//                     27           <     3       *       10
		endPageNum   = totalRecordPageCount < (pageGrpNum * pageTotalCount)?
					  totalRecordPageCount : (pageGrpNum * pageTotalCount);
		
		//[이전 10개] [다음 10개] 출력 여부
		if(startPageNum  == 1 ) isshowPagePrev = false;
		if(endPageNum>=totalRecordPageCount) isshowPageNext = false;
		
		//◀[이전 10개]
		prevNowPage = startPageNum - 1; 
		//▶[다음 10개]
		nextNowPage = endPageNum + 1;
		//계산 끝
		
		//값 저장
		vo.setNowpage(this.nowPage);
		vo.setPrevnowpage(this.nextNowPage);
		vo.setNextnowpage(this.nextNowPage);
		vo.setTotalcount(this.totalCount);
		vo.setTotalpagecount(this.pageTotalCount);
		vo.setPagestartnum(this.startPageNum);
		vo.setPageendnum(this.endPageNum);
		vo.setPagecount(this.pageCount);
		vo.setPagegrpnum(this.pageGrpNum);
		vo.setIsshowpagenext(isshowPageNext);
		vo.setIsshowpageprev(isshowPagePrev);
		
		
		return vo;
	}

}
