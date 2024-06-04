package capstone.zigtong.adminserver.domain.skill.service;

import capstone.zigtong.adminserver.domain.skill.dto.SkillCategoryDto;
import capstone.zigtong.adminserver.domain.skill.repository.SkillRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class SkillService {
    private final SkillRepository skillRepository;
    @Transactional
    public List<SkillCategoryDto> getSkillCategories() {
        return skillRepository.findAllCategories()
                .stream()
                .map(SkillCategoryDto::new)
                .collect(Collectors.toUnmodifiableList());
    }
}
