package com.alura.ForoHub.domain.model;

import com.alura.ForoHub.dto.topic.Topicstatus;
import com.alura.ForoHub.dto.topic.UpdateTopic;
import com.alura.ForoHub.dto.topic.TopicRegistry;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String topicId;

    private String titulo;
    private String mensaje;

    private LocalDateTime fechaCreacion;

    @Enumerated(EnumType.STRING)
    private Topicstatus status;

    private Long autor;

    private Long curso;

    private Boolean activo;

    public Topic(TopicRegistry topicRegistry) {
        this.titulo = topicRegistry.titulo();
        this.mensaje = topicRegistry.mensaje();
        this.fechaCreacion = topicRegistry.fechaCreacion();
        this.status = topicRegistry.status();
        this.autor = Long.valueOf(topicRegistry.autor());
        this.curso = Long.valueOf(topicRegistry.curso());
        this.topicId = topicRegistry.topicId();
        this.activo = true;
    }

    public void UpdateData(UpdateTopic updateTopic) {
        if (updateTopic.topicId() != null) {
            this.topicId = String.valueOf(Long.valueOf(updateTopic.topicId()));
        }
        if (updateTopic.titulo() != null) {
            this.titulo = updateTopic.titulo();
        }
        if (updateTopic.mensaje() != null) {
            this.mensaje = updateTopic.mensaje();
        }
        if (updateTopic.status() != null) {
            this.status = updateTopic.status();
        }
        if (updateTopic.autor() != null) {
            this.autor = Long.valueOf(updateTopic.autor());
        }
    }

    public void disableTopic() {
        this.activo = false;
    }
}
