import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/*")
  public class MeuFiltro implements Filter {
	
	
	Map<String,String> sessionValues = new HashMap<String, String>();
    
	public void doFilter(ServletRequest request,ServletResponse response, FilterChain chain) throws IOException, ServletException {

    	 //executado na ida

    	/*  long tempoInicial = System.currentTimeMillis();
    	  
    	  String work =  (String)request.getAttribute("GLB_WORK");
    	  String user="";
    	  if (work!=null && work.length()>10)
    	  {
    		  user= work.substring(0,5);    	  
    	  }
          if (sessionValues==null)
          {
        	  sessionValues =new HashMap<String, String>();
          }
          
          String mySession = sessionValues.get(user);

          if (mySession!=null)
          {
        	  request.setAttribute(user, mySession);
          }//Your validation code here.
    	  // boolean returnValue = LOGON.performSessionValidation();
    	  // if(returnValue){
    	  //    //Do Something
    	  // }else{
    	  //   //Do Something else
    	  // }
*/
    	  chain.doFilter(request, response);
/*
    	  //executado na volta
    	  
    	  
          work =  request.getParameter("GLB_WORK");
    	  
    	  //session.setAttribute("GLB_DSN",GLB.DSN);
    	  long tempoFinal = System.currentTimeMillis();
    	//  String uri = request..getRequestURI().substring(request.getContextPath().length()+1);

    			  //((HttpServletRequest)request.getRequestURI();
    	  
    	  //String parametros = ((HttpServletRequest) request)
    	  //    .getParameter("logica");
    	  
    	 
    	  System.out.println("Tempo da requisicao de " + work
    	        + "?logica="
    	       // + parametros + " demorou (ms): "
    	        + (tempoFinal - tempoInicial));*/
    }

  }