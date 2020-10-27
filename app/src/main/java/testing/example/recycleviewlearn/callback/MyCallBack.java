package testing.example.recycleviewlearn.callback;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

import testing.example.recycleviewlearn.R;
import testing.example.recycleviewlearn.adapter.GeneralAdapter;

public class MyCallBack extends ItemTouchHelper.Callback {

    GeneralAdapter adapter;
    private List<String> mDatas;

    public MyCallBack(GeneralAdapter adapter) {
        this.adapter = adapter;
        this.mDatas = adapter.datas;
    }

    /**
     * 此方法用以设置是否响应拖拽或滑动事件,并且确定类型
     */
    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        final int dragFlags, swipFlags;

        /**
         * 网格方向有UP ,Down ,left,right四个方向
         */
        if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
            dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            swipFlags = 0;
        } else {
            //LineaLayout只有Up ,Down
            dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            //右滑删除
//            swipFlags = ItemTouchHelper.END;
            swipFlags = ItemTouchHelper.LEFT;

        }

        return makeMovementFlags(dragFlags, swipFlags);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {

        int framPosition = viewHolder.getAdapterPosition();
        int toPosition = target.getAdapterPosition();
        Log.d("MyCallBack", "=====framPosition:" + framPosition);
        Log.d("MyCallBack", "=====toPosition:" + toPosition);

        /**
         * 向上拖拽
         */
        if (framPosition > toPosition) {
            Log.d("MyCallBack", "====up==up==");
            for (int i = framPosition; i > toPosition; i--) {
                Log.d("MyCallBack", "====up===i:" + i);
                Collections.swap(mDatas, i - 1, i);
            }
        } else {
            /**
             * 向下拖拽
             */
            for (int i = framPosition; i < toPosition; i++) {
                Log.d("MyCallBack", "====down===i:" + i);
                Collections.swap(mDatas, i, i+1);
            }
        }
        adapter.notifyItemMoved(framPosition, toPosition);
        adapter.datas = mDatas;
        Log.d("MyCallBack", "====data===mDatas:" + mDatas.toString());
        return true;
    }

    /**
     * viewHolder
     * params direction表示滑动方向,此处我们在上面规定为右滑
     */
    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getAdapterPosition();
        adapter.notifyItemRemoved(position);
        adapter.datas.remove(position);

    }

    /**
     * Item 是否支持长按拖动
     *
     * @return true  支持长按操作，false 不支持长按操作
     */

    @Override
    public boolean isLongPressDragEnabled() {
        return super.isLongPressDragEnabled();
    }

    /**
     * Item 是否支持滑动
     *
     * @return true  支持滑动操作，false 不支持滑动操作
     */
    @Override
    public boolean isItemViewSwipeEnabled() {
        return super.isItemViewSwipeEnabled();
    }

    /**
     * Item被选中时候回调
     *
     * @param viewHolder
     * @param actionState 当前Item的状态
     *                    ItemTouchHelper.ACTION_STATE_IDLE   闲置状态
     *                    ItemTouchHelper.ACTION_STATE_SWIPE  滑动中状态
     *                    ItemTouchHelper#ACTION_STATE_DRAG   拖拽中状态
     */
    @SuppressLint("ResourceAsColor")
    @Override
    public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) {
        // item 被选中的操作
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            viewHolder.itemView.setBackgroundColor(R.color.colorAccent);
        }
        super.onSelectedChanged(viewHolder, actionState);

    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        viewHolder.itemView.setBackgroundColor(R.color.colorWhite);

        super.clearView(recyclerView, viewHolder);
    }
}
