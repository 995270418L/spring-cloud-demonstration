package com.ybwx.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
public class BootstrapLogging {

    public static void logging(Environment environment){
        if(environment == null){
            return ;
        }
        try {
            InetAddress address = Inet4Address.getLocalHost();
            String ip = address.getHostAddress();
            String path = environment.getProperty("server.servlet.context-path") == null ? "" : environment.getProperty("server.servlet.context-path");
            String applicationName = environment.getProperty("spring.application.name");
            String port = environment.getProperty("server.port");
            String activeProfie = environment.getProperty("spring.profiles.active") == null ? "default" : environment.getProperty("spring.profiles.active") ;
            log.info("\n\n\n\t###########################\n\n\t{} application start\n\tURI: http://{}\n\tactive profie: {}\n\n\t###########################\n\n\n", applicationName,ip + ":" + port + path, activeProfie);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
