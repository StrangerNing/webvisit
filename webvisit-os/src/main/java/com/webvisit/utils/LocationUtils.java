package com.webvisit.utils;

import com.alibaba.fastjson.JSON;
import com.webvisit.common.component.Result;
import com.webvisit.common.exception.BusinessException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GeodeticCurve;
import org.gavaghan.geodesy.GlobalCoordinates;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zening.zhu
 * @version 1.0
 * @date 2019/4/29
 */

public class LocationUtils {

    private static String key = "e59111aad36690d6fb925d2a12b129b7";

    private static String urlPrefix = "https://restapi.amap.com/v3/geocode/regeo";

    public static double getDistanceMeter(BigDecimal locationALat, BigDecimal locationALng, BigDecimal locationBLat, BigDecimal locationBLng) {
        if (locationALat == null || locationALng == null || locationBLat == null || locationBLng == null) {
            return 0;
        }
        GlobalCoordinates source = new GlobalCoordinates(locationALat.doubleValue(), locationALng.doubleValue());
        GlobalCoordinates target = new GlobalCoordinates(locationBLat.doubleValue(), locationBLng.doubleValue());
        GeodeticCurve geodeticCurve = new GeodeticCalculator().calculateGeodeticCurve(Ellipsoid.Sphere, source, target);
        return geodeticCurve.getEllipsoidalDistance();
    }

    public static Object getAddressByLocation(Double locationLng, Double locationLat) {
        CloseableHttpClient client = HttpClients.createDefault();
        StringBuffer buffer = new StringBuffer(urlPrefix);
        buffer.append("?key=").append(key)
                .append("&radius=0")
                .append("&extensions=base")
                .append("&output=JSON")
                .append("&location=").append(locationLng)
                .append(",").append(locationLat);
        HttpGet get = new HttpGet(buffer.toString());
        try {
            HttpResponse response = client.execute(get);
            if (response.getStatusLine().getStatusCode() == 200) {
                return JSON.parse(EntityUtils.toString(response.getEntity(), "utf-8"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new BusinessException("请求详细地址失败");
    }

}
