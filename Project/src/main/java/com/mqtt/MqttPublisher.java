package com.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MqttPublisher {

    public static void publishMessage(String message) {
        String topicName = "client_message";
        int qualityOfService = 0;
        String broker = "tcp://localhost:1883";
        String clientId = "G0gg0S";
        MemoryPersistence persistence = new MemoryPersistence();

        try {
            MqttClient mqttClient = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
            mqttConnectOptions.setCleanSession(true);
            System.out.println("Connecting to broker: " + broker + "...");
            mqttClient.connect(mqttConnectOptions);
            System.out.println("Connected to broker!");
            System.out.println("Publishing message: \"" + message + "\" under topic \\" + topicName);
            MqttMessage mqttMessage = new MqttMessage(message.getBytes());
            mqttMessage.setQos(qualityOfService);
            mqttClient.publish(topicName, mqttMessage);
            System.out.println("Message published");
            System.out.println("-----------------");
            mqttClient.disconnect();
            mqttClient.close();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}