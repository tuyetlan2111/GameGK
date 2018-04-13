package com.example.lan.n14dcpt040_gamegk;

import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.lan.n14dcpt040_gamegk.data.Strdata;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {

    private Chesboard chesboard;
    private ImageView imageView;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.img);
        chesboard = new Chesboard(MainActivity.this, 800,800,8,8);
        chesboard.init();
        bitmap = chesboard.drawBoard();
        imageView.setImageBitmap(bitmap);
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return chesboard.onTouch(view,motionEvent);
            }
        });
    }

//    private class ThreadBackgound extends Thread {
//        private Boolean isStopAfterExe = false;
//        private String strData = null;
//        private Boolean isRunBg = true;
//        private Socket soc;
//
//        public void mStop() {
//            this.isRunBg = false;
//            try {
//                this.soc.close();
//            } catch (Exception e) {
//            }
//        }
//
//        public void setData(String str) {
//            this.strData = str;
//        }
//
//        public void run() {
//            try {
//                soc = new Socket(Strdata.strHost, Strdata.intPort);
//                PrintWriter pwOut = new PrintWriter(soc.getOutputStream(), true);
//                InputStreamReader inStream = new InputStreamReader(
//                        soc.getInputStream());
//                BufferedReader buff = new BufferedReader(inStream);
//                mThreadReceive.create(buff);
//                mThreadReceive.start();
//                while (isRunBg) {
//                    sleep(200);
//                    if (strData != null) {
//                        pwOut.println(strData);
//                        strData = null;
//                        if (isStopAfterExe)
//                            this.isRunBg = false;
//                    }
//                }
//            } catch (Exception exx) {
//                Log.d("thread bg error", exx.toString());
//            }
//        }
//    }
//public class ThreadReceive extends Thread {
//    private BufferedReader buff;
//    private Boolean isRunBg = true;
//
//    public void mStop() {
//        this.isRunBg = false;
//    }
//
//    public void create(BufferedReader buff) {
//        this.buff = buff;
//    }
//
//    public void run() {
//        try {
//            String valReceive = null;
//            while (isRunBg) {
//                valReceive = buff.readLine();
//                if (valReceive != null) {
//                    // Log.d("result receive",valReceive);
//                    FunProcResult(valReceive);
//                }
//                if (valReceive == null && isConnect) {
//                    FunConnectError();
//                    this.isRunBg = false;
//                    mThreadBg.mStop();
//                }
//            }
//        } catch (Exception ex) {
//            Log.d("thread receive error", ex.toString());
//        }
//    }
//
//    public void FunProcResult(String valReceive) {
//        BufferedReader buff;
//        Boolean isRunBg = true;
//
//        public void mStop(){
//            this.isRunBg = false;
//        }
//
//        public void create(BufferedReader buff) {
//            this.buff = buff;
//        }
//
//        public void run(){
//            try {
//                String valReceive = null;
//                while (isRunBg) {
//                    valReceive = buff.readLine();
//                    if (valReceive != null) {
//                        // Log.d("result receive",valReceive);
//                        FunProcResult(valReceive);
//                    }
//                    if (valReceive == null && isConnect) {
//                        FunConnectError();
//                        this.isRunBg = false;
//                        mThreadBg.mStop();
//                    }
//                }
//            } catch (Exception ex) {
//                Log.d("thread receive error", ex.toString());
//            }
//        }
//    }
//
//    private void FunConnectError() {
//    }
}
