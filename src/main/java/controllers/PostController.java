package controllers;

import models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import repositories.PostRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {
	
	private final PostRepository postsDao;
	
	public PostController(PostRepository postsDao) {
		this.postsDao = postsDao;
	}
	
	@GetMapping("/posts")
	public String postsIndex(Model model) {
		model.addAttribute("posts", postsDao.findAll());
		return "posts/index";
	}
	
	@GetMapping("/posts/{id}")
	public String postById(@PathVariable long id, Model model) {
		Post post = postsDao.getOne(id);
//		model.addAttribute("title", post.getTitle());
//		model.addAttribute("body", post.getBody());
		model.addAttribute("post", post);
		return "posts/show";
	}
	
	@GetMapping("/posts/create")
	@ResponseBody
	public String postCreateForm() {
		return "view the form for creating a post";
	}
	
	@PostMapping("/posts/create")
	@ResponseBody
	public String postCreate() {
		return "create a new post";
	}
	
	@GetMapping("/posts/{id}/edit")
	public String editForm(@PathVariable long id, Model model) {
		model.addAttribute("post", postsDao.getOne(id));
		return "posts/edit";
	}
	
	@PostMapping("/posts/{id}/edit")
	public String update(@PathVariable long id,
	                     @RequestParam String title,
	                     @RequestParam String body) {
		
		Post postToEdit = postsDao.getOne(id);
		
		postToEdit.setTitle(title);
		postToEdit.setBody(body);
		
		postsDao.save(postToEdit);
		
		return "redirect:/posts/" + id;
	}
	
	@PostMapping("/posts/{id}/delete")
	public String deletePost(@PathVariable long id) {
		postsDao.deleteById(id);
		return "redirect:/posts";
	}
}
