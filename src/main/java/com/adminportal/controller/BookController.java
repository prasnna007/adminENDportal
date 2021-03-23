package com.adminportal.controller;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.adminportal.entities.Book;
import com.adminportal.entities.ReqBook;
import com.adminportal.service.BookService;
import com.adminportal.service.ReqBookService; 

@Controller
@RequestMapping("/book")
public class BookController {

	@Autowired
	private BookService bookService;
	
	@Autowired
	private ReqBookService reqbookService;
	 
	
	@RequestMapping("/add")
	public String addBook(Model model) {
		Book book = new Book();
		
		model.addAttribute("book", book);
		return "addBook";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String addBookPost( @ModelAttribute("book") Book book, HttpServletRequest request ) 
	{
		
		bookService.save(book);
		MultipartFile bookImage = book.getBookImage();
		
		
		System.out.println(book.getId());
		System.out.println(book.toString());
		
		try {
			byte[] bytes = bookImage.getBytes();
			String name = book.getId()+".png";
			BufferedOutputStream stream =
			new BufferedOutputStream(new FileOutputStream(new java.io.File("src/main/resources/static/images/book/"+name)));
			
			
			stream.write(bytes);
			stream.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return "redirect:bookList";
	}
	
	@RequestMapping("/bookList")
	public String bookList(Model model) {
		List<Book> bookList = bookService.findAll();
		model.addAttribute("bookList", bookList);
		
		return "bookList";
	}
	
	
	
	
	@RequestMapping("/bookInfo")
	public String bookInfo(@RequestParam("id") Integer id, Model model) {
		Book book = bookService.findOne(id);
		model.addAttribute("book",book);
		return "bookInfo";
	}
	
	@RequestMapping("/deleteBook")
	public String deleteBook(@RequestParam("id") Integer id, Model model,HttpServletRequest request) {
		Book book = bookService.findOne(id);
		model.addAttribute("book", book);
		//bookService.
		bookService.remove(book);
		return "redirect:/book/bookList";
	}
	
	
	@RequestMapping("/updateBook")
	public String updateBook(@RequestParam("id") Integer id, Model model) {
		Book book = bookService.findOne(id);
		model.addAttribute("book", book);
		return "updateBook";
	}
	
	
	
	@RequestMapping(value = "/updateBook", method = RequestMethod.POST)
	public String updateBookPost(@ModelAttribute("book") Book book, HttpServletRequest request) {
		bookService.save(book);
		
		MultipartFile bookImage = book.getBookImage();
		if(!bookImage.isEmpty()) {
			try {
				byte[] bytes = bookImage.getBytes();
				String name = book.getId()+".png";
				Files.delete(Paths.get("src/main/resources/static/images/book/"+name));
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new java.io.File("src/main/resources/static/images/book/"+name)));
				stream.write(bytes);
				stream.close();
			}catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
		return "redirect:/book/bookInfo?id="+book.getId();
	}
	
	@RequestMapping("/reqbookLists")
	public String reqbookList(Model model) {
		List<ReqBook> reqbookLists = reqbookService.findAll();
		model.addAttribute("reqbookLists", reqbookLists);
		
		return "reqBookLists";
	}

	 
	
	
	@RequestMapping("/reqbooksInfo")
	public String reqBookInfo(@RequestParam("id") Integer id, Model model) {
		ReqBook reqbook = reqbookService.findOne(id);
		model.addAttribute("reqbook",reqbook);
		return "reqBooksInfo";
	}
	 
	@RequestMapping("/updateReqBook")
	public String updateReqBook(@RequestParam("id") Integer id, Model model) {
		ReqBook reqbook = reqbookService.findOne(id);
		model.addAttribute("reqbook", reqbook);
		return "updateReqBook";
	}
	
	@RequestMapping(value = "/updateReqBook", method = RequestMethod.POST)
	public String updateReqBookPost(@ModelAttribute("book") ReqBook book, HttpServletRequest request) {
		 
		System.out.println(book.toString());
		
		Book bk = new Book();
		
		bk.setActive(true); //Flag
		bk.setAuthor(book.getAuthor());
		bk.setBookImage(book.getBookImage()); 
		bk.setCategory(book.getCategory());
		bk.setDescription(book.getDescription());
		bk.setFormat(book.getFormat());
		//bk.setId(book.getId());
		bk.setInStockNumber(1);
		bk.setIsbn(book.getIsbn());
		bk.setLanguage(book.getLanguage());
		bk.setListPrice(book.getListPrice());
		bk.setNoOfPages(book.getNoOfPages());
		bk.setOurPrice(book.getYourPrice());
		bk.setPublicationDate(book.getPublicationDate());
		bk.setPublisher(book.getPublisher());
		bk.setShippingWeight(0);
		bk.setTitle(book.getTitle());
		bk.setSellerId(book.getReqUserid());
		
		System.out.println(book.getReqUserid());
		System.out.println(book.toString());
		System.out.println(bk.toString());
		
		bookService.save(bk);
		
		System.out.println(bk.getId());
		MultipartFile bookImage = book.getBookImage();
		
		
		if(!bookImage.isEmpty())  
		 {
		 try {
		 byte[] bytes = bookImage.getBytes();
		 String name = bk.getId()+".png";
		 BufferedOutputStream stream =
				 new BufferedOutputStream(new FileOutputStream(new java.io.File("src/main/resources/static/images/book/"+name)));
		  	stream.write(bytes);
		  	stream.close(); 
		  }catch (Exception e) {
		 e.printStackTrace(); 
		  }
		 }
		 
		return "redirect:/book/reqbookLists";
	}
	
	@RequestMapping("/deleteReqBook")
	public String deleteReqBook(@RequestParam("id") Integer id, Model model,HttpServletRequest request) {
		ReqBook reqbook = reqbookService.findOne(id);
		System.out.println(reqbook.toString());
		model.addAttribute("reqbook", reqbook); 
		reqbookService.remove(reqbook);
		return "redirect:/book/reqbookLists";
	}
	
	
	
}
