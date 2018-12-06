package analite.mobileapp.data.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import analite.mobileapp.R;
import analite.mobileapp.data.persistence.SimpleUserRepository;
import analite.mobileapp.data.entities.User;

public class SignUpActivity extends AppCompatActivity {

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private SimpleUserRepository dummy_users;

    private UserSignUpTask signUpTask = null;

    private AutoCompleteTextView emailView;
    private AutoCompleteTextView usernameView;
    private EditText passwordView;
    private View progressView;
    private View confirmationView;
    private View signUpFormView;

    private ImageButton backButton;
    private Button singUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        dummy_users = new SimpleUserRepository();
        componentsInitialization();
        actionListenersInitialization();
    }

    private void componentsInitialization() {
        emailView = (AutoCompleteTextView) findViewById(R.id.sign_up_email);
        usernameView = (AutoCompleteTextView) findViewById(R.id.sign_up_username);
        passwordView = (EditText) findViewById(R.id.sign_up_password);
        progressView = findViewById(R.id.sign_up_progress);
        confirmationView = findViewById(R.id.sign_up_completed);
        signUpFormView = findViewById(R.id.sign_up_form);
        backButton = (ImageButton) findViewById(R.id.sign_up_back);
        singUpButton = (Button) findViewById(R.id.sign_up_button);
    }

    private void actionListenersInitialization() {
        passwordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptSignUp();
                    return true;
                }
                return false;
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(SignUpActivity.this,InitActivity.class );
                startActivity(intent);
            }
        });
        singUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptSignUp();
            }
        });
    }

    private void attemptSignUp() {
        if (signUpTask != null) {
            return;
        }
        emailView.setError(null);
        passwordView.setError(null);
        String email = emailView.getText().toString();
        String username = usernameView.getText().toString();
        String password = passwordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            passwordView.setError(getString(R.string.error_invalid_password));
            focusView = passwordView;
            cancel = true;
        }

        if (TextUtils.isEmpty(email)) {
            emailView.setError(getString(R.string.error_field_required));
            focusView = emailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            emailView.setError(getString(R.string.error_invalid_email));
            focusView = emailView;
            cancel = true;
        }

        if (TextUtils.isEmpty(username)) {
            usernameView.setError(getString(R.string.error_field_required));
            focusView = usernameView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            showProgress(true);
            signUpTask = new UserSignUpTask(email, username, password);
            signUpTask.execute((Void) null);
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            signUpFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            signUpFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    signUpFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            progressView.setVisibility(show ? View.VISIBLE : View.GONE);
            progressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    progressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            progressView.setVisibility(show ? View.VISIBLE : View.GONE);
            signUpFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }



    public class UserSignUpTask extends AsyncTask<Void, Void, Boolean> {

        private final String Email;
        private final String Username;
        private final String Password;

        UserSignUpTask(String email, String username, String password) {
            Email = email;
            Username = username;
            Password = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt registration against a network service.

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }

            Boolean satisfy_login = false;
            User existed_user = dummy_users.findUserByEmail(Email);
            if (existed_user==null){
                dummy_users.createUser(Username,Email,Password);
                satisfy_login = true;
            }

            return satisfy_login;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            signUpTask = null;
            showProgress(false);

            if (success) {
                showConfirmation(true);
                Intent intent= new Intent(SignUpActivity.this,LogInActivity.class );
                startActivity(intent);
            } else {
                emailView.setError(getString(R.string.error_existed_user));
                emailView.requestFocus();
            }
        }

        private void showConfirmation(final boolean show) {
            confirmationView.setVisibility(show ? View.VISIBLE : View.GONE);
            signUpFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void onCancelled() {
            signUpTask = null;
            showProgress(false);
        }
    }
}
