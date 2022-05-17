package kr.co.skb.agent.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.skb.agent.device.KickboardLocation;
import kr.co.skb.agent.device.KickboardUse;
import lombok.extern.log4j.Log4j2;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@Log4j2
@SpringBootTest
class SkbAgentUtilTests {
    @Value("${kickboard}")
    private String kickboard;
    @Value("#{${ip}}")
    private Map<String, String> ip;
    @Value("#{${url}}")
    private Map<String, String> url;

    // 킥보드 정보 조회
    @Test
    void getKickboard() {
        log.info(kickboard);
    }

    // 관리 시스템 IP, 헬멧 IP 조회
    @Test
    void getIp() {
        log.info(ip);
    }

    // URL 조회
    @Test
    void getUrl() {
        log.info(url);
    }

    @Test
    private RequestBody commonRequest(Object obj) throws Exception {
        if (!(obj instanceof Kickboard
                || obj instanceof KickboardUse
                || obj instanceof KickboardLocation)) {
            log.error("타입 에러");
            throw new Exception();
        }

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(obj);
        RequestBody body = RequestBody.create(JSON, json);

        return body;
    }

    // 로그 테스트
    @Test
    void printLog() {
        log.debug("DEBUG");
    }

    // 킥보드 정보 조회
//    @Test
//    void getKickboard() {
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
//
//        try {
//            Kickboard kickboard = objectMapper.readValue(
//                    kickboardMsg, new TypeReference<Kickboard>() {});
//            log.info(kickboardMsg);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    // 관리 시스템 IP, 헬멧 IP 조회
//    @Test
//    void getIp() {
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
//
//        try {
//            Map<String, String> ip = objectMapper.readValue(
//                    this.ipMsg, new TypeReference<Map<String, String>>() {});
//            log.info(ipMsg);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
