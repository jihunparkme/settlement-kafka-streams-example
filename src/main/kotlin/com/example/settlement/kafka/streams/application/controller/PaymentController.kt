package com.example.settlement.kafka.streams.application.controller

import com.example.settlement.kafka.streams.common.BasicResponse
import com.example.settlement.kafka.streams.common.Result
import com.example.settlement.kafka.streams.application.service.PaymentService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/payment")
class PaymentController(
    private val paymentService: PaymentService,
) {
    @GetMapping("/send/{count}")
    fun sendToTopic(
        @PathVariable count: Int = 100
    ): ResponseEntity<BasicResponse<Result>> {
        paymentService.sendToTopic(count)
        return BasicResponse.ok(Result.SUCCESS)
    }

    @GetMapping("/send/{count}/test")
    fun sendToTopicTest(
        @PathVariable count: Int = 1
    ): ResponseEntity<BasicResponse<Result>> {
        paymentService.sendToTopicTest(count)
        return BasicResponse.ok(Result.SUCCESS)
    }
}

