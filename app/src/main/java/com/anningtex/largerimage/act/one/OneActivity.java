package com.anningtex.largerimage.act.one;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.anningtex.largerimage.R;
import com.anningtex.largerimage.bean.TestBean;
import com.anningtex.largerimage.weight.SPUtils;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.syp.library.BaseRecycleAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Song
 * desc:放大缩小，且左右滑动
 */
public class OneActivity extends AppCompatActivity {
    private String json = "{\"code\":1,\"msg\":\"Success\",\"data\":[{\"id\":119,\"container_id\":2139,\"master\":0,\"master_text\":\"副图\",\"path\":\"https://www.africatex.com:8666/uploads/Foreign_Container/202107/ImageSmall/36df863cfed142c011e4dcc4d0b27d6d.jpg\",\"path_medium\":\"https://www.africatex.com:8666/uploads/Foreign_Container/202107/ImageSmall/36df863cfed142c011e4dcc4d0b27d6d.jpg\",\"path_mini\":\"https://www.africatex.com:8666/uploads/Foreign_Container/202107/ImageSmall/36df863cfed142c011e4dcc4d0b27d6d.jpg\"},{\"id\":129,\"container_id\":2139,\"master\":1,\"master_text\":\"主图\",\"path\":\"https://www.africatex.com:8666/uploads/Foreign_Container/202107/ImageSmall/36df863cfed142c011e4dcc4d0b27d6d.jpg\",\"path_medium\":\"https://www.africatex.com:8666/uploads/Foreign_Container/202107/ImageSmall/36df863cfed142c011e4dcc4d0b27d6d.jpg\",\"path_mini\":\"https://www.africatex.com:8666/uploads/Foreign_Container/202107/ImageSmall/36df863cfed142c011e4dcc4d0b27d6d.jpg\"}]}";
    private final ArrayList<String> annexUrlList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
        initView();
    }

    private void initView() {
        RecyclerView mRvData = findViewById(R.id.rv_data);
        TestBean testBean = new Gson().fromJson(json, TestBean.class);
        List<TestBean.DataBean> dataBeanList = testBean.getData();
        for (TestBean.DataBean dataBean : dataBeanList) {
            annexUrlList.add(dataBean.getPath_medium());
        }
        BaseRecycleAdapter<TestBean.DataBean> adapter = new BaseRecycleAdapter<>(R.layout.item_image, dataBeanList);
        adapter.setOnDataToViewListener((helper, item, position) -> {
            TestBean.DataBean dataBean = (TestBean.DataBean) item;
            ImageView img = helper.getView(R.id.img);
            Glide.with(this).load(dataBean.getPath_mini()).into(img);
            helper.setText(R.id.tv_master, dataBean.getMaster_text());
            img.setOnClickListener(view -> {
                Intent intent = new Intent(view.getContext(), ImagePagerActivity.class);
                SPUtils.put(this, "annexUrlList1", new Gson().toJson(annexUrlList));
                intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_INDEX, position);
                intent.putExtra("type", "1");
                startActivity(intent);
            });
        });
        mRvData.setAdapter(adapter);
    }
}