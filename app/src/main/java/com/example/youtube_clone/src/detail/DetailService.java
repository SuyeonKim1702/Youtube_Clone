package com.example.youtube_clone.src.detail;

import com.example.youtube_clone.src.detail.interfaces.DetailActivityView;
import com.example.youtube_clone.src.detail.interfaces.DetailRetrofitInterface;
import com.example.youtube_clone.src.detail.models.DetailResponse;
import com.example.youtube_clone.src.splash.models.DefaultResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.youtube_clone.src.ApplicationClass.getRetrofit;


public class DetailService {

    private static DetailActivityView mDetailActivityView = null;

    DetailService(final DetailActivityView detailActivityView) {
        this.mDetailActivityView = detailActivityView;
    }


     static void getDetailVideoPath(int idx) {
        final DetailRetrofitInterface detailRetrofitInterface = getRetrofit().create(DetailRetrofitInterface.class);
       detailRetrofitInterface.getDetailVideoPath(idx).enqueue(new Callback<com.example.youtube_clone.src.detail.models.DetailResponse>() { // 복붙해서 사용
            @Override   // 성공
            public void onResponse(Call<DetailResponse> call, Response<DetailResponse> response) {
                final DetailResponse detailResponse = response.body(); // 파싱된 값들이 있음
                if (detailResponse == null) {
                    mDetailActivityView.validateFailure(null); //뷰에 실패했다고 나타냄
                    return;
                }
                mDetailActivityView.validateSuccess(detailResponse.getResult()); // 데이터 클래스를 넘겨줌
            }

            @Override  //실패
            public void onFailure(Call<DetailResponse> call, Throwable t) {
                mDetailActivityView.validateFailure(null); //뷰에 실패했다고 나타냄
            }
        });
    }

}

