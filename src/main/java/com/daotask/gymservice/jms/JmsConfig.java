package com.epam.task.gymtasksecurity.jms;

import com.epam.task.gymtasksecurity.model.Trainee;
import com.epam.task.gymtasksecurity.model.User;
import jakarta.jms.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MarshallingMessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@Slf4j
public class JmsConfig {

    private static final Logger LOG = LoggerFactory.getLogger(JmsConfig.class);

    // @Bean
    public MessageConverter jacksonJmsConverter(){
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }
    @Bean
    public MessageConverter xmlMarshallingMessageConverter(){
        MarshallingMessageConverter converter = new MarshallingMessageConverter();
        converter.setTargetType(MessageType.TEXT);
        return converter;
    }
    @Bean
    public XStreamMarshaller xStreamMarshaller(){
        XStreamMarshaller marshaller = new XStreamMarshaller();
        marshaller.setSupportedClasses(User.class, Trainee.class, TraineeWorkload.class);
        return marshaller;
    }
    @Bean
    public ActiveMQConnectionFactory connectionFactory(){
        return new ActiveMQConnectionFactory();
    }
    @Bean
    public DefaultJmsListenerContainerFactory defaultJmsListenerContainerFactory(){
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory((ConnectionFactory) connectionFactory());
        // factory.setMessageConverter(jacksonJmsConverter());
        factory.setMessageConverter(xmlMarshallingMessageConverter());
        factory.setErrorHandler(
                t -> LOG.info("Handling error. Listener encountered problem at: "+t.getMessage())
        );
        return factory;
    }
    @Bean
    public PlatformTransactionManager jmsTransactionManager(){
        return new JmsTransactionManager();
    }
    @Bean JmsTemplate jmsTemplate(){
        JmsTemplate jmsTemplate = new JmsTemplate((ConnectionFactory) connectionFactory());
        jmsTemplate.setMessageConverter(jacksonJmsConverter());
        // ensuring persistence
        jmsTemplate.setDeliveryPersistent(true);
        jmsTemplate.setSessionTransacted(true);
        return jmsTemplate;
    }
}
