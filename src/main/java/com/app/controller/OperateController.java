package com.app.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.database.DatabaseClass;
import com.app.database.MathDo;
import com.app.entity.ISBN;
import com.app.entity.LoginUser;
import com.app.repository.ISBNRepository;
import com.app.repository.UserRepository;
import com.app.service.UserService;
import com.app.database.Account;

@Controller
public class OperateController {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ISBNRepository isbnRepository;
	
	@GetMapping("/index")
	public String getIndexPage(Model model) {
		return "index";
	}
	
	@RequestMapping("operateCollate")
	public String operateCollate(ModelMap modelMap, @RequestParam("isbn") String isbn) {
		modelMap.addAttribute("isbn", isbn);
		return "operateCollate";
	}
	
	@RequestMapping("operateCheck")
	public String operateCheck(ModelMap modelMap) {
		List<ISBN> isbnList = isbnRepository.findAll();
		modelMap.addAttribute("isbnList",isbnList);
		return "operateCheck";
	}
	
	@RequestMapping("operateCover")
	public String operateCover(ModelMap modelMap) {
		List<ISBN> isbnList = isbnRepository.findAll();
		for(ISBN isbn: isbnList) {
			if(isbn.getThumbnail().contains("jpg")) {
				isbn.setThumbnail("https://cover.openbd.jp/"+isbn.getThumbnail());
			}
		}
		modelMap.addAttribute("isbnList",isbnList);
		return "operateCover";
	}

	@RequestMapping("regist")
	public String regist(ModelMap modelMap, @RequestParam("isbn") String isbn,
			@RequestParam("title") String title, @RequestParam("publisher") String publisher,
			@RequestParam("author") String author, @RequestParam("thumbnail") String thumbnail) throws SQLException {
		
		ISBN isbnInfo = new ISBN();
		isbnInfo.setIsbn(isbn);
		isbnInfo.setTitle(title);
		isbnInfo.setPublisher(publisher);
		isbnInfo.setAuthor(author);
		isbnInfo.setThumbnail(thumbnail.replace("https://cover.openbd.jp/", ""));
		
		DatabaseClass dc = new DatabaseClass(isbnRepository.findAll());
		String message = dc.registDB(isbnInfo,isbnRepository);
		modelMap.addAttribute("message",message);
		
		return "regist";
	}

	@RequestMapping("delete")
	public String delete(ModelMap modelMap, @RequestParam("isbn") String isbn,
			@RequestParam("title") String title, @RequestParam("publisher") String publisher,
			@RequestParam("author") String author, @RequestParam("thumbnail") String thumbnail) throws SQLException {
		
		ISBN isbnInfo = new ISBN();
		isbnInfo.setIsbn(isbn);
		isbnInfo.setTitle(title);
		isbnInfo.setPublisher(publisher);
		isbnInfo.setAuthor(author);
		isbnInfo.setThumbnail(thumbnail.replace("https://cover.openbd.jp/", ""));
		
		DatabaseClass dc = new DatabaseClass(isbnRepository.findAll());
		String message = dc.deleteDB(isbnInfo,isbnRepository);
		modelMap.addAttribute("message",message);
		
		return "delete";
	}
	
	public LoginUser returnInfo() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		List<LoginUser> userList = userRepository.findAll();
		
		MathDo md = new MathDo();
		LoginUser user = md.getUserInfo(userName, userList);
		
		return user;
	}
}
