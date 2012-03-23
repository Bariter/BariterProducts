package com.bariter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import net.arnx.jsonic.JSON;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class SharedBookMarksActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		TextView tv = new TextView(this);
		// Web�T�[�o�ɃA�N�Z�X
		// -----[�N���C�A���g�ݒ�]
		String urlString = "http://warm-summer-9351.herokuapp.com/add_user";
		JsonData jd = new JsonData();
		jd.setAge(18);
		jd.setGender("M");
		jd.setOccupation("studnet");
		jd.setEmail("test@nowhere.net");
		jd.setPasswd("this is my password");

		final String postJson = JSON.encode(jd);
		Log.d("POSJSONDATA", postJson);

		try {
			URL url = new URL(urlString);
			URLConnection uc = url.openConnection();
			uc.setDoOutput(true);// POST�\�ɂ���

			uc.setRequestProperty("User-Agent", "@Bariter URLConnection");// �w�b�_��ݒ�
			uc.setRequestProperty("Accept-Language", "ja");// �w�b�_��ݒ�
			OutputStream os = uc.getOutputStream();// POST�p��OutputStream���擾

			String postStr = postJson;// POST����f�[�^
			PrintStream ps = new PrintStream(os);
			ps.print(postStr);// �f�[�^��POST����
			ps.close();

			InputStream is = uc.getInputStream();// POST�������ʂ��擾
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(is));
			String s;
			while ((s = reader.readLine()) != null) {
				Log.d("result", s);
			}
			reader.close();
		} catch (MalformedURLException e) {
			Log.e("Invalid URL format: ", urlString);
		} catch (IOException e) {
			Log.e("Can't connect to ", urlString);
		}
	}
}
// -----[POST���M����p�����^���i�[]
// List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(1);

// @add_user_property = %w[email passwd age gender occupation]
// �T���v���p�����[�^(��ʂ���̓��͒l�œo�^�ł���悤�ɂ���B)

/**
 * nameValuePair.add(new BasicNameValuePair("email", "test@nowhere.com"));
 * nameValuePair.add(new BasicNameValuePair("passwd", "12345"));
 * nameValuePair.add(new BasicNameValuePair("age", "20")); nameValuePair.add(new
 * BasicNameValuePair("gender", "M")); nameValuePair.add(new
 * BasicNameValuePair("occupation", "Stundent"));
 **/
// JSON��POST

/*
 * super.onCreate(savedInstanceState); setContentView(R.layout.main);
 * 
 * btn = (Button) findViewById(R.id.btn1); tv = (TextView)
 * findViewById(R.id.tv1); btn.setOnClickListener(new View.OnClickListener() {
 * 
 * public void onClick(View v) { // �{�^�������� if (v == btn) { exec_post(); } } });
 * } /* private void exec_post() { try { URL url = new
 * URL("http://www.google.co.jp"); HttpURLConnection http = (HttpURLConnection)
 * url.openConnection(); http.setRequestMethod("GET"); http.connect();
 * InputStream in = http.getInputStream(); byte b[] = new byte[1024];
 * in.read(b); in.close(); http.disconnect(); tv.setText(new String(b)); } catch
 * (Exception e) { tv.setText(e.toString()); } setContentView(tv); }
 * 
 * /* Log.d("posttest", "post���܂�"); String ret = "";
 * 
 * // URL URI url = null; try { // url = new URI(
 * "http://10.0.2.2/android_post_test.php" ); url = new
 * URI("http://warm-summer-9351.herokuapp.com/"); Log.d("posttest", "URL��OK"); }
 * catch (URISyntaxException e) { e.printStackTrace(); ret = e.toString(); }
 * 
 * // POST�p�����[�^�t����POST���N�G�X�g���\�z HttpPost request = new HttpPost(url);
 * List<NameValuePair> post_params = new ArrayList<NameValuePair>();
 * post_params.add(new BasicNameValuePair("post_1", "���[�UID"));
 * post_params.add(new BasicNameValuePair("post_2", "�p�X���[�h")); try { //
 * ���M�p�����[�^�̃G���R�[�h���w�� request.setEntity(new UrlEncodedFormEntity(post_params,
 * "UTF-8")); } catch (UnsupportedEncodingException e1) { e1.printStackTrace();
 * }
 * 
 * // POST���N�G�X�g�����s DefaultHttpClient httpClient = new DefaultHttpClient(); try {
 * Log.d("posttest", "POST�J�n"); ret = httpClient.execute(request, new
 * ResponseHandler<String>() {
 * 
 * public String handleResponse(HttpResponse response) throws IOException {
 * Log.d("posttest", "���X�|���X�R�[�h�F" + response.getStatusLine().getStatusCode());
 * 
 * // ����Ɏ�M�ł����ꍇ��200 switch (response.getStatusLine().getStatusCode()) { case
 * HttpStatus.SC_OK: Log.d("posttest", "���X�|���X�擾�ɐ���");
 * 
 * // ���X�|���X�f�[�^���G���R�[�h�ς݂̕�����Ƃ��Ď擾���� return
 * EntityUtils.toString(response.getEntity(), "UTF-8");
 * 
 * case HttpStatus.SC_NOT_FOUND: Log.d("posttest", "�f�[�^�����݂��Ȃ�"); return null;
 * 
 * default: Log.d("posttest", "�ʐM�G���["); return null; } } });
 * 
 * } catch (IOException e) { Log.d("posttest", "�ʐM�Ɏ��s�F" + e.toString()); }
 * finally { // shutdown����ƒʐM�ł��Ȃ��Ȃ�
 * httpClient.getConnectionManager().shutdown(); }
 * 
 * // ��M���ʂ�UI�ɕ\�� tv.setText(ret); }
 */