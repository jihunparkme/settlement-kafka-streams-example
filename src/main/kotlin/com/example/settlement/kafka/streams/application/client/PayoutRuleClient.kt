package com.example.settlement.kafka.streams.application.client

import com.example.settlement.kafka.streams.application.common.PaymentActionType
import com.example.settlement.kafka.streams.application.common.PaymentMethodType
import com.example.settlement.kafka.streams.application.domain.rule.Rule
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID
import kotlin.random.Random

@Service
class PayoutRuleClient() {
    fun getPayoutDate(request: PayoutDateRequest): Rule? {
        val payoutDate = LocalDate.now().plusDays(Random.nextLong(2, 7))
        return getTestRule(payoutDate, request)
    }

    private fun getTestRule(
        payoutDate: LocalDate,
        request: PayoutDateRequest
    ): Rule = Rule(
        ruleId = UUID.randomUUID().toString(),
        payoutDate = payoutDate,
        confirmDate = payoutDate.minusDays(Random.nextLong(1, 2)),
        merchantNumber = request.merchantNumber,
        paymentDate = request.paymentDate,
        paymentActionType = request.paymentActionType,
        paymentMethodType = request.paymentMethodType,
    )
}

data class PayoutDateRequest(
    val merchantNumber: String,
    val paymentDate: LocalDateTime,
    val paymentActionType: PaymentActionType,
    val paymentMethodType: PaymentMethodType,
)