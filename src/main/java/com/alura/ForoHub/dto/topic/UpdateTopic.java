package com.alura.ForoHub.dto.topic;

import jakarta.validation.constraints.NotNull;

public record UpdateTopic(
        @NotNull
        Long topicId,
        String titulo,
        String mensaje,
        Topicstatus status,
        String autor
) {
}
