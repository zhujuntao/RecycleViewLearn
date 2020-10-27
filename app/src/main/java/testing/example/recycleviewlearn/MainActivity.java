package testing.example.recycleviewlearn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import testing.example.recycleviewlearn.adapter.GeneralAdapter;
import testing.example.recycleviewlearn.callback.MyCallBack;
import testing.example.recycleviewlearn.decoration.AdvanceDecoration;

/*
*
* RecyclerView自身带有三种默认的布局管理器：LinearLayoutManager、GridLayoutManager、StaggeredGridLayoutManager。

LinearLayoutManager线性布局管理器
LinearLayoutManager就如同它的翻译一样，线性布局管理器。设置这个布局管理器的话，可以设置它的排列方向，通过setOrientation（）函数设置，参数可以是水平或者垂直。如果是垂直的话，那就跟ListView一模一样了。也就是前面简单使用中的例子。

LinearLayoutManager布局管理器默认使用垂直排列布局，而需要水平排列布局的话则可以通过LinearLayoutManager对象的setOrientation方法设置，此方法接收int类型的值，分别为RecyclerView中的两个Int常量：

public static final int HORIZONTAL = LinearLayout.HORIZONTAL;
public static final int VERTICAL = LinearLayout.VERTICAL;
HORIZONTAL ：代表水平排列
VERTICAL ：代表垂直排列

也可以在new出LinearLayoutManager对象的时候调用它的三个参数的构造方法设置它的布局排列方向。

public LinearLayoutManager(Context context, @RecyclerView.Orientation int orientation,
boolean reverseLayout)
第二个参数就是这个布局管理器的排列方式，依旧是传入上方两个常量值中的其中一个。第三个参数一般传false，它的用途是当为true时数据和滑动都是反向的。具体意思可以自行实验。

* */


public class MainActivity extends AppCompatActivity {
    //初始化数据
    public List<String> datas = new ArrayList<>();
    private RecyclerView recyclerView;
    private GeneralAdapter generalAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        recyclerView = findViewById(R.id.recycler_view);

        //通过setOrientation设置或者直接在构造方法中设置

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
//        recyclerView.setLayoutManager(linearLayoutManager);

        //设置LayoutManager为LinearLayoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView.addItemDecoration(new AdvanceDecoration(this, RecyclerView.HORIZONTAL));
        //添加默认的动画效果
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //设置Adapter
        generalAdapter = new GeneralAdapter(this, datas);
        recyclerView.setAdapter(generalAdapter);

        //接着在MainActivity中我们之前定制的长按事件中利用ItemTouchHelper的```startDrag(viewHolder)```实现长按拖拽滑动删除,代码如下
        final ItemTouchHelper helper=new ItemTouchHelper(new MyCallBack(generalAdapter));
        helper.attachToRecyclerView(recyclerView);

        //点击事件
        generalAdapter.setOnItemClickListener(new GeneralAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.d("MyCallBack", "====data===onItemClickmDatas:" + generalAdapter.datas.toString());
                Toast.makeText(MainActivity.this, generalAdapter.datas.get(position) + "被点击了",
                        Toast.LENGTH_SHORT).show();
            }
        });
        //长按事件
        generalAdapter.setOnItemLongClickListener(new GeneralAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(RecyclerView.ViewHolder viewHolder, View view, int position) {
                Toast.makeText(MainActivity.this, generalAdapter.datas.get(position) + "被长按了",
                        Toast.LENGTH_SHORT).show();

                if (position !=generalAdapter.datas.size()-1){
                    helper.startDrag(viewHolder);
                }
                return true;
            }

        });
        findViewById(R.id.bt_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generalAdapter.addItem("item：5", 5);
            }
        });
        findViewById(R.id.bt_del).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generalAdapter.removeItem("item：5", 5);
            }
        });





    }

    private void initData() {
        for (int i = 0; i < 5; i++) {
            datas.add("item " + i);
        }
    }
    private List<String> mList = new ArrayList<>();
    private void initList() {
        mList.add("东南分区|亚特兰大老鹰");
        mList.add("东南分区|夏洛特黄蜂");
        mList.add("东南分区|迈阿密热火");
        mList.add("东南分区|奥兰多魔术");
        mList.add("东南分区|华盛顿奇才");
        mList.add("大西洋分区|波士顿凯尔特人");
        mList.add("大西洋分区|布鲁克林篮网");
        mList.add("大西洋分区|纽约尼克斯");
        mList.add("大西洋分区|费城76人");
        mList.add("大西洋分区|多伦多猛龙");
        mList.add("中央分区|芝加哥公牛");
        mList.add("中央分区|克里夫兰骑士");
        mList.add("中央分区|底特律活塞");
        mList.add("中央分区|印第安纳步行者");
        mList.add("中央分区|密尔沃基雄鹿");
        mList.add("西南分区|达拉斯独行侠");
        mList.add("西南分区|休斯顿火箭");
        mList.add("西南分区|孟菲斯灰熊");
        mList.add("西南分区|新奥尔良鹈鹕");
        mList.add("西南分区|圣安东尼奥马刺");
        mList.add("西北分区|丹佛掘金");
        mList.add("西北分区|明尼苏达森林狼");
        mList.add("西北分区|俄克拉荷马城雷霆");
        mList.add("西北分区|波特兰开拓者");
        mList.add("西北分区|犹他爵士");
        mList.add("太平洋分区|金州勇士");
        mList.add("太平洋分区|洛杉矶快船");
        mList.add("太平洋分区|洛杉矶湖人");
        mList.add("太平洋分区|菲尼克斯太阳");
        mList.add("太平洋分区|萨克拉门托国王");
    }

    private void initListData() {
       /* for (int i = 0; i < mList.size(); i++) {
            GroupDataBean bean = new GroupDataBean();

            String s = mList.get(i);
            // area
            String area = s.substring(0, s.indexOf("|"));
            // team
            String team = s.substring(s.indexOf("|") + 1, s.length());

            bean.setArea(area);
            bean.setTeam(team);

            mDataList.add(bean);
        }

        Log.d(TAG, "initData: " + mDataList.size());*/
    }
}
