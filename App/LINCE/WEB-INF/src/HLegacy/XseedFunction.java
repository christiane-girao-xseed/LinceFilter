package HLegacy;
import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;



public class XseedFunction
{   

private BigDecimal bigZeros = new BigDecimal(0);

/* greater */
/* greater BigDecimal e BigDecimal */
public boolean greater(BigDecimal pSource, BigDecimal pDest)
    throws Exception
{
	BigDecimal bigDec1 = pSource;    					
	BigDecimal bigDec2 = pDest;    					
    if (bigDec1.compareTo(bigDec2) == 1)
    {
    	return (true);
	}
	else   
   	{
    	return (false);
	}      
}


/* less BigDecimal e BigDecimal */
public boolean less(BigDecimal pSource, BigDecimal pDest)
    throws Exception
{
		BigDecimal bigDec1 = pSource;    					
		BigDecimal bigDec2 = pDest;    					

        if (bigDec1.compareTo(bigDec2) == -1)
        {
              return (true);
   		}
   		else   
   		{
              return (false);
        }      
}  

/* equals BigDecimal e BigDecimal */
public boolean equals(BigDecimal pSource, BigDecimal pDest)
    throws Exception
{
	BigDecimal bigDec1 = pSource;    					
	BigDecimal bigDec2 = pDest;    					


    if (bigDec1.compareTo(bigDec2) == 0)
    {
    	return (true);
   	}
   	else   
   	{
    	return (false);
	}      
}  



//string
public String string(int pLen, String pStr)
    throws Exception
{      
	if (pLen == 0)
   	{
		return("");
	}
    
    String wrkStr = pStr;

 	for (int i=1; i < pLen; i++)
   	{   
		wrkStr = wrkStr + pStr;
   	}

   	return(wrkStr);   
}

/* format BigDecimal --> BigDecimal */
public String format(BigDecimal pNumber, String pMask)
    throws Exception
{
    	DecimalFormat myFormatter;  
        String strNumber="0";
    	int numberLength;  
    	int numberDecimals;  
    	int count=0;
    	int index;    	   
	
    	
        // Format de um numero com mascara 999, ### ou ZZZ
        if ( (pMask.indexOf("9") != -1) ||
             (pMask.indexOf("#") != -1) ||
             (pMask.indexOf("Z") != -1) ||
             (pMask.indexOf("*") != -1)) //formato de um numero
    	{
            index = pMask.indexOf(",");
            while (index != -1)
            {
                count++;
           		index = pMask.indexOf(",",index+1);
            }
   
            index = pMask.indexOf(".");
    	    if (index != -1)
            {            	            	
            	numberDecimals = (pMask.substring(index+1)).length();                              	
               	numberLength = pMask.length() - count - 1;    
            }
            else
            {
               numberDecimals = 0;
               numberLength = pMask.length() - count;    
            }      		
   		    
            pNumber = fixBigDec (pNumber, numberLength, numberDecimals,"SIGNED", "TRUNCATE");    	               
            
            
            if (pMask.indexOf("#") != -1)
            {   String str="";
                strNumber = pNumber.toPlainString();        
                
                index = strNumber.indexOf(".");                
                if (index != -1)
                {   if ((strNumber.length() - index - 1) > numberDecimals)
                    {   strNumber = strNumber.substring(0, index + numberDecimals+1);
                    }
                }                
                
                numberLength =  strNumber.length();             
                
                int j=pMask.length()-1;
                                                               
                for (int i = numberLength - 1; i >= 0; i--)
                {   if ((pMask.charAt(j) == '.') || (pMask.charAt(j) == ',') )                      
                    {   str =  pMask.charAt(j) + str ;                                                                    
                        j--;
                    }
                    if (strNumber.charAt(i)!='.')
                    {    str= strNumber.charAt(i) + str;                   
                        j--;
                    }
                    if (j<0) 
                    {    break;
                    }
                                     
                }
                if (str.equals("")==false)
                {   strNumber = str;     
                }                
            }
   
            else if (pMask.indexOf("9") != -1)
            {
                strNumber = pNumber.toPlainString();        

                numberLength =  strNumber.length();
 
                if (numberLength <  pMask.length())
                {
                    for (int i = 0; i < (pMask.length() - numberLength); i++)
                    {   if (pMask.charAt(i) == '9')                            
                        {   strNumber = "0" + strNumber;
                        }
                    }
                }                  
            }
            
            else if (pMask.indexOf("Z") != -1)
            {   String str="";
                strNumber = pNumber.toPlainString();        
                numberLength =  strNumber.length(); 
                
                int j=pMask.length()-1;                                               
                for (int i = numberLength - 1; i >= 0; i--)
                {   if ((pMask.charAt(j) == '.') || (pMask.charAt(j) == ',') )                      
                    {   str =  pMask.charAt(j) + str ;                                                                    
                        j--;
                    }
                    if (strNumber.charAt(i)!='.')
                    {    str= strNumber.charAt(i) + str;                   
                        j--;
                    }
                    if (j<0) 
                    {    break;
                    }
                  
                }
                if (str.equals("")==false)
                {   strNumber = str;     
                    numberLength = str.length();
                }
                
                if (numberLength <  pMask.length())
                {
                   for (int i = 0; i < (pMask.length() - numberLength); i++)
                   {                              
                       strNumber = " " + strNumber;                            
                   }
                }
            }
            
            else if (pMask.indexOf("*") != -1)
            {   String str="";
                strNumber = pNumber.toPlainString();        
                numberLength =  strNumber.length(); 
                
                int j=pMask.length()-1;                                               
                for (int i = numberLength - 1; i >= 0; i--)
                {   if ((pMask.charAt(j) == '.') || (pMask.charAt(j) == ',') )                      
                    {   str =  pMask.charAt(j) + str ;                                                                    
                        j--;
                    }
                    if (strNumber.charAt(i)!='.')
                    {    str= strNumber.charAt(i) + str;                   
                        j--;
                    }
                    if (j<0) 
                    {    break;
                    }
                  
                }
                if (str.equals("")==false)
                {   strNumber = str;     
                    numberLength = str.length();
                }
                
                if (numberLength <  pMask.length())
                {
                   for (int i = 0; i < (pMask.length() - numberLength); i++)
                   {                              
                       strNumber = "*" + strNumber;                            
                   }
                }
            }
        
             if (getDecimalSeparator() != '.')
            {       
               for (int i = 0; i < strNumber.length(); i++)
               {      
             	    if (strNumber.substring(i,i+1).equals(",") == true)
               	    {
                  	strNumber = strNumber.substring(0,i) + "." + strNumber.substring(i+1);
               	    }
               	    else if (strNumber.substring(i,i+1).equals(".") == true)
               	    {
                  	strNumber = strNumber.substring(0,i) + "," + strNumber.substring(i+1);
               	    }
           	}  
            }     

            return(strNumber);

       }  //formato de uma data
       else if (pMask.equals("DD/MM/YY") == true)
       {
          myFormatter = new DecimalFormat("000000");
       	  pMask = myFormatter.format(pNumber); 
       	  return(pMask.substring(0,2) + "/" + pMask.substring(2,4) + "/" +  pMask.substring(4));
       }
       else if (pMask.equals("DD/MM/CCYY") == true)
       {
          myFormatter = new DecimalFormat("00000000");
       	  pMask = myFormatter.format(pNumber);
       	  return(pMask.substring(0,2) + "/" + pMask.substring(2,4) + "/" +  pMask.substring(4));
       }
       else if (pMask.equals("CCYY/MM/DD") == true)
       {
          myFormatter = new DecimalFormat("00000000");
       	  pMask = myFormatter.format(pNumber);
       	  return(pMask.substring(0,4) + "/" + pMask.substring(4,6) + "/" +  pMask.substring(6));
       }
       else
       {
          return(cStr(pNumber));
       }    
}



/* fixBigDec --> BigDecimals */
public BigDecimal fixBigDec (BigDecimal pBigDec, int pLength, int pDecimals, String pSigned, String pRounded)
   throws Exception
{
        BigDecimal bigDec; 
	    BigDecimal bigDecAbs;  	    
  
	    bigDecAbs = pBigDec.abs(); 

	    //arredondamento parte inteira
	    bigDec = bigDecAbs.movePointLeft(pLength -pDecimals);   
	    bigDec= bigDec.setScale(0,1);   
   	    bigDec = bigDec.movePointRight(pLength -pDecimals);        
        if (bigDec.compareTo(new BigDecimal("0.0")) > 0)
        {   bigDecAbs = bigDecAbs.subtract(bigDec);           
  	    }  	    
 		
        //arredondamento parte decimal  
        if (pRounded.equals("ROUNDED") == true)
        {
	        bigDecAbs = bigDecAbs.setScale(pDecimals,4);
  	    }
  	    else
  	    {	
            bigDecAbs = bigDecAbs.setScale(pDecimals,1);   
        }       		  		 		
  		
  	    //Tratamento sinal  
  	    if (pSigned.equals("UNSIGNED") == true)
    	{
            pBigDec = bigDecAbs; 
	    }  
    	else
    	{
            if (pBigDec.compareTo(new BigDecimal("0.0")) == -1)
            {
                pBigDec = bigDecAbs.multiply(new BigDecimal("-1.0"));
            }
            else   
            {
                pBigDec = bigDecAbs;
           }      
	    }    	
  	    return(pBigDec); 
}


/* cStr --> BigDecimal */
public String cStr (BigDecimal pNumber)
    throws Exception
{
 		String str = pNumber.toPlainString();

        if (str.length() >= 2)
        {
  	   		if (str.substring (str.length()-2).equals(".0")==true)
	   		{	
        		str = str.substring(0,str.length()-2);		
	   		}
        } 
		return(str);

}
/* cStr */
public String cStr (double pNumber)
    throws Exception
{
       
    String str = new BigDecimal(new Double(pNumber).toString()).toPlainString(); 
    if (str.substring (str.length()-2).equals(".0")==true)
    {   
           str = str.substring(0,str.length()-2);       
    }
    return(str);
    
}

public BigDecimal multiply(BigDecimal pNumber1, double pNumber2)
    throws Exception
{
    BigDecimal bigDec1 = pNumber1;      
    BigDecimal bigDec2 = new BigDecimal((new Double(pNumber2)).toString());             

	return( bigDec2.multiply(bigDec1) );    
}

public String insertSeparatorMask(String pMask)
    throws Exception
{	    
   	String strDecimal="";
   	String strInteger="";
   	String strMask;
	int i;   
    
	i = pMask.indexOf(".");
   	if (i != -1)
   	{      		
   		strInteger = pMask.substring(0,i);
   		strDecimal = pMask.substring(i);    
   	}
   	else
   	{
   		strInteger = pMask;
   	}
   	
   	i = (int)(strInteger.length()) / 3;   
	
	if ((i * 3) == strInteger.length()) 
    {
    	i = i - 1;
   	}
   	strMask = "";
   	while (i > 0)
    {    	
    	strMask = "," + strInteger.substring(strInteger.length() - 3) + strMask;            
        strInteger = strInteger.substring(0, strInteger.length() - 3);   
        i = i - 1;
   }   
   return(strInteger + strMask + strDecimal);
}


public char getDecimalSeparator()  
{
   DecimalFormatSymbols sb=new DecimalFormatSymbols();
   return sb.getDecimalSeparator();
}


/* twipsperPixel */
public double twipsperPixel(double pValue)
    throws Exception
{    
    return((int)pValue/15);
}


}