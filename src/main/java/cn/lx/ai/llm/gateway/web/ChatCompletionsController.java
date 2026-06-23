package cn.lx.ai.llm.gateway.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.List;
import java.util.Map;

/**
 * OpenAI 兼容的对话补全接口。
 *
 * <p>第③步：先 return 写死的 OpenAI 格式假数据，把接口骨架跑通（curl 测得通）。
 * 这一步不接任何上游模型，纯粹验证「请求进得来、响应是标准 OpenAI 结构」。
 * 后续第④步再换成真正转发到上游 LLM。
 */
@RestController
public class ChatCompletionsController {

    /**
     * 接收 OpenAI 风格的对话请求，返回写死的标准响应。
     *
     * @param request 客户端传来的请求体（这一步先不解析，原样收下即可）
     */
    @PostMapping("/v1/chat/completions")
    public Mono<Map<String, Object>> chatCompletions(@RequestBody(required = false) Map<String, Object> request) {
        // 一条符合 OpenAI chat.completion 结构的写死响应。
        // 关键字段：object=chat.completion、choices[].message、usage。
        Map<String, Object> message = Map.of(
                "role", "assistant",
                "content", "你好，我是 Skylark 网关返回的写死假数据。接口骨架已通 🚀"
        );

        Map<String, Object> choice = Map.of(
                "index", 0,
                "message", message,
                "finish_reason", "stop"
        );

        Map<String, Object> usage = Map.of(
                "prompt_tokens", 9,
                "completion_tokens", 12,
                "total_tokens", 21
        );

        Map<String, Object> response = Map.of(
                "id", "chatcmpl-skylark-stub",
                "object", "chat.completion",
                "created", Instant.now().getEpochSecond(),
                "model", "skylark-stub",
                "choices", List.of(choice),
                "usage", usage
        );

        return Mono.just(response);
    }
}
