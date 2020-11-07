package num.diplom.medicalsystem.event;

import num.diplom.base.event.model.UserCreateEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EventDispatcher {

    private final RabbitTemplate rabbitTemplate;
    private final String userExchange;
    private final String userCreatedRoutingKey;

    @Autowired
    EventDispatcher(
            final RabbitTemplate rabbitTemplate,
            @Value("${ms.base.user.exchange}") final String userExchange,
            @Value("${ms.base.user.created.key}") final String userCreatedRoutingKey
    ) {
        this.rabbitTemplate = rabbitTemplate;
        this.userExchange = userExchange;
        this.userCreatedRoutingKey = userCreatedRoutingKey;
    }

    public void send(final UserCreateEvent userCreateEvent) {
        rabbitTemplate.convertAndSend(
                userExchange,
                userCreatedRoutingKey,
                userCreateEvent
        );
    }
}
