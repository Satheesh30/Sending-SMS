package sendMail;

/*
 * Send Bulk SMS 
 * we use httpsURLConnection to send sms to phone number
 * send sms through apiKey in www.fast2sms.com 
 *  
 */


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.Iterator;
import java.util.Map;



import javax.net.ssl.HttpsURLConnection;





public class Sendsms {
	public static void main(String[] args) {
		
		Sms sms=new Sms();
		
		
		
		
		Properties properties = new Properties();
		properties.put("satheesh", "*******");//phone number
		properties.put("joshuva", "*******");//phone number
		
		Set set=properties.entrySet();
		Iterator itr=(Iterator) set.iterator();
		
		
		while(itr.hasNext()) {
			Map.Entry me=(Map.Entry)itr.next();
			sms.SendSms((String) me.getKey(), (String) me.getValue());
		}
		
		
	
	}
}
class Sms{
	public void SendSms(String name  ,String number) {
		System.out.println("Sending sms to "+name);
		try {
			String otp= new DecimalFormat("000000").format(new Random().nextInt(999999));
		String message = "Dear "+name+"\n Password is "+otp+"for opening the pdf file.\nit will be valid for 4 minutes";
		
		
		
		message = URLEncoder.encode(message , "UTF-8");
		String apiKey = "*****";
		String myUrl ="https://www.fast2sms.com/dev/bulkV2?authorization="+apiKey+"&sender_id=TXTIND"+"&message="+message+"&route=v3&numbers="+number;
		
		URL url = new URL(myUrl);
		HttpsURLConnection con = (HttpsURLConnection)url.openConnection();
		
	
		StringBuffer response=new StringBuffer();
		BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		
		while(true) {
			String line = br.readLine();
			
			if(line ==null) {
				break;
			}
			response.append(line);
		}
		System.out.println("\t Sms Sent Successfully..");
		System.out.println("_________________________________________________");
	}catch(Exception e) {e.printStackTrace();}
	}

}
