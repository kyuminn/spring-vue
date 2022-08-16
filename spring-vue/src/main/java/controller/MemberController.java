package controller;

import dto.MemberDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mapper.MemberMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberMapper memberMapper;

    @GetMapping("/member")
    public List<MemberDto> memberList(){
        List<MemberDto> list = new ArrayList<>();
        list = memberMapper.select1();
        log.info("list size:{}",list.size());
        return list;
    }

    @PostMapping("/member")
    public List<MemberDto> post(@RequestBody MemberDto memberDto){
        List<MemberDto> ls = memberMapper.postList(memberDto);
        return ls;
    }

//    @GetMapping( "/member/{email:.+}") // . 뒷부분 인식해야함
//    public List<MemberDto> findByEmail(@PathVariable String email){
//        log.info("email:{}", email);
//        MemberDto findMember = memberMapper.findByEmail(email);
//        log.info("findMember password:{}", findMember.getPassword());
//        List<MemberDto> ls = new ArrayList<>();
//        ls.add(findMember);
//        log.info("ls size:{}",ls.size());
//        return ls;
//    }

    // @RequestParam : URL의 queryString을 받음/ Multipart 형식으로 보낸 FormDate 안의 key값을 가져올 수 있음 (/fileTest참조)
    // @PathVariable : URL의 / 안에 있는 경로변수 받음
    // @RequestBody : http request의 body 부분을 (java 객체)로 받을 수 있음 (주로 json 받을 때 활용),
    //                body에 있는 데이터가 json일 경우 dto 가 아니더라도 Map<String,String> 으로도 받을 수 있음
    @GetMapping("/member/detail")
    public MemberDto findByEmail(@RequestParam String email){
        log.info("email:{}",email);
        MemberDto findMember = memberMapper.findByEmail(email);
        return findMember;
    }

    // vue에서 delete1 사용하는 경우 !
    @PostMapping("/member/delete")
    public boolean deleteByEmail(@RequestBody MemberDto memberDto){
        log.info("deleteByEmail:{}",memberDto.getEmail());
        int success = memberMapper.deleteByEmail(memberDto.getEmail());
        return success==1 ? true:false;
        // 여러개의 행이 변했을 수도 있기 때문에 !=0 으로 하는 것이 맞을 듯..
    }

    // vue 에서 delete2 사용하는 경우
    @DeleteMapping("/member")
    public boolean deleteTest(@RequestBody String email){ // 여기서 @RequstBody MemberDto memberDto 해도 됨.
        log.info("here! {}", email);
        // String으로 받는 경우 json 데이터 그대로 가져오므로 dto를 만들어서
        // dto.getEmail() 받는게 나아보임.
        return true;
    }

    @DeleteMapping("/members")
    public boolean deleteTest2(@RequestParam String email){
        log.info("deleteTest2:{}",email);
        return true;
    }

    @PutMapping("/member")
    public boolean edit(@RequestBody MemberDto memberDto){
        log.info("edit email:{}", memberDto.getEmail());
        int result = memberMapper.editPassword(memberDto.getEmail());
        if(result > 0){
            return true;
        }
        return false;
    }

    @GetMapping("/restful")
    public ResponseEntity<List<MemberDto>> test(){
        List<MemberDto> memberDtos = memberMapper.select1();
        MemberDto memberDto = new MemberDto();
        memberDto.setEmail("restEmail@test.com");
        memberDto.setPassword("R.E.S.T");
        memberDtos.add(memberDto);
        return ResponseEntity.ok(memberDtos);
    }


}
