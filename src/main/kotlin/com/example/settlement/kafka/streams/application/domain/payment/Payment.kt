package com.example.settlement.kafka.streams.application.domain.payment

import com.example.settlement.kafka.streams.application.common.DEFAULT_PAYOUT_DATE
import com.example.settlement.kafka.streams.application.common.PaymentActionType
import com.example.settlement.kafka.streams.application.common.PaymentMethodType
import com.example.settlement.kafka.streams.application.common.PaymentType
import java.time.LocalDate
import java.time.LocalDateTime

data class Payment(
    val paymentType: PaymentType? = null,
    val amount: Long = 0L,
    var payoutDate: LocalDate = DEFAULT_PAYOUT_DATE,
    var confirmDate: LocalDate = DEFAULT_PAYOUT_DATE,

    val merchantNumber: String? = null,
    val paymentDate: LocalDateTime = LocalDateTime.now(),
    val paymentActionType: PaymentActionType? = null,
    val paymentMethodType: PaymentMethodType? = null,
)
