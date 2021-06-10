package com.example.SportifyUserScores.config;

import com.example.SportifyUserScores.model.dto.MatchSelectionDto;
import com.example.SportifyUserScores.model.dto.broker.MatchResultMsgDto;
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
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.json.JsonToObjectTransformer;
import org.springframework.messaging.MessageChannel;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class InboundsIntegrationConfig {

    // rabbitmq queue
    private final String queueName = "finishedMatchesQueue";

    @Bean
    public AbstractMessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory) {
        var messageListenerContainer = new SimpleMessageListenerContainer(connectionFactory);
        messageListenerContainer.setQueueNames(queueName);
//        messageListenerContainer.setChannelTransacted(true);
//        messageListenerContainer.setTransactionManager(tr);
//        messageListenerContainer.setErrorHandler(new CustomErrorHandler());
        return messageListenerContainer;
    }

    @Bean
    public AmqpInboundChannelAdapter inboundChannelAdapter(AbstractMessageListenerContainer messageListenerContainer) {
        var adapter = new AmqpInboundChannelAdapter(messageListenerContainer);
        adapter.setOutputChannelName("fromRabbit");
        return adapter;
    }

    @Bean
    public MessageChannel fromRabbit() {
        return new DirectChannel();
    }

    @Bean
    @Transformer(inputChannel = "fromRabbit", outputChannel = "finishedMatchesChannel")
    public JsonToObjectTransformer jsonToObjectTransformer() {
        return new JsonToObjectTransformer(MatchResultMsgDto.class);
    }

    @Bean
    public MessageChannel finishedMatchesChannel() {
//        return new DirectChannel();
        return new ExecutorChannel(executor());
    }

    @Bean
    public ThreadPoolTaskExecutor executor() {
        ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
        pool.setCorePoolSize(10);
        pool.setMaxPoolSize(10);
        pool.setThreadNamePrefix("TaskExecutor-");
        pool.setWaitForTasksToCompleteOnShutdown(true);
        return pool;
    }


    @Bean
    Queue queue() {
        return new Queue(queueName);
    }
}
