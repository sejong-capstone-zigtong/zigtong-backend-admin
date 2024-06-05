package capstone.zigtong.adminserver.domain.skill.controller;

import capstone.zigtong.adminserver.domain.skill.dto.SkillCategoryDto;
import capstone.zigtong.adminserver.domain.skill.service.SkillService;
import capstone.zigtong.adminserver.global.security.constant.EndpointConstant;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(EndpointConstant.ENDPOINT_PREFIX + "/admins")
@CrossOrigin(origins = "*")
public class SkillController {
    private final SkillService skillService;
    @Operation(summary = "스킬 카테고리 조회", description = "스킬 카테고리를 조회합니다.")
    @GetMapping("/skills/categories")
    public List<SkillCategoryDto> getSkillCategories() {
        return skillService.getSkillCategories();
    }
}