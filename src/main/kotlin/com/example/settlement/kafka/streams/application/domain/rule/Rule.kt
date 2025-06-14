package com.example.settlement.kafka.streams.application.domain.rule

import com.example.settlement.kafka.streams.application.common.DEFAULT_PAYOUT_DATE
import com.example.settlement.kafka.streams.application.common.PaymentActionType
import com.example.settlement.kafka.streams.application.common.PaymentMethodType
import java.time.LocalDate
import java.time.LocalDateTime

data class Rule(
    val ruleId: String? = null,
    val payoutDate: LocalDate = DEFAULT_PAYOUT_DATE,
    val confirmDate: LocalDate = DEFAULT_PAYOUT_DATE,

    val merchantNumber: String? = null,
    val paymentDate: LocalDateTime = LocalDateTime.now(),
    val paymentActionType: PaymentActionType? = null,
    val paymentMethodType: PaymentMethodType? = null,
)