package controller;

import dao.DAO_DienThoai;
import dao.DAO_NhaCungCap;
import dao_interface.I_DAO_DienThoai;
import dao_interface.I_DAO_NhaCungCap;
import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DienThoai;
import model.NhaCungCap;
import utility.HibernateUtility;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/DienThoai", "/DienThoai*"})
public class ControllerDienThoai extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private HibernateUtility hibernateUtility;
    private EntityManager entityManager;
    private I_DAO_DienThoai iDaoDienThoai;
    private I_DAO_NhaCungCap iDaoNhaCungCap;

    public ControllerDienThoai() {
        super();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        hibernateUtility = new HibernateUtility();
        entityManager = hibernateUtility.getEntityManager();
        iDaoDienThoai = new DAO_DienThoai(entityManager);
        iDaoNhaCungCap = new DAO_NhaCungCap(entityManager);
    }

    @Override
    public void destroy() {
        hibernateUtility.close();
        super.destroy();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action") != null ? req.getParameter("action") : "unknown";
        switch (action) {
            case "list":
                doGetList(req, resp);
                break;
            case "create":
                doGetCreate(req, resp);
                break;
            case "manage":
                doGetManage(req, resp);
                break;
            case "update":
                doGetUpdate(req, resp);
                break;
            case "delete":
                doGetDelete(req, resp);
                break;
            default:
                doGetHome(req, resp);
                break;
        }
    }

    private void doGetHome(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("views/index.jsp").forward(req, resp);
    }

    private void doGetCreate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<NhaCungCap> listNhaCungCap = iDaoNhaCungCap.getAll();
        req.setAttribute("listNhaCungCap", listNhaCungCap);
        req.getRequestDispatcher("views/create.jsp").forward(req, resp);
    }

    private void doGetManage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<DienThoai> listDienThoai = iDaoDienThoai.getAll();
        req.setAttribute("listDienThoai", listDienThoai);
        req.getRequestDispatcher("views/manage.jsp").forward(req, resp);
    }

    private void doGetUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        DienThoai dienThoai = iDaoDienThoai.getByID(id);
        List<NhaCungCap> listNhaCungCap = iDaoNhaCungCap.getAll();
        req.setAttribute("dienThoai", dienThoai);
        req.setAttribute("listNhaCungCap", listNhaCungCap);
        req.getRequestDispatcher("views/update.jsp").forward(req, resp);
    }

    private void doGetDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        boolean status = iDaoDienThoai.delete(id);
        if (status) {
            resp.sendRedirect("DienThoai?action=list");
        }
    }

    private void doGetList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<DienThoai> listDienThoai = iDaoDienThoai.getAll();
        req.setAttribute("listDienThoai", listDienThoai);
        req.getRequestDispatcher("views/product.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action") != null ? req.getParameter("action") : "unknown";
        switch (action) {
            case "create":
                doPostCreate(req, resp);
                break;
            case "update":
                doPostUpdate(req, resp);
                break;
            default:
                doGetHome(req, resp);
                break;
        }
    }

    private void doPostCreate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tenDienThoai = req.getParameter("tenDienThoai");
        int namSanXuat = Integer.parseInt(req.getParameter("namSanXuat"));
        String cauHinh = req.getParameter("cauHinh");
        int maNhaCungCap = Integer.parseInt(req.getParameter("nhaCungCap"));
        NhaCungCap nhaCungCap = iDaoNhaCungCap.getByID(maNhaCungCap);

        DienThoai dienThoai = new DienThoai(tenDienThoai, namSanXuat, cauHinh, nhaCungCap);

        boolean status = iDaoDienThoai.add(dienThoai);
        if (status) {
            resp.sendRedirect("DienThoai?action=list");
        }
    }

    private void doPostUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int maDienThoai = Integer.parseInt(req.getParameter("maDienThoai"));
        String tenDienThoai = req.getParameter("tenDienThoai");
        int namSanXuat = Integer.parseInt(req.getParameter("namSanXuat"));
        String cauHinh = req.getParameter("cauHinh");
        int maNhaCungCap = Integer.parseInt(req.getParameter("nhaCungCap"));
        NhaCungCap nhaCungCap = iDaoNhaCungCap.getByID(maNhaCungCap);

        DienThoai dienThoai = new DienThoai(maDienThoai, tenDienThoai, namSanXuat, cauHinh, nhaCungCap);

        boolean status = iDaoDienThoai.update(dienThoai);
        if (status) {
            resp.sendRedirect("DienThoai?action=list");
        }
    }
}
