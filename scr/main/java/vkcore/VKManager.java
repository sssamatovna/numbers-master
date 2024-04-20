package vkcore;

import com.vk.api.sdk.objects.users.UserXtrCounters;
import com.vk.api.sdk.queries.messages.MessagesSendQuery;

/*
 * здесь бот отвечает конкретному пользователю
 * */
public class VKManager {
	public static VKConnect vkConnect;

	static {
		try {
			vkConnect = new VKConnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendMessage(String msg, int peerId) {
		if (msg == null) {
			System.out.println("null");
			return;
		}

		try {
			vkConnect.getVk().messages().send(vkConnect.getActor()).peerId(peerId).message(msg).execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public MessagesSendQuery getSendQuery() {
		return vkConnect.getVk().messages().send(vkConnect.getActor());
	}

	public static UserXtrCounters getUserInfo(int id) {
		try {
			return vkConnect.getVk().users()
					.get(vkConnect.getActor())
					.userIds(String.valueOf(id))
					.execute()
					.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}