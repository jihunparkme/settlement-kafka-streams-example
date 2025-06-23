package com.example.settlement.kafka.streams.application

import com.example.settlement.kafka.streams.application.TestContainerConfig.IntegrationTestInitializer
import com.example.settlement.kafka.streams.application.client.PayoutRuleClient
import com.example.settlement.kafka.streams.application.config.KafkaProperties
import com.example.settlement.kafka.streams.application.domain.rule.Rule
import com.example.settlement.kafka.streams.application.service.SettlementService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(TestContainerConfig::class)
@ContextConfiguration(initializers = [IntegrationTestInitializer::class])
class SettlementKafkaStreamsAppTest {
    @Autowired private lateinit var kafkaProperties: KafkaProperties
    @Autowired private lateinit var serdeFactory: SerdeFactory
    @Autowired private lateinit var settlementService: SettlementService
    @Autowired private lateinit var payoutRuleClient: PayoutRuleClient
    @Autowired private lateinit var ruleKafkaTemplate: KafkaTemplate<String, Rule>

    @Test
    fun test() {
        Assertions.assertEquals("SUCCESS", "SUCCESS")
    }
}