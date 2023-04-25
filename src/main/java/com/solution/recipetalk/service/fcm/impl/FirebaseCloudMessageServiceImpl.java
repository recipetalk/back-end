package com.solution.recipetalk.service.fcm.impl;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.*;
import com.solution.recipetalk.service.fcm.FirebaseCloudMessageService;
import jakarta.annotation.PostConstruct;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@NoArgsConstructor
public class FirebaseCloudMessageServiceImpl implements FirebaseCloudMessageService {
    @Value("${fcm.secret-key-dir}") private String SECRET_KEY_DIR;
    @Value("${fcm.scope}") private String FIREBASE_SCOPE;

    @PostConstruct
    public void init() {
        try{
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(
                            GoogleCredentials.fromStream(new ClassPathResource(SECRET_KEY_DIR).getInputStream())
                                    .createScoped(List.of(FIREBASE_SCOPE)))
                    .build();
            if(FirebaseApp.getApps().isEmpty()){
                FirebaseApp.initializeApp(options);
                log.info("Firebase application has been initialized");
            }
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public String sendMessageTo(Long notificationId, String targetToken, String title, String body) throws FirebaseMessagingException {

        Message msg = Message.builder()
                .putData("time", LocalDateTime.now().toString())
                .putData("notification_id", notificationId != null ? notificationId.toString() : "null")
                .setToken(targetToken)
                .setApnsConfig(ApnsConfig.builder().setAps(Aps.builder().setContentAvailable(true).build()).putHeader("apns-priority","10").build())
//                .setNotification(Notification.builder()
//                        .setBody(body).setTitle(title)
//                        .build())
                .setAndroidConfig(AndroidConfig.builder().setPriority(AndroidConfig.Priority.HIGH).build())
                .build();

        return sendMessageTo(msg);
    }

    @Override
    public String sendMessageTo(Message message) throws FirebaseMessagingException {

        return FirebaseMessaging.getInstance().send(message);
    }
}

    //    @Override
//    public ResponseEntity<?> sendMessageTo(String targetToken, String title, String body) {
//
//
//        try {
//            String message = makeMessage(targetToken,title,body);
//            return v1Forward(message);
//        } catch (IOException | URISyntaxException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//
//    private String makeMessage(String targetToken, String title, String body) throws JsonProcessingException {
//        FcmMessage fcmMessage = FcmMessage.builder()
//                .message(FcmMessage.Message.builder()
//                        .token(targetToken)
//                        .notification(FcmMessage.Notification.builder()
//                                .title(title)
//                                .body(body)
//                                .build()
//                        )
//                        .build()
//                )
//                .validate_only(false)
//                .build();
//
//        return objectMapper.writeValueAsString(fcmMessage);
//    }
//
//    private ResponseEntity<?> v1Forward(String message) throws IOException, URISyntaxException {
//        HttpHeaders headers = setHeader();
//
//        HttpEntity<String> req = new HttpEntity<>(message, headers);
//
//        RestTemplate restTemplate = new RestTemplate();
//
//        return restTemplate.exchange(new URI(V1_URL), HttpMethod.POST, req, String.class);
//    }
//
//    private HttpHeaders setHeader() throws IOException {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + getAccessToken());
//        headers.set("project_id", PROJECT_ID);
//        return headers;
//    }
//
//    private String getAccessToken() throws IOException {
//        // credentials가 만료되었거나 만료임박의 경우 refresh
//        googleCredentials.refreshIfExpired();
//
//        return googleCredentials.getAccessToken().getTokenValue();
//    }


