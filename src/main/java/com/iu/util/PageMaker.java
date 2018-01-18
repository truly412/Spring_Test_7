package com.iu.util;

public class PageMaker {
	
	public void pageMaker(int totalCount, ListData listData){
		//1. TotalCount 매개변수로 가져오기
		
		//2. totalCount로 전체페이지(totalPage) 수 구하기
		int totalPage = 0;
		if(totalCount % listData.getPerPage() == 0){
			totalPage = totalCount/listData.getPerPage();
		}else{
			totalPage = totalCount/listData.getPerPage() + 1;
		}
		
		//3. totalPage로 전체 블럭(totalBlock) 수 구하기
		int totalBlock = 0;
		int perBlock = 5;
		if(totalPage % perBlock == 0){
			totalBlock = totalPage/perBlock;
		}else{
			totalBlock = totalPage/perBlock + 1;
		}
		
		//4. curPage로 현재 블럭(curBlock) 구하기
		int curBlock = 0;
		if(listData.getCurPage() % perBlock == 0 ){
			curBlock = listData.getCurPage()/perBlock;
		}else{
			curBlock = listData.getCurPage()/perBlock + 1;
		}
		
		//5. curBlock로 startNum / lastNum 구하기
		listData.setStartNum((curBlock - 1) * perBlock + 1);
	    listData.setLastNum(curBlock * perBlock);
	    
	    //6. 마지막 블럭에 마지막 페이지만 나오게
	      if(curBlock == totalBlock) {
	         listData.setLastNum(totalPage);
	      }
	      
	      listData.setCurBlock(curBlock);
	      listData.setTotalBlock(totalBlock);
	      
	}

}
