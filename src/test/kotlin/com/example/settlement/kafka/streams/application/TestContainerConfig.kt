package com.example.settlement.kafka.streams.application

import org.junit.jupiter.api.extension.AfterAllCallback
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.support.TestPropertySourceUtils
import org.testcontainers.kafka.ConfluentKafkaContainer

@ActiveProfiles("test")
class TestContainerConfig : BeforeAllCallback, AfterAllCallback {

    companion object {
        val kafkaContainer: ConfluentKafkaContainer by lazy {
            println("TestContainerConfig: Kafka 컨테이너 초기화 및 시작 중...")
            ConfluentKafkaContainer("confluentinc/cp-kafka:7.4.0")
                .apply {
                    start()
                }
        }
    }

    override fun beforeAll(context: ExtensionContext?) {
        if (!kafkaContainer.isRunning) {
            println("TestContainerConfig: beforeAll에서 Kafka 컨테이너가 실행 중이 아니어서 시작합니다.")
            kafkaContainer.start()
        }
        println("TestContainerConfig: Kafka 컨테이너 실행 확인. Bootstrap Servers: ${kafkaContainer.bootstrapServers}")
    }

    override fun afterAll(context: ExtensionContext?) {
        println("TestContainerConfig: afterAll에서 Kafka 컨테이너 중지 시도 중...")
        if (kafkaContainer.isRunning) {
            kafkaContainer.stop()
            println("TestContainerConfig: Kafka 컨테이너가 성공적으로 중지되었습니다.")
        } else {
            println("TestContainerConfig: Kafka 컨테이너가 실행 중이 아니므로 중지 작업을 건너뜁니다.")
        }
    }

    class IntegrationTestInitializer : ApplicationContextInitializer<ConfigurableApplicationContext> {
        override fun initialize(applicationContext: ConfigurableApplicationContext) {
            println("TestContainerConfig.IntegrationTestInitializer: Kafka 컨테이너 속성 설정 중...")
            TestPropertySourceUtils.addInlinedPropertiesToEnvironment(
                applicationContext,
                "kafka.streams.bootstrap-servers=${kafkaContainer.bootstrapServers}"
            )
            println("TestContainerConfig.IntegrationTestInitializer: 'kafka.streams.bootstrap-servers' 설정 완료: ${kafkaContainer.bootstrapServers}")
        }
    }
}