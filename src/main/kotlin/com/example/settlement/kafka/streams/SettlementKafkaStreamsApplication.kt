package com.example.settlement.kafka.streams

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SettlementKafkaStreamsApplication

fun main(args: Array<String>) {
	runApplication<SettlementKafkaStreamsApplication>(*args)
}
