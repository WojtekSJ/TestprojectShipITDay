package io.github.adamw7.genaiagentcore;

import com.aisupport.service.AIService;
import com.aisupport.service.OllamaService;
import com.aisupport.util.AIConnection;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.request.ChatRequest;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.openai.internal.chat.AssistantMessage;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        /*2) Use connection*/
        AIService connection = AIConnection.provideOllamaGemma2Service();

   /*     SystemMessage systemMessage = new SystemMessage("You are a helpful assistant that translates English to French.");
        UserMessage userMessage = new UserMessage("Translate the following English text to French: 'Hello, how are you?'");

        List<ChatMessage>messages = List.of(systemMessage, userMessage);

        ChatRequest request = ChatRequest.builder().messages((List<ChatMessage>) List.of(systemMessage, userMessage)).build();

        ChatResponse response = connection.chat(request);
        System.out.println("Response: " + response.aiMessage().text());

        AIService vllmService = AIConnection.provideVllmService("mistral_7b_it");
        ChatResponse vllmResponse = vllmService.chat(request);
        System.out.println(vllmResponse.aiMessage().text());*/

        SystemMessage systemMessage = new SystemMessage("You are a helpful assistant that translates English to French.");
        UserMessage userMessage = new UserMessage("Translate the following English text to French: 'Hello, how are you?'");
        UserMessage userMessage2 = new UserMessage("Translate the following English text to French: 'Rozmawiamy przez translator?'");
        //AssistantMessage userMessage2 = AssistantMessage.builder().content("Translate the following English text to French: 'Rozmawiamy przez translator?'").build();


// Only system + user is allowed if it's the first message
        ChatRequest request = ChatRequest.builder()
                .messages(List.of(systemMessage, userMessage, userMessage2))
                .build();

        AIService vllmService = AIConnection.provideVllmService("mistral_7b_it");
        //AIService vllmService = AIConnection.provideOllamaGemma2Service();
        //ChatResponse vllmResponse = vllmService.chat(systemMessage, userMessage);
        ChatResponse vllmResponse = vllmService.chat(request);

        System.out.println(vllmResponse);
        String assistantReply = vllmResponse.aiMessage().text();

        AssistantMessage assistantMessage = AssistantMessage.builder()
                .content(vllmResponse.aiMessage().text())
                .build();


        UserMessage nextUserMessage = new UserMessage("Translate: 'Good morning!'");







    }
}