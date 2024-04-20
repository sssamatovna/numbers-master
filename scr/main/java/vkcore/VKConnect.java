package vkcore;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.messages.Message;
import com.vk.api.sdk.queries.messages.MessagesGetLongPollHistoryQuery;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

/*
 * класс соединения с группой через токен и id
 * */
public class VKConnect {
	/* интерфейс взаимодействия с API через запросы */
	private VkApiClient vk;
	/* сама группа; актер позволяет отправлять через него сообения */
	private GroupActor actor;
	/* идентификатор сообщения с которого нужно начинать обрабатывать сообщения */
	private static int ts;
	private static int maxMsgId = -1;

	/*
	 * настраиваем соединение: инициализируем GroupActor с groupId и accessToken
	 * */
	public VKConnect() throws ClientException, ApiException {
		TransportClient transportClient = HttpTransportClient.getInstance();
		vk = new VkApiClient(transportClient);

		Properties prop = new Properties();
		int groupId;
		String accessToken;
		try {

			prop.load(new FileInputStream("src/main/resources/vkconfig.properties"));
			groupId = Integer.parseInt(prop.getProperty("groupId"));
			accessToken = prop.getProperty("accessToken");
			actor = new GroupActor(groupId, accessToken);
			ts = vk.messages().getLongPollServer(actor).execute().getTs();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Ошибка при загрузке файла конфигурации");
		}
	}

	public GroupActor getActor() {
		return actor;
	}

	public VkApiClient getVk() {
		return vk;
	}

	/*
	 * получение сообщений
	 * */
	public Message getMessage() throws ClientException, ApiException {
		MessagesGetLongPollHistoryQuery eventsQuery = vk.messages()
				.getLongPollHistory(actor)
				.ts(ts);

		if (maxMsgId > 0) {
			eventsQuery.maxMsgId(maxMsgId);
		}
		List<Message> messages = eventsQuery
				.execute()
				.getMessages()
				.getMessages();

		if (!messages.isEmpty()) {
			try {
				ts = vk.messages()
						.getLongPollServer(actor)
						.execute()
						.getTs();
			} catch (ClientException e) {
				e.printStackTrace();
			}
		}
		if (!messages.isEmpty() && !messages.get(0).isOut()) {
			/*
			 * максимально полученный Id, ограничение vk api.
			 * использоуется вместе с ts и maxMsgId, видимо, для проверки сервера на затупы.
			 * если все три переменные не обновляются долгое время, сервер, а конкретно, метод,
			 * выкидывает ошибку 10 (Internal server error).
			 * */
			int messageId = messages.get(0).getId();
			if (messageId > maxMsgId) {
				maxMsgId = messageId;
			}

			return messages.get(0);
		}
		return null;
	}
}