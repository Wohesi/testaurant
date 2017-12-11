package com.example.test;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginFragment extends Fragment {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    // Email and password
    private Button login, register;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       final View rootView = inflater.inflate(R.layout.login_fragment, container, false);

        // buttons
        register = rootView.findViewById(R.id.registerButton);
        login = rootView.findViewById(R.id.loginButton);

        final EditText userEmail = (EditText) rootView.findViewById(R.id.email);
        final EditText userPassword = (EditText) rootView.findViewById(R.id.password);



        // login
        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                mAuth = FirebaseAuth.getInstance();
                // Checking if users filled in
                String email = userEmail.getText().toString();
                String password  = userPassword.getText().toString();

                // https://stackoverflow.com/questions/36388581/android-textutils-isempty-vs-string-isempty
                if(TextUtils.isEmpty(email)) {
                    Toast.makeText(getContext(), "Enter email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getContext(), "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Validating users
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener((Activity) getContext(), new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("Signed in", "signInWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.d("Failed to sign in", "signInWithEmail:failure", task.getException());
                                }
                            }
                        });
            }
        });

        // register
        register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent register =  new Intent(LoginFragment.this.getContext(), RegisterActivity.class);
                LoginFragment.this.startActivity(register);

            }
        });

        return rootView;
    }

}