package sergio.projects;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Controller extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String action = "default";
        if(request.getParameter("action") != null){
            action = request.getParameter("action");
        }
        if(action.equals("insert")){
            String name = request.getParameter("name");
            Double amount = Double.parseDouble(request.getParameter("amount"));
            BigDecimal bdAmount = new BigDecimal(amount);
            inBills(name, bdAmount);  
        }else if (action.equals("delete")){
            int id = Integer.parseInt(request.getParameter("dButton"));
            Double amount = getAmt(id);
            BigDecimal damount = new BigDecimal(amount);
            String name = getName(id);
            delBills(id, name, damount);
        }
        List<Bills> bills = getBills();
        request.setAttribute("bills", bills);
        
        Double total= 0.0;
        Double amount;
        for(int i=0; i<bills.size(); i++){
            amount = bills.get(i).getAmount().doubleValue();
            total = total + amount;
        }
        request.setAttribute("total", total);
        request.getRequestDispatcher("bills.jsp").forward(request, response);
    }
    
private List<Bills> getBills() throws PersistenceException {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("BillsPU");
    EntityManager em = emf.createEntityManager();
    List<Bills> bills = em.createNamedQuery("Bills.findAll").getResultList();
    return bills;
}
private Double getAmt(int index) throws PersistenceException {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("BillsPU");
    EntityManager em = emf.createEntityManager();
    Bills amount = (Bills)em.createNamedQuery("Bills.findById")
        .setParameter("id", index).getSingleResult();
    return amount.getAmount().doubleValue();
}
private String getName(int index) throws PersistenceException{
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("BillsPU");
    EntityManager em = emf.createEntityManager();
    Bills name = (Bills)em.createNamedQuery("Bills.findById")
        .setParameter("id", index).getSingleResult();
    return name.getName();
}
private void inBills(String name, BigDecimal amount) throws PersistenceException{
     Bills bills = new Bills(name, amount);
     EntityManagerFactory emf = Persistence.createEntityManagerFactory("BillsPU");
     EntityManager em = emf.createEntityManager();
     em.getTransaction().begin();
     em.persist(bills);
     em.merge(bills);
     em.getTransaction().commit();
}
private void delBills(int id, String name, BigDecimal amount){
     Bills bills = new Bills(id, name, amount);
     EntityManagerFactory emf = Persistence.createEntityManagerFactory("BillsPU");
     EntityManager em = emf.createEntityManager();
     em.getTransaction().begin();
     em.remove(em.merge(bills));
     em.getTransaction().commit();
    
}

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
