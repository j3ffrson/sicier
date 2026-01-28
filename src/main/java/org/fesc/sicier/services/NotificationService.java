package org.fesc.sicier.services;

public interface NotificationService {

    void notificateArea(Long areaId,Object payload);
    void notificateUser(String username,Object payload);

}
