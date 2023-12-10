package com.project.postapicodegen3.controller;

import com.project.postapicodegen3.api.PostsApi;
import com.project.postapicodegen3.entity.PostEntity;
import com.project.postapicodegen3.mapper.PostMapper;
import com.project.postapicodegen3.model.PostReq;
import com.project.postapicodegen3.model.PostRes;
import com.project.postapicodegen3.model.StatusUpdate;
import com.project.postapicodegen3.repository.PostRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PostController implements PostsApi {
    private final PostMapper postMapper;
    private final PostRepository postRepository;

    public PostController(PostMapper postMapper, PostRepository postRepository) {
        this.postMapper = postMapper;
        this.postRepository = postRepository;
    }

    @Override
    public ResponseEntity<Void> deletePost(Long postId) {
        postRepository.deleteById(postId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PostRes> getPost(Long postId) {
        return new ResponseEntity<>(postMapper.mapPostResFromPostEntity(postRepository.findById(postId).get()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PostRes> addPost(PostReq postReq) {
        PostEntity postEntity = postMapper.mapPostEntityFromPostReq(postReq);
        postRepository.save(postEntity);
        return new ResponseEntity<>(postMapper.mapPostResFromPostEntity(postEntity), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<PostRes>> getPostsByTag(String tag) {
        return new ResponseEntity<>(postMapper.mapListPostResFromListPostEntity(postRepository.findByTag(tag)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<PostRes>> postsGet() {
        return new ResponseEntity<>(postMapper.mapListPostResFromListPostEntity(postRepository.findAll()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PostRes> updatePost(Long postId, PostReq postReq) {
        PostEntity postEntity = postMapper.mapPostEntityFromPostUpdate(postId, postReq);
        postRepository.save(postEntity);
        return new ResponseEntity<>(postMapper.mapPostResFromPostEntity(postEntity), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PostRes> updatePostStatus(Long postId, StatusUpdate statusUpdate) {
        PostEntity postEntity = postRepository.findById(postId).get();
        postEntity.setStatus(statusUpdate.getStatus());
        postRepository.save(postEntity);
        return new ResponseEntity<>(postMapper.mapPostResFromPostEntity(postEntity), HttpStatus.OK);
    }
}
