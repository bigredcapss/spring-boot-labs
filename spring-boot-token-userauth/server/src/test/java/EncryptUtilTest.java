import com.fasterxml.jackson.databind.ObjectMapper;
import com.we.auth.server.dto.AccessTokenDto;
import com.we.auth.server.utils.EncryptUtil;

import static com.we.auth.server.utils.EncryptUtil.aesEncrypt;

/**
 * @author we
 * @date 2021-05-02 10:58
 **/
public class EncryptUtilTest {
        public static void main(String[] args) throws Exception{
//        ObjectMapper objectMapper=new ObjectMapper();
//        AccessTokenDto info=new AccessTokenDto(1,"debug",1568034072458L,"368543454793703424");
//
//        String jsonStr=objectMapper.writeValueAsString(info);
//        System.out.println("明文： "+jsonStr);
//
//        String key="e2bd6cee47e0402db80862a09ff4dfd6";
//        String encryptStr=aesEncrypt(jsonStr,key);
//        System.out.println("密文： "+ encryptStr);
//
//        String srcInfo=EncryptUtil.aesDecrypt(encryptStr,key);
//        System.out.println("明文： "+ srcInfo);
    }
}
