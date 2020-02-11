package com.davina.admin.web.servlet;

import com.davina.admin.dao.CategoryDao;
import com.davina.admin.model.Category;
import com.davina.admin.model.PageBean;
import com.davina.admin.model.Route;
import com.davina.admin.service.RouteService;
import com.davina.admin.util.UuidUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.jca.context.ResourceAdapterApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.util.List;


@MultipartConfig
@WebServlet(name = "RouteServlet",urlPatterns = "/RouteServlet")
public class RouteServlet extends BaseServlet {

    private RouteService routeService = new RouteService();

    private CategoryDao categoryDao = new CategoryDao();

    /**
     * 获取线路分页数据
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 获取请求参数curPage
        String curPage = request.getParameter("curPage");
        String rname = request.getParameter("rname");

        request.setAttribute("rname",rname);

        // 调用业务逻辑获取PageBean
        PageBean<Route> pageBean = routeService.getPageBean(curPage,rname);

        // 将pageBean放到请求域中
        request.setAttribute("pageBean",pageBean);

        // 转发跳转到rout_list.jsp
        request.getRequestDispatcher("route_list.jsp").forward(request,response);
    }

    /**
     * 根据id查询线路
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findRouteByRid(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{

        // 获取请求参数
        int rid = Integer.parseInt(request.getParameter("rid"));

        // 调用route业务类获取线路数据
        Route route = routeService.findByRid(rid);

        // 调用category业务类获取类别列表数据
        List<Category> categoryList = categoryDao.findAll();

        // 将数据存在域中
        request.setAttribute("route",route);
        request.setAttribute("categoryList",categoryList);

        // 跳转到route_update.jsp
        request.getRequestDispatcher("route_update.jsp").forward(request,response);
    }

    /**
     * 更新线路
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void updateRoute(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{

        try {
            // 获取请求数据封装到Route
            Route route = new Route();
            BeanUtils.populate(route,request.getParameterMap());

            //获取上传文件的磁盘目录
            String path = "img/product/small2/";
            String realPath1 = getServletContext().getRealPath(path);
            String realPath = "E:\\Myprojects\\java_pro\\2020\\travel\\backstage\\src\\main\\webapp\\img\\product\\small2";

            File file = new File(realPath);
            if (!file.exists()){
                //目录不存在，创建
                file.mkdir();
            }
            //获取上传文件对象
            Part rimage = request.getPart("rimage");
//            Part rimage2 = request.getPart("rimage");
            //判断是否允许的上传文件格式(form-data; name="rimage"; filename="goods_thumb_20167_330_195.jpeg")
            String uploadFileInfo  = rimage.getHeader("content-disposition");
            String fileExtName = uploadFileInfo.substring(uploadFileInfo.lastIndexOf("."), uploadFileInfo.length() - 1);
            if (fileExtName.equalsIgnoreCase(".jpg")|| fileExtName.equalsIgnoreCase(".jpeg")||fileExtName.equalsIgnoreCase(".png")) {
                //判断是否允许的上传文件大小
                if (rimage.getSize() / 1024 > 500) {
                    request.setAttribute("errorMsg", "图片大小不能超过500KB");
                    request.getRequestDispatcher("route_update.jsp").forward(request, response);
                    return;
                }
                //将上传文件写入磁盘
                //使用uuid生成一个32位长度的唯一码，uuid是根据当前电脑cpu、网卡mack地址，系统时间计算出来的一个唯一值
                String fileName = UuidUtil.getUuid() + fileExtName;
                String filePath = realPath +"\\"+ fileName;
                String filePath1 = realPath1 +"\\"+ fileName;
                rimage.write(filePath1);
                rimage.delete();

                FileInputStream fileInputStream = new FileInputStream(filePath1);
                BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
                FileOutputStream fileOutputStream = new FileOutputStream(filePath);
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);

                try{
                    byte[] buffer = new byte[1024];
                    int len = 0;
                    while ((len = bufferedInputStream.read(buffer))!= -1){
                        bufferedOutputStream.write(buffer,0,len);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
//                    fileInputStream.close();
                    bufferedInputStream.close();
//                    fileOutputStream.close();
                    bufferedOutputStream.close();
                }


                route.setRimage(path + fileName);


                // 调用更新业务
                routeService.update(route);

                // 跳转到线路管理的分页列表页面
                response.sendRedirect("RouteServlet?action=findByPage&rname=");
            }else {
                request.setAttribute("errorMsg","上传文件格式仅支持jpg,jpeg,png");
                request.getRequestDispatcher("route_update.jsp").forward(request,response);
                return;
            }
        }catch (Exception e){
            e.printStackTrace();
            request.setAttribute("errorMsg","服务器忙");
            request.getRequestDispatcher("route_update.jsp").forward(request,response);
        }
    }

    /**
     * 删除单个线路
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void delRoute(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{

       try {
           // 获取请求数据id
           int rid = Integer.parseInt(request.getParameter("rid"));

           // 调用业务删除
           routeService.delRoute(rid);

           // 跳转到线路分页查询页面
           response.sendRedirect("RouteServlet?action=findByPage&rname=");

       }catch (Exception e){
           e.printStackTrace();
           response.getWriter().write("服务器忙");
       }
    }

    /**
     * 批量删除
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void batchDelRoutes(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{

        // 获取请求的rid数组
        String[] itemSelect = request.getParameterValues("itemSelect");

        // 调用批量删除
        routeService.batchDelRoute(itemSelect);

        // 跳转到线路分页查询页面
        response.sendRedirect("RouteServlet?action=findByPage&rname=");
    }

    /**
     * 添加线路页面
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void addRouteUI(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{

        List<Category> categoryList = categoryDao.findAll();

        request.setAttribute("categoryList",categoryList);

        request.getRequestDispatcher("route_add.jsp").forward(request,response);
    }

    /**
     * 添加线路
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void addRoute(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{

        try {
            //获取请求数据封装到Route
            Route route = new Route();
            BeanUtils.populate(route,request.getParameterMap());

            //获取上传文件的磁盘目录
            String path = "img/product/small2/";
            String realPath1 = getServletContext().getRealPath(path);
            String realPath = "E:\\Myprojects\\java_pro\\2020\\travel\\backstage\\src\\main\\webapp\\img\\product\\small2";
            File file = new File(realPath);
            if (!file.exists()){
                //目录不存在，创建
                file.mkdir();
            }
            //获取上传文件对象
            Part rimage = request.getPart("rimage");
            //判断是否允许的上传文件格式(form-data; name="rimage"; filename="goods_thumb_20167_330_195.jpeg")
            String uploadFileInfo  = rimage.getHeader("content-disposition");
            String fileExtName = uploadFileInfo.substring(uploadFileInfo.lastIndexOf("."), uploadFileInfo.length() - 1);
            if (fileExtName.equalsIgnoreCase(".jpg")|| fileExtName.equalsIgnoreCase(".jpeg")||fileExtName.equalsIgnoreCase(".png")){
                //判断是否允许的上传文件大小
                if (rimage.getSize()/1024>500){
                    request.setAttribute("errorMsg","图片大小不能超过500KB");
//                    route.setRimage("");
                    request.setAttribute("route",route);
                    request.getRequestDispatcher("RouteServlet?action=addRouteUI").forward(request,response);
                    return;
                }
                //将上传文件写入磁盘
                //使用uuid生成一个32位长度的唯一码，uuid是根据当前电脑cpu、网卡mack地址，系统时间计算出来的一个唯一值
                String fileName = UuidUtil.getUuid()+fileExtName;
                String filePath = realPath +"\\"+ fileName;
                String filePath1 = realPath1 +"\\"+ fileName;
                rimage.write(filePath1);
                rimage.delete();


                FileInputStream fileInputStream = new FileInputStream(filePath1);
                BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
                FileOutputStream fileOutputStream = new FileOutputStream(filePath);
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);

                try{
                    byte[] buffer = new byte[1024];
                    int len = 0;
                    while ((len = bufferedInputStream.read(buffer))!= -1){
                        bufferedOutputStream.write(buffer,0,len);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
//                    fileInputStream.close();
                    bufferedInputStream.close();
//                    fileOutputStream.close();
                    bufferedOutputStream.close();
                }

                route.setRimage(path+fileName);

                //调用业务添加线路
                routeService.addRoute(route);

                response.sendRedirect("RouteServlet?action=findByPage&rname=");

            }else {
//                route.setRimage("");
                request.setAttribute("route",route);
                request.setAttribute("errorMsg","上传文件格式仅支持jpg,jpeg,png");
                request.getRequestDispatcher("RouteServlet?action=addRouteUI").forward(request,response);
                return;
            }

        }catch (Exception e){
            e.printStackTrace();
            response.getWriter().write("服务器忙");
        }

    }

}
