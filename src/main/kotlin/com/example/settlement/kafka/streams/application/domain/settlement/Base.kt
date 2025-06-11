package kafkastreams.study.sample.settlement.domain.settlement

import kafkastreams.study.sample.settlement.common.DEFAULT_PAYOUT_DATE
import kafkastreams.study.sample.settlement.common.PaymentActionType
import kafkastreams.study.sample.settlement.common.PaymentMethodType
import kafkastreams.study.sample.settlement.common.PaymentType
import kafkastreams.study.sample.settlement.domain.aggregation.BaseAggregationKey
import kafkastreams.study.sample.settlement.domain.rule.Rule
import java.time.LocalDate
import java.time.LocalDateTime

data class Base(
    val paymentType: PaymentType? = null,
    val amount: Long = 0L,
    var payoutDate: LocalDate = DEFAULT_PAYOUT_DATE,
    var confirmDate: LocalDate = DEFAULT_PAYOUT_DATE,

    // TODO: val
    var merchantNumber: String? = null,
    var paymentDate: LocalDateTime = LocalDateTime.now(),
    var paymentActionType: PaymentActionType? = null,
    var paymentMethodType: PaymentMethodType? = null,
) {
    fun updatePayoutDate(rule: Rule) {
        this.payoutDate = rule.payoutDate
        this.confirmDate = rule.confirmDate
    }

    fun updateDefaultPayoutDate() {
        this.payoutDate = DEFAULT_PAYOUT_DATE
        this.confirmDate = DEFAULT_PAYOUT_DATE
    }

    fun toAggregationKey() =
        BaseAggregationKey(
            merchantNumber = this.merchantNumber,
            paymentDateDaily = this.paymentDate.toLocalDate(),
            paymentActionType = this.paymentActionType,
            paymentMethodType = this.paymentMethodType
        )
}