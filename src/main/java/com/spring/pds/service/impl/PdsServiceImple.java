package com.spring.pds.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spring.board.dao.BoardDao;
import com.spring.file.vo.FileVo;
import com.spring.pds.dao.PdsDao;
import com.spring.pds.service.PdsService;
import com.spring.pds.vo.PdsVo;

@Service("pdsService")
public class PdsServiceImple implements PdsService {

	@Autowired
	private PdsDao pdsDao;
	@Autowired
	private BoardDao boardDao;
	
	@Override
	public List<PdsVo> getList(HashMap<String, Object> map) {
		//map: {menu_id:'MENU01'},
		//     {nowpage: 1},
		//     {pagegrpnum : 1}
		
		
		//페이징 처리를 위한 내용 추가
		int pagetotalcount = 5; //[이전] 1 2 3 4 5 6 7 8 9 10 [다음]
		//페이지 번호의 갯수 paging.jspf의 for문에 사용
		int pagecount = 2;//한페이지에 보여줄 게시물 라인(줄)수
		//int pagecount = Integer.parseInt((String) map.get("pagecount")); ; //한페이지에 보여줄 게시물 라인(줄)수
		//Object 타입은 직접적으로 int로 바꾸면 잘 안바꿔지기 때문에
		map.put("pagecount", pagecount); //db에 전달하려고 보관
		
		//실제 db 조회 menu_id, nowpage, pagecount
		List<PdsVo> list = pdsDao.getList(map);
		
		//페이징을 위한 정보처리
		//nowpage : 현재 페이지 정보
		int nowpage    = Integer.parseInt(String.valueOf(map.get("nowpage"))); 
		// pagegrpnum : home.jsp에서 넘어온 정보
		int pagegrpnum = Integer.parseInt(String.valueOf(map.get("pagegrpnum")));;
		
		//전체 자료수 : getList의 SP의 out 인자 정보
		int totalcount = Integer.parseInt(String.valueOf(map.get("totalcount")));;

		BoardPaging bp = new BoardPaging(nowpage,pagecount,totalcount, pagetotalcount, pagegrpnum);
		
		PdsVo vo = bp.getPdsPagingInfo();//paging.jspf 에 넘겨줄 정보 가져옴
		System.out.println("vo.pageendnum:" + vo.getPageendnum());
		//조회할 메뉴 아이디를 전달
		vo.setMenu_id((String)map.get("menu_id"));
		
		map.put("pagePdsVo", vo);
		return list;
	}

	@Override
	public void setWrite(HashMap<String, Object> map, HttpServletRequest request) { 
		//파일 업로드 비지니스 로직 시작
		//1. DaoImpl 가 처리
		//입력받은 문자들(title, writet, cont,menu_id, bnum, lvl, step, nref)
		// Board table 에 저장
		
		//2. (MultiPartHttpServletRequest 객체)
		//file들을 disk(d:\\upload\\)에 저장
		
		//3. DaoImpl 가 처리
		//업로드된 파일명과 같은 파일이 있는지 확인

			
			CheckFileName checkFile = new CheckFileName();
			
			//파일저장위치 : d:\\upload\\
			//파일경로설정(java에서 파일경로는 무조건 \ 두개!)
			String filePath = "d:\\upload\\";
			
			//업로드 된 파일의 처리(폴더의 저장)
			MultipartHttpServletRequest multipartServletRequest = (MultipartHttpServletRequest) request; 
			
			//System.out.println("multipartServletRequest:" + multipartServletRequest.getFileMap());
			//Map같은 경우는 0,1,2가 따로 없다. iterator는 0,1,2역할을 대신함
			Iterator<String> iterator = multipartServletRequest.getFileNames();
			//System.out.println("iterator:" + iterator.hasNext());
			//Iterator iterator2 = collection.iterator();
			//파일 하나하나를 받아서 multipartFile에 넣음
			
			MultipartFile multipartFile = null;
			
			List<String> filenames  = new ArrayList<String>();
			List<String> fileexts   = new ArrayList<String>();
			List<String> sfilenames = new ArrayList<String>();
			
			String fileName    = null;
			String orgFileName = null;
			String fileExt     = null;
			String sFileName   = null;
			
			int i = 0;
			while(iterator.hasNext()) {
				multipartFile = multipartServletRequest.getFile(iterator.next()); //파일 정보 하나를 꺼냄
				System.out.println("multipartFile : " + multipartFile);
				HashMap<String, String> hashMap = new HashMap<String, String>();
				
				if(!multipartFile.isEmpty()) {
					fileName = multipartFile.getOriginalFilename();
					// upload 된 파일명
					
					//             0 1 23 4 5678
					// fileName = '태풍.솔릭.jpg'
					int dotIdx = fileName.lastIndexOf('.');
					orgFileName = fileName.substring(0, dotIdx); //'태풍.솔릭'
					fileExt = fileName.substring(dotIdx);//"jpg";
					
					//중복파일이 존재하면 번호추가 후 실제 파일명 처리
					sFileName = checkFile.getCheckFileName(filePath, orgFileName, fileExt); //"태풍.솔릭1";
					
					filenames.add(fileName);
					fileexts.add(fileExt);
					sfilenames.add(sFileName);
					
					map.put("filenames", filenames);
					map.put("fileexts", fileexts);
					map.put("sfilenames", sfilenames);
								
					
					//저장
					File file = new File(filePath + sFileName);
					i += 1;
					try {
						multipartFile.transferTo(file); //실제파일명으로 저장
					} catch(IOException e) {
						System.out.println("오류:" + e.getMessage());
						e.printStackTrace();
					}
				
				}
			}

			if(map.get("filenames")!=null) {
				System.out.println("파일이 있을경우");
				pdsDao.setWrite(map);	
			}else {
				//dao는 db와 연결된 일만 처리
				//db에 정보저장(Table : Board, Files)
				System.out.println("파일이 없을경우");
				boardDao.addBoard(map);	
			}
	}

	//글내용 보기
	@Override
	public PdsVo getContent(HashMap<String, Object> map) {
		PdsVo pdsVo = pdsDao.getContent(map); 
		return pdsVo;
	}

	//파일리스트
	@Override
	public List<FileVo> getFiles(HashMap<String, Object> map) {
		List<FileVo> filesList = pdsDao.getFiles(map);
		return filesList;
	}

	//삭제하기
	@Override
	public void setDelete(HashMap<String, Object> map) {
		//relation이 걸려있을 경우 처리
		//1. Board table, File  table의 idx글 한꺼번에 삭제
		pdsDao.deletePds(map);
		
		
		//3. d:\\upload\\폴더의 파일 삭제 ->service가 해야할 일
		List<FileVo> fileList = pdsDao.getFiles(map);
		for(FileVo fileVo : fileList) {
			File file = new File("d:\\upload\\" + fileVo.getSfilename());
			if(file.exists())
				file.delete();
		}
		
	}


	@Override
	public void setUpdate(HashMap<String, Object> map, HttpServletRequest request) {
		
		//파일이 있으면 pds가 처리
		if(request != null) {

			CheckFileName checkFile = new CheckFileName();
			String filePath = "d:\\upload\\";
		
			MultipartHttpServletRequest multipartServletRequest = (MultipartHttpServletRequest) request; 

			Iterator<String> iterator = multipartServletRequest.getFileNames();
			
			MultipartFile multipartFile = null;
			
			List<String> filenames  = new ArrayList<String>();
			List<String> fileexts   = new ArrayList<String>();
			List<String> sfilenames = new ArrayList<String>();
			
			String fileName    = null;
			String orgFileName = null;
			String fileExt     = null;
			String sFileName   = null;
			
			int i = 0;
			while(iterator.hasNext()) {
				multipartFile = multipartServletRequest.getFile(iterator.next()); //파일 정보 하나를 꺼냄
				System.out.println("multipartFile : " + multipartFile);
				HashMap<String, String> hashMap = new HashMap<String, String>();
				
				if(!multipartFile.isEmpty()) {
					fileName = multipartFile.getOriginalFilename();
	
					int dotIdx = fileName.lastIndexOf('.');
					orgFileName = fileName.substring(0, dotIdx); //'태풍.솔릭'
					fileExt = fileName.substring(dotIdx);//"jpg";
	
					sFileName = checkFile.getCheckFileName(filePath, orgFileName, fileExt); //"태풍.솔릭1";
					
					filenames.add(fileName);
					fileexts.add(fileExt);
					sfilenames.add(sFileName);
					
					map.put("filenames", filenames);
					map.put("fileexts", fileexts);
					map.put("sfilenames", sfilenames);
					
					File file = new File(filePath + sFileName);
					i += 1;
					try {
						multipartFile.transferTo(file); //실제파일명으로 저장
					} catch(IOException e) {
						System.out.println("오류:" + e.getMessage());
						e.printStackTrace();
					}
				}
			}
			
		}else {		
			//파일없으면 board가 처리
		}
		
		if(map.get("filenames")!=null) {
			System.out.println("파일이 있을경우");
			pdsDao.setUpdate(map);
		}else {
			//dao는 db와 연결된 일만 처리
			//db에 정보저장(Table : Board, Files)
			System.out.println("파일이 없을경우");
			boardDao.updateBoard(map);
		}
		
	}

}
