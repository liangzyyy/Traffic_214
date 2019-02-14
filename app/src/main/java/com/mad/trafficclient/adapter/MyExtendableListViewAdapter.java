package com.mad.trafficclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mad.trafficclient.R;

/**
 * Created by liangzy on 2019/2/8.
 */

public class MyExtendableListViewAdapter extends BaseExpandableListAdapter {
    //
    private Context mContext;
    public String[] groupStr={"1路", "2路", "3路", "4路"};
    public String[][] childStr={
            {"光谷金融街", "戎军南路", "戎军南路", "戎军南路","戎军南路", "戎军南路","南湖商厦"},
            {"光谷金融街", "戎军南路", "戎军南路", "戎军南路","戎军南路", "戎军南路","南湖商厦"},
            {"光谷金融街", "戎军南路", "戎军南路", "戎军南路","戎军南路", "戎军南路","南湖商厦"},
            {"光谷金融街", "戎军南路", "戎军南路", "戎军南路","戎军南路", "戎军南路","南湖商厦"}};

    @Override
    public int getGroupCount() {
        return groupStr.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childStr[groupPosition].length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupStr[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childStr[groupPosition][childPosition];
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.parent_item,parent,false);
            groupViewHolder = new GroupViewHolder();
            groupViewHolder.tv_title = (TextView) convertView.findViewById(R.id.parentItem_roadId_tv);
            groupViewHolder.imageView = (ImageView) convertView.findViewById(R.id.parentImageViw);
            convertView.setTag(groupViewHolder);
        }else{
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }
        groupViewHolder.tv_title.setText(groupStr[groupPosition]);

        if (isExpanded){
            groupViewHolder.imageView.setImageResource(R.drawable.next2);//设置箭头
        }else{
            groupViewHolder.imageView.setImageResource(R.drawable.next);
        }

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_item,parent,false);
            childViewHolder = new ChildViewHolder();
            childViewHolder.tv_title = (TextView) convertView.findViewById(R.id.child_title_tv);
            childViewHolder.child_start_end_tv = (TextView) convertView.findViewById(R.id.child_start_end_tv);
            convertView.setTag(childViewHolder);
        }else{
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        childViewHolder.tv_title.setText(childStr[groupPosition][childPosition]);

        if (childStr[groupPosition][childPosition]=="光谷金融街"){
            childViewHolder.child_start_end_tv.setText("起点:");
        }
        if (isLastChild){
            childViewHolder.child_start_end_tv.setText("终点:");
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    static class GroupViewHolder {
        TextView tv_title;
        ImageView imageView;
    }

    static class ChildViewHolder {
        TextView tv_title;
        TextView child_start_end_tv;
    }
}
