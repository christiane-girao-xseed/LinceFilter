package HLegacy;
import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;

public class XseedFunctions
{

static private BigDecimal bigZeros = new BigDecimal(0);
static public String routineErrorMsg="";
static public String routineErrorName="";

/* greater */
/* greater BigDecimal e BigDecimal */
static public boolean greater(BigDecimal pSource, BigDecimal pDest)
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
static public boolean less(BigDecimal pSource, BigDecimal pDest)
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
static public boolean equals(BigDecimal pSource, BigDecimal pDest)
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
static public String string(int pLen, String pStr)
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
static public String format(BigDecimal pNumber, String pMask)
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
static public BigDecimal fixBigDec (BigDecimal pBigDec, int pLength, int pDecimals, String pSigned, String pRounded)
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

/* cDbl */
static public double cDbl (String pText)
 throws Exception
{
    try
    {
        // Retira ,
        int positionComman = pText.indexOf(",");
        if (positionComman != -1)
        {
            pText = pText.substring(0, positionComman) + pText.substring(positionComman + 1);
        }
        if (isNumeric(pText) == false)
        {
            return(0.0);
        }
        return(Double.valueOf(pText).doubleValue());
    }
    catch (Exception e)
    {
        return (0.0);
    }
}

/* cStr --> BigDecimal */
static public String cStr (BigDecimal pNumber)
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
static public String cStr (double pNumber)
    throws Exception
{

    String str = new BigDecimal(new Double(pNumber).toString()).toPlainString();
    if (str.substring (str.length()-2).equals(".0")==true)
    {
           str = str.substring(0,str.length()-2);
    }
    return(str);

}

static public BigDecimal multiply(BigDecimal pNumber1, double pNumber2)
    throws Exception
{
    BigDecimal bigDec1 = pNumber1;
    BigDecimal bigDec2 = new BigDecimal((new Double(pNumber2)).toString());

	return( bigDec2.multiply(bigDec1) );
}

static public String insertSeparatorMask(String pMask)
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

//isNumeric
static public boolean isNumeric(String InputTXT)
    throws Exception
{
    try
    {   String lastChar = (InputTXT.substring(InputTXT.length() -1)).toUpperCase();
        if ((lastChar.equals("D") == true) || (lastChar.equals("F") == true))
            return(false);

        double doubleNumber = Double.valueOf(InputTXT).doubleValue();
        return (true);
    }
    catch (Exception e)
    {
        return (false);
    }
}

static public char getDecimalSeparator()
{
   DecimalFormatSymbols sb=new DecimalFormatSymbols();
   return sb.getDecimalSeparator();
}


/* twipsperPixel */
static public double twipsperPixel(double pValue)
    throws Exception
{
    return((int)pValue/15);
}

//FileExists
static public boolean FileExists(String pFileName)
    throws Exception, IOException
{
    try
    {
        File wFile;
        wFile = new File(pFileName);
        return(wFile.exists());

    }
    catch(Exception e)
    {
        return(false);
    }
}

//FileCopy
static public boolean FileCopy(String pSource,String pDest)
    throws Exception, IOException
{
    try
    {
        File wFileSource;
        File wFileDest;
        wFileSource = new File(pSource);
        wFileDest = new File(pDest);
        if (wFileDest.exists())
        {
            wFileDest.delete();
        }
        wFileSource.renameTo(wFileDest);
        wFileSource=null;
        wFileDest=null;
        return(true);

    }
    catch(Exception e)
    {
        return(false);
    }
}

/* replaceChar */
static String replaceChar(String pStr, char inputChar, char outputChar)
   throws Exception
{
    int i=0;
    String strReturn;
    strReturn="";
    for ( i = 0; i < pStr.length(); i++)
    {   if (pStr.charAt(i) == inputChar)
        {
            strReturn = strReturn + outputChar;
        }
        else
        {
            strReturn = strReturn +pStr.charAt(i);
        }
    }
    return(strReturn);
}

/* rTrim */
static public String rTrim(String pStr)
    throws Exception
{
    try
    {
        int len;
        String wrkStr = pStr;
        if (wrkStr==null)
        {
           return("");
        }
        len = wrkStr.length();
        for (int i=len;i>=1;i--)
        {
            if (wrkStr.substring(i-1,i).equals(" ") == true)
            {
                wrkStr= pStr.substring(0,i-1);
            }
            else
            {
                break;
            }
        }
        return(wrkStr);
    }
    catch(Exception e)
    {
        return(pStr);
    }
}
/* rTrim */
static public String rTrim(String pStr, String pChar)
    throws Exception
{
    try
    {
        String wrkStr = pStr;
        if (wrkStr==null)
        {
           return("");
        }
        wrkStr = pChar + wrkStr;
        wrkStr= wrkStr.trim();
        wrkStr = wrkStr.substring(1);

        return(wrkStr);
    }
    catch(Exception e)
    {
        ShowErrorMsg (e,"rTrim");
        return(pStr);
    }
}

// Funções para cima sem tratamento error

/* ShowErrorMsg */
static public void ShowErrorMsg(Exception e, String routineName)
    throws Exception
{
    if (routineErrorMsg.equals("") == true)
    {
		StringWriter strwrt = new StringWriter();
       	PrintWriter out = new PrintWriter(strwrt);
       	e.printStackTrace(out);
       	routineErrorMsg = strwrt.toString();

       	if (routineErrorMsg == null)
       	{
           routineErrorMsg = "Null";
       	}
    }

    if (routineErrorName.equals("") == true)
    {
    	routineErrorName = routineName;
    }
    else
    {
    	routineErrorName = routineErrorName + ", " + routineName;
    }
   // RollBackTransaction(routineErrorMsg);  --> somente para ispec
    throw e;
}

///group
/* cDec --> BigDecimal */
static public BigDecimal cDec (double pNumber)
 throws Exception
{
    try
    {
        return(new BigDecimal((new Double(pNumber)).toString()));
    }
    catch (Exception e)
    {
        ShowErrorMsg (e, "cDec");
        return (new BigDecimal(0));
    }
}
/* cDec --> BigDecimal */
static public BigDecimal cDec (String pText)
 throws Exception
{
    try
    {
        pText = pText.trim();
        // Retira ,
        int positionComman = pText.indexOf(",");

        if (positionComman != -1)
        {
            pText = pText.substring(0, positionComman) + pText.substring(positionComman + 1);
        }

        if (isNumeric(pText) == false)
        {
            return(new BigDecimal(0));
        }

        return(new BigDecimal(pText));
    }
    catch (Exception e)
    {
        ShowErrorMsg (e, "cDec");
        return (new BigDecimal(0));
    }
}

//
/////////////
//getArrayChar
//
static public char [] getArrayChar (String pStr, int pLength)
throws Exception
{
	char init[]= new char [pLength];
	int charPosition =0;
    try
    {
    	int len=0;
		if (pLength < pStr.length())
        {
        	len = pLength;
    	}
    	else
    	{
    		len = pStr.length();
    	}

        for (int i=0;i<len;i++)
        {
        	init [charPosition++] = pStr.charAt(i);
		}

		if (len < pLength)
		{
			for (int i=len;i<pLength;i++)
			{
				init [charPosition++] = ' ';
			}
		}
        return(init);
    }
    catch (Exception e)
    {
        ShowErrorMsg (e, "getArrayChar");
        return (init);
    }
}

// subItemNumeric
static public String subItemNumeric(String pGroup, int pInit, int pEnd, int pLength, int pDecimals, String pSigned)
    throws Exception
{
	try
    {
        double number;
        char init[];

        int charPosition =1;
        int end;

        //espaço do numerico não preeenchido
        if (pInit>pGroup.length())
        {
        	return("0");
        }

        init =new char [pLength+2];

        //Le espaco preenchido pelo numero
        if (pEnd < pGroup.length())
        {
        	end = pEnd;
    	}
    	else
    	{
    		end = pGroup.length();
    	}

        for (int i=pInit;i<end;i++)
        {
        	if (pGroup.charAt(i)==' ')
        		init [charPosition++]='0';
        	else
        		init [charPosition++] = pGroup.charAt(i);
		}

		//preenche com 0 os espacos vazios
		if (end < pEnd)
		{
			for (int i=end;i<pEnd;i++)
			{
				init [charPosition++] = '0';
			}
		}

		charPosition --;
		//se sinalizado

		if (pSigned.equals("SIGNED")==true)
		{
			switch (init[charPosition])
			{
				case 'p' :
				{	init[charPosition]='0';
					init[0]='-';
					break;
				}
				case 'q':
				{	init[charPosition]='1';
					init[0]='-';
					break;
				}
				case 'r':
				{	init[charPosition]='2';
					init[0]='-';
					break;
				}
				case 's' :
				{	init[charPosition]='3';
					init[0]='-';
					break;
				}
				case 't':
				{	init[charPosition]='4';
					init[0]='-';
					break;
				}
				case 'u' :
				{	init[charPosition]='5';
					init[0]='-';
					break;
				}
				case 'v' :
				{	init[charPosition]='6';
					init[0]='-';
					break;
				}
				case 'w' :
				{	init[charPosition]='7';
					init[0]='-';
					break;
				}
				case 'x' :
				{	init[charPosition]='8';
					init[0]='-';
					break;
				}
				case 'y' :
				{	init[charPosition]='9';
					init[0]='-';
					break;
				}
			}
		}
		if (pDecimals > 0)
		{
			for (int i=charPosition+1;i>pLength-pDecimals;i--)
			{
				init [i] = init[i-1];
			}
			init[pLength-pDecimals + 1]= '.';
		}

	    return(String.valueOf(init,0,pLength+2));
    }
    catch (Exception e)
    {
        ShowErrorMsg (e,"subItem");
    	return("");
    }
}

//subItem
static public double subItem(double pItem, String pGroup, int pInit, int pEnd, int pLength, int pDecimals, String pSigned)
    throws Exception
{
    try
    {
    	pItem = cDbl(subItemNumeric(pGroup,pInit,pEnd,pLength,pDecimals, pSigned));

        return(pItem);
    }
    catch (Exception e)
    {
        ShowErrorMsg (e,"subItem");
    	return(0);
    }
}

// subItem
static public BigDecimal subItem(BigDecimal pItem, String pGroup, int pInit, int pEnd, int pLength, int pDecimals, String pSigned)
    throws Exception
{
    try
    {
    	pItem = cDec(subItemNumeric(pGroup,pInit,pEnd,pLength, pDecimals, pSigned));
        return(pItem);
    }
    catch (Exception e)
    {
        ShowErrorMsg (e,"subItem");
    	return(new BigDecimal(0));
    }
}

// subItem
static public String subItem(String pItem, String pGroup ,int pInit, int pEnd,int pLength)
   throws Exception
{
   try
   {
   		if (pGroup.length() < pInit)
   		{
   			return("");
   		}
   		else if (pGroup.length() < pEnd)
       	{
       		return(pGroup.substring(pInit));
       	}
       	return(rTrim(pGroup.substring(pInit,pEnd),"*"));

   }
   catch (Exception e)
   {
      ShowErrorMsg (e,"subItem");
      return("");
   }
}


// groupItem
static public String groupItem(String pItem, String pGroup, int pInit, int pEnd, int pLength)
    throws Exception
{
    try
    {

		String retString="";
		//group menor que posição inicial
		if (pGroup.length() < pInit)
		{
			char groupChar[] = new char[pInit];
			groupChar = getArrayChar(pGroup,pInit);
			retString =String.valueOf(groupChar,0,pInit) + pItem ;
			groupChar=null;

		}
		else if (pGroup.length() < pEnd)
		{	retString =pGroup.substring(0, pInit) + pItem ;
		}
		else
		{
			char itemChar[] = new char[pLength];
			itemChar = getArrayChar(pItem,pLength);
			retString = pGroup.substring(0, pInit) + String.valueOf(itemChar,0,pLength) + pGroup.substring(pEnd);
			itemChar=null;

    	}
    	retString =rTrim (retString,"*");
    	pItem = rTrim (pItem,"*");
    	return(retString);

    }
    catch (Exception e)
    {
        ShowErrorMsg (e,"groupItem");
    	return("");
    }
}


static public String groupItemNumeric (String pStrItem, String pGroup, int pInit, int pEnd, int pLength, int pDecimals, int pIsNegNumber)
   throws Exception
{
	try
	{
		String retString="";
		int i=0;
		int charPosition=0;
		int ind =0;
		String strLen="";
		String strDec="";
		int len = pLength;
		char itemChar[] = new char[pLength];

		if (pDecimals==0)
		{
		    // Ajuste LE
            if (pStrItem.length() > pLength)
            {
                pStrItem = pStrItem.substring(pStrItem.length() - pLength );
            }

			//preenche item com 0 no espaços vazio
			for (i=0;i<len-pStrItem.length();i++)
			{
				itemChar [charPosition++] = '0';
			}

			for(i=0;i<pStrItem.length();i++)
			{
				itemChar [charPosition++] = pStrItem.charAt(i);
        	}

		}
		else
		{
			ind = pStrItem.indexOf(".");
			if (ind!=-1)
			{
				strLen = pStrItem.substring(0,ind);
				strDec = pStrItem.substring(ind+1);
			}
			else
			{
				strLen = pStrItem;
				strDec = "";
			}
			len = pLength-pDecimals;
			//Ajuste LE e DE
            if (strLen.length() > len)
            {
                strLen =  strLen.substring(strLen.length() - len );
            }
            if (strDec.length() > pDecimals)
            {
                strDec =  strDec.substring(0, pDecimals);
            }
        	//preenche item com 0 no espaços vazio
			for (i=0;i<len-strLen.length();i++)
			{
				itemChar [charPosition++] = '0';
			}

			for(i=0;i<strLen.length();i++)
			{
				itemChar [charPosition++] = strLen.charAt(i);
        	}

        	//Parte decimal
        	for(i=0;i<strDec.length();i++)
			{
				itemChar [charPosition++] = strDec.charAt(i);
        	}
        	if (pDecimals > strDec.length())
        	{
        		for(i=strDec.length();i<pDecimals;i++)
        		{	itemChar[charPosition++] = '0';
        		}
        	}

	    }
    	charPosition--;
    	if (pIsNegNumber==1)
    	{

    		//se sinalizado switch
			switch (itemChar[charPosition])
			{
			case '0':
			{	itemChar[charPosition]='p';
				break;
			}
			case '1':
			{	itemChar[charPosition]='q';
				break;
			}
			case  '2':
			{	itemChar[charPosition]='r';
				break;
			}
			case  '3':
			{	itemChar[charPosition]='s';
				break;
			}
			case '4':
			{	itemChar[charPosition]='t';
				break;
			}
			case '5':
			{	itemChar[charPosition]='u';
				break;
			}
			case '6':
			{	itemChar[charPosition]='v';
				break;
			}
			case  '7':
			{	itemChar[charPosition]='w';
				break;
			}
			case '8':
			{	itemChar[charPosition]='x';
				break;
			}
			case '9':
			{	itemChar[charPosition]='y';
				break;
			}
			}
		}

		retString =String.valueOf(itemChar,0,pLength);


		//group menor que posição inicial
		if (pGroup.length() < pInit)
		{
			char groupChar[] = new char[pInit];
			groupChar = getArrayChar(pGroup,pInit);
			retString =String.valueOf(groupChar,0,pInit) + retString ;
			groupChar=null;

		}
		else  if (pGroup.length() < pEnd)
		{
			retString =pGroup.substring(0, pInit) + retString ;
		}
		else
		{
			retString = pGroup.substring(0, pInit) + retString + pGroup.substring(pEnd);
    	}
    	itemChar=null;
    	return(retString);
	}
	catch (Exception e)
    {
       	ShowErrorMsg (e,"groupItem");
    	return("");
    }
}

//groupItem
static public String groupItem(double pItem, String pGroup, int pInit, int pEnd, int pLength, int pDecimals,String pSigned)
    throws Exception
{
    try
    {
 		int negNumber=0;

 		String strItem = "";

		if ( (pSigned.equals("SIGNED")==true) && (pItem < 0) )
		{
			negNumber=1;
			pItem=Math.abs(pItem);
		}

 		strItem = cStr(pItem).trim();

		if(strItem.indexOf("E") > -1)
		{
    		return(groupItemNumeric((cStr(new BigDecimal(strItem))).trim(),pGroup, pInit, pEnd, pLength, pDecimals,negNumber));
    	}
    	else
    	{
			return(groupItemNumeric(strItem,pGroup, pInit, pEnd, pLength, pDecimals,negNumber));
    	}
    }
    catch (Exception e)
    {
        ShowErrorMsg (e,"groupItem");
    	return("");
    }
}


//groupItem
static public String groupItem(BigDecimal pItem, String pGroup, int pInit, int pEnd, int pLength, int pDecimals,String pSigned)
    throws Exception
{
    try
    {
 		int negNumber=0;
		if ( (pSigned.equals("SIGNED")==true) && (pItem.compareTo(bigZeros) < 0) )
		{
			negNumber=1;
			pItem=pItem.abs();
		}

    	return( groupItemNumeric((cStr(pItem)).trim(),pGroup, pInit, pEnd, pLength, pDecimals,negNumber));

    }
    catch (Exception e)
    {
        ShowErrorMsg (e,"groupItem");
    	return("");
    }
}


///////////////////////////////////////////////////////////////////////////////////////////////////
//SECAO FORMAT EXTRACT
///////////////////////////////////////////////////////////////////////////////////////////////////

/**
* Format a double value with leading zeros
*
* @param pNumber
* @param buffer
* @param posini
* @param posfin
* @param size
* @param scale
* @param signed
* @return
* @throws Exception
*/

public static char[] formatExtract(double pNumber, char[] buffer, int posini,
int posfin, int size, int scale, String signed) throws Exception {
	BigDecimal pBigNumber = new BigDecimal(new Double(pNumber).toString());
	return formatExtract(pBigNumber, buffer, posini, posfin, size, scale,signed);
}

/**
*
* Format a BigDecimal value with leading zeros at a target buffer at at
* specified position
*
* @param pNumber
* @param buffer
* @param posini
* @param posfin
* @param size
* @param scale
* @return
* @throws Exception
*/

public static char[] formatExtract(BigDecimal pNumber, char[] buffer, int posini,int posfin, int size, int scale, String signed) throws Exception {
    String str;
    str = (pNumber.setScale(scale, BigDecimal.ROUND_FLOOR)).unscaledValue().toString();
    buffer = lpad(str, '0', size, buffer, posini);
    return buffer;
}


public static char[] formatExtract(String pString, char[] buffer, int posini,
int posfin, int size) throws Exception {
	buffer = rpad(pString, ' ', size, buffer, posini);
	return buffer;
}


/**
* rpad - Right pad the sufix character to the specified string into the
* target buffer at position posini.
*
* @param str
* @param sufix
* @param size
* @param buffer
* @param posini
* @return
*/
public static char[] rpad(String str, char sufix, int size, char[] buffer,int posini) {
	if (str.length() > size) {
		str.getChars(0, size, buffer, posini);
	} else {
		str.getChars(0, str.length(), buffer, posini);
		for (int i = str.length(); i < size; i++) {
			buffer[posini + i] = sufix;
		}
	}
return buffer;
}

/**
* lpad - left pad the prefix character before the specified string into the
* target buffer at position posini.
*
* @param str
* @param prefix
* @param size
* @param buffer
* @param posini
* @return
*/
public static char[] lpad(String str, char prefix, int size, char[] buffer,int posini) {
	int len = size - str.length();
	for (int i = 0; i < len; i++) {
		buffer[posini + i] = prefix;
	}
	str.getChars(0, str.length(), buffer, posini + (size - str.length()));
	return buffer;
}



/* fix String */
public static String fix(String pString, int pLength)
    throws Exception
{
    try
    {
        if (pString.length() > pLength)
        {
            pString = pString.substring(0,pLength);
        }

        return(rTrim(pString));

    }

    catch (Exception e)
    {
        ShowErrorMsg (e, "fix");
        return ("");
    }
}
//format String
public static String format(String pString, int pLength)
    throws Exception
{
    try
    {
        if (pString.length() < pLength)
        {
            char init[];
            StringBuffer  mvStrBuffer =  new StringBuffer(pString);
            init = new char [pLength - mvStrBuffer.length()];
            for (int i = 0;i<pLength - mvStrBuffer.length();i++)
            {
                init[i]= ' ';
            }
            mvStrBuffer.append(init, 0,pLength - mvStrBuffer.length());

            pString = mvStrBuffer.toString();
        }
        else if (pString.length() > pLength)
        {
            pString = pString.substring(0,pLength);
        }

        return(pString);
    }
    catch (Exception e)
    {
        ShowErrorMsg (e, "format");
        return ("");
    }
}

/* Move Complexo - retorno String */
public  static String moveComplex(String pSource, int pPosition1, int pLength, String pDest, int pPosition2, int pDestLength)
    throws Exception
{
    try
    {
        if (pPosition1 > pSource.length())
            return pDest;

        if (pPosition2 > pDestLength)
            return pDest;

        // Criticar e spacos extra apos final do campo origem
        int posEnd = pPosition1 + pLength - 1;
        int extraSpaces = 0 ;
        if (posEnd > pSource.length())
        {   extraSpaces = posEnd - pSource.length();
            pLength = pLength - extraSpaces;
        }

        pDest = format(pDest, pDestLength);

        StringBuffer mvStrBuffer = new StringBuffer(pDest);

        if (pLength == 0)
        {
            pLength = pSource.length() - pPosition1 + 1;
        }

        if (pPosition2 + pLength - 1 < pDest.length())
        {
            mvStrBuffer = mvStrBuffer.replace(pPosition2 - 1, pPosition2 + pLength - 1, pSource.substring(pPosition1 - 1,pPosition1 + pLength - 1));
        }
        else
        {
            mvStrBuffer = mvStrBuffer.replace(pPosition2 - 1, pDest.length(), pSource.substring(pPosition1-1, pPosition1 +  pLength - 1));
        }

        pDest =fix(mvStrBuffer.toString(),pDestLength);

        return(pDest);
    }
    catch (Exception e)
    {
        ShowErrorMsg (e, "moveComplex");
        return ("");
    }
}

/**
 *moveSortA
 *
 */

public static String moveSortA(String pItem1, int pItem1Size, String pItem2, int pItem2Size)
throws Exception
{
	try
	{
		String aux = "";

		if(pItem1.length()== 0)
		{
			aux = pItem2.trim();
		}
		else
		{
			aux = pItem2.trim() + pItem1.trim();
		}

		ArrayList<String> list = new ArrayList<String>();

		//preenche o arraylist com strings de tamanho pItem1Size
		for(int i=0; i<aux.length();i += pItem1Size)
		{
			if((aux.length()-i)>=pItem1Size)
				list.add(new String(aux.substring(i,i+pItem1Size)));
			else
				list.add(new String(aux.substring(i)));
		}

		Collections.sort(list);
		aux = "";

		//busca os elementos do arraylist ordenados de forma crescente
		for(int i=0; i<list.size(); i++)
		{
			aux += list.get(i);
		}

		//trunca depois da ordenação
		if(aux.trim().length() > pItem2Size)
			aux = aux.substring(0,pItem2Size);

		return aux;
	}
	catch(Exception e)
	{
		ShowErrorMsg (e, "moveSORTA");
        return ("");
	}

}

public static String moveSortA(String pItem1, int pItem1Position1, int pItem1Size, String pItem2, int pItem2Size)
throws Exception
{
    try
    {
        String aux = "";

        if(pItem1.length()== 0)
        {
            aux = pItem2.trim();
        }
        else
        {
            aux = moveComplex(pItem1, pItem1Position1, pItem1Size, aux, 1,pItem1Size);
            aux = pItem2.trim() + aux;
        }

        ArrayList<String> list = new ArrayList<String>();

        //preenche o arraylist com strings de tamanho pItem1Size
        for(int i=0; i<aux.length();i += pItem1Size)
        {
            if((aux.length()-i)>=pItem1Size)
                list.add(new String(aux.substring(i,i+pItem1Size)));
            else
                list.add(new String(aux.substring(i)));
        }

        Collections.sort(list);
        aux = "";

        //busca os elementos do arraylist ordenados de forma crescente
        for(int i=0; i<list.size(); i++)
        {
            aux += list.get(i);
        }

        //trunca depois da ordenação
        if(aux.trim().length() > pItem2Size)
            aux = aux.substring(0,pItem2Size);

        return aux;
    }
    catch(Exception e)
    {
        ShowErrorMsg (e, "moveSORTA");
        return ("");
    }

}


/**
 *moveSortD
 *
 */
public static String moveSortD(String pItem1, int pItem1Size, String pItem2, int pItem2Size)
throws Exception
{
	try
	{
		String aux = "";

		if(pItem1.length()== 0)
		{
			aux = pItem2.trim();
		}
		else
		{
			aux = pItem2.trim() + pItem1.trim();
		}

		ArrayList<String> list = new ArrayList<String>();

		//preenche o arraylist com strings de tamanho pItem1Size
		for(int i=0; i<aux.length();i += pItem1Size)
		{
			if((aux.length()-i)>=pItem1Size)
				list.add(new String(aux.substring(i,i+pItem1Size)));
			else
				list.add(new String(aux.substring(i)));
		}

		Collections.sort(list);
		aux = "";

		//busca os elementos do arraylist ordenados de forma decrescente
		for(int i=list.size()-1; i>=0; i--)
		{
			aux += list.get(i);
		}

		//trunca depois da ordenação
		if(aux.trim().length() > pItem2Size)
			aux = aux.substring(0,pItem2Size);

		return aux;
	}
	catch(Exception e)
	{
		ShowErrorMsg (e, "moveSORTD");
        return ("");
	}

}

public static String moveSortD(String pItem1, int pItem1Position1, int pItem1Size, String pItem2, int pItem2Size)
throws Exception
{
    try
    {
        String aux = "";

        if(pItem1.length()== 0)
        {
            aux = pItem2.trim();
        }
        else
        {
            aux = moveComplex(pItem1, pItem1Position1, pItem1Size, aux, 1,pItem1Size);
            aux = pItem2.trim() + aux;
        }

        ArrayList<String> list = new ArrayList<String>();

        //preenche o arraylist com strings de tamanho pItem1Size
        for(int i=0; i<aux.length();i += pItem1Size)
        {
            if((aux.length()-i)>=pItem1Size)
                list.add(new String(aux.substring(i,i+pItem1Size)));
            else
                list.add(new String(aux.substring(i)));
        }

        Collections.sort(list);
        aux = "";

        //busca os elementos do arraylist ordenados de forma decrescente
        for(int i=list.size()-1; i>=0; i--)
        {
            aux += list.get(i);
        }

        //trunca depois da ordenação
        if(aux.trim().length() > pItem2Size)
            aux = aux.substring(0,pItem2Size);

        return aux;
    }
    catch(Exception e)
    {
        ShowErrorMsg (e, "moveSORTD");
        return ("");
    }

}

/**
* moveTimeMillis - Returns the current time in milliseconds
*
* @return
*/
static public double moveTimeMillis()
    throws Exception
{
    try
    {
        return(System.currentTimeMillis());
    }
    catch (Exception e)
    {
        ShowErrorMsg (e, "moveTimeMillis");
        return (0.0);
    }
}

/**
* Write Signed  number Extract in EBCDIC format
*
* @param pNumber
* @param buffer
* @param posini
* @param posfin
* @param size
* @param scale
* @param signed
* @return char[]
* @throws Exception
*/

public static char[] ExtractWriteEBCDIC(double pNumber, char[] buffer, int posini,int posfin, int size, int scale, String signed) throws Exception {
    return (ExtractWriteEBCDIC(cDec(pNumber), buffer, posini, posfin, size, scale, signed)) ;
}
/**
* Write Signed  number Extract in EBCDIC format
*
* @param pNumber
* @param buffer
* @param posini
* @param posfin
* @param size
* @param scale
* @param signed
* @return char[]
* @throws Exception
*/

public static char[] ExtractWriteEBCDIC(BigDecimal pNumber, char[] buffer, int posini,int posfin, int size, int scale, String signed) throws Exception {
    String str;
    String signal;
    if (pNumber.compareTo(bigZeros) >= 0)
        signal = "+";
    else
        signal="-";
    pNumber = pNumber.abs();
    str = (pNumber.setScale(scale, BigDecimal.ROUND_FLOOR)).unscaledValue().toString();
    buffer = XseedFunctions.lpad(str, '0', size, buffer, posini);
    if (signed.equals("SIGNED")==true)
    {       //se sinalizado switch
        if (signal.equals("+") )
        {
            switch (buffer[posfin-1])
            {
            case '0':
            {   buffer[posfin-1]='{';
                break;
            }
            case '1':
            {   buffer[posfin-1]='A';
                break;
            }
            case  '2':
            {   buffer[posfin-1]='B';
                break;
            }
            case  '3':
            {   buffer[posfin-1]='C';
                break;
            }
            case '4':
            {   buffer[posfin-1]='D';
                break;
            }
            case '5':
            {   buffer[posfin-1]='E';
                break;
            }
            case '6':
            {   buffer[posfin-1]='F';
                break;
            }
            case  '7':
            {   buffer[posfin-1]='G';
                break;
            }
            case '8':
            {   buffer[posfin-1]='H';
                break;
            }
            case '9':
            {   buffer[posfin-1]='I';
                break;
            }
            }
        }
        else
        {
            switch (buffer[posfin-1])
            {

            case '0':
            {
                buffer[posfin-1]='}';
                break;
            }
            case '1':
            {   buffer[posfin-1]='J';
                break;
            }
            case  '2':
            {   buffer[posfin-1]='K';
                break;
            }
            case  '3':
            {   buffer[posfin-1]='L';
                break;
            }
            case '4':
            {   buffer[posfin-1]='M';
                break;
            }
            case '5':
            {   buffer[posfin-1]='N';
                break;
            }
            case '6':
            {   buffer[posfin-1]='O';
                break;
            }
            case  '7':
            {   buffer[posfin-1]='P';
                break;
            }
            case '8':
            {   buffer[posfin-1]='Q';
                break;
            }
            case '9':
            {   buffer[posfin-1]='R';
                break;
            }
            }
        }
    }
    return buffer;
}
/**
* Read Signed  number Extract in EBCDIC format
*
* @param pNumber
* @param buffer
* @param posini
* @param posfin
* @param size
* @param scale
* @param signed
* @return String
* @throws Exception
*/

public static String ExtractReadEBCDIC(String pStr, int posini,int posfin, int size, int scale, String signed) throws Exception
{
   String field = pStr.substring(posini, posfin);

   char signal =' ';
   char lastChar = ' ';
   if (signed.equals("SIGNED"))
   {
            switch (field.charAt(size-1))
            {
                case '{' :
                {   lastChar='0';
                    break;
                }
                case 'A':
                {   lastChar='1';
                    break;
                }
                case 'B':
                {   lastChar='2';
                    break;
                }
                case 'C' :
                {   lastChar='3';
                    break;
                }
                case 'D':
                {   lastChar='4';
                    break;
                }
                case 'E' :
                {   lastChar='5';
                    break;
                }
                case 'F' :
                {   lastChar='6';
                    break;
                }
                case 'G' :
                {   lastChar='7';
                    break;
                }
                case 'H' :
                {   lastChar='8';
                    break;
                }
                case 'I' :
                {   lastChar='9';
                    break;
                }
                 case '}' :
                {   lastChar='0';
                    signal='-';
                    break;
                }
                case 'J':
                {   lastChar='1';
                    signal='-';
                    break;
                }
                case 'K':
                {   lastChar='2';
                    signal='-';
                    break;
                }
                case 'L' :
                {   lastChar='3';
                    signal='-';
                    break;
                }
                case 'M':
                {   lastChar='4';
                    signal='-';
                    break;
                }
                case 'N' :
                {   lastChar='5';
                    signal='-';
                    break;
                }
                case 'O' :
                {   lastChar='6';
                    signal='-';
                    break;
                }
                case 'P' :
                {   lastChar='7';
                    signal='-';
                    break;
                }
                case 'Q' :
                {   lastChar='8';
                    signal='-';
                    break;
                }
                case 'R' :
                {   lastChar='9';
                    signal='-';
                    break;
                }
                }
            }
            return (signal+field.substring(0, size-1)+lastChar);
}

public static int calcDaynum(Calendar pCal, double pBase) throws Exception{
    int year    = pCal.get(pCal.YEAR);
    int month   = pCal.get(pCal.MONTH);  //Jan é mes 0
    month++;
    int day     = pCal.get(pCal.DAY_OF_MONTH);
	int flag=adjustYear(year);

    int diffDay=year-(int)pBase;
    diffDay=diffDay*365;
	diffDay=diffDay+((year-(int)pBase)/4)+flag;
	if ((flag==1)&&(month==1 || month==2)){

		diffDay=diffDay-1;
	}
	diffDay=diffDay+ calcDays(month,day);
	diffDay--; // nao contar con 0101 do ano base
    return diffDay;

}
private static int adjustYear(int pYear)throws Exception
{
	int flag=0;
    if(pYear%100==0)
	{
    	if(pYear%400==0) {
			flag=1;
		}
	}else {
        if(pYear%4==0) {
			flag=1;
        }
    }
	return flag;
}
private static int calcDays(int pMonth,int pDay)  throws Exception
{
	int days=0;
	switch(pMonth)
	{       case 1:days=pDay;	  break;
			case 2:days=31+pDay;    break;
			case 3:days=31+28+pDay;  break;
            case 4:days=31+28+31+pDay;  break;
            case 5:days=31+28+31+30+pDay;  break;
            case 6:days=31+28+31+30+31+pDay;   break;
            case 7:days=31+28+31+30+31+30+pDay;   break;
            case 8:days=31+28+31+30+31+30+31+pDay;   break;
            case 9:days=31+28+31+30+31+30+31+31+pDay;   break;
            case 10:days=31+28+31+30+31+30+31+31+30+pDay;   break;
            case 11:days=31+28+31+30+31+30+31+31+30+31+pDay;  break;
            case 12:days=31+28+31+30+31+30+31+31+30+31+30+pDay;
     }
     return days;
}
public static String[] ReDimPreserve(String[] pArray, int pDimension) throws Exception {
	String[] wArray = new String[pDimension + 1];
	
	for (int i = 0; i < wArray.length; i++) {
		if (pArray != null && pArray.length > i) {
			wArray[i] = pArray[i];
		} else {
			wArray[i] = null; // for Variant arrays, default value is "Empty"
		}
	}	
	return wArray;
}

}