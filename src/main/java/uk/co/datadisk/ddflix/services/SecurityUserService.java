package uk.co.datadisk.ddflix.services;

public interface SecurityUserService {

    String validatePasswordResetToken(long id, String token);

}
