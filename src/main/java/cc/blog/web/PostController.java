package cc.blog.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cc.blog.model.Post;
import cc.blog.service.PostService;


@RestController
public class PostController {

	@Autowired
	private PostService service;
	
	@RequestMapping(value="/post", method=RequestMethod.POST)
	public Long addMember(Post post) {
		return service.addPost(post);
	}
	
	@RequestMapping(value="/post", method=RequestMethod.PUT)
	public void updatePost(@RequestBody Post post) {
		service.updatePost(post);
	}
	
	@RequestMapping(value="/post/{postId}", method=RequestMethod.GET)
	public Post viewPost(@PathVariable(value="postId") Long postId) {
		return service.findPostById(postId);
	}
	
	@RequestMapping(value="/post/{postId}", method=RequestMethod.DELETE)
	public void removePost(@PathVariable(value="postId") Long postId) {
		service.removePostById(postId);
	}
	
	@RequestMapping(value="/posts", method=RequestMethod.GET)
	public List<Post> listPosts() {
		return service.findAllPosts();
	}
}
