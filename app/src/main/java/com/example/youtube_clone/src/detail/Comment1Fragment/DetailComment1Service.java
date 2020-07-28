package com.example.youtube_clone.src.detail.Comment1Fragment;

import android.widget.Toast;

import com.example.youtube_clone.src.detail.Comment;
import com.example.youtube_clone.src.detail.Comment1Fragment.interfaces.Comment1ActivityView;
import com.example.youtube_clone.src.detail.Comment1Fragment.interfaces.Comment1RetrofitInterface;
import com.example.youtube_clone.src.detail.Comment1Fragment.models.CommentData;
import com.example.youtube_clone.src.detail.Comment1Fragment.models.CommentIndex;
import com.example.youtube_clone.src.detail.Comment1Fragment.models.DeleteCommentResponse;
import com.example.youtube_clone.src.detail.Comment1Fragment.models.GetCommentResponse;
import com.example.youtube_clone.src.detail.Comment1Fragment.models.NewCommentData;
import com.example.youtube_clone.src.detail.Comment1Fragment.models.PatchCommentResponse;
import com.example.youtube_clone.src.detail.Comment1Fragment.models.PostCommentResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.youtube_clone.src.ApplicationClass.getRetrofit;

public class DetailComment1Service {

    private static Comment1ActivityView mComment1ActivityView;

    DetailComment1Service(final Comment1ActivityView comment1ActivityView) {
        this.mComment1ActivityView = comment1ActivityView;
    }

    static void getComment(final int idx, int page) {
        final Comment1RetrofitInterface comment1RetrofitInterface = getRetrofit().create(Comment1RetrofitInterface.class);
        comment1RetrofitInterface.getCommentPathAndQuery(idx, page).enqueue(new Callback<GetCommentResponse>() {
            @Override //실패
            public void onResponse(Call<GetCommentResponse> call, Response<GetCommentResponse> response) {
                final GetCommentResponse commentResponse = response.body(); // 파싱된 값들이 있음
                if (commentResponse == null) {
                    mComment1ActivityView.validateFailure(null);//뷰에 실패했다고 나타냄
                    return;
                }
                mComment1ActivityView.validateSuccess(commentResponse.getResult()); // 데이터 클래스를 넘겨줌
            }

            @Override  //실패
            public void onFailure(Call<GetCommentResponse> call, Throwable t) {
                mComment1ActivityView.validateFailure2((GetCommentResponse) call); //뷰에 실패했다고 나타냄
            }
        });
    }

    static void postComment(final int idx,String text) {
        final Comment1RetrofitInterface comment1RetrofitInterface = getRetrofit().create(Comment1RetrofitInterface.class);
        comment1RetrofitInterface.postCommentPathAndQuery(idx, new CommentData(text)).enqueue(new Callback<PostCommentResponse>() {
            @Override //실패
            public void onResponse(Call<PostCommentResponse> call, Response<PostCommentResponse> response) {
                final PostCommentResponse commentResponse = response.body(); // 파싱된 값들이 있음
                if (commentResponse == null) {
                    mComment1ActivityView.validateFailure(null);//뷰에 실패했다고 나타냄
                    return;
                }
                mComment1ActivityView.validateSuccess(commentResponse); // 데이터 클래스를 넘겨줌
            }

            @Override  //실패
            public void onFailure(Call<PostCommentResponse> call, Throwable t) {
                mComment1ActivityView.validateFailure(null); //뷰에 실패했다고 나타냄
            }
        });
    }

    static void patchComment(final int videoIdx,int commentIdx, String text) {
        final Comment1RetrofitInterface comment1RetrofitInterface = getRetrofit().create(Comment1RetrofitInterface.class);
        comment1RetrofitInterface.patchCommentPathAndQuery(videoIdx, new NewCommentData(commentIdx,text)).enqueue(new Callback<PatchCommentResponse>() {
            @Override //실패
            public void onResponse(Call<PatchCommentResponse> call, Response<PatchCommentResponse> response) {
                final PatchCommentResponse commentResponse = response.body(); // 파싱된 값들이 있음
                if (commentResponse == null) {
                    mComment1ActivityView.validateFailure(null);//뷰에 실패했다고 나타냄
                    return;
                }
                mComment1ActivityView.validateSuccess(commentResponse); // 데이터 클래스를 넘겨줌
            }

            @Override  //실패
            public void onFailure(Call<PatchCommentResponse> call, Throwable t) {
                mComment1ActivityView.validateFailure(null); //뷰에 실패했다고 나타냄
            }
        });
    }

    public static void deleteComment(final int videoIdx,int commentIdx) {
        final Comment1RetrofitInterface comment1RetrofitInterface = getRetrofit().create(Comment1RetrofitInterface.class);
        comment1RetrofitInterface.deleteCommentPathAndQuery(videoIdx, new CommentIndex(commentIdx)).enqueue(new Callback<DeleteCommentResponse>() {
            @Override //실패
            public void onResponse(Call<DeleteCommentResponse> call, Response<DeleteCommentResponse> response) {
                final DeleteCommentResponse commentResponse = response.body(); // 파싱된 값들이 있음
                if (commentResponse == null) {
                    mComment1ActivityView.validateFailure(null);//뷰에 실패했다고 나타냄
                    return;
                }
                mComment1ActivityView.validateSuccess(commentResponse); // 데이터 클래스를 넘겨줌
            }

            @Override  //실패
            public void onFailure(Call<DeleteCommentResponse> call, Throwable t) {
                mComment1ActivityView.validateFailure(null); //뷰에 실패했다고 나타냄
            }
        });
    }

}

