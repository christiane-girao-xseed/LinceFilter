package HLegacy;
import java.io.*;


public class XseedNameFilter implements FilenameFilter
{
	String bkpOwner;
	String bkpDate;
	String bkpName;
	
	public XseedNameFilter(String pOwner, String pDate, String pBkpName)
	{
		bkpOwner = pOwner.trim().toUpperCase();
		bkpDate = pDate;
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
			ind2 = fileName.indexOf(" ", ind1 +1);
			if (ind2 != -1)
			{
				fileDate = fileName.substring(ind1+1,ind2);				
			}
		}
		
		String bkpFileDate = bkpDate.substring(4) + "-" +bkpDate.substring(2,4) + "-" + bkpDate.substring(0,2);		
		
		if (bkpName.equals("") == true)	
		{	return(bkpOwner.equals(fileOwner) && bkpFileDate.equals(fileDate));
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