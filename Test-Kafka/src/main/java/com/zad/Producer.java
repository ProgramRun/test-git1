package com.zad;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.io.IOException;
import java.util.Properties;

/**
 * 描述:
 *
 * @author zad
 * @create 2018-09-28 21:55
 */
@Slf4j
public class Producer {

    private KafkaProducer<Integer, String> producer;

    public Producer() {
        Properties props = new Properties();
        props.put("bootstrap.servers", KafkaCommons.KAFKA_BROKER_LIST);
        props.put("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("client.id", "producerDemo");

        this.producer = new KafkaProducer<>(props);
    }

    public void send() {
        producer.send(new ProducerRecord<Integer, String>(KafkaCommons.TOPIC, 1, "message"), new Callback() {
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                System.out.println("message send to:[ " + recordMetadata.partition() + "],offset:[" + recordMetadata.offset() + "]");
            }
        });
    }

    public static void main(String[] args) throws IOException {
        Producer p = new Producer();
        p.send();
        System.in.read();
    }

}
