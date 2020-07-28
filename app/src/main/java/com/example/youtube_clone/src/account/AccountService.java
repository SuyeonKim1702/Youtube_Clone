package com.example.youtube_clone.src.account;

import com.example.youtube_clone.src.account.interfaces.AccountActivityView;
import com.example.youtube_clone.src.account.interfaces.AccountRetrofitInterface;
import com.example.youtube_clone.src.account.models.PostIdTokenResponse;
import com.example.youtube_clone.src.main.Home.interfaces.MainActivityView;
import com.example.youtube_clone.src.main.Home.interfaces.MainRetrofitInterface;
import com.example.youtube_clone.src.main.Home.models.DefaultResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.youtube_clone.src.ApplicationClass.getRetrofit;

class AccountService {
    private static AccountActivityView mAccountActivityView;

    AccountService(final AccountActivityView accountActivityView) {
        this.mAccountActivityView = accountActivityView;
    }


   static void postIdToken(final String token) {
        final AccountRetrofitInterface accountRetrofitInterface = getRetrofit().create(AccountRetrofitInterface.class);
        accountRetrofitInterface.postIdToken(token).enqueue(new Callback<PostIdTokenResponse>() { // 복붙해서 사용
            @Override   // 성공
            public void onResponse(Call<PostIdTokenResponse> call, Response<PostIdTokenResponse> response) {
                final PostIdTokenResponse postIdTokenResponse = response.body(); // 파싱된 값들이 있음
                if (postIdTokenResponse == null) {
                   mAccountActivityView.validateFailure(null); //뷰에 실패했다고 나타냄
                    return;
                }
                mAccountActivityView.validateSuccess(postIdTokenResponse); // 데이터 클래스를 넘겨줌
            }

            @Override  //실패
            public void onFailure(Call<PostIdTokenResponse> call, Throwable t) {
                mAccountActivityView.validateFailure(null); //뷰에 실패했다고 나타냄
            }
        });
    }




}
