/**
 * 
 */
package com.mad.trafficclient.fragment;

import com.mad.trafficclient.Activity.RoadDetailActivity;
import com.mad.trafficclient.R;
import com.mad.trafficclient.adapter.MyExtendableListViewAdapter;
import com.mad.trafficclient.util.QRCodeUtil;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class Fragment_1 extends Fragment
{

	private ExpandableListView expandableListView;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View view = inflater
				.inflate(R.layout.fragment_layout01, container, false);
		expandableListView = (ExpandableListView) view.findViewById(R.id.expandableListView);
		expandableListView.setAdapter(new MyExtendableListViewAdapter());

		expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
			@Override
			public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
				Intent intent = new Intent(getContext(),RoadDetailActivity.class);
				startActivity(intent);
				return false;
			}
		});
		return view;
	}

}
