package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.repositories.UserRepository;

@Controller
public class PostController {
	
	private final PostRepository postsDao;
	private final UserRepository usersDao;
	
	public PostController(PostRepository postsDao, UserRepository usersDao) {
		this.postsDao = postsDao;
		this.usersDao = usersDao;
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
	
	// WITHOUT from-model binding
//	@GetMapping("/posts/create")
//	public String postCreateForm() {
//		return "posts/create";
//	}
	
	// WITH form-model binding
	@GetMapping("/posts/create")
	public String postCreateForm(Model model) {
		model.addAttribute("post", new Post());
		return "posts/create";
	}
	
	// WITHOUT form-model binding
//	@PostMapping("/posts/create")
//	public String postCreate(@RequestParam String title, @RequestParam String body) {
//		User user = usersDao.getOne(1L);
//		Post post = new Post(title, body, user);
//		postsDao.save(post);
//		return "redirect:/posts";
//	}
	
	// WITH form-model binding
	@PostMapping("/posts/create")
	public String postCreate(@ModelAttribute Post post) {
		User user = usersDao.getOne(1L);
		post.setAuthor(user);
		postsDao.save(post);
		return "redirect:/posts";
	}
	// WITHOUT form-model binding
//	@GetMapping("/posts/{id}/edit")
//	public String editForm(@PathVariable long id, Model model) {
//		model.addAttribute("post", postsDao.getOne(id));
//		return "posts/edit";
//	}
	
	// WITH form-model binding
	@GetMapping("/posts/{id}/edit")
	public String editForm(@PathVariable long id, Model model) {
		model.addAttribute("post", postsDao.getOne(id));
		return "posts/edit";
	}
	
	// WITHOUT form-model binding
//	@PostMapping("/posts/{id}/edit")
//	public String update(@PathVariable long id,
//	                     @RequestParam String title,
//	                     @RequestParam String body) {
//
//		Post postToEdit = postsDao.getOne(id);
//
//		postToEdit.setTitle(title);
//		postToEdit.setBody(body);
//
//		postsDao.save(postToEdit);
//
//		return "redirect:/posts/" + id;
//	}
	
	// WITH form-model binding
	@PostMapping("/posts/{id}/edit")
	public String update(@PathVariable long id, @ModelAttribute Post post) {
		User user = usersDao.getOne(1L);
		post.setAuthor(user);
		postsDao.save(post);
		return "redirect:/posts";
	}
	
	@PostMapping("/posts/{id}/delete")
	public String deletePost(@PathVariable long id) {
		postsDao.deleteById(id);
		return "redirect:/posts";
	}
}
