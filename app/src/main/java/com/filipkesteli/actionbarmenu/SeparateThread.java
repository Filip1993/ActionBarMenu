package com.filipkesteli.actionbarmenu;

import android.app.ProgressDialog;

/**
 * Created by Filip on 17.5.2016..
 */
public class SeparateThread extends Thread {

    private ProgressDialog progressDialog;
    private boolean hasProgress;

    public SeparateThread(ProgressDialog progressDialog, boolean hasProgress) {
        this.progressDialog = progressDialog;
        this.hasProgress = hasProgress;
    }

    @Override
    public void run() {
        if (hasProgress) {
            sleepWithProgress();
        } else {
            sleepWithNoProgress();
        }

        progressDialog.dismiss();
    }

    private void sleepWithProgress() {
        while (progressDialog.getProgress() < 100) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
            progressDialog.incrementProgressBy(10); //10 posto
        }
    }

    private void sleepWithNoProgress() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
    }
}
