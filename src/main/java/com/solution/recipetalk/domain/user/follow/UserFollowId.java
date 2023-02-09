package com.solution.recipetalk.domain.user.follow;

import com.solution.recipetalk.domain.user.entity.UserDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserFollowId implements Serializable {
    private UserDetail user;
    private UserDetail following;
}
