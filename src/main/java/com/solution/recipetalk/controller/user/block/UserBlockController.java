package com.solution.recipetalk.controller.user.block;


import com.solution.recipetalk.dto.user.UserBlockRegisterDTO;
import com.solution.recipetalk.dto.user.UserBlockRemoveDTO;
import com.solution.recipetalk.service.user.block.FindUserBlockService;
import com.solution.recipetalk.service.user.block.RegisterUserBlockService;
import com.solution.recipetalk.service.user.block.RemoveUserBlockService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserBlockController {

    private final RegisterUserBlockService registerUserBlockService;
    private final RemoveUserBlockService removeUserBlockService;
    private final FindUserBlockService findUserBlockService;

    @GetMapping("/block")
    public ResponseEntity<?> userBlockList(Pageable pageable){
        return findUserBlockService.findUserBlockUserList(pageable);
    }

    @PostMapping("/block")
    public ResponseEntity<?> userBlockAdd(@RequestBody UserBlockRegisterDTO dto) {
        return registerUserBlockService.addUserBlock(dto);
    }

    @DeleteMapping("/block/{blockedUsername}")
    public ResponseEntity<?> userBlockRemove(@PathVariable(value = "blockedUsername") String blockedUsername){
        return removeUserBlockService.removeUserBlock(blockedUsername);
    }

}
