package com.spring.pds.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.file.vo.FileVo;
import com.spring.menu.service.MenuService;
import com.spring.menu.vo.MenuVo;
import com.spring.pds.service.PdsService;
import com.spring.pds.vo.PdsVo;

@Controller
public class PdsController {
	
	@Autowired
	private MenuService menuService;
	@Autowired
	private PdsService pdsService;
	
	@RequestMapping("/PDS/List")
	public ModelAndView pdsList(@RequestParam HashMap<String, Object> map) {
		//map: {menu_id:'MENU01'},
		//     {nowpage: 1},
		//     {pagecount: 2}
		//     {pagegrpnum : 1}
		
		List<MenuVo> menuList  = menuService.getList();
		List<PdsVo>  pdsList   = pdsService.getList(map);     //몽땅 들고옴,paging도 처리
		PdsVo        pagePdsVo = (PdsVo)map.get("pagePdsVo"); //
		System.out.println("endPageNum:" +map.get("pagePdsVo"));
		PdsVo vo = (PdsVo) map.get("pagePdsVo");
		System.out.println("endnum:" +vo.getPageendnum());
		ModelAndView mv = new ModelAndView();
		mv.addObject("pagePdsVo", vo);
		mv.addObject("menuList", menuList);
		mv.addObject("pdsList",  pdsList);

		System.out.println("startnum:" + pagePdsVo.getPagestartnum());
		mv.addObject("menu_id",  map.get("menu_id"));
	
		mv.setViewName("pds/list");
		return mv;
	}

	//글입력
	@RequestMapping("/PDS/WriteForm")
	public ModelAndView pdsWriteForm(@RequestParam HashMap<String, Object> map) {
		ModelAndView mv = new ModelAndView();

		List<MenuVo> menuList = menuService.getList();
		
		map.put("user_id", "sky");
		
		MenuVo menuVo = menuService.selectMenu(map);
		
		mv.setViewName("pds/write");
		mv.addObject("map", map);       //request 정보들
		mv.addObject("menuVo", menuVo); //선택한 메뉴정보 전달
		mv.addObject("menuList", menuList);
		return mv;
	}
	
	// HttpServletRequest request : 넘어온 파일정보 처리
	@RequestMapping(value="/PDS/Write")
	public ModelAndView pdsWrite(@RequestParam HashMap<String, Object> map,
			HttpServletRequest request) {
		
		//List<MenuVo> menuList = menuService.getList();
		System.out.println(map);
		//{lvl=0, par_id=0, nref=0, menu_name=사회, bnum=0, step=0, writer=1, title=테스트, cont=파일테스트, menu_id=MENU01}
		System.out.println(request);
		//org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest@250af78e
		pdsService.setWrite(map, request);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("menu_id", map.get("menu_id"));
		//페이징처리할 정보추가 필요
		mv.addObject("nowpage", map.get("nowpage"));
		mv.addObject("pagegrpnum", map.get("pagegrpnum"));
		
		mv.setViewName("redirect:/PDS/List");
		return mv;
	}
	
	//내용보기
	@RequestMapping("PDS/Content")
	public ModelAndView pdsView(@RequestParam HashMap<String, Object> map) {
		//idx, menu_id 들어옴
		
		List<MenuVo> menuList  = menuService.getList();
		PdsVo        pdsVo     = pdsService.getContent(map);
		List<FileVo> filesList = pdsService.getFiles(map);
		
		ModelAndView mv = new ModelAndView();
		
		System.out.println("nowpage:" +map.get("nowpage"));
		System.out.println("pagegrpnum:" +map.get("pagegrpnum"));
		
		mv.addObject("menuList", menuList); //메뉴처리
		mv.addObject("pdsVo", pdsVo);
		mv.addObject("filesList", filesList);
		mv.addObject("nowpage", map.get("nowpage"));
		mv.addObject("pagegrpnum", map.get("pagegrpnum"));
/*		map.put("pagecount", 2);
		mv.addObject("pagecount", map.get("pagecount"));*/
		mv.setViewName("pds/content");
		return mv;
	}
	
	@RequestMapping("PDS/UpdateForm")
	public ModelAndView pdsUpdateForm(@RequestParam HashMap<String, Object> map) {
		
		//List<MenuVo> menuList  = menuService.getList();
		PdsVo pdsVo = pdsService.getContent(map);
		List<FileVo> filesList = pdsService.getFiles(map);
		MenuVo menuVo = menuService.selectMenu(map);
		
		
		map.put("user_id", "sky");
		System.out.println(map.get("menu_id"));
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("map", map);
		mv.addObject("menuVo", menuVo);
		//mv.addObject("menuList", menuList); //메뉴처리
		mv.addObject("pdsVo", pdsVo);
		mv.addObject("filesList", filesList);
		//페이징처리할 정보추가 필요
		mv.addObject("nowpage", map.get("nowpage"));
		mv.addObject("pagegrpnum", map.get("pagegrpnum"));
		
		mv.setViewName("pds/update");
		
		
		return mv;
	}
	
	@RequestMapping("PDS/Update")
	public ModelAndView pdsUpdate(@RequestParam HashMap<String, Object> map,
			HttpServletRequest request) {
		
		pdsService.setUpdate(map, request);
		System.out.println(map.get("menu_id"));
		ModelAndView mv = new ModelAndView();
		mv.addObject("menu_id", map.get("menu_id"));
		//페이징처리할 정보추가 필요
		mv.addObject("nowpage", map.get("nowpage"));
		mv.addObject("pagegrpnum", map.get("pagegrpnum"));
		mv.setViewName("redirect:/PDS/List");
		return mv;
	}
	
	@RequestMapping("PDS/Delete")
	public ModelAndView pdsDelete(@RequestParam HashMap<String, Object> map) {
	
		pdsService.setDelete(map);
		
		String menu_id = (String) map.get("menu_id");
		ModelAndView mv = new ModelAndView();
		mv.addObject("menu_id", menu_id);
		//페이징처리할 정보추가 필요
		mv.addObject("nowpage", map.get("nowpage"));
		mv.addObject("pagegrpnum", map.get("pagegrpnum"));
		mv.setViewName("redirect:/PDS/List?menu_id=" + menu_id);
		
		return mv;
	}
	
	//-----------------------------------------------------------------------
	
	@RequestMapping(value="/download/{type}/{sfile:.+}", method=RequestMethod.GET)
	public void downloadFile(HttpServletResponse response, 
			@PathVariable("type") String type,
			@PathVariable("sfile") String sfile ) throws IOException {
			//{sfile}    : .jpg와 같이 .포함
			//{sfile:.+} : 정규식에서 확장자에 . 문자가 한개(+)이상 있을 때
			
			//webapp 인터넷경로, 내부파일
			String INTERNAL_FILE      = sfile;
			//d드라이브같은 경우는 인터넷 경로가 아니기 때문에 외부파일
			String EXTERNAL_FILE_PATH = "d:\\upload\\" + sfile;
			
			File file = null;
			if(type.equalsIgnoreCase("internal")) {
				//웹경로를 알아내겠다.
				ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
				//파일의 위치파악
				file = new File(classLoader.getResource(INTERNAL_FILE).getFile());
			}else {
				file = new File(EXTERNAL_FILE_PATH);
			}
			
			if(!file.exists()) {
				String errorMessage = "Sorry. the file are looking for does not exist";
				System.out.println(errorMessage);
				OutputStream outputStream = response.getOutputStream();
				outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
				outputStream.close();
				return;
			}
		
		//mimeType설정
		String mimeType = URLConnection.guessContentTypeFromName(file.getName());
/*		if(mimeType == null) {
			System.out.println("mimetype is not detectable, will take default");
			mimeType = "application/octet-stream";
			//application/octet-stream : 기본값
		}*/
		mimeType = "application/octet-stream"; //무조건 다운로드
		System.out.println("mimeType:" + mimeType); 
		
		//브라우저에게 알려주는 정보
		response.setContentType(mimeType);
		response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() +"\""));
		response.setContentLength((int)file.length()); //용량지정
		
		//파일자체를 인풋스트림으로 저장
		InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
		
		//Copy bytes from source to destination(outputStream)
		//close both streams
		//인풋스트림을 카피?
		FileCopyUtils.copy(inputStream, response.getOutputStream());
		
		
	}
}
