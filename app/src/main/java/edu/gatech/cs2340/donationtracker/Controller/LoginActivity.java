package edu.gatech.cs2340.donationtracker.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import edu.gatech.cs2340.donationtracker.Model.UserModel;
import edu.gatech.cs2340.donationtracker.R;

/**
 * Implementation that will initiate the LOGIN page which
 * represents a login screen that offers login via
 * email/password.
 *
 * @author Group 71B
 * @version 1.0
 */
public class LoginActivity extends AppCompatActivity {
    static final int RC_SIGN_IN = 1;

    private EditText mEmailField;
    private EditText mPasswordField;
    private TextView mErrorMessage;
    private int attemptsCounter = 3;

    private UserModel userModel;

    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mEmailField = findViewById(R.id.email_field);
        mPasswordField = findViewById(R.id.password_field);
        mErrorMessage = findViewById(R.id.error_message);

        setUserModel(UserModel.getInstance());
        configureBackButton();

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso =
                new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    /**
     * Sets the UserModel to be used by this activity
     * @param userModel an instance of UserModel
     */
    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        if (account != null) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        }
    }

    /**
     * It uses the information, user input, to check and see
     * if the information is correct and exists. If so, the
     * method changes the current screen. Otherwise, it will
     * return a message stating the invalid credentials were
     * inputted and the current user cannot log in.
     *
     * @param view the current view of the LOGIN page
     */
    public void onLoginPressed(View view) {
        String email = mEmailField.getText().toString();
        String password = mPasswordField.getText().toString();
        Button loginButton = findViewById(R.id.sign_in_button);
        mErrorMessage.setText("");

        if (userModel.validUser(email, password)) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        } else {
            mEmailField.setText("");
            mPasswordField.setText("");
            mErrorMessage.setText(R.string.loginErrorM);
            attemptsCounter--;
            if (attemptsCounter == 0) {
                loginButton.setEnabled(false);
                mErrorMessage.setText(R.string.lockout);
            }
        }
    }
    public void facebookClick(View view) {

    }

    /**
     * Performs Google sign-in
     *
     * @param view the current view of the LOGIN page
     */
    public void googleClick(View view) {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    /**
     * On successful login, this method starts up the MainActivity.
     *
     * @param completedTask the completed task to get the signed in account
     */
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            // This object contains information about the signed-in user, such as the user's name.
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully. Go to MainActivity.
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("Google Sign-In", "signInResult:failed code=" + e.getStatusCode());
        }
    }

    /**
     * It will change the page on the screen if the
     * back button is pressed.
     */
    private void configureBackButton() {
        Button backButton = findViewById(R.id.cancel_login_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.slideright, R.anim.slideleft);
            }
        });
    }
}
