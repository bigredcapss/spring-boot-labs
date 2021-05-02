import com.we.auth.server.utils.SnowFlake;

/**
 * @author we
 * @date 2021-05-02 11:00
 **/
public class SnowFlakeTest {
        public static void main(String[] args) {
        SnowFlake snowFlake = new SnowFlake(2, 3);

        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            System.out.println("当前生成的有序数字串："+snowFlake.nextId());
        }

        System.out.println("总共耗时："+(System.currentTimeMillis() - start));
    }
}
