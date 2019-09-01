package com.xm.webservice.client;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

public class MyAuthenticator extends Authenticator {

    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication("S001", "CCPDsap111".toCharArray());
       // return new PasswordAuthentication("S001","Passw0rd".toCharArray());
    }
}
