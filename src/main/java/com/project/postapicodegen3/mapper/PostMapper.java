package com.project.postapicodegen3.mapper;

import com.project.postapicodegen3.entity.PostEntity;
import com.project.postapicodegen3.model.PostReq;
import com.project.postapicodegen3.model.PostRes;
import com.project.postapicodegen3.repository.PostRepository;
import org.springframework.stereotype.Service;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostMapper {
    private final PostRepository postRepository;

    public PostMapper(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostEntity mapPostEntityFromPostReq(PostReq from) {
        PostEntity to = new PostEntity();
        to.setTitle(from.getTitle());
        to.setAuthor(from.getAuthor());
        to.setTags(from.getTags());
        to.setImage(from.getImage());
        to.setStatus(from.getStatus());
        to.setCreatedAt(OffsetDateTime.now());
        return to;
    }
    public PostRes mapPostResFromPostEntity(PostEntity from) {
        PostRes to = new PostRes();
        to.setId(from.getId());
        to.setAuthor(from.getAuthor());
        to.setTags(from.getTags());
        to.setStatus(from.getStatus());
        to.setImage(from.getImage());
        to.setTitle(from.getTitle());
        to.setCreatedAt(from.getCreatedAt());
        return to;
    }
    public List<PostRes> mapListPostResFromListPostEntity(List<PostEntity> from) {
        List<PostRes> to = new ArrayList<>();
        from.forEach(p -> {
            to.add(mapPostResFromPostEntity(p));
        });
        return to;
    }
    public PostEntity mapPostEntityFromPostUpdate(Long id, PostReq from) {
        PostEntity to = postRepository.findById(id).get();
        to.setTitle(from.getTitle());
        to.setAuthor(from.getAuthor());
        to.setTags(from.getTags());
        to.setStatus(from.getStatus());
        to.setImage(from.getImage());
        return to;
    }
}
