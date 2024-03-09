package controllers;

import entities.MauSac;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet({
    "/mau-sac/create",
})
public class MauSacServlet extends HttpServlet {
    private List<MauSac> ds = new ArrayList<>();

    public void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException, ServletException {
        request.getRequestDispatcher("/views/mau_sac/create.jsp")
            .forward(request, response);
    }

    public void doPost(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws IOException, ServletException {
        String ma = request.getParameter("ma");
        String ten = request.getParameter("ten");
        String ttS = request.getParameter("trangThai");
        int trangThai = Integer.parseInt(ttS);
        MauSac ms = new MauSac(null, ma, ten, trangThai);
        this.ds.add(ms);

        request.setAttribute("data", ms);
        request.setAttribute("ds", ds);

        /**
         * LƯU Ý:
         * Sau khi gọi getRequestDispatcher().forward()
         * không được phép xử lý gì thêm.
         */
        request.getRequestDispatcher("/views/mau_sac/index.jsp")
            .forward(request, response);
    }

}
