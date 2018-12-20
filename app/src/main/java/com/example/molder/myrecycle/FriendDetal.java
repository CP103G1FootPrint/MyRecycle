package com.example.molder.myrecycle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class FriendDetal extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_view);
        show();
    }



    private void show(){

        ImageView imageView = findViewById(R.id.imageView);
        TextView tvName = findViewById(R.id.tvName);
        TextView tvPhone = findViewById(R.id.tvPhone);
        Bundle bundle = getIntent().getExtras();

        if(bundle != null){
            Friend friend = (Friend) bundle.getSerializable("friend");
            if(friend != null){
                imageView.setImageResource(friend.getImageId());
                tvName.setText(friend.getName());
                tvPhone.setText(friend.getPhone());
                setTitle(friend.getName());
            }
        }
    }
}
