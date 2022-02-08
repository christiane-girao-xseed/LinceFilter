package HLegacy;
import java.io.*;
import java.util.*;

public class XseedCompareDateTime implements Comparator<Object>
{
    public int compare(Object e1, Object e2)
    {
        File f1 = (File)e1;
        File f2 = (File)e2;
        
        String f1Name = f1.getName();
        String f2Name = f2.getName();

        int ind1 = 0;
        int ind2 = 0;
        String sdDate1 = "";
        String sdDate2 = "";
        String xDate ="";
            
     // Path 1
        ind1 = f1Name.indexOf('(');
        ind2 = f1Name.indexOf(')');
        if (ind1 == -1)
        {
            ind1 = f1Name.indexOf('-');            
            ind1 = f1Name.indexOf('-',ind1+1);            
            ind2 = f1Name.indexOf('-',ind1+1);            
            if ((ind1 == -1 ) || (ind2 == -1))
            {    sdDate1 = "0";
            }
            else
            {   
                xDate = f1Name.substring(ind1+1, ind2);
                sdDate1 = xDate.substring(0,4) + "-"+ xDate.substring(4,6) + "-"+ xDate.substring(6);                
                ind1 = f1Name.indexOf('.',ind2+1);
                sdDate1 = sdDate1 + " " + f1Name.substring(ind2+1,ind1-3);
                
            }
            
        }
        else
        {
            sdDate1 = f1Name.substring(ind1+1, ind2);
        }
        
     // Path 2
        ind1 = f2Name.indexOf('(');
        ind2 = f2Name.indexOf(')');
        if (ind1 == -1)
        {
            
            ind1 = f2Name.indexOf('-');            
            ind1 = f2Name.indexOf('-',ind1+1);            
            ind2 = f2Name.indexOf('-',ind1+1);            
            if ((ind1 == -1 ) || (ind2 == -1))
            {    sdDate1 = "0";
            }
            else
            {   xDate = f2Name.substring(ind1+1, ind2);
                sdDate2 = xDate.substring(0,4) + "-"+ xDate.substring(4,6) + "-"+ xDate.substring(6);
                ind1 = f2Name.indexOf('.',ind2+1);
                sdDate2 = sdDate2 + " " + f2Name.substring(ind2+1,ind1-3);
            }
        
        }
        else
        {
            sdDate2 = f2Name.substring(ind1+1, ind2);
        }

        return((sdDate1.compareTo(sdDate2)) * -1);
    }   
}