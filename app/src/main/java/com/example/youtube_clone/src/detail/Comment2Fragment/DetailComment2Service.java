package com.example.youtube_clone.src.detail.Comment2Fragment;

import com.example.youtube_clone.src.detail.Comment1Fragment.interfaces.Comment1ActivityView;
import com.example.youtube_clone.src.detail.Comment1Fragment.interfaces.Comment1RetrofitInterface;
import com.example.youtube_clone.src.detail.Comment1Fragment.models.CommentData;
import com.example.youtube_clone.src.detail.Comment1Fragment.models.GetCommentResponse;
import com.example.youtube_clone.src.detail.Comment1Fragment.models.PostCommentResponse;
import com.example.youtube_clone.src.detail.Comment2Fragment.interfaces.Comment2ActivityView;
import com.example.youtube_clone.src.detail.Comment2Fragment.interfaces.Comment2RetrofitInterface;
import com.example.youtube_clone.src.detail.Comment2Fragment.models.DeleteReplyResponse;
import com.example.youtube_clone.src.detail.Comment2Fragment.models.GetReplyResponse;
import com.example.youtube_clone.src.detail.Comment2Fragment.models.NewReplyData;
import com.example.youtube_clone.src.detail.Comment2Fragment.models.PatchReplyResponse;
import com.example.youtube_clone.src.detail.Comment2Fragment.models.PostReplyResponse;
import com.example.youtube_clone.src.detail.Comment2Fragment.models.ReplyData;
import com.example.youtube_clone.src.detail.Comment2Fragment.models.ReplyIndex;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.youtube_clone.src.ApplicationClass.getRetrofit;

public class DetailComment2Service {

    private static Comment2ActivityView mComment2ActivityView;

    DetailComment2Service(final Comment2ActivityView comment2ActivityView) {
        this.mComment2ActivityView = comment2ActivityView;
    }

    static void getReply(final int videoIdx,int commentIdx, int page) {
        final Comment2RetrofitInterface comment2RetrofitInterface = getRetrofit().create(Comment2RetrofitInterface.class);
        comment2RetrofitInterface.getReplyPathAndQuery(videoIdx, commentIdx, page).enqueue(new Callback<GetReplyResponse>() {
            @Override //실패
            public void onResponse(Call<GetReplyResponse> call, Response<GetReplyResponse> response) {
                final GetReplyResponse replyResponse = response.body(); // 파싱된 값들이 있음
                if (replyResponse == null) {
                    mComment2ActivityView.validateFailure(null);//뷰에 실패했다고 나타냄
                    return;
                }
                mComment2ActivityView.validateSuccess(replyResponse.getResult()); // 데이터 클래스를 넘겨줌
            }

            @Override  //실패
            public void onFailure(Call<GetReplyResponse> call, Throwable t) {
                mComment2ActivityView.validateFailure(null); //뷰에 실패했다고 나타냄
            }
        });
    }

    static void postReply(final int videoIdx,int commentIdx, String text) {
        final Comment2RetrofitInterface comment2RetrofitInterface = getRetrofit().create(Comment2RetrofitInterface.class);
        comment2RetrofitInterface.postReplyPathAndQuery(videoIdx,commentIdx, new ReplyData(text)).enqueue(new Callback<PostReplyResponse>() {
            @Override //실패
            public void onResponse(Call<PostReplyResponse> call, Response<PostReplyResponse> response) {
                final PostReplyResponse replyResponse = response.body(); // 파싱된 값들이 있음
                if (replyResponse == null) {
                    mComment2ActivityView.validateFailure(null);//뷰에 실패했다고 나타냄
                    return;
                }
                mComment2ActivityView.validateSuccess(replyResponse); // 데이터 클래스를 넘겨줌
            }

            @Override  //실패
            public void onFailure(Call<PostReplyResponse> call, Throwable t) {
                mComment2ActivityView.validateFailure(null); //뷰에 실패했다고 나타냄
            }
        });
    }

    static void patchReply(final int videoIdx,int commentIdx, String text,int replyIndex) {

        final Comment2RetrofitInterface comment2RetrofitInterface = getRetrofit().create(Comment2RetrofitInterface.class);
        comment2RetrofitInterface.patchReplyPathAndQuery(videoIdx,commentIdx, new NewReplyData(replyIndex,text)).enqueue(new Callback<PatchReplyResponse>() {
            @Override //실패
            public void onResponse(Call<PatchReplyResponse> call, Response<PatchReplyResponse> response) {
                final PatchReplyResponse replyResponse = response.body(); // 파싱된 값들이 있음
                if (replyResponse == null) {
                    mComment2ActivityView.validateFailure(null);//뷰에 실패했다고 나타냄
                    return;
                }
                mComment2ActivityView.validateSuccess(replyResponse); // 데이터 클래스를 넘겨줌
            }

            @Override  //실패
            public void onFailure(Call<PatchReplyResponse> call, Throwable t) {
                mComment2ActivityView.validateFailure(null); //뷰에 실패했다고 나타냄
            }
        });
    }

    public static void deleteReply(final int videoIdx, int commentIdx, int replyIndex) {

        final Comment2RetrofitInterface comment2RetrofitInterface = getRetrofit().create(Comment2RetrofitInterface.class);
        comment2RetrofitInterface.deleteReplyPathAndQuery(videoIdx,commentIdx, new ReplyIndex(replyIndex)).enqueue(new Callback<DeleteReplyResponse>() {
            @Override //실패
            public void onResponse(Call<DeleteReplyResponse> call, Response<DeleteReplyResponse> response) {
                final DeleteReplyResponse replyResponse = response.body(); // 파싱된 값들이 있음
                if (replyResponse == null) {
                    mComment2ActivityView.validateFailure(null);//뷰에 실패했다고 나타냄
                    return;
                }
                mComment2ActivityView.validateSuccess(replyResponse); // 데이터 클래스를 넘겨줌
            }

            @Override  //실패
            public void onFailure(Call<DeleteReplyResponse> call, Throwable t) {
                mComment2ActivityView.validateFailure(null); //뷰에 실패했다고 나타냄
            }
        });
    }
}

