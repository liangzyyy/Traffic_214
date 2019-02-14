package com.mad.trafficclient.login;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.mad.trafficclient.MainActivity;
import com.mad.trafficclient.R;
import com.mad.trafficclient.util.LoadingDialog;
import com.mad.trafficclient.util.UrlBean;
import com.mad.trafficclient.util.Util;

import org.json.JSONException;
import org.json.JSONObject;


public class LoginActivity extends Activity  {

	private UrlBean urlBean;
	private String urlHttp;
	private String urlPort = "80";
	
	EditText accountET, pwdET;
	Button loginBtn, regBtn,settingBtn;
	CheckBox checkBox;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//去除标题栏
		setContentView(R.layout.activity_login);
		initView();
		initLiserter();
	}

	private void initView() {
		// TODO Auto-generated method stub
		accountET = (EditText) findViewById(R.id.accountET);
		pwdET = (EditText) findViewById(R.id.pwdET);
		loginBtn = (Button) findViewById(R.id.loginBtn);
		regBtn = (Button) findViewById(R.id.regBtn);
		settingBtn = (Button) findViewById(R.id.setting);
		checkBox = (CheckBox) findViewById(R.id.jzpwdCB);
        urlBean = Util.loadSetting( LoginActivity.this );

	}
	
	private void initLiserter() {
		// TODO Auto-generated method stub
		regBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(LoginActivity.this,
						RegActivity.class);
				startActivity(intent);
			}
		});
		final SharedPreferences sp = getSharedPreferences("rmbPwd",MODE_PRIVATE);
		if (sp.getString("username","")!=""&&sp.getString("userpwd","")!=""){
			accountET.setText(sp.getString("username",""));
			pwdET.setText(sp.getString("userpwd",""));
			checkBox.setChecked(true);
		}
		loginBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				String account = accountET.getText().toString();
				String pwd = pwdET.getText().toString();

				SharedPreferences.Editor editor = sp.edit();

				if (account.equals("admin")&&pwd.equals("admin")){
					if (checkBox.isChecked()){
						editor.putString("username",account);
						editor.putString("userpwd",pwd).commit();
					}else{
						editor.clear().commit();
					}
					Intent intent = new Intent(LoginActivity.this,	MainActivity.class);
					startActivity(intent);
					finish();
				}
//				LoadingDialog.showDialog( LoginActivity.this);
//				JSONObject params = new JSONObject();
//				try {
//					params.put("UserName", account);
//                    params.put("UserPwd", pwd);
//				} catch (JSONException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				Log.d("TAG", params.toString());
//
//				String strUrl = "http://"+ urlBean.getUrl() +":" + urlBean.getPort() + "/transportservice/action/user_login.do";
//
//				Log.d("TAG", strUrl);
//
//				RequestQueue mQueue = Volley.newRequestQueue(LoginActivity.this);
//				JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, strUrl, params, new Response.Listener<JSONObject>() {
//					@Override
//					public void onResponse(JSONObject response) {
//						// TODO Auto-generated method stu
//						Log.d("TAG", response.toString());
//						LoadingDialog.disDialog();
//						if ( response.optString("RESULT").equals("S")){
//							Toast.makeText(getApplicationContext(), response.optString("ERRMSG"), Toast.LENGTH_LONG).show();
//
//							Intent intent = new Intent(LoginActivity.this,	MainActivity.class);
//							startActivity(intent);
//							finish();
//						}else if ( response.optString("RESULT").equals("F")) {
//							Toast.makeText(getApplicationContext(), response.optString("ERRMSG"), Toast.LENGTH_LONG).show();
//						}
//
//					}
//				}, new Response.ErrorListener() {
//					@Override
//					public void onErrorResponse(VolleyError error) {
//						// TODO Auto-generated method stub
//						LoadingDialog.disDialog();
//                        Log.d("TAG volley error", error.toString());
//						Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
//
//					}
//				});
//				mQueue.add(jsonObjectRequest);
			}
		});
		
		settingBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				final Dialog urlSettingDialog = new Dialog(LoginActivity.this);
				urlSettingDialog.show();
				urlSettingDialog.setTitle("Setting");
				urlSettingDialog.getWindow().setContentView(R.layout.login_setting);
				final EditText edit_urlHttp = (EditText) urlSettingDialog.getWindow().findViewById(R.id.edit_setting_url);
				final EditText edit_urlPort = (EditText) urlSettingDialog.getWindow().findViewById(R.id.edit_setting_port);

				edit_urlHttp.setText( urlBean.getUrl() );
				edit_urlPort.setText( urlBean.getPort());
				Button save = (Button) urlSettingDialog.getWindow().findViewById(R.id.save);
				Button cancel = (Button) urlSettingDialog.getWindow().findViewById(R.id.cancel);
				save.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						urlHttp = edit_urlHttp.getText().toString();
						urlPort = edit_urlPort.getText().toString();

						if ( urlHttp == null || urlHttp.equals("")   ) {
							Toast.makeText(LoginActivity.this,R.string.error_ip, Toast.LENGTH_LONG).show();
						} else {
							Util.saveSetting(urlHttp,urlPort,LoginActivity.this);
                            urlBean = Util.loadSetting( LoginActivity.this );
							urlSettingDialog.dismiss();
						}
					}
				});
				cancel.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						urlSettingDialog.dismiss();
					}
				});
				urlSettingDialog.show();
	
			}
		});
	
	}

}
