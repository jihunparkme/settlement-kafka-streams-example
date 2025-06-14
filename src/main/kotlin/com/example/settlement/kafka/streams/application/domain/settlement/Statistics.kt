package com.example.settlement.kafka.streams.application.domain.settlement

import com.example.settlement.kafka.streams.application.common.PaymentActionType
import com.example.settlement.kafka.streams.application.common.PaymentMethodType
import com.example.settlement.kafka.streams.application.common.PaymentType
import java.time.LocalDate
import java.time.LocalDateTime

data class Statistics(
    val count: Long,
    val totalAmount: Long,
    val confirmDate: LocalDate,
    val payoutDate: LocalDate,
    val paymentType: PaymentType,

    val merchantNumber: String,
    val paymentDate: LocalDateTime,
    val paymentActionType: PaymentActionType,
    val paymentMethodType: PaymentMethodType,
)