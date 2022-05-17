package kr.co.skb.agent.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.skb.agent.device.KickboardLocation;
import kr.co.skb.agent.device.KickboardUse;
import lombok.extern.log4j.Log4j2;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Log4j2
@Component
public class CommunicationUtil {
    @Value("#{${ip}}")
    private Map<String, String> ip;
    @Value("#{${url}}")
    private Map<String, String> url;

    public boolean request(final Kickboard kickboard) throws IOException, JSONException {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(kickboard);
        RequestBody body = RequestBody.create(JSON, json);

        Request request = new Request.Builder()
                .url("http://" + ip.get("system") + url.get("info"))
                .post(body)
                .build();

        Response response = client.newCall(request).execute();
        ResponseBody responseBody = response.body();
        JSONObject code = new JSONObject(responseBody.string());

        return "200".equals(code.getString("code"));
    }

    public boolean request(final KickboardUse kickboardUse) throws IOException, JSONException {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(kickboardUse);
        RequestBody body = RequestBody.create(JSON, json);

        Request request = new Request.Builder()
                .url("http://" + ip.get("helmet") + url.get("use"))
                .post(body)
                .build();

        Response response = client.newCall(request).execute();
        ResponseBody responseBody = response.body();
        JSONObject code = new JSONObject(responseBody.string());

        return "200".equals(code.getString("code"));
    }

    public boolean request(final KickboardLocation kickboardLocation) throws IOException, JSONException {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(kickboardLocation);
        RequestBody body = RequestBody.create(JSON, json);

        Request request = new Request.Builder()
                .url("http://" + ip.get("system") + url.get("location"))
                .post(body)
                .build();

        Response response = client.newCall(request).execute();
        ResponseBody responseBody = response.body();
        JSONObject code = new JSONObject(responseBody.string());

        return "200".equals(code.getString("code"));
    }
}