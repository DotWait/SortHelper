package com.dotwait.algorithmic_practice.controller;

import com.dotwait.algorithmic_practice.core.Invoke;
import com.dotwait.algorithmic_practice.util.ArrayUtil;
import com.dotwait.algorithmic_practice.util.DynamicCompilation;
import com.dotwait.algorithmic_practice.util.ReadUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;

@RestController
public class Controller {
    private static final int ARRAY_NUM = 10000;
    /**
     * 存放上传文件的目录
     */
    private static final String rootPath = "/tmp/file/";
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public String uploadSrc(MultipartFile file){
        if (file.isEmpty()){
            return "No File!";
        }
        File serverFile = new File(rootPath + File.separator + "tmpFiles");
        try {
            file.transferTo(serverFile);
            String sourceStr = ReadUtil.read(serverFile);
            String fileName = file.getOriginalFilename();
            String clsName = fileName.substring(0, fileName.indexOf("."));
            String methodName = clsName.substring(0,1).toLowerCase() + clsName.substring(1);
            System.out.println("clsName:"+clsName+",methodName:"+methodName);
            Class aClass = DynamicCompilation.compile(sourceStr, clsName, methodName);
            int[] array = ArrayUtil.randomArray(ARRAY_NUM);
            Invoke.invokeSort(aClass, methodName, array);
            boolean ascending = ArrayUtil.isAscending(array);
            if (ascending){
                return "you are right!";
            }
            return "you are wrong!";
        } catch (IOException e) {
            e.printStackTrace();
            return "read file failed!";
        } catch (Exception e) {
            System.out.println("compile failed!");
            e.printStackTrace();
            return "compile failed!";
        }
    }

    @RequestMapping("/index")
    public ModelAndView index(){
        return new ModelAndView("index");
    }
}
