package com.vijaya.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    public static String LoggedInUser1="";
    public static String LoggedInUser2="";
    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };

    private FirebaseAuth auth;

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    public void attemptLogin(View v) {

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        final String email = mEmailView.getText().toString();
        final String password = mPasswordView.getText().toString();

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
        }


/*

*/
        //authenticate user
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if(LoggedInUser1.equalsIgnoreCase("")) {
                            LoggedInUser1 = email+","+password;
                        }else
                        {
                            LoggedInUser2 = email+","+password;
                        }
                        if (!task.isSuccessful()) {
                            // there was an error
                            Toast.makeText(LoginActivity.this, getString(R.string.auth_fail_msg), Toast.LENGTH_LONG).show();

                        } else {
                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            intent.putExtra("email",email);
                            intent.putExtra("pass", password);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    public void redirectToSignUpPage(View v) {
        Intent redirect = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(redirect);
    }

    public void redirectToForgotPasswordPage(View v) {
        Intent redirect = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
        startActivity(redirect);
    }

    public void redirectToUser1(View view) {
        if(LoggedInUser1.equalsIgnoreCase(""))
        {
            Toast.makeText(LoginActivity.this, "User has already logged out", Toast.LENGTH_LONG).show();
        }
        else {

            String[] str = LoggedInUser1.split(",");
            final String emailUser1 = str[0];
            final String passwordUser1 = str[1];

            auth.signInWithEmailAndPassword(emailUser1, passwordUser1)
                    .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.

                            if (!task.isSuccessful()) {
                                // there was an error
                                Toast.makeText(LoginActivity.this, getString(R.string.auth_fail_msg), Toast.LENGTH_LONG).show();

                            } else {
                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                intent.putExtra("email", emailUser1);
                                intent.putExtra("pass", passwordUser1);
                                startActivity(intent);
                                finish();
                            }
                        }
                    });

        }
    }

    public void redirectToUser2(View view) {

        if (LoggedInUser2.equalsIgnoreCase("")) {
            Toast.makeText(LoginActivity.this, "User has already logged out", Toast.LENGTH_LONG).show();
        }
        else {
        String[] str = LoggedInUser2.split(",");
        final String emailUser2 = str[0];
        final String passwordUser2 = str[1];
        auth.signInWithEmailAndPassword(emailUser2, passwordUser2)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.

                        if (!task.isSuccessful()) {
                            // there was an error
                            Toast.makeText(LoginActivity.this, getString(R.string.auth_fail_msg), Toast.LENGTH_LONG).show();

                        } else {
                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            intent.putExtra("email", emailUser2);
                            intent.putExtra("pass", passwordUser2);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
    }
    }
}