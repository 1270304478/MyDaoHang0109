package che.bwei.com.mydaohang0109.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import che.bwei.com.mydaohang0109.R;
import che.bwei.com.mydaohang0109.bannerGilde.GlideImageLoader;
import che.bwei.com.mydaohang0109.bean.ShouYeLunBoBean;
import che.bwei.com.mydaohang0109.bean.ShouyeGridBean;
import che.bwei.com.mydaohang0109.fragment.Fragment_vp_1;
import che.bwei.com.mydaohang0109.fragment.Fragment_vp_2;

/**
 * 此类的作用：
 *
 * @author: forever
 * @date: 2018/1/9 17:15
 */
public class ShouyeRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //定义几种不同的类型
    int ONE = 0;//lunbo banner
    int TWO = 1;//一张图片
    int THREE = 2;//gridview
    int FOUR = 3;//一张图片
    int FIVE = 4;//跑马灯
    int SIX = 5;//京东秒杀 倒计时
    int SEVEN = 6;//水平滑动的秒杀
    int EIGHT = 7;//猜你喜欢 一张图片
    int NINE = 8;//为你推荐

    static long mHour = 02;
    static long mMin =15;
    boolean isRun = true;
    static long mSecond = 36;
    List<ShouYeLunBoBean.MiaoshaBean> listMiaosha;//轮播图的接口
    List<ShouYeLunBoBean.TuijianBean> listTuijian;//轮播图的接口
    List<ShouYeLunBoBean.DataBean> listIMageDa;//轮播图的接口
    List<ShouyeGridBean.DataBean> listGridDa;//grid九宫格
    Context context;
    private List<String> listImagelunbo;
    private ViewHolder1 viewHolder1;
    private ViewHolder2 viewHolder2;
    private ViewHolder3 viewHolder3;
    private ViewHolder4 viewHolder4;
    private ViewHolder5 viewHolder5;
    private ViewHolder6 viewHolder6;
    private ViewHolder7 viewHolder7;
    private ViewHolder8 viewHolder8;
    private ViewHolder9 viewHolder9;

    FragmentManager manager;
    private List<ImageView> listshape;
    private static Handler timeHandler;


    public ShouyeRecyclerViewAdapter(Context context, FragmentManager manager) {
        this.context = context;
        this.manager = manager;
    }

    //轮播图的集合
    public void addLunbo(List<ShouYeLunBoBean.DataBean> listIMage) {
        if (listIMageDa == null) {
            listIMageDa = new ArrayList<>();
        }
        listIMageDa.addAll(listIMage);

        //装轮播图的集合
        listImagelunbo = new ArrayList<>();
        for (int i = 0; i < listIMageDa.size(); i++) {
            //将所有的图片都添加到集合里
            listImagelunbo.add(listIMageDa.get(i).getIcon());
        }
        notifyDataSetChanged();
    }

    //推荐图片的的集合
    public void addTuijian(ShouYeLunBoBean.TuijianBean tuijian) {
        if(listTuijian==null){
            listTuijian = new ArrayList<>();
        }
        listTuijian.add(tuijian);
        notifyDataSetChanged();
    }

    //秒杀图片的的集合
    public void addMiaosha(ShouYeLunBoBean.MiaoshaBean miaosha) {
        if(listMiaosha==null){
            listMiaosha = new ArrayList<>();
        }
        listMiaosha.add(miaosha);
        notifyDataSetChanged();
    }

    //grid九宫格的集合
    public void addGrid(List<ShouyeGridBean.DataBean> listGrid) {
        if (listGridDa == null) {
            listGridDa = new ArrayList<>();
        }
        listGridDa.addAll(listGrid);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ONE) {//lunbo
            //如果类型是0
            View view = View.inflate(context, R.layout.shouye_recy_lunbo, null);
            viewHolder1 = new ViewHolder1(view);
            return viewHolder1;
        } else if (viewType == TWO) {//图片
            View view = View.inflate(context, R.layout.shouye_recy_image01, null);
            viewHolder2 = new ViewHolder2(view);
            return viewHolder2;
        } else if (viewType == THREE) {//gridview
            //如果类型是1
            View view = View.inflate(context, R.layout.shouye_recy_02, null);
            viewHolder3 = new ViewHolder3(view);
            return viewHolder3;
        } else if (viewType == FOUR) {//viewpager
            View view = View.inflate(context, R.layout.shouye_recy_image02, null);
            viewHolder4 = new ViewHolder4(view);
            return viewHolder4;
        } else if (viewType == FIVE) {//图片
            View view = View.inflate(context, R.layout.shouye_recy_paomadeng, null);
            viewHolder5 = new ViewHolder5(view);
            return viewHolder5;
        }else if(viewType==SIX){
            View view = View.inflate(context, R.layout.shouye_recy_daojishi, null);
            viewHolder6 = new ViewHolder6(view);
            return viewHolder6;
        }else if(viewType==SEVEN){
            View view = View.inflate(context, R.layout.shouye_recy_miaosha, null);
            viewHolder7 = new ViewHolder7(view);
            return viewHolder7;
        }else if(viewType==EIGHT){
            View view = View.inflate(context, R.layout.shouye_cainixihuan, null);
            viewHolder8 = new ViewHolder8(view);
            return viewHolder8;
        }else if(viewType==NINE){
            View view = View.inflate(context, R.layout.shouye_recy_tuijian, null);
            viewHolder9 = new ViewHolder9(view);
            return viewHolder9;
        }
        return viewHolder1;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder1) {//轮播图
            ViewHolder1 viewHolder1 = (ViewHolder1) holder;
            //设置banner的图片加载器
            viewHolder1.banner.setImageLoader(new GlideImageLoader());
            //设置图片的集合
            if (listImagelunbo != null) {
                viewHolder1.banner.setImages(listImagelunbo);
                viewHolder1.banner.start();
            }

        } else if (holder instanceof ViewHolder2) {//一张图片
                  /*  ViewHolder2 viewHolder2 = (ViewHolder2) holder;
                     Uri uri= Uri.parse("res://com.example.jdong/"+R.drawable.recy_image01);
                     viewHolder2.simpleDraweeView.setImageURI(uri);*/

        } else if (holder instanceof ViewHolder3) {//gridview
            ViewHolder3 viewHolder3 = (ViewHolder3) holder;

            final List<Fragment> list = new ArrayList<>();
            list.add(new Fragment_vp_1());
            list.add(new Fragment_vp_2());


            viewHolder3.viewPager.setAdapter(new FragmentPagerAdapter(manager) {
                @Override
                public Fragment getItem(int position) {
                    return list.get(position);
                }

                @Override
                public int getCount() {
                    return list.size();
                }
            });
            initShape();
            //页面改变的时候 监听
            viewHolder3.viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    for (int i=0;i<listshape.size();i++){
                        if(i==position){
                            listshape.get(i).setImageResource(R.drawable.shape02);
                        }else{
                            listshape.get(i).setImageResource(R.drawable.shape01);
                        }
                    }
                }

                @Override
                public void onPageSelected(int position) {

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });

        } else if (holder instanceof ViewHolder4) {//一张图片


        } else if (holder instanceof ViewHolder5) {//走马灯
            ViewHolder5 viewHolder5 = (ViewHolder5) holder;

            //给跑马灯填充布局
            viewHolder5.viewFlipper.addView(View.inflate(context, R.layout.paomadeng_item, null));
        }else if (holder instanceof ViewHolder6) {//秒杀倒计时
            startRun();
        }else if (holder instanceof ViewHolder7) {//秒杀左滑
            ViewHolder7 viewHolder7 = (ViewHolder7) holder;

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);

            viewHolder7.shouye_recy_miaosha.setLayoutManager(linearLayoutManager);
            RecyMiaoshaAdapter miaoshaAdapter = new RecyMiaoshaAdapter(context);
            miaoshaAdapter.addData(listMiaosha);
            viewHolder7.shouye_recy_miaosha.setAdapter(miaoshaAdapter);
            viewHolder7.shouye_recy_miaosha.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "cik", Toast.LENGTH_SHORT).show();
                }
            });
        }else if (holder instanceof ViewHolder8) {//一张图片


        }else if (holder instanceof ViewHolder9) {//为你推荐
            ViewHolder9 viewHolder9 = (ViewHolder9) holder;

            GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2);

            viewHolder9.shouye_recy_tuijian.setLayoutManager(gridLayoutManager);
            RecyTuijianAdapter tuijianAdapter = new RecyTuijianAdapter(context);
            tuijianAdapter.addData(listTuijian);
            viewHolder9.shouye_recy_tuijian.setAdapter(tuijianAdapter);

        }

    }

    @Override
    public int getItemCount() {
        return 9;//几种类型
    }

    //返回条目的类型

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            //返回第一种类型
            return ONE;//0轮播
        } else if (position == 1) {
            return TWO;//1图片
        } else if (position == 2) {
            return THREE;//2九宫格
        } else if (position == 3) {
            return FOUR;//图片
        } else if (position == 4) {
            return FIVE;//图片
        }else if(position==5){
            return SIX;
        }else if(position==6){
            return SEVEN;
        }else if(position==7){
            return EIGHT;
        }else if(position==8){
            return NINE;
        }

        return FIVE;
    }

    /**
     * 开启倒计时
     * */
    private void startRun(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(isRun){
                    try {
                        //睡眠一秒发送消息handler
                        Thread.sleep(1000);
                        Message message = Message.obtain();
                        message.what=1;
                        //发送消息
                        timeHandler.sendMessage(message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }



    //轮播图的布局
    public static class ViewHolder1 extends RecyclerView.ViewHolder {

        private final Banner banner;

        public ViewHolder1(View itemView) {
            super(itemView);
            banner = (Banner) itemView.findViewById(R.id.banner);
        }
    }

    //一张图片的布局
    public static class ViewHolder2 extends RecyclerView.ViewHolder {

        //  private final SimpleDraweeView simpleDraweeView;

        public ViewHolder2(View itemView) {
            super(itemView);

            //  simpleDraweeView = (SimpleDraweeView) itemView.findViewById(R.id.recy_simpledrawee_01);

        }
    }


    //grid的布局
    public static class ViewHolder3 extends RecyclerView.ViewHolder {

        private final ViewPager viewPager;
        private final LinearLayout linearLayout;

        // private final RecyclerView recy_02_view;
        public ViewHolder3(View itemView) {
            super(itemView);
            // recy_02_view = (RecyclerView) itemView.findViewById(R.id.recy_02_view);
            viewPager = (ViewPager) itemView.findViewById(R.id.view_pager);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linear_layout);
        }
    }

    //一张图片的布局
    public static class ViewHolder4 extends RecyclerView.ViewHolder {


        public ViewHolder4(View itemView) {
            super(itemView);


        }
    }

    //跑马灯的布局
    public static class ViewHolder5 extends RecyclerView.ViewHolder {

        private final ViewFlipper viewFlipper;

        public ViewHolder5(View itemView) {
            super(itemView);
            viewFlipper = (ViewFlipper) itemView.findViewById(R.id.view_filpper);

        }
    }

    //秒杀倒计时的布局
    public static class ViewHolder6 extends RecyclerView.ViewHolder {

        //控制倒计时的显示

        TextView tvHour;//倒计时的小时
        TextView tvMinute;//分钟
        TextView tvSecond;//秒
        public ViewHolder6(View itemView) {
            super(itemView);
            tvHour = (TextView) itemView.findViewById(R.id.tv_hour);
            tvMinute = (TextView) itemView.findViewById(R.id.tv_minute);
            tvSecond = (TextView) itemView.findViewById(R.id.tv_second);


            //调用 倒计时计算的方法
            timeHandler = new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    if(msg.what==1){
                        //调用 倒计时计算的方法
                        computeTime();
                        if(mHour<10){
                            tvHour.setText("0"+mHour+"");
                        }else{
                            tvHour.setText(mHour+"");
                        }
                        if(mMin<10){
                            tvMinute.setText("0"+mMin+"");
                        }else{
                            tvMinute.setText(mMin+"");
                        }if(mSecond<10){
                            tvSecond.setText("0"+mSecond+"");
                        }else{
                            tvSecond.setText(mSecond+"");
                        }
                    }
                }
            };


        }

    }

    /**
     * 倒计时计算
     * */
    private static void computeTime(){
        //首先把秒减1
        mSecond--;
        if(mSecond<0){//如果秒已经减到了0
            mMin--;//分钟就减1
            mSecond=59;//秒变成 59
            if(mMin<0){//如果分钟小于0
                mMin=59;//分钟变成59
                mHour--;//小时减1
            }
        }
    }
    //秒杀的布局
    public static class ViewHolder7 extends RecyclerView.ViewHolder {


        private final RecyclerView shouye_recy_miaosha;

        public ViewHolder7(View itemView) {
            super(itemView);
            shouye_recy_miaosha = (RecyclerView) itemView.findViewById(R.id.shouye_recy_miaosha);

        }
    }
    //猜你喜欢的图片
    public static class ViewHolder8 extends RecyclerView.ViewHolder {

        public ViewHolder8(View itemView) {
            super(itemView);

        }
    }
    //为你推荐的
    public static class ViewHolder9 extends RecyclerView.ViewHolder {

        private final RecyclerView shouye_recy_tuijian;

        public ViewHolder9(View itemView) {
            super(itemView);

            shouye_recy_tuijian = (RecyclerView) itemView.findViewById(R.id.shouye_recy_tuijian);
        }
    }
    //初始化小圆点的方法
    private void initShape(){
        //创建装着小圆点的集合
        listshape = new ArrayList<>();

        //清空布局和集合
        viewHolder3.linearLayout.removeAllViews();
        listshape.clear();

        for (int i=0;i<2;i++){
            ImageView imageView = new ImageView(context);
            if(i==0){
                //如果当前是第一页,就设置选中图片
                imageView.setImageResource(R.drawable.shape02);
            }else{
                imageView.setImageResource(R.drawable.shape01);
            }
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(5,0,5,0);
            //添加到集合 ,布局里
            listshape.add(imageView);
            viewHolder3.linearLayout.addView(imageView,layoutParams);
        }
    }
}
