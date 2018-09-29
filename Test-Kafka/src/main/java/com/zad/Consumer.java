package com.zad;

import kafka.utils.ShutdownableThread;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

/**
 * 描述:
 *
 * @author zad
 * @create 2018-09-28 22:12
 */
@Slf4j
public class Consumer extends ShutdownableThread {

    private KafkaConsumer<Integer, String> consumer;

    public Consumer() {
        super("KafkaConsumerTest", false);

        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaCommons.KAFKA_BROKER_LIST);
        // 消息所属的分组
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "DemoGroup1");
        // 消息是否自动提交
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        // 自动提交的时间间隔
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        // 设置使用最开始的offset偏移量为当前group.id的最早消息
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.IntegerDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");

        this.consumer = new KafkaConsumer<Integer, String>(props);
    }

    @Override
    public void doWork() {
        consumer.subscribe(Collections.singletonList(KafkaCommons.TOPIC));
        ConsumerRecords<Integer, String> records = consumer.poll(Duration.ofMillis(1000));

        for (ConsumerRecord record : records) {
            System.out.println("[" + record.partition() + "] receive message:[" + record.key() + "->" + record.value() + "],offset:" + record.offset());
        }
    }

    public static void main(String[] args) {
        Consumer consumer = new Consumer();
        consumer.start();
    }
}
