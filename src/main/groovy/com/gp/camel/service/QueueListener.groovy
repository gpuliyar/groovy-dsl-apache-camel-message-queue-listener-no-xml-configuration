package com.gp.camel.service

import groovy.util.logging.Slf4j
import org.apache.camel.Exchange
import org.apache.camel.Handler

@Slf4j
class QueueListener {

    @Handler
    void handleMessage(Exchange exchange) {
        log.info "handling the pdf queue exchange message"
        println("recieved message : ${exchange.in.body}")
    }
}
