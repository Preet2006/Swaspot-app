package com.example.swapspot.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.swapspot.R;
import com.example.swapspot.model.Message;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.MessageViewHolder> {

    private List<Message> messages;
    private OnMessageClickListener listener;

    public interface OnMessageClickListener {
        void onMessageClick(Message message);
    }

    public MessagesAdapter(List<Message> messages, OnMessageClickListener listener) {
        this.messages = messages;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_message, parent, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        Message message = messages.get(position);
        holder.bind(message);
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    class MessageViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView profileImage;
        private View onlineIndicator;
        private TextView userName;
        private TextView lastMessage;
        private TextView messageTime;

        MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            profileImage = itemView.findViewById(R.id.profileImage);
            onlineIndicator = itemView.findViewById(R.id.onlineIndicator);
            userName = itemView.findViewById(R.id.userName);
            lastMessage = itemView.findViewById(R.id.lastMessage);
            messageTime = itemView.findViewById(R.id.messageTime);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION && listener != null) {
                    listener.onMessageClick(messages.get(position));
                }
            });
        }

        void bind(Message message) {
            // Load profile image using Glide
            if (message.getProfileImage() > 0) {
                Glide.with(profileImage.getContext())
                    .load(message.getProfileImage())
                    .placeholder(R.drawable.profile_placeholder)
                    .error(R.drawable.profile_placeholder)
                    .into(profileImage);
            } else {
                profileImage.setImageResource(R.drawable.profile_placeholder);
            }

            onlineIndicator.setVisibility(message.isOnline() ? View.VISIBLE : View.GONE);
            userName.setText(message.getUserName());
            lastMessage.setText(message.getLastMessage());
            messageTime.setText(message.getTime());
        }
    }
} 