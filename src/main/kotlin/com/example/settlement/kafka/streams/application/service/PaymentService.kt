package com.example.settlement.kafka.streams.application.service

import com.example.settlement.kafka.streams.common.randomEnum
import com.example.settlement.kafka.streams.application.common.PaymentActionType
import com.example.settlement.kafka.streams.application.common.PaymentMethodType
import com.example.settlement.kafka.streams.application.common.PaymentType
import com.example.settlement.kafka.streams.application.common.StreamMessage
import com.example.settlement.kafka.streams.application.common.Type
import com.example.settlement.kafka.streams.application.config.KafkaProperties
import com.example.settlement.kafka.streams.application.domain.payment.Payment
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID
import kotlin.random.Random

@Service
class PaymentService(
    private val paymentKafkaTemplate: KafkaTemplate<String, StreamMessage<Payment>>,
    private val kafkaProperties: KafkaProperties,
) {
    fun sendToTopic(count: Int) {
        val paymentType = randomEnum<PaymentType>()
        sendPayment(count, paymentType)
        sendFinishMessage(paymentType)
    }

    private fun sendPayment(count: Int, paymentType: PaymentType) {
        repeat(count) {
            val payment = Payment(
                paymentType = paymentType,
                amount = Random.nextLong(1000L, 1000000L),
                payoutDate = LocalDate.now().plusDays(2),
                confirmDate = LocalDate.now().plusDays(2),
                merchantNumber = "merchant-${Random.nextInt(1000, 9999)}",
                paymentDate = LocalDateTime.now(),
                paymentActionType = randomEnum<PaymentActionType>(),
                paymentMethodType = randomEnum<PaymentMethodType>(),
            )
            paymentKafkaTemplate.send(
                kafkaProperties.paymentTopic,
                UUID.randomUUID().toString(),
                StreamMessage(
                    action = Type.PAYMENT,
                    channel = paymentType,
                    data = payment,
                )
            )
        }
    }

    private fun sendFinishMessage(paymentType: PaymentType) {
        repeat(kafkaProperties.partition) {
            paymentKafkaTemplate.send(
                kafkaProperties.paymentTopic,
                it,
                UUID.randomUUID().toString(),
                StreamMessage(
                    action = Type.FINISH,
                    channel = paymentType,
                )
            )
        }
    }

    fun sendToTopicTest(count: Int) {
        val paymentType = randomEnum<PaymentType>()
        sendPayment(count, paymentType)
    }
}
