package com.iu.s7;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.iu.board.BoardDTO;
import com.iu.notice.NoticeDTO;
import com.iu.qna.QnaDTO;
import com.iu.qna.QnaService;
import com.iu.util.ListData;

@Controller
@RequestMapping(value="/qna/**")
public class QnaController {
	
	@Inject
	private QnaService qnaService;
	
	//List
	@RequestMapping(value="qnaList")
	public ModelAndView selectList(ListData listData) throws Exception{
		ModelAndView mv = new ModelAndView();
		List<BoardDTO> ar = qnaService.selectList(listData);
		mv.addObject("list", ar);
		mv.addObject("page", listData);
		mv.addObject("board", "qna");
		mv.setViewName("board/boardList");
		return mv;
	}
	
	//Write (Insert)
		@RequestMapping(value="qnaWrite", method=RequestMethod.GET)
		public String qnaWrite(Model model) throws Exception{
			model.addAttribute("board", "notice");
			return "board/boardWrite";
		}
		@RequestMapping(value="qnaWrite", method=RequestMethod.POST)
		public String qnaWrite(QnaDTO qnaDTO, RedirectAttributes re) throws Exception{
			int result = 0;
			result = qnaService.insert(qnaDTO);
			
			// Redirect 방식 - 리턴 String / 매개변수 RedirectAttributes
			String message = "Fail";
			if(result > 0){
				message = "Success";
			}
			re.addFlashAttribute("message", message);
			return "redirect:./qnaList"; 
			
		/*	// Result 방식 - 리턴 ModelAndView
			if(result>0){
				mv.addObject("message", "Success");
			}else{
				mv.addObject("message", "Fail");
			}
			mv.addObject("path", "noticeList");
			mv.setViewName("common/result");
			*/
		}

}
