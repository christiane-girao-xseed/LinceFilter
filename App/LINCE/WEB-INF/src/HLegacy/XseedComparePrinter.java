package HLegacy;
import java.util.*;
import javax.print.*;

public class XseedComparePrinter implements Comparator<Object>
{
    public int compare(Object e1, Object e2)
    {
        PrintService f1 = (PrintService)e1;
        PrintService f2 = (PrintService)e2;
        
        return(f1.getName().compareTo(f2.getName()));
    }
}
