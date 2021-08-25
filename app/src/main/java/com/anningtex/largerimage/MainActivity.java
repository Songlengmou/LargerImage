package com.anningtex.largerimage;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.anningtex.largerimage.act.one.OneActivity;
import com.anningtex.largerimage.act.three.ThreeActivity;
import com.anningtex.largerimage.act.two.TwoActivity;

/**
 * @author Song
 * decs:三种图片放大缩小
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        Button mBtnOne = findViewById(R.id.btn_one);
        Button mBtnTwo = findViewById(R.id.btn_two);
        Button mBtnThree = findViewById(R.id.btn_three);
        mBtnOne.setOnClickListener(this);
        mBtnTwo.setOnClickListener(this);
        mBtnThree.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_one:
                startActivity(new Intent(this, OneActivity.class));
                break;
            case R.id.btn_two:
                startActivity(new Intent(this, TwoActivity.class));
                break;
            case R.id.btn_three:
                startActivity(new Intent(this, ThreeActivity.class));
                break;
            default:
                break;
        }
    }
}