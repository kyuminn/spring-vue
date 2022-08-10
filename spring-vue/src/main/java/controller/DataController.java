package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.MemberDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.*;

@RestController //data json 형태로 반환 , 여기서 보낸 데이터는 vue 에서  axios 후 then 에서 response.data로 가져올 수 있음
@Slf4j
public class DataController {

    @GetMapping("/axiosTest")
    public String axiosTest(){
        log.info("get axios test");
        return "nice";
    }

    @GetMapping("/list")
    public List<MemberDto> list(){
        log.info("111");
        MemberDto testDto1 = new MemberDto("testemail1","1234");
        MemberDto testDto2 = new MemberDto("testemail2","1234");
        MemberDto testDto3 = new MemberDto("testemail3","1234");

        List<MemberDto> list = new ArrayList<>();
        list.add(testDto1);
        list.add(testDto2);
        list.add(testDto3);

        return list;
    }

    @PostMapping("/post")
    public String post(@RequestBody MemberDto testDto){
        log.info(testDto.getEmail());
        return testDto.getEmail();
    }

    @PostMapping("/fileTest")
    // 파일 하나만 받을때는 @RequestPart MultipartFile file 로
    public String file(@RequestPart(value = "myFile1") MultipartFile[] files,
                       @RequestParam(value="myText") String text,
                       @RequestParam(value="dto")String jsonData) throws JsonProcessingException, UnsupportedEncodingException {
        for (MultipartFile file : files) {
            log.info(file.getName());
            log.info(file.getOriginalFilename());
        // 파일명이 한글인 경우에 getOriginalFilename() 했을때 깨져서 나옴...
            // 실제 상황에서는 파일까지 포함한 dto로 받을 거같아서 그 방향으로 해보는 게 나을 듯..
        }
        log.info(text);
        // multipart/form-data 형식으로 formdata 안에 넣어서 보낸 경우에는
        // @RequestParam을 이용하여 key값을 통해 얻어낼 수 있기 때문에
        // json data를 java class로 변환해주는 ObjectMapper를 이용한다

        MemberDto memberDto= new ObjectMapper().readValue(jsonData, MemberDto.class);
        log.info(memberDto.getEmail());

        return "success!!";
    }

    @GetMapping("/sessionCheck")
    public Map<String,Object> checkSession(HttpSession session){
        session.setAttribute("session","testSession");
        session.setAttribute("sessionId", UUID.randomUUID());
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("session", session.getAttribute("session"));
        resultMap.put("sessionId",session.getAttribute("sessionId"));
        return resultMap;
    }

    @PostMapping("/popup")
    public String getName(@RequestBody String name){
        log.info("name:{}", name);
        return "success";
    }

}
