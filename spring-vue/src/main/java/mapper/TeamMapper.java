package mapper;

import dto.TeamDto;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface TeamMapper {

    List<TeamDto> getAll();
    List<TeamDto> findByDate(TeamDto dto);
}
