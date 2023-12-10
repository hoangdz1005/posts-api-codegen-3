package com.project.postapicodegen3.repository;

import com.project.postapicodegen3.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
    @Query("SELECT p FROM PostEntity p WHERE :tag MEMBER OF p.tags")
    List<PostEntity> findByTag(@Param("tag") String tag);
}
