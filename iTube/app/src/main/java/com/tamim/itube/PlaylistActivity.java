package com.tamim.itube;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class PlaylistActivity extends AppCompatActivity {
    TextView textView;
    RecyclerView recyclerView;
    VerticalAdapter adapter;
    List<PlayItem> playItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);
        textView = findViewById(R.id.textView);
        recyclerView = findViewById(R.id.recyclerView);
        setupList();
    }

    private void setupList() {
        //playItemList = db.getAllAdverts();
        adapter = new VerticalAdapter(playItemList, new OnItemClickListener() {
            @Override public void onItemClick(PlayItem playItem) {
                System.out.println("PlayItem "+playItem.getUrl()+" clicked!");
                Intent intent = new Intent(PlaylistActivity.this, PlayVideoActivity.class);
                intent.putExtra("playItem", playItem);
                startActivity(intent);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    public class VerticalAdapter extends RecyclerView.Adapter<VerticalAdapter.ViewHolder> {

        private List<PlayItem> playItemList;
        private final OnItemClickListener listener;

        public VerticalAdapter(List<PlayItem> playItemList, OnItemClickListener listener) {
            this.playItemList = playItemList;
            this.listener = listener;
            System.out.println("initialized Adapter with " + playItemList.size());
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private TextView titleTextView;

            public ViewHolder(View itemView) {
                super(itemView);
                titleTextView = itemView.findViewById(R.id.titleTextView);
            }

            public void bind(PlayItem playItem, final OnItemClickListener listener) {
                titleTextView.setText(playItem.getUrl());
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override public void onClick(View v) {
                        listener.onItemClick(playItem);
                    }
                });
            }
        }
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.playlist_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            PlayItem playItem = playItemList.get(position);
            holder.bind(playItem, listener);
        }

        @Override
        public int getItemCount() {
            if(this.playItemList == null) {
                System.out.println("getItemCount playItemList is NULL");
                return 0;
            }
            return this.playItemList.size();
        }
    }
}