package com.alura.ForoHub.dto.topic;

import com.alura.ForoHub.domain.model.Topic;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TopicList(
        @NotBlank
        String topicId,

        @NotBlank
        String titulo,

        @NotBlank
        String mensaje,

        LocalDateTime fechaCreacion,

        Topicstatus status,

        @NotNull
        Long autor,

        @NotNull
        Long curso
) {

    public TopicList(Topic topic) {
        this(String.valueOf(Long.valueOf(topic.getTopicId())), topic.getTitulo(), topic.getMensaje(),
                topic.getFechaCreacion(), topic.getStatus(),
                topic.getAutor(), topic.getCurso());
    }
}
