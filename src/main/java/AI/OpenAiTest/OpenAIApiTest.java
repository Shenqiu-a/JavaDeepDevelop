package AI.OpenAiTest;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.responses.Response;
import com.openai.models.responses.ResponseCreateParams;

/**
 * 功能：
 * 作者：yml
 * 日期：2026/3/2615:18
 */

public class OpenAIApiTest {
    public static void main(String[] args) {
        // 从 system environment 中获取OpenAI API密钥
        OpenAIClient openAIClient = OpenAIOkHttpClient.fromEnv();

        ResponseCreateParams params = ResponseCreateParams.builder()
                .input(" Hi there !!! ")
                .model("gpt-5.4")
                .build();
        Response response = openAIClient.responses().create(params);
        System.out.println(response);
    }
}
