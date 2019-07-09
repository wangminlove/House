package com.house.java.controller;

import com.github.pagehelper.PageInfo;
import com.house.entity.District;
import com.house.entity.House;
import com.house.entity.Type;
import com.house.entity.Users;
import com.house.service.DistrictService;
import com.house.service.HouseService;
import com.house.service.TypeService;
import com.house.util.FileBean;
import com.house.util.HouseCondition;
import com.house.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/page/")
public class HouseController {
    @Autowired
    private TypeService typeService;
    @Autowired
    private DistrictService districtService;
    @Autowired
    private HouseService houseService;
    //fabu.jsp页面异步获取的房屋类型及区域下拉列表信息
    @RequestMapping("goFaBu")
    public String goFaBu(Model model){
        //所有房屋类型
        List<Type> types = typeService.getAllType();
        //所有区域信息
        List<District> districts = districtService.getAllDistrict();
        model.addAttribute("types",types);//发布页面的房屋类型下拉列表
        model.addAttribute("districts",districts);//发布页面的区域下拉列表
        return "/page/fabu";
    }
    //用户发布房屋信息,包含文件上传,此时文件不放在项目当中,而是上传Nginx服务器保存
    @RequestMapping(value = "addHouse",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String addHouse(House house, MultipartFile pfile, Model model, HttpSession session) throws IOException {
        //1.图片上传到图片服务器F:\王敏\images,不放在项目里
        String filename = pfile.getOriginalFilename();//获取上传的文件名称
        String pname = filename.substring(filename.lastIndexOf("."));//获取上传问价的扩展名
        String saveFileName = System.currentTimeMillis() + pname;//保存新的文件名,尽量保证每张图片的图片名不同
        String path="F:/wangmin/images/"+saveFileName;//path是保存上传文件的路劲
        File saveFile=new File(path);
        pfile.transferTo(saveFile);//上传文件到指定路径

        /*String saveFileName = FileBean.Upload(pfile);//上传图片并返回图片名*/

        //2.将获取的house保存到数据库,添加信息
        house.setId(System.currentTimeMillis()+"");//设置编号
        Users user =(Users) session.getAttribute("login");
        house.setUserId(user.getId());//设置用户编号
        house.setPath(saveFileName);//设置图片名
        house.setIsdel(0);//设置是否删除
        house.setIspass(0);//设置是否通过审核
        int temp = houseService.addHouse(house);
        if (temp>0){
            String url="/page/guanli.jsp";
            return "<script>alert('发布出租房成功');location.href='"+url+"'</script>";
        }else{
            //若图片上传成功但数据添加未成功,将图片删掉
            saveFile.delete();
            return "<script>alert('发布出租房失败');history.go(-1)</script>";
        }
    }
      //通过用户id获取该用户发布的所有房屋信息
      @RequestMapping("getHouse")
      public String getHouse(PageBean pageBean,HttpSession session,Model model){
          Integer id = ((Users) session.getAttribute("login")).getId();
          PageInfo<House> pageInfo = houseService.getHouseByUserId(pageBean, id);
          model.addAttribute("pageInfo",pageInfo);
          //存作用域跳转到guanli.jsp
          return "/page/guanli";
      }
      //通过房屋id,获取单条房屋信息,通过作用域转到修改页面做信息回显
      @RequestMapping("getSingleHouse")
       public String getSingleHouse(String id,Model model){
          House house = houseService.getHouse(id);
          model.addAttribute("house",house);
          //转到修改页面
          return "/page/UpdateHouse";
      }

      /*用户个人修改自己发布的房屋信息
       *修改分两种情况
       * 1.有做文件修改
       * 2.没有修改文件
       * */
      @RequestMapping("updateHouse")
      public String updateHouse(House house,String oldPath,MultipartFile pfile) throws IOException {
          String filename = pfile.getOriginalFilename();
          //用户有重新修改文件(图片),此时需要重新上传文件,同时删除之前的文件
          if (!filename.equals("")){
              String pname = filename.substring(filename.lastIndexOf("."));//获取文件后缀名
              //给文件重新取名,避免文件服务器有文件重名,导致图片覆盖
              String saveFileName=System.currentTimeMillis()+pname;
              //指定文件保存路径
              String path = "F:/wangmin/images/" + saveFileName;
              File file = new File(path);
              pfile.transferTo(file);//上传文件到指定路径
              //删除原有图片
              new File("F:/wangmin/images/"+oldPath).delete();

              house.setPath(saveFileName);//设置房屋的path
          }
          int i = houseService.updateHouse(house);
          //重定向到getHouse服务器
          return "redirect:getHouse";
      }
      //逻辑删除房屋信息isdel=1
      @RequestMapping("deleteHouse")
      public String deleteHouse(String id){
          int temp = houseService.deleteHouse(id, 1);
          //删除后重定向到getHouse控制器
          return "redirect:getHouse";
      }
      //list页面所有已审核房屋展示,带分页查询条件,前台传page页号
    @RequestMapping("getBorswerHouse")
    public String getBorswerHouse(HouseCondition condition,Model model){
        PageInfo<House> pageInfo = houseService.getBorswerHouse(condition);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("condition",condition);
        //存作用域跳转list.jsp
        return "/page/list";
    }
      //房屋浏览页面通过房屋id单个房屋详情展示
    @RequestMapping("getDetail")
    public String getDetail(String id,Model model){
        House house = houseService.getHouse(id);
        model.addAttribute("house",house);
        //跳转details.jsp
        return "/page/details";
    }
}
