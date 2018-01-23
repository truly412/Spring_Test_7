package com.iu.s7;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.iu.board.BoardDTO;
import com.iu.notice.NoticeDTO;
import com.iu.notice.NoticeService;
import com.iu.util.ListData;

@Controller
@RequestMapping(value="/notice/**")
public class NoticeController {

	@Inject
	private NoticeService noticeService;
	
	@RequestMapping(value="noticeUpdate", method=RequestMethod.GET)
	public String Update(int num, Model model) throws Exception {
		BoardDTO boardDTO=noticeService.selectOne(num);
		model.addAttribute("view",boardDTO);
		model.addAttribute("board","notice");
		return "board/boardUpdate";
	}
	
	@RequestMapping(value="noticeUpdate", method=RequestMethod.POST)
	public String Update(NoticeDTO noticeDTO, MultipartFile[] file, HttpSession session) throws Exception {
		noticeService.viewUpdate(noticeDTO,file,session);
		return "redirect:./noticeList";
	}
	
	/*@RequestMapping(value="noticeDelete")
	public void viewDelete(int num, HttpSession session) throws Exception {
		noticeService.viewDelete(num, session);
	}*/
	
	@RequestMapping(value="noticeDelete")
	public String delete(int num,HttpSession session) throws Exception {
		int reulst = noticeService.delete(num,session);
		return "redirect:./noticeList";
	}
	
	
	//view
	@RequestMapping(value="noticeView")
	public ModelAndView selectOne(int num) throws Exception {
		 BoardDTO boardDTO=noticeService.selectOne(num);
		 ModelAndView mv = new ModelAndView();
		 mv.addObject("view", boardDTO);
		 mv.addObject("board", "notice");
		 mv.setViewName("board/boardView");
		return mv;
	}
	
	//List
	@RequestMapping(value="noticeList", method=RequestMethod.GET)
	public ModelAndView selectList(ListData listData) throws Exception{
		ModelAndView mv = new ModelAndView();
		List<BoardDTO> ar = noticeService.selectList(listData);
		mv.addObject("list", ar);
		mv.addObject("page", listData);
		mv.addObject("board", "notice");
		mv.setViewName("board/boardList");
		return mv;
	}

	//Write
	@RequestMapping(value="noticeWrite", method=RequestMethod.GET)
	public String noticeWrite(Model model) throws Exception{
		model.addAttribute("board", "notice");
		return "board/boardWrite";
	}
	
	//Write
	@RequestMapping(value="noticeWrite", method=RequestMethod.POST)
	public ModelAndView noticeWrite(NoticeDTO noticeDTO, MultipartFile[] file, HttpSession session) throws Exception{
		ModelAndView mv = new ModelAndView();
		int result = 0;
		result = noticeService.insert(noticeDTO, file, session);
		
		// Result 방식 - 리턴 ModelAndView
		if(result>0){
			mv.addObject("message", "Success");
		}else{
			mv.addObject("message", "Fail");
		}
		mv.addObject("path", "noticeList");
		mv.setViewName("common/result");
		return mv;
		
		/* Redirect 방식 - 리턴 String / 매개변수 RedirectAttributes
		rd.addFlashAttribute("message", "Success");
		return "redirect:./noticeList?curPage="; 
		*/
	}



}
