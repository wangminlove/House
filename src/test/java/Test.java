import com.house.entity.District;
import com.house.mapper.DistrictMapper;
import com.house.service.DistrictService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Test {
    @org.junit.Test
    public void test(){
        for (int i = 0; i <10 ; i++) {
            int code=(int)((Math.random()*9+1)*100000);
            System.out.println(code);
        }
    }
    //测试service
    @org.junit.Test
    public void test01(){
        ApplicationContext Context = new ClassPathXmlApplicationContext("applicationContext.xml");
        DistrictService districtService = (DistrictService)Context.getBean("districtServiceImpl");
        List<District> list = districtService.getAllDistrict();
        System.out.println(list);
    }
    //测试mapper
    @org.junit.Test
    public void test02(){
        ApplicationContext Context = new ClassPathXmlApplicationContext("applicationContext.xml");
        DistrictMapper districtMapper = (DistrictMapper)Context.getBean("districtMapper");
        List<District> list = districtMapper.selectByExample(null);
        System.out.println(list);
    }
}
