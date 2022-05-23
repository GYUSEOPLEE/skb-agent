package kr.co.skb.agent.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import kr.co.skb.agent.domain.Kickboard;
import kr.co.skb.agent.domain.KickboardLocation;
import kr.co.skb.agent.domain.KickboardUse;
import kr.co.skb.agent.domain.RequestInfo;
import lombok.extern.log4j.Log4j2;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.io.IOException;

@Log4j2
@Component
@Validated
public class CommunicationUtil {
    @Autowired RequestInfo.Ip ip;
    @Autowired RequestInfo.Url url;

    // 킥보드 정보 송신
    public boolean request(@Valid final Kickboard kickboard)
            throws IOException, JSONException, Exception {
        try {
            String requestUrl = "http://" + ip.getSystem() + url.getInfo();
            JSONObject code = new JSONObject(request(kickboard, requestUrl).string());
            log.info(code.toString());

            return "200".equals(code.getString("code"));
        } catch (ConstraintViolationException e) {
            log.error("킥보드 정보가 존재하지 않음");
            throw new Exception();
        }
    }

    // 킥보드 사용 정보 송신
    public boolean request(@Valid final KickboardUse kickboardUse)
            throws IOException, JSONException, Exception {
        try {
            String requestUrl = "http://" + ip.getHelmet() + url.getUse();
            JSONObject code = new JSONObject(request(kickboardUse, requestUrl).string());
            log.info(code.toString());

            return "200".equals(code.getString("code"));
        } catch (ConstraintViolationException e) {
            log.error("킥보드 사용 정보가 존재하지 않음");
            throw new Exception();
        }
    }
    
    // 킥보드 위치 정보 송신
    public boolean request(@Valid final KickboardLocation kickboardLocation)
            throws IOException, JSONException, Exception {
        try {
            String requestUrl = "http://" + ip.getSystem() + url.getLocation();
            JSONObject code = new JSONObject(request(kickboardLocation, requestUrl).string());
            log.info(code.toString());

            return "200".equals(code.getString("code"));
        } catch (ConstraintViolationException e) {
            log.error("킥보드 위치 정보가 존재하지 않음");
            throw new Exception();
        }
    }

    public ResponseBody request(Object obj, String requestUrl) throws IOException {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String json = objectMapper.writeValueAsString(obj);
        RequestBody body = RequestBody.create(JSON, json);

        Request request = new Request.Builder()
                .url(requestUrl)
                .post(body)
                .build();

        log.info(request.toString());
        log.info(json);

        Response response = client.newCall(request).execute();
        ResponseBody responseBody = response.body();

        return responseBody;
    }
}