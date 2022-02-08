package HLegacy;
import java.util.Date;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

import java.io.File;
import javax.activation.*;

public class XseedEmail
{

   public String SMTPUser;
   public String SMTPPassword;
   public String host;
   public String subject;
   public String from;
   public String to;
   public String cc;
   public String bcc; 				
   public String attach;
   public String text = "";
   public String html = "";
   
   public String protocol ="smtp";
   public String port="";
   
   public String exception;
   

	/* splitElements */
	private String[] splitElements(String elements)
    	throws Exception
	{
    	try
    	{
    		int n   = 0;
    		int ini = 0;
	    	int pos = 0;
    
    		if ((elements != null) && (elements.equals("") == false))
	    	{
    			for(int i=0; i < elements.length(); i++)
    			{
    				if (elements.charAt(i) == ';') n++;
	        	}
       			if(elements.charAt(elements.length() - 1) != ';')
       			{
    	   			n++;
	       		}
            
        		String arrayElem[] = new String[n];
        
        		for (int i=0; i < elements.length(); i++)
    	    	{
	        		if (elements.charAt(i) == ';')
	            	{
               			arrayElem[pos] = elements.substring(ini,i).trim();
            	    	ini = i + 1;
        	        	pos++;
    	        	}
	        	}
        		if(pos <= (arrayElem.length -1))
        		{
    	    		arrayElem[pos] = elements.substring(ini, elements.length()).trim();
	        	}
        		return(arrayElem);
    		}
	    	return(null);
    	}
	    catch (Exception e)
    	{
    		exception = e.toString();
    		return(null);
    	}	
	}

   public void send()
    {
        try
        {
        	if(html.trim().equals("")==true)
        	{
        		sendText();	
        	}
        	else
        	{
        		sendHtml();
        	}
        }
        catch(Exception ex)
        {
            exception = ex.toString();            
        }
    }


   public void sendHtml()
    {

        try
        {

            Properties props = new Properties(); 
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.auth", "true");            
//            props.put("mail.debug", "false");

			//Cria uma MailSession
            Session sessao = Session.getDefaultInstance(props, null); 
			sessao.setDebug(false);

            //Cria a Mensagem
            MimeMessage message = new MimeMessage(sessao); 

            //From
            message.setFrom(new InternetAddress(from));
            
	        //Subject
    	    message.setSubject(subject, "UTF-8");
    	
        	//Data
        	message.setSentDate(new java.util.Date());
        	        	
	        //Tratamento de erro para To e Cc
    	    String arrayTo[] = splitElements(to);
        	String arrayCc[] = splitElements(cc);
        	String arrayBcc[] = splitElements(bcc);        	
        		        
	        if ((arrayTo == null) & (arrayCc == null) & (arrayBcc == null))		
        	{				
				throw new MessagingException("There must be at least one name or distribution list in the To, Cc or Bcc box."); 
        	}        	
        	
        	//To
        	if (arrayTo != null)
        	{
	        	InternetAddress address[] = new InternetAddress[arrayTo.length];
    	        for (int i=0; i < arrayTo.length; i++)
        	    {
            		address[i] = new InternetAddress(arrayTo[i]);
        	    }
    	        message.setRecipients(Message.RecipientType.TO, address);
	        }
        	
            
		    // Cc
	        if (arrayCc != null)
    	    {
        		InternetAddress addressCc[] = new InternetAddress[arrayCc.length];
            	for (int i=0; i < arrayCc.length; i++)
	            {
    	            addressCc[i] = new InternetAddress(arrayCc[i]);
        	    }   
            	message.setRecipients(Message.RecipientType.CC, addressCc);
        	}
        	
            //Bcc
            if (arrayBcc != null)
    	    {
        		InternetAddress addressBcc[] = new InternetAddress[arrayBcc.length];
            	for (int i=0; i < arrayBcc.length; i++)
	            {
    	            addressBcc[i] = new InternetAddress(arrayBcc[i]);
        	    }   
            	message.setRecipients(Message.RecipientType.BCC, addressBcc);
        	}
        	
	        // Attachments
	        String arrayAt[] = splitElements(attach);
        	if (arrayAt != null)   
        	{       	
    			//Cria parte de texto do email
	    		MimeBodyPart plainTextPart = new MimeBodyPart ();
	    		plainTextPart.setContent (text, "text/plain");
 			   			    		
	    		//Cria parte de html do email
    			MimeBodyPart htmlTextPart = new MimeBodyPart ();    			
    			htmlTextPart.setContent(html, "text/html; charset=\"UTF-8\"");
    			
        		Multipart mp = new MimeMultipart("alternative");
    			//adiciona parte de texto ao email
    			mp.addBodyPart (plainTextPart);
    			//adiciona parte de html ao email
	    		mp.addBodyPart (htmlTextPart);   	    		
        	    
            	FileDataSource fds;
            	
            	MimeBodyPart mbp2[] = new MimeBodyPart[arrayAt.length];
                for (int i=0; i < arrayAt.length; i++)
    	        {

        			File attachFile = new File(arrayAt[i]);
			        if (attachFile.exists() == false)
        			{
						throw new MessagingException("Unable to attach file. Could not find file '" + arrayAt[i] + "'.");
        	    	}
        	    	
        	    	fds = new FileDataSource(arrayAt[i]);
	
    	            mbp2[i] = new MimeBodyPart();
        	        mbp2[i].setDataHandler(new DataHandler(fds));
            	    mbp2[i].setFileName(fds.getName());
            	    mbp2[i].setHeader("Content-ID", "imageID" + i);
                	mp.addBodyPart(mbp2[i]);
            	}
        	    message.setContent(mp);
        	}
        	else // Sem Attachments
	        {     	        			      			
    			
    			//Cria parte de texto do email
	    		MimeBodyPart plainTextPart = new MimeBodyPart ();
	    		plainTextPart.setContent (text, "text/plain");
 			   			    		
	    		//Cria parte de html do email
    			MimeBodyPart htmlTextPart = new MimeBodyPart ();    			
    			htmlTextPart.setContent(html, "text/html; charset=\"UTF-8\"");
	            	
    			MimeMultipart mp = new MimeMultipart ("alternative");
    			//adiciona parte de texto ao email
    			mp.addBodyPart (plainTextPart);
    			//adiciona parte de html ao email
	    		mp.addBodyPart (htmlTextPart);    		
	    		
    			MimeBodyPart mpp = new MimeBodyPart ();
    			mpp.setContent (mp);
    		
				MimeMultipart related = new MimeMultipart ("related");
    			related.addBodyPart (mpp);	
                
            	message.setContent (related);
    	       	       	    
    	    }

            message.saveChanges();


            Transport transport = sessao.getTransport("smtp"); 

            transport.connect(host, SMTPUser, SMTPPassword);

            transport.sendMessage(message, message.getAllRecipients()); 

            transport.close(); 

            exception = "";

        }
        catch(AddressException ex)
        {
       		exception = ex.toString();            
        }
        catch(MessagingException ex)
        {
			exception = ex.toString();            
        }
        catch(Exception ex)
        {
            exception = ex.toString();            
        }
    }
    

   public void sendText()
    {

        try
        {

            Properties props = new Properties(); 
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.auth", "true");

			//Cria uma MailSession
            Session sessao = Session.getDefaultInstance(props, null); 

            //Cria a Mensagem
            MimeMessage message = new MimeMessage(sessao); 

            //From
            message.setFrom(new InternetAddress(from));
            
	        //Subject
    	    message.setSubject(subject);

        	//Data
        	message.setSentDate(new java.util.Date());
        	        	
	        //Tratamento de erro para To e Cc
    	    String arrayTo[] = splitElements(to);
        	String arrayCc[] = splitElements(cc);        	
        	String arrayBcc[] = splitElements(bcc);		
        		        
	        if ((arrayTo == null) & (arrayCc == null) & (arrayBcc == null))		
        	{				
				throw new MessagingException("There must be at least one name or distribution list in the To,Cc or Bcc box.");	
        	}        	
        	
        	//To
        	if (arrayTo != null)
        	{
	        	InternetAddress address[] = new InternetAddress[arrayTo.length];
    	        for (int i=0; i < arrayTo.length; i++)
        	    {
            		address[i] = new InternetAddress(arrayTo[i]);
        	    }
    	        message.setRecipients(Message.RecipientType.TO, address);
	        }
        	
            
		    // Cc
	        if (arrayCc != null)
    	    {
        		InternetAddress addressCc[] = new InternetAddress[arrayCc.length];
            	for (int i=0; i < arrayCc.length; i++)
	            {
    	            addressCc[i] = new InternetAddress(arrayCc[i]);
        	    }   
            	message.setRecipients(Message.RecipientType.CC, addressCc);
        	}        	

        	//Bcc
        	if (arrayBcc != null)
    	    {
        		InternetAddress addressBcc[] = new InternetAddress[arrayBcc.length];
            	for (int i=0; i < arrayBcc.length; i++)
	            {
    	            addressBcc[i] = new InternetAddress(arrayBcc[i]);
        	    }   
            	message.setRecipients(Message.RecipientType.BCC, addressBcc);
        	}      

	        // Attachments
	        String arrayAt[] = splitElements(attach);
        	if (arrayAt != null)   
        	{
        		Multipart mp = new MimeMultipart();
        	
	        	MimeBodyPart mbp1 = new MimeBodyPart();
    	        mbp1.setText(text);
        	    mp.addBodyPart(mbp1);
        	    
            	FileDataSource fds;
            	
            	MimeBodyPart mbp2[] = new MimeBodyPart[arrayAt.length];
                for (int i=0; i < arrayAt.length; i++)
    	        {

        			File attachFile = new File(arrayAt[i]);
			        if (attachFile.exists() == false)
        			{
						throw new MessagingException("Unable to attach file. Could not find file '" + arrayAt[i] + "'.");
        	    	}
        	    	
        	    	fds = new FileDataSource(arrayAt[i]);
	
    	            mbp2[i] = new MimeBodyPart();
        	        mbp2[i].setDataHandler(new DataHandler(fds));
            	    mbp2[i].setFileName(fds.getName());
                	mp.addBodyPart(mbp2[i]);
            	}
        	    message.setContent(mp);
        	}
        	else // Sem Attachments
	        {  
    	        message.setText(text);
    	    }

            message.saveChanges();

            Transport transport = sessao.getTransport("smtp"); 

            transport.connect(host, SMTPUser, SMTPPassword);

            transport.sendMessage(message, message.getAllRecipients()); 

            transport.close(); 

            exception = "";

        }
        catch(AddressException ex)
        {
       		exception = ex.toString();            
        }
        catch(MessagingException ex)
        {
			exception = ex.toString();            
        }
        catch(Exception ex)
        {
            exception = ex.toString();            
        }
    }
   
   public void sendEmail()
   {
	   try
	   {
		   if(html.trim().equals("")==true)
       	   {
       	       sendText();
       	       return; 
       	   }
		   
		   
		   Properties mailProps = new Properties();
		   mailProps.put("mail.transport.protocol", this.protocol);
		   mailProps.put("mail.smtp.auth", "true");    
		   
		   Session mailSession = Session.getDefaultInstance(mailProps, null);

		   mailSession.setDebug(false);

		   MimeMessage mailMessage = new MimeMessage(mailSession);
		   mailMessage.setSentDate(new Date());
		   
		   mailMessage.setFrom(new InternetAddress(this.from));

		   String[] arrayTo = splitElements(this.to);
		   String[] arrayCc = splitElements(this.cc);
		   String[] arrayBcc = splitElements(this.bcc);
		   if (((arrayTo == null ? 1 : 0) & (arrayCc == null ? 1 : 0) & (arrayBcc == null ? 1 : 0)) != 0) {
			   throw new MessagingException("There must be at least one name or distribution list in the To,Cc or Bcc box.");
		   }
		   if (arrayTo != null)
		   {
			   InternetAddress[] address = new InternetAddress[arrayTo.length];
			   for (int i = 0; i < arrayTo.length; i++) {
				   address[i] = new InternetAddress(arrayTo[i]);
			   }
			   mailMessage.setRecipients(Message.RecipientType.TO, address);
		   }
		   if (arrayCc != null)
		   {
			   InternetAddress[] addressCc = new InternetAddress[arrayCc.length];
			   for (int i = 0; i < arrayCc.length; i++) {
				   addressCc[i] = new InternetAddress(arrayCc[i]);
			   }
			   mailMessage.setRecipients(Message.RecipientType.CC, addressCc);
		   }
		   if (arrayBcc != null)
		   {
			   InternetAddress[] addressBcc = new InternetAddress[arrayBcc.length];
			   for (int i = 0; i < arrayBcc.length; i++) {
				   addressBcc[i] = new InternetAddress(arrayBcc[i]);
			   }
			   mailMessage.setRecipients(Message.RecipientType.BCC, addressBcc);
		   }
		   MimeBodyPart mbpMensagem = new MimeBodyPart();
		   mbpMensagem.setHeader("Content-Type", "text/html; charset=\"UTF-8\"");
		   mbpMensagem.setContent(this.html, "text/html; charset=\"UTF-8\"");
		   mbpMensagem.setHeader("Content-Transfer-Encoding", "quoted-printable");
		   
		   Multipart mp = new MimeMultipart();
		   mp.addBodyPart(mbpMensagem);

		   String[] arrayAt = splitElements(this.attach);
		   if (arrayAt != null)
		   {
			   MimeBodyPart[] mbpAttach = new MimeBodyPart[arrayAt.length];
			   for (int i = 0; i < arrayAt.length; i++)
			   {
				   File attachFile = new File(arrayAt[i]);
				   if (!attachFile.exists()) {
					   throw new MessagingException("Unable to attach file. Could not find file '" + arrayAt[i] + "'.");
				   }
				   DataSource source = new FileDataSource(arrayAt[i]);				  
				   mbpAttach[i] = new MimeBodyPart();
				   mbpAttach[i].setDataHandler(new DataHandler(source));
				   mbpAttach[i].setFileName(attachFile.getName());
				   mp.addBodyPart(mbpAttach[i]);
			   }
		   }
		   mailMessage.setSubject(this.subject, "UTF-8");
		
		   mailMessage.setContent(mp, "text/html; charset=\"UTF-8\"");

		   Transport transport = mailSession.getTransport(this.protocol);	  
		   transport.connect(this.host, this.SMTPUser, this.SMTPPassword);
		   transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
		   transport.close();
		   this.exception = "";
	   }
	   catch (AddressException ex)
	   {
		   this.exception = ex.toString();
	   }
	   catch (MessagingException ex)
	   {
		   this.exception = ex.toString();
	   }
	   catch (Exception ex)
	   {
		   this.exception = ex.toString();
	   }
   }


    
}    