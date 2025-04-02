package com.synkin.in.app;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.synkin.in.app.utils.NetworkUtils;

public class BaseActivity extends AppCompatActivity {
    private Dialog noInternetDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupNoInternetDialog();
    }

    private void setupNoInternetDialog() {
        noInternetDialog = new Dialog(this);
        noInternetDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        noInternetDialog.setContentView(R.layout.dialog_no_internet);
        noInternetDialog.setCancelable(false);

        MaterialButton tryAgainButton = noInternetDialog.findViewById(R.id.tryAgainButton);
        MaterialButton exitButton = noInternetDialog.findViewById(R.id.exitButton);

        tryAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (NetworkUtils.isNetworkAvailable(BaseActivity.this)) {
                    noInternetDialog.dismiss();
                    onNetworkRestored();
                }
            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    protected void checkInternetConnection() {
        if (!NetworkUtils.isNetworkAvailable(this)) {
            showNoInternetDialog();
        }
    }

    protected void showNoInternetDialog() {
        if (noInternetDialog != null && !noInternetDialog.isShowing()) {
            noInternetDialog.show();
        }
    }

    protected void onNetworkRestored() {
        // Override this method in child activities to handle network restoration
    }
} 