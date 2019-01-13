# groovy-dsl-apache-camel-message-queue-listener-no-xml-configuration
A simple Groovy project to listen to Apache Message Queue using Apache Camel DSL. There are no XML configuration.

## Project details:
The project uses the following:
1. Apache Message Queue
2. Apache Camel
3. Groovy

## What this project does?
If you are conceptually familiar with Message Queue and want to implement a Message Queue listener, then this sample code can help you to achieve the same. Do note that the Project uses Apache Camel router to listen to the Queue. Do read a bit more about Apache Camel to understand what it's capabilities are and where and when to use them. The sample code shows one approach of how to use the Apache Camel - to listen to the Queue. Apache Camel router is a powerful tool, and it supports a wide range of features. The project uses Groovy for the implementation. The concept of the project is simple - always listen to the Queue and print the message body. Do note that the Apache Camel uses XML configuration as well. In this project, I have used the Groovy DSL configuration and not XML configuration. If you intend to understand a simple PoC work on how to use the DSL way to configure the routes in Apache Camel, then this Project can help.