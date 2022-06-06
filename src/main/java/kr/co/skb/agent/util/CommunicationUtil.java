package kr.co.skb.agent.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import kr.co.skb.agent.communication.CommunicationServiceImpl;
import kr.co.skb.agent.domain.Kickboard;
import kr.co.skb.agent.domain.KickboardLocation;
import kr.co.skb.agent.domain.KickboardUse;
import kr.co.skb.agent.domain.RequestInfo;
import lombok.extern.log4j.Log4j2;
import org.json.*;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Properties;

@Log4j2
@Component
@Validated
public class CommunicationUtil {
    static Properties info = new Properties();
    @Autowired RequestInfo.Ip ip;
    @Autowired RequestInfo.Url url;

    // 킥보드 정보 송신
    public boolean request(@Valid final Kickboard kickboard)
            throws IOException, JSONException, ConstraintViolationException {
        try {
            String requestUrl = "http://" + info.getProperty("systemIp") + url.getInfo();
            JSONObject code = new JSONObject(request(kickboard, requestUrl).string());
            log.info(code.toString());

            return "200".equals(code.getString("code"));
        } catch (ConstraintViolationException e) {
            log.error("킥보드 정보가 존재하지 않음");
            throw e;
        }
    }

    // 킥보드 사용 정보 송신
    public boolean request(@Valid final KickboardUse kickboardUse)
            throws IOException, JSONException, ConstraintViolationException {
        try {
            String requestUrl = "http://" + info.getProperty("helmetIp") + url.getUse();
            JSONObject code = new JSONObject(request(kickboardUse, requestUrl).string());
            log.info(code.toString());

            return "200".equals(code.getString("code"));
        } catch (ConstraintViolationException e) {
            log.error("킥보드 사용 정보가 존재하지 않음");
            throw e;
        }
    }
    
    // 킥보드 위치 정보 송신
    public boolean request(@Valid final KickboardLocation kickboardLocation)
            throws IOException, JSONException, ConstraintViolationException {
        try {
            String requestUrl = "http://" + info.getProperty("systemIp") + url.getLocation();

            JSONObject code = new JSONObject(requestLoc(kickboardLocation, requestUrl).string());
            log.info(code.toString());

            return "200".equals(code.getString("code"));
        } catch (ConstraintViolationException e) {
            log.error("킥보드 위치 정보가 존재하지 않음");
            throw e;
        }
    }

    private ResponseBody request(Object obj, String requestUrl) throws IOException {
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

    private ResponseBody requestLoc(KickboardLocation kikKickboardLocation, String requestUrl)
            throws IOException {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        Gson kickboardLoc = new Gson();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("kickboardNo", kikKickboardLocation.getKickboardNo());
        jsonObject.addProperty("latitude", kikKickboardLocation.getLatitude());
        jsonObject.addProperty("longitude", kikKickboardLocation.getLongitude());
        jsonObject.addProperty("dateTime", kikKickboardLocation.getDateTime().toString());

        String json = kickboardLoc.toJson(jsonObject);

        log.info(json);

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