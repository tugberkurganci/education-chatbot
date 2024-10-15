package org.vaadin.marcus.client;

import org.springframework.web.bind.annotation.*;
import org.vaadin.marcus.client.dto.ChatRequest;
import org.vaadin.marcus.langchain4j.LangChain4jAssistant;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@RestController
@RequestMapping("/api/v1/assistants")
public class AssistantService {

    private final LangChain4jAssistant langChain4JAssistant;


    public AssistantService(LangChain4jAssistant langChain4JAssistant) {

        this.langChain4JAssistant = langChain4JAssistant;
    }

    @PostMapping
    public Flux<String> chat(@RequestBody ChatRequest chatRequest) {
        Sinks.Many<String> sink = Sinks.many().unicast().onBackpressureBuffer();
        langChain4JAssistant.chat(chatRequest.getChatId(), chatRequest.getUserMessage(), chatRequest.getWalletId())
                .onNext(sink::tryEmitNext)
                .onComplete(aiMessageResponse -> sink.tryEmitComplete())
                .onError(sink::tryEmitError)
                .start();

        return sink.asFlux();
    }


}
