package testing.example.recycleviewlearn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import testing.example.recycleviewlearn.adapter.GeneralAdapter;
import testing.example.recycleviewlearn.adapter.SwipeAdapter;
import testing.example.recycleviewlearn.bean.Student;
import testing.example.recycleviewlearn.callback.MyCallBack;
import testing.example.recycleviewlearn.callback.SwipeItemTouchHelper;
import testing.example.recycleviewlearn.decoration.AdvanceDecoration;

public class SwipeActivity extends AppCompatActivity {
    //初始化数据
    public List<String> datas = new ArrayList<>();
    private RecyclerView recyclerView;
    private SwipeAdapter swipeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe);
        initData();
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView.addItemDecoration(new AdvanceDecoration(this, RecyclerView.VERTICAL));
        //添加默认的动画效果
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setHasFixedSize(true);
        //设置Adapter
        swipeAdapter = new SwipeAdapter(this, datas);
        recyclerView.setAdapter(swipeAdapter);
        //接着在MainActivity中我们之前定制的长按事件中利用ItemTouchHelper的```startDrag(viewHolder)```实现长按拖拽滑动删除,代码如下
        final ItemTouchHelper helper = new ItemTouchHelper(new SwipeItemTouchHelper(swipeAdapter));
        helper.attachToRecyclerView(recyclerView);
    }

    private void initData() {
        Student student=Student.builder().name("张三").age("23").sex("boey").build();
        for (int i = 0; i < 20; i++) {
            datas.add("item " + i);
        }
    }
}
