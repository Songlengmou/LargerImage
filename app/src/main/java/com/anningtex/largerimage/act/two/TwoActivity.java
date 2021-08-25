package com.anningtex.largerimage.act.two;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.anningtex.largerimage.R;
import com.anningtex.largerimage.bean.PictureEntity;
import com.anningtex.largerimage.bean.TestBean;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.syp.library.BaseRecycleAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Song
 * desc:放大缩小，且左右滑动
 */
public class TwoActivity extends AppCompatActivity {
    private RecyclerView mRvData;
    private String json = "{\"code\":1,\"msg\":\"Success\",\"data\":[{\"id\":119,\"container_id\":2139,\"master\":0,\"master_text\":\"副图\",\"path\":\"https://www.africatex.com:8666/uploads/Foreign_Container/202107/ImageSmall/36df863cfed142c011e4dcc4d0b27d6d.jpg\",\"path_medium\":\"https://www.africatex.com:8666/uploads/Foreign_Container/202107/ImageSmall/36df863cfed142c011e4dcc4d0b27d6d.jpg\",\"path_mini\":\"https://www.africatex.com:8666/uploads/Foreign_Container/202107/ImageSmall/36df863cfed142c011e4dcc4d0b27d6d.jpg\"},{\"id\":129,\"container_id\":2139,\"master\":1,\"master_text\":\"主图\",\"path\":\"https://www.africatex.com:8666/uploads/Foreign_Container/202107/ImageSmall/36df863cfed142c011e4dcc4d0b27d6d.jpg\",\"path_medium\":\"https://www.africatex.com:8666/uploads/Foreign_Container/202107/ImageSmall/36df863cfed142c011e4dcc4d0b27d6d.jpg\",\"path_mini\":\"https://www.africatex.com:8666/uploads/Foreign_Container/202107/ImageSmall/36df863cfed142c011e4dcc4d0b27d6d.jpg\"}]}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        initView();
    }

    private void initView() {
        mRvData = findViewById(R.id.rv_data);
        TestBean testBean = new Gson().fromJson(json, TestBean.class);
        List<TestBean.DataBean> dataBeanList = testBean.getData();
        BaseRecycleAdapter<TestBean.DataBean> adapter = new BaseRecycleAdapter<>(R.layout.item_image, dataBeanList);
        adapter.setOnDataToViewListener((helper, item, position) -> {
            TestBean.DataBean dataBean = (TestBean.DataBean) item;
            ImageView img = helper.getView(R.id.img);
            Glide.with(this).load(dataBean.getPath_mini()).into(img);
            helper.setText(R.id.tv_master, dataBean.getMaster_text());
        });
        adapter.setOnItemClickListener((adapter1, view, position1) -> {
            List<TestBean.DataBean> dataBeans = (List<TestBean.DataBean>) adapter1.getData();
            List<PictureEntity> pictureEntities = new ArrayList<>();
            for (TestBean.DataBean bean : dataBeans) {
                PictureEntity pictureEntity = new PictureEntity();
                pictureEntity.setImgOriginUrl(bean.getPath());
                pictureEntity.setImgMiddleUrl(bean.getPath_medium());
                pictureEntity.setName(bean.getMaster_text());
                pictureEntities.add(pictureEntity);
            }
            Intent intent = new Intent(this, LargerImageActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("imgList", (Serializable) pictureEntities);
            bundle.putInt("index", position1);
            //判断图片是否需要拼接(true和false)
            bundle.putBoolean("isHasTitle", false);
            intent.putExtras(bundle);
            startActivity(intent);
        });
        mRvData.setAdapter(adapter);
    }
}