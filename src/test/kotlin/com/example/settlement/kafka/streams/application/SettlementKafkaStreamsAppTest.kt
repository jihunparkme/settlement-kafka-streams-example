package com.example.settlement.kafka.streams.application

import com.example.settlement.kafka.streams.application.TestContainerConfig.IntegrationTestInitializer
import com.example.settlement.kafka.streams.application.client.PayoutRuleClient
import com.example.settlement.kafka.streams.application.config.KafkaProperties
import com.example.settlement.kafka.streams.application.domain.rule.Rule
import com.example.settlement.kafka.streams.application.service.SettlementService
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(TestContainerConfig::class)
@ContextConfiguration(initializers = [IntegrationTestInitializer::class])
class SettlementKafkaStreamsAppTest(
    private val kafkaProperties: KafkaProperties,
    private val serdeFactory: SerdeFactory,
    private val settlementService: SettlementService,
    private val payoutRuleClient: PayoutRuleClient,
    private val ruleKafkaTemplate: KafkaTemplate<String, Rule>,
) {
    @Test
    fun test() {
        println("SUCCESS")
    }
}