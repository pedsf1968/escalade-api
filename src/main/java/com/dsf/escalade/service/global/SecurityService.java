package com.dsf.escalade.service.global;

public interface SecurityService {
   String findLoggedInUsername();

   void autoLogin(String alias, String motDePasse);

}