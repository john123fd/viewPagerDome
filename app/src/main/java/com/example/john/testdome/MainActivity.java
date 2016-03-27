package com.example.john.testdome;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;  //对应的viewPager

    private List<View> viewList;//view数组
    private LinearLayout viewpager_partent;
    private TextView main_text;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewList = new ArrayList<View>();
        viewPager = (ViewPager)findViewById(R.id.view_pager);
        viewpager_partent =(LinearLayout)findViewById(R.id.viewpager_partent);
        main_text = (TextView)findViewById(R.id.main_text);
        btn = (Button)findViewById(R.id.btn);

        viewPager.setOffscreenPageLimit(5);

        int[] images = {R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.img5};
        LayoutInflater inflater=getLayoutInflater();
        for (int i = 0; i < 5; i++) {
            View  view = inflater.inflate(R.layout.viewpager, null);
            ImageView imageView = (ImageView)view.findViewById(R.id.image);
            TextView textView = (TextView)view.findViewById(R.id.text);
            imageView.setImageResource(images[i]);
            textView.setText("Text; " + i);
            viewList.add(view);
        }

        PagerAdapter my = new PagerAdapter() {
            @Override
            public int getCount() {
                return viewList.size();
            }

            // 来判断显示的是否是同一张图片，这里我们将两个参数相比较返回即可
            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                return arg0 == arg1;
            }

            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                // TODO Auto-generated method stub
                container.removeView(viewList.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                // TODO Auto-generated method stub
                container.addView(viewList.get(position));
                return viewList.get(position);
            }
        };

        viewPager.setAdapter(my);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (viewpager_partent != null) {
                    viewpager_partent.invalidate();
                }
            }

            @Override
            public void onPageSelected(int position) {
                main_text.setText("页面： " + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        viewpager_partent.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // dispatch the events to the ViewPager, to solve the problem that we can swipe only the middle view.
                return viewPager.dispatchTouchEvent(event);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Main2Activity.class));
            }
        });
    }
}
