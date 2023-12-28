package io.jabrains.isthesiteup.controllers;

import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class UrlCheckcontroller {

    private final String SITE_UP="Site is up";
    private final String SITE_DOWN="Site is down";
    private final String INCORRECT_URL="URL is incorrect!";
    
    @GetMapping("/check")
    public String gerURLStatusMessage(@RequestParam String url) {
        String returnMessage="";
        try {
            URL urlobj=new URL(url);
            HttpURLConnection connection =(HttpURLConnection) urlobj.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int responseCode=connection.getResponseCode()/100;
            // returnMessage=SITE_UP;
            if (responseCode!=3 && responseCode!=2) {
                returnMessage=SITE_DOWN;
                
            }
            else{
                returnMessage=SITE_UP;
            }
        } catch (MalformedURLException e) {
            returnMessage=INCORRECT_URL;
        } catch (IOException e) {
             returnMessage=SITE_DOWN;
        }
        return returnMessage;
    }


}
