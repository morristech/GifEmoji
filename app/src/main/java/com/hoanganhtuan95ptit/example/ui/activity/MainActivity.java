package com.hoanganhtuan95ptit.example.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.hoanganhtuan95ptit.example.R;
import com.hoanganhtuan95ptit.example.model.Message;
import com.hoanganhtuan95ptit.example.ui.adapter.ChatAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.list)
    RecyclerView list;
    @BindView(R.id.et_message)
    EditText etMessage;
    @BindView(R.id.img_send)
    ImageView imgSend;
    @BindView(R.id.footer)
    RelativeLayout footer;

    private ChatAdapter chatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        chatAdapter = new ChatAdapter(this);
        list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        list.setAdapter(chatAdapter);
        addMessage("Hello world (giggle)(monkey)(monkey)", Message.MessageSlide.LEFT);
        addMessage("(spiderman)(spiderman)(spiderman)", Message.MessageSlide.LEFT);
        addMessage("Hello :) :) :)", Message.MessageSlide.RIGHT);
        addMessage("(monkey)(monkey)(monkey)", Message.MessageSlide.LEFT);
    }

    @OnClick(R.id.img_send)
    public void onViewClicked() {
        final String text = etMessage.getText().toString();
        if (text.length() > 0) {
            addMessage(text, Message.MessageSlide.RIGHT);
            etMessage.setText("");
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    addMessage(text, Message.MessageSlide.LEFT);
                }
            }, 500);
        }
    }

    private void addMessage(String info, Message.MessageSlide slide) {
        chatAdapter.add(new Message(info, slide));
        list.scrollToPosition(chatAdapter.getItemCount() - 1);
    }
}
