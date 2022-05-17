package kr.co.skb.agent.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class KickboardUtil {
    @Value("${kickboard}")
    private String kickboard;

    public Kickboard getKickboard() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);

        Kickboard kickboard = objectMapper.readValue(
                this.kickboard, new TypeReference<Kickboard>() {});
        log.info(kickboard);

        return kickboard;
    }

    public boolean isEmpty(final Kickboard kickboard) {
        if (kickboard == null) {
            return true;
        } else if (kickboard.getIp().trim().length() < 1
                || kickboard.getModel().trim().length() < 1
                || kickboard.getIp().trim().length() < 1) {
            return true;
        }

        return false;
    }
}