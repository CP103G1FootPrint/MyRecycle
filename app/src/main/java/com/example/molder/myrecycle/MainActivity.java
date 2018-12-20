package com.example.molder.myrecycle;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handlerView();
        recyclerView.setAdapter(new FriendAdapter(this, getFriends()));
    }

    private class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.MyViewHolder> {
        Context context;
        List<Friend> friends;



        public FriendAdapter(Context context, List<Friend> friends) {
            this.context = context;
            this.friends = friends;
        }

        @Override
        public int getItemCount() {
            return friends.size();
        }

         class MyViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;
            TextView tvName;
            TextView tvPhone;

            public MyViewHolder(View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.imageView);
                tvName = itemView.findViewById(R.id.tvName);
                tvPhone = itemView.findViewById(R.id.tvPhone);
            }
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            View itemView = layoutInflater.inflate(R.layout.item_view, viewGroup, false);
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull final MyViewHolder viewHolder, int i) {
            final Friend friend = friends.get(i);

            viewHolder.imageView.setImageResource(friend.getImageId());
            viewHolder.tvName.setText(friend.getName());
            viewHolder.tvPhone.setText(friend.getPhone());



            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Animation animation = getShakeAnimation();
                    v.startAnimation(animation);
//                    Toast.makeText(context,friend.getName(),Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(MainActivity.this,FriendDetal.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("friend",friend);
                    /* 將Bundle儲存在Intent內方便帶至下一頁 */
                    intent.putExtras(bundle);
//                    /* 呼叫startActivity()開啟新的頁面 */
                    startActivity(intent);
                }
            });
        }

    }

    /* 建立搖晃動畫，將位移動畫重複且快速播放數次即可達到搖晃效果 */
    private TranslateAnimation getShakeAnimation() {
        TranslateAnimation shakeAnimation =
                new TranslateAnimation(0, 100, 0, 0);
        shakeAnimation.setDuration(1500);
        /* 設定CycleInterpolator特效以重複播放7次 */
        CycleInterpolator cycleInterpolator = new CycleInterpolator(50);
        shakeAnimation.setInterpolator(cycleInterpolator);
        return shakeAnimation;
    }

    private void handlerView() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private List<Friend> getFriends() {
        List<Friend> friends = new ArrayList<>();
        friends.add(new Friend(R.drawable.kon, "Lion", "0923428113"));
        friends.add(new Friend(R.drawable.ball, "Ball", "0921443815"));
        friends.add(new Friend(R.drawable.appletouch, "X", "0929222829"));
        return friends;
    }


}
