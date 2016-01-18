package com.zto.elasticsearch;

import java.math.BigDecimal;
import java.util.Date;

import com.zto.entity.BillStatus;

public class testison {

	BillStatus bill;
 
	    public static void main(String[] args) {
			for(int i=0;i<10;i++){
				/*{"title":"title2","name":"name2","age":2}
				{"index":{"_id":"3"}}*/
				System.out.println("{\"index\":{\"_id\":\""+i+"\"}}");
				System.out.println("{\"billcode\":\""+"bill"+(10000+i)+"\",\"useSite\":\""+"useSite"+i+"\""
						+",\"useSiteId\":"+100000+i
						+",\"recordSite\":\""+"recordSite"+i+"\""
						+",\"recordSiteId\":"+100+i
						+",\"recordDate\":\""+new Date().getTime()
						+",\"recordWeight\":\""+202.03+i
						+",\"recSite\":\""+"recSite"+i+"\""
						+",\"recSiteId\":\""+"recSiteId"+i+"\""
						+",\"recDate\":\""+new Date().getTime()
						+",\"firScanSite\":\""+"firScanSite"+i+"\""
						+",\"firScanSiteId\":\""+567+i
						+",\"firScanDate\":\""+new Date().getTime()
						+",\"firScanType\":\""+"firScanType"+i+"\""
						+",\"firWeight\":"+43543.9+i
						+",\"recMan\":\""+"recMan"+i+"\""
						+",\"recManCode\":\""+"recManCode"+i+"\""
						+",\"recCustomer\":\""+"recCustomer"+i+"\""
						+",\"presSite\":\""+"presSite"+i+"\""
						+",\"presSiteId\":"+546+i
						+",\"presScanDate\":"+new Date().getTime()+i
						+",\"presScanType\":\""+"presScanType"+i+"\""
						+",\"presContent\":\""+"presContent"+i+"\""
						+",\"presWeight\":"+53245.21+i
						+",\"firPreSite\":\""+"firPreSite"+i+"\""
						+",\"firPreId\":"+8978+i
						
						+ "}");
			}
		}
	    
	    
}
