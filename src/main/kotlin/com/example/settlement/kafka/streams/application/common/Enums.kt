package com.example.settlement.kafka.streams.application.common

enum class PaymentType {
    ONLINE,
    OFFLINE,
}

enum class PaymentMethodType {
    CARD,
    MONEY,
    PAY,
    POINT,
}

enum class PaymentActionType {
    PAYMENT,
    CANCEL,
    PARTIAL_CANCEL,
    FULL_CANCEL,
}

enum class Type {
    FINISH,
    PAYMENT,
}