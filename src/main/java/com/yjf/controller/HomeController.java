package com.yjf.controller;

import com.github.pagehelper.PageInfo;
import com.yjf.entity.Category;
import com.yjf.entity.PageResult;
import com.yjf.entity.Product;
import com.yjf.entity.Result;
import com.yjf.service.CategoryService;
import com.yjf.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 余俊锋
 * @date 2020/12/6 11:31
 * @Description
 */
@RestController
@RequestMapping("home")
public class HomeController {

    @Autowired
    private  CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @GetMapping
    @RequestMapping("categoryList")
    public Result categoryList(){
        List<Category> list = categoryService.findAll();
        return new Result(true,"查询成功",list);
    }


    @GetMapping
    @RequestMapping("productList/{isHot}")
    public Result productList(String title,@PathVariable Integer isHot,Integer pageCurrent,Integer pageSize,Integer categoryId){
        PageInfo<Product> pageInfo = productService.selectAll(title, isHot, pageCurrent, pageSize,categoryId);
        List<Product> list = pageInfo.getList();
        PageResult<Product> page = new PageResult<>(pageInfo.getPages()==0?1:pageInfo.getPages(),list);
        page.setPageSize(pageSize);
        return new Result(true,"查询成功",page);
    }
}
