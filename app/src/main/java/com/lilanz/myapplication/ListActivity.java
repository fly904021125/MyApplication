package com.lilanz.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add(new String("第" + i + "条"));
        }
        final AnimationAdapter adapter = new SlideInBottomAnimationAdapter(new MyAdapter());
//        recyclerView.setItemAnimator(new AppearItemAnimator());
        recyclerView.setAdapter(adapter);

    }

    class MyAdapter extends RecyclerView.Adapter<Holder> {

        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.item_text, parent, false);
            Holder holder = new Holder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(Holder holder, final int position) {
            holder.tv.setText(list.get(position));
            holder.tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("MyAdapter.onClick");
                    list.remove(position);
                    notifyItemRemoved(position);
                }
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    class Holder extends RecyclerView.ViewHolder {
        TextView tv;

        public Holder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv_text);
        }
    }

}
