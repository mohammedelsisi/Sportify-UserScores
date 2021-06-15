package com.example.SportifyUserScores.config;

import com.example.SportifyUserScores.model.dto.broker.MatchResultMsgDto;
import com.example.SportifyUserScores.model.dto.broker.NewUserMessage;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.AbstractMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.amqp.inbound.AmqpInboundChannelAdapter;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.ExecutorChannel;
import org.springframework.integration.json.JsonToObjectTransformer;
import org.springframework.messaging.MessageChannel;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
@Configuration
public class InboundNewUsersConfig {

    private static final String QUEUE_NAME = "NewRegisteredUsers";

    @Bean
    public AbstractMessageListenerContainer newUsersMessageListener(ConnectionFactory connectionFactory) {
        var messageListenerContainer = new SimpleMessageListenerContainer(connectionFactory);
        messageListenerContainer.setQueueNames(QUEUE_NAME);
        return messageListenerContainer;
    }

    @Bean
    public AmqpInboundChannelAdapter newUsersChannelAdapter(AbstractMessageListenerContainer newUsersMessageListener) {
        var adapter = new AmqpInboundChannelAdapter(newUsersMessageListener);
        adapter.setOutputChannelName("NewUsersFromMQChannel");
        return adapter;
    }

    @Bean
    public MessageChannel NewUsersFromMQChannel() {
        return new DirectChannel();
    }

    @Bean
    @Transformer(inputChannel = "NewUsersFromMQChannel", outputChannel = "newUsersMessageChannel")
    public JsonToObjectTransformer jsonToObjectTransformerForNewUsers() {
        return new JsonToObjectTransformer(NewUserMessage.class);
    }

    @Bean
    public MessageChannel newUsersMessageChannel() {
        return new DirectChannel();
    }


    @Bean
    Queue NewRegisteredUsers() {
        return new Queue(QUEUE_NAME);
    }
}
