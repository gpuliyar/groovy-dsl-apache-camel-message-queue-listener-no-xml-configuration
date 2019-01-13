package com.gp.camel.service

import groovy.util.logging.Slf4j
import org.apache.activemq.ActiveMQConnectionFactory
import org.apache.camel.builder.RouteBuilder
import org.apache.camel.component.jms.JmsComponent
import org.apache.camel.impl.DefaultCamelContext
import org.apache.camel.impl.SimpleRegistry

import static java.lang.Boolean.TRUE
import static org.apache.camel.ExchangePattern.InOut

@Slf4j
class CamelService {

    static void main(String... args) {
        log.info "start the main process"
        new CamelService().start()
    }

    def start = { ->
        log.info "start the Camel Service"
        def (brokerUrl, queueUri) = ['tcp://0.0.0.0:61616', 'jms:queue:poc.camel.service']
        log.info "broker url : $brokerUrl - queue uri : $queueUri"
        def jmsComponent = new JmsComponent(connectionFactory: new ActiveMQConnectionFactory(brokerURL: brokerUrl),
                useMessageIDAsCorrelationID: TRUE)
        def camelContext = new DefaultCamelContext(new SimpleRegistry([jms: jmsComponent]))

        camelContext.addRoutes(new RouteBuilder() {
            void configure() {
                from(queueUri).setExchangePattern(InOut).bean(QueueListener.class)
            }
        })
        camelContext.start()

        Runtime.runtime.addShutdownHook({ ->
            log.info "calling the shutdown hook"
            camelContext.stop()
        })

        synchronized (this) {
            this.wait()
        }
    }
}
