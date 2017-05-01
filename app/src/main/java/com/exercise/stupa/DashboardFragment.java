package com.exercise.stupa;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.exercise.stupa.adapter.ListPresentAdapter;
import com.exercise.stupa.object.Presence;
import com.exercise.stupa.object.PresenceCount;
import com.exercise.stupa.object.Present;
import com.exercise.stupa.object.retrofit.PresenceResponse;
import com.exercise.stupa.object.retrofit.ProfilResponse;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment {
    String apiToken;
    ArrayList<PresenceCount> data;
    TextView nameView, studentNumberView, classView;
    public DashboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        apiToken = getActivity().getSharedPreferences("DATA", Context.MODE_PRIVATE).getString("api_token","");
        nameView = (TextView)view.findViewById(R.id.nameView);
        studentNumberView = (TextView)view.findViewById(R.id.studentNumberView);
        classView = (TextView)view.findViewById(R.id.classView);
        getData();
        return view;
    }

    public void getData(){
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                Request request = original.newBuilder()
                        .header("Authorization", "Bearer "+apiToken)
                        .header("Content-type","application/json")
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        });

        final OkHttpClient client = httpClient.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.api))
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        final StupaApi service = retrofit.create(StupaApi.class);

        final Call<ProfilResponse> newsResponseCall = service.getProfil();

        newsResponseCall.enqueue(new Callback<ProfilResponse>() {
            @Override
            public void onResponse(Call<ProfilResponse> call, Response<ProfilResponse> response) {
                data = new ArrayList<PresenceCount>();
                ProfilResponse newsData = response.body();

                if(newsData.isSuccess()) {
                    nameView.setText(newsData.getProfil().getName());
                    studentNumberView.setText(newsData.getProfil().getStudent_number());
                    classView.setText(newsData.getProfil().getClasses().getGrade()+""+newsData.getProfil().getClasses().getName());
                }
            }

            @Override
            public void onFailure(Call<ProfilResponse> call, Throwable t) {

            }
        });
    }

}
