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
		// Webサーバにアクセス
		// -----[クライアント設定]
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
			uc.setDoOutput(true);// POST可能にする

			uc.setRequestProperty("User-Agent", "@Bariter URLConnection");// ヘッダを設定
			uc.setRequestProperty("Accept-Language", "ja");// ヘッダを設定
			OutputStream os = uc.getOutputStream();// POST用のOutputStreamを取得

			String postStr = postJson;// POSTするデータ
			PrintStream ps = new PrintStream(os);
			ps.print(postStr);// データをPOSTする
			ps.close();

			InputStream is = uc.getInputStream();// POSTした結果を取得
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
// -----[POST送信するパラメタを格納]
// List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(1);

// @add_user_property = %w[email passwd age gender occupation]
// サンプルパラメータ(画面からの入力値で登録できるようにする。)

/**
 * nameValuePair.add(new BasicNameValuePair("email", "test@nowhere.com"));
 * nameValuePair.add(new BasicNameValuePair("passwd", "12345"));
 * nameValuePair.add(new BasicNameValuePair("age", "20")); nameValuePair.add(new
 * BasicNameValuePair("gender", "M")); nameValuePair.add(new
 * BasicNameValuePair("occupation", "Stundent"));
 **/
// JSONでPOST

/*
 * super.onCreate(savedInstanceState); setContentView(R.layout.main);
 * 
 * btn = (Button) findViewById(R.id.btn1); tv = (TextView)
 * findViewById(R.id.tv1); btn.setOnClickListener(new View.OnClickListener() {
 * 
 * public void onClick(View v) { // ボタン押下時 if (v == btn) { exec_post(); } } });
 * } /* private void exec_post() { try { URL url = new
 * URL("http://www.google.co.jp"); HttpURLConnection http = (HttpURLConnection)
 * url.openConnection(); http.setRequestMethod("GET"); http.connect();
 * InputStream in = http.getInputStream(); byte b[] = new byte[1024];
 * in.read(b); in.close(); http.disconnect(); tv.setText(new String(b)); } catch
 * (Exception e) { tv.setText(e.toString()); } setContentView(tv); }
 * 
 * /* Log.d("posttest", "postします"); String ret = "";
 * 
 * // URL URI url = null; try { // url = new URI(
 * "http://10.0.2.2/android_post_test.php" ); url = new
 * URI("http://warm-summer-9351.herokuapp.com/"); Log.d("posttest", "URLはOK"); }
 * catch (URISyntaxException e) { e.printStackTrace(); ret = e.toString(); }
 * 
 * // POSTパラメータ付きでPOSTリクエストを構築 HttpPost request = new HttpPost(url);
 * List<NameValuePair> post_params = new ArrayList<NameValuePair>();
 * post_params.add(new BasicNameValuePair("post_1", "ユーザID"));
 * post_params.add(new BasicNameValuePair("post_2", "パスワード")); try { //
 * 送信パラメータのエンコードを指定 request.setEntity(new UrlEncodedFormEntity(post_params,
 * "UTF-8")); } catch (UnsupportedEncodingException e1) { e1.printStackTrace();
 * }
 * 
 * // POSTリクエストを実行 DefaultHttpClient httpClient = new DefaultHttpClient(); try {
 * Log.d("posttest", "POST開始"); ret = httpClient.execute(request, new
 * ResponseHandler<String>() {
 * 
 * public String handleResponse(HttpResponse response) throws IOException {
 * Log.d("posttest", "レスポンスコード：" + response.getStatusLine().getStatusCode());
 * 
 * // 正常に受信できた場合は200 switch (response.getStatusLine().getStatusCode()) { case
 * HttpStatus.SC_OK: Log.d("posttest", "レスポンス取得に成功");
 * 
 * // レスポンスデータをエンコード済みの文字列として取得する return
 * EntityUtils.toString(response.getEntity(), "UTF-8");
 * 
 * case HttpStatus.SC_NOT_FOUND: Log.d("posttest", "データが存在しない"); return null;
 * 
 * default: Log.d("posttest", "通信エラー"); return null; } } });
 * 
 * } catch (IOException e) { Log.d("posttest", "通信に失敗：" + e.toString()); }
 * finally { // shutdownすると通信できなくなる
 * httpClient.getConnectionManager().shutdown(); }
 * 
 * // 受信結果をUIに表示 tv.setText(ret); }
 */