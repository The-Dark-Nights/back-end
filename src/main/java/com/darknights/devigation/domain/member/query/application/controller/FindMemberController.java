package com.darknights.devigation.domain.member.query.application.controller;

import com.darknights.devigation.domain.member.query.application.dto.FindMemberDTO;
import com.darknights.devigation.domain.member.query.application.service.FindMemberService;
import com.darknights.devigation.global.common.annotation.CurrentMember;
import com.darknights.devigation.global.security.token.UserPrincipal;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "MemberInfo", description = "유저 정보 관련 API")
@RestController
@RequestMapping("/v1/member")
public class FindMemberController {

    private final FindMemberService findMemberService;

    @Autowired
    public FindMemberController(FindMemberService findMemberService) {
        this.findMemberService = findMemberService;
    }

    @Operation(
            summary = "유저 정보 조회",
            description = "회원의 정보를 확인합니다."
    )
    // response 정보
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = FindMemberDTO.class))}),
    })

    @GetMapping
    public ResponseEntity<FindMemberDTO> getMember(@CurrentMember UserPrincipal userPrincipal) {
        Long memberId = userPrincipal.getId();

        FindMemberDTO findMember = findMemberService.findById(memberId);

        return ResponseEntity.ok()
                .body(findMember);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FindMemberDTO> getOtherMember(@PathVariable Long id) {
        FindMemberDTO findMember = findMemberService.findById(id);

        return ResponseEntity.ok()
                .body(findMember);
    }
}
