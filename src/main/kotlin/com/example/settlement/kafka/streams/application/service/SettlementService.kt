package kafkastreams.study.sample.settlement.service

import com.example.settlement.kafka.streams.common.logger
import kafkastreams.study.sample.settlement.common.StreamMessage
import kafkastreams.study.sample.settlement.domain.payment.Payment
import kafkastreams.study.sample.settlement.domain.settlement.Base
import org.springframework.stereotype.Service

@Service
class SettlementService {
    fun savePaymentMessageLog(data: StreamMessage<Payment>) {
        log.info(">>> [결제 메시지 로그 저장] Save payment message log to payment_log.. $data")
    }

    fun saveBase(data: Base) {
        log.info(">>> [정산 베이스 저장] Save base to base.. $data")
    }

    fun isSettlement(base: Base) = true

    companion object {
        private val log by logger()
    }
}