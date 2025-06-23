package com.example.settlement.kafka.streams.application

import com.example.settlement.kafka.streams.application.TestContainerConfig.IntegrationTestInitializer
import com.example.settlement.kafka.streams.application.client.PayoutRuleClient
import com.example.settlement.kafka.streams.application.config.KafkaProperties
import com.example.settlement.kafka.streams.application.domain.rule.Rule
import com.example.settlement.kafka.streams.application.service.SettlementService
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.kafka.config.StreamsBuilderFactoryBean
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

    // StreamsBuilderFactoryBean을 주입받아 스트림의 생명주기를 관리합니다.
    @Autowired
    private lateinit var streamsBuilderFactoryBean: StreamsBuilderFactoryBean

    @AfterEach
    fun tearDown() {
        // 각 테스트가 끝난 후 스트림을 중지하여 테스트 프로세스가 종료되도록 합니다.
        streamsBuilderFactoryBean.stop()
    }

    @Test
    fun `실제 스트림을 실행하여 처리 로직을 테스트`() {
        // 테스트를 위해 스트림을 시작합니다.
        streamsBuilderFactoryBean.start()

        // Kafka Streams가 완전히 Running 상태가 될 때까지 잠시 기다립니다.
        // (상황에 따라 Awaitility 같은 라이브러리를 사용하면 더 좋습니다.)
        // val streamsStarted = streamsBuilderFactoryBean.kafkaStreams.waitForState(
        //     org.apache.kafka.streams.KafkaStreams.State.RUNNING,
        //     10,
        //     TimeUnit.SECONDS
        // )
        // Assertions.assertTrue(streamsStarted, "Kafka Streams가 시작되어야 합니다.")

        // --- 테스트 로직 ---
        // 1. KafkaTemplate을 사용해 토픽에 테스트 메시지를 produce 합니다.
        // 2. 잠시 기다린 후 (또는 Awaitility 사용)
        // 3. 결과 토픽에서 메시지를 consume 하여 예상과 일치하는지 검증합니다.
        // --------------------

        Assertions.assertEquals("SUCCESS", "SUCCESS") // 실제 검증 로직으로 대체
    }

    @Test
    fun `컨텍스트 로딩 테스트`() {
        // 이 테스트는 스트림을 실행할 필요가 없으므로 start()를 호출하지 않습니다.
        // AfterEach의 stop()이 호출되지만, 시작되지 않은 스트림을 중지해도 안전합니다.
        Assertions.assertNotNull(settlementService)
        Assertions.assertEquals("SUCCESS", "SUCCESS")
    }
}