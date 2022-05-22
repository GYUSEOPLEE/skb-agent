package kr.co.skb.agent.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.skb.agent.domain.RequestInfo;
import kr.co.skb.agent.domain.KickboardLocation;
import kr.co.skb.agent.domain.KickboardUse;
import kr.co.skb.agent.domain.Kickboard;
import lombok.extern.log4j.Log4j2;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Log4j2
@Component
public class CommunicationUtil {
    @Autowired RequestInfo.Ip ip;
    @Autowired RequestInfo.Url url;

    public boolean request(final Kickboard kickboard) throws IOException, JSONException {
        String requestUrl = "http://" + ip.getSystem() + url.getInfo();
        JSONObject code = new JSONObject(request(kickboard, requestUrl).string());

        return "200".equals(code.getString("code"));
    }

    public boolean request(final KickboardUse kickboardUse) throws IOException, JSONException {
        String requestUrl = "http://" + ip.getHelmet() + url.getUse();
        JSONObject code = new JSONObject(request(kickboardUse, requestUrl).string());

        return "200".equals(code.getString("code"));
    }

    public boolean request(final KickboardLocation kickboardLocation) throws IOException, JSONException {
        String requestUrl = "http://" + ip.getSystem() + url.getLocation();
        JSONObject code = new JSONObject(request(kickboardLocation, requestUrl).string());

        return "200".equals(code.getString("code"));
    }

    /*
    공통 기능 분리
    대상: request()
        : 킥보드 정보 송신, 킥보드 사용 정보 송신, 킥보드 위치 정보 송신
     */
    public ResponseBody request(Object obj, String requestUrl) throws IOException, JSONException {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(obj);
        RequestBody body = RequestBody.create(JSON, json);

        Request request = new Request.Builder()
                .url(requestUrl)
                .post(body)
                .build();

        Response response = client.newCall(request).execute();
        ResponseBody responseBody = response.body();

        log.info(request.body().toString());
        log.info(responseBody.string());

        return responseBody;
    }
}