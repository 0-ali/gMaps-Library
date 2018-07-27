package com.xc0d3rz.gmaps.gstatic;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class gStatic {
    /**
     * Google Direction API
     */
    private static String DirectionAPI = "https://maps.googleapis.com/maps/api/directions/json?origin=$A&destination=$B&waypoints=$C";

    /**
     * Google Static Maps API
     */
    private static String StaticAPI = "https://maps.googleapis.com/maps/api/staticmap?size=$A&maptype=roadmap&path=color:black|weight:5|enc:$B";
    /**
     * Start Map Marker
     */
    public static String sMarker = "https://www.wpgmaps.com/wp-content/uploads/2015/12/Marker-A-Retina.png";

    /**
     * End Map Marker
     */
    public static String eMarker = "https://www.wpgmaps.com/wp-content/uploads/2015/12/Marker-B-Retina.png";

    /**
     * Default Map Size
     */
    public static String mapSize = "640x640";
    /**
     * Google Map API Key
     */
    public static String gMapsKey = "";


    /**
     * '
     *
     * @param origin
     * @param destination
     * @param waypoints
     * @return
     */
    public static String getMap(String origin, String destination, List<String> waypoints) {
        String $final = "";
        String urlDirection = String.valueOf(DirectionAPI).replace("$A", String.valueOf(origin)).replace("$B", String.valueOf(destination)).replace("$C", implode(waypoints, "|"));
        try {
            JSONObject directionResults = getJSONObjectFromURL(urlDirection);
            JSONArray routesArray = directionResults.getJSONArray("routes");
            JSONObject route = routesArray.getJSONObject(0);
            JSONObject overviewPolyline = route.getJSONObject("overview_polyline");
            String routePath = overviewPolyline.getString("points");
            return String.valueOf(StaticAPI).replace("$A", mapSize).replace("$B", routePath) + "&markers=icon:" + sMarker + "%7C" + origin +
                    "&markers=icon:" + eMarker + "%7C" + destination + "&key=" + gMapsKey;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "https://maps.googleapis.com/maps/api/staticmap?center=Khartoum,+SD&zoom=13&scale=1&size=600x300&maptype=roadmap&format=png&visual_refresh=true";
    }

    /**
     * @param origin
     * @param destination
     * @param waypoints
     * @return
     */
    public static Bitmap getMapAsBitmap(String origin, String destination, List<String> waypoints) {
        String $final = "";
        String urlDirection = String.valueOf(DirectionAPI).replace("$A", String.valueOf(origin)).replace("$B", String.valueOf(destination)).replace("$C", implode(waypoints, "|"));
        try {
            JSONObject directionResults = getJSONObjectFromURL(urlDirection);
            JSONArray routesArray = directionResults.getJSONArray("routes");
            JSONObject route = routesArray.getJSONObject(0);
            JSONObject overviewPolyline = route.getJSONObject("overview_polyline");
            String routePath = overviewPolyline.getString("points");
            $final = String.valueOf(StaticAPI).replace("$A", mapSize).replace("$B", routePath) + "&markers=icon:" + sMarker + "%7C" + origin +
                    "&markers=icon:" + eMarker + "%7C" + destination + "&key=" + gMapsKey;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        $final = "https://maps.googleapis.com/maps/api/staticmap?center=Khartoum,+SD&zoom=13&scale=1&size=600x300&maptype=roadmap&format=png&visual_refresh=true";
        return getBitmapFromURL($final);
    }

    /**
     * @param urlString
     * @return
     * @throws IOException
     * @throws JSONException
     */
    private static JSONObject getJSONObjectFromURL(String urlString) throws IOException, JSONException {
        HttpURLConnection urlConnection = null;
        URL url = new URL(urlString);
        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setReadTimeout(10000 /* milliseconds */);
        urlConnection.setConnectTimeout(15000 /* milliseconds */);
        urlConnection.setDoOutput(true);
        urlConnection.connect();

        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        StringBuilder sb = new StringBuilder();

        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line + "\n");
        }
        br.close();
        String jsonString = sb.toString();
        return new JSONObject(jsonString);
    }

    /**
     * @param items
     * @param separator
     * @return
     */
    private static String implode(List<String> items, String separator) {

        if (items == null || items.isEmpty()) {
            return null;
        }
        String delimiter = "";
        StringBuilder builder = new StringBuilder();
        for (String item : items) {
            builder.append(delimiter).append(item);
            delimiter = separator;
        }
        return builder.toString();
    }

    /**
     * @param src
     * @return
     */
    private static Bitmap getBitmapFromURL(String src) {
        try {

            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
