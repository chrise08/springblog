package controllers;

import models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class PostController {
	
	@GetMapping("/posts")
	public String postsIndex(Model model) {
		ArrayList<Post> posts = new ArrayList<>();
		posts.add(new Post(2, "A Title", "A description"));
		posts.add(new Post(3, "Another Title", "Another description"));
		posts.add(new Post(4, "Yet Another Title", "Yet another description"));
		
		model.addAttribute("posts", posts);
		return "posts/index";
	}
	
	@GetMapping("/posts/{id}")
	public String postById(@PathVariable long id, Model model) {
		Post post = new Post(id, "Title", "Description");
		model.addAttribute("title", post.getTitle());
		model.addAttribute("body", post.getBody());
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
}
