package com.onemore.exchange.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
/**
 * HTTPS操作工具類
 * @createTime 2017-11-14 15:44:11
 */
public class HttpsUtil {

    /**
     * 通過登錄憑證code獲取session_key和openid
     * @param appid 小程序ID
     * @param secret 小程序密鑰
     * @param js_code 用戶登錄憑證
     * @return String JSON格式數據
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     * @throws IOException
     * @throws KeyManagementException
     */
    public static String getSession_keyAndOpenID(String appid, String secret, String js_code) throws NoSuchAlgorithmException, NoSuchProviderException, IOException, KeyManagementException {
        StringBuffer result = new StringBuffer();
        String apiUrl="https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";
        // 修改appid、secret和js_code
        apiUrl = apiUrl.replace("APPID", appid).replace("SECRET", secret).replace("JSCODE", js_code);
        //建立連接
        URL url = new URL(apiUrl);
        HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();

        // 創建SSLContext對象，並使用我們指定的信任管理器初始化
        TrustManager[] tm = { new MyX509TrustManager() };
        SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
        sslContext.init(null, tm, new java.security.SecureRandom());
        // 從上述SSLContext對象中得到SSLSocketFactory對象
        SSLSocketFactory ssf = sslContext.getSocketFactory();

        httpUrlConn.setSSLSocketFactory(ssf);
        httpUrlConn.setDoOutput(true);
        httpUrlConn.setDoInput(true);

        // 設置請求方式（GET/POST）
        httpUrlConn.setRequestMethod("GET");

        // 取得輸入流
        InputStream inputStream = httpUrlConn.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        //讀取響應內容
        String str = null;
        while ((str = bufferedReader.readLine()) != null) {
            result.append(str);
        }
        bufferedReader.close();
        inputStreamReader.close();
        // 釋放資源
        inputStream.close();
        httpUrlConn.disconnect();
        //輸出返回結果
        System.out.println(result);
        return result.toString();
    }

}