package com.escape.utility;

public class Paging {
	// 페이징을 위한 클래스
	private int totalCount = 0 ; // 테이블에 들어 있는 총 행의 개수
	private int totalPage = 0 ; // 전체 페이지 수
	
	private int pageNumber = 0 ; // 현재 페이지 번호
	private int pageSize = 0 ; // 한 페이지에 보여줄 행의 개수 
	private int beginRow = 0 ; // 현재 페이지에 보여지는 시작 랭킹 번호 
	private int endRow = 0 ; // 현재 페이지에 보여지는 끝 랭킹 번호 
	
	private int pageCount = 10 ; // 하단 중간에 보이는 페이지 링크 개수
	private int beginPage = 0 ; // 페이지 링크 시작 번호
	private int endPage = 0 ; // 페이지 링크 끝 번호 
	
	private String url = "" ; // 게시물 보여 주는 페이지(예시 : boList)
	private String pagingHtml = "" ; // 하단의 이전/다음/숫자 목록 페이지 하이퍼 링크를 저장하고 있는 문자열
	
	private String pagingStatus = "" ; // 상단 우측의 현재 페이지 현황(예시 : 총 295건[12/30])
	
	private String mode = "" ; // 검색 모드(예시 : 작성자, 글제목 등등)
	
	// 하기 4개는 프로덕트에만 사용.
	private String pagingHtmlPr = "" ; // 하단의 이전/다음/숫자 목록 페이지 하이퍼 링크를 저장하고 있는 문자열
	
	private String modeGenre = "" ; // 장르
	private String modeLevel = "" ; // 난이도
	private String modeArea = "" ; // 지역
	
	private String keyword = "" ; // 검색할 단어	
	
	private String flowParameter = "" ; // 페이지 이동시 같이 수반되는 파라미터 리스트
	

	public int getPageNumber() {return pageNumber;}
	public int getPageSize() {return pageSize;}
	public int getBeginRow() {return beginRow;}
	public int getEndRow() {return endRow;}
	public String getPagingHtml() {return pagingHtml;}
	public String getPagingStatus() {return pagingStatus;}
	public String getMode() {return mode;}
	public String getKeyword() {return keyword;}
	
	
	public String getPagingHtmlPr() {return pagingHtmlPr;}
	public String getModeGenre() {return modeGenre;}
	public String getModeLevel() {return modeLevel;}
	public String getModeArea() {return modeArea;}
	
	public String getFlowParameter() {return flowParameter;}
	
	public Paging(String _pageNumber, String _pageSize, int totalCount, String url, String mode, String keyword, boolean isGrid) {
		
		// isGrid=true이면 상품 목록 보기, false이면 일반 형식으로 보기
		
		if(_pageNumber==null || _pageNumber.equals("null") || _pageNumber.equals("")) {
			_pageNumber = "1" ;
		}
		this.pageNumber = Integer.parseInt(_pageNumber);
		
		if(_pageSize==null || _pageSize.equals("null") || _pageSize.equals("")) {
			if (isGrid==true) { // 상품 목록 보기인 경우 격자 형식으로 화면을 보여 줍니다.
				_pageSize = "6" ; // 6 = 2행 3열의 격자(grid) 구조
			} else {
				_pageSize = "5" ; // 한 페이지당 몇개 보여줄래?
			}			
		}
		this.pageSize = Integer.parseInt(_pageSize);
		
		
		this.totalCount = totalCount;
		this.url = url;
		
		this.mode = mode;
		this.keyword = keyword;
		
		double _totalPage = Math.ceil((double)totalCount/pageSize) ;
		this.totalPage = (int)_totalPage ;
		
		this.beginRow = (this.pageNumber-1) * this.pageSize + 1 ;
		this.endRow = this.pageNumber * this.pageSize  ;
		
		this.beginPage = (this.pageNumber-1) / this.pageCount * this.pageCount + 1 ;
		this.endPage = this.beginPage + this.pageCount - 1 ;
		
		if(endRow > totalCount) {
			endRow = totalCount ;
		}		
		
		if(endPage > totalPage) {
			endPage = totalPage ;
		}
		
		this.pagingStatus = "총 " + totalCount + "건[" + pageNumber + "/" + totalPage + "]" ;
		this.pagingHtml = this.getMakePagingHtml() ;		
		
		this.flowParameter = "";
		this.flowParameter += "&pageNumber=" + this.pageNumber;
		this.flowParameter += "&pageSize=" + this.pageSize;
		this.flowParameter += "&mode=" + this.mode;
		this.flowParameter += "&keyword=" + this.keyword;
	}
	
	private String getMakePagingHtml() { 
		String html = "" ;
		
		html += "<ul class='pagination justify-content-center'>" ;
		
		if (this.pageNumber <= this.pageCount) {
			// '맨처음'과 '이전' 항목이 존재하지 않는 영역 
		} else {
			html += this.makeLiTag(String.valueOf(1), "맨처음");
			html += this.makeLiTag(String.valueOf(beginPage-1), "이전"); 
		}
		
		for (int i = beginPage; i <= endPage; i++) {
			if(i==pageNumber) {  
				html += "<li class='page-item active'><a class='page-link'><b><font color='white'>" ;
				html += i ;
				html += "</font></a></b></li>";				
			}else {
				html += this.makeLiTag(String.valueOf(i), String.valueOf(i)); 
			}
		}
		
		if (this.pageNumber >= (totalPage/pageCount*pageCount + 1)){
			// '맨끝'과 '다음' 항목이 존재하지 않는 영역 
		} else {
			html += this.makeLiTag(String.valueOf(endPage+1), "다음"); 			
			html += this.makeLiTag(String.valueOf(totalPage), "맨끝");
		}
		html += "</ul>" ;
		return html;
	}

	private String makeLiTag(String strPageNumber, String strLabel) {
		String result = "" ;
		
		result += "<li class='page-item'>" ;
		result += "<a class='page-link' href='";
		result += this.url;
		result += "&pageNumber=" + strPageNumber ;
		result += "&pageSize=" + this.pageSize ;
		result += "&mode=" + this.mode ;
		result += "&keyword=" + this.keyword ;
		result += "'>" + strLabel ;
		result += "</a></li>";
		
		return result;
	}
	
	
	// 상품용 페이징 메소드
	public Paging(String _pageNumber, String _pageSize, int totalCount, String url, String modeGenre, String modeLevel, String modeArea, String keyword, boolean isGrid) {
	// isGrid=true이면 상품 목록 보기, false이면 일반 형식으로 보기
	
	if(_pageNumber==null || _pageNumber.equals("null") || _pageNumber.equals("")) {
		_pageNumber = "1" ;
	}
	this.pageNumber = Integer.parseInt(_pageNumber);
	
	if(_pageSize==null || _pageSize.equals("null") || _pageSize.equals("")) {
		if (isGrid==true) { // 상품 목록 보기인 경우 격자 형식으로 화면을 보여 줍니다.
			_pageSize = "6" ; // 6 = 2행 3열의 격자(grid) 구조
		} else {
			_pageSize = "5" ; // 한 페이지당 몇개 보여줄래?
		}			
	}
	this.pageSize = Integer.parseInt(_pageSize);
	
	this.totalCount = totalCount;
	this.url = url;
	
	this.modeArea = modeArea;
	this.modeGenre = modeGenre;
	this.modeLevel = modeLevel;
	
	this.keyword = keyword;
	
	double _totalPage = Math.ceil((double)totalCount/pageSize) ;
	this.totalPage = (int)_totalPage ;
	
	this.beginRow = (this.pageNumber-1) * this.pageSize + 1 ;
	this.endRow = this.pageNumber * this.pageSize  ;
	
	this.beginPage = (this.pageNumber-1) / this.pageCount * this.pageCount + 1 ;
	this.endPage = this.beginPage + this.pageCount - 1 ;
	
	if(endRow > totalCount) {
		endRow = totalCount ;
	}		
	
	if(endPage > totalPage) {
		endPage = totalPage ;
	}
	
	this.pagingStatus = "총 " + totalCount + "건[" + pageNumber + "/" + totalPage + "]" ;
	this.pagingHtmlPr = this.getMakePagingHtmlPr() ;		
	
	this.flowParameter = "";
	this.flowParameter += "&pageNumber=" + this.pageNumber;
	this.flowParameter += "&pageSize=" + this.pageSize;
	
	this.flowParameter += "&modeGenre=" + this.modeGenre;
	this.flowParameter += "&modeLevel=" + this.modeLevel;
	this.flowParameter += "&modeArea=" + this.modeArea;
	
	this.flowParameter += "&keyword=" + this.keyword;
}
	
	//상품용
	private String getMakePagingHtmlPr() { 
		String html = "" ;
		
		html += "<ul class='pagination justify-content-center'>" ;
		
		if (this.pageNumber <= this.pageCount) {
			// '맨처음'과 '이전' 항목이 존재하지 않는 영역 
		} else {
			html += this.makeLiTagPr(String.valueOf(1), "맨처음");
			html += this.makeLiTagPr(String.valueOf(beginPage-1), "이전"); 
		}
		
		for (int i = beginPage; i <= endPage; i++) {
			if(i==pageNumber) {  
				html += "<li class='page-item active'><a class='page-link'><b><font color='white'>" ;
				html += i ;
				html += "</font></a></b></li>";				
			}else {
				html += this.makeLiTagPr(String.valueOf(i), String.valueOf(i)); 
			}
		}
		
		if (this.pageNumber >= (totalPage/pageCount*pageCount + 1)){
			// '맨끝'과 '다음' 항목이 존재하지 않는 영역 
		} else {
			html += this.makeLiTagPr(String.valueOf(endPage+1), "다음"); 			
			html += this.makeLiTagPr(String.valueOf(totalPage), "맨끝");
		}
		html += "</ul>" ;
		return html;
	}

	private String makeLiTagPr(String strPageNumber, String strLabel) {
		String result = "" ;
		
		result += "<li class='page-item'>" ;
		result += "<a class='page-link' href='";
		result += this.url;
		result += "&pageNumber=" + strPageNumber ;
		result += "&pageSize=" + this.pageSize ;
		result += "&modeGenre=" + this.modeGenre;
		result += "&modeLevel=" + this.modeLevel ;
		result += "&modeArea=" + this.modeArea ;
		result += "&keyword=" + this.keyword ;
		result += "'>" + strLabel ;
		result += "</a></li>";
		
		return result;
	}
	
	
	
	
	
}