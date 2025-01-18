package com.alura.ForoHub.controller;
import com.alura.ForoHub.dto.topic.UpdateTopic;
import com.alura.ForoHub.dto.topic.TopicList;
import com.alura.ForoHub.dto.topic.TopicRegistry;
import com.alura.ForoHub.dto.topic.AnswerTopic;
import com.alura.ForoHub.domain.model.Topic;
import com.alura.ForoHub.domain.repository.TopicRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicController {

    @Autowired
    private TopicRepository topicRepository;

    //REGISTER TOPICS
    @PostMapping
    public ResponseEntity<AnswerTopic> registerTopic(@RequestBody @Valid TopicRegistry topicRegistry, UriComponentsBuilder uriComponentsBuilder){
        Topic topic = topicRepository.save(new Topic(topicRegistry));
        AnswerTopic answerTopic = new AnswerTopic(
                topic.getId(),
                String.valueOf(topic.getTopicId()),
                topic.getTitulo(),
                topic.getMensaje(),
                topic.getFechaCreacion(),
                topic.getStatus(),
                topic.getAutor(),
                topic.getCurso());
        URI url =uriComponentsBuilder.path("/topicos").buildAndExpand(topic.getId()).toUri();
        return ResponseEntity.created(url).body(answerTopic);
    }

    //LIST TOPICS
    @GetMapping
    public ResponseEntity<Page <TopicList>> topicList(@PageableDefault(sort = "fechaCreacion",direction = Sort.Direction.ASC) Pageable paginacion){
        //return  topicRepository.findAll(paginacion).map(ListaTopico::new);
        return ResponseEntity.ok(topicRepository.findByActivoTrue(paginacion)
                .map(TopicList::new));
    }

    //UPDATE TOPICS
    @PutMapping("{topicId}")
    @Transactional
    public ResponseEntity updateTopic(@PathVariable Long topicId, @RequestBody @Valid UpdateTopic updateTopic){
        Topic topic = topicRepository.getReferenceById(topicId);
        topic.UpdateData(updateTopic);
        return ResponseEntity.ok(new AnswerTopic(
                topic.getId(),
                String.valueOf(topic.getTopicId()),
                topic.getTitulo(),
                topic.getMensaje(),
                topic.getFechaCreacion(),
                topic.getStatus(),
                topic.getAutor(),
                topic.getCurso()));
    }

    //DELETE TOPICS
    @DeleteMapping("/{topicId}")
    @Transactional
    public ResponseEntity eliminaTopico(@PathVariable Long topicId){
        Topic topic = topicRepository.getReferenceById(topicId);
        topic.disableTopic();
        return ResponseEntity.noContent().build();

    }


//    Delete from data base
//    @DeleteMapping("/{topicId}")
//    @Transactional
//    public void eliminaTopico(@PathVariable Long topicId){
//        Topico topico = topicRepository.getReferenceById(topicId);
//        topicRepository.delete(topico);
//    }


    //RETURN DATA/TOPICS
    @GetMapping("/{topicId}")
    public ResponseEntity<AnswerTopic> retornaDatosTopico(@PathVariable Long topicId){
        Topic topic = topicRepository.getReferenceById(topicId);
        var datosTopico = new AnswerTopic(
                topic.getId(),
                String.valueOf(topic.getTopicId()),
                topic.getTitulo(),
                topic.getMensaje(),
                topic.getFechaCreacion(),
                topic.getStatus(),
                topic.getAutor(),
                topic.getCurso());
        return ResponseEntity.ok(datosTopico);

    }
}
