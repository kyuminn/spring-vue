package mapper;

import dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {

    public List<MemberDto> select1();
    public List<MemberDto> postList(MemberDto memberDto);
    public MemberDto findByEmail(String email);
    public int deleteByEmail(String email);
    public int editPassword(String email);
}
