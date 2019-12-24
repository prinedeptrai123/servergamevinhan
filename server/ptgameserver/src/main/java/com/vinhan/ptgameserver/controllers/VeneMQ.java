/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vinhan.ptgameserver.controllers;

import io.swagger.annotations.ApiOperation;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Qui
 */
@RestController
@RequestMapping("/api/venemq")
public class VeneMQ {

    String topic = "MQTT Examples";
    String content = "Message from MqttPublishSample";
    int qos = 2;
    String broker = "tcp://quicorp.tk:8888";
    String clientId = "qui";
    MemoryPersistence persistence = new MemoryPersistence();

    @ApiOperation(value = "Testing venemq")
    @GetMapping(value = "/test", produces = "application/json")
    public String connectToVenemq() {
        try {

            MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            System.out.println("Connecting to broker: " + broker);
            sampleClient.connect(connOpts);
            System.out.println("Connected");
            System.out.println("Publishing message: " + content);
            MqttMessage message = new MqttMessage(content.getBytes());
            message.setQos(qos);
            sampleClient.publish(topic, message);
            System.out.println("Message published");
            sampleClient.disconnect();
            System.out.println("Disconnected");
            System.exit(0);

        } catch (Exception e) {
            System.err.println(e);
        }
        return "Error";
    }
}
