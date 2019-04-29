package com.webvisit.utils;

import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GeodeticCurve;
import org.gavaghan.geodesy.GlobalCoordinates;

import java.math.BigDecimal;

/**
 * @author zening.zhu
 * @version 1.0
 * @date 2019/4/29
 */

public class LocationUtils {

    public static double getDistenceMeter(BigDecimal locationALat, BigDecimal locationALng, BigDecimal locationBLat, BigDecimal locationBLng) {
        if (locationALat == null || locationALng == null || locationBLat == null || locationBLng == null) {
            return 0;
        }
        GlobalCoordinates source = new GlobalCoordinates(locationALat.doubleValue(), locationALng.doubleValue());
        GlobalCoordinates target = new GlobalCoordinates(locationBLat.doubleValue(), locationBLng.doubleValue());
        GeodeticCurve geodeticCurve = new GeodeticCalculator().calculateGeodeticCurve(Ellipsoid.Sphere, source, target);
        return geodeticCurve.getEllipsoidalDistance();
    }
}
