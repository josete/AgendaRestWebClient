/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import Objetos.AgendaObjetoConId;
import Objetos.PersonaObjetoConId;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servicios.PersonaService;

/**
 *
 * @author Familia
 */
public class ModificarPersona extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    PersonaService personaService = new PersonaService();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AgendaObjetoConId aid = personaService.getInfo(AgendaObjetoConId.class, request.getSession().getAttribute("idAgenda").toString(), request.getSession().getAttribute("token").toString());
        response.setContentType("text/html;charset=UTF-8");
        String nPersona = request.getParameter("nPersona");
        PersonaObjetoConId pid = null;
        for(PersonaObjetoConId p: aid.getPersonas()){
            if(p.getId()==Integer.valueOf(nPersona)){
                pid=p;
            }
        }        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ModificarPersona</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<form method='POST' action='/AgendaRestWebClient/ActualizarPersona'>"
                    + "<input type='text' name='nombre' value='"+pid.getNombre()+"'>"
                    + "<input type='email' name='email' value='"+pid.getEmail()+"'>"
                    + "<input type='text' name='telefono' value='"+pid.getTelefono()+"'>"
                    + "<input type='submit' value='Modificar Persona'>"
                    + "<input type='hidden' name='idPersona' value="+pid.getId()+">"
                    + "</form>");            
            out.println("</body>");
            out.println("</html>");
        }
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
