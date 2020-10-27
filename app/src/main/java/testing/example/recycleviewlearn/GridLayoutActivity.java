package testing.example.recycleviewlearn;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import testing.example.recycleviewlearn.adapter.GeneralAdapter;

//https://www.jianshu.com/p/5ad99a1170ab

public class GridLayoutActivity extends AppCompatActivity {
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

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);

        gridLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
//        recyclerView.setLayoutManager(gridLayoutManager);

        //GridLayoutManager网格布局管理器
        //网格布局管理器，顾名思义Item已网格方式排列。
        //跟线性布局管理器一样，也支持通过setOrientation（）或者构造方法来设置Item的排列方向。
        //这里使用了GridLayoutManager两个参数的构造方法，
        // 第二个参数Item有几行或者几列。之所以这么说，是因为当水平排列时，就是有几行。
        // 垂直排列时就是有几列。当不设置排列方式时，默认使用垂直排列。
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        //设置Adapter
        generalAdapter = new GeneralAdapter(this, datas);
        recyclerView.setAdapter(generalAdapter);
        //点击事件
        generalAdapter.setOnItemClickListener(new GeneralAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(GridLayoutActivity.this, datas.get(position) + "被点击了",
                        Toast.LENGTH_SHORT).show();
            }
        });
        //长按事件
        generalAdapter.setOnItemLongClickListener(new GeneralAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(RecyclerView.ViewHolder viewHolder, View view, int position) {
                Toast.makeText(GridLayoutActivity.this, datas.get(position) + "被长按了",
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
