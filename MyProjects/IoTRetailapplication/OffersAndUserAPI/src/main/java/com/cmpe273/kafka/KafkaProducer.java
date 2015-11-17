package com.cmpe273.kafka;

import java.util.Properties;

import org.springframework.stereotype.Component;

import com.cmpe273.model.MailRequest;

import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import kafka.javaapi.producer.Producer;

@Component
public class KafkaProducer 
{
    private final Producer<Integer, String> producer;
    private final String topic;
    private final Properties props = new Properties();

    
    public KafkaProducer()
    {
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("metadata.broker.list", "localhost:9092");
        // Use random partitioner. Don't need the key type. Just set it to Integer.
        // The message is of type String.
        producer = new kafka.javaapi.producer.Producer<Integer, String>(new ProducerConfig(props));
        this.topic = "test";
    }

    public void sendMessage(MailRequest mailRequest){
    	String message=mailRequest.getMailId()+";"+mailRequest.getMessage();
        producer.send(new KeyedMessage<Integer, String>(topic, message));
    }

    

}
