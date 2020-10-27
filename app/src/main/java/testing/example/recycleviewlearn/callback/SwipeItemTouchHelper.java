package testing.example.recycleviewlearn.callback;

import android.graphics.Canvas;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import testing.example.recycleviewlearn.R;
import testing.example.recycleviewlearn.adapter.SwipeAdapter;

public class SwipeItemTouchHelper extends ItemTouchHelper.Callback {

    public SwipeItemTouchHelper(SwipeAdapter adapter) {
        this.adapter = adapter;
    }

    private SwipeAdapter adapter;

    /**
     * 设置滑动类型标记
     */

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int dragFlags = 0;//禁止上下拖动
        int swipeFlags = ItemTouchHelper.LEFT;// 只允许从右向左滑动


        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        adapter.onItemDismiss(viewHolder.getAdapterPosition());
    }

    /**
     * 设置 Item 不支持长按拖动
     */
    @Override
    public boolean isLongPressDragEnabled() {
        return false;
    }

    /**
     * 设置 Item 支持滑动
     */
    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    /**
     * Item 被选中时候，改变 Item 的背景
     */
    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        // item 被选中的操作
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            viewHolder.itemView.setBackgroundResource(R.drawable.select_bg);
        }
        super.onSelectedChanged(viewHolder, actionState);
    }

    /**
     * 移动过程中重新绘制 Item，随着滑动的距离，设置 Item 的透明度
     */
    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        float x = Math.abs(dX) + 0.5f;
        float width = viewHolder.itemView.getWidth();
        float alpha = 1f - x / width;
        viewHolder.itemView.setAlpha(alpha);
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }

    /**
     * 用户操作完毕或者动画完毕后调用，恢复 item 的背景和透明度
     */
    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        // 操作完毕后恢复颜色
        viewHolder.itemView.setBackgroundResource(R.drawable.common_bg);
        viewHolder.itemView.setAlpha(1.0f);
        super.clearView(recyclerView, viewHolder);
    }
}
