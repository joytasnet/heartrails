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
	public List<String> getRails(String urlString){
		List<String> list=new ArrayList<>();
		HttpURLConnection con=null;
		JsonReader jr=null;
		try {
			URL url=new URL(urlString);
			con=(HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");
			InputStream is=con.getInputStream();
			InputStreamReader isr=new InputStreamReader(is,"UTF-8");
			jr=new JsonReader(isr);
			JsonObject root=new Gson().fromJson(jr,JsonObject.class);
			JsonArray lines=root.get("response").getAsJsonObject().get("line").getAsJsonArray();
			for(JsonElement ele:lines) {
				list.add(ele.getAsString());
			}
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}finally {
			if(jr != null) {
				try {
					jr.close();
				} catch (IOException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
			}
			if(con !=null) {
				con.disconnect();
			}
		}
		System.out.println(list.size()+list.get(0));
		return list;
	}
	public List<String> getStations(String urlString){
		List<String> list=new ArrayList<>();
		HttpURLConnection con=null;
		JsonReader jr=null;
		try {
			URL url=new URL(urlString);
			con=(HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");
			InputStream is=con.getInputStream();
			InputStreamReader isr=new InputStreamReader(is,"UTF-8");
			jr=new JsonReader(isr);
			JsonObject root=new Gson().fromJson(jr,JsonObject.class);
			JsonArray stations=root.get("response").getAsJsonObject().get("station").getAsJsonArray();
			for(JsonElement ele:stations) {
				list.add(ele.getAsJsonObject().get("name").getAsString());
			}
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}finally {
			if(jr != null) {
				try {
					jr.close();
				} catch (IOException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
			}
			if(con !=null) {
				con.disconnect();
			}
		}
		System.out.println(list.size()+list.get(0));
		return list;
	}

}
