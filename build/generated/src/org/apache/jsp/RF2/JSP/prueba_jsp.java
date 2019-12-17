package org.apache.jsp.RF2.JSP;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.ArrayList;
import DTO.Usuario;
import NEGOCIO.SegundoParcial;

public final class prueba_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <div>\n");
      out.write("            \n");
      out.write("            ");
  
            Usuario s = (Usuario) request.getSession().getAttribute("usuario");
            String usuario= s.getCorreo();
            
            
      out.write("\n");
      out.write("            \n");
      out.write("            <h3>Hola esta es la prueba </h3>\n");
      out.write("            <form action=\"../../presentarPrueba.do\">\n");
      out.write("                <p name=\"correo\">  ");
      out.print(usuario);
      out.write("  </p>\n");
      out.write("                <p> 10 <input type=\"checkbox\" name=\"check\" value=\"10\">  20 <input type=\"checkbox\" name=\"check\" value=\"20\"> </p>\n");
      out.write("                <p> 30 <input type=\"checkbox\" name=\"check\" value=\"30\">  40 <input type=\"checkbox\" name=\"check\" value=\"40\"> </p>\n");
      out.write("                <p> 50 <input type=\"checkbox\" name=\"check\" value=\"50\">  60 <input type=\"checkbox\" name=\"check\" value=\"60\"> </p>\n");
      out.write("                <p> 70 <input type=\"checkbox\" name=\"check\" value=\"70\">  80 <input type=\"checkbox\" name=\"check\" value=\"80\"> </p>\n");
      out.write("                <p> 90 <input type=\"checkbox\" name=\"check\" value=\"90\"> </p>\n");
      out.write("                <p> <input type=\"submit\" placeholder=\"Presentar\"></p>\n");
      out.write("\n");
      out.write("            </form>\n");
      out.write("        </div>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
