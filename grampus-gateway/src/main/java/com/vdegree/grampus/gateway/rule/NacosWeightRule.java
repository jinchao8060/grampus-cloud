//package com.vdegree.grampus.gateway.rule;
//
//import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
//import com.alibaba.cloud.nacos.ribbon.NacosServer;
//import com.alibaba.nacos.api.exception.NacosException;
//import com.alibaba.nacos.api.naming.pojo.Instance;
//import com.netflix.client.config.IClientConfig;
//import com.netflix.loadbalancer.AbstractLoadBalancerRule;
//import com.netflix.loadbalancer.DynamicServerListLoadBalancer;
//import com.netflix.loadbalancer.Server;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//
///**
// * 权重负载均衡
// *
// * @author Beck
// * @since 2021-06-10
// */
//@Slf4j
//@AllArgsConstructor
//public class NacosWeightRule extends AbstractLoadBalancerRule {
//    private final NacosDiscoveryProperties discoveryProperties;
//
//    @Override
//    public Server choose(Object key) {
//        DynamicServerListLoadBalancer loadBalancer = (DynamicServerListLoadBalancer) getLoadBalancer();
//        String name = loadBalancer.getName();
//        try {
//            Instance instance = discoveryProperties.namingServiceInstance()
//                    .selectOneHealthyInstance(name);
//            log.info("chose instance = {}", instance);
//            return new NacosServer(instance);
//        } catch (NacosException e) {
//            log.error("NacosWeightLoadBalancerRule.choose Exception", e);
//            return null;
//        }
//    }
//
//    @Override
//    public void initWithNiwsConfig(IClientConfig iClientConfig) {
//    }
//}
