package com.example.settlement.kafka.streams.application

import org.jetbrains.annotations.NotNull
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.test.context.ActiveProfiles
import org.testcontainers.kafka.ConfluentKafkaContainer
import org.testcontainers.kafka.KafkaContainer
import org.testcontainers.utility.DockerImageName

@ActiveProfiles("test")
class TestContainerConfig : BeforeAllCallback {
    @Throws(Exception::class)
    override fun beforeAll(extensionContext: ExtensionContext?) {
        KAFKA_CONTAINER.start()
    }

    class IntegrationTestInitializer : ApplicationContextInitializer<ConfigurableApplicationContext?> {
        override fun initialize(@NotNull applicationContext: ConfigurableApplicationContext) {
            val properties: MutableMap<String?, String?> = HashMap()
            setKafkaProperties(properties)
            TestPropertyValues.of(properties).applyTo(applicationContext)
        }

        private fun setKafkaProperties(properties: MutableMap<String?, String?>) {
            properties.put("spring.kafka.bootstrap-servers", KAFKA_CONTAINER.getBootstrapServers())
        }
    }

    companion object {
        private val KAFKA_CONTAINER = ConfluentKafkaContainer("confluentinc/cp-kafka:7.4.0")
    }
}