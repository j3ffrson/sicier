package org.fesc.sicier.services;

public interface NotificationService {

    void notificateArea(String area,Object payload);
    void notificateUser(String username,Object payload);

}
