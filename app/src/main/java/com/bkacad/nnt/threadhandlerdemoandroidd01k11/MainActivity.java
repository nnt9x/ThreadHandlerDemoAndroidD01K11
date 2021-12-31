package com.bkacad.nnt.threadhandlerdemoandroidd01k11;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView tvProcess;
    private ProgressBar progressBar;
    private Button btnRun;
    private Handler myHandler = null;
    private MyThread myThread;

    private void initUI(){
        tvProcess = findViewById(R.id.tv_main);
        progressBar = findViewById(R.id.pb_main);
        btnRun = findViewById(R.id.btn_main_run);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();

        // Tao handler
        myHandler = new Handler(getMainLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                // Xử lý các message được đến
                switch (msg.what){
                    case MyThread.NUMBER_FROM_THREAD:

                        tvProcess.setText(msg.obj.toString() + "%");
                        progressBar.setProgress((int)msg.obj);
                        break;
                    case MyThread.FINISH_FROM_THREAD:
                        Toast.makeText(MainActivity.this, "Hoàn thành", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };
        myThread = new MyThread(myHandler);

        // Sự kiện khi click vào button -> run luồng
        btnRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myThread.start();
            }
        });
        // 2 cách tạo luồng: extends Thread, implement Runable
    }
}