package com.example.youtube_clone.src.account;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.youtube_clone.R;
import com.example.youtube_clone.src.account.interfaces.AccountActivityView;
import com.example.youtube_clone.src.account.models.PostIdTokenResponse;
import com.example.youtube_clone.src.detail.interfaces.DetailActivityView;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import static com.example.youtube_clone.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.example.youtube_clone.src.ApplicationClass.sSharedPreferences;

public class AccountActivity extends AppCompatActivity implements AccountActivityView {

    private static final int RC_SIGN_IN = 1;
    Button logIn;
    GoogleSignInClient mGoogleSignInClient;
    SignInButton signInButton;
    private FirebaseAuth mAuth;
    GoogleSignInAccount account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);


        signInButton = findViewById(R.id.sign_in_button);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestServerAuthCode(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        mAuth = FirebaseAuth.getInstance();


        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               signIn();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();  // 이미 로그인되어있는지 여부 확인해서 ui 업데이트하기
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(account);
    }

    private void signIn() { //권한 동의창 띄움
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void signOut() {
        FirebaseAuth.getInstance().signOut();
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("msg", "signInWithCredential:success");

                            AccountService.postIdToken(idToken);
                            //서버에 토큰 아이디 전달 -> service에서 전달해주기!

                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("msg", "signInWithCredential:failure", task.getException());
                            //Snackbar.make(mBinding.mainLayout, "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
                            // updateUI(null);
                        }
                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                account = task.getResult(ApiException.class);
                Log.d("msg", "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());

            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w("msg", "Google sign in failed", e);
                // ...
            }
        }
    }


    @Override
    public void validateSuccess(PostIdTokenResponse result) {
        //여기서 jwt 저장
        //sSharedPreferences.edit().putString(X_ACCESS_TOKEN, "sfhkjsdahfkljshkjsagklshfklakjsfhkjsakf").apply();
    }

    @Override
    public void validateFailure(String message) {
        Toast.makeText(this, "jwt를 받아오지 못했습니다", Toast.LENGTH_SHORT).show();

    }
}