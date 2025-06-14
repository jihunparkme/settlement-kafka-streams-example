package com.example.settlement.kafka.streams.application

import com.example.settlement.kafka.streams.application.common.StreamMessage
import com.example.settlement.kafka.streams.application.domain.payment.Payment
import com.example.settlement.kafka.streams.application.domain.settlement.Base
import org.apache.kafka.streams.kstream.ValueMapper

class BaseMapper() : ValueMapper<StreamMessage<Payment>, Base> {
    override fun apply(payment: StreamMessage<Payment>): Base {
        return Base(
            paymentType = payment.data?.paymentType ?: throw IllegalArgumentException(),
            amount = payment.data.amount,
            payoutDate = payment.data.payoutDate,
            confirmDate = payment.data.confirmDate,
            merchantNumber = payment.data.merchantNumber ?: throw IllegalArgumentException(),
            paymentDate = payment.data.paymentDate,
            paymentActionType = payment.data.paymentActionType ?: throw IllegalArgumentException(),
            paymentMethodType = payment.data.paymentMethodType ?: throw IllegalArgumentException(),
        )
    }
}