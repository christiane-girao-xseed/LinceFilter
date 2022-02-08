package HLegacy;
import java.io.*;
import java.util.*;

public class XseedCompareOwner implements Comparator<Object>
{
    public int compare(Object e1, Object e2)
    {
        File f1 = (File)e1;
        File f2 = (File)e2;
        
        return(f1.getName().compareTo(f2.getName()));
    }
}