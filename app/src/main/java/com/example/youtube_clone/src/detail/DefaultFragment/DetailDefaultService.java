package com.example.youtube_clone.src.detail.DefaultFragment;


import com.example.youtube_clone.src.detail.Comment1Fragment.interfaces.Comment1RetrofitInterface;
import com.example.youtube_clone.src.detail.Comment1Fragment.models.GetCommentResponse;
import com.example.youtube_clone.src.detail.DefaultFragment.interfaces.DefaultActivityView;
import com.example.youtube_clone.src.detail.DefaultFragment.interfaces.DefaultRetrofitInterface;
import com.example.youtube_clone.src.detail.DefaultFragment.models.LikesResponse;
import com.example.youtube_clone.src.detail.DefaultFragment.models.SavedResponse;
import com.example.youtube_clone.src.detail.DefaultFragment.models.Subscribe;
import com.example.youtube_clone.src.detail.DefaultFragment.models.SubscribeResponse;
import com.example.youtube_clone.src.detail.DetailActivity;
import com.example.youtube_clone.src.detail.interfaces.DetailRetrofitInterface;
import com.example.youtube_clone.src.detail.DefaultFragment.models.DetailResponse;
import com.example.youtube_clone.src.detail.models.Likes;
import com.example.youtube_clone.src.main.Home.interfaces.MainActivityView;
import com.example.youtube_clone.src.main.Home.interfaces.MainRetrofitInterface;
import com.example.youtube_clone.src.main.Home.models.DefaultResponse;
import com.example.youtube_clone.src.main.Home.models.StoryResponse;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.youtube_clone.src.ApplicationClass.getRetrofit;

class DetailDefaultService {

    private static DefaultActivityView mDefaultActivityView;

    DetailDefaultService(final DefaultActivityView defaultActivityView) {
        this.mDefaultActivityView = defaultActivityView;
    }

    static void updateLikesStatus(final int idx, int status) {
        final DefaultRetrofitInterface defaultRetrofitInterface = getRetrofit().create(DefaultRetrofitInterface.class);
        defaultRetrofitInterface.patchLikes(idx, new Likes(status)).enqueue(new Callback<LikesResponse>() {
            @Override //실패
            public void onResponse(Call<LikesResponse> call, Response<LikesResponse> response) {
                final LikesResponse likesResponse = response.body(); // 파싱된 값들이 있음
                if (likesResponse == null) {
                    mDefaultActivityView.validateFailure(null);//뷰에 실패했다고 나타냄
                    return;
                }
                mDefaultActivityView.validateSuccess(likesResponse); // 데이터 클래스를 넘겨줌
            }

            @Override  //실패
            public void onFailure(Call<LikesResponse> call, Throwable t) {
                mDefaultActivityView.validateFailure(null); //뷰에 실패했다고 나타냄
            }
        });
    }

    static void getDetailVideoPath(int idx) {
        final DefaultRetrofitInterface defaultRetrofitInterface = getRetrofit().create(DefaultRetrofitInterface.class);
        defaultRetrofitInterface.getDetailVideoPath(idx).enqueue(new Callback<DetailResponse>() { // 복붙해서 사용
            @Override   // 성공
            public void onResponse(Call<DetailResponse> call, Response<DetailResponse> response) {
                final DetailResponse detailResponse = response.body(); // 파싱된 값들이 있음
                if (detailResponse == null) {
                    mDefaultActivityView.validateFailure(null); //뷰에 실패했다고 나타냄
                    return;
                }
                mDefaultActivityView.validateSuccess(detailResponse.getResult()); // 데이터 클래스를 넘겨줌
            }

            @Override  //실패
            public void onFailure(Call<DetailResponse> call, Throwable t) {
                mDefaultActivityView.validateFailure(null); //뷰에 실패했다고 나타냄
            }
        });
    }

    void getVideoPathAndQuery(final int pageNum) {
        final MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);
        mainRetrofitInterface.getVideoPathAndQuery(pageNum).enqueue(new Callback<DefaultResponse>() {
            @Override //실패
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                final DefaultResponse defaultResponse = response.body(); // 파싱된 값들이 있음
                if (defaultResponse == null) {
                    mDefaultActivityView.validateFailure(null); //뷰에 실패했다고 나타냄
                    return;
                }
                mDefaultActivityView.validateSuccess(defaultResponse.getResult()); // 데이터 클래스를 넘겨줌
            }

            @Override  //실패
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                mDefaultActivityView.validateFailure(null); //뷰에 실패했다고 나타냄
            }
        });
    }

    void getStoryPathAndQuery(final int pageNum) {
        final MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);
        mainRetrofitInterface.getStoryPathAndQuery(pageNum).enqueue(new Callback<StoryResponse>() {
            @Override //실패
            public void onResponse(Call<StoryResponse> call, Response<StoryResponse> response) {
                final StoryResponse storyResponse = response.body(); // 파싱된 값들이 있음
                if (storyResponse == null) {
                    mDefaultActivityView.validateFailure(null); //뷰에 실패했다고 나타냄
                    return;
                }
                mDefaultActivityView.validateSuccess(storyResponse.getResult()); // 데이터 클래스를 넘겨줌
            }

            @Override  //실패
            public void onFailure(Call<StoryResponse> call, Throwable t) {
                mDefaultActivityView.validateFailure(null); //뷰에 실패했다고 나타냄
            }
        });
    }

    static void updateSaved(int idx) {
        final DefaultRetrofitInterface defaultRetrofitInterface = getRetrofit().create(DefaultRetrofitInterface.class);
        defaultRetrofitInterface.postSaved(idx).enqueue(new Callback<SavedResponse>() {
            @Override //실패
            public void onResponse(Call<SavedResponse> call, Response<SavedResponse> response) {
                final SavedResponse savedResponse = response.body(); // 파싱된 값들이 있음
                if (savedResponse == null) {
                    mDefaultActivityView.validateFailure(null);//뷰에 실패했다고 나타냄
                    return;
                }
                mDefaultActivityView.validateSuccess(savedResponse); // 데이터 클래스를 넘겨줌
            }

            @Override  //실패
            public void onFailure(Call<SavedResponse> call, Throwable t) {
                mDefaultActivityView.validateFailure(null); //뷰에 실패했다고 나타냄
            }
        });
    }
    static void updateSubscribe(int userIdx,int channelIdx) {
        final DefaultRetrofitInterface defaultRetrofitInterface = getRetrofit().create(DefaultRetrofitInterface.class);
        defaultRetrofitInterface.patchSubscribed(userIdx , new Subscribe(channelIdx)).enqueue(new Callback<SubscribeResponse>() {
            @Override //실패
            public void onResponse(Call<SubscribeResponse> call, Response<SubscribeResponse> response) {
                final SubscribeResponse subscribedResponse = response.body(); // 파싱된 값들이 있음
                if ( subscribedResponse == null) {
                    mDefaultActivityView.validateFailure(null);//뷰에 실패했다고 나타냄
                    return;
                }
                mDefaultActivityView.validateSuccess(subscribedResponse); // 데이터 클래스를 넘겨줌
            }

            @Override  //실패
            public void onFailure(Call<SubscribeResponse> call, Throwable t) {
                mDefaultActivityView.validateFailure(null); //뷰에 실패했다고 나타냄
            }
        });
    }

    static void getOneComment(final int idx) {
        final DefaultRetrofitInterface defaultRetrofitInterface = getRetrofit().create(DefaultRetrofitInterface.class);
        defaultRetrofitInterface.getOneComment(idx, 1,"new").enqueue(new Callback<GetCommentResponse>() {
            @Override //실패
            public void onResponse(Call<GetCommentResponse> call, Response<GetCommentResponse> response) {
                final GetCommentResponse commentResponse = response.body(); // 파싱된 값들이 있음
                if (commentResponse == null) {
                    mDefaultActivityView.validateFailure(null);//뷰에 실패했다고 나타냄
                    return;
                }
                mDefaultActivityView.validateSuccess(commentResponse.getResult()); // 데이터 클래스를 넘겨줌
            }

            @Override  //실패
            public void onFailure(Call<GetCommentResponse> call, Throwable t) {
                mDefaultActivityView.validateFailure(null); //뷰에 실패했다고 나타냄
            }
        });
    }



}

