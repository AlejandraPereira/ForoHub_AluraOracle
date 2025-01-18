package com.alura.ForoHub.dto.topic;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;


public record TopicRegistry(

        @NotBlank
        String topicId,

        @NotBlank
        String titulo,

        @NotBlank
        String mensaje,

        LocalDateTime fechaCreacion,

        @NotNull
        Topicstatus status,

        @NotBlank
        String autor,

        @NotBlank
        String curso
) {
}
