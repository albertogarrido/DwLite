package net.albertogarrido.dawandalite.di;


import android.content.Context;

import com.google.gson.GsonBuilder;

import net.albertogarrido.dawandalite.network.NetworkStatusLiveData;
import net.albertogarrido.dawandalite.utilities.AutoValueGsonFactory;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DI {

    private static String BASE_URL = "https://de.dawanda.com/third-party-public/";

    public static Retrofit getRetrofitClient() {

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(getClient())
                .addConverterFactory(getGsonConverter())
                .build();
    }

    private static GsonConverterFactory getGsonConverter() {
        return GsonConverterFactory.create(
                new GsonBuilder().registerTypeAdapterFactory(AutoValueGsonFactory.create())
                        .setPrettyPrinting()
                        .create());
    }

    private static OkHttpClient getClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BASIC);

        Interceptor networkInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();

                // for reviewers: added delay to be able to see the loading on WiFi.
                // This should not go on eventual release, of course
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                return chain.proceed(request);
            }
        };

        return new OkHttpClient.Builder()
                .addNetworkInterceptor(networkInterceptor)
                .addInterceptor(interceptor)
                .build();
    }

    public static NetworkStatusLiveData getNetworkStatusLiveData(Context context) {
        return new NetworkStatusLiveData(context);
    }
}
