package testing.example.recycleviewlearn.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import testing.example.recycleviewlearn.R;

public class GeneralAdapter extends RecyclerView.Adapter<GeneralAdapter.MyViewHolder> {
    //当前上下文对象
    public Context context;
    //RecyclerView填充Item数据的List对象
    public List<String> datas;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    /**
     * 事件回调监听
     */
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;


    /**
     * 设置回调接口
     */

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    /**
     * 设置长按回调接口
     */
    public interface OnItemLongClickListener {
        boolean onItemLongClick(RecyclerView.ViewHolder viewHolder, View view, int position);
    }


    public GeneralAdapter(Context context, List<String> datas) {
        this.context = context;
        this.datas = datas;
    }

    //创建ViewHolder
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //实例化得到Item布局文件的View对象
        View view = View.inflate(context, R.layout.litem_ayout, null);
        //返回MyViewHolder的对象
        return new MyViewHolder(view);
    }

    //绑定数据
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.textView.setText(datas.get(position));

        /*在Adapter里面直接对控件做点击事件
        写接口，在Activity或Fragment上实现接口中定义的方法*/
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Toast.makeText(context,holder.textView.getText()+"被点击了",
                        Toast.LENGTH_SHORT).show();*/
                //通过接口回调响应点击事件
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(v, position);
                }
            }
        });
        //通过接口回调响应长按事件
        holder.textView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onItemLongClickListener != null) {
                    return onItemLongClickListener.onItemLongClick(holder, v, position);
                }
                return false;
            }
        });

    }

    //返回Item的数量
    @Override
    public int getItemCount() {
        return datas.size();
    }

    //继承RecyclerView.ViewHolder抽象类的自定义ViewHolder

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text);

        }
    }


    public void addItem(String data, int position) {
        datas.add(data);
        notifyItemInserted(position);
    }

    public void removeItem(String s, int position) {
        int p = datas.indexOf(s);
        datas.remove(p);
        notifyItemRemoved(p);
    }


}
