package com.example.swapspot;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swapspot.adapter.MessagesAdapter;
import com.example.swapspot.model.Message;

import java.util.ArrayList;
import java.util.List;

public class MessagesActivity extends AppCompatActivity implements MessagesAdapter.OnMessageClickListener {

    private RecyclerView messagesRecyclerView;
    private MessagesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

        // Initialize RecyclerView
        messagesRecyclerView = findViewById(R.id.messagesRecyclerView);
        messagesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Create dummy data
        List<Message> messages = createDummyMessages();

        // Set up adapter
        adapter = new MessagesAdapter(messages, this);
        messagesRecyclerView.setAdapter(adapter);
    }

    private List<Message> createDummyMessages() {
        List<Message> messages = new ArrayList<>();
        
        // Using placeholder image for all profiles until we implement real image loading
        int placeholderImage = R.drawable.profile_placeholder;
        
        messages.add(new Message("Selin Uni", "ok done ...", "1 hr", placeholderImage, true));
        messages.add(new Message("Mariah Office", "I'll meet you tomorrow", "5 hr", placeholderImage, false));
        messages.add(new Message("Kubra College", "great", "5 hr", placeholderImage, false));
        messages.add(new Message("Ali Uni", "good job", "6 hr", placeholderImage, true));
        messages.add(new Message("Khaled Uni", "I booked your appointment for ...", "7 hr", placeholderImage, false));
        messages.add(new Message("Miley Kay Lee", "hahahahaha...", "12 hr", placeholderImage, false));

        return messages;
    }

    @Override
    public void onMessageClick(Message message) {
        // Launch ChatActivity with the selected user's name
        Intent intent = new Intent(this, ChatActivity.class);
        intent.putExtra("userName", message.getUserName());
        startActivity(intent);
    }
} 