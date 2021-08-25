package com.anningtex.largerimage.act.three;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.anningtex.largerimage.R;
import com.anningtex.largerimage.bean.TestBean;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.syp.library.BaseRecycleAdapter;

import java.util.List;

/**
 * @author Song
 * desc:放大缩小，但不能左右滑动
 */
public class ThreeActivity extends AppCompatActivity {
    private String json = "{\"code\":1,\"msg\":\"Success\",\"data\":[{\"id\":119,\"container_id\":2139,\"master\":0,\"master_text\":\"副图\",\"path\":\"https://www.africatex.com:8666/uploads/Foreign_Container/202107/ImageSmall/36df863cfed142c011e4dcc4d0b27d6d.jpg\",\"path_medium\":\"https://www.africatex.com:8666/uploads/Foreign_Container/202107/ImageSmall/36df863cfed142c011e4dcc4d0b27d6d.jpg\",\"path_mini\":\"https://www.africatex.com:8666/uploads/Foreign_Container/202107/ImageSmall/36df863cfed142c011e4dcc4d0b27d6d.jpg\"},{\"id\":129,\"container_id\":2139,\"master\":1,\"master_text\":\"主图\",\"path\":\"https://www.africatex.com:8666/uploads/Foreign_Container/202107/ImageSmall/36df863cfed142c011e4dcc4d0b27d6d.jpg\",\"path_medium\":\"https://www.africatex.com:8666/uploads/Foreign_Container/202107/ImageSmall/36df863cfed142c011e4dcc4d0b27d6d.jpg\",\"path_mini\":\"https://www.africatex.com:8666/uploads/Foreign_Container/202107/ImageSmall/36df863cfed142c011e4dcc4d0b27d6d.jpg\"}]}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);
        initView();
    }

    private void initView() {
        RecyclerView mRvData = findViewById(R.id.rv_data);
        TestBean testBean = new Gson().fromJson(json, TestBean.class);
        List<TestBean.DataBean> dataBeanList = testBean.getData();
        BaseRecycleAdapter<TestBean.DataBean> adapter = new BaseRecycleAdapter<>(R.layout.item_image, dataBeanList);
        adapter.setOnDataToViewListener((helper, item, position) -> {
            TestBean.DataBean dataBean = (TestBean.DataBean) item;
            ImageView img = helper.getView(R.id.img);
            Glide.with(this).load(dataBean.getPath_mini()).into(img);
            helper.setText(R.id.tv_master, dataBean.getMaster_text());
            img.setOnClickListener(view -> {
                Intent intent = new Intent(view.getContext(), CropActivity.class);
                intent.putExtra("picUrl", dataBean.getPath_mini());
                startActivity(intent);
            });
        });
        mRvData.setAdapter(adapter);
    }
}