package HLegacy;
import java.io.*;


public class XseedBackupFilter implements FilenameFilter
{
	String bkpOwner;
	String bkpDate = "";
	String bkpName;
	
    public XseedBackupFilter(String pOwner, String pDate, String pBkpName)
	{
		bkpOwner = pOwner.trim().toUpperCase();
		bkpDate = pDate;
		bkpName = pBkpName.trim().toUpperCase();        
	}
	
	
    public XseedBackupFilter(String pOwner, String pBkpName)
	{
		bkpOwner = pOwner.trim().toUpperCase();
		bkpName = pBkpName.trim().toUpperCase();        
	}
		
	public boolean accept (File pDir, String pName)
	{	int ind1;		
	    int ind2;
		String fileName = pName.trim().toUpperCase();
		String fileOwner = "";
		String fileDate = "";		
		String fileBkp= "";

		ind1 = fileName.indexOf("(");

		if (ind1 != -1)
		{	fileOwner = fileName.substring(0, ind1);		
			ind2 = fileName.indexOf(" ", ind1 + 1);
			if (ind2 != -1)
			{
				fileDate = fileName.substring(ind1+1,ind2);				
            }
		}
		else
        {  return(acceptInnovation(pDir,pName));
	    }        
		// Utilizado somente para a tela BKPRO
		if (bkpOwner.equals(""))
		{
			if (bkpDate.equals("") == true)
			{
			
				if (bkpName.equals("") == true)	
				{						
					return (true);
				}
				else
				{	ind1 = fileName.indexOf(")");
					if (ind1 != -1)
					{	
						fileBkp = fileName.substring(ind1+1);														
						if (bkpName.length()< fileBkp.length())
						{
							fileBkp = fileBkp.substring(0,bkpName.length());
						}				
						return (bkpName.equals(fileBkp));
					}								
					return (true);
				}
			}
			else
			{
				String bkpFileDate = bkpDate.substring(4) + "-" +bkpDate.substring(2,4) + "-" + bkpDate.substring(0,2);		
				
				if (bkpName.equals("") == true)	
				{	
					return(bkpFileDate.equals(fileDate));
					
				}
				else
				{	ind1 = fileName.indexOf(")");
					if (ind1 != -1)
					{	
						fileBkp = fileName.substring(ind1+1);														
						if (bkpName.length()< fileBkp.length())
						{
							fileBkp = fileBkp.substring(0,bkpName.length());
						}				
						return(bkpFileDate.equals(fileDate) &&  bkpName.equals(fileBkp));						
					}			
					return(bkpFileDate.equals(fileDate));					
				}
			}
		}
		else
		{
			// Utilizado somente para a tela BKP
			if (bkpDate.equals("") == true)
			{
				if (bkpName.equals("") == true)	
				{				
					return(bkpOwner.equals(fileOwner));
				}
				else
				{	ind1 = fileName.indexOf(")");
					if (ind1 != -1)
					{	
						fileBkp = fileName.substring(ind1+1);														
						if (bkpName.length()< fileBkp.length())
						{
							fileBkp = fileBkp.substring(0,bkpName.length());
						}										
						return(bkpOwner.equals(fileOwner) && bkpName.equals(fileBkp));
					}								
					return(bkpOwner.equals(fileOwner));
				}
			}
			else
			{
				String bkpFileDate = bkpDate.substring(4) + "-" +bkpDate.substring(2,4) + "-" + bkpDate.substring(0,2);		
				
				if (bkpName.equals("") == true)	
				{	
                   return(bkpOwner.equals(fileOwner) && bkpFileDate.equals(fileDate));					
				}
				else
				{	ind1 = fileName.indexOf(")");
					if (ind1 != -1)
					{	
						fileBkp = fileName.substring(ind1+1);														
						if (bkpName.length()< fileBkp.length())
						{
							fileBkp = fileBkp.substring(0,bkpName.length());
						}			
                        return(bkpOwner.equals(fileOwner) && bkpFileDate.equals(fileDate) &&  bkpName.equals(fileBkp));
						
					}	
					return(bkpOwner.equals(fileOwner) && bkpFileDate.equals(fileDate));					
				}
			}
		}
		
	}	
    
    public boolean acceptInnovation (File pDir, String pName)
    {   int ind1;       
        int ind2;
        String fileName = pName.trim().toUpperCase();
        String fileOwner = "";
        String fileDate = "";       
        String fileBkp= "";

        ind1 = fileName.indexOf("-");
        if (ind1 != -1)
        {   fileOwner = fileName.substring(0, ind1);                
            ind2 = fileName.indexOf("-", ind1 + 1);
            if (ind2 != -1)
            {
                fileBkp = fileName.substring(ind1+1,ind2);             
            
            	ind1 = fileName.indexOf("-", ind2 + 1);
            	if (ind1 != -1)
            	{
                	String xDate = fileName.substring(ind2+1,ind1);                   
                	fileDate = xDate.substring(0,4) + "-"+ xDate.substring(4,6) + "-"+ xDate.substring(6);
           	 	}
            }
        }
        // Utilizado somente para a tela BKPRO
        if (bkpOwner.equals(""))
        {
            
            if (bkpDate.equals("") == true)
            {
                if (bkpName.equals("") == true) 
                {   
                    return (true);
                }
                else
                {   ind1 = fileName.indexOf("-");
                    if (ind1 != -1)
                    {   
                        fileBkp = fileName.substring(ind1+1);                                                       
                        if (bkpName.length()< fileBkp.length())
                        {
                            fileBkp = fileBkp.substring(0,bkpName.length());
                        }               
                        return (bkpName.equals(fileBkp));
                    }           
                    return (true);
                }
            }
            else
            {                
                String bkpFileDate = bkpDate.substring(4) + "-" +bkpDate.substring(2,4) + "-" + bkpDate.substring(0,2);     
        
                if (bkpName.equals("") == true) 
                {   
                    return(bkpFileDate.equals(fileDate));                    
                }
                else
                {   ind1 = fileName.indexOf("-");
                    if (ind1 != -1)
                    {   
                        fileBkp = fileName.substring(ind1+1);                                                       
                        if (bkpName.length()< fileBkp.length())
                        {
                            fileBkp = fileBkp.substring(0,bkpName.length());
                        }            
                        return(bkpFileDate.equals(fileDate) &&  bkpName.equals(fileBkp));                        
                    }                               
                    return(bkpFileDate.equals(fileDate));                    
                }
            }
        }
        else
        {
            
            // Utilizado somente para a tela BKP
            if (bkpDate.equals("") == true)
            {
                if (bkpName.equals("") == true) 
                {               
                    return(bkpOwner.equals(fileOwner));
                }
                else
                {   ind1 = fileName.indexOf("-");
                    if (ind1 != -1)
                    {   
                        fileBkp = fileName.substring(ind1+1);                                                       
                        if (bkpName.length()< fileBkp.length())
                        {
                            fileBkp = fileBkp.substring(0,bkpName.length());
                        }               
               
                        return(bkpOwner.equals(fileOwner) && bkpName.equals(fileBkp));
                    }                         
                    return(bkpOwner.equals(fileOwner));
                }
            }
            else
            {
                String bkpFileDate = bkpDate.substring(4) + "-" +bkpDate.substring(2,4) + "-" + bkpDate.substring(0,2);                    
                if (bkpName.equals("") == true) 
                {                   
                    return(bkpOwner.equals(fileOwner) && bkpFileDate.equals(fileDate));                    
                }
                else
                {   ind1 = fileName.indexOf("-");
                    if (ind1 != -1)
                    {   
                        fileBkp = fileName.substring(ind1+1);                                                       
                        if (bkpName.length()< fileBkp.length())
                        {
                            fileBkp = fileBkp.substring(0,bkpName.length());
                        }               
                        return(bkpOwner.equals(fileOwner) && bkpFileDate.equals(fileDate) &&  bkpName.equals(fileBkp));
                        
                    }                               
                    return(bkpOwner.equals(fileOwner) && bkpFileDate.equals(fileDate));
                    
                }
            }
        }        
    }       
    
}