package cn.lx.ai.llm.gateway.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * 根路径欢迎信息，用于验证网关已启动。
 */
@RestController
public class IndexController {

    @GetMapping("/")
    public Mono<Map<String, Object>> index() {
        return Mono.just(Map.of(
                "name", "skylark-llm-gateway",
                "status", "running",
                "message", "Skylark LLM Gateway is up. 🚀"
        ));
    }
}
