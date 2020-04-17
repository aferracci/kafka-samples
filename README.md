ISR = In Synch Replica

bin/zookeeper-server-start.sh config/zookeeperroperties
bin/kafka-server-start.sh config/server-0.properties



 bin/kafka-topics.sh \
  --create --topic replicated_topic \
  --zookeeper localhost:2181 \
  --replication-factor 3 \
  --partitions 1

bin/kafka-topics.sh \
  --zookeeper localhost:2181 \
  --alter --topic my-topic --partitions 4

bin/kafka-topics.sh \
  --describe \
  --topic replicated_topic \
  --zookeeper localhost:2181

bin/kafka-console-producer.sh \
  --broker-list localhost:9092 \
  --topic replicated_topic

bin/kafka-console-consumer.sh \
  --bootstrap-server localhost:9092 \
  --topic replicated_topic \
  --from-beginning

bin/kafka-producer-perf-test.sh \
  --topic my-topic \
  --num-record 50 \
  --record-size 1 \
  --throughput 10 \
  --producer-props bootstrap.servers=localhost:9092 key.serializer=org.apache.kafka.common.serialization.StringSerializer value.serializer=org.apache.kafka.common.serialization.StringSerializer
