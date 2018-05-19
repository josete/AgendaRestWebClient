/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import Objetos.AgendaObjeto;
import Objetos.ListaAgendas;
import Objetos.PersonaObjeto;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servicios.AgendaService;

/**
 *
 * @author Portatil
 */
public class VerAgendas extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    AgendaService agendaSesrvice = new AgendaService();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ListaAgendas a = agendaSesrvice.getXml(ListaAgendas.class, request.getSession().getAttribute("token").toString());
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Ver agenda</title>");
            out.println("</head>");
            out.println("<body>");
            if (a.getAgendas() != null) {
                int i = 1;
                for (String p : a.getAgendas()) {
                    out.println("<h3>" + i + ". " + p + "</h3>");
                    i++;
                }
                out.println("<h3>Selecciona una agenda</h3>");
                out.println("<form method='POST' action='/AgendaRestWebClient/SeleccionarAgenda'><input type='text' name='nAgenda'><input type='submit' value='Seleccionar agenda'></form>");
            } else {
                out.println("<h3>No hay ninguna agenda</h3>");
                out.println("<form method='POST' action='/AgendaRestWebClient/CrearAgendaServlet'><input type='submit' value='Crear agenda'></form>");
            }
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
