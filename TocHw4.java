/*
F74006014 陳兆元 TocHw4.java

利用url存入jsonArray格式，在依照優先順序判斷"路" "街" "大道" "巷"，讀出路名

利用HashMap存取各項對應路名的資料，最後output出有max_distinct_month的路名及最高 最低成交價


*/



import java.net.*;
import java.io.*;

import org.json.*;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TocHw4 {
	
	private static HashMap<String, Integer> count_road;
	private static HashMap<String, Integer> road_index;
	public static void main(String[] args) throws IOException, JSONException {
			URL oracle = new URL(args[0]);
	        URLConnection yc = oracle.openConnection();
	        BufferedReader in = new BufferedReader(new InputStreamReader(
	                                    yc.getInputStream(),"UTF-8"));
	        
	        int [][] month = new int[2000][100];
	        int [][] money = new int[2000][100];
	        count_road = new HashMap<String, Integer>();
	        road_index = new HashMap<String, Integer>();
	        
	        JSONTokener x = new JSONTokener(in);
	        JSONArray json = new JSONArray(x);
	        
	        //System.out.println(json.getJSONObject(3));
	        int y=0,avg_price=0,count=0;
	        String[] road_name = new String[2000];
	        int tmp_count=0,tmp_index=0,data_count=0;
	        int zz=0;
	        int [] max_distinct = new int[2000];
	        
	        int month_temp;

	        while (y!=json.length())
	        	{        		
	 
	            Pattern pattern1 = Pattern.compile(".*路");
	            Matcher matcher1 = pattern1.matcher((CharSequence) json.getJSONObject(y).get("土地區段位置或建物區門牌"));
	            
	            Pattern pattern2 = Pattern.compile(".*街");
	            Matcher matcher2 = pattern2.matcher((CharSequence) json.getJSONObject(y).get("土地區段位置或建物區門牌"));
	            
	            Pattern pattern3 = Pattern.compile(".*大道");
	            Matcher matcher3 = pattern3.matcher((CharSequence) json.getJSONObject(y).get("土地區段位置或建物區門牌"));
	            
	            Pattern pattern4 = Pattern.compile(".*巷");
	            Matcher matcher4 = pattern4.matcher((CharSequence) json.getJSONObject(y).get("土地區段位置或建物區門牌"));

	            
	            if(matcher1.find())
	            {
	            	if(count_road.get(matcher1.group())==null)
	            	{
	            		count_road.put(matcher1.group(), 1);
	            		road_index.put(matcher1.group(), count);
	            		road_name[count]=matcher1.group();	          
	            		count++;
	            		
	            	}
	            	else
	            	{
	            		count_road.put(matcher1.group(), count_road.get(matcher1.group() )+ 1);
	            	}
	            	
	            	month_temp=json.getJSONObject(y).getInt("交易年月");
	            	money[road_index.get(matcher1.group())][count_road.get(matcher1.group())]=json.getJSONObject(y).getInt("總價元");
	            	
	            	for(zz=0;zz<=count_road.get(matcher1.group());zz++)
	            	{
	            		if(month_temp==month[ road_index.get(matcher1.group()) ][zz])    //重覆
	            			break;
	            		if(zz==count_road.get(matcher1.group()))
	            		{
	            			max_distinct[road_index.get(matcher1.group())]++;
	            			month[road_index.get(matcher1.group())][zz+1]=month_temp;
	            		}
	            	}
	            	
	            }
	            else if(matcher2.find())
	            {
	            	if(count_road.get(matcher2.group())==null)
	            	{
	            		count_road.put(matcher2.group(), 1);
	            		road_index.put(matcher2.group(), count);
	            		road_name[count]=matcher2.group();
	            		count++;
	 
	            	}
	            	else
	            		count_road.put(matcher2.group(), count_road.get(matcher2.group())+ 1);
	            	
	            	
	            	month_temp=json.getJSONObject(y).getInt("交易年月");
	            	money[road_index.get(matcher2.group())][count_road.get(matcher2.group())]=json.getJSONObject(y).getInt("總價元");
	            	
	            	for(zz=0;zz<=count_road.get(matcher2.group());zz++)
	            	{
	            		if(month_temp==month[ road_index.get(matcher2.group()) ][zz])    //重覆
	            			break;
	            		if(zz==count_road.get(matcher2.group()))
	            		{
	            			max_distinct[road_index.get(matcher2.group())]++;
	            			month[road_index.get(matcher2.group())][zz+1]=month_temp;
	            		}
	            	}
	            }
	            else if(matcher3.find())
	            {
	            	if(count_road.get(matcher3.group())==null)
	            	{
	            		count_road.put(matcher3.group(), 1);
	            		road_index.put(matcher3.group(), count);
	            		road_name[count]=matcher3.group();
	            		count++;
	            		
	            	}
	            	else
	            		count_road.put(matcher3.group(), count_road.get(matcher3.group())+1);
	            	
	            	
	            	month_temp=json.getJSONObject(y).getInt("交易年月");
	            	money[road_index.get(matcher3.group())][count_road.get(matcher3.group())]=json.getJSONObject(y).getInt("總價元");
	            	
	            	for(zz=0;zz<=count_road.get(matcher3.group());zz++)
	            	{
	            		if(month_temp==month[ road_index.get(matcher3.group()) ][zz])    //重覆
	            			break;
	            		if(zz==count_road.get(matcher3.group()))
	            		{
	            			max_distinct[road_index.get(matcher3.group())]++;
	            			month[road_index.get(matcher3.group())][zz+1]=month_temp;
	            		}
	            	}
	            }
	            else if(matcher4.find())
	            {
	            	if(count_road.get(matcher4.group())==null)
	            	{
	            		count_road.put(matcher4.group(), 1);
	            		road_index.put(matcher4.group(), count);
	            		road_name[count]=matcher4.group();
	            		count++;
	 
	            	}
	            	else
	            		count_road.put(matcher4.group(), count_road.get(matcher4.group())+ 1);
	            	
	            	
	            	month_temp=json.getJSONObject(y).getInt("交易年月");
	            	money[road_index.get(matcher4.group())][count_road.get(matcher4.group())]=json.getJSONObject(y).getInt("總價元");
	            	
	            	for(zz=0;zz<=count_road.get(matcher4.group());zz++)
	            	{
	            		if(month_temp==month[ road_index.get(matcher4.group()) ][zz])    //重覆
	            			break;
	            		if(zz==count_road.get(matcher4.group()))
	            		{
	            			max_distinct[road_index.get(matcher4.group())]++;
	            			month[road_index.get(matcher4.group())][zz+1]=month_temp;
	            		}
	            	}
	            
	            }
	            
	        	y++;
	        	
	        	}
	        
	        int[] same_distinct = new int[100];
	        int same_count =0;
	        for(Object key : road_index.keySet()){
		        
	        	
	        	if(max_distinct[road_index.get(key)]>tmp_count)
	        	{
	        		tmp_index=road_index.get(key);
	        		tmp_count = max_distinct[road_index.get(key)];
	        	}

	        }
	        
	        for(Object key : road_index.keySet()){
		        
		        if(max_distinct[road_index.get(key)]==tmp_count)
	        	{
	        		same_distinct[same_count]=road_index.get(key);      		
	        		same_count++;
	        	}


	        }

	        int i=0;
	        
	        for(y=0;y<same_count;y++)
	        {	       
        	int tmphigh=0;
	        int tmplow=999999999;
		        for(i=0;i<=count_road.get(road_name[same_distinct[y]]);i++)
		        {
		        	if(money[same_distinct[y]][i]!=0)
		        	{
		        		if(money[same_distinct[y]][i]>tmphigh)
		        			tmphigh=money[tmp_index][i];
	        		
	        			if(money[same_distinct[y]][i]<tmplow)
	        				tmplow=money[same_distinct[y]][i];
		        	}
		        }
		        System.out.println(road_name[same_distinct[y]] +", 最高成交價:"+tmphigh+", 最低成交價"+tmplow);
	        }
	        
	}
}
	
