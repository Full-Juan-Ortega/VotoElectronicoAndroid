package com.ispc.ispcappvoto;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import com.auth0.android.Auth0;
import com.auth0.android.authentication.AuthenticationAPIClient;
import com.auth0.android.authentication.AuthenticationException;
import com.auth0.android.callback.Callback;
import com.auth0.android.provider.WebAuthProvider;
import com.auth0.android.result.Credentials;
import com.auth0.android.result.UserProfile;


public class auth0Service {

    private static final Auth0 account = new Auth0(
            "SrOIENQmz1Tk3jijhEjpdxkq06rP9YuK",
            "dev-f72pcxe5akzine3s.us.auth0.com");

    public static void loginWithBrowser(Context context) {
        Log.i(TAG, "loginWithBrowser: ");
        WebAuthProvider.login(account)
                .withScheme("voto")
                .withScope("openid profile email")

                // Launch the authentication passing the callback where the results will be received
                .start(context, new Callback<Credentials, AuthenticationException>() {
                    // Called when there is an authentication failure
                    @Override
                    public void onFailure(AuthenticationException error) {
                        // Something went wrong!
                        Log.i(TAG, "onFailure 1: " + error.getMessage());
                    }

                    // Called when authentication completed successfully
                    @Override
                    public void onSuccess(Credentials result) {
                        // Get the access token from the credentials object.
                        // This can be used to call APIs
                        Log.i(TAG, "onSuccess: 1");
                        String accessToken = result.getAccessToken();

                        showUserProfile(accessToken);
                    }
                });
    }

    private static void showUserProfile(String accessToken) {
        AuthenticationAPIClient client = new AuthenticationAPIClient(account);

        client.userInfo(accessToken)
                .start(new Callback<UserProfile, AuthenticationException>() {
                    @Override
                    public void onFailure(AuthenticationException error) {
                        // Something went wrong!

                        Log.i(TAG, "onFailure 2: " + error.getMessage());
                    }

                    @Override
                    public void onSuccess(UserProfile result) {
                        // We have the user's profile!

                        Log.i(TAG, "onSuccess 2 : " + result.getEmail() + result.getId());
                        Log.i(TAG, "onSuccess 2 :  Login Successful! ");
                    }
                });
    }
    //agregar parametro View
    public static void logout(Context context) {

        Log.i(TAG, "logout: btn log");
        WebAuthProvider.logout(account)
                .withScheme("voto")
                .start(context, new Callback<Void, AuthenticationException>() {
                    @Override
                    public void onSuccess(Void result) {
                        // The user has been logged out!

                        Log.i(TAG, "Successfully logged out!");
                    }

                    @Override
                    public void onFailure(AuthenticationException error) {

                        Log.i(TAG, "onFailure: Couldn't Logout!");
                    }
                });
    }


    /*
    CODIGO SI QUIEREN AGREGARLO DIRECTAMENTE AL JAVA DEL XML Y NO COMO SERVICIO.
    atributo :     private Auth0 account;
    on create :         account = new Auth0(
                   "SrOIENQmz1Tk3jijhEjpdxkq06rP9YuK",
                    "dev-f72pcxe5akzine3s.us.auth0.com");

    //agregar el parametro view
    public void loginWithBrowser(View view) {
        Log.i(TAG, "loginWithBrowser: ");
        WebAuthProvider.login(account)
                .withScheme("voto")
                .withScope("openid profile email")

                // Launch the authentication passing the callback where the results will be received
                .start(this, new Callback<Credentials, AuthenticationException>() {
                    // Called when there is an authentication failure
                    @Override
                    public void onFailure(AuthenticationException error) {
                        // Something went wrong!
                        Log.i(TAG, "onFailure 1: " + error.getMessage());
                    }

                    // Called when authentication completed successfully
                    @Override
                    public void onSuccess(Credentials result) {
                        // Get the access token from the credentials object.
                        // This can be used to call APIs
                        Log.i(TAG, "onSuccess: 1");
                        String accessToken = result.getAccessToken();
                        showUserProfile(accessToken);
                    }
                });
    }

    private void showUserProfile(String accessToken) {
        AuthenticationAPIClient client = new AuthenticationAPIClient(account);

        client.userInfo(accessToken)
                .start(new Callback<UserProfile, AuthenticationException>() {
                    @Override
                    public void onFailure(AuthenticationException error) {
                        // Something went wrong!

                        Log.i(TAG, "onFailure 2: " + error.getMessage());
                    }

                    @Override
                    public void onSuccess(UserProfile result) {
                        // We have the user's profile!

                        Log.i(TAG, "onSuccess 2 : " + result.getEmail() + result.getId());
                        Log.i(TAG, "onSuccess 2 :  Login Successful! ");
                    }
                });
    }

    public void logout(View view) {
        Log.i(TAG, "logout: btn log");
        WebAuthProvider.logout(account)
                .withScheme("voto")
                .start(this, new Callback<Void, AuthenticationException>() {
                    @Override
                    public void onSuccess(Void result) {
                        // The user has been logged out!

                        Log.i(TAG, "Successfully logged out!");
                    }

                    @Override
                    public void onFailure(AuthenticationException error) {

                        Log.i(TAG, "onFailure: Couldn't Logout!");
                    }
                });
    }
*/
}