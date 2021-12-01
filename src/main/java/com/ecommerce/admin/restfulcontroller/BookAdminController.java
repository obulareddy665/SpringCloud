package com.ecommerce.admin.restfulcontroller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.admin.model.Book;
import com.ecommerce.admin.model.ResponseBean;
import com.ecommerce.admin.service.BookService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/book")
@Slf4j
public class BookAdminController {
	
	@Autowired
	private BookService bookService;
	
	
	//private static final Logger log = LoggerFactory.getLogger(BookAdminController.class);

	
	@Value("${upload_folder}")
	private String UPLOAD_FOLDER; 

	
	@PostMapping
	public ResponseEntity<?> save(@Valid @RequestBody Book book) {
		log.debug("Book", book);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Custom-Header", "foo");
		ResponseEntity<?> responseEntity = new ResponseEntity<>(bookService.save(book), headers,
				HttpStatus.OK);
		return responseEntity;
		}
	@GetMapping("/findAll")
	@ApiOperation(value = "View a list of available Book", response = Iterable.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 398, message = "Successfully retrieved list"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	public ResponseEntity<?> findAll() {
		log.info("# Find all controller");
		return new ResponseEntity<>(bookService.findAll(), HttpStatus.OK);
	}
	@DeleteMapping
	public ResponseBean delete(@RequestParam("id")Integer id) {
		
		if((bookService.findById(id))==null) {
			return new ResponseBean(205, "Record Is Not Found With id:"+id);
		}
		else {
		
			ResponseBean responseBean=new ResponseBean();
		responseBean.setStatus(203);
		bookService.deleteById(id);
		responseBean.setResult("Deleted Data Successfully with id: "+id);
		
		return responseBean;
		}
		}
	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody Book book) {
		
		return new ResponseEntity<>( bookService.update(book), HttpStatus.OK);
	}
	
		@GetMapping("/findById")
		public ResponseBean findById(@RequestParam("id") Integer id) {
			
			
			
//			if(!(bookService.findById(id)==null)) {
//				return new ResponseBean(205, "Record is not Present with id "+id);
//			}
			ResponseBean responseBean=new ResponseBean();
			responseBean.setStatus(203);
			responseBean.setResult(bookService.findById(id));
			return responseBean;
		}
		@PostMapping(value="/saveWithImg",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
		public ResponseBean save(@RequestPart("book") String stringBook ,@RequestPart("file") MultipartFile file) {
			Book book=new Book();
			ObjectMapper mapper=new ObjectMapper();
			
			try {
				book=mapper.readValue(stringBook, Book.class);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			book=bookService.save(book);
			
			try {
				byte[] bytes=file.getBytes();
				Path path=Paths.get(UPLOAD_FOLDER+book.getId()+".png");
				Files.write(path,bytes);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			ResponseBean responseBean=new ResponseBean();
			responseBean.setStatus(201);
			responseBean.setResult(bookService.save(book));
			return responseBean;
			}
		
}
