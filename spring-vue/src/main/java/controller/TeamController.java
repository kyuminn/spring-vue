package controller;

import dto.TeamDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mapper.TeamMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TeamController {

    private final TeamMapper teamMapper;

    @PostMapping("/searchDate")
    public List<TeamDto> searchByDate(@RequestBody TeamDto teamDto){
        log.info("date:{}",teamDto.getEstbdate());
        List<TeamDto> byDate = teamMapper.findByDate(teamDto);
        // Javascript에서는 1월~12월을 0~11로 표현하므로 미리 달에서 1 빼주기
//        for (TeamDto dto : byDate) {
//            dto.setEstbdate(dto.getEstbdate().minusMonths(1L));
//        }
        log.info("length:{}", byDate.size());
        return byDate; // axios에서 res.data로 받을 수 있음.
    }
}
