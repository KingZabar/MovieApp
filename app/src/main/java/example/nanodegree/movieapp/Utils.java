package example.nanodegree.movieapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;


public class Utils {

    // private static String TAG = Utils.class.getSimpleName();

    /**
     * Returns String content from a url.
     */
    public static String getStringRequest(String url) {

        try {
            InputStream inputStream = new URL(url).openConnection().getInputStream();

            if (inputStream == null)
                return null;

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            reader.close();
            inputStream.close();

            return sb.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static boolean isInternetConnected(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (NetworkInfo anInfo : info)
                    if (anInfo.getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
        }
        return false;
    }
}
