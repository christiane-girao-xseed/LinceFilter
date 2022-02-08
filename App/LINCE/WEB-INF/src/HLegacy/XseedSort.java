package HLegacy;
import java.io.*;
import java.util.*;
import java.math.*;

public class XseedSort implements Comparable<Object>
{
	public        String  ordinate[];
	public static boolean descend [];
	public        String  edit[];  
	public        String  buffer;
	
 //	Constructor
    public XseedSort(int numOrd)
    {   int i;
    	ordinate = new String[numOrd];
    	edit = new String[numOrd];
    	for (i=0; i < edit.length; i++)
    	{   edit[i] = "A";
        }
    	
    }
    
 // Overriden Method
    public int compareTo(Object obj)
    {  	int i;
        
    
    	for(i=0; i < ordinate.length; i++)
    	{   if (edit[i].equals("A") == true)
    	    {   if(this.ordinate[i].compareTo(((XseedSort)obj).ordinate[i]) != 0)
    		        break; 
    		}
    		else
            {   if ((new BigDecimal(this.ordinate[i])).compareTo(new BigDecimal(  ((XseedSort)obj).ordinate[i])) !=0)
    		        break;
            }
    		
    	}
    	
    	if(i >= ordinate.length) i = i-1;
    	
    	if (edit[i].equals("A") == true)         
    	{   if(descend[i])
    	    {	return( (this.ordinate[i].compareTo(((XseedSort)obj).ordinate[i])) * -1 );
    	    }
    	    else
    	    {	return(  this.ordinate[i].compareTo(((XseedSort)obj).ordinate[i])       );
    	    }
    	}         
        else
        {  
            
            if(descend[i])
            {   return( (new BigDecimal(this.ordinate[i])).compareTo(new BigDecimal(  ((XseedSort)obj).ordinate[i])) * -1 );
            }
            else
            {   return(  (new BigDecimal(this.ordinate[i])).compareTo(new BigDecimal(  ((XseedSort)obj).ordinate[i])));
            }            
        }
    }
}