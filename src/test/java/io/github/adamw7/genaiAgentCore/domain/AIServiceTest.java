package io.github.adamw7.genaiAgentCore.domain;

import com.aisupport.config.AIBasicConfig;
import com.aisupport.service.AIService;
import com.aisupport.util.AIConnection;
import org.junit.Test;

public class AIServiceTest {
    private static final String OLLAMA_ENGINE_NAME = "ollama";
    private static final String VLLM_ENGINE_NAME = "vllm";
    private static final String OLLAMA_DEFAULT_MODEL = "gemma2";
    private static final String VLLM_DEFAULT_MODEL = "mistral_7b_it";
    private static final String OLLAMA_URL = "http://20.185.83.16:8080/";
    private static final String VLLM_URL = "http://9.169.65.166:8080/v1";
    private static final String OLLAMA_API_KEY = "aie93JaTv1GW1AP4IIUSqeecV22HgpcQ6WlgWNyfx2HflkY5hTw19JDbT90ViKcZaZ6lpjOo3YIGgpkG7Zb8jEKvdM5Ymnq9jPm79osLppCebwJ7WdWTwWq3Rf15NDxm";
    private static final String VLLM_API_KEY = "dummy";


    @Test
    public void scenario1() {
        // Simple use of deafault ollama or VLM
        String question = "What is the capital of France?";

        AIService ollamaService = AIConnection.provideOllamaGemma2Service();
        String ollamaResponse = ollamaService.chat(question);
        System.out.println("Ollama response -> \n" + ollamaResponse);

        AIService vllmService = AIConnection.provideVllmBasicService();
        String vllmResponse = vllmService.chat(question);
        System.out.println("VLLM response -> \n" + vllmResponse);

    }

    @Test
    public void scenario2() {
        // Simple use of ollama or VLM with defined model
        String question = "Can people colonize the Moon?";

        AIService ollamaService = AIConnection.provideOllamaService("gemma2");
        String ollamaResponse = ollamaService.chat(question);
        System.out.println("Ollama response -> \n" + ollamaResponse);

        AIService vllmService = AIConnection.provideVllmService("mistral_7b_it");
        String vllmResponse = vllmService.chat(question);
        System.out.println("VLLM response -> \n" + vllmResponse);

    }

    @Test
    public void scenario3() {
        // BasicConfiguration passed for specific model
        String question = "Could You create short joke about star conquering";


        AIBasicConfig configOllama = new AIBasicConfig(OLLAMA_ENGINE_NAME, OLLAMA_API_KEY, OLLAMA_URL, OLLAMA_DEFAULT_MODEL, 2048, 0.7);
        AIBasicConfig configVLLM = new AIBasicConfig(VLLM_ENGINE_NAME, VLLM_API_KEY, VLLM_URL, VLLM_DEFAULT_MODEL, 2048, 0.7);


        AIService ollamaService = AIConnection.provideService(configOllama);
        String ollamaResponse = ollamaService.chat(question);
        System.out.println("Ollama response -> \n" + ollamaResponse);

        AIService vllmService = AIConnection.provideService(configVLLM);
        String vllmResponse = vllmService.chat(question);
        System.out.println("VLLM response -> \n" + vllmResponse);
    }

}



/*
AIBasicConfig {
    protected String engineType;
    protected String apiKey;
    protected String apiUrl;
    protected String modelName;
    protected int maxTokenLimit;
    protected double temperature;
}*/
