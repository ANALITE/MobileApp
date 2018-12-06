package analite.mobileapp.data.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import analite.mobileapp.R;

public class InitActivity extends AppCompatActivity {

    private Button buttonSignIn;
    private Button buttonSignUp;
    //private SimpleUserRepository user_repo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);
        //user_repo = new SimpleUserRepository();
        componentsInitialization();
        actionListenersInitialization();
    }

    void componentsInitialization() {
        buttonSignUp = (Button) findViewById((R.id.sign_init_button));
        buttonSignIn = (Button) findViewById(R.id.login_init_button);
    }

    void actionListenersInitialization() {


        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(InitActivity.this,LogInActivity.class );
                //intent.putExtra("UserRepo",user_repo);
                startActivity(intent);
            }
        });

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(InitActivity.this,SignUpActivity.class );
                //intent.putExtra("UserRepo",user_repo);
                startActivity(intent);
            }
        });
    }
}
