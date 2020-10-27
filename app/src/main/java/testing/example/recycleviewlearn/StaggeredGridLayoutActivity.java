package testing.example.recycleviewlearn;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

import testing.example.recycleviewlearn.adapter.GeneralAdapter;
import testing.example.recycleviewlearn.adapter.StaggeredApapter;

//https://www.jianshu.com/p/5ad99a1170ab

public class StaggeredGridLayoutActivity extends AppCompatActivity {
    //初始化数据
    public List<String> datas = new ArrayList<>();
    private RecyclerView recyclerView;
    private StaggeredApapter staggeredApapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        recyclerView = findViewById(R.id.recycler_view);

        //通过setOrientation设置或者直接在构造方法中设置

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, RecyclerView.VERTICAL);

//        recyclerView.setLayoutManager(staggeredGridLayoutManager);

        /*通过设置StaggeredGridLayoutManager则可以实现炫酷的瀑布流效果。它最常用的构造函数就一个，
        StaggeredGridLayoutManager(int spanCount, int orientation)，
        spanCount代表每行或每列的Item个数，orientation代表列表的方向，竖直或者水平。
        要实现瀑布流效果（仅讨论竖直方向的瀑布流样式），每一个Item的高度要有所差别，
        如果所有的item的高度相同，就和网格样式是一样的展示效果。示例中就实现两中不同高度的Item，
        一个高度为100dp,一个高度为200dp。
         */

        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, RecyclerView.VERTICAL));
        //设置Adapter
        staggeredApapter = new StaggeredApapter(this, datas);
        recyclerView.setAdapter(staggeredApapter);
        //点击事件
        staggeredApapter.setOnItemClickListener(new StaggeredApapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(StaggeredGridLayoutActivity.this, datas.get(position) + "被点击了",
                        Toast.LENGTH_SHORT).show();
            }
        });
        //长按事件
        staggeredApapter.setOnItemLongClickListener(new StaggeredApapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(View view, int position) {
                Toast.makeText(StaggeredGridLayoutActivity.this, datas.get(position) + "被长按了",
                        Toast.LENGTH_SHORT).show();
                return true;
            }
        });

    }

    private void initData() {
        for (int i = 0; i < 20; i++) {
            datas.add("item " + i);
        }
    }
}
