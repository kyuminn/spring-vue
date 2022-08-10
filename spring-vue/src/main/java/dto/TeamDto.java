package dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class TeamDto {

    private String name;

    // front쪽에서 String 타입으로 날짜가 들어온 경우 Date 형태로 변환하도록(직렬화를) 도와주는 어노테이션
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate estbdate;
}
