package com.learning.beer.order.service.sm;

import com.learning.beer.order.service.domain.BeerOrderEventEnum;
import com.learning.beer.order.service.domain.BeerOrderStatusEnum;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfig;
import org.springframework.statemachine.config.builders.*;
import org.springframework.statemachine.config.common.annotation.AnnotationBuilder;

import java.util.EnumSet;

@Configuration
@EnableStateMachineFactory
public class BeerOrderStateMachineConfig implements StateMachineConfigurer<BeerOrderStatusEnum, BeerOrderEventEnum> {

    @Override
    public void configure(StateMachineModelConfigurer<BeerOrderStatusEnum, BeerOrderEventEnum> model) throws Exception {

    }

    @Override
    public void configure(StateMachineConfigurationConfigurer<BeerOrderStatusEnum, BeerOrderEventEnum> config) throws Exception {

    }

    @Override
    public void configure(
            StateMachineStateConfigurer<BeerOrderStatusEnum, BeerOrderEventEnum> states)
            throws Exception {

        states
                .withStates()
                .initial(BeerOrderStatusEnum.NEW)
                .states(EnumSet.allOf(BeerOrderStatusEnum.class))
                .end(BeerOrderStatusEnum.PICKED_UP)
                .end(BeerOrderStatusEnum.DELIVERED)
                .end(BeerOrderStatusEnum.DELIVERY_EXCEPTION)
                .end(BeerOrderStatusEnum.VALIDATION_EXCEPTION)
                .end(BeerOrderStatusEnum.ALLOCATION_EXCEPTION);
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<BeerOrderStatusEnum, BeerOrderEventEnum> transitions) throws Exception {
        transitions.withExternal()
                .source(BeerOrderStatusEnum.NEW).target(BeerOrderStatusEnum.NEW)
                .source(BeerOrderStatusEnum.NEW).target(BeerOrderStatusEnum.VALIDATION_PENDING)
                .event(BeerOrderEventEnum.VALIDATE_ORDER)
                //todo add validation action here
                .and().withExternal()
                .source(BeerOrderStatusEnum.NEW).target(BeerOrderStatusEnum.VALIDATED)
                .event(BeerOrderEventEnum.VALIDATION_PASSED)
                .and().withExternal()
                .source(BeerOrderStatusEnum.NEW).target(BeerOrderStatusEnum.VALIDATION_EXCEPTION)
                .event(BeerOrderEventEnum.VALIDATION_FAILED);




    }

    @Override
    public void init(StateMachineConfigBuilder<BeerOrderStatusEnum, BeerOrderEventEnum> builder) throws Exception {

    }

    @Override
    public void configure(StateMachineConfigBuilder<BeerOrderStatusEnum, BeerOrderEventEnum> builder) throws Exception {

    }

    @Override
    public boolean isAssignable(AnnotationBuilder<StateMachineConfig<BeerOrderStatusEnum, BeerOrderEventEnum>> builder) {
        return false;
    }
}
