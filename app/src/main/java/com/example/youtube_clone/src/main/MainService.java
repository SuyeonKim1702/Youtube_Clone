package com.example.youtube_clone.src.main;

import com.example.youtube_clone.src.main.interfaces.MainRetrofitInterface;
import com.example.youtube_clone.src.main.models.DefaultResponse;
import com.example.youtube_clone.src.main.interfaces.MainActivityView;
import com.example.youtube_clone.src.main.models.SignInBody;
import com.example.youtube_clone.src.main.models.SignInResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.youtube_clone.src.ApplicationClass.getRetrofit;

class MainService {
    private final MainActivityView mMainActivityView;

    MainService(final MainActivityView mainActivityView) {
        this.mMainActivityView = mainActivityView;
    }

    void getTest() {
        final MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);
        mainRetrofitInterface.getTest().enqueue(new Callback<DefaultResponse>() { // 복붙해서 사용
            @Override   // 성공
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                final DefaultResponse defaultResponse = response.body(); // 파싱된 값들이 있음
                if (defaultResponse == null) {
                    mMainActivityView.validateFailure(null); //뷰에 실패했다고 나타냄
                    return;
                }

                mMainActivityView.validateSuccess(defaultResponse.getMessage());
            }

            @Override  //실패
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                mMainActivityView.validateFailure(null); //뷰에 실패했다고 나타냄
            }
        });
    }
//로그인 함수를 새로 만들었음
    void postSignIn(String id, String pw) {
        final MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);
        mainRetrofitInterface.signInTest(new SignInBody(id,pw)).enqueue(new Callback<SignInResponse>() {
            //SignInResponse함수에서는 SignInResponse 타입으로 response를 받는다
            @Override   // 성공
            public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {
                final SignInResponse signInResponse = response.body(); // 파싱된 값들이 있음
                if (signInResponse == null) {
                    mMainActivityView.validateFailure(null); //뷰에 실패했다고 나타냄
                    return;
                }

                mMainActivityView.validateSuccess(signInResponse.getMessage());
            }

            @Override  //실패
            public void onFailure(Call<SignInResponse> call, Throwable t) {
                mMainActivityView.validateFailure(null); //뷰에 실패했다고 나타냄
            }
        });
    }


}
