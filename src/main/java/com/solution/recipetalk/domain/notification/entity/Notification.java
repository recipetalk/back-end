package com.solution.recipetalk.domain.notification.entity;

import com.solution.recipetalk.domain.common.AuditingEntity;
import com.solution.recipetalk.domain.common.SoftDeleteEntity;
import com.solution.recipetalk.domain.notification.state.NotificationSort;
import com.solution.recipetalk.domain.notification.state.NotificationState;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import com.solution.recipetalk.dto.notification.NotificationDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Entity
@Table(name = "notification")
@Getter
@NoArgsConstructor
@SuperBuilder
@SQLDelete(sql = "UPDATE notification SET is_deleted = true WHERE notification_id = ?")
@Where(clause = "is_deleted = false")
public class Notification extends SoftDeleteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserDetail user;

    @Enumerated( value = EnumType.ORDINAL)
    @Column(name = "state")
    private NotificationState state;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "body", nullable = false)
    private String body;

    @Column(name = "navigation_id")
    private String navigationId;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "sort")
    private NotificationSort sort;

    public HashMap<String, String> navigationIdToHashmap() {
        if(navigationId == null){
            return null;
        }
        HashMap<String, String> data = new HashMap<>();
        String[] navigationSplit = navigationId.split("&");
        for (String s : navigationSplit) {
            String[] map = s.split("=");
            data.put(map[0], map[1]);
        }
        return data;
    }

    public static String toNavigationId(Map<String, String> navigations) {
        Iterator<Map.Entry<String, String>> iterator = navigations.entrySet().iterator();
        StringBuilder stringBuilder = new StringBuilder();
        while(iterator.hasNext()){
            Map.Entry<String, String> next = iterator.next();
            stringBuilder.append(next.getKey());
            stringBuilder.append("=");
            stringBuilder.append(next.getValue());
            if(iterator.hasNext()){
                stringBuilder.append("&");
            }
        }
        return stringBuilder.toString();
    }

    public NotificationDTO toDTO() {
        return NotificationDTO.builder()
                .id(id)
                .notificationSort(sort.toString())
                .createdDate(getCreatedDate())
                .navigations(navigationIdToHashmap())
                .isOpened(isOpened())
                .body(body)
                .title(title)
                .notificationSort(sort.toString())
                .build();
    }

    public Boolean isOpened() {
        return state == NotificationState.OPEN;
    }
}
