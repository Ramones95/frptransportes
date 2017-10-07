/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AutorizacaoDeAcesso;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Cliente;
import model.PerfilDeAcesso;
import model.Usuario;
import util.Conf;

/**
 *
 * @author Ramon Cordeiro
 */
public class AcessoCliente implements Filter{

    public void init(FilterConfig filterConfig) throws ServletException {
       
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        
             HttpServletRequest req = (HttpServletRequest) request;       
        
        
        
        
        Cliente cliente = (Cliente) req.getSession().getAttribute("clienteAutenticado");
        
        
        if(cliente!=null){
            chain.doFilter(request, response);
            
            
        }else{
            
            ((HttpServletResponse)response).sendRedirect(Conf.getCaminhoContexto()+"acessoNegado.jsp");
        }
        
    }

    @Override
    public void destroy() {
        
    }
    
    
    
}
