package com.blog.www.myBlog.test;



import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


// 사용자가 요청 -> 응답(html 파일)
// @Controller

// 사용자가 요청 -> 응답(Data)
@RestController
public class HttpControllerTest {
	
	private static final String TAG = "HttpControllerTest : ";			
	// 인터넷 브라우저는 Get밖에 할 수 없다.
	//ex) http:localhost:8080/http/get
	@GetMapping("/http/lombok")
	public String lombokTest(){
		//builder를 사용하면 생성자를 여러개 생성하지 않고, 사용할 수 있다.
		Member m = Member.builder().username("sasr").password("1234").email("serser@awaer.com").build();

		System.out.println("TAG+"+"getter : "+ m.getId());
		System.out.println("Username : " +m.getUsername());
		m.setId(5000);
		m.setUsername("cos");
		System.out.println("Username : " +m.getUsername());
		System.out.println("TAG+"+"setter : "+ m.getId());
		return "Lom bok test ";
	}
	@GetMapping("/http/get")
	public String getTest(Member m) {
	System.out.println(TAG+"Getter : " + m.getId());
		m.setId(5000);
		System.out.println(TAG+"Getter : " + m.getId());
		return "get" + m.getId();
	} 
	
	@GetMapping("/http/getall")
	public String getAllTest(Member m ) {
		return "get" + m.getId() +","+m.getUsername();
	} 
	// @RequestBody -> text 방식을 받을 수 있다.

	@PostMapping("http/post")
	public String postTest(@RequestBody Member s) {
		return "post" + s.getId() + s.getUsername();
	}
	
	@PostMapping("http/post2")
	public String post2Test(@RequestBody String text) {
		return "post" + text;
	}
	
	@PutMapping("http/put")
	public String putTest(@RequestBody Member m) {
		
		return "post" + m.getId() + m.getUsername();
	} 
	
	@DeleteMapping("http/delete")
	public String deleTest() {
		return "delete";
	}
}
