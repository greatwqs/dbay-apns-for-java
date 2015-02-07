package com.dbay.apns4j.demo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.dbay.apns4j.ApnsDelegate;
import com.dbay.apns4j.IApnsService;
import com.dbay.apns4j.impl.ApnsServiceImpl;
import com.dbay.apns4j.model.ApnsConfig;
import com.dbay.apns4j.model.Payload;
import com.dbay.apns4j.model.PushNotification;

/**
 * greatwqs updated for ApnsDelegate on 2015-02-07;
 * 
 * @author RamosLi
 * @author greatwqs
 */
public class Apns4jDemo {
	
	private static IApnsService apnsService;
	
	private static final ApnsDelegate delegate    = new ApnsDelegate() {
		
		public void messageSendFailed(PushNotification message, byte[] responseCode) {
			System.out.println("Apns4jDemo, tokenNotValid, Sent message " + message + " responseCode: " + responseCode);
			if(message == null){
				return ;
			}
		}
	};
	
	private static IApnsService getApnsService() throws FileNotFoundException {
		if (apnsService == null) {
			ApnsConfig config = new ApnsConfig();
			FileInputStream fis = new FileInputStream("./certificate/iphone_one_dis.p12");
//			InputStream is = new InputStream(fis); //Apns4jDemo.class.getClassLoader().getResourceAsStream("./certificate/iphone_one_dis.p12");
			config.setKeyStore(fis);
			config.setDevEnv(false);
			config.setPassword("cantellow");
			config.setPoolSize(3);
			config.setRetries(1);
			config.setDelegate(delegate);
			// 假如需要在同个java进程里给不同APP发送通知，那就需要设置为不同的name
//			config.setName("welove1");
			apnsService = ApnsServiceImpl.createInstance(config);
		}
		return apnsService;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		IApnsService service = getApnsService();
		
		// send notification
		String token = "8ACCF90D4146D2649DE279EF877E65ACF9F09C710382F9975E00EB59E5E3253";//"f0b2ef3425f2f44b182405648d083a40a66909df7429e74b9545faf5870ccd71";
		
		Payload payload = new Payload();
		payload.setAlert("ZZ不爱说话回复了你");
		// If this property is absent, the badge is not changed. To remove the badge, set the value of this property to 0
		payload.setBadge(1);
		// set sound null, the music won't be played
//		payload.setSound(null);
//		payload.setSound("msg.mp3");
//		payload.addParam("uid", 123456);
//		payload.addParam("type", 12);
		service.sendNotification(token, payload);
		
//		// payload, use loc string
//		Payload payload2 = new Payload();
//		payload2.setBadge(1);
//		payload2.setAlertLocKey("GAME_PLAY_REQUEST_FORMAT");
//		payload2.setAlertLocArgs(new String[]{"Jenna", "Frank"});
//		service.sendNotification(token, payload2);
		
		// get feedback
//		List<Feedback> list = service.getFeedbacks();
//		if (list != null && list.size() > 0) {
//			for (Feedback feedback : list) {
//				System.out.println(feedback.getDate() + " " + feedback.getToken());
//			}
//		}
		
//		try {
//			Thread.sleep(5000);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		// It's a good habit to shutdown what you never use
//		service.shutdown();
		
//		System.exit(0);
	}
}
