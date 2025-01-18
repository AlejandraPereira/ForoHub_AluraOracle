package com.alura.ForoHub.domain.repository;

import com.alura.ForoHub.domain.model.Topic;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository  extends JpaRepository<Topic, Long> {

    Page<Topic> findByActivoTrue(Pageable pageable);
}
