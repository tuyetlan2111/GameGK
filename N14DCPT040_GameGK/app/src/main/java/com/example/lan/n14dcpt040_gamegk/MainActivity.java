package com.example.lan.n14dcpt040_gamegk;

import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lan.n14dcpt040_gamegk.data.MessageSender;
import com.example.lan.n14dcpt040_gamegk.data.Strdata;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {


    public static int SERVER_PORT = 3000;
    private String ip="192.168.1.101";
    BufferedReader bufferedReader;
    private InputStreamReader inputStreamReader;
    private Chesboard chesboard;
    private ImageView imageView;
    private Bitmap bitmap;
    private Button btnmay, btnnguoi;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnmay = findViewById(R.id.btnmay);
        btnnguoi = findViewById(R.id.btnnguoi);
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

        Thread thread = new Thread(new MyserverThread());
        thread.start();

    }

    class MyserverThread implements Runnable{

       Socket socket;
        ServerSocket serverSocket;
        InputStreamReader inputStreamReader;
        BufferedReader bufferedReader;
        @Override
        public void run() {
            try {
                serverSocket = new ServerSocket(SERVER_PORT);
                while (true){
                    socket = serverSocket.accept();
                    inputStreamReader = new InputStreamReader(socket.getInputStream());
                    bufferedReader = new BufferedReader(inputStreamReader);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void send(View view){
        MessageSender messageSender = new MessageSender();
        messageSender.execute();
    }

}
