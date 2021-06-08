package web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 *
 * @author clopez
 */
@WebServlet("/ContadorVisitasServlet")
public class ContadorVisitasServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int contador = 0;
        
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie c : cookies ){
               if(c.getName().equals("contadorVisitas")){
                   contador = Integer.parseInt(c.getValue());
                   
               } 
            }
        }
        //Incremetar el contador
        contador++;
        
        //Agregamos la respuesta al navegador
        
        Cookie c = new Cookie("contadorVisitas", Integer.toString(contador));
        
        //La cookie se almacenara en el cliente por una horas
        c.setMaxAge(3600);
        response.addCookie(c);
        
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        out.print("Contador de visitas de cada cliente: " + contador);
        out.close();
    }

}
