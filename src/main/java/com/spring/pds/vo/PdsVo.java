package com.spring.pds.vo;

public class PdsVo {
	//Fields
	//게시물(Board)
	private int     idx;
	private String title;
	private String cont;
	private String writer;
	private String regdate;
	private int    readcount;
	//게시물(Board) - 답글처리
	private int    bnum;
	private int    lvl;
	private int    step;
	private int    nref;
	private int    delnum;
	private int    par_id;
	
	//메뉴관련
	private String menu_id;
	private String menu_name;
	private String menu_seq;
	
	private int    filescount;

	//페이징관련정보
	private int nowpage;        //현재 페이지 정보
	private int prevnowpage;    //이전 10개  클릭시 적용되는 nowpage
	private int nextnowpage;    //다음 10개  클릭시 적용되는 nowpage
	
	private int totalcount;     //전체 Row수
	private int totalpagecount; //화면에 보여줄 페이지 수
	
	private int pagestartnum;   //화면에 보여줄 페이지 시작 번호
	private int pageendnum;     //화면에 보여줄 페이지 끝 번호
	private int pagegrpnum;     //화면에 보여줄 페이지 그룹 번호	
	private int pagecount;      //한 페이지에 보여줄 자료 라인수
	
	private boolean isshowpageprev;
	private boolean isshowpagenext;

	
	
	//Getter/Setter
	public int getIdx() {
		return idx;
	}
	
	public void setIdx(int idx) {
		this.idx = idx;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getCont() {
		return cont;
	}
	
	public void setCont(String cont) {
		this.cont = cont;
	}
	
	public String getWriter() {
		return writer;
	}
	
	public void setWriter(String writer) {
		this.writer = writer;
	}
	
	public String getRegdate() {
		return regdate;
	}
	
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
	public int getReadcount() {
		return readcount;
	}
	
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	
	public int getBnum() {
		return bnum;
	}
	
	public void setBnum(int bnum) {
		this.bnum = bnum;
	}
	
	public int getLvl() {
		return lvl;
	}
	
	public void setLvl(int lvl) {
		this.lvl = lvl;
	}
	
	public int getStep() {
		return step;
	}
	
	public void setStep(int step) {
		this.step = step;
	}
	
	public int getNref() {
		return nref;
	}
	
	public void setNref(int nref) {
		this.nref = nref;
	}
	
	public int getDelnum() {
		return delnum;
	}
	
	public void setDelnum(int delnum) {
		this.delnum = delnum;
	}
	
	public int getPar_id() {
		return par_id;
	}
	
	public void setPar_id(int par_id) {
		this.par_id = par_id;
	}
	
	public String getMenu_id() {
		return menu_id;
	}
	
	public void setMenu_id(String menu_id) {
		this.menu_id = menu_id;
	}
	
	public String getMenu_name() {
		return menu_name;
	}
	
	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}
	
	public String getMenu_seq() {
		return menu_seq;
	}
	
	public void setMenu_seq(String menu_seq) {
		this.menu_seq = menu_seq;
	}
	
	public int getFilescount() {
		return filescount;
	}
	
	public void setFilescount(int filescount) {
		this.filescount = filescount;
	}

	public int getNowpage() {
		return nowpage;
	}

	public void setNowpage(int nowpage) {
		this.nowpage = nowpage;
	}

	public int getPrevnowpage() {
		return prevnowpage;
	}

	public void setPrevnowpage(int prevnowpage) {
		this.prevnowpage = prevnowpage;
	}

	public int getNextnowpage() {
		return nextnowpage;
	}

	public void setNextnowpage(int nextnowpage) {
		this.nextnowpage = nextnowpage;
	}

	public int getTotalcount() {
		return totalcount;
	}

	public void setTotalcount(int totalcount) {
		this.totalcount = totalcount;
	}

	public int getTotalpagecount() {
		return totalpagecount;
	}

	public void setTotalpagecount(int totalpagecount) {
		this.totalpagecount = totalpagecount;
	}

	public int getPagestartnum() {
		return pagestartnum;
	}

	public void setPagestartnum(int pagestartnum) {
		this.pagestartnum = pagestartnum;
	}

	public int getPageendnum() {
		return pageendnum;
	}

	public void setPageendnum(int pageendnum) {
		this.pageendnum = pageendnum;
	}

	public int getPagegrpnum() {
		return pagegrpnum;
	}

	public void setPagegrpnum(int pagegrpnum) {
		this.pagegrpnum = pagegrpnum;
	}

	public int getPagecount() {
		return pagecount;
	}

	public void setPagecount(int pagecount) {
		this.pagecount = pagecount;
	}

	public boolean isIsshowpageprev() {
		return isshowpageprev;
	}

	public void setIsshowpageprev(boolean isshowpageprev) {
		this.isshowpageprev = isshowpageprev;
	}

	public boolean isIsshowpagenext() {
		return isshowpagenext;
	}

	public void setIsshowpagenext(boolean isshowpagenext) {
		this.isshowpagenext = isshowpagenext;
	}
	
	
	
	
	
}
