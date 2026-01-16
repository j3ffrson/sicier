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
    public void notificateArea(Long areaId, Object payload) {
        messagingTemplate.convertAndSend(
                "/topic/area/"+areaId,
                payload
        );
    }

    @Override
    public void notificateUser(Long userId, Object payload) {
        messagingTemplate.convertAndSend(
                "/topic/user/"+userId,
                payload
        );
    }
}
