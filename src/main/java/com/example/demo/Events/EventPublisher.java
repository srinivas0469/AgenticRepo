package event;

import com.example.demo.Events.AgentEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class EventPublisher {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publish(String contextId, String nodeId, String message) {
        AgentEvent event = new AgentEvent(contextId, nodeId, message);
        applicationEventPublisher.publishEvent(event);
    }
}
