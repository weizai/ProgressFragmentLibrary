package com.wjw.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.myfragmenttest.R;

public abstract class ProgressFragment extends Fragment {
	private ViewGroup rootGroupView;
	public abstract void onEmptyClick();
	
	public abstract int setContentViewRes();

	private void addToRootView(View view) {
		ViewGroup viewGroup = (ViewGroup) getView().findViewById(
				R.id.frag_content);
		viewGroup.addView(view);
	}

	public void setRealContentView() {
		int layoutId = setContentViewRes();
		if(layoutId == 0)
			throw new RuntimeException("please set layoutId...");
		
		View view = LayoutInflater.from(getActivity()).inflate(layoutId,
				new FrameLayout(getActivity()), true);
		addToRootView(view);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setRealContentView();
		registEmptyClick();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.m_layout, container, false);
	}

	public void showProgress() {
		showView(R.id.pbar);
	}

	public void showEmpty(){
		showView(R.id.loadError);
	}
	
	public void showContent(){
		showView(R.id.frag_content);
	}
	
	private void showView(int viewId){
		ViewGroup viewGroup = getRootViewGroup();
		if(viewGroup == null){
			return ;
		}
		for(int i = 0 ; i < viewGroup.getChildCount(); i++){
			if(viewGroup.getChildAt(i).getId() == viewId){
				viewGroup.getChildAt(i).setVisibility(View.VISIBLE);
			}else {
				viewGroup.getChildAt(i).setVisibility(View.GONE);
			}
		}
	}
	
	private ViewGroup getRootViewGroup(){
		if(rootGroupView == null){
			if(getView() == null){
				return null;
			}
			
			rootGroupView = (ViewGroup) getView().findViewById(R.id.mrootView);
		}
		return rootGroupView;
	}
	
	public void stopProgress() {
		getView().findViewById(R.id.pbar).setVisibility(View.GONE);
	}

	private void registEmptyClick(){
		getView().findViewById(R.id.loadError).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				onEmptyClick();
			}
		});
	}
}
