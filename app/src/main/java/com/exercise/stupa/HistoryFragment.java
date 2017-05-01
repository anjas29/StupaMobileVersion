package com.exercise.stupa;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exercise.stupa.adapter.ListPresentAdapter;
import com.exercise.stupa.object.Presence;
import com.exercise.stupa.object.Present;
import com.exercise.stupa.object.PresentDetail;
import com.exercise.stupa.object.retrofit.PresenceResponse;

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
public class HistoryFragment extends Fragment {
    RecyclerView presenceList;
    ArrayList<Present> data;
    LinearLayoutManager layoutManager;
    String apiToken;
    public HistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        apiToken = getActivity().getSharedPreferences("DATA", Context.MODE_PRIVATE).getString("api_token","").toString();
        Log.d("DEBUGS", apiToken);
        presenceList = (RecyclerView)view.findViewById(R.id.presence_list);
        layoutManager= new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        presenceList.setHasFixedSize(true);
        presenceList.setLayoutManager(layoutManager);

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

        OkHttpClient client = httpClient.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.api))
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        final StupaApi service = retrofit.create(StupaApi.class);

        final Call<PresenceResponse> newsResponseCall = service.getPresence();

        newsResponseCall.enqueue(new Callback<PresenceResponse>() {
            @Override
            public void onResponse(Call<PresenceResponse> call, Response<PresenceResponse> response) {
                data = new ArrayList<Present>();
                Log.d("Login Response", ""+response.code());
                PresenceResponse newsData = response.body();
                Log.d("Login Response", ""+response.body());

                if(newsData.isSuccess()) {
                    for(Presence object : newsData.getData()){
                        String created_at = object.getCreated_at();
                        String course = object.getPresence().getDetail_course().getCourse().getName();
                        String presence = object.getPresent();

                        data.add(new Present(presence, course, created_at));
                    }
                    ListPresentAdapter adapter = new ListPresentAdapter(getContext(), data);
                    presenceList.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<PresenceResponse> call, Throwable t) {

            }
        });
    }

}
