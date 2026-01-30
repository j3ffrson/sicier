package org.fesc.sicier.services.implementations;

import lombok.RequiredArgsConstructor;
import org.fesc.sicier.services.NotificationService;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final SimpMessagingTemplate messagingTemplate;

    @Override
    public void notificateArea(String area, Object payload) {
        messagingTemplate.convertAndSend(
                "/topic/area/"+area,
                payload
        );
    }

    @Override
    public void notificateUser(String username, Object payload) {
        messagingTemplate.convertAndSend(
                "/queue/user/"+username,
                payload
        );
    }
}
