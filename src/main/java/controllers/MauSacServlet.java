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
    "/mau-sac/store",
    "/mau-sac/edit",
    "/mau-sac/update",
    "/mau-sac/index",
    "/mau-sac/delete",
})
public class MauSacServlet extends HttpServlet {
    private List<MauSac> ds = new ArrayList<>();

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
        String ma = request.getParameter("ma");
        String ten = request.getParameter("ten");
        String ttS = request.getParameter("trangThai");
        int trangThai = Integer.parseInt(ttS);
        MauSac ms = new MauSac(null, ma, ten, trangThai);
        this.ds.add(ms);

        request.setAttribute("data", ms);
        request.setAttribute("ds", ds);

        response.sendRedirect("/SD19306_SOF3011_war/mau-sac/index");
    }
    public void edit(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException, ServletException {
        String ma = request.getParameter("ma");
        for (int i = 0; i < this.ds.size(); i++) {
            MauSac c = this.ds.get(i);
            if (c.getMa().equals( ma )) {
                request.setAttribute("ms", c);
            }
        }

        request.getRequestDispatcher("/views/mau_sac/edit.jsp")
                .forward(request, response);
    }

    public void update(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException, ServletException {
        String ma = request.getParameter("ma");
        String ten = request.getParameter("ten");
        String ttS = request.getParameter("trangThai");
        int trangThai = Integer.parseInt(ttS);
        MauSac ms = new MauSac(null, ma, ten, trangThai);

        for (int i = 0; i < this.ds.size(); i++) {
            MauSac c = this.ds.get(i);
            if (c.getMa().equals( ms.getMa() )) {
                this.ds.set(i, ms);
            }
        }

        response.sendRedirect("/SD19306_SOF3011_war/mau-sac/index");
    }

    public void index(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException, ServletException {
        request.setAttribute("ds", ds);
        request.getRequestDispatcher("/views/mau_sac/index.jsp")
                .forward(request, response);
    }
    public void delete(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException, ServletException {
        String ma = request.getParameter("ma");
        for (int i = 0; i < this.ds.size(); i++) {
            MauSac c = this.ds.get(i);
            if (c.getMa().equals( ma )) {
                this.ds.remove(i);
            }
        }

        response.sendRedirect("/SD19306_SOF3011_war/mau-sac/index");
    }
}
