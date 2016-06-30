package com.growthbeat.message.handler;

import com.growthbeat.message.MessageQueue;
import com.growthbeat.message.model.CardMessage;
import com.growthbeat.message.model.Message;
import com.growthbeat.message.view.MessageActivity;

import android.content.Context;
import android.content.Intent;

public class CardMessageHandler implements MessageHandler {

	private Context context;

	public CardMessageHandler(Context context) {
		this.context = context;
	}

	@Override
	public boolean handle(final MessageQueue messageJob) {

        Message message = messageJob.getMessage();
		if (message.getType() != Message.MessageType.card)
			return false;
		if (!(message instanceof CardMessage))
			return false;

		Intent intent = new Intent(context, MessageActivity.class);
		intent.putExtra("message", (CardMessage) message);
        intent.putExtra("uuid", messageJob.getUuid());
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);

		return true;

	}

}