package controllers;

import entities.MauSac;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import repositories.MauSacRepository;
import utils.DBContext;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@WebServlet({
    "/mau-sac/create",
    "/mau-sac/store",
    "/mau-sac/edit",
    "/mau-sac/update",
    "/mau-sac/index",
    "/mau-sac/delete",
})
public class MauSacServlet extends HttpServlet {
    private List<MauSac> ds = new ArrayList<>();
    private MauSacRepository msRepo = new MauSacRepository();

    public MauSacServlet()
    {
        ds.add(new MauSac(null, "1", "Yellow", 1));
        ds.add(new MauSac(null, "2", "Green", 1));
        ds.add(new MauSac(null, "3", "Black", 0));
    }

    public void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException, ServletException {
        String uri = request.getRequestURI();
        if (uri.contains("create")) {
            this.create(request, response);
        } else if (uri.contains("edit")) {
            this.edit(request, response);
        } else if (uri.contains("delete")) {
            this.delete(request, response);
        } else {
            this.index(request, response);
        }
    }

    public void doPost(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws IOException, ServletException {
        String uri = request.getRequestURI();
        if (uri.contains("store")) {
            this.store(request, response);
        } else if (uri.contains("update")) {
            this.update(request, response);
        } else {
            //
        }
    }


    public void create(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws IOException, ServletException {
        request.getRequestDispatcher("/views/mau_sac/create.jsp")
            .forward(request, response);
    }
    public void store(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException, ServletException {
        MauSac ms = new MauSac();
        try {
            BeanUtils.populate(ms, request.getParameterMap());
            this.msRepo.insert(ms);
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("data", ms);
        request.setAttribute("ds", ds);

        response.sendRedirect("/SD19306_SOF3011_war/mau-sac/index");
    }
    public void edit(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException, ServletException {
        String idS = request.getParameter("id");
        int id = Integer.parseInt(idS);
        request.setAttribute("ms", this.msRepo.findById(id));
        request.getRequestDispatcher("/views/mau_sac/edit.jsp")
                .forward(request, response);
    }

    public void update(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException, ServletException {
        String idS = request.getParameter("id");
        String ma = request.getParameter("ma");
        String ten = request.getParameter("ten");
        String ttS = request.getParameter("trangThai");
        Integer trangThai = null, id = null;

        // Check trống
        if (
            idS == null || idS.trim().length() == 0 ||
            ma == null || ma.trim().length() == 0 ||
            ten== null || ten.trim().length() == 0 ||
            ttS == null || ttS.trim().length() == 0
        ) {
            this.error(request, response, "Không được để trống", "/mau-sac/edit?id=" + idS);
            return ;
        }

        // Check độ dài
        if (ma.trim().length() > 255 || ten.trim().length() > 255) {
            this.error(request, response, "Mã/Tên tối đa 255 kí tự", "/mau-sac/edit?id=" + idS);
            return ;
        }

        // Check trạng thái
        try {
            trangThai = Integer.parseInt(ttS);
            if (trangThai != 0 && trangThai != 1) {
                new Exception();
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.error(request, response, "Trạng thái không hợp lệ", "/mau-sac/edit?id=" + idS);
            return ;
        }

        // Check ID là số
        try {
            id = Integer.parseInt(idS);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            this.error(request, response, "ID không hợp lệ", "/mau-sac/index");
            return ;
        }

        // Check ID tồn tại
        MauSac ms = this.msRepo.findById(id);
        if (ms == null) {
            this.error(request, response, "ID không tồn tại", "/mau-sac/index");
            return ;
        }

        MauSac ms1 = this.msRepo.getByIdAndMa(ms.getId(), ms.getMa());
        if (ms1 != null) {
            this.error(request, response, "", "/mau-sac/index");
            return ;
        }

        MauSac newVal = new MauSac(id, ma, ten, trangThai);
        this.msRepo.update(newVal);

        response.sendRedirect("/SD19306_SOF3011_war/mau-sac/index");
    }

    public void error(HttpServletRequest request, HttpServletResponse res, String message, String url) throws IOException {
        request.getSession().setAttribute("error", message);
        res.sendRedirect(url);
    }

    public void index(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException, ServletException {
        List<MauSac> ds = this.msRepo.findAll();
        request.setAttribute("ds", ds);
        request.getRequestDispatcher("/views/mau_sac/index.jsp")
                .forward(request, response);
    }

    public void delete(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException, ServletException {
        String idS = request.getParameter("id");
        int id = Integer.parseInt(idS);
        MauSac ms = this.msRepo.findById(id);
        this.msRepo.delete(ms);

        response.sendRedirect("/SD19306_SOF3011_war/mau-sac/index");
    }
}
