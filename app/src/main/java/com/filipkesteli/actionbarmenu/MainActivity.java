package com.filipkesteli.actionbarmenu;

import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.my_first_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_account_circle:
                Toast.makeText(MainActivity.this, R.string.my_profile, Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_shopping_cart:
                //FACTORY metoda -> odmah iskoristimo vec postojecu ugradenu sprancu za ProgressDialog objekt:
                progressDialog = ProgressDialog.show(MainActivity.this, "Loading Bank Transaction", "Please wait");

                //Netko treba ubit progresDialog, pa iskoristimo Thread:
                SeparateThread separateThread = new SeparateThread(progressDialog, false); //constructorom smo kreirali Thread ciji je hasProgress = true;
                separateThread.start(); //zapocinjemo Thread
                break;
            case R.id.action_euro:
                DialogFragment dialogFragment = new TransactionDialog();
                dialogFragment.show(getFragmentManager(), null);
                break;
        }
        return true;
    }

    public void showHide(View view){
        ActionBar actionBar = getSupportActionBar();
        if (actionBar.isShowing()) {
            actionBar.hide();
        } else {
            actionBar.show();
        }
    }
}

