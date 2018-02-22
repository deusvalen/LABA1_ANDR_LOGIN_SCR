package by.bstu.fit.lyolia.laba1_andr_login_scr;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button loginButton;
    EditText loginText;
    EditText passwordText;
    TextView errorText;

    private int attempts;
    private final String LOGIN = "admin";
    private final String PASSWORD = "admin";
    private boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton = (Button) findViewById(R.id.loginButton);
        loginText = (EditText) findViewById(R.id.etLogin);
        passwordText = (EditText) findViewById(R.id.etPassword);
        errorText = (TextView) findViewById(R.id.errorTextView);

        passwordText.setTransformationMethod(new AsteriskPasswordTransformationMethod());

        attempts = 3;
        flag = true;
    }

    @SuppressLint("SetTextI18n")
    public void Click(View view) {
        if(loginText.getText().toString().equals(LOGIN) &&
                passwordText.getText().toString().equals(PASSWORD)) {
            Toast.makeText(getApplicationContext(), "Welcome", Toast.LENGTH_SHORT).show();
        } else {

            if(editDist(PASSWORD, passwordText.getText().toString()) == 1 && flag) {
                attempts = 5;
                flag = false;
            }

            errorText.setText("Wrong password\n" + attempts + " attempts left");
            attempts--;
            if(attempts < 0) {
                loginButton.setEnabled(false);
                errorText.setText("Button is blocked now");
                loginButton.setBackgroundColor(Color.RED);
                loginButton.setTextColor(Color.WHITE);
                this.finishAffinity();
            }
        }
    }

    int editDist(String S1, String S2) {
        int m = S1.length(), n = S2.length();
        int[] D1;
        int[] D2 = new int[n + 1];

        for (int i = 0; i <= n; i++)
            D2[i] = i;

        for (int i = 1; i <= m; i++) {
            D1 = D2;
            D2 = new int[n + 1];
            for (int j = 0; j <= n; j++) {
                if (j == 0) D2[j] = i;
                else {
                    int cost = (S1.charAt(i - 1) != S2.charAt(j - 1)) ? 1 : 0;
                    if (D2[j - 1] < D1[j] && D2[j - 1] < D1[j - 1] + cost)
                        D2[j] = D2[j - 1] + 1;
                    else if (D1[j] < D1[j - 1] + cost)
                        D2[j] = D1[j] + 1;
                    else
                        D2[j] = D1[j - 1] + cost;
                }
            }
        }
        return D2[n];
    }

}

