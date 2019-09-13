package model;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

public class TrainParser {
	private HttpURLConnection con;
	private JsonReader jr;
	private void readJson(String urlString) throws IOException {
		URL url=new URL(urlString);
		this.con=(HttpURLConnection)url.openConnection();
		this.con.setRequestMethod("GET");
		InputStream is=this.con.getInputStream();
		InputStreamReader isr=new InputStreamReader(is,"UTF-8");
		this.jr=new JsonReader(isr);
	}
	private void disconnect() throws IOException {
		if(this.jr !=null) {
			this.jr.close();
		}
		if(this.con !=null) {
			this.con.disconnect();
		}
	}
	public List<String> getRails(String urlString){
		List<String> list=new ArrayList<>();

		try {
			this.readJson(urlString);
			JsonObject root=new Gson().fromJson(jr,JsonObject.class);
			JsonArray lines=root.get("response").getAsJsonObject().get("line").getAsJsonArray();
			for(JsonElement ele:lines) {
				list.add(ele.getAsString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				this.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println(list.size()+list.get(0));
		return list;
	}
	public List<String> getStations(String urlString){
		List<String> list=new ArrayList<>();
		try {
			this.readJson(urlString);
			JsonObject root=new Gson().fromJson(jr,JsonObject.class);
			JsonArray stations=root.get("response").getAsJsonObject().get("station").getAsJsonArray();
			for(JsonElement ele:stations) {
				list.add(ele.getAsJsonObject().get("name").getAsString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				this.disconnect();
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
		System.out.println(list.size()+list.get(0));
		return list;
	}

}
