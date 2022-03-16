//package com.oceancloud.grampus.admin.modules.testtx.controller;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.core.ExchangeTypes;
//import org.springframework.amqp.rabbit.annotation.Exchange;
//import org.springframework.amqp.rabbit.annotation.Queue;
//import org.springframework.amqp.rabbit.annotation.QueueBinding;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
///**
// * TestRabbitListener
// *
// * @author Beck
// * @since 2022-03-02
// */
//@Slf4j
//@Component
//public class TestRabbitListener {
//
//	@RabbitListener(bindings = @QueueBinding(
//			exchange = @Exchange(value = "direct.exchange"),
//			value = @Queue(value = "queue.direct.1"),
//			key = "queue.direct.1"))
//	public void direct1(List<TestDTO> content) {
//		log.info("content1:{}", content);
//	}
//
//	@RabbitListener(queuesToDeclare = @Queue(value = "queue.direct.2"), ackMode = "MANUAL")
//	public void direct2(String content) {
//		log.info("content2:{}", content);
//	}
//
//	@RabbitListener(bindings = @QueueBinding(
//			exchange = @Exchange(value = "test.topic", type = ExchangeTypes.TOPIC),
//			value = @Queue(value = "queue.topic.2"),
//			key = "key.*"), ackMode = "MANUAL")
//	public void topic1(String content) {
//		log.info("content3:{}", content);
//	}
//
//	@RabbitListener(bindings = @QueueBinding(
//			exchange = @Exchange(value = "test.fanout", type = ExchangeTypes.FANOUT),
//			value = @Queue(value = "queue.fanout.3"),
//			key = "key.r3"), ackMode = "MANUAL")
//	public void fanout1(String content) {
//		log.info("content4:{}", content);
//	}
//
//	@RabbitListener(bindings = @QueueBinding(
//			exchange = @Exchange(value = "test.fanout", type = ExchangeTypes.FANOUT),
//			value = @Queue(value = "queue.fanout.4"),
//			key = "key.r4"), ackMode = "MANUAL")
//	public void fanout2(String content) {
//		log.info("content5:{}", content);
//	}
//}
