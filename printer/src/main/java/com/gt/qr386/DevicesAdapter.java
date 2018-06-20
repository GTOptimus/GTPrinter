package com.gt.qr386;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * 类描述：蓝牙设备适配器
 * 创建人：yangxing
 * 创建时间：2018/6/11 14:52
 * 修改人：yangxing
 * 修改时间：2018/6/11 14:52
 * 修改备注：
 */

public class DevicesAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<Device> groupList;
    private OnExpandItemClickListener onExpandItemClickListener;

    public DevicesAdapter(Context context, List<Device> groupList,
                          OnExpandItemClickListener onExpandItemClickListener) {
        this.context = context;
        this.groupList = groupList;
        this.onExpandItemClickListener = onExpandItemClickListener;
    }

    @Override
    public int getGroupCount() {
        return groupList == null?0:groupList.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return groupList.get(i).getDeviceSets() == null?0:groupList.get(i).getDeviceSets().size();
    }

    @Override
    public Object getGroup(int i) {
        return groupList.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return groupList.get(i).getDeviceSets().get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(final int i, boolean b, View convertView, ViewGroup viewGroup) {
        GroupHolder groupHolder;
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_group,null);
            groupHolder = new GroupHolder();
            groupHolder.tv_group = (TextView) convertView;
            convertView.setTag(groupHolder);
        }else{
            groupHolder = (GroupHolder) convertView.getTag();
        }
        String title = groupList.get(i).getTitle();
        if (groupList.get(i).getDeviceSets() == null
                || groupList.get(i).getDeviceSets().isEmpty()){
            title += "\t\t\t\t点击重新加载";
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onExpandItemClickListener != null)
                    onExpandItemClickListener.groupItemClick();
            }
        });
        }
        groupHolder.tv_group.setText(title);

        return convertView;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View convertView, ViewGroup viewGroup) {
        ChildHolder childHolder;
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_child,null);
            childHolder = new ChildHolder();
            childHolder.tv_child = (TextView) convertView;
            convertView.setTag(childHolder);
        }else{
            childHolder = (ChildHolder) convertView.getTag();
        }

        childHolder.tv_child.setText(
                groupList.get(i).getDeviceSets().get(i1).getName()
                        + "\n" +
                        groupList.get(i).getDeviceSets().get(i1).getAddress());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onExpandItemClickListener != null)
                    onExpandItemClickListener.childItemClick(((TextView) view).getText().toString());
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    class GroupHolder{
        private TextView tv_group;

    }
    class ChildHolder{
        private TextView tv_child;

    }

    interface OnExpandItemClickListener{
         void groupItemClick();

         void childItemClick(String deviceInfo);
    }

    public void setOnExpandItemClickListener(OnExpandItemClickListener onExpandItemClickListener) {
        this.onExpandItemClickListener = onExpandItemClickListener;
    }

    public void updateData(List<Device> dataList){
        this.groupList = dataList;
        notifyDataSetChanged();
    }
}
