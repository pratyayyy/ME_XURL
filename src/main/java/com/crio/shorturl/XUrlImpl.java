package com.crio.shorturl;

import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

public class XUrlImpl implements XUrl{

   
    private HashMap<String,String> longToShortMap;
    private HashMap<String,String> shortToLongMap;
    private HashMap<String,Integer> hitCountMap;

    public XUrlImpl()
    {
        longToShortMap = new HashMap<>();
        shortToLongMap = new HashMap<>();
        hitCountMap = new HashMap<>();

    }
   
   
    
    @Override
    public String registerNewUrl(String longUrl) {
       if(longToShortMap.containsKey(longUrl))
       {
        return longToShortMap.get(longUrl);
       }
       else
       {
        String shortUrl="http://short.url/";
        String uuid = UUID.randomUUID().toString().replace("-","").substring(0,9);
        shortUrl += uuid;
        longToShortMap.put(longUrl,shortUrl);
        shortToLongMap.put(shortUrl,longUrl);
        
        return shortUrl;
       }
       
    }
    


    @Override
    public String registerNewUrl(String longUrl, String shortUrl) {
        if(shortToLongMap.containsKey(shortUrl))
        {
        return null;
        }
        else
        {
            longToShortMap.put(longUrl,shortUrl);
            shortToLongMap.put(shortUrl,longUrl);
            hitCountMap.put(longUrl,0);
            return shortUrl;
        }
    }

    @Override
    public String getUrl(String shortUrl) {
        if(shortToLongMap.containsKey(shortUrl)){
            String longUrl = shortToLongMap.get(shortUrl);
            Integer oldValue = hitCountMap.get(longUrl);
            if(oldValue==null)
            {
               hitCountMap.put(longUrl,1);
            }
            else
            {
               hitCountMap.put(longUrl,oldValue+1);
            }
            return longUrl;
        }
        else{
            return null;
        }
      }

   


    @Override
    public String delete(String longUrl) {
       
       if(longToShortMap.containsKey(longUrl))
       {
        String shortUrl = longToShortMap.get(longUrl);

        longToShortMap.remove(longUrl);
        shortToLongMap.remove(shortUrl);
        return shortUrl;
       }
       else
       { 
        return null;
       }
       
    }


    @Override
    public Integer getHitCount(String longUrl)
    {
        if(!longToShortMap.containsKey(longUrl))
        {
          return 0;
        }
        return hitCountMap.get(longUrl);
    }

    
}