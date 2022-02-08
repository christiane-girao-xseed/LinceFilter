package HLegacy;
import java.io.*;
import java.util.*;

public class XseedCompareName implements Comparator<Object>
{
        public int compare(Object e1, Object e2)
    {
        File f1 = (File)e1;
        File f2 = (File)e2;
        

        int ind = 0;
        String sdName1 = "";
        String sdName2 = "";

        //path 1
        ind = f1.getName().indexOf(')');
        if (ind == -1)
        {
            ind = f1.getName().indexOf('-');
            if (ind == -1)
            {    sdName1 = f1.getName();
            }
            else
            {   sdName1 = f1.getName().substring (ind+1);
                ind = f1.getName().indexOf('-');
                if (ind == -1)
                {
                    sdName1 = sdName1.substring (1, ind-1);
                }                 
             
            }    
        }
        else
        {
            sdName1 = f1.getName().substring(ind+1);
        }
        //path 2
        ind = f2.getName().indexOf(')');
        if (ind == -1)
        {
            ind = f2.getName().indexOf('-');
            if (ind == -1)
            {    sdName2 = f2.getName();
            }
            else
            {   sdName2 = f2.getName().substring (ind+1);
                ind = f2.getName().indexOf('-');
                if (ind == -1)
                {
                    sdName2 = sdName2.substring (1, ind-1);
                }                             
            }    
        }
        else
        {
            sdName2 = f2.getName().substring(ind+1);
        }
        return(sdName1.compareTo(sdName2));
    }
}
