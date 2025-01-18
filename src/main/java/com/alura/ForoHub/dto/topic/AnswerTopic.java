package com.alura.ForoHub.dto.topic;

import java.time.LocalDateTime;

public record AnswerTopic(

        Long id,

         String topicId,

         String titulo,

         String mensaje,

         LocalDateTime fechaCreacion,

         Topicstatus status,

         String autor,

         String curso) {

    public AnswerTopic(Long id, String topicId, String titulo, String mensaje, LocalDateTime fechaCreacion, Topicstatus status, Long autor, Long curso) {

        this(
                id,
                topicId,
                titulo,
                mensaje,
                fechaCreacion,
                status,
                autor.toString(),
                curso.toString()
        );
    }

}
