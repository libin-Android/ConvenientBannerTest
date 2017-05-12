package com.example.administrator.convenientbannertest;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ToxicBakery.viewpager.transforms.AccordionTransformer;
import com.ToxicBakery.viewpager.transforms.BackgroundToForegroundTransformer;
import com.ToxicBakery.viewpager.transforms.CubeInTransformer;
import com.ToxicBakery.viewpager.transforms.CubeOutTransformer;
import com.ToxicBakery.viewpager.transforms.DefaultTransformer;
import com.ToxicBakery.viewpager.transforms.DepthPageTransformer;
import com.ToxicBakery.viewpager.transforms.FlipHorizontalTransformer;
import com.ToxicBakery.viewpager.transforms.FlipVerticalTransformer;
import com.ToxicBakery.viewpager.transforms.ForegroundToBackgroundTransformer;
import com.ToxicBakery.viewpager.transforms.RotateDownTransformer;
import com.ToxicBakery.viewpager.transforms.RotateUpTransformer;
import com.ToxicBakery.viewpager.transforms.StackTransformer;
import com.ToxicBakery.viewpager.transforms.ZoomInTransformer;
import com.ToxicBakery.viewpager.transforms.ZoomOutTranformer;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Transformer;

import views.NetworkImageHolderView;

public class MainActivity extends AppCompatActivity {
    private List<String> networkImages=new ArrayList<>();//存放图片的list
    private ArrayList<String> transformerList = new ArrayList<String>();//图片加载样式的list
    private ConvenientBanner convenientBannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initImageLoader();
        initData();
        initView();
    }

    private void initImageLoader() {
        //网络图片例子,结合常用的图片缓存库UIL,你可以根据自己需求自己换其他网络图片库
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder().
                showImageForEmptyUri(R.mipmap.ic_launcher)
                .cacheInMemory(true).cacheOnDisk(true).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                getApplicationContext()).defaultDisplayImageOptions(defaultOptions)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO).build();
        ImageLoader.getInstance().init(config);
    }

    private void initData() {
        networkImages.add("http://img04.tooopen.com/images/20130712/tooopen_17270713.jpg");
        networkImages.add("http://img05.tooopen.com/images/20150202/sy_80219211654.jpg");
        networkImages.add("http://img04.tooopen.com/images/20130712/tooopen_17270713.jpg");
        networkImages.add("http://img.ivsky.com/img/tupian/li/201607/31/wulanmaodu_caoyuan-001.jpg");
        networkImages.add("http://img.ivsky.com/img/tupian/li/201607/28/xiyang.jpg");
        networkImages.add("http://img.ivsky.com/img/tupian/li/201607/21/yunhai_fengjing.jpg");
        networkImages.add("http://img.ivsky.com/img/tupian/li/201607/06/bolang_henji-007.jpg");
        networkImages.add("http://img.ivsky.com/img/tupian/li/201606/30/ningjing_de_haiwan.jpg");

//        //各种翻页效果
        transformerList.add(DefaultTransformer.class.getSimpleName());
        transformerList.add(AccordionTransformer.class.getSimpleName());//3D立体效果
        transformerList.add(BackgroundToForegroundTransformer.class.getSimpleName());//侧滑换页
        transformerList.add(CubeInTransformer.class.getSimpleName());
        transformerList.add(CubeOutTransformer.class.getSimpleName());
        transformerList.add(DepthPageTransformer.class.getSimpleName());//侧滑换页
        transformerList.add(FlipHorizontalTransformer.class.getSimpleName());//左右翻转的效果
        transformerList.add(FlipVerticalTransformer.class.getSimpleName());//上下翻转的效果
        transformerList.add(ForegroundToBackgroundTransformer.class.getSimpleName());//侧面翻转的效果
        transformerList.add(RotateDownTransformer.class.getSimpleName());
        transformerList.add(RotateUpTransformer.class.getSimpleName());//旋转效果
        transformerList.add(StackTransformer.class.getSimpleName());
        transformerList.add(ZoomInTransformer.class.getSimpleName());
        transformerList.add(ZoomOutTranformer.class.getSimpleName());//缩放效果
    }

    private void initView() {
        convenientBannerView = (ConvenientBanner) findViewById(R.id.convenientBannerView);
        convenientBannerView.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
            @Override
            public NetworkImageHolderView createHolder() {

                return new NetworkImageHolderView();
            }
        },networkImages)
                .setPageIndicator(new int[]{R.mipmap.common_tips_selected_icon, R.mipmap.common_tips_unselect_icon})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT);   //设置指示器圆点
    }

    @Override
    protected void onResume() {
        super.onResume();
        //开始自动翻页
        convenientBannerView.startTurning(5000);
        convenientBannerView.getViewPager().setPageTransformer(true,new AccordionTransformer());
    }

    @Override
    protected void onPause() {
        super.onPause();
        //停止翻页
        convenientBannerView.stopTurning();
    }
}
